package com.liveguru.users;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
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

public class Level_09_Dynamic_Locator extends AbstractTest {
	private WebDriver driver;
	HomePageObject homepage;
	LoginPageObject loginpage;
	RegisterPageObject registerpage;
	MyDashboardPageObject mydashboardpage;
	AddressBookPageObject addressbookpage;
	AboutUsPageObject aboutuspage;
	AdvancedSearchPageObject advancedsearchpage;
	CustomerServicePageObject customerservicepage;
    ContactUsPageObject contactuspage;
    SearchTermsPageObject searchtermspage;
	
	String email = "automation" + randomNumber() + "@gmai.com";
	String password = "123456";

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homepage = PageGeneratorManager.getHomePage(driver);
		// hompage = new HomePageObject(driver);
	}

	@Test
	public void TC_01_Register() {
		loginpage = homepage.clickToMyAccountLink();
		// loginpage = new LoginPageObject(driver);

		registerpage = loginpage.clickToCreateAnAccountButton();
		// registerpage = new RegisterPageObject(driver);

		registerpage.inputToFistnamTextbox("Kane");
		registerpage.inputToLastnameTextbox("Lee");
		registerpage.inputToEmailTextbox(email);
		registerpage.inputToPasswordTextbox(password);
		registerpage.inputToConfirmPasswordTextbox(password);
		mydashboardpage = registerpage.clickToRegisterButton();

		// mydashboardpage = new MyDashboardPageObject(driver);
		Assert.assertEquals(mydashboardpage.getWelcomeSuccessMessage(), "Thank you for registering with Main Website Store.");

		homepage = mydashboardpage.clickLogoutLink();
		// hompage = new HomePageObject(driver);

	}

	
	public void TC_02_Login() {
		loginpage = homepage.clickToMyAccountLink();
		// loginpage = new LoginPageObject(driver);

		loginpage.inputEmailTextbox(email);
		loginpage.inputPasswordTextbox(password);
		mydashboardpage = loginpage.clickLoginButton();
		// mydashboardpage = new MyDashboardPageObject(driver);
		Assert.assertTrue(mydashboardpage.isMyDashboardHeaderDisplayed());

	}

	
	public void TC_03_Add_Address_Book() {
		addressbookpage = mydashboardpage.clickManageAddresses();
		// addressbookpage = new AddressBookPageObject(driver);
		addressbookpage.inputTelephoneTexbox("0987888999");
		addressbookpage.inputStreetAddressTextbox("123 Ngo Gia Tu");
		addressbookpage.inputCityTexbox("Ha Noi");
		addressbookpage.inputZipTextbox("999888");
		addressbookpage.selectValueInCountryDropdown("Vietnam");
		addressbookpage.clickSaveAddressButton();
		Assert.assertTrue(addressbookpage.isSuccessMessageDisplayed());

	}
	
	public void TC_04_Little_Page() {
		aboutuspage = (AboutUsPageObject) mydashboardpage.openFooterPageLink(driver,"About Us");
		advancedsearchpage = (AdvancedSearchPageObject) aboutuspage.openFooterPageLink(driver,"Advanced Search");
		customerservicepage = (CustomerServicePageObject) advancedsearchpage.openFooterPageLink(driver,"Customer Service");
		searchtermspage = (SearchTermsPageObject) customerservicepage.openFooterPageLink(driver,"Search Terms");
		contactuspage = (ContactUsPageObject) searchtermspage.openFooterPageLink(driver,"Contact Us");
		advancedsearchpage = (AdvancedSearchPageObject) contactuspage.openFooterPageLink(driver,"Advanced Search");
		searchtermspage = (SearchTermsPageObject) advancedsearchpage.openFooterPageLink(driver,"Search Terms");
	}
	
	public void TC_05_More_Page() {
		
		searchtermspage.openFooterPageByName(driver,"About Us");
		aboutuspage = PageGeneratorManager.getAboutUsPage(driver);
				
		 aboutuspage.openFooterPageByName(driver,"Advanced Search");
		advancedsearchpage = PageGeneratorManager.getAdvancedSearchPage(driver);
				
		 advancedsearchpage.openFooterPageByName(driver,"Customer Service");
		customerservicepage = PageGeneratorManager.getCustomerServicePage(driver);
				
	     customerservicepage.openFooterPageByName(driver,"Search Terms");
	     searchtermspage = PageGeneratorManager.getSearchTermsPage(driver);
	}

	
	@AfterClass
	public void afterClass() {
		removeDriver();
	}

}
