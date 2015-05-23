/**
 * @author Vincent F et Thomas B
 * Classe de gestion de la forme du plateau;
 * Note : le mot clé static permet d'éviter d'instancier la classe pour y avoir accès (aucune sens d'instancier la classe Type)
 */

public class Type {
	
	//Classe qui créer un plateau carré (tableau 2D avec toutes les cases vides à 0)
	public static int[][] carre(int x, int y){
		int[][] tab = null;
				
				// A compélter
		return tab;
	}
	
	//Classe qui gère l'appel des autres méthodes
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
	
	//Classe qui créer un plateau rond (tableau 2D avec les cases aux angles à -1)
	public static int[][] rond(int r){
		 int[][] tab = null;
		
		// A compélter
		return tab;
	}
	
	//Classe qui créer un plateau triangle (tableau 2D avec des cases en coin à -1)
	public static int[][] triangle(int cote){
		 int[][] tab = null ;
		
		// A compélter
		return tab;
	}
	
	//Classe qui créer un plateau rectangle (tableau 2D avec toutes les cases vides à 0)
	public static int[][] rectangle(int x, int y){
		 int[][] tab = null;
		
		// A compélter
		return tab;
	}

}