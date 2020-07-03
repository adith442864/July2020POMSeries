package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ExcelUtil;

public class ContactsPageTest extends BaseTest {
	
	HomePage homePage;
	ContactsPage contactsPage;
	
	
	@BeforeClass
	public void contactsSetup() {
		
	homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	contactsPage = homePage.goToContactsPage();
	
	}
	
	@Test(priority=1)
	public void verifyContactsPageTitleTest() {
		String title = contactsPage.getContactsPageTitle();
		System.out.println("Contacts page title is :" +title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
		
	}
	
	@DataProvider
	public Object[][] getContactsTestData() {
		Object[][] data = ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
	}
	
	@Test(priority=2,dataProvider = "getContactsTestData")
	public void createContactTest(String email, String fn, String ln, String jobTitle) {
		contactsPage.createContact(email, fn, ln, jobTitle);
	}

}
