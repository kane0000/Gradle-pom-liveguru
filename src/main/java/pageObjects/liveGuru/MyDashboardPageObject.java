package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.liveGuru.MyDashboardPageUI;

public class MyDashboardPageObject extends AbstractPage {
	private WebDriver driver;

	public MyDashboardPageObject(WebDriver driver) {
	this.driver = driver;
	}
	public String getWelcomeSuccessMessage() {
		waitElementVisible(driver, MyDashboardPageUI.WELCOME_SUCCESS_MESSAGE);
		return getTextElement(driver, MyDashboardPageUI.WELCOME_SUCCESS_MESSAGE);
	}
	public AddressBookPageObject clickManageAddresses() {
		waitElementClickable(driver,  MyDashboardPageUI.MANAGE_ADDRESS_LINK);
		clickToElement(driver, MyDashboardPageUI.MANAGE_ADDRESS_LINK);
		return PageGeneratorManager.getAddressbookPage(driver);
		//return  new AddressBookPageObject(driver);
	}
	public HomePageObject clickLogoutLink() {
		waitElementClickable(driver,  MyDashboardPageUI.ACCOUNT_HEADER);
		clickToElement(driver, MyDashboardPageUI.ACCOUNT_HEADER);
		waitElementClickable(driver,  MyDashboardPageUI.LOGOUT_BUTTON);
		clickToElement(driver, MyDashboardPageUI.LOGOUT_BUTTON);
		return PageGeneratorManager.getHomePage(driver);
		//return new HomePageObject(driver);
		
	}
	public boolean isMyDashboardHeaderDisplayed() {
		waitElementClickable(driver,  MyDashboardPageUI.MY_DASHBOARD_HEADER);
		return isElementDisplayed(driver, MyDashboardPageUI.MY_DASHBOARD_HEADER);
	}

}
