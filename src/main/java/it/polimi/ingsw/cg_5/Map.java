package it.polimi.ingsw.cg_5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


public class Map  {
	protected HashMap <String, Sector> map = new HashMap <String,Sector>();	
	
	public void Generator() { //metodo che crea mappa
	String nomeSettore;
	String tipoSettore="SAFE";
	FileReader fileSettori;
	try {
		fileSettori = new FileReader("./src/main/java/it/polimi/ingsw/cg_5/Mappa_Galilei.txt");
		Scanner in =new Scanner(fileSettori);
		while(in.hasNextLine()){
			while (in.hasNext() ){ // se la stringa non contiene "/"
				nomeSettore=in.next();
				if(nomeSettore.contains("/")==false){
					if(tipoSettore.equals("SAFE")){								
						SafeSector settore= new SafeSector(nomeSettore);
						this.addSector(nomeSettore , settore); 
					}
					if (tipoSettore.equals("DANGEROUS")) {
						DangerousSector settore=new DangerousSector(nomeSettore);
						this.addSector(nomeSettore , settore); 
					}
					if (tipoSettore.equals("ESCAPE_HATCH")) {
						EscapeSector settore=new EscapeSector(nomeSettore);
						this.addSector(nomeSettore , settore); 
						}
				
				}
				else{
					tipoSettore=in.next();
				}
			}
		}
	in.close();
	} catch (FileNotFoundException e) {
		System.err.println("Errore nel leggere file Mappa");
	}
	
	
	}
	
	public void AddBorders(){
		FileReader fileSettori;
		try {
			String nomeSettore;
			String nomeConfine;
			fileSettori = new FileReader("./src/main/java/it/polimi/ingsw/cg_5/Mappa_Galilei_Confini.txt");
			Scanner in =new Scanner(fileSettori);
			while(in.hasNextLine()){
				while (in.hasNext() ){
					nomeSettore=in.next();
					nomeConfine=in.next();
							while(nomeConfine.contains("/")==false){
								this.map.get(nomeSettore).addBorder(this.map.get(nomeConfine));
								nomeConfine=in.next();
								
							}
						
										
				}
				
			}
			in.close();
			 
		}catch (FileNotFoundException e) {
			System.err.println("Errore nel leggere file Mappa Confini");
		}
		
	}
	
	public void printMap(){
		Iterator<String> iterator = map.keySet().iterator();		  
		while (iterator.hasNext()) {
		   String key = iterator.next().toString();
		   String value = map.get(key).toString();		  
		   System.out.println(key + " " + value);
		}
	}

	public void  addSector(String name, Sector sector){
		map.put(name, sector);
	}
	
	public Sector takeSector(String name){
		return map.get(name);
	}
}
