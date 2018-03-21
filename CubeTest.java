package nl.sogyo.mancala;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;

public class CubeTest {

	@Test
	public void TestNeighbourCube() {
		Cube cube = new Cube();
		assertNotNull(cube.getNeighbour());
	}

	@Test
	public void TestNeighbourIsNotCube() {
		Cube cube = new Cube();
		Assert.assertNotSame(cube, cube.getNeighbour());
	}
	
	@Test
	public void TestIfCubeHasOwner() {
		Cube cube = new Cube();
		assertNotNull(cube.getOwner());
	}
	
	@Test
	public void TestIfOwnerHasOpponent() {
		Cube cube = new Cube();
		assertNotNull(cube.getOwner().getOpponent());
	}
	
	@Test
	public void TestIfOwnerAndOpponentAreNotSame() {
		Cube cube = new Cube();
		Player Owner1 = cube.getOwner();
		Player Owner2 = cube.getOwner().getOpponent();
		Assert.assertNotSame(Owner1, Owner2);
		}

	@Test
	public void TestCheckStock() {
		Cube cube = new Cube();
		Assert.assertEquals(cube.CheckStock(), 4);
	}

	@Test
	public void TestIfCubeEmptiesAtStart() {
		Cube cube = new Cube();
		cube.StartCube(2);
		SuperCube sc = cube.GetCube(2);
		Assert.assertEquals( 0, sc.CheckStock());
	}
	
	@Test
	public void Test14Cubes() {
		Cube cube = new Cube();
		int cubeCount = 14;
		Assert.assertEquals("Kalaha", cube.GetCubeType(cubeCount));
	}
		
	@Test
	public void Test1IsNeighbourOf14() {
		Cube cube = new Cube();
		Assert.assertSame(cube.GetCube(1), cube.GetCube(15));
		}
	
	@Test
	public void TestNotSameOwner() {
		Cube cube = new Cube();
		SuperCube one = cube.GetCube(1);
		SuperCube eight = cube.GetCube(8);
		Assert.assertNotSame(one.getOwner(), eight.getOwner());
	}
	
	@Test
	public void TestCheckTurnOwner() {
		Cube cube = new Cube();
		SuperCube kalaha2 = cube.GetCube(14);
		Assert.assertFalse(kalaha2.getOwner().getTurn());
	}
	
	@Test
	public void TestCheckTurnOwnersNotSame() {
		Cube cube = new Cube();
		SuperCube one = cube.GetCube(1);
		SuperCube kalaha2 = cube.GetCube(14);
		Assert.assertNotSame(one.getOwner().getTurn(), kalaha2.getOwner().getTurn());
	}
	
	@Test
	public void TestIfKalahaIsSkippedWhenNotTurn() {
		Cube cube = new Cube();
		cube.StartCube(12);
		SuperCube sc = cube.GetCube(14);
		Assert.assertEquals( 0, sc.CheckStock());
	}
	
	@Test
	public void TestIfHittingCubeIsEmptied() {
		Cube cube = new Cube();
		SuperCube sc = cube.GetCube(5);
		sc.EmptyStock();
		cube.StartCube(1);
		Assert.assertEquals(0, sc.CheckStock());
	}
	
	@Test
	public void TestIfHittingCubeIsNotEmptyWhenNeighbourEmpty() {
		Cube cube = new Cube();
		SuperCube sc = cube.GetCube(5);
		sc.EmptyStock();
		SuperCube scNeighbour = cube.GetCube(9);
		scNeighbour.EmptyStock();
		cube.StartCube(1);
		Assert.assertEquals(1, sc.CheckStock());
	}
	
	@Test
	public void TestRightCubeWasHit() {
		Cube cube = new Cube();
		SuperCube sc = cube.GetCube(5);
		SuperCube scNeighbour = cube.GetCube(9);
		sc.EmptyStock();
		cube.StartCube(1);
		Assert.assertEquals(0, scNeighbour.CheckStock());
	}
	
	@Test
	public void TestIfKalahaGetsStonesAfterHit() {
		Cube cube = new Cube();
		SuperCube sc = cube.GetCube(5);
		sc.EmptyStock();
		cube.StartCube(1);
		SuperCube kalaha = cube.GetCube(7);
		Assert.assertEquals(5, kalaha.CheckStock());
	}
	
	@Test
	public void TestIfNoHitWhenNotTurnOwner() {
		Cube cube = new Cube();
		SuperCube sc = cube.GetCube(9);
		sc.EmptyStock();
		cube.StartCube(5);
		Assert.assertEquals(1, sc.CheckStock());
	}
	
	@Test
	public void TestIfNoHitWhenCubeWasNotEmptyBefore() {
		Cube cube = new Cube();
		SuperCube sc = cube.GetCube(5);
		cube.StartCube(1);
		Assert.assertNotEquals(0, sc.CheckStock());
	}
	
	@Test 
	public void TestIfSwitchTurnWhenNoHit() {
		Cube cube = new Cube();
		SuperCube sc = cube.GetCube(5);
		cube.StartCube(1);
		Assert.assertFalse(sc.getOwner().getTurn());
	}
	
	@Test 
	public void TestIfOpponentGetsTurnWhenNoHit() {
		Cube cube = new Cube();
		SuperCube sc = cube.GetCube(9);
		cube.StartCube(1);
		Assert.assertTrue(sc.getOwner().getTurn());
	}
	
	@Test
	public void TestIfEmptyCubeCannotBePlayed() {
		Cube cube = new Cube();
		SuperCube sc = cube.GetCube(5);
		sc.EmptyStock();
		cube.StartCube(5);
		SuperCube scNext = cube.GetCube(6);
		Assert.assertEquals(4, scNext.CheckStock());
	}
	
	@Test
	public void TestIfCubeOfOtherPlayerCannotBePlayed() {
		Cube cube = new Cube();
		cube.StartCube(8);
		SuperCube scNext = cube.GetCube(9);
		Assert.assertEquals(4, scNext.CheckStock());
	}
	
	@Test 
	public void TestIfPlayerCanNotPlayKalaha() {
		Cube cube = new Cube();
		cube.StartCube(3);
		cube.StartCube(7);
		SuperCube Kalaha = cube.GetCube(7);
		Assert.assertEquals(Kalaha.CheckStock(), 1);
	}
	
	@Test
	public void TestIfWhenCubesOnRowAreEmptyThereIsAWinner() {
		Cube cube = new Cube();
		SuperCube sc = cube.GetCube(1);
		SuperCube sc2 = cube.GetCube(2);
		SuperCube sc3 = cube.GetCube(3);
		SuperCube sc4 = cube.GetCube(4);
		SuperCube sc5 = cube.GetCube(5);
		SuperCube sc6 = cube.GetCube(6);
		sc.EmptyStock();
		sc2.EmptyStock();
		sc3.EmptyStock();
		sc4.EmptyStock();
		sc5.EmptyStock();
		sc6.EmptyStock();
		cube.StartCube(1);
		Assert.assertTrue(sc.getOwner().getOpponent().getWinner());
	}
	
}

	

