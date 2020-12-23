package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.liveGuru.LoginManagerPageUI;

public class LoginManagerPageObject extends AbstractPage{
	private WebDriver driver;

	public LoginManagerPageObject(WebDriver driver) {
	this.driver = driver;
	}

	public ManagerCustomerPageObject clickToLoginButton() {
		waitElementClickable(driver, LoginManagerPageUI.LOGIN_TO_BUTTON);
		clickToElement(driver, LoginManagerPageUI.LOGIN_TO_BUTTON);
		return PageGeneratorManager.getManagerCustomerPage(driver);
	}

	public void inputToPasswordTexbox(String email) {
		waitElementVisible(driver, LoginManagerPageUI.PASSWORD_TO_TEXTBOX);
		sendkeyToElement(driver, LoginManagerPageUI.PASSWORD_TO_TEXTBOX, email);
		
	}

	public void inputToEmailTexbox(String password) {
		waitElementVisible(driver, LoginManagerPageUI.EMAIL_TO_TEXTBOX);
		sendkeyToElement(driver, LoginManagerPageUI.EMAIL_TO_TEXTBOX, password);
		
	}
	

}
