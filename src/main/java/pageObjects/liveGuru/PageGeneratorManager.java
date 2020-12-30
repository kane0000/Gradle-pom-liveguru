package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	//Khởi tạo các page
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	public static MyDashboardPageObject getMyDashboardPage(WebDriver driver) {
		return new MyDashboardPageObject(driver);
	}
	public static AboutUsPageObject getAboutUsPage(WebDriver driver) {
		return new AboutUsPageObject(driver);
	}
	public static AdvancedSearchPageObject getAdvancedSearchPage(WebDriver driver) {
		return new AdvancedSearchPageObject(driver);
	}
	public static ContactUsPageObject getContactUsPage(WebDriver driver) {
		return new ContactUsPageObject(driver);
	}
	public static CustomerServicePageObject getCustomerServicePage(WebDriver driver) {
		return new CustomerServicePageObject(driver);
	}
	public static SearchTermsPageObject getSearchTermsPage(WebDriver driver) {
		return new SearchTermsPageObject(driver);
	}
	public static AddressBookPageObject getAddressbookPage(WebDriver driver) {
		return new AddressBookPageObject(driver);
	}
	public static LoginManagerPageObject getLoginManagerPage(WebDriver driver) {
		return new LoginManagerPageObject(driver);
	}
	public static ManagerCustomerPageObject getManagerCustomerPage(WebDriver driver) {
		return new ManagerCustomerPageObject(driver);
	}
	
}
