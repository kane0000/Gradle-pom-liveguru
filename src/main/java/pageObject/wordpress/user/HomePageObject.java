package pageObject.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;

public class HomePageObject extends AbstractPage {
	private WebDriver driver;
	public HomePageObject(WebDriver driver) {
	this.driver = driver;
}
	public PostDetailPageObject clickToPostDetailWithTitleName() {
		// TODO Auto-generated method stub
		return PageGeneratorManager.getPostDetailUserPage(driver);
	}
	
	
	
}
