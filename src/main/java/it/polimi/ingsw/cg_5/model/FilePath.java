package it.polimi.ingsw.cg_5.model;

public enum FilePath {
		GALVANI("./src/main/java/it/polimi/ingsw/cg_5/model/Mappa_Galvani"), 
		GALVANI_CONFINI("./src/main/java/it/polimi/ingsw/cg_5/model/Mappa_Galvani_Confini") , 
		GALILEI("./src/main/java/it/polimi/ingsw/cg_5/model/Mappa_Galilei.txt"),
		GALILEI_CONFINI("./src/main/java/it/polimi/ingsw/cg_5/model/Mappa_Galilei_Confini.txt"),
		FERMI("./src/main/java/it/polimi/ingsw/cg_5/model/Mappa_Fermi"), 
		FERMI_CONFINI("./src/main/java/it/polimi/ingsw/cg_5/model/Mappa_Fermi_Confini") ;
		
		private final String filePath;
		
		private FilePath(String name){
			this.filePath=name;
		}

		public String getFilePath() {
			return filePath;
		}
		
		
	

}
