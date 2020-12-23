package pageUI.wordpress.admind;

public class NewEditPostPageUI {

	public static final String POST_TITLE_TEXBOX = "//input[@id='title']";
	public static final String TENY_MCE_CONTENT_TEXBOX = "//body[@id='tinymce']/p";
	public static final String TENY_MCE_IFRAME = "//iframe[@id='content_ifr']";
	public static final String CATEGORY_CHECKBOX = "//label[contains(text(),'%s')]";
	public static final String TAG_TEXTBOX = "//input[@id='new-tag-post_tag']";
	public static final String TAG_BUTTON = "//input[@class='button tagadd']";
	public static final String SET_FEATURE_IMAGE = "//a[@id='set-post-thumbnail']";
	public static final String UPLOAD_FILE_TAB = "//button[@id='menu-item-upload']";
	public static final String SET_FEATURED_BUTTON = "//button[contains(text(),'Set featured image')]";
	public static final String PUBLISH_OR_UPDATE_BUTTON = "//input[@id='publish']";
	public static final String POST_IMAGE_DISPLAYED = "//a[@class='thickbox']//img[contains(@src,'%s')]";
	public static final String REMOVE_TAG_BUTTON = "//span[@class='screen-reader-text' and contains(text(),'%s')]/preceding-sibling::span";
	public static final String ALL_CATEGORY = "//li[@class='tabs']/a[text()='All Categories']";
	public static final String MOVE_TO_TRASH = "//a[contains(text(),'Move to Trash')]";
	
}
