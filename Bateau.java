/**
 * @author Vincent F et Thomas B
 */

/**
 * Classe de gestion du plateau des bateaux.
 */
public class Bateau {
	private int numero;
	private int longueur;
	private boolean horizontal;
	private boolean alive = false;
	private int[][] lieuEtat = null; // Deux premi�re colonne pour les coordonn�es, la troisi�me pour l'�tat

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

		this.numero = numero; // on lui attribue un numero
		rendreAlive();	//on le rend alive en mettant toute la colonne 2 � "1"

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

	/** M�thode qui devait �tre pr�sente selon le sujet.
	 * @param plateauJoueur : un plateau
	 * @param x : x 
	 * @param y : y
	 */
	public void tir(Plateau plateauJoueur, int x, int y){

		//C'�tait dans le sujet. Il n'est pas dit qu'on doit vraiment s'en servir.
	}

	/** M�thode qui permet d'effectuer une modification sur un case occup�e par un bateau.
	 * @param x : ordonn�e
	 * @param y : abscisse
	 * @param modification : nombre sign� ( il faut mettre le -) qui modifie la case correspondante, sur laquelle se trouve le bateau.
	 */
	public void recevoirModification(int x, int y, int modification){
		for(int i =0; i<lieuEtat.length ; i++){
			if(lieuEtat[i][0] == x && lieuEtat[i][1] == y){
				lieuEtat[i][2] += modification; // Le bateau subit la modification notifi�e dans la colonne 3 de son tableau d'�tat.
			}
		}
		this.verifierAlive();

		// DEBUG MODE //
		/* Pour connaitre les etats des bateaux, cheat.
		String etat = "";
		for(int i =0; i<lieuEtat.length ; i++){
			etat += "[" +lieuEtat[i][0] + "," + lieuEtat[i][1]+"]"+lieuEtat[i][2] ;
		}
		System.out.println("Etat du bateau tir� : " + etat);
		 */
		// FIN DEBUG MODE //
	}

	/** M�thode qui permet de recevoir un tir, donc par d�faut, une modification de -1 sur la case x/y
	 * @param x : ordonn�e
	 * @param y : abscisse
	 */
	public void recevoirTir(int x, int y){
		this.recevoirModification(x, y, -1); // Le bateau subit une modification de vie de -1
	}

	/** M�thode qui v�rifie si le bateau est alive, donc si toutes ces cases ont �t� touch�es ou non. (Si la colonne 2 est compl�tement � 0). Modifie la variable alive du bateau courant.
	 */
	public void verifierAlive(){
		boolean aliveOrNot = false;

		for(int i =0; i<lieuEtat.length ; i++){
			if(lieuEtat[i][2] > 0){
				aliveOrNot = true;
			}
		}

		this.alive = aliveOrNot;
	}

	/** Donne les coordonn�es d'une case d'un bateau alive.
	 * @return des coordonn�s
	 */
	public int[] donnerCoordonnesAlive(){
		int coordonnes[] = new int[2];

		for(int i =0; i<lieuEtat.length ; i++){
			if(lieuEtat[i][2] > 0){
				coordonnes[0] = lieuEtat[i][0];
				coordonnes[1] = lieuEtat[i][1];
			}
		}

		return coordonnes;
	}
	// ********** LES GETTERS ET SETTERS ********** //

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero � setter
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * @return the longueur
	 */
	public int getLongueur() {
		return longueur;
	}

	/**
	 * @param longueur the longueur � setter
	 */
	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	/**
	 * @return the horizontal
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * @param horizontal the horizontal � setter
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @param alive the alive � setter
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * @return the lieuEtat
	 */
	public int[][] getLieuEtat() {
		return lieuEtat;
	}

	/**
	 * @param lieuEtat the lieuEtat � setter
	 */
	public void setLieuEtat(int[][] lieuEtat) {
		this.lieuEtat = lieuEtat;
	}

}