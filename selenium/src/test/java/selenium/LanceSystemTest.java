package selenium;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LanceSystemTest {
	private WebDriver driver;
	private LeiloesPage leiloes;

	@Before
	public void inicializa() {

		System.setProperty("webdriver.chrome.driver", "/opt/selenium/chromedriver");
		driver = new ChromeDriver();
		driver.get(new URLDaAplicacao().getUrlBase() + "/apenas-teste/limpa");
		leiloes = new LeiloesPage(driver);
		
		
		
		new CriadorDeCenarios(driver).umUsuario("Paulo Henrique", "paulo@henrique.com")
		.umUsuario("José Alberto", "jose@alberto.com").umLeilao("Paulo Henrique", "Geladeira", 100, false);
	}

	@Test
	public void deveFazerUmLance() {

		DetalhesDoLeilaoPage lances = leiloes.detalhes(1);

		lances.lance("José Alberto", 150);

		assertTrue(lances.existeLance("José Alberto", 150));
	}

}
