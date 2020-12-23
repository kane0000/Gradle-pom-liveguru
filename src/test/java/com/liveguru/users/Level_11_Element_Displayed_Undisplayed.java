package com.liveguru.users;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.liveGuru.HomePageObject;
import pageObjects.liveGuru.PageGeneratorManager;

public class Level_11_Element_Displayed_Undisplayed extends AbstractTest {
	private WebDriver driver;
	HomePageObject homepage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homepage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void TC_01_Register() {
		Assert.assertTrue(homepage.isFooterMyAccountLinkDisplayed());
		
		Assert.assertTrue(homepage.isHeaderMyAccountLinkUndisplayed());
		
		Assert.assertTrue(homepage.isErrorMessageAtSubscribeTextboxUndisplayed());
		
		homepage.clickToSubscribeButton();
		Assert.assertTrue(homepage.isErrorMessageAtSubscribeTextboxDisplayed());
		
	}
	
	@AfterClass
	public void afterClass() {
		removeDriver();
	}

}
