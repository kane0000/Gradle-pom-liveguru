package com.liveguru.users;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.liveGuru.AddressBookPageObject;
import pageObjects.liveGuru.HomePageObject;
import pageObjects.liveGuru.LoginPageObject;
import pageObjects.liveGuru.MyDashboardPageObject;
import pageObjects.liveGuru.RegisterPageObject;

public class Level_02_Page_Object2 {
	WebDriver driver;
	HomePageObject hompage;
	LoginPageObject loginpage;
	RegisterPageObject registerpage;
	MyDashboardPageObject mydashboardpage;
	AddressBookPageObject addressbookpage;
	
	@BeforeClass
	public void beforeClass() {
			System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		hompage = new HomePageObject(driver);
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
	@Test
	public void Add_Address_Book() {
		mydashboardpage.clickManageAddresses();
		addressbookpage = new AddressBookPageObject(driver);
		addressbookpage.inputTelephoneTexbox("0987888999");
		addressbookpage.inputStreetAddressTextbox("123 Ngo Gia Tu");
		addressbookpage.inputCityTexbox("Ha Noi");
		addressbookpage.inputZipTextbox("999888");
		addressbookpage.selectValueInCountryDropdown("Vietnam");
		addressbookpage.clickSaveAddressButton();
		Assert.assertTrue(addressbookpage.isSuccessMessageDisplayed());

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
