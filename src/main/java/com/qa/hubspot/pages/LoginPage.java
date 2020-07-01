package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;

public class LoginPage extends BasePage {
	
	private WebDriver driver;
	
	//1. Page Objects // By locators -- OR(Object Repository)
	
	By emailId = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	
	// 2. create constructor of page class:
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	// 3. Page Methods/Actions.
	
	public String getLoginPageTitle() throws InterruptedException {
		Thread.sleep(5000);
		return driver.getTitle();
	}
	
	
	public boolean verifySignUpLink() {
		return driver.findElement(signUpLink).isDisplayed();
	}
	
	
	public HomePage doLogin(String username, String pwd) {
		driver.findElement(emailId).sendKeys(username);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginButton).click();
		
		return new HomePage(driver);
	}
	
	
}
