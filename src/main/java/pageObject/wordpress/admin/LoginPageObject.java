package pageObject.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUI.wordpress.admind.LoginPageUI;

public class LoginPageObject extends AbstractPage {
	private WebDriver driver;
	
	
	public LoginPageObject(WebDriver driver) {
	this.driver = driver;
}
	public void inputToEmailTexbox(String email) {
		waitElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
		
	}
	public DashboardPageObject clickToContinueOrLoginButton() {
		waitElementClickable(driver, LoginPageUI.CONTINUE_OR_LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.CONTINUE_OR_LOGIN_BUTTON);
		return PageGeneratorManager.getDashboardAminPage(driver);
	}
	
	
	public void inputToPasswordTextbox(String password) {
		waitElementVisible(driver, LoginPageUI.PASWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASWORD_TEXTBOX, password);
	}
}
