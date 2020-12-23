package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.liveGuru.LoginPageUI;

public class LoginPageObject extends AbstractPage{
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
	this.driver = driver;
	}
	public RegisterPageObject clickToCreateAnAccountButton() {
		waitElementClickable(driver, LoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
		clickToElement(driver, LoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
		return PageGeneratorManager.getRegisterPage(driver);
		//return new RegisterPageObject(driver);
	}
// chứa những action của từng page
	public void inputEmailTextbox(String email) {
		waitElementClickable(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
	}
	public MyDashboardPageObject clickLoginButton() {
		waitElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getMyDashboardPage(driver);
		//return new MyDashboardPageObject(driver);
	}
	public void inputPasswordTextbox(String password) {
		waitElementClickable(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
		
	}
	
}
