package pageFactory.liveGuru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePageObject extends AbstractPage{

//	// cách 2
//	@FindBy(xpath = "//div[@class='footer']//a[text()='My Account']")
//	private WebElement my_Account_Link;
//	
//	// nhiều element
//	@FindBy(xpath = "//input")
//	private List<WebElement> textboxes;
//	
//	//  And; bothCriteria: cả 2 tiêu chí.
//	// hoặc dùng xpath thay cho findbys: xpath = //input[@id='Pasword' and @id='ConfirmPassword']
//	@FindBys({@FindBy(how = How.NAME , using = "uid") , @FindBy(how = How.NAME, using = "pasword")})
//	private List<WebElement> bothCriteria;
//	
//	// Or; eitherCriterion: 1 trong 2 tiêu chí.
//	// hoặc dùng xpath thay cho findAll: xpath = //input[@id='Pasword' or @id='ConfirmPassword']
//	@FindAll({@FindBy(how = How.NAME , using = "uid") , @FindBy(how = How.NAME, using = "pasword")})
//	private List<WebElement> eitherCriterion;

	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[@class='footer']//a[text()='My Account']")
	private WebElement myAccountLink;
	
	public void clickToMyAccountLink() {
		waitElementClickable(driver, myAccountLink);
		clickToElement(driver, myAccountLink);
	}

}
