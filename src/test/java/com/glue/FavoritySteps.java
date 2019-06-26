package com.glue;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static com.glue.TestUtil.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.Color;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import support.Browser;

public class FavoritySteps {

	WebDriver driver;

	@After
	public void quitBrowserAndClean() {
		quitBrowserUtil();
    }

    @Given("^I'm in the home page to press heart$")
	public void am_I_on_home_page_favority() throws Throwable {
		TestUtil.driver = driver = Browser.launch();
        driver.get("https://www.netshoes.com.br/");
	}

    @And("^I go to login")
    public void that_I_m_looged_in() throws Throwable {
        goTologin();     
        clickEntrarLogin();
    }

    @And("^I fill my user name and password")
    public void fill_user_password() throws Throwable {
        logInUser();      
    }

    @Then("^I should be logged in")
    public void i_m_logged_in() throws Throwable {
        assertThat(getUserName(), is (getUserLogged()));
    }

    @And("^I'm on the home page")
    public void i_m_on_the_home_page() throws Throwable {

    }

    @Given("^that I click on the Todas as categorias tab")
    public void i_click_on_the_Todas_as_categorias_tab() throws Throwable {
        goToAllCategories();
    }

    @Given("^I choose one category")
    public void i_choose_one_item() throws Throwable {
        chooseOneCategory();
    }

    @Given("^I choose the first item from the category")
    public void i_choose_the_first_item_on_the_category() throws Throwable {
        chooseOneItem();
    }

    @Given("^I choose the first display item")
    public void i_choose_the_first_display_item() throws Throwable {
        chooseFirstDisplay();
        clickProduct();

    }

    @Given("^I click on favority list icon$")
    public void i_click_on_favority_list_icon() throws Throwable {
        clickHeart();
    }

    @Then("^the item is added to favority list$")
    public void the_item_is_added_to_favority_list() throws Throwable {
        String expectedColor = "rgba(235, 0, 19, 1)";
        assertThat(getColorHeart(), is (expectedColor));
        clickHeart();
        
    }
}