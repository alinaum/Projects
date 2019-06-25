package com.glue;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import support.*;
import java.util.concurrent.TimeUnit;

public class TestUtil {
  public static WebDriver driver;
  public static User pretty = new User();
  
  public static WebElement any(String css) {
    return any(driver.findElement(By.cssSelector("html")), css);
  }
  
  public static WebElement any(WebElement parent, String css) {
    try {
      return parent.findElement(By.cssSelector(css));
    } catch(NoSuchElementException e) {
      return null;
    }
  }
  
  public static void addTodo(String text) {
    any("#new-todo").sendKeys(text + "\n");
  }

  public static WebElement todoAt(int index) {
    return any("#todo-list li:nth-child("+(index + 1)+")");
  }
  
  public static void markTodoDone(WebElement todo) {
    any(todo, "input.check").click();    
  }
  
  public static boolean isDone(WebElement todo) {
    return any(todo, ".todo.done") != null; 
  }
  public static void goTologin() {
      driver.findElement(By.xpath("//*[@id='header-user-anonymous']/a")).click();
  }

  public static void clickEntrarLogin() {
    driver.findElement(By.xpath("//*[@id='header-user-anonymous']/ul/li[1]/a")).click();
  }
   
  public static void deleteTodo(WebElement todo) {
    new Actions(driver)
      .moveToElement(todo)
      .moveToElement(any(todo, ".todo-destroy"))
      .click().build().perform();
  }

  public static void goToNewAccount() {
    WebDriverWait wait = new WebDriverWait(driver, 15);
    wait.until(ExpectedConditions.elementToBeClickable(By.id("verify-email-button")));
    driver.findElement(By.id("verify-email-button")).click();          
  }
  
  public static void emailNewAccount() {
    String emailRandom = "Pretty"+System.currentTimeMillis()+"@gmail.com";
    driver.findElement(By.id("sign-up-username")).sendKeys(emailRandom);          
  }

  public static void resultscreenshot() {
    File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    try {
      FileUtils.copyFile(src, new File("/home/danilo/Desktop/"+System.currentTimeMillis()+".png"));
    }catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
  public static void quitBrowserUtil() {
		driver.quit();
  }

  public static void fillAllFilds(boolean fillAll) {
    //prepare variables
    pretty.setName("Pretty Name");
    String lastName = "Pretty Last";
    String cep = "13036265";
    String numAdrress = "00";
    String numPhone = "19999999999";
    String passValue = "Senha!@3";
    String cpf = generateCPF();
    
    //skip a mandatory field
    if (fillAll) {
        driver.findElement(By.id("password")).sendKeys(passValue);      
    }

    driver.findElement(By.id("person-name")).sendKeys(pretty.getName());  
    driver.findElement(By.id("person-last-name")).sendKeys(lastName); 
    driver.findElement(By.id("address-zipcode")).sendKeys(cep);
    driver.findElement(By.id("address-number")).sendKeys(numAdrress);
    driver.findElement(By.xpath("//*[@id='dateofbirth-day']/option[2]")).click();
    driver.findElement(By.xpath("//*[@id='dateofbirth-month']/option[3]")).click();
    driver.findElement(By.xpath("//*[@id='dateofbirth-year']/option[37]")).click();
    driver.findElement(By.id("phones-home")).sendKeys(numPhone);
    driver.findElement(By.id("label-female")).click();  
    driver.findElement(By.id("cpf")).sendKeys(cpf);   

  }

  public static String generateCPF(){
    GeraCpf gerador = new GeraCpf();
    String cpfResult = gerador.cpf(true);
    return cpfResult;
  }

  public static void pressContinuar() {
		driver.findElement(By.id("save-register-natural")).click(); 
  }

  public static  String getUserLogged() {
    String userLogged = driver.findElement(By.id("username-logged")).getText();
    return userLogged;
  }

  public static String getUserName() {
    String userName = pretty.getName();
    return userName;
  }

  public static String getErrorMessage() {
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    String errorMessage = driver.findElement(By.className("error-message")).getText();
    return errorMessage;
  }
}
