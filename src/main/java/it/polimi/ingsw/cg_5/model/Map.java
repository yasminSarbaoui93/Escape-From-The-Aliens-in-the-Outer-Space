package it.polimi.ingsw.cg_5.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


public class Map  {
	protected HashMap <String, Sector> map = new HashMap <String,Sector>();	
	
	public void Generator() { 
	//metodo che crea la mappa leggendo dal file specificato nella chiamata(lo si implementerà più avanti,
	//per ora chiamiamo il file della mappa Galilei. 
	//Il file è strutturato per righe, ogni riga rappresenta un tipo di settore che andrà generato
	String nomeSettore;
	String tipoSettore="SAFE";
	FileReader fileSettori;
	try {
<<<<<<< HEAD
		fileSettori = new FileReader("./src/main/java/it/polimi/ingsw/cg_5/model/Mappa_Galilei.txt");
		Scanner in =new Scanner(fileSettori);
		while(in.hasNextLine()){ //leggiamo fino alla fine del file
			while (in.hasNext() ){ 
				nomeSettore=in.next();
				if(nomeSettore.contains("/")==false){ // se la stringa non contiene "/" chiamiamo il costruttore
													  //in base al tipo letto dal file
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
					//se entriamo nell'else vuol dire che abbiamo letto "/", cioè siamo arrivati a fine riga,
					//la prossima stringa che leggeremo ci indicherà il tipo dei settori elencati nella nuova riga
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
	//questo metodo legge da file i confini dei settori e li aggiunge nella lista dei settori confinanti di ogni confine
	// per fare questo leggerà per ogni riga il primo settore,che è il settore a cui aggiungere i confini, mentre
	// i settori fino alla fine della riga sono i settori da aggiungere come confini
		FileReader fileSettori;
		try {
			String nomeSettore;
			String nomeConfine;
			fileSettori = new FileReader("./src/main/java/it/polimi/ingsw/cg_5/model/Mappa_Galilei_Confini.txt");
=======
		fileSettori = new FileReader("./src/main/java/it/polimi/ingsw/cg_5/Mappa_Galilei.txt");
		Scanner in =new Scanner(fileSettori);
		while(in.hasNextLine()){ //leggiamo fino alla fine del file
			while (in.hasNext() ){ 
				nomeSettore=in.next();
				if(nomeSettore.contains("/")==false){ // se la stringa non contiene "/" chiamiamo il costruttore
													  //in base al tipo letto dal file
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
					//se entriamo nell'else vuol dire che abbiamo letto "/", cioè siamo arrivati a fine riga,
					//la prossima stringa che leggeremo ci indicherà il tipo dei settori elencati nella nuova riga
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
	//questo metodo legge da file i confini dei settori e li aggiunge nella lista dei settori confinanti di ogni confine
	// per fare questo leggerà per ogni riga il primo settore,che è il settore a cui aggiungere i confini, mentre
	// i settori fino alla fine della riga sono i settori da aggiungere come confini
		FileReader fileSettori;
		try {
			String nomeSettore;
			String nomeConfine;
			fileSettori = new FileReader("./src/main/java/it/polimi/ingsw/cg_5/Mappa_Galilei_Confini.txt");
>>>>>>> refs/heads/master
			Scanner in =new Scanner(fileSettori);
			while(in.hasNextLine()){				
					nomeSettore=in.next();
					nomeConfine=in.next();
							while(nomeConfine.contains("/")==false){
					//finchè non leggo"/" vuol dire che devo aggiungere la stringa che leggo come confine
					//se esco dal ciclo vuol dire che ho letto "/", sono arrivato a fine riga, quindi cambierà
					//il settore a cui aggiungere i confini
								this.map.get(nomeSettore).addBorder(this.map.get(nomeConfine));
								nomeConfine=in.next();
							}				
			}
			in.close();
			 
		}catch (FileNotFoundException e) {
			System.err.println("Errore nel leggere file Mappa Confini");
		}
		
	}
	
	public void printMap(){ 
		//questo metodo stampa tutti gli elementi della mappa, sarà utile per implementare la CLI
		Iterator<String> iterator = map.keySet().iterator();
		//keySet restituisce tutte le chiavi degli oggetti contenuti nella Hashmap
		while (iterator.hasNext()) {
		   String key = iterator.next().toString();
		   String value = map.get(key).toString();		  
		   System.out.println(" " + value);
		}
	}

	public void  addSector(String name, Sector sector){
		map.put(name, sector);
	}
	
	public Sector takeSector(String name){
		return map.get(name);
	}
}
