package trivago.magazineAutomation.PageObjects;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import trivago.magazineAutomation.stepdefination.ContactFormSteps;

public class ContactForm extends ContactFormSteps {
	WebDriver driver;

	public ContactForm(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(text(),'Contact')]")
	public WebElement contactButton;

	@FindBy(xpath = "//div[@class='col col-xs-12 col-md-12']/h2")
	public WebElement verifyContactForm;

	@FindBy(xpath = "//textarea[@class='contact-msg']")
	public WebElement contactMsgTextArea;

	@FindBy(xpath = "//body/div[@id='app']/div[@id='__nuxt']/div[@id='__layout']/div[@id='app']/div[3]/div[1]/div[1]/div[1]/div[3]/div[1]/input[1]")
	public WebElement contactFullNameTextArea;

	@FindBy(id = "contact-email")
	public WebElement contactEmailTextArea;

	@FindBy(id = "confirm")
	public WebElement confirmCheckBox;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	public WebElement submitButton;

	@FindBy(className = "feedback")
	public WebElement verifyMessageSent;

	@FindBy(how = How.LINK_TEXT, using = "Selenium Java TestNG Tutorials - Bakkappa N")
	public WebElement ChannelNameLink;

	/**
	 * Fill contact form
	 *
	 * @param message, fullname, email
	 * @throws InterruptedException
	 */
	public void fillContactFormPage(String message, String fullName, String email) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		log.info("Click on contacat button to fill form");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", contactButton);

		// Switching to current tab
		getWindowHandler();

		// Assertion
		Assert.assertEquals("Please give us your feedback and tell us what you want to read about!",
				verifyContactForm.getText());

		contactMsgTextArea.sendKeys(message);
		contactFullNameTextArea.sendKeys(fullName);
		contactEmailTextArea.sendKeys(email);

		confirmCheckBox.click();

		submitButton.click();

		log.info("Submitting contact form");

		explicatWait(driver, verifyMessageSent);

		Assert.assertEquals("Message Sent Successfully!", verifyMessageSent.getText());

	}

	/**
	 * Verify contact form submitted successfully
	 *
	 * @throws Exception
	 */
	public void verifyContactForm() throws Exception {

		explicatWait(driver, verifyMessageSent);

		Assert.assertEquals("Message Sent Successfully!", verifyMessageSent.getText());
		log.info("Form filled successfully and message Sent ");

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

}
