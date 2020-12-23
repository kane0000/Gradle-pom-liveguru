package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class SearchTermsPageObject extends AbstractPage {
    WebDriver driver;
	public SearchTermsPageObject(WebDriver driver) {
	this.driver = driver;
	}
}
