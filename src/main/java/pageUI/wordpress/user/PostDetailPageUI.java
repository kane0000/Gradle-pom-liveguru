package pageUI.wordpress.user;

public class PostDetailPageUI {

	public static final String CATEGORY_NAME ="//p[@class='post-categories']/a[text()='%s']";
	public static final String TITLE_NAME ="//h1[text()='%s']";
	public static final String IMAGE_NAME ="//img[contains(@src,'%s')]";
	public static final String CONTENT_NAME ="//div[@class='post-content']/p[text()='%s']";
	public static final String TAG_NAME ="//div[@class='post-tags']/a[text()='%s']";
	public static final String DAY_CREATE ="//span[@class='post-meta-date']/a[text()='%s']";
	public static final String AUTHOR_NAME ="//span[@class='post-meta-author']/a[text()='%s']";
}
