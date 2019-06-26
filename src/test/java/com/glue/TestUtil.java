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
  
  public static void goTologin() {
      WebDriverWait wait = new WebDriverWait(driver, 15);
      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='header-user-anonymous']/a")));
      driver.findElement(By.xpath("//*[@id='header-user-anonymous']/a")).click();
  }

  public static void clickEntrarLogin() {
    WebDriverWait wait = new WebDriverWait(driver, 15);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='header-user-anonymous']/ul/li[1]/a")));
    driver.findElement(By.xpath("//*[@id='header-user-anonymous']/ul/li[1]/a")).click();
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
      String userHome = System.getProperty("user.home");
      FileUtils.copyFile(src, new File(userHome+"/Desktop/"+System.currentTimeMillis()+".png"));
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

  public static void goToAllCategories() {
    driver.findElement(By.className("navbar__hamburger--title")).click();
  }

  public static void chooseOneItem() {
    driver.findElement(By.xpath("//*[@id='header-content']/header/div[2]/div/div[2]/div/div[3]/ul[1]/li[2]")).click(); 
  }

  public static void chooseOneCategory() {
    driver.findElement(By.xpath("//*[@id='header-content']/header/div[2]/div/div[2]/div/ul/li[1]")).click(); 
    
  }

  public static void chooseFirstDisplay() {
    WebDriverWait wait = new WebDriverWait(driver, 15);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='header-content']/header/div[2]/div/div[2]/div/div[3]/ul[1]/li[2]/a")));
    driver.findElement(By.xpath("//*[@id='header-content']/header/div[2]/div/div[2]/div/div[3]/ul[1]/li[2]/a")).click();
  }

  public static void clickProduct() {
    WebDriverWait wait = new WebDriverWait(driver, 15);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='item-list']/div[1]/div[1]/div[2]/a")));
    driver.findElement(By.xpath("//*[@id='item-list']/div[1]/div[1]/div[2]/a")).click();
  }

  public static void clickHeart() {
    driver.findElement(By.className("wishlist__heart")).click();
  }

  
  public static void logInUser() {
    pretty.setName("Pretty Name");
    String emailUser =  "Pretty1561491414916@gmail.com";
    String passwordUser = "Senha!@3";

    WebDriverWait wait = new WebDriverWait(driver, 15);
    wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));

    driver.findElement(By.id("username")).sendKeys(emailUser); 
    driver.findElement(By.id("password")).sendKeys(passwordUser);  
    driver.findElement(By.id("login-button")).click(); 
    
  }

  public static String getColorHeart() {
    WebDriverWait wait = new WebDriverWait(driver, 15);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='buy-button-wrapper']/div/span/i")));
    String color = driver.findElement(By.xpath("//*[@id='buy-button-wrapper']/div/span/i")).getCssValue("color");
    return color;
  }

  public static  String getUserLogged() {
    WebDriverWait wait = new WebDriverWait(driver, 15);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username-logged")));
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
