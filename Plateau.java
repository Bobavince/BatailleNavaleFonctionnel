/**
 * @author Vincent F et Thomas B
 * Classe de gestion du plateau de jeu.
 */

public class Plateau {
	//Les Tableaux de la classe
	int[][][] plateauValeurs; //Initilisation d'un tableau 3D Pour impl�mentation des deux niveaux de donn�es
	
	//Les variables internes
	
	
	//Les variables externes
	int nbBateaux;
	String name = "Joueur";
	ListeBateau bateaux;
	
	
	
	/**
	 * @param name : nom � donner au plateau/joueur
	 * @param x : largeur du plateau
	 * @param y : hauteur du plateau
	 */
	public Plateau(String name, int x, int y){					// Par d�faut on cr�� un plateau carr�.
		plateauValeurs = new int[x][y][2]; 						// on initialise le plateau � la tailel voulue
		int[][] plateauBuffer = Type.carre(x,y);				 // on r�cup�re un tableau de la forme voulue
		
		for(int i =0 ; i<plateauValeurs.length ; i++){
			for(int j =0 ; j<plateauValeurs[0].length ; j++){
				plateauValeurs[i][j][0] = plateauBuffer[i][j]; //on remplis le tableau principal avec le pattern choisi
			}
		}
			
		this.name = name;
	}
	
	/**
	 * @param name : nom � donner au plateau/joueur
	 * @param x : largeur du plateau
	 * @param y : hauteur du plateau
	 * @param numeroDeType : Numero correspondant au type de plateau voulu (0 pour carr�, 1 pour rond etc .. (A CORRIGER)) 
	 */
	public Plateau(String name, int x, int y, int numeroDeType){
		plateauValeurs = new int[x][y][2];						// on initialise le plateau � la tailel voulue
		int[][] plateauBuffer = Type.forme(x, y, numeroDeType);  // on r�cup�re un tableau de la forme voulue
		
		for(int i =0 ; i<plateauValeurs.length ; i++){
			for(int j =0 ; j<plateauValeurs[0].length ; j++){
				plateauValeurs[i][j][0] = plateauBuffer[i][j]; //on remplis le tableau principal avec le pattern choisi
			}
		}
		
		this.name = name;
	}

	
	/** Classe qui va remplir le plateau de (nombredeBateau) bateaux.
	 * @param nombreDeBateaux : nombre de Bateaux � ajouter au plateau de jeu.
	 */
	public void remplirAleatoirement(int nombreDeBateaux){
		bateaux = new ListeBateau(nombreDeBateaux); // On cr�� la liste des bateaux
		boolean bateauPossible;
		
		for(int i=0 ; i<bateaux.listeBat.length ; i++){
			
			int x = 0;
			int y = 0;
			int z = 0;
			int t = 0;
			int longueur = 4; //correspond � la vrai longueur du bateau.
			bateauPossible = false;
			
			while(bateauPossible==false){
			
				// **** ON CHOISIT LES COORDONNES DU BATEAU ET SON ORIENTATION **** // Les variables ont le suffixe -p pour "possible"
				int xMax = plateauValeurs.length; // On rep�re la hauteur maximal du plateau de jeu
				int yMax = plateauValeurs[0].length; // on rep�re la longueur maximale du plateau de jeu
				
				int xp = (int)(Math.random()*(xMax)); // on choisi une case sur les x.
				int zp ;
				int yp = (int)(Math.random()*(yMax)); // on rep�re une case sur les y.
				int tp ;
				
				bateauPossible = true; // On consid�re que le bateau est possible
				
				if(Math.random()<(0.5)){ // On fait un 50/50
					zp = xp; // Le bateau est horizontal.
					tp = (longueur-1+yp); // le bateau est de taille "longueur" voir explication ci-dessous.
					
					// **** ON VERIFIE QUE CE BATEAU RENTRE LA OU ON A CHOISIT ****
					if((yp+longueur)<plateauValeurs.length){
						for( int j=0 ; j<longueur ; j++){
							if(plateauValeurs[xp][yp+j][0]!=0){
								bateauPossible=false;
							}
						}
					} else {
						bateauPossible = false;
					}
				} else {
					zp = (longueur-1+xp); // le bateau est de taille "longueur" d'o� le -1 (compter sur un damier pour v�rifier : y + 5 va donner un bateau de taille 6 car y est inclus)
					tp = yp; // le bateau est vertical.
					
					// **** ON VERIFIE QUE CE BATEAU RENTRE LA OU ON A CHOISIT ****
					if((xp+longueur)<plateauValeurs[xp].length){
						for( int j=0 ; j<longueur ; j++){
							if(plateauValeurs[xp+j][yp][0]!=0){
								bateauPossible=false;
							}
						}
					} else {
						bateauPossible = false;
					}
					
				}
				
				// **** SI OUI ON PLACE LES VARIABLES TEMPORAIRE (EN -P) DANS LES VARIBLES DEFINITIVES pour les sortir de la boucle while****
				if(bateauPossible==true){
					x = xp;
					y = yp;
					z = zp;
					t = tp;
				} //Fin du if(beateaupossible==true)
				
			}// fin du while (bateauPossible==false)
			
			// **** ON CREE LE BATEAU SI LE bateau etait possible****
			bateaux.listeBat[i] = new Bateau(x,y,z,t,i+1);
		  	System.out.println("Le bateau N�" + (i+1) + " (" + x + y + z + t + ") est cr��. (xmin,ymin,xmax,ymax) donc il est horizontal :" + (bateaux.listeBat[i].horizontal) );

		}
	}
	
	/**
	 * @param x : largeur du plateau
	 * @param y : hauteur du plateau
	 */
	public void tirer(int x, int y){
		
	}
	
	/**
	 * @param x : largeur du plateau
	 * @param y : hauteur du plateau
	 * @param joueur : sur qui on veut tirer
	 */
	public void tirerJoueur(int x, int y, Plateau joueur){
		
	}
	
}


/**
* Le plateau est compos� de deux niveaux. Un niveau 0 qui sert � connaitre ce qu'il y a sur le le plateau et un niveau 1 qui sert � savoir les actions subit par le plateau.
* Au niveau 0 : 0 correspond � une case vide, -1 � une r�cif, 1,2,3,...,n au num�ro du bateau.
* C'est le plateau qui appelle la m�thode du bateau tireur (On devrait faire de la hi�rarchie, avec le classe bateau, puis la classe bateau principal, puis bateau tireur)
* Le plateau tire aux coordonn�es x,y sur le plateau du joueur Z. La m�thode sera donc Tire(Plateau Z, int x, int y)
* Si il touche une case au niveau 0 qui a pour valeur : 0, le tire tombe dans l'eau. -1 le tire ne fait rien (probabilit� de rebondir?), 1,2,...n le tire touche un bateau.
* Si le tire touche un bateau, dans ce cas, le bateau en question en est inform�. Il met � jour son statut, son �tat, etc...
* 
* Chaque joueur tire tour � tour.
* Le niveau 1 du plateau permet de savoir quelles cases ont d�j� �t� touch�es.
* 
* Evolutions possibles : 
* Ajouter plusieurs points de vie � une m�me case.
* Permet de g�rer la forme du plateau en mettant une valeur -2 qui indique que le plateau n'existe pas � cette endroit.
* Permet de ne pas tirer deux fois au m�me endroit si la couche 1 du plateau a pour valeur 1. (on pourrait �galement mettre le num�ro du joueur � la palce, de mani�re � savoir qui a d�j� tir� � cet endroit)
* L'IA a g�rer est ind�pendante de ces caract�res. PAs plus compliqu� � g�rer.
* La repr�sentation en fenetre peut se faire facilement grace justement au nombre de la couche 0 ! On pourrait rajouter une troisi�me couche pour le brouillard de guerre, et ainsi savoir quelles cases cacher au joueur.
*/ 


