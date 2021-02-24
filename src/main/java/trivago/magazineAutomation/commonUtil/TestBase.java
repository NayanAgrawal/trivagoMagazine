package trivago.magazineAutomation.commonUtil;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;

/**
 * The TestBase program defines base code of browser selection, loading of URL,
 * screenshot capture different waits for locators.
 *
 * @author Nayan Agawal
 * @version 1.0
 * @since 20121-02-19
 */
public class TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	public WebDriver driver;

	String url = ReadPropertiesFile.getProperty("URL");
	String browser = ReadPropertiesFile.getProperty("BROWSER");

	/**
	 * Initialize
	 *
	 * @exception MalformedURLException
	 */
	public void init() throws MalformedURLException {
		selectBrowser(browser);
		getUrl(url);
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);

	}

	/**
	 * Select browser to execute testcase
	 *
	 * @param browser name
	 */
	public void selectBrowser(String browser) throws MalformedURLException {
		if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			log.info("Creating object of " + browser);
			ChromeDriverManager.chromedriver().setup();

			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {
			FirefoxDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("headless")) {

			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			ChromeDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless", "--window-size=1200,800", "--ignore-certificate-errors", "--silent");
			driver = new ChromeDriver(options);

		}
	}

	/**
	 * Open base URL
	 *
	 * @param url
	 */
	public void getUrl(String url) {
		log.info("Navigating to : " + url);
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	/**
	 * This method is for switching browser window
	 *
	 */
	public void getWindowHandler() {
		String parentGUID = driver.getWindowHandle();
		Set<String> allGUID = driver.getWindowHandles();

		try {

			for (String guid : allGUID) {
				// one enter into if blobk if the GUID is not equal to parent window's GUID
				if (!guid.equals(parentGUID)) {
					// switch to the guid
					driver.switchTo().window(guid);
					// break the loop
					break;
				}
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Takes screenshot
	 *
	 * @param diver and imageName
	 */
	public String getScreenshot(WebDriver driver, String imageName) throws IOException {

		if (imageName.equals("")) {
			imageName = "_blank";
		}

		Calendar calander = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
				+ "/src/main/java/trivago/magazineAutomation/screenshot/";

		String actualImageName = reportDirectory + imageName + "_" + format.format(calander.getTime()) + ".png";
		File destFile = new File(actualImageName);
		FileUtils.copyFile(image, destFile);

		Reporter.log("<a href= '" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
				+ "' height='100' width ='100'/></a>");

		return actualImageName;

	}

	/**
	 * Wait for an element for 30 sec
	 *
	 * @param diver and xpathValue
	 */
	public void explicatWait(WebDriver driver, WebElement xpathValue) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(xpathValue));
	}

	/**
	 * Wait till element is invisible for 30 sec.
	 * 
	 * @param xpathValue
	 */
	public void explicatWaitTillInvisibility(WebElement xpathValue) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOf(xpathValue));

	}

}
