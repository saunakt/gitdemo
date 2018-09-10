package com.app.appname.serenityStepDefs;

import static org.hamcrest.CoreMatchers.equalTo;
import org.hamcrest.MatcherAssert;
import com.app.appname.pageObjects.HomePage;
import com.app.appname.pageObjects.locators.HomePageLocators;
import com.app.appname.utilities.FrameworkUtilities;

import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Step;

public class HomePageSerenitySteps {

	@ManagedPages
	HomePage objHomePage;

	@Step
	public void verifyUserIsOnHomePage() {
		objHomePage.isOnHomePage();
		
	}
}
