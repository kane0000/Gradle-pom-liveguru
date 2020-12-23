package com.liveguru.users;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import driverFactory.DriverManager;
import pageObjects.liveGuru.AboutUsPageObject;
import pageObjects.liveGuru.AddressBookPageObject;
import pageObjects.liveGuru.AdvancedSearchPageObject;
import pageObjects.liveGuru.ContactUsPageObject;
import pageObjects.liveGuru.CustomerServicePageObject;
import pageObjects.liveGuru.HomePageObject;
import pageObjects.liveGuru.LoginPageObject;
import pageObjects.liveGuru.MyDashboardPageObject;
import pageObjects.liveGuru.PageGeneratorManager;
import pageObjects.liveGuru.RegisterPageObject;
import pageObjects.liveGuru.SearchTermsPageObject;

public class Level_14_Share_State extends AbstractTest {
	private WebDriver driver;
	HomePageObject hompage;
	LoginPageObject loginpage;
	RegisterPageObject registerpage;
	MyDashboardPageObject mydashboardpage;
	AddressBookPageObject addressbookpage;
	AboutUsPageObject aboutuspage;
	AdvancedSearchPageObject advancedsearchpage;
	CustomerServicePageObject customerservicepage;
    ContactUsPageObject contactuspage;
    SearchTermsPageObject searchtermspage;

	DriverManager driverManager;
	String email = "automation" + randomNumber() + "@gmai.com";
	String password = "123456";

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		hompage = PageGeneratorManager.getHomePage(driver);
	}
	@Test
	public void TC_01_Login() {
		loginpage = hompage.clickToMyAccountLink();

		loginpage.inputEmailTextbox(email);
		loginpage.inputPasswordTextbox(password);
		mydashboardpage = loginpage.clickLoginButton();
		Assert.assertTrue(mydashboardpage.isMyDashboardHeaderDisplayed());

	}

	@Test
	public void TC_02_Add_Address_Book() {
		
		addressbookpage = mydashboardpage.clickManageAddresses();
		addressbookpage.inputTelephoneTexbox("0987888999");
		addressbookpage.inputStreetAddressTextbox("123 Ngo Gia Tu");
		addressbookpage.inputCityTexbox("Ha Noi");
		addressbookpage.inputZipTextbox("999888");
		addressbookpage.selectValueInCountryDropdown("Vietnam");
		addressbookpage.clickSaveAddressButton();
		Assert.assertTrue(addressbookpage.isSuccessMessageDisplayed());

	}
	@Test
	public void TC_03_Navigate_Page_In_Footer() {
		aboutuspage = mydashboardpage.openAboutUsPageLink(driver);
		advancedsearchpage = aboutuspage.openAdvancedSearchPageLink(driver);
		customerservicepage = advancedsearchpage.openCustomerServivePageLink(driver);
		searchtermspage = customerservicepage.openSearchTermsPageLink(driver);
		contactuspage = searchtermspage.openContactUsPagelink(driver);
		advancedsearchpage = contactuspage.openAdvancedSearchPageLink(driver);
		searchtermspage = advancedsearchpage.openSearchTermsPageLink(driver);
	}

	
	@AfterClass
	public void afterClass() {
		removeDriver();
	}

}
