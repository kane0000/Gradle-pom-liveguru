package com.liveguru.common;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.AbstractTest;
import driverFactory.DriverManager;
import pageObjects.liveGuru.HomePageObject;
import pageObjects.liveGuru.LoginPageObject;
import pageObjects.liveGuru.MyDashboardPageObject;
import pageObjects.liveGuru.PageGeneratorManager;
import pageObjects.liveGuru.RegisterPageObject;

public class Common_01_Register extends AbstractTest {
	private WebDriver driver;
	HomePageObject hompage;
	LoginPageObject loginpage;
	RegisterPageObject registerpage;
	MyDashboardPageObject mydashboardpage;
	
	DriverManager driverManager;
	public static String email, password;

	// Pre - Conditions
	@Parameters({"browser","url"})
	@BeforeTest
	public void beforeTest(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		hompage = PageGeneratorManager.getHomePage(driver);
		email = "commonregister" + randomNumber() + "@gmai.com";
		password = "123456";
	
			loginpage = hompage.clickToMyAccountLink();
			
			registerpage =loginpage.clickToCreateAnAccountButton();
		
		registerpage.inputToFistnamTextbox("Kane");
		registerpage.inputToLastnameTextbox("Lee");
		registerpage.inputToEmailTextbox(email);
		registerpage.inputToPasswordTextbox(password);
		registerpage.inputToConfirmPasswordTextbox(password);
		mydashboardpage = registerpage.clickToRegisterButton();

		Assert.assertEquals(mydashboardpage.getWelcomeSuccessMessage(),"Thank you for registering with Main Website Store.");
		
		removeDriver();
	}

}
