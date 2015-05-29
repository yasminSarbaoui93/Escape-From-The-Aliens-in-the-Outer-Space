package it.polimi.ingsw.cg_5.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import it.polimi.ingsw.cg_5.model.Sector;;

public class Map  {
	protected HashMap <String, Sector> map = new HashMap <String,Sector>();	
	private String choosenMap;
	
	public Map(String choosenMap) { 
	//metodo che crea la mappa leggendo dal file specificato nella chiamata(lo si implementerà più avanti,
	//per ora chiamiamo il file della mappa Galilei. 
	//Il file è strutturato per righe, ogni riga rappresenta un tipo di settore che andrà generato
	this.choosenMap = choosenMap;
	String sectorName;
	String sectorType="SAFE";
	FileReader sectorsFile;
	String mapFile=FilePath.GALILEI.getFilePath();
	String mapBordersFile= FilePath.GALILEI_CONFINI.getFilePath();
	
	if(choosenMap=="GALVANI"){
		mapFile=FilePath.GALVANI.getFilePath();
		mapBordersFile=FilePath.GALVANI_CONFINI.getFilePath();
	}
	if(choosenMap=="FERMI"){
		mapFile=FilePath.FERMI.getFilePath();
		mapBordersFile=FilePath.FERMI_CONFINI.getFilePath();
	}
	
	
	try {

		sectorsFile = new FileReader(mapFile);
		Scanner in =new Scanner(sectorsFile);
		while(in.hasNextLine()){ //leggiamo fino alla fine del file
			while (in.hasNext() ){ 
				sectorName=in.next();
				if(sectorName.contains("/")==false){ // se la stringa non contiene "/" chiamiamo il costruttore
													  //in base al tipo letto dal file
					if(sectorType.equals("SAFE")){								
						SafeSector settore= new SafeSector(sectorName);
						this.addSector(sectorName , settore); 
					}
					if (sectorType.equals("DANGEROUS")) {
						DangerousSector settore=new DangerousSector(sectorName);
						this.addSector(sectorName , settore); 
					}
					if (sectorType.equals("ESCAPE_HATCH")) {
						EscapeSector settore=new EscapeSector(sectorName);
						this.addSector(sectorName , settore); 
						}
					if (sectorType.equals("ALIEN_SECTOR")) {
						AlienStart settore=new AlienStart(sectorName);
						this.addSector(sectorName , settore); 
						}
					if (sectorType.equals("HUMAN_SECTOR")) {
						HumanStart settore=new HumanStart(sectorName);
						this.addSector(sectorName , settore); 
						}
				
				}
				else{
					//se entriamo nell'else vuol dire che abbiamo letto "/", cioè siamo arrivati a fine riga,
					//la prossima stringa che leggeremo ci indicherà il tipo dei settori elencati nella nuova riga
					sectorType=in.next();
				}
			}
		}
	in.close();
	} catch (FileNotFoundException e) {
		System.err.println("Errore nel leggere file Mappa");
	}
	this.AddBorders(mapBordersFile);
	
	}
	
	

	/**
	 * Method that adds all the confines of all the sectors to create the map. It reads the confines from a text file
	 */
	public void AddBorders(String mapBordersFile){
	//questo metodo legge da file i confini dei settori e li aggiunge nella lista dei settori confinanti di ogni confine
	// per fare questo leggerà per ogni riga il primo settore,che è il settore a cui aggiungere i confini, mentre
	// i settori fino alla fine della riga sono i settori da aggiungere come confini
		FileReader sectorsFile;
		try {
			String sectorName;
			String nomeConfine;
			sectorsFile = new FileReader(mapBordersFile);
			Scanner in =new Scanner(sectorsFile);
			while(in.hasNextLine()){				
				sectorName=in.next();
				//System.out.println(sectorName + "a");
					nomeConfine=in.next();
							while(nomeConfine.contains("/")==false){
					//finchè non leggo"/" vuol dire che devo aggiungere la stringa che leggo come confine
					//se esco dal ciclo vuol dire che ho letto "/", sono arrivato a fine riga, quindi cambierà
					//il settore a cui aggiungere i confini
								
								///////NON CANCELLARE SERVONO PER CONTROLLARE SE NEI CONFINI VIENE AGGIUNTO null PERCHE'
								//LEGGE DA FILE UN SETTORE CHE NON ESISTE
								//String teststring=this.map.get(nomeConfine).getSectorName();
							    //System.out.println(teststring);
								this.map.get(sectorName).addBorder(this.map.get(nomeConfine));
								nomeConfine=in.next();
								
								
								
							}				
			}
			in.close();
			 
		}catch (FileNotFoundException e) {
			System.err.println("Error in reading file Mappa Confini");
		}
		
	}
	
	
	/**
	 * Prints all the sectors in the map.
	 */
	public void printMap(String name){ 
		
		String c = "";
		c += "Printing map "+name+": [";
		//questo metodo stampa tutti gli elementi della mappa, sarà utile per implementare la CLI
		Iterator<String> iterator = map.keySet().iterator();
		//keySet restituisce tutte le chiavi degli oggetti contenuti nella Hashmap
		while (iterator.hasNext()) {
		   String key = iterator.next().toString();
		   String value = map.get(key).toString();		  
		  // System.out.println(" " + value  );
		   c+= " "+ value;
		 
		}
		 c+="]";
		  System.out.println(c);
	}

	public void  addSector(String name, Sector sector){
		map.put(name, sector);
	}
	
	public Sector takeSector(String name){
		return map.get(name);
	}
	
	public String getMapName(){
		return choosenMap;
	}


	@Override
	public String toString() {
		return "Map [map=" + map + "]";
	}
}
