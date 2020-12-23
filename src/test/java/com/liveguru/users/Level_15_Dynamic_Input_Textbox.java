package com.liveguru.users;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.liveGuru.AddressBookPageObject;
import pageObjects.liveGuru.HomePageObject;
import pageObjects.liveGuru.LoginPageObject;
import pageObjects.liveGuru.MyDashboardPageObject;
import pageObjects.liveGuru.PageGeneratorManager;
import pageObjects.liveGuru.RegisterPageObject;
import reportConfig.AllureTestListener;

@Listeners ({AllureTestListener.class})
public class Level_15_Dynamic_Input_Textbox extends AbstractTest {
	WebDriver driver;
	HomePageObject homepage;
	LoginPageObject loginpage;
	RegisterPageObject registerpage;
	MyDashboardPageObject mydashboardpage;
	AddressBookPageObject addressbookpage;
	
	String email, password;

	@Parameters({ "browser", "url" })
	@BeforeClass(groups="smoke")
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homepage = PageGeneratorManager.getHomePage(driver);
		email = "automation" + randomNumber() + "@gmai.com";
	    password = "123456";
	}

	@Test(groups="smoke")
	public void TC_01_Register() {
		loginpage = homepage.clickToMyAccountLink();
		registerpage =loginpage.clickToCreateAnAccountButton();
		
		registerpage.inputToDynamicTextbox(driver, "firstname", "Kane");
		registerpage.inputToDynamicTextbox(driver, "lastname", "Lee");
		registerpage.inputToDynamicTextbox(driver, "email_address",email);
		registerpage.inputToDynamicTextbox(driver,"password",password);
		registerpage.inputToDynamicTextbox(driver,"confirmation",password);
		mydashboardpage = registerpage.clickToRegisterButton();

		Assert.assertEquals(mydashboardpage.getWelcomeSuccessMessage(), "Thank you for registering with Main Website Store.");
		homepage = mydashboardpage.clickLogoutLink();
	}
	@Test(groups="smoke")
	public void TC_02_Login() {
		loginpage = homepage.clickToMyAccountLink();

		loginpage.inputToDynamicTextbox(driver, "email",email);
		loginpage.inputToDynamicTextbox(driver,"pass",password);
		mydashboardpage = loginpage.clickLoginButton();
		Assert.assertTrue(mydashboardpage.isMyDashboardHeaderDisplayed());
	}
	@Test(groups="smoke")
	public void TC_03_Add_Address_Book() {
		addressbookpage = mydashboardpage.clickManageAddresses();
		
		addressbookpage.inputToDynamicTextbox(driver, "telephone","0987888999");
		addressbookpage.inputToDynamicTextbox(driver, "street_1","123 Ngo Gia Tu");
		addressbookpage.inputToDynamicTextbox(driver, "city","Ha Noi");
		addressbookpage.inputToDynamicTextbox(driver, "zip","888999");
		addressbookpage.selectValueInCountryDropdown("Vietnam");
		addressbookpage.clickSaveAddressButton();
		Assert.assertTrue(addressbookpage.isSuccessMessageDisplayed());
	}
	
	@AfterClass(groups="smoke")
	public void afterClass() {
		removeDriver();
	}

}
