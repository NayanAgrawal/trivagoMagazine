package trivago.magazineAutomation.stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import trivago.magazineAutomation.PageObjects.NavigateDestination;
import trivago.magazineAutomation.commonUtil.ReadPropertiesFile;
import trivago.magazineAutomation.commonUtil.TestBase;

/**
* The NavigateDestinationSteps program is the step definition of search destination 
* where destination value is taken from constant.properites file and can be changed
* as per requirement.
*
* @author  Nayan Agawal
* @version 1.0
* @since   20121-02-23
*/
public class NavigateDestinationSteps extends TestBase {

	NavigateDestination navigateDestination;

	@Given("^user is on trivago homepage to access any destination$")
	public void user_is_on_trivago_homepage() throws Throwable {
		init();
		navigateDestination = new NavigateDestination(driver);

	}

	@When("^search for specific destination$")
	public void search_for_specific_location() throws Throwable {
		navigateDestination.NavigateDestinationPage();
	}

	@Then("^verify searched destination$")
	public void verify_searched_location() throws Throwable {
		navigateDestination.verifyNavigateDestination(ReadPropertiesFile.getProperty("DestinationSearch"));
	}

	@And("^Close the browser for Navigate to destination")
	public void close_the_browser() throws Throwable {
		if (driver != null) {
			driver.quit();
		}
	}

}
