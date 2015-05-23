/**
 * @author Vincent F et Thomas B
 * Classe de gestion du plateau des bateaux.
 */

public class Bateau {
	int numero;
	int longueur;
	boolean horizontal;
	boolean alive = false;
	int[][] lieuEtat = null; // Deux première colonne pour les coordonnées, la troisième pour l'état
	
	/** Méthode qui créé un bateau à partir de ses coordonnées de placement, et qui calculera sa longueur.
	 * @param xDebut : valeur sur les x de début du bateau (inclus)
	 * @param yDebut : valeur sur les y de début du bateau (inclus)
	 * @param xFin : valeur sur les x de fin du bateau (inclus)
	 * @param yFin : valeur sur les y de fin du bateau (inclus)
	 * @param numero : numero du bateau qui correspond à son identifiant.
	 */
	public Bateau(int xDebut, int yDebut, int xFin, int yFin, int numero){
		
		
		if(xDebut==xFin){ // si le bateau est horizontal
			lieuEtat = new int[(yFin-yDebut+1)][3]; // On créé un tableau de (yMax-Ymin)+1 en hauteur (soit pour la case 1 à 4, 3 cases + 1, soit bien 4 cases de long.
			horizontal = true;
			
			for(int i=0 ; i<(yFin-yDebut+1) ; i++){ // On parcourt chaque case et on ajoute ses coodonnées en X et en Y. le +1 est pour la même raison qu'au dessus.
				lieuEtat[i][0] = xDebut;
				lieuEtat[i][1] = yDebut+i;
			}
		} else if (yDebut==yFin) { // si le bateau est vertical
			lieuEtat = new int[(xFin-xDebut+1)][3]; // On créé un tableau de (xMax-xMin)+1 en hauteur (soit pour la case 1 à 4, 3 cases + 1, soit bien 4 cases de long.
			horizontal = false;
			
			for(int i=0 ; i<(xFin-xDebut+1) ; i++){ // On parcourt chaque case et on ajoute ses coodonnées en X et en Y. le +1 est pour la même raison qu'au dessus.
				lieuEtat[i][0] = xDebut+i; 
				lieuEtat[i][1] = yDebut;
			}
		}
		
		creerBateau(numero); // on lui attribue un numero
		rendreAlive();	//on le rend alive en mettant toute la colonne 2 à "1"
		
	}
	
	public void creerBateau(int numero){
		this.numero = numero;
	}
	
	public void rendreAlive(){
		for(int i =0; i<lieuEtat.length ; i++){
			lieuEtat[i][2] = 1;						//on passe toute la colonne "de Vie" à 1 comme vivant
		}
		alive = true;
	}
	
	public void rendreCouler(){
		for(int i =0; i<lieuEtat.length ; i++){
			lieuEtat[i][2] = 0;						//on passe toute la colonne "de Vie" à 0 comme mort
		}
		alive = false;
	}
	
	public boolean estPresentSurCetteCase(int x, int y){
		boolean jeSuisPresent = false; // On pose une variable à retourner
		
		for(int i=0; i< lieuEtat.length ; i++){ // On parcourt le tableau de coordonnées du bateau en question
			if(lieuEtat[i][0] == x && lieuEtat[i][1]==y){ // Si les coordonnées en argument correspondent à un binome du tableau ( si à la ligne i, la colonne 0 = x et la colonne 1 = y, alors on renvois true
				jeSuisPresent = true;
			}
		}
		
		return jeSuisPresent;
	}
	
	public boolean tir(Plateau plateauJoueur, int x, int y){
		
		
	}
	
	public int tir(Plateau plateauJoueur, int x, int y){
		
		
	}

}