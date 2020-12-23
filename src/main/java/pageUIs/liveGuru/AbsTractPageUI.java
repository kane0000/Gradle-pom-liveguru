package pageUIs.liveGuru;

public class AbsTractPageUI {
	public static final String ABOUT_US_LINK = "//div[@class='footer']//a[text()='About Us']";
	
	public static final String ADVANCED_SEARCH_LINK = "//div[@class='footer']//a[text()='Advanced Search']";

	public static final String CUSTOMER_SERVICE_LINK = "//div[@class='footer']//a[text()='Customer Service']";

	public static final String SEARCH_TERMS_LINK = "//div[@class='footer']//a[text()='Search Terms']";

	public static final String CONTACT_US_LINK = "//div[@class='footer']//a[text()='Contact Us']";

	public static final String FOOTER_PAGE = "//div[@class='footer']//a[text()='%s']";
	
	// Chức năng uploadFiles luôn luôn có 1 element cố định là: //input[@type='file'];
	// và hàm sendkey chỉ upload ở element này.
	public static final String UPLOAD_FILE_TYPE ="//input[@type='file']";
	// Wait cho icon loading xong
	public static final String MEDIA_PROGRESS_BAR_ICON = "//div[@class='thumbnail']/div[@class='media-progress-bar']";
	public static final String ALL_UPLOAD_IMAGE = "//div[@class='thumbnail']//img";
	
	public static final String DYNAMIC_TEXTBOX ="//input[@id='%s']";
}

