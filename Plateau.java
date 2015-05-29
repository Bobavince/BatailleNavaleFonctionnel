/**
 * @author Vincent F et Thomas B
 */

/**
 * Classe de gestion du plateau de jeu.
 */
public class Plateau {
	
	//Les Tableaux de la classe
	int[][][] plateauValeurs; //Initilisation d'un tableau 3D Pour implémentation des deux niveaux de données

	//Les variables externes
	int nbBateaux;
	String name = "Joueur";
	ListeBateau bateaux;
	int type = 0 ;

	
	/** Constructeur du plateau de jeu d'un joueur : par défaut plateau carré de la taille passée en argument.
	 * @param name : nom à donner au plateau/joueur
	 * @param x : largeur du plateau
	 * @param y : hauteur du plateau
	 */
	public Plateau(String name, int x, int y){// Par défaut on créé un plateau carré.
		plateauValeurs = new int[x][y][2]; 						// on initialise le plateau à la tailel voulue
		int[][] plateauBuffer = Type.carre(x,y);				 // on récupère un tableau de la forme voulue

		for(int i =0 ; i<plateauValeurs.length ; i++){
			for(int j =0 ; j<plateauValeurs[0].length ; j++){
				plateauValeurs[i][j][0] = plateauBuffer[i][j]; //on remplis le tableau principal avec le pattern choisi
			}
		}

		this.name = name;
	}

	/** Constructeur du plateau de jeu d'un joueur avec choix du type de plateau.
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
		this.type = numeroDeType;
	}


	/** Méthode qui va remplir le plateau de (nombredeBateau) bateaux.
	 * @param listeTailleBateaux : nombre de Bateaux à ajouter au plateau de jeu, sous forme de liste.
	 */
	public void remplirAleatoirement(Joueur listeTaille){
		// On vérifie si les bateaux tiennent dans le plateau, on fait modifier la liste en conséquence.
		//listeTaille.transformationEnlisteTailleBateauCorrecte(this, listeTaille);
		
		bateaux = new ListeBateau(listeTaille.getTypeDeFlotte().length); // On créé la liste des bateaux
		boolean bateauPossible;
		System.out.println("Longueur : " + bateaux.getListeBat().length);

		for(int i=0 ; i<bateaux.getListeBat().length ; i++){

			int x = 0;
			int y = 0;
			int z = 0;
			int t = 0;
			int longueur = listeTaille.getTypeDeFlotte()[i]; //correspond à la vrai longueur du bateau.
			System.out.println("Longueur : " + listeTaille.getTypeDeFlotte()[i]);

			bateauPossible = false;

			while(bateauPossible==false){

				// **** ON CHOISIT LES COORDONNES DU BATEAU ET SON ORIENTATION **** // Les variables ont le suffixe -p pour "possible"
				int xMax = getPlateauValeurs().length; // On repère la hauteur maximal du plateau de jeu
				int yMax = getPlateauValeurs()[0].length; // on repère la longueur maximale du plateau de jeu

				int xp = (int)(Math.random()*(xMax)); // on choisi une case sur les x.
				int zp ;
				int yp = (int)(Math.random()*(yMax)); // on repère une case sur les y.
				int tp ;

				bateauPossible = true; // On considère que le bateau est possible

				if(Math.random()<(0.5)){ // On fait un 50/50
					zp = xp; // Le bateau est horizontal.
					tp = (longueur-1+yp); // le bateau est de taille "longueur" voir explication ci-dessous.

					// **** ON VERIFIE QUE CE BATEAU RENTRE LA OU ON A CHOISIT ****
					if((yp+longueur)<getPlateauValeurs()[xp].length){
						for( int j=0 ; j<longueur ; j++){
							if(getPlateauValeurs()[xp][yp+j][0]!=0){
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
					if((xp+longueur)<getPlateauValeurs().length){
						for( int j=0 ; j<longueur ; j++){
							if(getPlateauValeurs()[xp+j][yp][0]!=0){
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
			placerBateau(bateaux.getListeBat()[i]);
			// **** ON LE PLACE SUR LE PLATEAU****

			//DEBUG ONLY // 
			System.out.println("Le bateau N°" + (i+1) + " de taille" + longueur + "(" + x + y + z + t + ") est créé. (xmin,ymin,xmax,ymax) donc il est horizontal :" + (bateaux.listeBat[i].horizontal) );

		}
	}

	
	/** Méthode qui va demander au joueur de remplir son plateau, avec les bateaux dont les tailles sont spécifié dans le tableau passé en argument "listeTailleBateau"
	 * @param tailleMaxBateau : Spécifie la taille maximum d'un bateau
	 * @param listeTailleBateaux : La liste des bateaux à créer en fonction de leur taille.
	 */
	public void remplirManuellement(int tailleMaxBateau, Joueur listeTaille){
		// On vérifie si les bateaux tiennent dans le plateau, on fait modifier la liste en conséquence.
		//listeTaille.transformationEnlisteTailleBateauCorrecte(this, listeTaille);
		
		bateaux = new ListeBateau(listeTaille.getTypeDeFlotte().length); // On créé la liste des bateaux
		boolean bateauPossible;

		for(int i=0 ; i<bateaux.getListeBat().length ; i++){

			System.out.println(" **** BATEAU N°" + (i+1) + " ****");

			int x = 0;
			int y = 0;
			int z = 0;
			int t = 0;
			int[] coordonnes = new int[2];
			int longueur = listeTaille.getTypeDeFlotte()[i]; //correspond à la vrai longueur du bateau.
			bateauPossible = false;
			boolean horizontal = false;


			//la première coordonnées. puis l'orientation.
			while(bateauPossible==false){
				Affichage.afficherGrille(this);
				coordonnes = Joueur.choixCoordonnes(this); // On demande des coordonnés pour placer le bateau.
				horizontal = Joueur.choixOrientation();

				// **** ON CHOISIT LES COORDONNES DU BATEAU ET SON ORIENTATION **** // Les variables ont le suffixe -p pour "possible"
				int xp = coordonnes[0]; // on choisi une case sur les x.
				int zp ;
				int yp = coordonnes[1]; // on repère une case sur les y.
				int tp ;

				bateauPossible = true; // On considère que le bateau est possible

				if(horizontal){ // On fait un 50/50
					zp = xp; // Le bateau est horizontal.
					tp = (longueur-1+yp); // le bateau est de taille "longueur" voir explication ci-dessous.

					// **** ON VERIFIE QUE CE BATEAU RENTRE LA OU ON A CHOISIT ****
					if((yp+longueur)<getPlateauValeurs()[xp].length){
						for( int j=0 ; j<longueur ; j++){
							if(getPlateauValeurs()[xp][yp+j][0]!=0){
								bateauPossible=false;
								System.out.println("Le bateau ne rentre pas ! Colision avec : " + getPlateauValeurs()[xp][yp+j][0] );
							}
						}
					} else {
						bateauPossible = false;
						System.out.println("Le bateau ne rentre pas ! Il sort du plateau !");
					}
				} else {
					zp = (longueur-1+xp); // le bateau est de taille "longueur" d'où le -1 (compter sur un damier pour vérifier : y + 5 va donner un bateau de taille 6 car y est inclus)
					tp = yp; // le bateau est vertical.

					// **** ON VERIFIE QUE CE BATEAU RENTRE LA OU ON A CHOISIT ****
					if((xp+longueur)<getPlateauValeurs().length){
						for( int j=0 ; j<longueur ; j++){
							if(getPlateauValeurs()[xp+j][yp][0]>0){
								bateauPossible=false;
								System.out.println("Le bateau ne rentre pas ! Colision avec : " + getPlateauValeurs()[xp+j][yp][0] );
							} else if (getPlateauValeurs()[xp+j][yp][0]==-2){
								bateauPossible=false;
								System.out.println("Le bateau ne rentre pas ! Hors de la zone de jeu autorisée" );
							} else if (getPlateauValeurs()[xp+j][yp][0]==-1){
								bateauPossible=false;
								System.out.println("Le bateau ne rentre pas ! Colision avec des récifs ! ");
							} else {
								bateauPossible=false;
								System.out.println("Le bateau ne rentre pas ! Gros problème ! " );
							}
						}
					} else {
						bateauPossible = false;
						System.out.println("Le bateau ne rentre pas ! Il sort du plateau !");
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
			placerBateau(bateaux.getListeBat()[i]);
			// **** ON LE PLACE SUR LE PLATEAU****

			//DEBUG ONLY // 
			//System.out.println("Le bateau N°" + (i+1) + " de taille" + longueur + "(" + x + y + z + t + ") est créé. (xmin,ymin,xmax,ymax) donc il est horizontal :" + (bateaux.listeBat[i].horizontal) );
			
		}

	}


	/** Méthode qui renvois une liste de Taille de bateaux correcte, pour permettre que ça tienne dans le plateau.
	 * @param listeTailleBateaux
	 * @return
	 */
	public int[] transformationEnlisteTailleBateauCorrecte(int[] listeTailleBateaux){
		// **** ON VERIFIE QUE LE NOMBRE DE BATEAU ENTRE PAR L'UTILISATEUR EST COHERENT ***//
		int placeTotal = 0;
		int[] listeTailleBateauxRemplacement = listeTailleBateaux;
		int tailleNouveauTableau = 0;

		for(int i=0; i<listeTailleBateaux.length ; i++){
			placeTotal += listeTailleBateaux[i];
		}

		if(Joueur.calculNombreBateauxOptimal(this, listeTailleBateaux.length)>placeTotal){
			boolean correct = true ;
			int placePriseParNouveauTableau = 0;
			for(int i=0; i<listeTailleBateaux.length && placePriseParNouveauTableau < placeTotal && correct == true; i++){
				if(placePriseParNouveauTableau+listeTailleBateaux[i]<placeTotal){
					placePriseParNouveauTableau += listeTailleBateaux[i];
					tailleNouveauTableau++;
				} else if(placePriseParNouveauTableau+listeTailleBateaux[i]>placeTotal && i==0){
					tailleNouveauTableau = 1;
					listeTailleBateaux[0] = 2;
				} else {
					correct = false;
				}
			}

			listeTailleBateauxRemplacement = new int[tailleNouveauTableau];
			for(int i=0; i<listeTailleBateaux.length && i<listeTailleBateauxRemplacement.length ; i++){
				listeTailleBateauxRemplacement[i] = listeTailleBateaux[i];
			}
		}
		return listeTailleBateauxRemplacement;
	}
	
	/** Méthode qui teste si un bateau est présent sur la case considérée. Il parcourt la liste des bateau affiliée au plateau du joueur, et demande à chaque bateau si il est sur la case considérée.
	 * @param x : ordonnées de la case (vertical)
	 * @param y : abscisse de la case (horizontal)
	 * @return un boolean qui vaut true si un bateau est présent.
	 */
	public boolean bateauPresent(int x, int y){
		boolean estPresent = false; // On pose une variable à retourner.

		for(int i =0; i < bateaux.getListeBat().length ; i++) { // on parcours la liste des bateaux sans dépasser
			if(bateaux.getListeBat()[i].estPresentSurCetteCase(x, y)==true){ //on demande à chaque bateau si il est sur la case qu'on considère
				estPresent = true; // Si oui on passe la variable à True : il y a un bateau sur cette case
			} 
		}

		return estPresent;
	}

	/** Méthode qui teste si un bateau est présent sur la case considérée. Il parcourt la liste des bateau affiliée au plateau du joueur, et demande à chaque bateau si il est sur la case considérée. Si il est présent, on retourne son numéro.
	 * @param x : ordonnées de la case (vertical)
	 * @param y : abscisse de la case (horizontal)
	 * @return un boolean qui vaut la valeur du bateau si un bateau est présent. Sinon il renvoit 0 comme "eau".
	 */
	public int numeroBateauPresent(int x, int y){
		int numeroDuBateau = 0; // On pose une variable à retourner.

		for(int i =0; i < bateaux.getListeBat().length ; i++) { // on parcours la liste des bateaux sans dépasser
			if(bateaux.getListeBat()[i].estPresentSurCetteCase(x, y)==true){ //on demande à chaque bateau si il est sur la case qu'on considère
				numeroDuBateau = bateaux.getListeBat()[i].getNumero(); // Si oui on récupère son numéro
			} 
		}
		return numeroDuBateau;
	}

	/** Méthode qui place un bateau sur le plateau de jeu, sur la couche 0, avec son numéro de bateau. (On remplace l'eau par son numéro d'identifiant.)
	 * @param boat Un objet de type bateau, qui sera placé sur le plateau de jeu, en récupérant ses coordonnées directement dans la classe bateau.
	 */
	public void placerBateau(Bateau boat){
		for (int i=0 ; i<boat.getLieuEtat().length ; i++){ // pour toutes les couples de coordonnées du bateau donné
			plateauValeurs[boat.getLieuEtat()[i][0]][boat.getLieuEtat()[i][1]][0] = boat.getNumero();	//on met le numero du bateau sur la couche 0
		}
	}

	/** Méthode qui tire sur un plateau ennemi aux coordonnées passées en argument.
	 * @param coordonnes : tableau qui prend x en [0] et y en [1]
	 * @param joueur : plateau cible.
	 */
	public void tirerJoueur(int[] coordonnes, Plateau joueur){
		joueur.recevoirTir(coordonnes[0], coordonnes[1]);
	}
	
	/** Méthode qui permet de recevoir un tir, en mettant à jour la case sur la couche 1 du plateau, et en appelant la méthode "recevoir tire" du bateau sur la case de la couche 0
	 * @param x : coordonnées de tir (vertical/ordonnée)
	 * @param y : coordonnées de tir (horizontal/abscisse)
	 */
	public void recevoirTir(int x, int y){
		this.plateauValeurs[x][y][1] = 1; // on met à jour la couche 1 du plateau en mettant un "1" pour signaler un tir ennemi.
		if(this.bateauPresent(x, y)==true){
			this.bateaux.getListeBat()[this.numeroBateauPresent(x, y)-1].recevoirTir(x, y); // On prend le plateau du joueur, la liste de bateau associée, on récupère la liste des bateaux, dont on sélectionne le numéro du bateau qui nous intéresse, et on lui demande de recevoir un tir.			
		}
	}
	
	/** Méthode qui calcul le nombre de bateaux alive restant. Retourne si le joueur a perdu ou non.
	 * @return un boolean qui vaut true si tous les bateaux sont à "mort"
	 */
	public boolean aPerdu(){
		boolean aPerdu = false;
		this.calculNombreDeBateauRestant();
		if(nbBateaux==0){
			aPerdu=true;
		}
		return aPerdu;
	}

	/** Méthode qui calcul le nombre de bateaux restants en vie sur le plateau courant.
	 */
	public void calculNombreDeBateauRestant(){
		int nombreDeBateauxAlive = 0;
		for(int i =0; i < bateaux.getListeBat().length ; i++) { // on parcours la liste des bateaux sans dépasser
			if(bateaux.getListeBat()[i].isAlive()==true){ //on demande à chaque bateau si il est vivant
				nombreDeBateauxAlive++;
			} 
		}
		this.nbBateaux = nombreDeBateauxAlive;
	}

	/** Méthode qui regarde si la case considérée a déjà subit un tir ou non.
	 * @param x : ordonnée.
	 * @param y : abscisse.
	 * @return un boolean qui vaut true si la case a déjà subit un tir, false sinon.
	 */
	public boolean dejaSubiTir(int x, int y){
		boolean dejaSubiTir = false;
		if(plateauValeurs[x][y][1]!=0){
			dejaSubiTir=true;
		}
		return dejaSubiTir;
	}
	
	/** Méthode qui compte le nombre de cases libres sur un plateau de jeu.
	 * @return un int qui représente le nombre de cases libres (0) sur le plateau.
	 */
	public int compterCasesLibres(){
		int casesLibres = 0;
		
		for(int i = 0; i<this.plateauValeurs.length ; i++){
			for(int j =0; j<this.plateauValeurs[i].length ; j++){
				if(this.plateauValeurs[i][j][0]==0){
					casesLibres++;
				}
			}
		}
		return casesLibres;
	}
	
	// ********** LES GETTERS ET SETTERS ********** //
	
	/**
	 * @return the plateauValeurs
	 */
	public int[][][] getPlateauValeurs() {
		return plateauValeurs;
	}

	/**
	 * @param plateauValeurs le plateauValeurs à "setter"
	 */
	public void setPlateauValeurs(int[][][] plateauValeurs) {
		this.plateauValeurs = plateauValeurs;
	}

	/**
	 * @return the nbBateaux
	 */
	public int getNbBateaux() {
		return nbBateaux;
	}

	/**
	 * @param nbBateaux le nbBateaux à "setter"
	 */
	public void setNbBateaux(int nbBateaux) {
		this.nbBateaux = nbBateaux;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name le name à "setter"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the bateaux
	 */
	public ListeBateau getBateaux() {
		return bateaux;
	}

	/**
	 * @param bateaux le bateaux à "setter"
	 */
	public void setBateaux(ListeBateau bateaux) {
		this.bateaux = bateaux;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type le type à "setter"
	 */
	public void setType(int type) {
		this.type = type;
	}
}