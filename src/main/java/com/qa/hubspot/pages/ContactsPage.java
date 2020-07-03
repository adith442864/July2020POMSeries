package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

public class ContactsPage extends BasePage {
	
	WebDriver driver;
	
	// By locators:
	By header = By.xpath("//i18n-string[text()='Contacts']");
	
	By createContactPrimary = By.xpath("//span[text()='Create contact']");
	By createContactSecondary = By.xpath("(//span[text()='Create contact'])[last()]");

	By email = By.xpath("//input[@data-field='email']");
	By firstName = By.xpath("//input[@data-field='firstname']");
	By lastName = By.xpath("//input[@data-field='lastname']");
	By jobTitle = By.xpath("//input[@data-field='jobtitle']");
	
	By contactsBackLink = By.xpath("(//i18n-string[text()='Contacts'])[position()=1]");
	
	
//	By contactsClick = By.xpath("(//i18n-string[text()='Contacts'])");
	
	
	// 2. Page Constructor...
	public ContactsPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
	}
	
	
	//3. page Actions:
	public String getContactsPageTitle() {
		return elementUtil.waitForTitleToBePresent(Constants.CONTACTS_PAGE_TITLE, 10);
		
	}
	
	public String getContactsPageHeader() {
		elementUtil.waitForElementToBeVisible(header, 10);
		return elementUtil.doGetText(header);
	}
	
	public void createContact(String email, String firstname, String lastname, String jobTitle) {
		elementUtil.clickWhenReady(createContactPrimary, 10);
		
		elementUtil.waitForElementToBeVisible(this.email, 5);
		elementUtil.doSendKeys(this.email, email);
		
		elementUtil.doSendKeys(firstName, firstname);
		elementUtil.doSendKeys(lastName, lastname);
		
		elementUtil.waitForElementToBeVisible(this.jobTitle, 5);
		elementUtil.doSendKeys(this.jobTitle, jobTitle);
		
		elementUtil.clickWhenReady(createContactSecondary, 5);
	
//		elementUtil.clickWhenReady(contactsBackLink, 5);
		elementUtil.doActionsClick(contactsBackLink);
	}
	

}
