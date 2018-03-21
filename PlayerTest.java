package nl.sogyo.mancala;


import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {
		
	@Test
	public void TestTwoPlayer() {
		Player player = new Player();
		Assert.assertNotSame(player, player.getOpponent());
	}
	
	@Test
	public void TestWhoHasTurn() {
		Player player = new Player();
		Assert.assertTrue(player.getTurn());
	}
	
	@Test
	public void TestSwitchTurn() {
		Player player = new Player();
		player.SwitchTurn();
		Assert.assertFalse(player.getTurn());
	}
	
	@Test 
	public void TestTurnNotSame() {
		Player player = new Player();
		player.SwitchTurn();
		Assert.assertNotSame(player.getTurn(), player.getOpponent().getTurn());
	}
}

