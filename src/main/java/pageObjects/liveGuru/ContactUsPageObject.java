package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class ContactUsPageObject extends AbstractPage {
    WebDriver driver;
	public ContactUsPageObject(WebDriver driver) {
	this.driver = driver;
	}
}
