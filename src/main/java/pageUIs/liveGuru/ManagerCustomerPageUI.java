package pageUIs.liveGuru;

public class ManagerCustomerPageUI {
	public static final String CLOSE_INCOMING = "//a[@title='close']";
	public static final String DYNAMIC_COLUMN_NAME = "//tr[@class='headings']//span[text()='%s']/ancestor::th/preceding-sibling::th";
	public static final String DYNAMIC_TEXTBOX_BY_INDEX = "//tr[@class='filter']//th[%s]//input";
	public static final String DYNAMIC_BUTTON_TITLE = "//button[@title='Search']";
	public static final String DYNAMIC_VALUE_ROWNUMBER_AND_COLUMNNAME = "//tr[%s]//td[%s][contains(text(),'%s')]";
	
}
