package it.polimi.ingsw.cg_5.gui;

/**classe di supporto utilizzata per avere un oggetto che abbia una coordinata minima e una massima,
 * si è fatta la scelta di utilizzare un oggetto di tipo Object in quanto nel caso delle colonne si tratta di una variabile 
 * di tipo char, nel caso delle righe di una variabile di tipo String
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
