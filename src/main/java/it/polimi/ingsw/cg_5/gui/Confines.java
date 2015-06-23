package it.polimi.ingsw.cg_5.gui;

import java.util.ArrayList;

/** Classe che contiene tre liste che associano le cordinate min e max sulla schermata con la giusta/lettera numero
 * che identificano la riga/colonna esatta, la classe verrà utilizzata sia per riconoscere il settore dopo aver cliccato 
 * in un punto grazie alle coordinate,ma anche viceversa dato un settore per trovare le coordinate in cui stampare diverse
 * immagini
 * Si noti che le liste per le righe sono due, in quanto si avranno valori diversi in base alla colonna del settore
 * @author Andrea
 *
 */
/**
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


	/**Nel costruttore inizializziamo le tre liste tramite cicli for
	 * 
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
	
	/** metodo che data una colonna restituisce il punto centrale del settore, in modo da stampare le immagine centrate
	 * rispetto alla colonna
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
	/**metodo che data una riga restituisce il punto centrale del settore, in modo da stampare le immagine centrate
	 * rispetto alla riga,  la differenza è che per le righe abbiamo due possibili valori, in base a se la colonna del settore
	 * e pari oppure dispari
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


