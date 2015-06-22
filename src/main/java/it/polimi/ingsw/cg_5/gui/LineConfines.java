package it.polimi.ingsw.cg_5.gui;

public class LineConfines {
	private final Object line;
	private final double minValue;
	private final double maxValue;
	
	
	public LineConfines(Object line, double minValue, double maxValue) {
		super();
		this.line = line;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	public Object getLine() {
		return line;
	}
	public double getMinValue() {
		return minValue;
	}
	public double getMaxValue() {
		return maxValue;
	}
	
	

}
