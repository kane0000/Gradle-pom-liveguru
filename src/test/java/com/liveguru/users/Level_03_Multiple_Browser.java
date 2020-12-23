package com.liveguru.users;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.liveGuru.AddressBookPageObject;
import pageObjects.liveGuru.HomePageObject;
import pageObjects.liveGuru.LoginPageObject;
import pageObjects.liveGuru.MyDashboardPageObject;
import pageObjects.liveGuru.RegisterPageObject;

public class Level_03_Multiple_Browser extends AbstractTest {
	private WebDriver driver;
	HomePageObject hompage;
	LoginPageObject loginpage;
	RegisterPageObject registerpage;
	MyDashboardPageObject mydashboardpage;
	AddressBookPageObject addressbookpage;
	

	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		
		driver = getBrowserDriver(browserName, appUrl);
		
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
