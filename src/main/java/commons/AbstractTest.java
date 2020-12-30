package commons;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest {
	// Declare log
	protected final Log log;

	// constractor
	protected AbstractTest() {
		log = LogFactory.getLog(getClass());
	}

	protected void showBrowserConsolelogs(WebDriver driver) {
		if (driver.toString().contains("chrome")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for (LogEntry logging : logList) {
				System.out
						.println("----------" + logging.getLevel().toString() + "----------\n" + logging.getMessage());
			}
		}
	}

	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();

	// protected
	protected void removeDriver() {
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String cmd = "";
			// close browser
			if (getDriver() != null) {
				getDriver().manage().deleteAllCookies();
				getDriver().quit();
			}
// close browser driver
			if (getDriver().toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
			} else if (getDriver().toString().toLowerCase().contains("internetexplorer")) {
				if (osName.toLowerCase().contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (getDriver().toString().toLowerCase().contains("firefox")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill geckodriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				}
			} else if (getDriver().toString().toLowerCase().contains("edge")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill edgedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq edgedriver*\"";
				}
			}
			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		threadLocalDriver.remove();
	}

	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}

	private void setDriver(WebDriver driver) {
		threadLocalDriver.set(driver);
	}

	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		Browser browser = Browser.valueOf(browserName.toUpperCase());

		if (browser == Browser.FIREFOX) {
			// System.setProperty("webdriver.gecko.driver",
			// ".\\browserDrivers\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();

			//
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,
					GlobalConstants.BROWSER_LOG_FOLDER + "\\Firefox.log");

			// Add extension for Firefox
			FirefoxProfile profile = new FirefoxProfile();
			File translate = new File(GlobalConstants.BROWSER_EXTENSION_FOLDER + "\\to_google_translate-4.0.6-fx.xpi");
			profile.addExtension(translate);
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(profile);
			// download file
			options.addPreference("browser.download.folderList", 2);
			options.addPreference("browser.download.dir", GlobalConstants.DOWLOAD_FILE);
			options.addPreference("browser.download.useDownloadDir", true);
			options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
			options.addPreference("pdfjs.disabled", true);
			// cháº¡y á»Ÿ cháº¿ Ä‘á»™ áº©n danh ( náº¿u nhÆ° khÃ´ng muá»‘n lÆ°u láº¡i lá»‹ch sá»­ cháº¡y)
			options.addArguments("-private");
			setDriver(new FirefoxDriver(options));

		} else if (browser == Browser.FIREFOX_HEADLESS) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setHeadless(true);
			setDriver(new ChromeDriver());
		} else if (browser == Browser.CHROME) {
			// System.setProperty("webdriver.chrome.driver",
			// ".\\browserDrivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();

			// Add extension for Chrome
			File translate = new File(GlobalConstants.BROWSER_EXTENSION_FOLDER + "\\extension_2_0_9_0.crx");
			// download file on chrome
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", GlobalConstants.DOWLOAD_FILE);

			ChromeOptions options = new ChromeOptions();
			options.addExtensions(translate);
			// setting bằng tiếng Việt,Anh,...
			options.addArguments("--lang=vi");
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.setExperimentalOption("prefs", chromePrefs);
			// chạy chế độ ẩn danh
			options.addArguments("--incognito");

			setDriver(new ChromeDriver(options));

		} else if (browser == Browser.CHROME_HEADLESS) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1366x768");
			setDriver(new ChromeDriver());
		} else if (browser == Browser.EDGE) {
			// System.setProperty("webdriver.edge.driver",
			// ".\\browserDrivers\\edgedriver.exe");
			WebDriverManager.edgedriver().setup();
			setDriver(new EdgeDriver());
		} else if (browser == Browser.IE) {
			WebDriverManager.iedriver().arch32().setup();
			setDriver(new InternetExplorerDriver());
		} else if (browser == Browser.SAFARI) {
			setDriver(new SafariDriver());
		} else if (browser == Browser.COCCOC) {
			// System.setProperty("webdriver.chrome.driver",
			// ".\\browserDrivers\\chromedriver 83.exe");
			WebDriverManager.chromedriver().browserVersion("83").setup();
			// WebDriverManager.chromedriver().driverVersion("83.0.4103.39").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Users\\SHC\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			setDriver(new ChromeDriver(options));
		} else {
			throw new RuntimeException("Please input your browser name!");
		}
		getDriver().get(appUrl);
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
		return getDriver();
	}

	protected int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
			if (condition == true) {
			} else {
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lá»—i vÃ o ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
			if (condition == false) {
			} else {
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected String getDateNumber() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return formatter.format(date).replace(":", "-").replace(" ", "-");
	}

	protected String getCurrentDay() {
		DateTime nowUTC = new DateTime();
		int day = nowUTC.getDayOfMonth();
		if (day < 10) {
			String dayValue = "0" + day;
			return dayValue;
		}
		return String.valueOf(day);
	}

	protected String getCurrentMonth() {
		DateTime now = new DateTime();
		int month = now.getMonthOfYear();
		if (month < 10) {
			String monthValue = "0" + month;
			return monthValue;
		}
		return String.valueOf(month);
	}

	protected String getCurrentYear() {
		DateTime now = new DateTime();
		return String.valueOf(now.getYear());
	}

	protected String getWordpressToday() {
		return getCurrentDay() + "/" + getCurrentMonth() + "/" + getCurrentYear();
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
}
