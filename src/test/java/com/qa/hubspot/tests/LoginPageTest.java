package com.qa.hubspot.tests;


import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.listeners.ExtentReportListener;
import com.qa.hubspot.util.Constants;

//@Listeners(ExtentReportListener.class)
public class LoginPageTest extends BaseTest{
	
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
	
	

}
