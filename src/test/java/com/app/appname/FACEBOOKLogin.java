
package com.app.appname;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;


import org.apache.log4j.Logger;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/FACEBOOKFeatures/FACEBOOK Login.feature")
public class FACEBOOKLogin {
	
	static Logger logger = Logger.getLogger(FACEBOOKLogin.class);
	@BeforeClass
	public static void setup() {
		logger.info("Running Feature- FACEBOOK Login Feature");
	}
}
