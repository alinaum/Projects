package com.glue;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static com.glue.TestUtil.*;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import support.Browser;

public class LoginSteps {

	WebDriver driver;

	@After
	public void quitBrowser() {
		quitBrowserUtil();
	}

	@Given("^that I'm on the home page")
	public void am_I_on_home_page() throws Throwable {
		TestUtil.driver = driver = Browser.launch();
		driver.get("https://www.netshoes.com.br/");
	}

	@When("^I click on Entrar")
	public void click_on_Entrar() throws Throwable {
		goTologin();
	}

	@When("^I choose loggin$")
	public void i_choose_loggin() throws Throwable {
		clickEntrarLogin();
	}

	@Then("^I'm on loggin Page$")
	public void im_on_loggin_Page() throws Throwable {
		assertThat(driver.getCurrentUrl(), is("https://www.netshoes.com.br/login"));
		resultscreenshot();
	}

}
