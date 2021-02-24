package trivago.magazineAutomation.stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import trivago.magazineAutomation.PageObjects.ContactForm;
import trivago.magazineAutomation.commonUtil.ReadPropertiesFile;
import trivago.magazineAutomation.commonUtil.TestBase;

/**
* The ContactFormSteps program is the step definition to fill contact form 
* where form details is taken from constant.properites file and can be changed
* as per requirement.
*
* @author  Nayan Agawal
* @version 1.0
* @since   20121-02-23
*/
public class ContactFormSteps extends TestBase {

	ContactForm contactForm;

	@Given("^user is on trivago homepage for contact form$")
	public void user_is_on_trivago_homepage() throws Throwable {
		init();
		contactForm = new ContactForm(driver);

	}

	@When("^Fill contact form$")
	public void Fill_contact_form() throws Throwable {

		contactForm.fillContactFormPage(ReadPropertiesFile.getProperty("ContactMessage"),
				ReadPropertiesFile.getProperty("ContactFullName"), ReadPropertiesFile.getProperty("ContactEmail"));
	}

	@Then("^verify sent message$")
	public void verify_sent_message() throws Throwable {
		contactForm.verifyContactForm();
	}

	@And("^Close the browser for contact form$")
	public void close_the_browser() throws Throwable {
		if (driver != null) {
			driver.quit();
		}
	}

}
