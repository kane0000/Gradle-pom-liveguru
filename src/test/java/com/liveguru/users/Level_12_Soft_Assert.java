package com.liveguru.users;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
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
import reportConfig.AllureTestListener;
@Listeners ({AllureTestListener.class})
public class Level_12_Soft_Assert extends AbstractTest {
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
	private String email,password;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homepage = PageGeneratorManager.getHomePage(driver);
		email = "automation" + randomNumber() + "@gmai.com";
		password = "123456";
	}

	@Test
	public void TC_01_Assert() {
		loginpage = homepage.clickToMyAccountLink();

		registerpage = loginpage.clickToCreateAnAccountButton();

		registerpage.inputToFistnamTextbox("Kane");
		registerpage.inputToLastnameTextbox("Lee");
		registerpage.inputToEmailTextbox(email);
		registerpage.inputToPasswordTextbox(password);
		registerpage.inputToConfirmPasswordTextbox(password);
		mydashboardpage = registerpage.clickToRegisterButton();

		//Failed
		Assert.assertEquals(mydashboardpage.getWelcomeSuccessMessage(), "Thank you for registering with Main Website Store");

		homepage = mydashboardpage.clickLogoutLink();
		loginpage = homepage.clickToMyAccountLink();

		loginpage.inputEmailTextbox(email);
		loginpage.inputPasswordTextbox(password);
		mydashboardpage = loginpage.clickLoginButton();
		
		Assert.assertFalse(mydashboardpage.isMyDashboardHeaderDisplayed());

	}

	@Test
	
	public void TC_02_Verify_Assert() throws InterruptedException {
		
		loginpage = homepage.clickToMyAccountLink();

		registerpage = loginpage.clickToCreateAnAccountButton();
		Thread.sleep(2000);

		registerpage.inputToFistnamTextbox("Kane");
		registerpage.inputToLastnameTextbox("Lee");
		registerpage.inputToEmailTextbox(email);
		registerpage.inputToPasswordTextbox(password);
		registerpage.inputToConfirmPasswordTextbox(password);
		mydashboardpage = registerpage.clickToRegisterButton();
		
		verifyEquals(mydashboardpage.getWelcomeSuccessMessage(), "Thank you for registering with Main Website Store");

		homepage = mydashboardpage.clickLogoutLink();
		loginpage = homepage.clickToMyAccountLink();

		loginpage.inputEmailTextbox(email);
		loginpage.inputPasswordTextbox(password);
		mydashboardpage = loginpage.clickLoginButton();
		
		verifyTrue(mydashboardpage.isMyDashboardHeaderDisplayed());

	}
	
	@AfterMethod
	public void afterMethod() {
		removeDriver();
	}

}
