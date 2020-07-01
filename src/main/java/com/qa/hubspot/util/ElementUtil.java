package com.qa.hubspot.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtil extends BasePage{
	
	WebDriver driver;
	WebDriverWait wait;
	JavaScriptUtil jsUtil;
	Properties prop;
	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, Constants.DEFAULT_TIME_OUT);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	/*****************************************Generic Utils*********************************/
	
	/**
	 * This method is used to get the page title.
	 * @return
	 */
	
	public String doGetPageTitle() {
		try{
			return driver.getTitle();
		}catch (Exception e) {
			System.out.println("some exception occured getting the title of the page...");
		}
		return null;
	}
	
	
	
	/**
	 * This method is used to create WebElement on the basis of By locator.
	 * @param locator
	 * @return element
	 */
	public WebElement getElement(By locator) {
		
		WebElement element = null;
		try {
			//if(waitForElementToBePresent(locator)) 
				element = driver.findElement(locator);
//				if(highlightElement) {
//					jsUtil.flash(element);
//				}
				
				
			
		}catch (Exception e) {
			System.out.println("some exception got occured while creating the webelement...");
			System.out.println(e.getMessage());
		}
		return element;
	}
	
	
	/**
	 * This method is used to click on element
	 * @param locator
	 */
	public void doClick(By locator) {
	try {
		getElement(locator).click();
	} 
	catch(Exception e) {
		System.out.println(e.getMessage());
		System.out.println("some exception occured while clicking on the webelement...");
	}
	
	}
	
	/**
	 * This method is used to click on element
	 * @param locator
	 */
	public void doActionsClick(By locator) {
	try {
		WebElement element = getElement(locator);
		Actions action = new Actions(driver);
		action.click(element).build().perform();
	} catch(Exception e) {
		System.out.println("some error occured while clicking on the webelement...");
	}
	
	
	}
	
	/**
	 * This method is used to pass the values in a webelement using Action class.
	 * @param locator
	 */
	public void doActionsSendKeys(By locator, String value) {
		try{
			WebElement element = getElement(locator);
			Actions action = new Actions(driver);
			action.sendKeys(element, value).build().perform();
		} catch (Exception e) {
			System.out.println("some exception got occured while entering values in a field...");
		}
		
		
	}
	

	/**
	 * This method is used to pass the values in a webelement
	 * @param locator
	 */
	public void doSendKeys(By locator, String value) {
		try{
			WebElement ele = getElement(locator);
			ele.clear();
			ele.sendKeys(value);
		}
		catch(Exception e) {
			System.out.println("some exception got occured while entering values in a field...");
		}
	}
	
	/**
	 * This method is used to pass the values in a webelement
	 * @param locator
	 */
	public void doSendKeysInt(By locator, String amt) {
		try{
			WebElement ele = getElement(locator);
			ele.clear();
			ele.sendKeys(String.valueOf(amt));
		}
		catch(Exception e) {
			System.out.println("some exception got occured while entering values in a field...");
		}
	}
	
	

	/**
	 * this method is used to get the text from the specific webelement...
	 * @param driver
	 * @param locator
	 * @return
	 */
	public String doGetText(By locator) {
		try{
			return getElement(locator).getText();
		}catch (Exception e) {
			System.out.println("some exception occured while getting the text from a webelement...");
		}
		return null;
	}
	
	/**
	 * this is used for checking element is displayed
	 * @param driver
	 * @param locator
	 * @return
	 */
	public boolean doIsDisplayed(By locator){
		try{
			return getElement(locator).isDisplayed();
		}catch (Exception e) {
			System.out.println("some exception occured while displaying the webelement...");
		}
		return false;
	}
	
/********************************Explicit Wait Methods******************************/
	
	/**
	 * This method is used to check visibility of all webElements.
	 * @param elements
	 * @param timeOut
	 */
	public boolean visibilityOfAllElements(List<WebElement> elements) {
//		WebDriverWait wait = new WebDriverWait(driver);
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		return true;
	}
	
	/**
	 * This method is used to explicit wait for the element to be present
	 * @param locator
	 */
	public boolean waitForElementToBePresent(By locator) {
//		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
	}
	
	
	
	/**
	 * This method is used to explicit wait for the element to be clickable
	 * @param locator
	 */
	public boolean waitForElementToBeClickable(By locator) {
//		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		return true;
	}
	
	/**
	 * This method is used to explicit wait for the element to be visible
	 * @param locator
	 */
	public WebElement waitForElementToBeVisible(By locator) {
		WebElement element = getElement(locator);
//		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	
	/**
	 * This method is used to explicit wait for the element to be visible on basis of Locator
	 * @param locator
	 */
	public boolean waitForElementVisibilityLocated(By locator) {
//		WebElement element = getElement(locator);
//		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
	}
	
	
	/**
	 * This method is used to explicit wait for the URL
	 * @param locator
	 */
	public boolean waitForUrl(String url) {
//		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.urlContains(url));
		return true;
	}
	
	/**
	 * This method is used to explicit wait for the Alert to be present
	 * @param locator
	 */
	public boolean waitForAlertToBePresent() {
//		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.alertIsPresent());
		return true;
	}
	
	

	/**
	 * This method is used to explicit wait for the title to be present
	 * @param locator
	 */
	public boolean waitForTitlePresent(String title) {
//		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleContains(title));
		return true;
	}
	
	/**
	 * This method is used to explicit wait for clicking the locator when ready
	 * @param locator
	 * @param timeOut
	 */
	public void clickWhenReady(By locator) {
//		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		getElement(locator).click();
	}
	
	
	/**************************************Drop Down values Methods****************************/
	
	/**
	 * This method is used to select the value from a drop down on basis of given text
	 * @param element
	 * @param value
	 */
	public static void selectValueFromDropDownByText(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
		
	}
	
	
	/**
	 * This method is used to select the value from a drop down on basis of given index
	 * @param element
	 * @param value
	 */
	public static void selectValueFromDropDownByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		
	}
	
	
	
	/**
	 * This method is used to get all the values from drop down.
	 * 
	 * @param element
	 */
	public static ArrayList<String> getDropDownValues(WebElement element) {
		System.out.println("=================================");
		Select select = new Select(element);
		List<WebElement> dropList = select.getOptions();
		
		System.out.println("total number of values in drop down :" + dropList.size());
		ArrayList<String> ar = new ArrayList<String>();
		
		for(int i=0; i<dropList.size(); i++) {
			String text = dropList.get(i).getText();
			//System.out.println(text);
			ar.add(text);
		}
		
		return ar;
	}
	
	/**
	 * This method is used to select all the values from DropDown without select class
	 * @param driver
	 * @param locator
	 * @param value
	 */
	public static void selectDropDownValueWithoutSelect(WebDriver driver, String locator, String value) {
		
		List<WebElement> dropList = driver.findElements(By.xpath(locator));
		System.out.println(dropList.size());
		
		
		for(int i=0; i<dropList.size(); i++) {
			String text = dropList.get(i).getText();
			System.out.println(text);
			if(text.equals(value)) {
				dropList.get(i).click();
				break;
			}
		}

	}	
	
	

}
