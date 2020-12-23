package pageObject.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class PagesPageObject extends AbstractPage {
	private WebDriver driver;
	public PagesPageObject(WebDriver driver) {
	this.driver = driver;
}
}
