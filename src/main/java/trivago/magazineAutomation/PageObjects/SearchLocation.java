package trivago.magazineAutomation.PageObjects;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import trivago.magazineAutomation.stepdefinition.SearchLocationSteps;

/**
* The SearchLocation program implements any search location
* and verifies location is searched successfully or not.
*
* @author  Nayan Agawal
* @version 1.0
* @since   20121-02-20
*/
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
	 * @throws IOException 
	 */
	public String searchLocationPage(String location) throws IOException {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		log.info("Location which is getting searched is  - " + location);

		SearchIcon.click();
		inputSearch.sendKeys(location);
		getScreenshot(driver, location+"Entered");
		inputSearch.sendKeys(Keys.RETURN);

		explicatWait(driver, SearchResultTitle);

		log.info(SearchResultTitle.getText() + " found");

		return SearchResultTitle.getText();

	}

	/**
	 * Verifies searched location
	 * @throws IOException 
	 *
	 */
	public void verifysearchLocation() throws IOException {

		List<WebElement> count = driver
				.findElements(By.xpath("//div[@class='article-card col col-xs-12 col-sm-6 col-md-6']"));
		int xpathCount = count.size() - 4;

		String actualSearchResult = xpathCount + " Search Results";

		log.info(actualSearchResult);

		Assert.assertEquals(actualSearchResult, SearchResultTitle.getText());

		getScreenshot(driver, "verifySearchLocationResut");
		
	}

}
