package com.app.appname.utilities;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import org.openqa.selenium.JavascriptExecutor;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.jruby.RubyProcess.Sys;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * <h1>Framework Utility Functions Library</h1>
 * Contains functions which can be reused across entire test scripts
 */

public class FrameworkUtilities {
	private static WebDriver driver = DriverGetterSetter.getDriver();
	static Logger logger = Logger.getLogger(FrameworkUtilities.class);
	

	/**
	 ***************************************************************************************
	 * Wait time so that script will wait to continue to next step.
	 * 
	 * @param milliSeconds
	 *            This is wait time in milli seconds.
	 ***************************************************************************************
	 */
	public static void doWait(long milliSeconds) {
	try {
	Thread.sleep(milliSeconds);
	} catch (Exception e) {
	logger.error("doWait: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	}
	}

	/**
	 **************************************************************************************
	 * Wait till the page loads with in the provided time units.
	 * 
	 * @param loadTime
	 *            This is load time in seconds
	 **************************************************************************************
	 */
	public static void waitTillPageLoad(int loadTime) {
	try {
	driver.manage().timeouts().pageLoadTimeout(loadTime, TimeUnit.SECONDS);
	ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
	public Boolean apply(WebDriver driver) {
	return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
	.equals("complete");
	}
	};
	Thread.sleep(2000);
	WebDriverWait wait = new WebDriverWait(driver, loadTime);
	wait.until(expectation);
	} catch (Exception e) {
	logger.error("waitTillPageLoad: Exception thrown -- " + getStackTrace(e));
	Assert.fail("Timeout waiting for Page Load Request to complete.");
	}
	}

	/**
	 **************************************************************************************
	 * Wait till the page loads with in the provided time units.
	 * 
	 * @param locator
	 *            This is locator in String format
	 **************************************************************************************
	 */
	public static void clickElement(String locator) {
	try {
	
	int timeout=5;
	WebElement webElement = findElementByLocator(locator);
	
	WebDriverWait wait = new WebDriverWait(driver, timeout);
	wait.until(ExpectedConditions.elementToBeClickable(webElement));
	
	webElement.click();
	} catch (Exception e) {
	logger.error("clickElement: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	}
	}
	
	/**
	 **************************************************************************************
	 * Wait till the page loads with in the provided time units.
	 * 
	 * @param locator
	 *            This is locator in String format
	 **************************************************************************************
	 */
	public static void enterText(String locator, String text) {
	try {
	WebElement webElement = findElementByLocator(locator);
	webElement.clear();
	webElement.sendKeys(text);
	} catch (Exception e) {
	logger.error("enterText: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	}
	}
	
	
	/**
	 **************************************************************************************
	 * Select the Name from the drop down of the given drop down locator.
	 * 
	 * @param locator
	 *            This is locator in String format.
	 * @param Name
	 *            This is name which needs to be selected in String format.
	 **************************************************************************************
	 */
	public static void selectDropDownElementByVisibleText(String locator, String Name) {
	try {
	Select selectitem = new Select(findElementByLocator(locator));
	selectitem.selectByVisibleText(Name);
	} catch (Exception e) {
	logger.error("selectDropDownElementByVisibleText: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	}
	}
	
	/**
	 **************************************************************************************
	 * Verify if checkbox is selected
	 * 
	 * @param locator
	 *            This is locator in String format.
	 * 
	 **************************************************************************************
	 */

	public static boolean isCheckboxSelected(String locator) {
	try {

	WebElement checkBox = findElementByLocator(locator);
	boolean checkedStatus = checkBox.isSelected();
	return checkedStatus;

	} catch (Exception e) {
	logger.error("isCheckboxSelected: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return false;
	}

	}

	/**
	 ***************************************************************************************
	 * Select the Value from the drop down of the given drop down locator.
	 * 
	 * @param locator
	 *            This is locator in String format.
	 * @param value
	 *            This is value which needs to be selected in String format.
	 ***************************************************************************************
	 */
	public static void selectDropDownElementByValue(String locator, String value) {
	try {
	Select selectitem = new Select(findElementByLocator(locator));
	selectitem.selectByValue(value);
	} catch (Exception e) {
	logger.error("selectDropDownElementByValue: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	}
	}

	/**
	 *****************************************************************************************
	 * Select the Index from the drop down of the given drop down locator.
	 * 
	 * @param locator
	 *            This is locator in String format.
	 * @param index
	 *            This is index which needs to be selected in Integer format.
	 *****************************************************************************************
	 */
	public static void selectDropDownElementByIndex(String locator, int index) {
	try {
	Select selectitem = new Select(findElementByLocator(locator));
	selectitem.selectByIndex(index);
	} catch (Exception e) {
	logger.error("selectDropDownElementByIndex: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	}
	}

	/**
	 ******************************************************************************************
	 * Click any element from given List.
	 * 
	 * @param locator
	 *            This is locator in String format.
	 * @param valueToSelect
	 *            This is value to select in String format.
	 ******************************************************************************************
	 */
	public static void clickElementFromListOfElements(String locator, String valueToSelect) {
	try {
	List<WebElement> lst = getElementListByLocator(locator);
	for (WebElement f : lst) {
	//System.out.println("value in the list : " + f.getText());
	if (valueToSelect.equals(f.getText())) {
	//System.out.println(f.getText());
	f.click();
	break;
	}
	}
	} catch (Exception e) {
	logger.error("clickElementFromListOfElements: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	}
	}

	/**
	 *****************************************************************************************
	 * Click on the given radio button if not selected.
	 * 
	 * @param locator
	 *            This is locator in String format.
	 *****************************************************************************************
	 */
	public static void selectRadioButton(String locator) {
	try {
	WebElement radio = findElementByLocator(locator);
	boolean checkstatus;
	checkstatus = radio.isSelected();
	if (checkstatus == false) {
	radio.click();
	}
	} catch (Exception e) {
	logger.error("selectRadioButton: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	}
	}

	/**
	 *****************************************************************************************
	 * Click on the given radio button if selected.
	 * 
	 * @param locator
	 *            This is locator in String format.
	 *****************************************************************************************
	 */
	public static void deselectRadioButton(String locator) {
	try {
	WebElement radio = findElementByLocator(locator);
	boolean checkstatus;
	checkstatus = radio.isSelected();
	if (checkstatus == true) {
	radio.click();
	}
	} catch (Exception e) {
	logger.error("deselectRadioButton: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	}
	}

	/**
	 ****************************************************************************************
	 * Find element based on the input locator.
	 * 
	 * @param locator
	 *            This is locator in String format.
	 * @return WebElement.
	 ***************************************************************************************
	 */
	public static WebElement findElementByLocator(String locator) {
	try {
	long waitTime = 120;
	WebDriverWait w = new WebDriverWait(driver, waitTime);
	if (locator.startsWith("css_")) {
	WebElement we = w.until(
	ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator.replace("css_", ""))));
	return we;
	} else if (locator.startsWith("xpath_")) {
	WebElement we = w.until(
	ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.replace("xpath_", ""))));
	return we;
	} else if (locator.startsWith("id_")) {
	WebElement we = w
	.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator.replace("id_", ""))));
	return we;
	} else if (locator.startsWith("name_")) {
	WebElement we = w.until(
	ExpectedConditions.visibilityOfElementLocated(By.name(locator.replace("name_", ""))));
	return we;
	} else if (locator.startsWith("link_")) {
	WebElement we = w.until(
	ExpectedConditions.visibilityOfElementLocated(By.linkText(locator.replace("link_", ""))));
	return we;
	} else if (locator.startsWith("partial_")) {
	WebElement we = w.until(ExpectedConditions
	.visibilityOfElementLocated(By.partialLinkText(locator.replace("partial_", ""))));
	return we;
	} else if (locator.startsWith("class_")) {
	WebElement we = w.until(
	ExpectedConditions.visibilityOfElementLocated(By.className(locator.replace("class_", ""))));
	return we;
	} else if (locator.startsWith("tag_")) {
	WebElement we = w.until(
	ExpectedConditions.visibilityOfElementLocated(By.tagName(locator.replace("tag_", ""))));
	return we;
	} else
	return null;
	} catch (Exception e) {
	logger.error("findElementByLocator: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return null;
	}
	}



	/**
	 ***************************************************************************************
	 * Find list of Web elements based on the input locator.
	 * 
	 * @param locator
	 *            This is locator in String format.
	 * @return List of WebElements.
	 ***************************************************************************************
	 */
	public static List<WebElement> getElementListByLocator(String locator) {
	try {
	if (locator.startsWith("css_")) {
	String s = locator.replace("css_", "");
	return driver.findElements(By.cssSelector(s));
	} else if (locator.startsWith("xpath_")) {
	return driver.findElements(By.xpath(locator.replace("xpath_", "")));
	} else if (locator.startsWith("id_")) {
	return driver.findElements(By.id(locator.replace("id_", "")));
	} else if (locator.startsWith("name_")) {
	return driver.findElements(By.name(locator.replace("name_", "")));
	} else if (locator.startsWith("link_")) {
	return driver.findElements(By.linkText(locator.replace("link_", "")));
	} else if (locator.startsWith("partiallink_")) {
	return driver.findElements(By.partialLinkText(locator.replace("partiallink_", "")));
	} else if (locator.startsWith("class_")) {
	return driver.findElements(By.xpath(locator.replace("class_", "")));
	} else if (locator.startsWith("tag_")) {
	return driver.findElements(By.tagName(locator.replace("tag_", "")));
	} else
	return null;
	} catch (Exception e) {
	logger.error("getElementListByLocator: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return null;
	}
	}

	/**
	 *******************************************************************************************
	 * Verify the given element is present or not.
	 * 
	 * @param locator
	 *            This is locator in String format.
	 * @return boolean True / False
	 *******************************************************************************************/
	public static boolean isElementPresent(String locator) {
	try {
	findElementByLocator(locator).isDisplayed();
	return true;
	} catch (org.openqa.selenium.NoSuchElementException e) {
	return false;
	} catch (Exception e) {
	logger.error("isElementPresent: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return false;
	}
	}

	/**
	 ******************************************************************************************
	 * Will give a CurrentPageTitle.
	 * 
	 * @return pageTitle This is Page title in String format.
	 ******************************************************************************************
	 */
	public static String getCurrentPageTitle() {
	try {
	return driver.getTitle();
	} catch (Exception e) {
	logger.error("getCurrentPageTitle: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return null;
	}
	}

	/**
	 ******************************************************************************************
	 * Will Check Alert present or not. And if alert present accept.
	 * 
	 * @return boolean True / False.
	 ******************************************************************************************
	 */
	public static boolean acceptAlert() {
	try {
	int timeout=10;
	WebDriverWait wait = new WebDriverWait(driver, timeout);
	wait.until(ExpectedConditions.alertIsPresent());
	
	Alert a = driver.switchTo().alert();
	a.accept();
	return true;
	} catch (Exception e) {
	logger.error("acceptAlert: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return false;
	}
	}

	/**
	 ******************************************************************************************
	 * Will Check Alert present or not. And if alert present dismiss.
	 * 
	 * @return boolean True / False.
	 ******************************************************************************************
	 */
	public static boolean dimissAlert() {
	try {
	int timeout=10;
	WebDriverWait wait = new WebDriverWait(driver, timeout);
	wait.until(ExpectedConditions.alertIsPresent());
	
	Alert a = driver.switchTo().alert();
	a.dismiss();
	return true;
	} catch (Exception e) {
	logger.error("dimissAlert: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return false;
	}
	}

	/**
	 ******************************************************************************************
	 * Perform Scrolling till it finds the element specified.
	 * 
	 * @param locator
	 *            This is locator in String format.
	 ******************************************************************************************
	 */
	public static void scrollToElement(String locator) {
	try {
	WebElement mainElement = findElementByLocator(locator);
	Coordinates coordinate = ((Locatable) mainElement).getCoordinates();
	coordinate.onPage();
	coordinate.inViewPort();
	} catch (Exception e) {
	logger.error("scrollToElement: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	}
	}

	/**
	 ******************************************************************************************
	 * Perform double click on the element specified.
	 * 
	 * @param locator
	 *            This is locator in String format.
	 ******************************************************************************************
	 */
	public static void doubleClickElement(String locator) {
	try {
	WebElement doubleclickonWebElement = findElementByLocator(locator);
	Actions builder = new Actions(driver);
	builder.doubleClick(doubleclickonWebElement).perform();
	doWait(2000);
	} catch (Exception e) {
	logger.error("doubleClickElement: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	}
	}

	/**
	 ******************************************************************************************
	 * Perform switch To child Window operation when there is child and parent
	 * window .
	 ******************************************************************************************
	 */
	public static void switchToChildWindow() {
	try {
	int timeOut=5;
	@SuppressWarnings("rawtypes")
	Set s = driver.getWindowHandles();
	@SuppressWarnings("rawtypes")
	Iterator itr = s.iterator();
	@SuppressWarnings("unused")
	String w1 = (String) itr.next();
	System.out.println("PARENT WINDOW: " + w1);
	
	int numOfWindows=driver.getWindowHandles().size();
	int timeoutWaitCounter=0;
	System.out.println("NO. of Windows " + numOfWindows);
	while (numOfWindows <2 && timeoutWaitCounter <timeOut){
	System.out.println("Inside while loop " + numOfWindows);
	numOfWindows=driver.getWindowHandles().size();
	Thread.sleep(1000);
	timeoutWaitCounter= timeoutWaitCounter +1 ;
	}
	String w2 = (String) itr.next();
	driver.switchTo().window(w2);

	
	System.out.println("CHILD WINDOW: " + w2);
	} catch (Exception e) {
	logger.error("switchToChildWindow: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	}
	}

	/**
	 ******************************************************************************************
	 * Perform switch To parent Window operation when there is child and parent
	 * window .
	 ******************************************************************************************
	 */
	public static void switchToParentWindow() {
	try {
	@SuppressWarnings("rawtypes")
	Set s = driver.getWindowHandles();
	@SuppressWarnings("rawtypes")
	Iterator itr = s.iterator();
	String w1 = (String) itr.next();
	driver.switchTo().window(w1);
	System.out.println("PARENT WINDOW: " + w1);
	} catch (Exception e) {
	logger.error("switchToParentWindow: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	}
	}

	
	
	/**
	 *******************************************************************************************
	 * Converts StackTrace generated at time of Exception thrown into Message
	 * String format.
	 * 
	 * @param throwable
	 *            This is Exception thrown.
	 * @return String This is Exception message in String format.
	 *******************************************************************************************
	 */
	public static String getStackTrace(final Throwable throwable) {
	try {
	final StringWriter sw = new StringWriter();
	final PrintWriter pw = new PrintWriter(sw, true);
	throwable.printStackTrace(pw);
	return sw.getBuffer().toString();
	} catch (Exception e) {
	logger.error("getStackTrace: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return null;
	}
	}

	/**
	 ******************************************************************************************
	 * Will Check Alert present or not.
	 * 
	 * @return boolean True / False.
	 ******************************************************************************************
	 */
	public static boolean isAlertPresent() {
	try {
	int timeout=10;
	WebDriverWait wait = new WebDriverWait(driver, timeout);
	wait.until(ExpectedConditions.alertIsPresent());
	return true;
	} catch (NoAlertPresentException e) {
	logger.error("isAlertPresent: No Alert Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return false;
	} catch (Exception e) {
	logger.error("isAlertPresent: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return false;
	}
	}

	/**
	 ******************************************************************************************
	 * Will Check Whether Page Contains given text or not.
	 * 
	 * @param pageText
	 *            This is text in string format to be checked on Web Page.
	 * @return boolean True / False.
	 ******************************************************************************************
	 */
	public static boolean verifyPageContainsText(String pageText) {
	try {
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + pageText + "')]"));
	if (list.size() > 0) {
	return true;
	}
	return false;
	} catch (Exception e) {
	logger.error("verifyPageContainsText: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return false;
	}

	}

	/**
	 ******************************************************************************************
	 * Will Check Whether Page Contains given WebElement or not.
	 * 
	 * @param locator
	 *            This is locator in String format.
	 * @return boolean True / False.
	 ******************************************************************************************
	 */
	public static boolean verifyPageContainsElement(String locator) {
	try {
	List<WebElement> element = getElementListByLocator(locator);
	if (element.size() != 0) {
	return true;
	}
	return false;
	} catch (Exception e) {
	logger.error("verifyPageContainsElement: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return false;
	}

	}

	/**
	 ******************************************************************************************
	 * Will Check Whether given WebElement Visible or not.
	 * 
	 * @param locator
	 *            This is locator in String format.
	 * @return boolean True / False.
	 ******************************************************************************************
	 */
	public static boolean verifyElementIsVisible(String locator) {
	try {
	WebElement element = findElementByLocator(locator);
	if (element.isDisplayed()) {
	return true;
	}
	return false;
	} catch (Exception e) {
	logger.error("verifyElementIsVisible: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return false;
	}
	}

	/**
	 ******************************************************************************************
	 * Will Check Whether given WebElement Enabled or not.
	 * 
	 * @param locator
	 *            This is locator in String format.
	 * @return boolean True / False.
	 ******************************************************************************************
	 */
	public static boolean verifyElementIsEnabled(String locator) {
	try {
	WebElement element = findElementByLocator(locator);
	if (element.isEnabled()) {
	return true;
	}
	return false;
	} catch (Exception e) {
	logger.error("verifyElementIsEnabled: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return false;
	}
	}

	/**
	 ******************************************************************************************
	 * Wait for element to be enabled
	 * 
	 * @param locator, timeout
	 *            This is locator in String format.
	 *            timeout is in seconds
	 * @return boolean True / False.
	 ******************************************************************************************
	 */
	public static boolean waitForElementToBeEnabled(String locator, int timeoutInSeconds){
	try {
	int timeoutWaitCounter=0;
	WebElement element = findElementByLocator(locator);
	while (!element.isEnabled() && timeoutWaitCounter <timeoutInSeconds){
	Thread.sleep(1000);
	timeoutWaitCounter= timeoutWaitCounter +1 ;
	}
	return true;
	} catch (Exception e) {
	logger.error("verifyElementIsEnabled: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return false;
	}
	}

	/**
	 ******************************************************************************************
	 * Will Check Whether given WebElement Disabled or not.
	 * 
	 * @param locator
	 *            This is locator in String format.
	 * @return boolean True / False.
	 ******************************************************************************************
	 */
	public static boolean verifyElementIsDisabled(String locator) {
	try {
	WebElement element = findElementByLocator(locator);
	if (!element.isEnabled()) {
	return true;
	}
	return false;
	} catch (Exception e) {
	logger.error("verifyElementIsDisabled: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return false;
	}
	}

	/**
	 ******************************************************************************************
	 * Will Check Whether text field contains given value or not
	 * 
	 * @param locator
	 *            This is locator in String format.
	 * @param value
	 *            This is value to be checked in the text field.
	 * @return boolean True / False.
	 ******************************************************************************************
	 */
	public static boolean verifyTextFieldContainsValue(String locator, String value) {
	try {
	WebElement element = findElementByLocator(locator);
	if (element.getAttribute("value").equals(value)) {
	return true;
	}
	return false;
	} catch (Exception e) {
	logger.error("verifyTextFieldContainsValue: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return false;
	}
	}

	/**
	 ******************************************************************************************
	 * Will Check Whether frame contains given text or not using frame index
	 * 
	 * @param index
	 *            This is frame index to be switched in.
	 * @param pageText
	 *            This is text in string format to be checked on Frame inside
	 *            Web Page.
	 * @return boolean True / False.
	 ******************************************************************************************
	 */
	public static boolean verifyFrameContainsTextUsingIndex(int index, String pageText) {
	try {
	driver.switchTo().frame(index);
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + pageText + "')]"));
	if (list.size() > 0) {
	return true;
	}
	return false;
	} catch (Exception e) {
	logger.error(
	"verifyFrameContainsTextUsingIndex: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return false;
	}
	}

	/**
	 *****************************************************************************************************
	 * Will Check Whether frame contains given text or not using name of the
	 * frame or id of frame element.
	 * 
	 * @param name
	 *            This is frame name to be switched in.
	 * @param pageText
	 *            This is text in string format to be checked on Frame inside
	 *            Web Page.
	 * @return boolean True / False.
	 *****************************************************************************************************
	 */
	public static boolean verifyFrameContainsTextUsingName(String name, String pageText) {
	try {
	driver.switchTo().frame(name);
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + pageText + "')]"));
	if (list.size() > 0) {
	return true;
	}
	return false;
	} catch (Exception e) {
	logger.error("verifyFrameContainsTextUsingName: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return false;
	}
	}

	/**
	 *****************************************************************************************************
	 * Will Check Whether frame contains given text or not using its previously
	 * located WebElement.
	 * 
	 * @param locator
	 *            This is locator in String format of previously located
	 *            frame.
	 * @param pageText
	 *            This is text in string format to be checked on Frame inside
	 *            Web Page.
	 * @return boolean True / False.
	 *****************************************************************************************************
	 */
	public static boolean verifyFrameContainsTextUsingElement(String locator, String pageText) {
	try {
	if (isElementPresent(locator)) {
	WebElement element = findElementByLocator(locator);
	driver.switchTo().frame(element);
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + pageText + "')]"));
	if (list.size() > 0) {
	return true;
	}
	}
	return false;
	} catch (Exception e) {
	logger.error("verifyFrameContainsTextUsingElement: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return false;
	}
	}

	

	/**
	 *****************************************************************************************************
	 * To read config file
	 *****************************************************************************************************
	 */
	public static String getConfigProperty(String filename, String key) {
	Properties prop = new Properties();
	InputStream input = null;

	try {
	String path = System.getProperty("user.dir");
	path += new PropertyReader().readProperty("configpath");
	input = new FileInputStream(path + filename);

	// load a properties file
	prop.load(input);
	// System.out.println("file is " + input);
	return prop.getProperty(key);

	} catch (IOException ex) {
	ex.printStackTrace();
	logger.error("getConfigProperty: Exception Thrown - Cant read config file " + getStackTrace(ex));
	} finally {
	if (input != null) {
	try {
	input.close();
	} catch (IOException e) {
	logger.error("getConfigProperty: Exception Thrown - Cant read config file " + getStackTrace(e));
	e.printStackTrace();
	}
	}
	}
	return null;

	}

	/**
	 *****************************************************************************************************
	 * To check whether element is visible or not
	 *****************************************************************************************************
	 */
	public static boolean checkElementVisible(String locator) {
	return findElementByLocator(locator).isDisplayed();
	}
	
	/**
	 ******************************************************************************************
	 * Will if edit box is empty
	 * 
	 * @param locator
	 *            This is locator in String format.
	 * @return boolean True / False.
	 ******************************************************************************************
	 */
	public static boolean verifyEditBoxFieldIsEmpty(String locator) {
	try {
	WebElement element = findElementByLocator(locator);
	String fieldValue = element.getAttribute("value");
	//String fieldText = element.getText();
	if (fieldValue.isEmpty()) {
	return true;
	}
	return false;
	} catch (Exception e) {
	logger.error("verifyEditBoxFieldIsEmpty: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return false;
	}
	}
	
	/**
	 ******************************************************************************************
	 * Will check if drop down default value is empty
	 * 
	 * @param locator
	 *            This is locator in String format.
	 * @return boolean True / False.
	 ******************************************************************************************
	 */
	public static String getDefaultValueOfDropdown(String locator) {
	String defaultSelectedValue="";
	try {
	Select selectitem = new Select(findElementByLocator(locator));
	WebElement defaultSelectedElement= selectitem.getFirstSelectedOption();
	defaultSelectedValue= defaultSelectedElement.getText();
	System.out.println("VALUE" + defaultSelectedValue);
	return defaultSelectedValue;
	
	} catch (Exception e) {
	logger.error("getDefaultValueOfDropdown: Exception thrown -- " + getStackTrace(e));
	MatcherAssert.assertThat(e.getMessage(), false);
	return null;
	}
	
	}
		
}
