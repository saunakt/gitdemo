package com.app.appname.pageObjects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static org.hamcrest.Matchers.*;
import com.app.appname.TestData.PageLevelValidationData;
import com.app.appname.cucumberStepDefs.LoginPageCucumberSteps;
import com.app.appname.pageObjects.locators.HomePageLocators;
import com.app.appname.pageObjects.locators.LoginPageLocators;
import com.app.appname.utilities.FrameworkUtilities;
import com.app.appname.utilities.PropertyReader;

public class LoginPage extends CommonObjects {
	static Logger logger = Logger.getLogger(LoginPage.class);

	public void launchFACEBOOK() {
		try {
			logger.info("launchFACEBOOK: Launching FACEBOOK ");
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			String url = new PropertyReader().readProperty("appurl");
			openAt(url);
		} catch (Exception e) {
			logger.error("launchFACEBOOK: Exception thrown -- " + FrameworkUtilities.getStackTrace(e));
			MatcherAssert.assertThat(e.getMessage(), false);
		}
	}

	public void isFACEBOOKLogInPage() {
		try {
			logger.info("verifyLogInPageLanding: Verifying user is on Login Page ");
			MatcherAssert.assertThat(FrameworkUtilities.getCurrentPageTitle(), equalToIgnoringCase(PageLevelValidationData.LOGINPAGE_PAGE_TITLE));
		} catch (Exception e) {
			logger.error("verifyLogInPageLanding: Exception thrown -- " + FrameworkUtilities.getStackTrace(e));
			MatcherAssert.assertThat(e.getMessage(), false);
		}
	}

	
	public void enterEmailId(String userName) {
		try {
			logger.info("enterEmailId: Enter EmailId ");
			FrameworkUtilities.enterText(LoginPageLocators.EMAIL_EDITFIELD, userName);
		} catch (Exception e) {
			logger.error("enterEmailId: Exception thrown -- " + FrameworkUtilities.getStackTrace(e));
			MatcherAssert.assertThat(e.getMessage(), false);
		}
	}

	public void enterPassword(String password) {
		try {
			logger.info("enterPassword: Enter Password ");
			FrameworkUtilities.enterText(LoginPageLocators.PASSWORD_INPUTFIELD, password);
		} catch (Exception e) {
			logger.error("enterPassword: Exception thrown -- " + FrameworkUtilities.getStackTrace(e));
			MatcherAssert.assertThat(e.getMessage(), false);
		}
	}

	public void clickLogInButton() {
		try {
			logger.info("clickLogInButton: Click On The SignIn Button ");
			FrameworkUtilities.clickElement(LoginPageLocators.LOGIN_BUTTON);
		} catch (Exception e) {
			logger.error("clickLogInButton: Exception thrown -- " + FrameworkUtilities.getStackTrace(e));
			MatcherAssert.assertThat(e.getMessage(), false);
		}

	}

}