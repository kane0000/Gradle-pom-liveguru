package pageUI.wordpress.admind;

public class AbstractWordpressPageUI {

	public static final String DYNAMIC_MENU_BY_NAME = "//div[text()='%s']";
	public static final String DYNAMIC_SUCCESS_MESSAGE_WITH_VALUE = "//a[contains(text(),'%s')]";
	public static final String DYNAMIC_SUCCESS_MESSAGE_VALUE = "//div[@id='message']/p[text()='%s']";
	public static final String DYNAMIC_COLUMN_AND_ROW = "//td[@data-colname='%s']//a[text()='%s']";
	public static final String POST_TITLE_LINK = "//td[@data-colname='Title']//a[text()='%s']";
	public static final String DYNAMIC_POST_LATEST_POST = "//p[@class='post-categories']/a[text()='%s']/parent::p/following-sibling::h2/a[text()='%s']/parent::h2/following-sibling::p/a[text()='%s']";
	public static final String DYNAMIC_POST_AVATAR_IMAGE_BY_TITLE = "//a[@title='%s']/img[contains(@src,'%s')]";
	public static final String DYNAMIC_POST_TITLE = "//h2[@class='post-title']/a[text()='%s']";
	public static final String SEARCH_ICON = "//a[@class='search-toggle']";
	public static final String SEARCH_TEXTBOX = "//input[@class='search-field']";
	public static final String SEARCH_BUTTON = "//span[@class='fa fw fa-search']";
	public static final String CLASSIC_EDITOR = "//a[contains(@aria-label,'%s') and text()='Classic Editor']";
			                                                       
	
}
