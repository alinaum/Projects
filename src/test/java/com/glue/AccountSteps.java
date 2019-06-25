package com.glue;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static com.glue.TestUtil.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import support.Browser;

public class AccountSteps {

	WebDriver driver;

	@After
	public void quitBrowser() {
		quitBrowserUtil();
	}

	@Given("^that I'm in login page")
	public void am_I_on_login_page() throws Throwable {
		TestUtil.driver = driver = Browser.launch();
        driver.get("https://www.netshoes.com.br/login");      
	}

	@And("^I click in Prosseguir")
	public void click_on_Prosseguir() throws Throwable {
		goToNewAccount();
		resultscreenshot();
	}

	@Given("^that I informe my email")
	public void i_informe_email() throws Throwable {
		emailNewAccount();
	}
	
    @Then("^I should be able to see the login page")
    public void i_can_see_loggin() throws Throwable {
		assertThat(driver.getCurrentUrl(), is("https://www.netshoes.com.br/login"));
	}

	@When("^I fill the mandatory fields")
	public void fill_mandatory_fields() throws Throwable {
		fillAllFilds(true);
	}

	@And("^I press Continuar")
	public void press_continuar() throws Throwable {
		pressContinuar();
		resultscreenshot();
	}

	@Then("^my new account is created")
    public void account_is_created() throws Throwable {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		assertThat(driver.getCurrentUrl(), is("https://www.netshoes.com.br/"));			
		
		assertThat(getUserName(), is (getUserLogged()));
		resultscreenshot();
	}

	@When("^I do not all fill the mandatory fields")
	public void not_fill_mandatory_fields() throws Throwable {
		fillAllFilds(false);
	}

	@Then("^The error message is displayed")
    public void error_message_display() throws Throwable {
		String expectedMessage = "O campo Senha é obrigatório.";
		assertThat(getErrorMessage(), is (expectedMessage));
	}

}
