package trivago.magazineAutomation.PageObjects;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import trivago.magazineAutomation.stepdefination.VerifyHomePageLinksSteps;

public class VerifyHomepageLinks extends VerifyHomePageLinksSteps {
	WebDriver driver;

	public VerifyHomepageLinks(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	String homePage = "https://magazine.trivago.com/";
	String url = "";
	HttpURLConnection huc = null;
	int respCode = 200;

	/**
	 * Verifies all the links present in homepage
	 *
	 */
	public void HomePageLinkTab() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		List<WebElement> links = driver.findElements(By.tagName("a"));

		Iterator<WebElement> it = links.iterator();

		while (it.hasNext()) {

			url = it.next().getAttribute("href");

			log.info(url);

			if (url == null || url.isEmpty()) {
				log.info("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			if (!url.startsWith(homePage)) {
				log.info("URL belongs to another domain, skipping it.");
				continue;
			}

			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();

				if (respCode >= 400) {
					log.info(url + " is a broken link");
				} else {
					log.info(url + " is a valid link");
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}
