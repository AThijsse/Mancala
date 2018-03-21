package nl.sogyo.mancala;

public class Kalaha extends SuperCube {

	public Kalaha(SuperCube firstcube, int count, Player ownerCube) {
		count++;
		this.setStockOfRocks(0);
		this.setOwner(ownerCube);
		
		if (count > 7) {
			this.setOwner(ownerCube.getOpponent());
		}

		if (count == 7) {
			this.setNeighbour(new Cube(firstcube, count, ownerCube));
		} else {
			this.setNeighbour(firstcube);
			//System.out.println("Cube" + count);
		}

	}

	public String GetCubeType(int cubeCount) {
		// System.out.println("Kalaha is here");
		return super.GetCubeType(cubeCount);
	}

	public String Declare() {
		return "Kalaha";
	}

	public SuperCube GetCube(int cubeCount) {
		return super.GetCube(cubeCount);
	}

	public void StartCube(int cubeCount) {
	
		if (cubeCount > 1) {
			cubeCount = cubeCount - 1;
			getNeighbour().StartCube(cubeCount);
		} else {
			getOwner().PickACube();
		}
	}

	public void TakeARock(int rollingstones) {
		boolean turn = getOwner().getTurn();

		if (turn == false) {
			getNeighbour().TakeARock(rollingstones);
		} else {
			super.TakeARock(rollingstones);
		}
	}
	
	public void FindNeighbourToKalaha(int cubeCount, SuperCube HittingCube) {
		getNeighbour().FindNeighbourFromKalaha(cubeCount, HittingCube, this, 1);
	}
	
	public void EndTurn() {
		getOwner().PickACube();
	}
	
	public boolean CheckPlayability() {
		return false;
	}
	
	public void CheckPlayabilityBoard(int count) {
		count++;
		getNeighbour().CheckPlayabilityBoard(count);
	}
	
	public void EndGame(int rollingstones) {
		boolean HasTurn = getOwner().getTurn();
		
		if (HasTurn == true ) {
			this.setStockOfRocks(this.getStockOfRocks() + rollingstones);
			CheckWinner(rollingstones);
		} else {
			getNeighbour().EndGame(rollingstones);
		}
	}
	
	public void CheckWinner(int rollingstones) {
		int StockKalahaTurn = rollingstones;
		SuperCube KalahaTurn = GetCube(7);
		int StockKalahaNotTurn = KalahaTurn.CheckStock();

		if (StockKalahaNotTurn > StockKalahaTurn) {
			getOwner().getOpponent().Winner();
		} else {
			getOwner().Winner();
		}
			
	}
	
	
}
