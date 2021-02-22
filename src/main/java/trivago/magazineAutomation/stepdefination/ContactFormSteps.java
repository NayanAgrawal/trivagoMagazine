package trivago.magazineAutomation.stepdefination;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import trivago.magazineAutomation.PageObjects.ContactForm;
import trivago.magazineAutomation.commonUtil.ReadPropertiesFile;
import trivago.magazineAutomation.commonUtil.TestBase;

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
