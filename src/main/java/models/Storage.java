package models;

public class Storage {
	// timeStamp returns the timestamp in nanoseconds. 64 bits integer
	private long timeStamp;
	// The datapoint array of 64 bits floats
	private double[] values;
	// size returns the number of data points the partition holds.
	private int size = 0;
	// active means its writable
	private boolean active;
	// expired means it should get removed
	private boolean expired;
	
	public Storage(long startTimeStamp, double[] startValues, boolean startStatus) {
		timeStamp = startTimeStamp;
		values = startValues;
		active = startStatus;
		size = startValues.length;
		expired = false;
	}
	
	public long getTimeStamp() {return timeStamp;}
	public void setTimeStamp(long newTimeStamp) {timeStamp = newTimeStamp;}
	public double[] getValues() {return values;}
	public void appendValues(double[] newValues) {
		double[] newArray = new double[values.length + newValues.length];
		int i;
		for(i = 0; i < values.length; i++) {
			newArray[i] = values[i];
        }
        for(i = 0; i < newValues.length; i++) {
        	newArray[values.length + i] = newValues[i];
        }
        values = newArray;
	}
	public int getSize() {return size;}
	public void setSize(int newSize) {size = newSize;}
	public boolean isActive() {return active;}
	public void setActive(boolean bool) {active = bool;}
	public boolean isExpired() {return expired;}
	public void setExpired(boolean bool) {expired = bool;}
}
