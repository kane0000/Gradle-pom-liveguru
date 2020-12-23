package com.liveguru.users;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.liveGuru.HomePageObject;
import pageObjects.liveGuru.LoginPageObject;
import pageObjects.liveGuru.MyDashboardPageObject;
import pageObjects.liveGuru.RegisterPageObject;

public class Level_02_Page_Object  {
	WebDriver driver;
	HomePageObject hompage;
	LoginPageObject loginpage;
	RegisterPageObject registerpage;
	MyDashboardPageObject mydashboardpage;


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		hompage = new HomePageObject(driver);
	}

	@BeforeMethod
	public void beforeMethod() {
		hompage.clickToMyAccountLink();

		loginpage = new LoginPageObject(driver);
		loginpage.clickToCreateAnAccountButton();

	}

	@Test
	public void Register_01_Empty_Data() {
		registerpage = new RegisterPageObject(driver);

		registerpage.clickToRegisterButton();

		Assert.assertEquals(registerpage.getErrorMessageAtFistnameTextbox(), "This is a required field.");
		Assert.assertEquals(registerpage.getErrorMessageAtLastnameTextbox(), "This is a required field.");
		Assert.assertEquals(registerpage.getErrorMessageAtEmailTextbox(), "This is a required field.");
		Assert.assertEquals(registerpage.getErrorMessageAtPasswordTextbox(), "This is a required field.");
		Assert.assertEquals(registerpage.getErrorMessageAtConfirmPassworkTextbox(), "This is a required field.");
	}

	@Test
	public void Register_02_Invalid_Email() {
		registerpage.inputToEmailTexbox("123@123.123");
		registerpage.clickToRegisterButton();

		Assert.assertEquals(registerpage.getInvalidErrorMessageAtEmailTextbox(), "Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test
	public void Register_03_Password_Less_6_Chars() {
		registerpage.inputToPasswordTextbox("1234");
		registerpage.clickToRegisterButton();

		Assert.assertEquals(registerpage.getInvalidErrorMessageAtPasswordTextbox(), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void Register_04_Confirm_Password_Notmatching() {

		registerpage.inputToPasswordTextbox("123445");
		registerpage.inputToConfirmPasswordTextbox("123478");
		registerpage.clickToRegisterButton();

		Assert.assertEquals(registerpage.getInvalidErrorMessageAtConfirmPasswordTextbox(), "Please make sure your passwords match.");

	}

	@Test
	public void Register_05_Valid_Data() {

		registerpage.inputToFistnamTextbox("Kane");
		registerpage.inputToLastnameTextbox("Lee");
		registerpage.inputToEmailTextbox("kanelee" + randomNumber() + "@gmai.com");
		registerpage.inputToPasswordTextbox("123456");
		registerpage.inputToConfirmPasswordTextbox("123456");
		registerpage.clickToRegisterButton();

		mydashboardpage = new MyDashboardPageObject(driver);
		Assert.assertEquals(mydashboardpage.getWelcomeSuccessMessage(), "Thank you for registering with Main Website Store.");
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}
}
