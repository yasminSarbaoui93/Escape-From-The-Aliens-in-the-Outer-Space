package it.polimi.ingsw.cg_5.model;

<<<<<<< HEAD
import java.util.HashSet;

public class Main {

	public static void main(String[] args) {

		Map prova= new Map();
		prova.Generator();
		prova.printMap();
		prova.AddBorders();
		prova.map.get("A03").bordersPrint();
		HashSet <Sector> provalista = new HashSet <Sector> ();
		provalista = prova.takeSector("A02").getReachableSectors(1);
		System.out.println(provalista);
		provalista = prova.takeSector("A02").getReachableSectors(2);
		System.out.println(provalista);
		provalista = prova.takeSector("A02").getReachableSectors(3);
		System.out.println(provalista);
	
=======
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
>>>>>>> refs/heads/master

	}

}
