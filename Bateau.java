/**
 * @author Vincent F et Thomas B
 * Classe de gestion du plateau des bateaux.
 */

public class Bateau {
	int numero;
	int longueur;
	boolean horizontal;
	boolean alive = false;
	int[][] lieuEtat = null; // Deux premi�re colonne pour les coordonn�es, la troisi�me pour l'�tat

	/** M�thode qui cr�� un bateau � partir de ses coordonn�es de placement, et qui met � jour sa longueur.
	 * @param xDebut : valeur sur les x de d�but du bateau (inclus)
	 * @param yDebut : valeur sur les y de d�but du bateau (inclus)
	 * @param xFin : valeur sur les x de fin du bateau (inclus)
	 * @param yFin : valeur sur les y de fin du bateau (inclus)
	 * @param numero : numero du bateau qui correspond � son identifiant.
	 */
	public Bateau(int xDebut, int yDebut, int xFin, int yFin, int numero){

		if(xDebut==xFin){ // si le bateau est horizontal
			lieuEtat = new int[(yFin-yDebut+1)][3]; // On cr�� un tableau de (yMax-Ymin)+1 en hauteur (soit pour la case 1 � 4, 3 cases + 1, soit bien 4 cases de long.
			horizontal = true;

			for(int i=0 ; i<(yFin-yDebut+1) ; i++){ // On parcourt chaque case et on ajoute ses coodonn�es en X et en Y. le +1 est pour la m�me raison qu'au dessus.
				lieuEtat[i][0] = xDebut;
				lieuEtat[i][1] = yDebut+i;
			}
		} else if (yDebut==yFin) { // si le bateau est vertical
			lieuEtat = new int[(xFin-xDebut+1)][3]; // On cr�� un tableau de (xMax-xMin)+1 en hauteur (soit pour la case 1 � 4, 3 cases + 1, soit bien 4 cases de long.
			horizontal = false;

			for(int i=0 ; i<(xFin-xDebut+1) ; i++){ // On parcourt chaque case et on ajoute ses coodonn�es en X et en Y. le +1 est pour la m�me raison qu'au dessus.
				lieuEtat[i][0] = xDebut+i; 
				lieuEtat[i][1] = yDebut;
			}
		}

		creerBateau(numero); // on lui attribue un numero
		rendreAlive();	//on le rend alive en mettant toute la colonne 2 � "1"

	}

	/** Cr�er un bateau du num�ro pass� en argument ( Note : classe un peu inutile, �a pourrait �tre vir� si on veut plus de place. Mais bon, c'est une sorte de guetteur/setter)
	 * @param numero : num�ro du bateau.
	 */
	public void creerBateau(int numero){
		this.numero = numero;
	}

	/** R�g�n�re un bateau. Le rend alive en mettant toute la colonne 2 (son �tat) � 1. �a pourrait �tre vu comme un regen du bateau, quel que soit son �tat.
	 */
	public void rendreAlive(){
		for(int i =0; i<lieuEtat.length ; i++){
			lieuEtat[i][2] = 1;						//on passe toute la colonne "de Vie" � 1 comme vivant
		}
		alive = true;
	}

	/** M�thode qui coule un bateau, quel que soit son �tat, mettant toute la colonne 2 (son �tat) � 0.
	 */
	public void rendreCouler(){
		for(int i =0; i<lieuEtat.length ; i++){
			lieuEtat[i][2] = 0;						//on passe toute la colonne "de Vie" � 0 comme mort
		}
		alive = false;
	}

	/** M�thode qui demande � un bateau si il est pr�sent sur la case des coordon�es pass�es en argument.
	 * @param x : hauteur de la case, son indice en vertical. (ordonn�e)
	 * @param y : largeur de la case, son indice en horizontal. (abscisse)
	 * @return un boolean qui vaut true si le batau est pr�sent sur la case, false si le bateau n'est pas pr�sent sur la case.
	 */
	public boolean estPresentSurCetteCase(int x, int y){
		boolean jeSuisPresent = false; // On pose une variable � retourner

		for(int i=0; i< lieuEtat.length ; i++){ // On parcourt le tableau de coordonn�es du bateau en question
			if(lieuEtat[i][0] == x && lieuEtat[i][1]==y){ // Si les coordonn�es en argument correspondent � un binome du tableau ( si � la ligne i, la colonne 0 = x et la colonne 1 = y, alors on renvois true
				jeSuisPresent = true;
			}
		}

		return jeSuisPresent;
	}

	public void tir(Plateau plateauJoueur, int x, int y){


	}

	public void recevoirModification(int x, int y, int modification){
		for(int i =0; i<lieuEtat.length ; i++){
			if(lieuEtat[i][0] == x && lieuEtat[i][1] == y){
				lieuEtat[i][2] += modification; // Le batea subit la modification notifi�e dans la colonne 3 de son tableau d'�tat.
			}
		}
		this.verifierAlive();
	}
	
	public void recevoirTir(int x, int y){
		this.recevoirModification(x, y, -1); // Le bateau subit une modification de vie de -1
	}

	public void verifierAlive(){
		boolean aliveOrNot = false;

		for(int i =0; i<lieuEtat.length ; i++){
			if(lieuEtat[i][2] > 0){
				aliveOrNot = true;
			}
		}
		this.alive = aliveOrNot;
	}
		
}