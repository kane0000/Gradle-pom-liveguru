package pageFactory.liveGuru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageObject extends AbstractPage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.CSS, using = "button[title='Register']")
	private WebElement registerButton;
	
	@FindBy(how = How.ID, using = "firstname")
	private WebElement firstnameTextbox;
	
	@FindBy(how = How.ID, using = "lastname")
	private WebElement lastnameTextbox;
	
	@FindBy(how = How.ID, using = "email_address")
	private WebElement emailTextbox;
	
	@FindBy(how = How.ID, using = "password")
	private WebElement passwordTextbox;
	
	@FindBy(how = How.ID, using = "confirmation")
	private WebElement confirmPasswordTextbox;
	
	public void clickToRegisterButton() {
		waitElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);
	}
	public void inputToFistnamTextbox(String fistnamValue) {
		waitElementVisible(driver, firstnameTextbox);
		sendkeyToElement(driver, firstnameTextbox,fistnamValue);
		
	}
	public void inputToLastnameTextbox(String lastnameValue) {
		waitElementVisible(driver,lastnameTextbox);
		sendkeyToElement(driver, lastnameTextbox,lastnameValue);
		
	}
	public void inputToEmailTextbox(String emailValue) {
		waitElementVisible(driver,emailTextbox);
		sendkeyToElement(driver, emailTextbox,emailValue);
		
	}
	public void inputToPasswordTextbox(String passwordValue) {
		waitElementVisible(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox,passwordValue);
		
	}
	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitElementVisible(driver, confirmPasswordTextbox);
		sendkeyToElement(driver, confirmPasswordTextbox,confirmPassword );
		
	}
	

}
