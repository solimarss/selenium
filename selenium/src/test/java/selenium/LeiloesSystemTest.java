package selenium;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesSystemTest {
	 private WebDriver driver;
	    private LeiloesPage leiloes;

	    @Before
	    public void inicializa() {
	    	System.setProperty("webdriver.chrome.driver", "/opt/selenium/chromedriver");
			driver = new ChromeDriver();
	        leiloes = new LeiloesPage(driver);
	        driver.get("http://localhost:8080/apenas-teste/limpa");
	        
	        UsuariosPage usuarios = new UsuariosPage(driver);
	        usuarios.visita();
	        usuarios.novo().cadastra("Paulo Henrique", "paulo@henrique.com");
	    }
	    @Test
	    public void deveCadastrarUmLeilao() {

	        leiloes.visita();
	        NovoLeilaoPage novoLeilao = leiloes.novo();
	        novoLeilao.preenche("Geladeira", 123, "Paulo Henrique", true);

	        assertTrue(leiloes.existe("Geladeira", 123, "Paulo Henrique", true));

	    }
	    
	   
	    
}
