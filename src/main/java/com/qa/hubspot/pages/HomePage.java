package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage {
	
	WebDriver driver;
//	ElementUtil elementUtil;
	//1. By Locators
	
	By header = By.xpath("//h1[text()='Sales Dashboard']");
	By accountName = By.xpath("//span[text()='test']");
	
	By primaryContactLink = By.id("nav-primary-contacts-branch");
	By secondaryContactLink = By.id("nav-secondary-contacts");
	
	
	
	//2.const...
	public HomePage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//3. Page Methods:
	
	public String getHomePageTitle() throws Exception {
		//return driver.getTitle();
		return elementUtil.waitForTitleToBePresent(Constants.HOME_PAGE_TITLE, 15);
	}
	
	
	public String getHomePageHeaderText() {
		if(elementUtil.doIsDisplayed(header)) {
			return elementUtil.doGetText(header);
		}
		return null;
		
	}
	
	public String verifyLoggedInUser() {
		if(elementUtil.doIsDisplayed(accountName)) {
			return elementUtil.doGetText(accountName);
		}
		return null;
	}
	

	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
		
	}
	
	private void clickOnContacts() {
		elementUtil.waitForElementPresent(primaryContactLink, 10);
		elementUtil.doClick(primaryContactLink);
		
		elementUtil.waitForElementPresent(secondaryContactLink, 5);
		elementUtil.doClick(secondaryContactLink);
		
		
		
	}
	
	
	
}
