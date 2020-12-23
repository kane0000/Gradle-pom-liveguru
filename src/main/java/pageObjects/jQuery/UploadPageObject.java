package pageObjects.jQuery;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.jQuery.UploadPageUI;

public class UploadPageObject extends AbstractPage {
    WebDriver driver;
	public UploadPageObject(WebDriver driver) {
	this.driver = driver;
	}
	public boolean isFileLoaded(String fileName) {
		waitElementVisible(driver, UploadPageUI.DYNAMIC_IMAGE_TEXT, fileName);
		return isElementDisplayed(driver, UploadPageUI.DYNAMIC_IMAGE_TEXT, fileName);
	}
	public void clickToStartButton(String fileName) {
		waitElementClickable(driver, UploadPageUI.DYNAMIC_IMAGE_START_BUTTON, fileName);
		clickToElement(driver, UploadPageUI.DYNAMIC_IMAGE_START_BUTTON, fileName);
	}
	public boolean isFileUploadedSuccess(String fileName) {
		waitElementVisible(driver, UploadPageUI.DYNAMIC_IMAGE_LINK, fileName);
		return isElementDisplayed(driver, UploadPageUI.DYNAMIC_IMAGE_LINK, fileName);
		
	}

}
