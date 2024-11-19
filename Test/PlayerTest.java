import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.poli.edu.model.Player;

class PlayerTest {

	@Test
	//Traer vidas del jugador
	void GetNumberLivesTest() {
		Player player = new Player();
		player.numberLives = 1;
		assertEquals(1, player.getNumberLives());
	}
	
	@Test
	//Configurar vidas del jugador
	void setNumberLivesTest() {
		Player player = new Player();
		player.setNumberLives(8);
		assertEquals(8, player.getNumberLives());
	}
	
	@Test
	//Traer puntos del jugador
	void getNumberPointsTest() {
		Player player = new Player();
		player.numberPoints = 15;
		assertEquals(15, player.getNumberPoints());
	}
	
	@Test
	//Configurar puntos del jugador
	void setNumberPointsTest() {
		Player player = new Player();
		player.setNumberPoints(20);
		assertEquals(20, player.getNumberPoints());
	}
	
	@Test
	//Armar objeto para mostrar al jugador las vidas y puntos obtenidos
	void TextPlayerInfoTest() {
		Player player = new Player();
		player.setNumberPoints(20);
		var resp = player.toString();
		assertEquals(resp.length() > 0, player.toString().length() > 0);
	}
	
	@Test
	//Descontar vida
	void loseLiveTest() {
		Player player = new Player();
		player.setNumberLives(3);
		player.loseLive();
		assertEquals(2, player.getNumberLives());
	}
	
	@Test
	//Reiniciar vidas
	void resetLiveTest() {
		Player player = new Player();
		player.numberLives = 0;
		player.resetLive();
		assertEquals(3, player.getNumberLives());
	}

}
