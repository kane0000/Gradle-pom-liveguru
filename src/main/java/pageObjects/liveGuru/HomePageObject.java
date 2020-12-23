package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.liveGuru.HomePageUI;

public class HomePageObject extends AbstractPage {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPageObject clickToMyAccountLink() {
		waitElementClickable(driver, HomePageUI.FOOTER_MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.FOOTER_MY_ACCOUNT_LINK);
         return PageGeneratorManager.getLoginPage(driver);
		//return new LoginPageObject(driver);
	}
// chứa những action của từng page

	public boolean isFooterMyAccountLinkDisplayed() {
		waitElementVisible(driver, HomePageUI.FOOTER_MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, HomePageUI.FOOTER_MY_ACCOUNT_LINK);
	}

	public boolean isHeaderMyAccountLinkUndisplayed() {
		return isElementUndisplayed(driver, HomePageUI.HEADER_MY_ACCOUNT_LINK);
	}

	public boolean isErrorMessageAtSubscribeTextboxUndisplayed() {
		return isElementUndisplayed(driver, HomePageUI.ERROR_MESSAGE_AT_SUBSCRIBE_TEXTBOX);
		
	}

	public void clickToSubscribeButton() {
		waitElementClickable(driver, HomePageUI.SUBSCRIBE_BUTTON);
		clickToElement(driver, HomePageUI.SUBSCRIBE_BUTTON);
		
	}

	public boolean isErrorMessageAtSubscribeTextboxDisplayed() {
		waitElementVisible(driver, HomePageUI.ERROR_MESSAGE_AT_SUBSCRIBE_TEXTBOX);
		return isElementDisplayed(driver, HomePageUI.ERROR_MESSAGE_AT_SUBSCRIBE_TEXTBOX);
	}
}
