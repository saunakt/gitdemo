package com.app.appname.cucumberStepDefs;

import java.util.List;
import com.app.appname.serenityStepDefs.LoginPageSerenitySteps;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class LoginPageCucumberSteps {

	@Steps
	LoginPageSerenitySteps objLoginPageSerenity;
	
		
	@Given("^User is on FACEBOOK page$")
	public void user_is_on_FACEBOOK_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		objLoginPageSerenity.openFACEBOOKPage();
		objLoginPageSerenity.verifyUserIsOnLoginPage();
	}

	@Given("^User enters EmailId as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
	public void user_enters_EmailId_as_and_Password_as(String usrName, String pwd) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		objLoginPageSerenity.enterEmailId(usrName);
		objLoginPageSerenity.enterPassword(pwd);
	}
	

	@Given("^Clicks on LogIn Button$")
	public void clicks_on_LogIn_Button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		objLoginPageSerenity.clickOnLogInButton();
	}

}
