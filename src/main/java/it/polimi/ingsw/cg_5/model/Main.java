package it.polimi.ingsw.cg_5.model;

public class Main {

	public static void main(String[] args) {

		Map prova= new Map();
		prova.Generator();
		prova.printMap();
		prova.AddBorders();
		prova.map.get("A03").bordersPrint();
		
		
		/*System.out.println(prova.map.get("A02").toString());
		System.out.println(prova.map.get("A03").toString());
		prova.map.get("A02").bordersPrint();
		prova.map.get("A02").addBorder(prova.map.get("A03"));
		prova.map.get("A02").bordersPrint();
		*/

	}

}
