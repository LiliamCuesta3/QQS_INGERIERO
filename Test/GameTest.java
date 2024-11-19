import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import co.poli.edu.model.Game;

class GameTest {

	@Test
	// Validar que traiga el nivel solicitado
	void GetNumberLevelTest() {
		Game game = new Game();
		game.numberLevel = 2;
		assertEquals(2, game.getNumberLevel());
	}
	
	@Test
	// Configurar nivel actual
	void SetNumberLevelTest() {
		Game game = new Game();
		game.setNumberLevel(4);
		assertEquals(4, game.getNumberLevel());
	}
	
	@Test
	// Validar que traiga numero de ayudas configuradas
	void getNumberTohelpsTest() {
		Game game = new Game();
		game.numberTohelps = 5;
		assertEquals(5, game.getNumberTohelps());
	}
	
	@Test
	// Configurar numero de ayudas
	void setNumberTohelpsTest() {
		Game game = new Game();
		game.setNumberTohelps(8);
		assertEquals(8, game.getNumberTohelps());
	}
	
	@Test
	// Validar el uso de ayuda
	void isUseHelpTest() {
		Game game = new Game();
		game.useHelp = false;
		assertEquals(false, game.isUseHelp());
	}
	
	@Test
	// Solicitar ayuda
	void setUseHelpTest() {
		Game game = new Game();
		game.useHelp = true;
		assertEquals(true, game.isUseHelp());
	}

}
