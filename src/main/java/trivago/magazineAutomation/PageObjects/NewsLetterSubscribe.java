package trivago.magazineAutomation.PageObjects;

import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import trivago.magazineAutomation.stepdefinition.NewsletterSteps;

/**
 * The NewsLetterSubscribe program implements subscription of newsletter and
 * verifies successful subscription.
 *
 * @author Nayan Agawal
 * @version 1.0
 * @since 20121-02-20
 */
public class NewsLetterSubscribe extends NewsletterSteps {
	WebDriver driver;

	public NewsLetterSubscribe(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "email")
	public WebElement emailTextBox;

	@FindBy(className = "newsletter-submit")
	public WebElement submitButton;

	@FindBy(xpath = "//div[@class='container-wide']/p[@class='submitted h1']")
	public WebElement verifySubmittedMessage;

	@FindBy(xpath = "//div[@class='alert-error et-email-error']")
	public WebElement verifyInvalidEmail;

	/**
	 * Enter emailID to subscribe newsletter and submit
	 *
	 * @param newsletter emailID
	 * @throws Exception
	 */
	public void newsLetterSubscribePage(String newsletterEmail) throws Exception {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("ddhhmmss");
		String strDate = formatter.format(date);
		System.out.println(strDate);

		newsletterEmail = strDate + newsletterEmail;

		emailTextBox.sendKeys(newsletterEmail);
		log.info("Entered email ID is - " + newsletterEmail);

		getScreenshot(driver, "enteredEmailIDNewsletter");

		submitButton.click();

		log.info("Clicked on submit button");

		Thread.sleep(2000);

		explicatWait(driver, verifySubmittedMessage);

	}

	/**
	 * Verify successful subscription to newsletter
	 *
	 * @throws Exception
	 */
	public void verifyNewsLetter() throws Exception {

		assertEquals("You are now checked-in!", verifySubmittedMessage.getText());
		log.info("User successfully subscribed to newsletter");
		getScreenshot(driver, "verifyNewsletterSubscription");

	}

	/*
	 * public void InvalidNewsLetterSubscribePage(String newsletterEmail) throws
	 * Exception {
	 * 
	 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 * 
	 * emailTextBox.sendKeys(newsletterEmail); submitButton.click();
	 * 
	 * assertEquals("Please enter a valid e-mail address",
	 * verifyInvalidEmail.getText());
	 * 
	 * }
	 */

}
