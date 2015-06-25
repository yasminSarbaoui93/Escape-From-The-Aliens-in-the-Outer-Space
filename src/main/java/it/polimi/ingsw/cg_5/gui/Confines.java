package it.polimi.ingsw.cg_5.gui;

import java.util.ArrayList;

/**Class that cointains three lists that associate the coordinates min and max on the screan with the right combination letter-number that
 * identify the rigt line and column. The class is used both to recognize the sector after a botton gets pushed
 * and to find the coordinates where to print an image after a sector is given.
 * For the lines there are two lists since there are different values based on the column of the sector. 
 * @author Andrea
 *
 */

public class Confines {
	private ArrayList <LineConfines> columnlist = new ArrayList <LineConfines> () ;
	private ArrayList <LineConfines> oddrowlist = new ArrayList <LineConfines> () ;
	private ArrayList <LineConfines> evenrowlist = new ArrayList <LineConfines> () ;
	double minValue=15;
	double maxValue=43;
	char letter='A';
	
	
	public ArrayList<LineConfines> getColumnlist() {
		return columnlist;
	}


	public void setColumnlist(ArrayList<LineConfines> columnlist) {
		this.columnlist = columnlist;
	}


	public ArrayList<LineConfines> getOddrowlist() {
		return oddrowlist;
	}


	public void setOddrowlist(ArrayList<LineConfines> oddrowlist) {
		this.oddrowlist = oddrowlist;
	}


	public ArrayList<LineConfines> getEvenrowlist() {
		return evenrowlist;
	}


	public void setEvenrowlist(ArrayList<LineConfines> evenrowlist) {
		this.evenrowlist = evenrowlist;
	}


	/**
	 * In the confines constructor the three lists are inizialized.
	 */
	public Confines(){
		for(int i= 0; i<23 ; i++){
			LineConfines columnConfines = new LineConfines(letter,minValue,maxValue);
			columnlist.add(columnConfines);	
				minValue=minValue +33.75;
				maxValue=maxValue +33.75;
				letter=(char)((int)letter + 1);		
		}
		minValue=45.5;
		maxValue=83.5;
		String num="01";
		for(Integer i= 2; i<16 ; i++){
			LineConfines columnConfines = new LineConfines(num,minValue,maxValue);
			oddrowlist.add(columnConfines);	
				minValue=minValue +39;
				maxValue=maxValue +39;
			if(i>9){
				num=i.toString();
			}
			else num = "0"+i.toString();
		}
		minValue=65.5;
		maxValue=103.5;
		num="01";
		for(Integer i= 2; i<16 ; i++){
			LineConfines columnConfines = new LineConfines(num,minValue,maxValue);
			evenrowlist.add(columnConfines);	
				minValue=minValue +39;
				maxValue=maxValue +39;
				if(i>9){
					num=i.toString();
				}
				else num = "0"+i.toString();
		}
		

		}
	
	/**Given a column, it returns the central point of the sector, so that it's possible to print the images in the center of the sector.
	 * @param col
	 * @return
	 */
	public double getXCoordinateFromLetter(char col){
		double ret=0;
		for(LineConfines column : columnlist){
			if(column.getLine().equals(col))
				ret=(column.getMinValue()+(column.getMaxValue()-column.getMinValue())/2)-32;
		}
		return ret;		
	}
	/**Method that takes as input the letter of a line and return the central point of the sector, so that it's possible to print the
	 * images at the center of the line. For the lines there are two possibke values based on the value of the column.
	 * @param letter
	 * @return
	 */
	public double getYOddCoordinateFromLetter(String letter){
		double ret=0;
		for(LineConfines column : oddrowlist){
			if(column.getLine().equals(letter))
				ret=(column.getMinValue()+(column.getMaxValue()-column.getMinValue())/2)-57;
		}
		return ret;		
	}
	public double getYevenCoordinateFromLetter(String letter){
		double ret=0;
		for(LineConfines column : evenrowlist){
			if(column.getLine().equals(letter))
				ret=(column.getMinValue()+(column.getMaxValue()-column.getMinValue())/2)-57;
		}
		return ret;		
	}
	
	}


