/**
 * @author Vincent F et Thomas B
 */

import java.util.Scanner;

/**
 * Classe du MAIN
 */
public class TestJeu {
	public static void main(String[] args) {

		//Creations des instances nécessaires (Scanner, fenetre...)
		Scanner sc = new Scanner(System.in); 

		//Creation des variables 
		String answer = ""; // Utilisée pour le choix de l'utilsateur du mode de jeu
		char choix = '0';
		boolean rejouer = true;

		System.out.println("**** Bienvenue dans notre jeu ****");

		while(rejouer){ // Boucle principale qui va se relancer à chaque partie.

			System.out.println("A quel mode de jeu voulez vous jouer ?");

			while(choix != '1' && choix != '2' && choix != '3' && choix != '4' && choix != '5'){ // Boucle secondaire concernant le choix du mode de jeu.

				System.out.println("1 - Humain contre Humain \n2 - Humain contre IA\n3 - Humain contre ... contre Humain circulaire\n4 - Humain contre ... contre Humain non circulaire\n5 - Fenetré 2 joueurs humains");
				System.out.println("Veuillez saisir le numéro de votre réponse :");

				answer = sc.nextLine();
				if(answer.length()!=0){		//Sécurité anti-débile qui appuis sur entrée sans rien mettre
					choix = answer.charAt(0);
					System.out.println("Vous avez saisi : " + choix);
				}

				switch (choix) // Lancement du mode de jeu choisi
				{
				case '1' :
					HvsH();
					break;  
				case '2' :
					HvsIA();
					break;
				case '3' :
					HvsvsH();
					break;
				case '4' :
					HvsHNC();
					break;
				case 5 :
					fenetre();
					break;
				default:
					System.out.println("Vous ne savez pas lire =)");   
					break;
				}	    
			}
			choix = 0;
			System.out.println("Voulez vous rejouer ?"); //Pour rejouer.
			answer = sc.nextLine();
			if(answer.length()!=0 && answer.charAt(0) == 'n'){		//Sécurité anti-débile qui appuis sur entrée sans rien mettre
				if(answer.charAt(0) == 'n'){
					rejouer = false;
				} else if (answer.charAt(0)== 'O'){
					rejouer = true;
				}
			}

		}
	}

	public static void HvsH() {
		System.out.println("Mode HvsH lancé");// Ici est géré le mode de jeu Humain contre Humain

		//On instancie les variables utiles pour les demandes utilisateur
		int tailleX = 0;
		int tailleY = 0;
		int nombreBateau =0;

		//On instancie les variables utiles pour les "Max" : le nombre de bateaux maximal autorisé, la longueur maximal autorisée etc ... 
		int nombreBateauMax = 0;
		int longueurMax = 5;

		//On instancie les variables utiles pour les données sur les plateaux de jeux.
		int type = -1 ;
		String pseudo1 = "";
		String pseudo2 = "";
		Plateau joueur1;
		Plateau joueur2 ;

		// **** DEMANDE AU JOUEUR 1 DE S'IDENTIFIER ****
		pseudo1 = Joueur.identification(1);

		// **** DEMANDE AU JOUEUR 2 DE S'IDENTIFIER ****
		pseudo2 = Joueur.identification(2);

		// **** DEMANDE AU JOUEURS LA TYPE DU PLATEAU ****
		type = Joueur.typeDePlateau();

		// **** DEMANDE AU JOUEURS LA TAILLE DU PLATEAU ET LE GENERE ****
		if( type==0){
			tailleX = Joueur.taillePlateauCarre();
			//On va créer le plateau du joueur 1 et du joueur 2
			joueur1 = new Plateau(pseudo1, tailleX, tailleX, 0); // C'est un cercle ! Donc de taille r*2 
			joueur2 = new Plateau(pseudo2, tailleX, tailleX, 0);
			nombreBateauMax=tailleX*tailleX; // Environ le nombre de cases.

		} else if(type==1){

			tailleX = Joueur.taillePlateauRond();
			//On va créer le plateau du joueur 1 et du joueur 2
			joueur1 = new Plateau(pseudo1, tailleX*2, tailleX*2, 1); // C'est un cercle ! Donc de taille r*2 
			joueur2 = new Plateau(pseudo2, tailleX*2, tailleX*2, 1);
			nombreBateauMax=10*tailleX; // Environ 2Pi*r soit le nombre maximal de cases.

		} else if(type==2){

			tailleX = Joueur.taillePlateauTriangle();
			//On va créer le plateau du joueur 1 et du joueur 2
			joueur1 = new Plateau(pseudo1, (int)(tailleX*0.88660), tailleX, 2); // Un peu de trigo. Si on considère un triangle équilatéral, on a la hauteur du triangle qui est égale à 0,8*coté.
			joueur2 = new Plateau(pseudo2, (int)(tailleX*0.88660), tailleX, 2);
			nombreBateauMax=(int)(tailleX*tailleX*0.88660*0.5); // Environ la surface d'un triangle de hauteur cote*0,8 

		} else if (type==4){
			while((tailleX<=5 || tailleY<=5) && (tailleX + tailleY <= 10)){ 

				System.out.println( pseudo1 + " , " + pseudo2 + ": Sur quelle taille de plateau voulez-vous jouer ? Hauteur ?");
				tailleX = Joueur.demanderInt();
				System.out.println("Largeur ?");
				tailleY = Joueur.demanderInt();
			}

			//On va créer le plateau du joueur 1 et du joueur 2
			joueur1 = new Plateau(pseudo1, tailleX, tailleY, 4); 
			joueur2 = new Plateau(pseudo2, tailleX, tailleY, 4); 
			nombreBateauMax = tailleX*tailleY;

		} else {
			while((tailleX<=5 || tailleY<=5) && (tailleX + tailleY <= 10)){ 
				System.out.println( pseudo1 + " , " + pseudo2 + ": Sur quelle taille de plateau voulez-vous jouer ? Hauteur ?");
				tailleX = Joueur.demanderInt();
				System.out.println("Largeur ?");
				tailleY = Joueur.demanderInt();
			}

			//On va créer le plateau du joueur 1 et du joueur 2
			joueur1 = new Plateau(pseudo1, tailleX, tailleY); 
			joueur2 = new Plateau(pseudo2, tailleX, tailleY); 
			nombreBateauMax = tailleX*tailleY;
		}

		// ON CREE UN PATTERN DE FLOTTE QUI SERA APPLIQUE A CHAQUE JOUEUR.
		Joueur joueurs = new Joueur(); // On initialise une instance de la classe joueur pour qu'elle garde en mémoire le tableau des

		if(Joueur.veutChoisirCompositionDeFlotte()==true){ // Si les joueurs veulent composer la flotte
			nombreBateau = joueurs.utilitaireTypeDeFlotte(longueurMax,nombreBateauMax);	 // Alors on lance l'utilitaire de création de flotte
		} else {
			nombreBateau = Joueur.calculNombreBateauxOptimal(joueur1, nombreBateauMax);// METHODE A COMPLETER
			joueurs.utilitaireFlotteParDefaut(nombreBateau); // On lance l'utilitaire qui va créer la flotte par défaut.
		}

		// **** DEMANDE AU JOUEUR 1 LE CHOIX PLACEMENT DE SES BATEAUX ****
		joueurs.utilitairePlacementDesBateaux(joueur1, nombreBateau ,nombreBateauMax, longueurMax);	

		// **** DEMANDE AU JOUEUR 2 LE PLACEMENT DE SES BATEAUX ****
		joueurs.utilitairePlacementDesBateaux(joueur2, nombreBateau ,nombreBateauMax, longueurMax);

		//Pour tester l'affichage : on affiche le plateau du joueur 1
		Affichage.afficherGrille(joueur1);
		//Pour tester l'affichage : on affiche le plateau du joueur 2
		Affichage.afficherGrille(joueur2);

		//on gère les tours
		while(joueur1.aPerdu()==false && joueur2.aPerdu()==false){ // Tant que l'un ou l'autre n'a pas perdu
			System.out.println("\n ---------- \n" + joueur1.name + " : Vous tirez. Voici votre tableau de tir."); // On annonce le joueur
			Affichage.afficherGrilleEnnemi(joueur2); // On affiche la grille de tir du joueur envers l'ennemi
			Joueur.recupCoordonnesVerifierTirer(joueur1, joueur2); // On lui demande de tirer.

			System.out.println("\n ---------- \n" + joueur2.name + " : Vous tirez. Voici votre tableau de tir."); // On annonce le joueur
			Affichage.afficherGrilleEnnemi(joueur1); // On affiche la grille de tir du joueur nevers l'ennemi
			Joueur.recupCoordonnesVerifierTirer(joueur2, joueur1); // On lui demande de tirer.
		}

		if(joueur1.aPerdu()==true){
			System.out.println(joueur1.name + " a perdu ! " + joueur2.name + " a gagné !");
		} else if(joueur2.aPerdu()==true){
			System.out.println(joueur2.name + " a perdu ! " + joueur1.name + " a gagné !");
		}

		System.out.println("Mode HvsH stoppé.\n\n");  
	}

	public static void HvsIA() {

		System.out.println("Mode HvsIA lancé");   

		//On instancie les variables utiles pour les demandes utilisateur
		int tailleX = 0;
		int tailleY = 0;
		int nombreBateau =0;

		//On instancie les variables utiles pour les "Max" : le nombre de bateaux maximal autorisé, la longueur maximal autorisée etc ... 
		int nombreBateauMax = 0;
		int longueurMax = 5;

		//On instancie les variables utiles pour les données sur les plateaux de jeux.
		int type = -1 ;
		String pseudo1 = "";
		String pseudo2 = "";
		Plateau joueur1;
		Plateau joueur2 ;

		// **** DEMANDE AU JOUEUR 1 DE S'IDENTIFIER ****
		pseudo1 = Joueur.identification(1);

		// **** DEMANDE AU JOUEUR 1 DE DONNER LA DIFFICULTE DE L'IA ****
		IA iaDeJeu = new IA();
		pseudo2 = iaDeJeu.typeIA();

		// **** DEMANDE AU JOUEURS LE TYPE DU PLATEAU ****
		type = Joueur.typeDePlateau();

		// **** DEMANDE AU JOUEURS LA TAILLE DU PLATEAU ET LE GENERE ****
		if( type==0){
			tailleX = Joueur.taillePlateauCarre();
			//On va créer le plateau du joueur 1 et du joueur 2
			joueur1 = new Plateau(pseudo1, tailleX, tailleX, 0); // C'est un cercle ! Donc de taille r*2 
			joueur2 = new Plateau(pseudo2, tailleX, tailleX, 0);
			nombreBateauMax=tailleX*tailleX; // Environ le nombre de cases.

		} else if(type==1){

			tailleX = Joueur.taillePlateauRond();
			//On va créer le plateau du joueur 1 et du joueur 2
			joueur1 = new Plateau(pseudo1, tailleX*2, tailleX*2, 1); // C'est un cercle ! Donc de taille r*2 
			joueur2 = new Plateau(pseudo2, tailleX*2, tailleX*2, 1);
			nombreBateauMax=10*tailleX; // Environ 2Pi*r soit le nombre maximal de cases.

		} else if(type==2){

			tailleX = Joueur.taillePlateauTriangle();
			//On va créer le plateau du joueur 1 et du joueur 2
			joueur1 = new Plateau(pseudo1, (int)(tailleX*0.88660), tailleX, 2); // Un peu de trigo. Si on considère un triangle équilatéral, on a la hauteur du triangle qui est égale à 0,8*coté.
			joueur2 = new Plateau(pseudo2, (int)(tailleX*0.88660), tailleX, 2);
			nombreBateauMax=(int)(tailleX*tailleX*0.88660*0.5); // Environ la surface d'un triangle de hauteur cote*0,8 

		} else if (type==4){
			while((tailleX<=5 || tailleY<=5) && (tailleX + tailleY <= 10)){ 
				System.out.println( pseudo1 + " , " + pseudo2 + ": Sur quelle taille de plateau voulez-vous jouer ? Hauteur ?");
				tailleX = Joueur.demanderInt();

				System.out.println("Largeur ?");
				tailleY = Joueur.demanderInt();
			}

			//On va créer le plateau du joueur 1 et du joueur 2
			joueur1 = new Plateau(pseudo1, tailleX, tailleY, 4); 
			joueur2 = new Plateau(pseudo2, tailleX, tailleY, 4); 
			nombreBateauMax = tailleX*tailleY;

		} else {
			while((tailleX<=5 || tailleY<=5) && (tailleX + tailleY <= 10)){ 
				System.out.println( pseudo1 + " , " + pseudo2 + ": Sur quelle taille de plateau voulez-vous jouer ? Hauteur ?");
				tailleX = Joueur.demanderInt();

				System.out.println("Largeur ?");
				tailleY = Joueur.demanderInt();
			}

			//On va créer le plateau du joueur 1 et du joueur 2
			joueur1 = new Plateau(pseudo1, tailleX, tailleY); 
			joueur2 = new Plateau(pseudo2, tailleX, tailleY); 
			nombreBateauMax = tailleX*tailleY;
		}

		// ON CREE UN PATTERN DE FLOTTE QUI SERA APPLIQUE A CHAQUE JOUEUR.
		Joueur joueurs = new Joueur(); // On initialise une instance de la classe joueur pour qu'elle garde en mémoire le tableau des

		if(Joueur.veutChoisirCompositionDeFlotte()==true){ // Si les joueurs veulent composer la flotte
			nombreBateau = joueurs.utilitaireTypeDeFlotte(longueurMax,nombreBateauMax);	 // Alors on lance l'utilitaire de création de flotte
		} else {
			nombreBateau = Joueur.calculNombreBateauxOptimal(joueur1, nombreBateauMax);// METHODE A COMPLETER
			joueurs.utilitaireFlotteParDefaut(nombreBateau); // On lance l'utilitaire qui va créer la flotte par défaut.
		}

		// **** DEMANDE AU JOUEUR 1 LE CHOIX PLACEMENT DE SES BATEAUX ****
		joueurs.utilitairePlacementDesBateaux(joueur1, nombreBateau ,nombreBateauMax, longueurMax);	

		// **** DEMANDE AU JOUEUR 2 LE PLACEMENT DE SES BATEAUX ****
		iaDeJeu.placementBateauIA(joueur2, joueurs);

		//Pour tester l'affichage : on affiche le plateau du joueur 1
		Affichage.afficherGrille(joueur1);
		//Pour tester l'affichage : on affiche le plateau du joueur 2
		Affichage.afficherGrille(joueur2);

		//on gère les tours
		while(joueur1.aPerdu()==false && joueur2.aPerdu()==false){ // Tant que l'un ou l'autre n'a pas perdu
			System.out.println("\n ---------- \n" + joueur1.name + " : Vous tirez. Voici votre tableau de tir."); // On annonce le joueur
			Affichage.afficherGrilleEnnemi(joueur2); // On affiche la grille de tir du joueur envers l'ennemi
			Joueur.recupCoordonnesVerifierTirer(joueur1, joueur2); // On lui demande de tirer.
			System.out.println("\n ---------- \n" + joueur2.name + " : Vous tirez."); // On annonce l'IA
			iaDeJeu.tirSelonNiveau(joueur2, joueur1); // On lui demande de tirer.
		}

		if(joueur1.aPerdu()==true){
			System.out.println(joueur1.name + " a perdu ! " + joueur2.name + " a gagné !");
		} else if(joueur2.aPerdu()==true){
			System.out.println(joueur2.name + " a perdu ! " + joueur1.name + " a gagné !");
		}
		// Ici est géré le mode de jeu Humain contre IA

		System.out.println("Mode HvsIA stoppé");   
	}

	public static void HvsvsH() {
		System.out.println("Mode Hvsh..vsH circulaire lancé");   

		//On instancie les variables utiles pour les demandes utilisateur
		int tailleX = 0;
		int tailleY = 0;
		int nombreBateau =0;

		//On instancie les variables utiles pour les "Max" : le nombre de bateaux maximal autorisé, la longueur maximal autorisée etc ... 
		int nombreBateauMax = 0;
		int longueurMax = 5;

		//On instancie les variables utiles pour les données sur les plateaux de jeux.
		int type = -1 ;
		int nombreJoueurs = 0;

		// **** ON DEMANDE LE NOMBRE DE JOUEURS QUI COMPOSERONT LA PARTIE ****
		nombreJoueurs = Joueur.nombreDeJoueurs();

		// On instancie le tableau des joueurs en conséquence.
		String[] pseudoJoueurs = new String[nombreJoueurs];
		Plateau[] listeJoueurs = new Plateau[nombreJoueurs];

		// **** DEMANDE A CHAQUE JOUEUR DE S'IDENTIFIER ****
		for(int i = 0; i<pseudoJoueurs.length ; i++){
			pseudoJoueurs[i] = Joueur.identification(i);
		}

		// **** DEMANDE AU JOUEURS LE TYPE DU PLATEAU ****
		type = Joueur.typeDePlateau();

		// **** DEMANDE AU JOUEURS LA TAILLE DU PLATEAU ET LE GENERE ****
		if( type==0){

			tailleX = Joueur.taillePlateauCarre();
			//On va créer le plateau des joueurs.
			for(int i = 0; i<listeJoueurs.length ; i++){
				listeJoueurs[i] = new Plateau(pseudoJoueurs[i], tailleX, tailleX, 0); 
			}
			nombreBateauMax=tailleX*tailleX; // Environ le nombre de cases.

		} else if(type==1){

			tailleX = Joueur.taillePlateauRond();
			//On va créer le plateau des joueurs.
			for(int i = 0; i<listeJoueurs.length ; i++){
				listeJoueurs[i] = new Plateau(pseudoJoueurs[i], tailleX*2, tailleX*2, 1); // C'est un cercle ! Donc de taille r*2
			}
			nombreBateauMax=10*tailleX; // Environ 2Pi*r soit le nombre maximal de cases.

		} else if(type==2){

			tailleX = Joueur.taillePlateauTriangle();
			//On va créer le plateau des joueurs
			for(int i = 0; i<listeJoueurs.length ; i++){
				listeJoueurs[i] = new Plateau(pseudoJoueurs[i], (int)(tailleX*0.88660), tailleX, 2); // Un peu de trigo. Si on considère un triangle équilatéral, on a la hauteur du triangle qui est égale à 0,8*coté.
			}
			nombreBateauMax=(int)(tailleX*tailleX*0.88660*0.5); // Environ la surface d'un triangle de hauteur cote*0,8 

		}  else if (type==4){
			while((tailleX<=5 || tailleY<=5) && (tailleX + tailleY <= 10)){ 

				System.out.println("Sur quelle taille de plateau voulez-vous jouer ? Hauteur ?");
				tailleX = Joueur.demanderInt();

				System.out.println("Largeur ?");
				tailleY = Joueur.demanderInt();
			}

			//On va créer le plateau des joueurs
			for(int i = 0; i<listeJoueurs.length ; i++){
				listeJoueurs[i] = new Plateau(pseudoJoueurs[i],  tailleX, tailleY, 4); // Un peu de trigo. Si on considère un triangle équilatéral, on a la hauteur du triangle qui est égale à 0,8*coté.
			}
			nombreBateauMax = tailleX*tailleY;

		} else {
			while((tailleX<=5 || tailleY<=5) && (tailleX + tailleY <= 10)){ 

				System.out.println( "Joueurs : Sur quelle taille de plateau voulez-vous jouer ? Hauteur ?");
				tailleX = Joueur.demanderInt();

				System.out.println("Largeur ?");
				tailleY = Joueur.demanderInt();
			}

			//On va créer le plateau des joueurs
			for(int i = 0; i<listeJoueurs.length ; i++){
				listeJoueurs[i] = new Plateau(pseudoJoueurs[i], tailleX, tailleY); // Un peu de trigo. Si on considère un triangle équilatéral, on a la hauteur du triangle qui est égale à 0,8*coté.
			}
			nombreBateauMax = tailleX*tailleY;
		}

		// ON CREE UN PATTERN DE FLOTTE QUI SERA APPLIQUE A CHAQUE JOUEUR.
		Joueur joueurs = new Joueur(); // On initialise une instance de la classe joueur pour qu'elle garde en mémoire le tableau de la flotte

		if(Joueur.veutChoisirCompositionDeFlotte()==true){ // Si les joueurs veulent composer la flotte
			nombreBateau = joueurs.utilitaireTypeDeFlotte(longueurMax,nombreBateauMax);	 // Alors on lance l'utilitaire de création de flotte
		} else {
			nombreBateau = Joueur.calculNombreBateauxOptimal(listeJoueurs[0], nombreBateauMax);// METHODE A COMPLETER
			joueurs.utilitaireFlotteParDefaut(nombreBateau); // On lance l'utilitaire qui va créer la flotte par défaut.
		}

		// **** DEMANDE AUX JOUEURS LE PLACEMENT DE SES BATEAUX ****
		for(int i = 0; i<listeJoueurs.length ; i++){
			joueurs.utilitairePlacementDesBateaux(listeJoueurs[i], nombreBateau, nombreBateauMax, longueurMax);
		}

		//Pour tester l'affichage : on affiche le plateau de chaque joueur
		for(int i = 0; i<listeJoueurs.length ; i++){
			Affichage.afficherGrille(listeJoueurs[i]);
		}

		boolean tousLesJoueursSaufUnOntPerdu = false;
		Plateau cibleCourante;

		//on gère les tours
		for(int i = 0; i<listeJoueurs.length && tousLesJoueursSaufUnOntPerdu == false; i++){ // Tant que l'un des joueur n'a pas gagné.
			System.out.println("\n ---------- \n" + listeJoueurs[i].name + " : Vous tirez. Voici votre tableau de tir."); // On annonce le joueur
			cibleCourante = null;
			for(int j = 1; j<listeJoueurs.length && cibleCourante==null ;j++){
				if(listeJoueurs[(i+j)%listeJoueurs.length].aPerdu()==false){
					cibleCourante = listeJoueurs[(i+j)%listeJoueurs.length];
				}
			}

			Affichage.afficherGrilleEnnemi(cibleCourante); // On affiche la grille de tir du joueur envers l'ennemi
			Joueur.recupCoordonnesVerifierTirer(listeJoueurs[i], cibleCourante); // On lui demande de tirer.

			int compteurDeGagnant = 0;
			for(int j = 0; j<listeJoueurs.length ; j++){ // Petite astuce pour compter les gagnant à chaque tour.
				if(listeJoueurs[j].aPerdu()==false){
					compteurDeGagnant ++;
				}
			}
			if(compteurDeGagnant>1){
				tousLesJoueursSaufUnOntPerdu = false;
			} else {
				tousLesJoueursSaufUnOntPerdu = true ;
			}
		}

		for(int i = 0; i<listeJoueurs.length ; i++){
			if(listeJoueurs[i].aPerdu()==true){
				System.out.println(listeJoueurs[i].name + " a perdu ! ");
			} else {
				System.out.println(listeJoueurs[i].name + " a gagné ! ");
			}
		}

		System.out.println("Mode Hvsh..vsH circulaire stoppé");   

	}

	public static void HvsHNC() {
		System.out.println("Mode HVSH Non circulaire lancé");   

		//On instancie les variables utiles pour les demandes utilisateur
		int tailleX = 0;
		int tailleY = 0;
		int nombreBateau =0;

		//On instancie les variables utiles pour les "Max" : le nombre de bateaux maximal autorisé, la longueur maximal autorisée etc ... 
		int nombreBateauMax = 0;
		int longueurMax = 5;

		//On instancie les variables utiles pour les données sur les plateaux de jeux.
		int type = -1 ;
		int nombreJoueurs = 0;

		// **** ON DEMANDE LE NOMBRE DE JOUEURS QUI COMPOSERONT LA PARTIE ****
		nombreJoueurs = Joueur.nombreDeJoueurs();

		// On instancie le tableau des joueurs en conséquence.
		String[] pseudoJoueurs = new String[nombreJoueurs];
		Plateau[] listeJoueurs = new Plateau[nombreJoueurs];

		// **** DEMANDE A CHAQUE JOUEUR DE S'IDENTIFIER ****
		for(int i = 0; i<pseudoJoueurs.length ; i++){
			pseudoJoueurs[i] = Joueur.identification(i);
		}

		// **** DEMANDE AU JOUEURS LE TYPE DU PLATEAU ****
		type = Joueur.typeDePlateau();

		// **** DEMANDE AU JOUEURS LA TAILLE DU PLATEAU ET LE GENERE ****
		if(type==1){

			tailleX = Joueur.taillePlateauRond();

			//On va créer le plateau des joueurs.
			for(int i = 0; i<listeJoueurs.length ; i++){
				listeJoueurs[i] = new Plateau(pseudoJoueurs[i], tailleX*2, tailleX*2, 1); // C'est un cercle ! Donc de taille r*2
			}

			nombreBateauMax=10*tailleX; // Environ 2Pi*r soit le nombre maximal de cases.

		} else if(type==2){

			tailleX = Joueur.taillePlateauTriangle();

			//On va créer le plateau des joueurs
			for(int i = 0; i<listeJoueurs.length ; i++){
				listeJoueurs[i] = new Plateau(pseudoJoueurs[i], (int)(tailleX*0.88660), tailleX, 1); // Un peu de trigo. Si on considère un triangle équilatéral, on a la hauteur du triangle qui est égale à 0,8*coté.
			}

			nombreBateauMax=(int)(tailleX*tailleX*0.88660*0.5); // Environ la surface d'un triangle de hauteur cote*0,8 

		} else if (type==4){
			while((tailleX<=5 || tailleY<=5) && (tailleX + tailleY <= 10)){ 

				System.out.println( "Sur quelle taille de plateau voulez-vous jouer ? Hauteur ?");
				tailleX = Joueur.demanderInt();
				System.out.println("Largeur ?");
				tailleY = Joueur.demanderInt();
			}

			//On va créer le plateau du joueur 1 et du joueur 2
			for(int i = 0; i<listeJoueurs.length ; i++){
				listeJoueurs[i] = new Plateau(pseudoJoueurs[i], tailleX, tailleY, 4); 
			}
			nombreBateauMax = tailleX*tailleY;

		} else {
			while((tailleX<=5 || tailleY<=5) && (tailleX + tailleY <= 10)){ 

				System.out.println( "Joueurs : Sur quelle taille de plateau voulez-vous jouer ? Hauteur ?");
				tailleX = Joueur.demanderInt();

				System.out.println("Largeur ?");
				tailleY = Joueur.demanderInt();
			}

			//On va créer le plateau des joueurs
			for(int i = 0; i<listeJoueurs.length ; i++){
				listeJoueurs[i] = new Plateau(pseudoJoueurs[i], tailleX, tailleY); // Un peu de trigo. Si on considère un triangle équilatéral, on a la hauteur du triangle qui est égale à 0,8*coté.
			}

			nombreBateauMax = tailleX*tailleY;
		}

		// ON CREE UN PATTERN DE FLOTTE QUI SERA APPLIQUE A CHAQUE JOUEUR.
		Joueur joueurs = new Joueur(); // On initialise une instance de la classe joueur pour qu'elle garde en mémoire le tableau de la flotte

		if(Joueur.veutChoisirCompositionDeFlotte()==true){ // Si les joueurs veulent composer la flotte
			nombreBateau = joueurs.utilitaireTypeDeFlotte(longueurMax,nombreBateauMax);	 // Alors on lance l'utilitaire de création de flotte
		} else {
			nombreBateau = Joueur.calculNombreBateauxOptimal(listeJoueurs[0], nombreBateauMax);// METHODE A COMPLETER
			joueurs.utilitaireFlotteParDefaut(nombreBateau); // On lance l'utilitaire qui va créer la flotte par défaut.
		}

		// **** DEMANDE AUX JOUEURS LE PLACEMENT DE SES BATEAUX ****
		for(int i = 0; i<listeJoueurs.length ; i++){
			joueurs.utilitairePlacementDesBateaux(listeJoueurs[i], nombreBateau, nombreBateauMax, longueurMax);
		}

		//Pour tester l'affichage : on affiche le plateau de chaque joueur
		for(int i = 0; i<listeJoueurs.length ; i++){
			Affichage.afficherGrille(listeJoueurs[i]);
		}

		boolean tousLesJoueursSaufUnOntPerdu = false;
		Plateau cibleCourante;

		//on gère les tours
		for(int i = 0; i<listeJoueurs.length && tousLesJoueursSaufUnOntPerdu == false ; i++){  // Tant que l'un des joueur n'a pas gagné.
			System.out.println("\n ---------- \n" + listeJoueurs[i].name + " : Vous tirez. Voici votre tableau de tir."); // On annonce le joueur
			cibleCourante = Joueur.surQuiVoulezVousTirer(listeJoueurs[i], listeJoueurs); // On lui demande sur qui il veut tirer.
			Affichage.afficherGrilleEnnemi(cibleCourante); // On affiche la grille de tir du joueur envers l'ennemi
			Joueur.recupCoordonnesVerifierTirer(listeJoueurs[i], cibleCourante); // On lui demande de tirer.

			int compteurDeGagnant = 0;
			for(int j = 0; j<listeJoueurs.length ; j++){ // Petite astuce pour compter les gagnant à chaque tour.
				if(listeJoueurs[j].aPerdu()==false){
					compteurDeGagnant ++;
				}
			}
			if(compteurDeGagnant>1){
				tousLesJoueursSaufUnOntPerdu = false;
			} else {
				tousLesJoueursSaufUnOntPerdu = true ;
			}

		}

		for(int i = 0; i<listeJoueurs.length ; i++){
			if(listeJoueurs[i].aPerdu()==true){
				System.out.println(listeJoueurs[i].name + " a perdu ! ");
			} else {
				System.out.println(listeJoueurs[i].name + " a gagné ! ");
			}
		}

		System.out.println("Mode HVSH Non circulaire stoppé");   
	}


	public static void fenetre() {
		System.out.println("Mode fenetre lancé");// Ici est géré le mode de jeu Humain contre Humain

		//On instancie les variables utiles pour les demandes utilisateur
		int tailleX = 0;
		int tailleY = 0;
		int nombreBateau =0;

		//On instancie les variables utiles pour les "Max" : le nombre de bateaux maximal autorisé, la longueur maximal autorisée etc ... 
		int nombreBateauMax = 0;
		int longueurMax = 5;

		//On instancie les variables utiles pour les données sur les plateaux de jeux.
		int type = -1 ;
		String pseudo1 = "";
		String pseudo2 = "";
		Plateau joueur1;
		Plateau joueur2 ;

		// **** DEMANDE AU JOUEUR 1 DE S'IDENTIFIER ****
		pseudo1 = Joueur.identification(1);

		// **** DEMANDE AU JOUEUR 2 DE S'IDENTIFIER ****
		pseudo2 = Joueur.identification(2);

		// **** DEMANDE AU JOUEURS LA TYPE DU PLATEAU ****
		type = Joueur.typeDePlateau();

		// **** DEMANDE AU JOUEURS LA TAILLE DU PLATEAU ET LE GENERE ****
		if( type==0){
			tailleX = Joueur.taillePlateauCarre();
			//On va créer le plateau du joueur 1 et du joueur 2
			joueur1 = new Plateau(pseudo1, tailleX, tailleX, 0); // C'est un cercle ! Donc de taille r*2 
			joueur2 = new Plateau(pseudo2, tailleX, tailleX, 0);
			nombreBateauMax=tailleX*tailleX; // Environ le nombre de cases.

		} else if(type==1){

			tailleX = Joueur.taillePlateauRond();
			//On va créer le plateau du joueur 1 et du joueur 2
			joueur1 = new Plateau(pseudo1, tailleX*2, tailleX*2, 1); // C'est un cercle ! Donc de taille r*2 
			joueur2 = new Plateau(pseudo2, tailleX*2, tailleX*2, 1);
			nombreBateauMax=10*tailleX; // Environ 2Pi*r soit le nombre maximal de cases.

		} else if(type==2){

			tailleX = Joueur.taillePlateauTriangle();
			//On va créer le plateau du joueur 1 et du joueur 2
			joueur1 = new Plateau(pseudo1, (int)(tailleX*0.88660), tailleX, 2); // Un peu de trigo. Si on considère un triangle équilatéral, on a la hauteur du triangle qui est égale à 0,8*coté.
			joueur2 = new Plateau(pseudo2, (int)(tailleX*0.88660), tailleX, 2);
			nombreBateauMax=(int)(tailleX*tailleX*0.88660*0.5); // Environ la surface d'un triangle de hauteur cote*0,8 

		} else if (type==4){
			while(tailleX<=0 || tailleY<=0){ 
				System.out.println( pseudo1 + " , " + pseudo2 + ": Sur quelle taille de plateau voulez-vous jouer ? Hauteur ?");
				tailleX = Joueur.demanderInt();
				
				System.out.println("Largeur ?");
				tailleY = Joueur.demanderInt();
			}

			//On va créer le plateau du joueur 1 et du joueur 2
			joueur1 = new Plateau(pseudo1, tailleX, tailleY, 4); 
			joueur2 = new Plateau(pseudo2, tailleX, tailleY, 4); 
			nombreBateauMax = tailleX*tailleY;

		} else {
			while(tailleX<=0 || tailleY<=0){ 
				System.out.println( pseudo1 + " , " + pseudo2 + ": Sur quelle taille de plateau voulez-vous jouer ? Hauteur ?");
				tailleX = Joueur.demanderInt();
				
				System.out.println("Largeur ?");
				tailleY = Joueur.demanderInt();
			}

			//On va créer le plateau du joueur 1 et du joueur 2
			joueur1 = new Plateau(pseudo1, tailleX, tailleY); 
			joueur2 = new Plateau(pseudo2, tailleX, tailleY); 
			nombreBateauMax = tailleX*tailleY;
		}

		// ON CREE UN PATTERN DE FLOTTE QUI SERA APPLIQUE A CHAQUE JOUEUR.
		Joueur joueurs = new Joueur(); // On initialise une instance de la classe joueur pour qu'elle garde en mémoire le tableau des

		if(Joueur.veutChoisirCompositionDeFlotte()==true){ // Si les joueurs veulent composer la flotte
			nombreBateau = joueurs.utilitaireTypeDeFlotte(longueurMax,nombreBateauMax);	 // Alors on lance l'utilitaire de création de flotte
		} else {
			nombreBateau = Joueur.calculNombreBateauxOptimal(joueur1, nombreBateauMax);// METHODE A COMPLETER
			joueurs.utilitaireFlotteParDefaut(nombreBateau); // On lance l'utilitaire qui va créer la flotte par défaut.
		}

		// **** DEMANDE AU JOUEUR 1 LE CHOIX PLACEMENT DE SES BATEAUX ****
		joueurs.utilitairePlacementDesBateaux(joueur1, nombreBateau ,nombreBateauMax, longueurMax);	

		// **** DEMANDE AU JOUEUR 2 LE PLACEMENT DE SES BATEAUX ****
		joueurs.utilitairePlacementDesBateaux(joueur2, nombreBateau ,nombreBateauMax, longueurMax);

		//Pour tester la fenetre on met :
		Partie maPartie1 = new Partie(joueur1);
		Partie maPartie2 = new Partie(joueur2);
		maPartie1.setCible(joueur1, joueur2);
		maPartie2.setCible(joueur2, joueur1);
		PlateauGraphique fenetre1 = new PlateauGraphique(maPartie1);
		PlateauGraphique fenetre2 = new PlateauGraphique(maPartie2);
		int [][] plateauCourant ;

		//on gère les tours
		while(joueur1.aPerdu()==false && joueur2.aPerdu()==false){ // Tant que l'un ou l'autre n'a pas perdu
			System.out.println("\n ---------- \n" + joueur1.name + " : Vous tirez. Voici votre tableau de tir."); // On annonce le joueur

			plateauCourant = joueur2.getPlateauValeursZero();
			fenetre1.afficher(plateauCourant, "Joueur 1 tirez !");
			System.out.println("\n ---------- \n" + joueur2.name + " : Vous tirez. Voici votre tableau de tir."); // On annonce le joueur

			plateauCourant = joueur1.getPlateauValeursZero();
			fenetre2.afficher(plateauCourant, "Joueur 2 tirez !");
		}

		if(joueur1.aPerdu()==true){
			System.out.println(joueur1.name + " a perdu ! " + joueur2.name + " a gagné !");
		} else if(joueur2.aPerdu()==true){
			System.out.println(joueur2.name + " a perdu ! " + joueur1.name + " a gagné !");
		}

		System.out.println("Mode FENETRE stoppé.\n\n");  
	}
}
