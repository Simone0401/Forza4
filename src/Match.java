
public class Match{
	
	private Grid g;
	private int t;
	private boolean e;
	private Player p1;
	private Player p2;
	
	public Grid getG() {
		return g;
	}

	public void setG(Grid g) {
		this.g = g;
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public Match(Player p1, Player p2) {
		this.g = new Grid();
		this.t = 1;
		this.e = false;
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Match(int t, Grid g) {
		this.g = g;
		this.t = t;
		this.e = false;
	}
	
	public int getValue() {
		return this.t;
	}
	
	public void changep() {
		if (this.t == 1) {
			this.t = 2;
		}
		else {
			this.t = 1;
		}
	}
	public void ended() {
		this.e = true;
	}
	
	public boolean isended() {
		return this.e;
	}
	
	
}
