package it.polimi.ingsw.cg_5.gui;

/**Support class used to have an object that has a coordinate min and max. It's used an object because in the case of columns, it's used
 * a char variable while in case of lines it's used a String variable.
 * @author Andrea
 *
 */
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
