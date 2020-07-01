package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;

public class HomePage extends BasePage {
	
	WebDriver driver;
	//1. By Locators
	
	By header = By.xpath("//h1[text()='Sales Dashboard']");
	By accountName = By.xpath("//span[text()='test']");
	
	//2.const...
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	//3. Page Methods:
	
	public String getHomePageTitle() {
		return driver.getTitle();
	}
	
	
	public String getHomePageHeaderText() {
		if(driver.findElement(header).isDisplayed()) {
			return driver.findElement(header).getText();
		}
		return null;
		
	}
	
	public String verifyLoggedInUser() {
		if(driver.findElement(accountName).isDisplayed()) {
			return driver.findElement(accountName).getText();
		}
		return null;
	}
	

}
