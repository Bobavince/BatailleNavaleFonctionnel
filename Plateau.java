/**
 * @author Vincent F et Thomas B
 * Classe de gestion du plateau de jeu.
 */

public class Plateau {
	//Les Tableaux de la classe
	int[][][] plateauValeurs; //Initilisation d'un tableau 3D Pour implémentation des deux niveaux de données
	
	//Les variables internes
	
	
	//Les variables externes
	int nbBateaux;
	String name = "Joueur";
	ListeBateau bateaux;
	
	
	
	/**
	 * @param name : nom à donner au plateau/joueur
	 * @param x : largeur du plateau
	 * @param y : hauteur du plateau
	 */
	public Plateau(String name, int x, int y){					// Par défaut on créé un plateau carré.
		plateauValeurs = new int[x][y][2]; 						// on initialise le plateau à la tailel voulue
		int[][] plateauBuffer = Type.carre(x,y);				 // on récupère un tableau de la forme voulue
		
		for(int i =0 ; i<plateauValeurs.length ; i++){
			for(int j =0 ; j<plateauValeurs[0].length ; j++){
				plateauValeurs[i][j][0] = plateauBuffer[i][j]; //on remplis le tableau principal avec le pattern choisi
			}
		}
			
		this.name = name;
	}
	
	/**
	 * @param name : nom à donner au plateau/joueur
	 * @param x : largeur du plateau
	 * @param y : hauteur du plateau
	 * @param numeroDeType : Numero correspondant au type de plateau voulu (0 pour carré, 1 pour rond etc .. (A CORRIGER)) 
	 */
	public Plateau(String name, int x, int y, int numeroDeType){
		plateauValeurs = new int[x][y][2];						// on initialise le plateau à la tailel voulue
		int[][] plateauBuffer = Type.forme(x, y, numeroDeType);  // on récupère un tableau de la forme voulue
		
		for(int i =0 ; i<plateauValeurs.length ; i++){
			for(int j =0 ; j<plateauValeurs[0].length ; j++){
				plateauValeurs[i][j][0] = plateauBuffer[i][j]; //on remplis le tableau principal avec le pattern choisi
			}
		}
		
		this.name = name;
	}

	
	/** Classe qui va remplir le plateau de (nombredeBateau) bateaux.
	 * @param nombreDeBateaux : nombre de Bateaux à ajouter au plateau de jeu.
	 */
	public void remplirAleatoirement(int nombreDeBateaux){
		bateaux = new ListeBateau(nombreDeBateaux); // On créé la liste des bateaux
		boolean bateauPossible;
		
		for(int i=0 ; i<bateaux.listeBat.length ; i++){
			
			int x = 0;
			int y = 0;
			int z = 0;
			int t = 0;
			int longueur = 4; //correspond à la vrai longueur du bateau.
			bateauPossible = false;
			
			while(bateauPossible==false){
			
				// **** ON CHOISIT LES COORDONNES DU BATEAU ET SON ORIENTATION **** // Les variables ont le suffixe -p pour "possible"
				int xMax = plateauValeurs.length; // On repère la hauteur maximal du plateau de jeu
				int yMax = plateauValeurs[0].length; // on repère la longueur maximale du plateau de jeu
				
				int xp = (int)(Math.random()*(xMax)); // on choisi une case sur les x.
				int zp ;
				int yp = (int)(Math.random()*(yMax)); // on repère une case sur les y.
				int tp ;
				
				bateauPossible = true; // On considère que le bateau est possible
				
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
					zp = (longueur-1+xp); // le bateau est de taille "longueur" d'où le -1 (compter sur un damier pour vérifier : y + 5 va donner un bateau de taille 6 car y est inclus)
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
		  	System.out.println("Le bateau N°" + (i+1) + " (" + x + y + z + t + ") est créé. (xmin,ymin,xmax,ymax) donc il est horizontal :" + (bateaux.listeBat[i].horizontal) );

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
* Le plateau est composé de deux niveaux. Un niveau 0 qui sert à connaitre ce qu'il y a sur le le plateau et un niveau 1 qui sert à savoir les actions subit par le plateau.
* Au niveau 0 : 0 correspond à une case vide, -1 à une récif, 1,2,3,...,n au numéro du bateau.
* C'est le plateau qui appelle la méthode du bateau tireur (On devrait faire de la hiérarchie, avec le classe bateau, puis la classe bateau principal, puis bateau tireur)
* Le plateau tire aux coordonnées x,y sur le plateau du joueur Z. La méthode sera donc Tire(Plateau Z, int x, int y)
* Si il touche une case au niveau 0 qui a pour valeur : 0, le tire tombe dans l'eau. -1 le tire ne fait rien (probabilité de rebondir?), 1,2,...n le tire touche un bateau.
* Si le tire touche un bateau, dans ce cas, le bateau en question en est informé. Il met à jour son statut, son état, etc...
* 
* Chaque joueur tire tour à tour.
* Le niveau 1 du plateau permet de savoir quelles cases ont déjà été touchées.
* 
* Evolutions possibles : 
* Ajouter plusieurs points de vie à une même case.
* Permet de gérer la forme du plateau en mettant une valeur -2 qui indique que le plateau n'existe pas à cette endroit.
* Permet de ne pas tirer deux fois au même endroit si la couche 1 du plateau a pour valeur 1. (on pourrait également mettre le numéro du joueur à la palce, de manière à savoir qui a déjà tiré à cet endroit)
* L'IA a gérer est indépendante de ces caractères. PAs plus compliqué à gérer.
* La représentation en fenetre peut se faire facilement grace justement au nombre de la couche 0 ! On pourrait rajouter une troisième couche pour le brouillard de guerre, et ainsi savoir quelles cases cacher au joueur.
*/ 


