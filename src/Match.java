
public class Match{
	

	private int t;
	private boolean e;
	
	public Match() {
		this.t = 1;
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
