package com.liveguru.users;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import driverFactory.DriverManager;
import pageFactory.liveGuru.HomePageObject;
import pageFactory.liveGuru.LoginPageObject;
import pageFactory.liveGuru.MyDashboardPageObject;
import pageFactory.liveGuru.RegisterPageObject;

public class Level_06_Page_Factory extends AbstractTest {
	private WebDriver driver;
	HomePageObject hompage;
	LoginPageObject loginpage;
	RegisterPageObject registerpage;
	MyDashboardPageObject mydashboardpage;
	
	DriverManager driverManager;

	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		hompage = new HomePageObject(driver);
	}
		@Test
		public void Register_01() {
		hompage.clickToMyAccountLink();
		loginpage = new LoginPageObject(driver);
		loginpage.clickToCreateAnAccountButton();
		registerpage = new RegisterPageObject(driver);
		
		registerpage.inputToFistnamTextbox("Kane");
		registerpage.inputToLastnameTextbox("Lee");
		registerpage.inputToEmailTextbox("kanelee" + randomNumber() + "@gmai.com");
		registerpage.inputToPasswordTextbox("123456");
		registerpage.inputToConfirmPasswordTextbox("123456");
		registerpage.clickToRegisterButton();

		mydashboardpage = new MyDashboardPageObject(driver);
		Assert.assertEquals(mydashboardpage.getWelcomeSuccessMessage(),"Thank you for registering with Main Website Store.");
	}

	@AfterClass
	public void afterClass() {
		removeDriver();
	}
}

