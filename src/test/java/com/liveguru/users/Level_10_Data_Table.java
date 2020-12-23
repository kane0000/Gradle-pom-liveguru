package com.liveguru.users;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.liveGuru.LoginManagerPageObject;
import pageObjects.liveGuru.ManagerCustomerPageObject;
import pageObjects.liveGuru.PageGeneratorManager;

public class Level_10_Data_Table extends AbstractTest {
	private WebDriver driver;
	LoginManagerPageObject loginpage;
	ManagerCustomerPageObject managercustomerpage;
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginpage = PageGeneratorManager.getLoginManagerPage(driver);
		loginpage.inputToEmailTexbox("user01");
		loginpage.inputToPasswordTexbox("guru99com");
		managercustomerpage = loginpage.clickToLoginButton();
		managercustomerpage.clickToClose();
		
	}
		
	@Test
	public void TC_01_Search_Data_Page() {
		managercustomerpage.inputTextboxTableByColumnName("Name", "Kevin Nguyen");
		managercustomerpage.inputTextboxTableByColumnName("Email", "automation1516@Vietnam.com");
		managercustomerpage.inputTextboxTableByColumnName("Telephone", "0988666999");
		managercustomerpage.inputTextboxTableByColumnName("ZIP", "123456");
		managercustomerpage.inputTextboxTableByColumnName("State/Province", "Thanh Xuan");
	
		managercustomerpage.clickToButtonByTitleName("Search");
		
		//verify
		Assert.assertTrue(managercustomerpage.isValuesDisplayRowNumber("ID","1", "45397"));
		Assert.assertTrue(managercustomerpage.isValuesDisplayRowNumber("Name","1", "Kevin Nguyen"));
		Assert.assertTrue(managercustomerpage.isValuesDisplayRowNumber("Email","1", "automation1516@Vietnam.com"));
		Assert.assertTrue(managercustomerpage.isValuesDisplayRowNumber("Group","1", " General"));
		Assert.assertTrue(managercustomerpage.isValuesDisplayRowNumber("Telephone","1", "0988666999"));
		Assert.assertTrue(managercustomerpage.isValuesDisplayRowNumber("ZIP","1", "123456"));
		Assert.assertTrue(managercustomerpage.isValuesDisplayRowNumber("Country","1", "Vietnam"));
		Assert.assertTrue(managercustomerpage.isValuesDisplayRowNumber("State/Province","1", "Thanh Xuan"));
		
	}

	
	@AfterClass
	public void afterClass() {
		removeDriver();
	}

}
