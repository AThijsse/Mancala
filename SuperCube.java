package nl.sogyo.mancala;

abstract public class SuperCube {
	private int StockOfRocks;
	private SuperCube neighbour;
	private Player owner;

	
	public Player getOwner() {
		return owner;
	}

	public int getStockOfRocks() {
		return StockOfRocks;
	}

	/**
	 * @param neighbour the neighbour to set
	 */
	public void setNeighbour(SuperCube neighbour) {
		this.neighbour = neighbour;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * @param stockOfRocks the stockOfRocks to set
	 */
	public void setStockOfRocks(int stockOfRocks) {
		StockOfRocks = stockOfRocks;
	}

	public SuperCube getNeighbour() {
		return neighbour;
	}

	public void EmptyStock() {
		StockOfRocks = 0;
	}

	public void StartCube(int cubeCount) {

		if (cubeCount > 1) {
			cubeCount = cubeCount - 1;
			neighbour.StartCube(cubeCount);

		} else if (CheckPlayability() == true) {

			int rollingstones = StockOfRocks;
			StockOfRocks = 0;
			neighbour.TakeARock(rollingstones);

		} else {

			neighbour.CheckPlayabilityBoard(0);

		}
	}

	public boolean CheckPlayability() {
		boolean HasTurn = getOwner().getTurn();

		if (StockOfRocks > 0 && HasTurn == true) {

			return true;

		} else {

			return false;
		}
	}

	public void CheckPlayabilityBoard(int count) {
		boolean HasTurn = getOwner().getTurn();
		count++;

		if (StockOfRocks > 0 && HasTurn == true) {

			getOwner().PickACube();

		} else if (count > 14) {

			neighbour.CheckPlayabilityBoard(count);

		} else {

			EndGame(0);
		}

	}

	public void EndGame(int rollingstones) {

		rollingstones = rollingstones + StockOfRocks;

		neighbour.EndGame(rollingstones);

	}

	public void AddARock(int rollingstones) {

		StockOfRocks = StockOfRocks + 1;
		if (rollingstones > 0) {
			neighbour.TakeARock(rollingstones);
		} else {
			EndTurn();
		}
	}

	public int CheckStock() {

		return StockOfRocks;
	}

	public void TakeARock(int rollingstones) {

		rollingstones = rollingstones - 1;
		AddARock(rollingstones);

	}

	public int CheckRocks(int rollingstones) {

		return rollingstones;
	}

	public String GetCubeType(int cubeCount) {

		if (cubeCount > 1) {
			cubeCount = cubeCount - 1;
			return neighbour.GetCubeType(cubeCount);
		} else {
			return Declare();
		}
	}

	abstract public String Declare();

	public SuperCube GetCube(int cubeCount) {

		if (cubeCount > 1) {
			cubeCount = cubeCount - 1;
			return neighbour.GetCube(cubeCount);
		} else {
			return this;
		}

	}

	public void EndTurn() {
		boolean turn = getOwner().getTurn();

		if (turn == true) {
			CheckHit();
		} else {
			getOwner().SwitchTurn();
		}
	}

	public void CheckHit() {
		int Stock = CheckStock();

		if (Stock == 1) {

			FindNeighbourToKalaha(1, this);
		} else {
			getOwner().SwitchTurn();
		}
	}

	public void FindNeighbourToKalaha(int cubeCount, SuperCube HittingCube) {
		cubeCount++;
		neighbour.FindNeighbourToKalaha(cubeCount, HittingCube);
	}

	public void FindNeighbourFromKalaha(int cubeCount, SuperCube HittingCube, SuperCube Kalaha, int neighbourCount) {
		neighbourCount++;

		if (neighbourCount == cubeCount) {
			HitCube(cubeCount, HittingCube, Kalaha);

		} else {
			neighbour.FindNeighbourFromKalaha(cubeCount, HittingCube, Kalaha, neighbourCount);
		}
	}

	public void HitCube(int cubeCount, SuperCube HittingCube, SuperCube Kalaha) {
		int rollingstones = StockOfRocks;
		StockOfRocks = 0;
		PassStonesToKalaha(rollingstones, HittingCube, Kalaha);
	}

	public void PassStonesToKalaha(int rollingstones, SuperCube HittingCube, SuperCube Kalaha) {
		Kalaha.KalahaReceiveStones(rollingstones, HittingCube);
	}

	public void KalahaReceiveStones(int rollingstones, SuperCube HittingCube) {

		if (rollingstones > 0) {

			StockOfRocks = StockOfRocks + rollingstones + 1;
			HittingCube.EmptyStock();
		}

		getOwner().SwitchTurn();

	}




}
