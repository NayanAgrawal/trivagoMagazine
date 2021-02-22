package trivago.magazineAutomation.PageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import trivago.magazineAutomation.stepdefination.SearchLocationSteps;

public class SearchLocation extends SearchLocationSteps {
	WebDriver driver;

	public SearchLocation(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='search-icon']")
	public WebElement SearchIcon;

	@FindBy(xpath = "//input[@class='input search-input']")
	public WebElement inputSearch;

	@FindBy(xpath = "//h3[@class='section-title']")
	public WebElement SearchResultTitle;

	/**
	 * Open particular location.
	 *
	 * @param location
	 */
	public String searchLocationPage(String location) {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		log.info("Location which is getting searched is  - " + location);

		SearchIcon.click();
		inputSearch.sendKeys(location);
		inputSearch.sendKeys(Keys.RETURN);

		explicatWait(driver, SearchResultTitle);

		log.info(SearchResultTitle.getText() + " found");

		return SearchResultTitle.getText();

	}

	/**
	 * Verifies searched location
	 *
	 */
	public void verifysearchLocation() {

		List<WebElement> count = driver
				.findElements(By.xpath("//div[@class='article-card col col-xs-12 col-sm-6 col-md-6']"));
		int xpathCount = count.size() - 4;

		String actualSearchResult = xpathCount + " Search Results";

		log.info(actualSearchResult);

		Assert.assertEquals(actualSearchResult, SearchResultTitle.getText());

	}

}
