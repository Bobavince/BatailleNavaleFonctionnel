/**
 * @author Vincent F et Thomas B
 * Classe de gestion de la forme du plateau;
 * Note : le mot cl� static permet d'�viter d'instancier la classe pour y avoir acc�s (aucune sens d'instancier la classe Type)
 */

public class Type {
	
	//Classe qui cr�er un plateau carr� (tableau 2D avec toutes les cases vides � 0)
	public static int[][] carre(int x, int y){
		int[][] tab = null;
				
				// A comp�lter
		return tab;
	}
	
	//Classe qui g�re l'appel des autres m�thodes
	public static int[][] forme(int x, int y, int numeroDeType){
		 int[][] tab;
		
		switch (numeroDeType)
	    {
	      case 1:
	        tab = rond(x);
	        break;  
	      case 2:
	    	tab = triangle(x);
	    	  break;
	      case 3:
	    	tab = rectangle(x,y);
	    	  break;
	      default:
	    	tab = carre(x,y);    
	    	  break;
	    }
		
		return tab;
		
	}
	
	//Classe qui cr�er un plateau rond (tableau 2D avec les cases aux angles � -1)
	public static int[][] rond(int r){
		 int[][] tab = null;
		
		// A comp�lter
		return tab;
	}
	
	//Classe qui cr�er un plateau triangle (tableau 2D avec des cases en coin � -1)
	public static int[][] triangle(int cote){
		 int[][] tab = null ;
		
		// A comp�lter
		return tab;
	}
	
	//Classe qui cr�er un plateau rectangle (tableau 2D avec toutes les cases vides � 0)
	public static int[][] rectangle(int x, int y){
		 int[][] tab = null;
		
		// A comp�lter
		return tab;
	}

}