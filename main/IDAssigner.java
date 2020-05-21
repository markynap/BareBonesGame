package main;
/** Assigns a new integer ID to whoever calls upon it */
public class IDAssigner {
	
	private int baseID;
	
	public IDAssigner(int baseID) {
		this.baseID = baseID;
	}
	/** Get the next ID, one greater than the previous */
	public int next() {
		return baseID++;
	}
	/** The ID we are currently on, calling next() will increment this value by 1 */
	public int getBaseID() {
		return baseID;
	}
}
