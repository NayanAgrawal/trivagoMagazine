package trivago.magazineAutomation.stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import trivago.magazineAutomation.PageObjects.NewsLetterSubscribe;
import trivago.magazineAutomation.commonUtil.ReadPropertiesFile;
import trivago.magazineAutomation.commonUtil.TestBase;

/**
* The NewsletterSteps program is the step definition to subscribe Newsletter
* where emailID is taken from constant.properites file and can be changed
* as per requirement.
*
* @author  Nayan Agawal
* @version 1.0
* @since   20121-02-23
*/
public class NewsletterSteps extends TestBase {

	NewsLetterSubscribe newsLetterSubscribe;

	@Given("^user is on trivago homepage to subscribe newsletter$")
	public void user_is_on_trivago_homepage_to_subscribe_newsletter() throws Throwable {
		init();
		newsLetterSubscribe = new NewsLetterSubscribe(driver);
	}

	@When("^Enter user emailID")
	public void Enter_user_emailID() throws Throwable {
		newsLetterSubscribe.newsLetterSubscribePage(ReadPropertiesFile.getProperty("NewsLetterEmail"));
	}

	@Then("^verify successful subscription$")
	public void verify_successful_subscription() throws Throwable {
		newsLetterSubscribe.verifyNewsLetter();
	}

	@And("^Close the browser for subscribe newsletter")
	public void close_the_browser_for_subscribe_newsletter() throws Throwable {
		if (driver != null) {
			driver.quit();
		}
	}

}
