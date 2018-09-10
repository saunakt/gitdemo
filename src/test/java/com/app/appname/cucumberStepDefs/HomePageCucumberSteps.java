package com.app.appname.cucumberStepDefs;

import java.util.List;
import com.app.appname.serenityStepDefs.HomePageSerenitySteps;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class HomePageCucumberSteps {

	@Steps
	HomePageSerenitySteps objHomePageSerenity;

	@Then("^User lands into Home Page$")
	public void user_lands_into_Home_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		objHomePageSerenity.verifyUserIsOnHomePage();
		
	}
	
	
	
}
