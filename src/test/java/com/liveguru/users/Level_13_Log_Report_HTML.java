package com.liveguru.users;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
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
// Không bắt buộc
@Epic("Regression Tests")
@Feature("Login Tests")
public class Level_13_Log_Report_HTML extends AbstractTest {
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
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		// Trường hợp cần login, đăng kí mới sử dụng:
//		log.info("Pre-Condition - Step 01: Open browser and application");
		driver = getBrowserDriver(browserName, appUrl);
		homepage = PageGeneratorManager.getHomePage(driver);
		
		email = "automation" + randomNumber() + "@gmai.com";
		password = "123456";
	}

	@Description("User 01 - Register to system and verify register success")
	@Story("Register to system")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void TC_01_Register() {
		log.info("Register - Step 01: Click to 'My Account' link");
		
		loginpage = homepage.clickToMyAccountLink();

		log.info("Register - Step 02: Click to 'Create An Account' button");
		registerpage = loginpage.clickToCreateAnAccountButton();

		// Phần bắt buộc
		log.info("Register - Step 03: Input to all Requied fields");
		registerpage.inputToFistnamTextbox("Kane");
		registerpage.inputToLastnameTextbox("Lee");
		registerpage.inputToEmailTextbox(email);
		registerpage.inputToPasswordTextbox(password);
		registerpage.inputToConfirmPasswordTextbox(password);
		
		log.info("Register - Step 04: Click to 'Register' button");
		mydashboardpage = registerpage.clickToRegisterButton();

		log.info("Register - Step 05: Verify welcome success message display");
		verifyEquals(mydashboardpage.getWelcomeSuccessMessage(), "Thank you for registering with Main Website Store.");

		log.info("Register - Step 06: Click to 'Logout' link");
		homepage = mydashboardpage.clickLogoutLink();

	}

	@Test
	public void TC_02_Login() {
		log.info("Login - Step 01: Click to 'My Account' link");
		loginpage = homepage.clickToMyAccountLink();

		log.info("Login - Step 02: Input to 'Email' and 'Password' textbox");
		loginpage.inputEmailTextbox(email);
		loginpage.inputPasswordTextbox(password);
		
		log.info("Login - Step 03: Click to 'Login' button");
		mydashboardpage = loginpage.clickLoginButton();
		
		log.info("Login - Step 04: Verify 'My Dashboard'header display");
		verifyFalse(mydashboardpage.isMyDashboardHeaderDisplayed());
		
	}
	
	@AfterClass
	public void afterClass() {
		removeDriver();
	}

}
