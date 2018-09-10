package com.app.appname.serenityStepDefs;

import static org.hamcrest.CoreMatchers.equalTo;
import org.hamcrest.MatcherAssert;
import com.app.appname.TestData.PageLevelValidationData;
import com.app.appname.pageObjects.LoginPage;
import com.app.appname.utilities.FrameworkUtilities;

import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Step;

public class LoginPageSerenitySteps {
	
	@ManagedPages
	LoginPage objLoginPage;
	
	@Step
	public void openFACEBOOKPage() {
		objLoginPage.launchFACEBOOK();
		
	}
	
	@Step
	public void verifyUserIsOnLoginPage() {
		objLoginPage.isFACEBOOKLogInPage();
	}

	@Step
	public void enterEmailId(String companyName) {	
		objLoginPage.enterEmailId(companyName);
	}

	@Step
	public void enterPassword(String password) {
		objLoginPage.enterPassword(password);	
	}

	@Step
	public void clickOnLogInButton() {
		objLoginPage.clickLogInButton();	
	}
		
}
