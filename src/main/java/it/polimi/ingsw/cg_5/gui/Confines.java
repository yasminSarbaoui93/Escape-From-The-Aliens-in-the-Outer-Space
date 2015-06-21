package it.polimi.ingsw.cg_5.gui;

import java.util.ArrayList;

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
	public double getXCoordinateFromLetter(char col){
		double ret=0;
		for(LineConfines column : columnlist){
			if(column.getLine().equals(col))
				ret=(column.getMinValue()+(column.getMaxValue()-column.getMinValue())/2)-32;
		}
		return ret;		
	}
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


