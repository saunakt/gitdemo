package com.app.appname.pageObjects;

import static org.hamcrest.CoreMatchers.equalTo;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.app.appname.utilities.DriverGetterSetter;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;

public class CommonObjects extends PageObject {
	WebDriver driver= DriverGetterSetter.getDriver();
	
	
}
