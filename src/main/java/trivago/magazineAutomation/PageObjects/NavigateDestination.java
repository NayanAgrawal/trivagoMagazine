package trivago.magazineAutomation.PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import trivago.magazineAutomation.stepdefination.NavigateDestinationSteps;

public class NavigateDestination extends NavigateDestinationSteps {
	WebDriver driver;

	public NavigateDestination(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "nav-icon")
	public WebElement navIcon;

	@FindBy(xpath = "//div[@class='menu-title'][contains(text(),'Destinations')]")
	public WebElement destinationSearch;

	@FindBy(xpath = "//div[@class='swiper-slide menu-destination-card swiper-slide-active']")
	public WebElement selectDestination;

	@FindBy(xpath = "//h1[@class='hero-main-title dest-2-main-title']")
	public WebElement verifyDestinationResult;

	@FindBy(xpath = "//div[@class='swiper-button-next menu-card-button-next icon-swiper-button-next']")
	public WebElement swiperButtonNext;

	/**
	 * Navigate to destination Page selection
	 *
	 */
	public void NavigateDestinationPage() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		navIcon.click();

		destinationSearch.click();
		log.info("Clicked in destination search + button");
	}

	/**
	 * verify that user is successfully navigated to destination
	 *
	 * @param destination
	 * @throws Exception
	 */
	public void verifyNavigateDestination(String destination) throws Exception {

		String selectDestination = "//div[@class='destination-menu'][contains(text(),'" + destination + "')]";

		if (destination.equals("Southeast")) {
			log.info("Destination selected is Southeast");
			swiperButtonNext.click();
			driver.findElement(By.xpath(selectDestination)).click();
			explicatWait(driver, verifyDestinationResult);
			Assert.assertEquals(destination, verifyDestinationResult.getText());

		} else if (destination.equals("Midwest") || destination.equals("Northeast") || destination.equals("Northwest")
				|| destination.equals("Southwest")) {
			log.info("Destination selected is " + destination);
			driver.findElement(By.xpath(selectDestination)).click();
			explicatWait(driver, verifyDestinationResult);
			Assert.assertEquals(destination, verifyDestinationResult.getText());

		}

		else {
			log.info("Destination selected is " + destination + " which is invalid!!");
		}

	}

}
