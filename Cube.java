package nl.sogyo.mancala;

public class Cube extends SuperCube {

	public Cube() {

		this.setStockOfRocks(4);
		setOwner(new Player()); 
		this.setNeighbour(new Cube(this, 1, getOwner()));
		//System.out.println("Cube1");

	}

	public Cube(SuperCube firstcube, int count, Player ownerCube) {
		count++;
		this.setOwner(ownerCube);
		this.setStockOfRocks(4);
		
		if (count > 7) {
			this.setOwner(ownerCube.getOpponent());
		}

		if (count == 6 || count == 13) {
			this.setNeighbour(new Kalaha(firstcube, count, ownerCube));
			// System.out.println("Kalaha" + count);
		}
		else {
			this.setNeighbour( new Cube(firstcube, count, ownerCube));
			// System.out.println("Cube" + count);
		}

	}

	public String Declare() {
		return "Cube";
	}


}
