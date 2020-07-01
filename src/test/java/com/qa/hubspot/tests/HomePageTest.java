package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;

public class HomePageTest {
	
	WebDriver driver;
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	Properties prop;
	
	@BeforeTest
	public void SetUp() throws Exception {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(5000);
	}
	
	@Test(priority=1)
	public void verifyHomePageTitle() {
		String title = homePage.getHomePageTitle();
		System.out.println("home page title is :" +title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE, "home page title is not matched...");
	}
	
	@Test(priority=2)
	public void verifyHomePageHeader() {
		String header = homePage.getHomePageHeaderText();
		System.out.println("Home page header is : "+header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER,"Home page header is not matched..");
	}
	
	@Test(priority=3)
	public void verifyLoggedInUserTest() {
		String loggedInUser = homePage.verifyLoggedInUser();
		System.out.println("Logged in user is : " +loggedInUser);
		Assert.assertEquals(loggedInUser, prop.getProperty("accountName") , "logged in user is not matched... ");
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	

	
	

}
