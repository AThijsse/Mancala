package nl.sogyo.mancala;

public class Player {

	private Player opponent;
	private boolean turn;
	private boolean winner;

	public Player() {

		opponent = new Player(this);
		// System.out.println("Player one");
		turn = true;
	}

	public Player(Player first) {

		this.opponent = first;
		// System.out.println("Player two");
		turn = false;
	}

	public void PickACube() {
		System.out.println("Pick a cube");
	}

	public Player getOpponent() {

		return opponent;
	}

	public boolean getTurn() {
		return turn;
	}

	public boolean SwitchTurn() {

		turn = !(opponent.turn = turn);
		turn = !turn;
		opponent.turn = !turn;
		
		if (turn == false) {
			turn = true;
			opponent.turn = false;
			return turn;

		} else {
			turn = false;
			opponent.turn = true;
			return turn;
		}

	}
	
	public void Winner() {
		
		this.winner = true;
		opponent.winner = false;
		
	}
	
	public boolean getWinner() {
		
		return winner;
		
	}

}
