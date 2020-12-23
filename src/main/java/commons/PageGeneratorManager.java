package commons;

import org.openqa.selenium.WebDriver;

import pageObject.wordpress.admin.DashboardPageObject;
import pageObject.wordpress.admin.LoginPageObject;
import pageObject.wordpress.admin.MediaPageObject;
import pageObject.wordpress.admin.NewEditPostPageObject;
import pageObject.wordpress.admin.PagesPageObject;
import pageObject.wordpress.admin.PostsPageObject;
import pageObject.wordpress.user.HomePageObject;
import pageObject.wordpress.user.PostDetailPageObject;
import pageObject.wordpress.user.SearchResultPageObject;


public class PageGeneratorManager {
	
	public static LoginPageObject getLoginAdminPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	public static DashboardPageObject getDashboardAminPage(WebDriver driver) {
		return new DashboardPageObject(driver);
	}
	public static MediaPageObject getMediaAdminPage(WebDriver driver) {
		return new MediaPageObject(driver);
	}
	public static PagesPageObject getPagesAfminPage(WebDriver driver) {
		return new PagesPageObject(driver);
	}
	public static PostsPageObject getPostsAdminPage(WebDriver driver) {
		return new PostsPageObject(driver);
	}
	public static HomePageObject getHomeUserPage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	public static PostDetailPageObject getPostDetailUserPage(WebDriver driver) {
		return new PostDetailPageObject(driver);
	}
	public static SearchResultPageObject getSearchResultUserPage(WebDriver driver) {
		return new SearchResultPageObject(driver);
	}
	public static NewEditPostPageObject getNewEditPostAdmindPage(WebDriver driver) {
		return new NewEditPostPageObject(driver);
	}
	
}
