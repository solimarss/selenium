package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

public class TesteAutomatizado {
	
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.gecko.driver", "/opt/selenium/geckodriver");

		ProfilesIni profile = new ProfilesIni();

		FirefoxProfile SeleniumProfile = profile.getProfile("SeleniumProfile");

		WebDriver driver = new FirefoxDriver(SeleniumProfile);

		// WebDriver driver = new FirefoxDriver();

		// System.setProperty("webdriver.chrome.driver","/opt/selenium/chromedriver");
		// WebDriver driver = new ChromeDriver();

		// acessa o site do google
		driver.get("https://www.google.com.br/");

		// digita no campo com nome "q" do google
		WebElement campoDeTexto = driver.findElement(By.name("q"));
		campoDeTexto.sendKeys("Caelum");

		// submete o form
		campoDeTexto.submit();

	}

}
