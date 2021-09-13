
public class Turno{
	private int t;
	
	public Turno() {
		this.t = 1;
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
}
