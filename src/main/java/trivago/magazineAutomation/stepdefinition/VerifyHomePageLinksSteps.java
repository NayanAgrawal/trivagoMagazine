package trivago.magazineAutomation.stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import trivago.magazineAutomation.PageObjects.VerifyHomepageLinks;
import trivago.magazineAutomation.commonUtil.TestBase;

/**
* The VerifyHomePageLinksSteps program is the step definition to verify homepage links
*
* @author  Nayan Agawal
* @version 1.0
* @since   20121-02-23
*/
public class VerifyHomePageLinksSteps extends TestBase {

	VerifyHomepageLinks verifyHomePageLinks;

	@Given("^user is on trivago homepage to verify homepage links$")
	public void user_is_on_trivago_homepage() throws Throwable {
		init();
		verifyHomePageLinks = new VerifyHomepageLinks(driver);
	}

	@When("^Verify all the links in homepage$")
	public void Fill_contact_form() throws Throwable {

		verifyHomePageLinks.HomePageLinkTab();
	}

	@And("^Close the browser for homepage$")
	public void close_the_browser() throws Throwable {
		if (driver != null) {
			driver.quit();
		}
	}

}
