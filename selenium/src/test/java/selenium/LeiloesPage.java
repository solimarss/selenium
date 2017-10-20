package selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeiloesPage {
	private WebDriver driver;

	public LeiloesPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get(new URLDaAplicacao().getUrlBase() + "/leiloes");
	}

	public NovoLeilaoPage novo() {
		// clica no link de novo leilao
		driver.findElement(By.linkText("Novo Leilão")).click();
		// retorna a classe que representa a nova pagina
		return new NovoLeilaoPage(driver);
	}

	public boolean existe(String produto, double valor, String usuario, boolean usado) {

		return driver.getPageSource().contains(produto) && driver.getPageSource().contains(String.valueOf(valor))
				&& driver.getPageSource().contains(usado ? "Sim" : "Não");

	}

	public boolean existeLance(String usuario, double valor) {
		Boolean temUsuario = new WebDriverWait(driver, 10)
				.until(ExpectedConditions.textToBePresentInElement(By.id("lancesDados"), usuario));

		if (temUsuario)
			return driver.getPageSource().contains(String.valueOf(valor));
		return false;
	}

	public DetalhesDoLeilaoPage detalhes(int posicao) {
		try {
			List<WebElement> elementos = driver.findElements(By.linkText("exibir"));
			elementos.get(posicao - 1).click();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new DetalhesDoLeilaoPage(driver);
	}
}