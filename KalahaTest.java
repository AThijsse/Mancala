package nl.sogyo.mancala;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;

public class KalahaTest {
	
	@Test
	public void TestKalahaExists() {
		Kalaha kalaha = new Kalaha(new Cube(), 1, new Player ());
		assertNotNull(kalaha);
	}

	@Test
	public void TestKalahaHasNeighbour() {
		Kalaha kalaha = new Kalaha(new Cube(), 1, new Player());
		assertNotNull(kalaha.getNeighbour());
	}
	
	@Test
	public void TestKalahaHasOwner() {
		Kalaha kalaha = new Kalaha(new Cube(), 1, new Player());
		assertNotNull(kalaha.getOwner());
	}
	
	@Test
	public void TestIfOwnerHasTurn() {
		Kalaha kalaha = new Kalaha(new Cube(), 1, new Player());
		Assert.assertTrue(kalaha.getOwner().getTurn());
	}
	
	
}
