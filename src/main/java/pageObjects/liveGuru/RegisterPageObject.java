package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import io.qameta.allure.Step;
import pageUIs.liveGuru.RegisterPageUI;

public class RegisterPageObject extends AbstractPage {
	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
	this.driver = driver;
	}
	public MyDashboardPageObject clickToRegisterButton() {
		waitElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
		return PageGeneratorManager.getMyDashboardPage(driver);
		//return new MyDashboardPageObject(driver);
	}

	public String getErrorMessageAtFistnameTextbox() {
		waitElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_FISTNAM_TEXBOX);
		return getTextElement(driver, RegisterPageUI.ERROR_MESSAGE_FISTNAM_TEXBOX);
		
	}

	public String getErrorMessageAtLastnameTextbox() {
		waitElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_LASTNAM_TEXBOX);
		return getTextElement(driver, RegisterPageUI.ERROR_MESSAGE_LASTNAM_TEXBOX);
		
	}

	public String getErrorMessageAtEmailTextbox() {
		waitElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_EMAIL_TEXBOX);
		return getTextElement(driver, RegisterPageUI.ERROR_MESSAGE_EMAIL_TEXBOX);
		
	}

	public String getErrorMessageAtPasswordTextbox() {
		waitElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_PASSWORD_TEXBOX);
		return getTextElement(driver, RegisterPageUI.ERROR_MESSAGE_PASSWORD_TEXBOX);
		
	}

	public String getErrorMessageAtConfirmPassworkTextbox() {
		waitElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_CONFIRM_PASSWORD_TEXBOX);
		return getTextElement(driver, RegisterPageUI.ERROR_MESSAGE_CONFIRM_PASSWORD_TEXBOX);
		
	}

	public void inputToEmailTexbox(String emailValue) {
		waitElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX,emailValue);
		
	}

	public String getInvalidErrorMessageAtEmailTextbox() {
		waitElementVisible(driver, RegisterPageUI.INVALID_ERROR_MESSAGE_EMAIL_TEXBOX);
		return getTextElement(driver, RegisterPageUI.INVALID_ERROR_MESSAGE_EMAIL_TEXBOX);
		
	}
	@Step("input to password textbox {0}")
	public void inputToPasswordTextbox(String passwordValue) {
		waitElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX,passwordValue);
		
	}

	public String getInvalidErrorMessageAtPasswordTextbox() {
		waitElementVisible(driver, RegisterPageUI.INVALID_ERROR_MESSAGE_PASSWORD_TEXBOX);
		return getTextElement(driver, RegisterPageUI.INVALID_ERROR_MESSAGE_PASSWORD_TEXBOX);
		
	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX,confirmPassword );
		
	}

	public String getInvalidErrorMessageAtConfirmPasswordTextbox() {
		waitElementVisible(driver, RegisterPageUI.INVALID_ERROR_MESSAGE_CONFIRM_PASSWORD_TEXBOX);
		return getTextElement(driver, RegisterPageUI.INVALID_ERROR_MESSAGE_CONFIRM_PASSWORD_TEXBOX);
		
	}
	@Step("input to firtname textbox {0}")
	public void inputToFistnamTextbox(String fistnameValue) {
		waitElementVisible(driver, RegisterPageUI.FISTNAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.FISTNAME_TEXTBOX, fistnameValue);
		
	}
	@Step("input to lastname textbox {0}")
	public void inputToLastnameTextbox(String lastnameValue) {
		waitElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX,lastnameValue);
		
	}
@Step("input to email textbox {0}")
	public void inputToEmailTextbox(String emailValue) {
		waitElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX,emailValue);
		
	}

}
