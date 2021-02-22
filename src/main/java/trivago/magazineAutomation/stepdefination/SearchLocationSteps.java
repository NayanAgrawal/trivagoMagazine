package trivago.magazineAutomation.stepdefination;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import trivago.magazineAutomation.PageObjects.SearchLocation;
import trivago.magazineAutomation.commonUtil.ReadPropertiesFile;
import trivago.magazineAutomation.commonUtil.TestBase;

public class SearchLocationSteps extends TestBase {

	SearchLocation searchLocation;

	@Given("^user is on trivago homepage for search location$")
	public void user_is_on_trivago_homepage() throws Throwable {
		init();
		searchLocation = new SearchLocation(driver);

	}

	@When("^search for specific location$")
	public void search_for_specific_location() throws Throwable {
		searchLocation.searchLocationPage(ReadPropertiesFile.getProperty("LocationSearch"));
	}

	@Then("^verify searched location$")
	public void verify_searched_location() throws Throwable {
		searchLocation.verifysearchLocation();
	}

	@Then("^Close the browser for search location")
	public void close_the_browser() throws Throwable {
		if (driver != null) {
			driver.quit();
		}
	}

}
