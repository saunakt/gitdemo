package com.app.appname.pageObjects;


import static org.hamcrest.Matchers.*;
import org.apache.log4j.Logger;
import org.hamcrest.MatcherAssert;
import com.app.appname.TestData.PageLevelValidationData;
import com.app.appname.pageObjects.locators.HomePageLocators;
import com.app.appname.utilities.FrameworkUtilities;

public class HomePage extends CommonObjects {

	static Logger logger = Logger.getLogger(HomePage.class);

	public void isOnHomePage() {
		try {
			logger.info("isOnHomePage: Home Page ");
			MatcherAssert.assertThat(FrameworkUtilities.findElementByLocator(HomePageLocators.HOMEPAGE_TITLE).getText(), equalToIgnoringCase(PageLevelValidationData.HOME_PAGE_HOME_LINK_TEXT));
		}
		 catch (Exception e) {
			logger.error("isOnHomePage: Exception thrown -- " + FrameworkUtilities.getStackTrace(e));
			MatcherAssert.assertThat(e.getMessage(), false);
		}

	}

}
