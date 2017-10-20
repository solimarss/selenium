package selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UsuariosSystemTest {

	private WebDriver driver;
	private UsuariosPage usuarios;

	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver", "/opt/selenium/chromedriver");
		driver = new ChromeDriver();
		 driver.get("http://localhost:8080/apenas-teste/limpa");
		this.usuarios = new UsuariosPage(driver);

	}

	@After
	public void finalizar() {
		driver.close();
	}

	@Test
	public void deveAdicionarUmUsuario() {

		usuarios.visita();
		usuarios.novo().cadastra("Herique Silva", "es@terra.com.br");

		assertTrue(usuarios.existeNaListagem("Herique Silva", "es@terra.com.br"));

	}

	@Test
	public void naoDeveAdicionarUmUsuarioSemNome() {

		usuarios.visita();
		NovoUsuarioPage form = usuarios.novo();

		form.cadastra("", "ronaldo2009@terra.com.br");

		assertTrue(form.validacaoDeNomeObrigatorio());

	}

	@Test
	public void naoDeveAdicionarUmUsuarioSemNomeOuEmail() {

		usuarios.visita();
		NovoUsuarioPage form = usuarios.novo();

		form.cadastra("", "");

		assertTrue(form.validacaoDeNomeEEmailObrigatorios());

	}

	@Test
	public void deveDeletarUmUsuario() {

		usuarios.visita();
		usuarios.novo().cadastra("Ronaldo Luiz de Albuquerque", "ronaldo2009@terra.com.br");
		assertTrue(usuarios.existeNaListagem("Ronaldo Luiz de Albuquerque", "ronaldo2009@terra.com.br"));

		usuarios.deletaUsuarioNaPosicao(1);

		assertFalse(usuarios.existeNaListagem("Ronaldo Luiz de Albuquerque", "ronaldo2009@terra.com.br"));
	}

	@Test
	public void deveAlterarUmUsuario() {
		usuarios.visita();
		usuarios.novo().cadastra("Ronaldo Luiz de Albuquerque", "ronaldo2009@terra.com.br");
		usuarios.altera(1).para("José da Silva", "jose@silva.com");

		assertFalse(usuarios.existeNaListagem("Ronaldo Luiz de Albuquerque", "ronaldo2009@terra.com.br"));
		assertTrue(usuarios.existeNaListagem("José da Silva", "jose@silva.com"));
	}

}
