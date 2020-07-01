package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;

public class LoginPageTest {
	
	WebDriver driver;
	BasePage basePage;
	LoginPage loginPage;
	Properties prop;
	
	
	@BeforeTest
	public void SetUp() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		
	}
	
	
	@Test(priority=1)
	public void verifyLoginPageTitleTest() throws InterruptedException {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login page title is :" +title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "Login page title is not matched...");
	}
	
	@Test(priority=2)
	public void verifySignUpLinkTest() {
		boolean signUpLink = loginPage.verifySignUpLink();
		Assert.assertTrue(signUpLink, "Sign up link is not displayed..");
	}
	
	@Test(priority=3)
	public void doLogin() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	

}
