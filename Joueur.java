/**
 * @author Vincent F et Thomas B
 */

import java.util.Scanner;

/**
 * Classe qui permet de demander au joueur des choses diverses. (Gestion du jeu "haut-niveau")
 */
public class Joueur {

	//Variables internes de la classe 
	int[] typeDeFlotte;
	
	/** Méthode qui demande le nombre de joueur voulu par le lanceur du jeu
	 * @return un int qui contient le nombre de joueurs choisi.
	 */
	public static int nombreDeJoueurs(){
		int nombreJoueurs = -1 ;

		// **** DEMANDE AU JOUEURS LA TYPE DU PLATEAU ****
		while(nombreJoueurs<2){ 
			System.out.println( "Combien de joueurs veulent jouer ? 2/3/4/5.. ");
			nombreJoueurs = demanderInt();
		}
		
		return nombreJoueurs;
	}

	/** Methode qui demande au joueur de s'identifier en lui demandant son nom.
	 * @param numeroJoueur : sert simplement à afficher le N° du joueur lors de la requête "Joueur x, quel est votre pseudo ? " 
	 * @return le pseudo du joueur sous forme de String.
	 */
	public static String identification(int numeroJoueur){
		String pseudo = "";
		Scanner sc = new Scanner(System.in);
		String answer = "";

		// **** DEMANDE AU JOUEUR DE S'IDENTIFIER ****
		while(pseudo.length()==0){ 
			System.out.println("Joueur" + numeroJoueur + ", quel est votre pseudo ? (15 caractères maximum) ");
			answer = sc.nextLine();
			if(answer.length()!=0){	
				if(answer.length()>15){
					pseudo = answer.substring(0, 15);
				} else {
					pseudo = answer;
				}
				System.out.println("Bienvenue " + pseudo);
			} 
		}
		return pseudo;
	} // FIN DE " identification " 

	/** Methode qui demande au joueur le type de plateau voulu. "quel plateau voulez vous ? "
	 * @return un int ayant pour valeur : 0 - carré // 1 - rond // n2 - triangle // n3 - rectangle
	 */
	public static int typeDePlateau(){
		int type = -1 ;

		// **** DEMANDE AU JOUEURS LA TYPE DU PLATEAU ****
		while(type!= 0 && type != 1 && type!=2 && type!=3 && type!=4){ 
			System.out.println("Joueurs : Sur quel type de plateau voulez-vous jouer ? 0/1/2/3 ");
			System.out.println("0 - carré \n1 - rond \n2 - triangle\n3 - rectangle\n4 - rectangle avec récifs");
			
			type = demanderInt();
		}
		
		return type;
	} 

	/** Méthode qui demande la taille d'un plateau rond (donc le rayon)"  quel taille voulez vous ? "
	 * @return Le rayon du plateau sous forme d'un INT.
	 */
	public static int taillePlateauRond(){
		int tailleX = 0;

		// **** DEMANDE AU JOUEURS LA TAILLE DU PLATEAU ET LE GENERE ****
		while(tailleX<=0){ 
			System.out.println( "Joueurs : Sur quelle taille de plateau voulez-vous jouer ? Rayon ? ");
			tailleX = demanderInt();
		}
		return tailleX;
	}

	/** Méthode qui demande la taille d'un plateau triangulaire (donc le coté) " quel taille voulez vous ? "
	 * @return Le coté du plateau sous forme d'un INT.
	 */
	public static int taillePlateauTriangle(){
		int tailleX = 0;
		
		// **** DEMANDE AU JOUEUR LA TAILLE DU PLATEAU TRIANGULAIRE VOULU ****
		while(tailleX<=0){ 
			System.out.println( "Joueurs : Sur quelle taille de plateau voulez-vous jouer ? Coté ? ");
			tailleX = demanderInt();
		}
		
		return tailleX;
	}

	
	/** Méthode qui demande la taille d'un plateau rectangulaire (donc les cotés): "quel taille voulez vous ? " 
	 * @return Le coté du plateau sous forme d'un INT. PROBLEME ON DEVRAIT EN RENVOYER 2 ...  NE PAS UTILISER CETTE CLASSE DONC =) 
	 */
	/*public static int taillePlateauRectangle(){
		int tailleX = 0;
		int tailleY = 0;

		while(tailleX<=0 || tailleY<=0){ 
			System.out.println( "Joueurs : Sur quelle taille de plateau voulez-vous jouer ? Hauteur ?");
			tailleX = demanderInt();

			System.out.println("Largeur ?");
			tailleY = demanderInt();
		}
		return tailleX;
	} // FIN DE " taillePlateauRectangle " PROBLEME ON DEVRAIT EN RENVOYER 2 ...  NE PAS UTILISER CETTE CLASSE DONC
	*/
	
	/** Méthode qui demande la taille d'un plateau carre (donc les cotés): "quel taille voulez vous ? " 
	 * @return Le coté du plateau sous forme d'un INT.
	 */
	public static int taillePlateauCarre(){
		int tailleX = 0;

		while(tailleX<=0){ 
			System.out.println( "Joueurs : Sur quelle taille de plateau voulez-vous jouer ? Cote ?");
			tailleX = demanderInt();
		}
		
		return tailleX;
	}

	/** Méthode qui demande si le joueur veut placer ses bateaux
	 * @param pseudo simplement utile pour dire, "Joueur 1" voulez vous placer vos bateaux ? 
	 * @return retourne un boolean qui vaut true si il veut placer ses bateaux
	 */
	public static boolean veutPlacerSesBateaux(String pseudo){
		boolean placerSesBateaux;

		// **** DEMANDE AU JOUEUR 1 LE PLACEMENT DE SES BATEAUX ****
		System.out.println(pseudo +": Voulez-vous placer vos bateaux ? O/n");
		placerSesBateaux = demanderOuiNon();

		return placerSesBateaux;
	}

	/** Méthode qui demande si les joueurs veulent choisir leur flotte // A NOTER : IL FAUDRAIT RAJOUTER UNE SECURITE POUR EVITER LES DEBILES QUI METTENT 40 BATEAUX SUR UN PLATEAU 4*4. LE PLACEMENT ALEATOIRE NE POURRA JAMAIS ABOUTIR ET ON AURA UNE BOUCLE INFINIE 
	 * @return retourne un boolean qui vaut true si il veut choisir sa flotte.
	 */
	public static boolean veutChoisirCompositionDeFlotte(){
		boolean veutChoisirFlotte;

		// **** DEMANDE AU JOUEUR "PSEUDO" LE PLACEMENT DE SES BATEAUX ****
		System.out.println("Voulez-vous composer la flotte de jeu ? O/n");
		veutChoisirFlotte = demanderOuiNon();

		return veutChoisirFlotte;
	} 

	/** Méthode qui demande au joueur "pseudo" le nombre de bateaux qu'il désire. Elle lui demande " "Joueur 1" combien voulez vous de bateaux ? " 
	 * @param nombreMaxBateaux : nombre passé en argument, et calculé en dehors de cette classe, qui fixe la limite à ne pas dépasser. Fixe donc la limite haute du choix du joueur.
	 * @return le nombre de bateau choisi par le joueur.
	 */
	public static int choixNombreDeBateaux(int nombreMaxBateaux){
		int nombreBateaux = -1;

		while(nombreBateaux <= 0 || nombreBateaux>nombreMaxBateaux){ // Boucle concernant le choix du nombre de bateaux.
			System.out.println("Combien voulez vous de bateau(x) ?");
			nombreBateaux = demanderInt();
		}
		
		return nombreBateaux;
	}

	/** Méthode à compléter, qui calcul le nombre de bateaux optimal de bateaux, selon la forme du plateau, en connaissant le nombre de bateaux maximal.
	 * @param type : nombre (int) correspondant au type du plateau
	 * @param nombreBateauxMax : nombre de bateau maximum calculé à l'extérieur.
	 * @return le nombre de bateau optimal sous forme de int. Note : il pourrait aussi retourner une liste de bateau avec leur taille.
	 */
	public static int calculNombreBateauxOptimal(Plateau plateau, int nombreBateauxMax){
		int nombreBateaux = 5;
		double ratio = 0.2;
		
		switch (plateau.getType()){
		case 1:
			nombreBateaux = (int)(plateau.compterCasesLibres()*ratio);
			break;
		case 2 :
			nombreBateaux = (int)(plateau.compterCasesLibres()*ratio);
			break;
		case 3 :
			nombreBateaux = (int)(plateau.compterCasesLibres()*ratio);
			break;
		default :
			nombreBateaux = (int)(plateau.compterCasesLibres()*ratio);
			break;
		}
		
		if(nombreBateaux>10){
			nombreBateaux = 10;
		} else if(nombreBateaux>nombreBateauxMax){
			nombreBateaux = nombreBateauxMax;
		}

		return nombreBateaux;
	} // Fin calculNombreBateauxOptimal


	/** Méthode qui renvois une liste de Taille de bateaux correcte, pour permettre que ça tienne dans le plateau.
	 * @param aVerifier : plateau sur lequel effectuer l'essai.
	 * @param listeTailleBateaux : liste des tailles de bateaux
	 * @return la liste modifiée pour qu'elle soit correcte.
	 */
	public void transformationEnlisteTailleBateauCorrecte(Plateau aVerifier,Joueur joueur){
		// **** ON VERIFIE QUE LE NOMBRE DE BATEAU ENTRE PAR L'UTILISATEUR EST COHERENT ***//
		int placeTotal = 0;
		int[] listeTailleBateauxRemplacement = new int[(joueur.getTypeDeFlotte().length)];
		int tailleNouveauTableau = 0;

		for(int i=0; i<joueur.typeDeFlotte.length ; i++){
			placeTotal += joueur.typeDeFlotte[i];
		}
/* A REVOIR
		if(calculNombreBateauxOptimal(aVerifier, joueur.typeDeFlotte.length)>placeTotal){
			boolean correct = true ;
			int placePriseParNouveauTableau = 0;
			for(int i=0; i<joueur.typeDeFlotte.length && placePriseParNouveauTableau < placeTotal && correct == true; i++){
				if(placePriseParNouveauTableau+joueur.typeDeFlotte[i]<placeTotal){
					placePriseParNouveauTableau += joueur.typeDeFlotte[i];
					tailleNouveauTableau++;
				} else if(placePriseParNouveauTableau+joueur.typeDeFlotte[i]>placeTotal && i==0){
					tailleNouveauTableau = 1;
					joueur.typeDeFlotte[0] = 2;
				} else {
					correct = false;
				}
			}

			listeTailleBateauxRemplacement = new int[tailleNouveauTableau];
			for(int i=0; i<joueur.typeDeFlotte.length && i<listeTailleBateauxRemplacement.length ; i++){
				listeTailleBateauxRemplacement[i] = joueur.typeDeFlotte[i];
			}
			
			joueur.typeDeFlotte = new int[listeTailleBateauxRemplacement.length];
			
			for(int i=0; i<joueur.typeDeFlotte.length && i<listeTailleBateauxRemplacement.length ; i++){
				joueur.typeDeFlotte[i] = listeTailleBateauxRemplacement[i];
			}
		}*/
		
		
	}
	
	/** Méthode qui gère les demandes du joueur quand à son choix de placer ses bateaux, et leur nombre. Tout est automatisé. 
	 * @param plateauDuJoueur : Plateau concerné par le placement des bateaux.
	 * @param nombreBateau : Nombre calculé en amont, qui fixe la limite haute du nombre de bateaux sur le plateau de jeu. (Suivant le type de plateau, le calcul devra être différent)
	 * @param nombreMaxDeBateaux : nombre maximum de bateaux que le joueur pourra choisir 
	 * @param tailleMaxBateau : taille maximum qu'un user pourra choisir.
	 */
	public void utilitairePlacementDesBateaux(Plateau plateauDuJoueur, int nombreBateau, int nombreMaxDeBateaux, int tailleMaxBateau){

		if(Joueur.veutPlacerSesBateaux(plateauDuJoueur.getName())){
			//LE JOUEUR VEUT PLACER SES BATEAUX
			System.out.println("Mise en route du programme de placement des bateaux ...");
			plateauDuJoueur.remplirManuellement(nombreBateau, this); // On le fait remplir manuellement.

		} else {
			//LE JOUEUR VEUT PAS PLACER SES BATEAUX
			System.out.println(" Mise en route du programme de placement automatique des bateaux ...");
			plateauDuJoueur.remplirAleatoirement(this); // On le fait remplir aléatoirement.
		}
	} 

	/** Méthode qui gère la demande utilisateur pour créer une flotte. Il demande le nombre de bateaux ovulu et il va demander la taille de chaque bateau voulu, en connaissant la taille maximal autorisée pour un bateau.  Il remplit le tableau de la classe "Joueur" nommé "typeDeFlotte" 
	 * @param tailleMaxBateau : taille maximum autorisée pour un bateau de la flotte
	 * @param nombreMaxBateau : va demande le nombre de bateau voulu à l'utilisateur, sans dépasser cette limite.
	 * @return le nombre de bateaux choisis par l'utilisateur
	 */
	public int utilitaireTypeDeFlotte(int tailleMaxBateau, int nombreMaxBateau){
		int tailleBateau = 0;
		int nbBateau = choixNombreDeBateaux(nombreMaxBateau); // On demande aux joueurs combien de bateaux ils veulent que leur flotte soit composée. Le nombre de bateau SERA INFERIEUR AU nombre de bateau maximum autorisé ! 
		this.typeDeFlotte = new int[nbBateau]; // On inialise la liste des tailles des bateaux de la longueur demandé juste avant.

		System.out.println(" **** Initilisation du programme de création de flotte ****");
		System.out.println("Vous allez choisir un par un chaque type de bateau en rentrant sa taille.");

		for (int i=0; i<nbBateau ; i++){

			if(i==0){ // On gère le premier bateau à part. C'est le bateau 'maitre'/Tireur	
				//System.out.println("Le premier bateau est votre bateau tireur. C'est le bateau principal de votre jeu. Si vous le perdez, vous avez perdu.");
			}
			
			while(tailleBateau <= 0 || tailleBateau>tailleMaxBateau){ // Boucle concernant le choix de l'utilisateur.
				System.out.println("Quelle taille de bateau désirez vous pour votre bateau N°" + (i+1) + " ? ");
				tailleBateau = demanderInt(); // On récupère la taille du bateau entrée par l'utilisateur
			}

			this.typeDeFlotte[i]=tailleBateau; // On range la taille du bateau dans le tableau de la classe joueur.
			tailleBateau = 0;
		}
		return nbBateau; // On renvois le nombre de bateau choisi par l'utilisateur au début de la méthode.
	}// Fin utilitaireTypeDeFlotte ---- POSSIBILITE D'OPTIMISATION : EN RETIRANT LE NBBATEAU, ET EN SE SERVANT D typeDEFlotte.Length.

	/** Méthode qui remplit la liste "typeDeFlotte" de la classe "Joueur" par la flotte par défaut. Composée de 5 bateaux : 5 4 3 2 2 1 ..1
	 * @param nombreDeBateau : nombre de bateau qui vont composer la flotte par défaut.
	 */
	public void utilitaireFlotteParDefaut(int nombreDeBateau){

		int tailleBateau = 0;
		this.typeDeFlotte = new int[nombreDeBateau];

		for (int i=0; i<nombreDeBateau ; i++){
			switch(i){ // On définit la taille du bateau suivant le i de la boucle for principal.
			case 0 :
				tailleBateau = 5;
				break;
			case 1 :
				tailleBateau = 4;
				break;
			case 2 :
				tailleBateau = 3;
				break;
			case 3 :
				tailleBateau = 2;
				break;
			case 4 :
				tailleBateau = 2;
				break;
			default : 
				tailleBateau = 1;
				break;
			}

			this.typeDeFlotte[i]=tailleBateau; // On range la taille du bateau dans le tableau de la classe joueur.
			tailleBateau =0;
		}
	} // FIN utilitaireFlotteParDefaut

	/** Méthode standard pour demander des coordonnes à l'utilisateur dans le respect des limitations posées par le plateau passé en argument. (On évite les OutOfBoundException)
	 * @param plateau : Plateau passé en argument, comme limitateur en abscisse et en ordonnées pour les coordonnées acceptées.
	 * @return un tableau de deux entiers, avec l'ordonnée en [0] et l'abscisse en [1]
	 */
	public static int[] choixCoordonnes(Plateau plateau){
		int[] coordonnes = new int[2];
		String answer = "";
		String cordA ;
		String cordB ; 
		boolean correct = false;
		Scanner sc = new Scanner(System.in);

		coordonnes[0]=-1;
		coordonnes[1]=-1;

		while(coordonnes[0]>=plateau.getPlateauValeurs().length || coordonnes[0]<0 || coordonnes[1]<0 || coordonnes[1]>=plateau.getPlateauValeurs()[0].length || correct==false){ // Boucle concernant le choix du nombre de bateaux.
			System.out.println("Quelles coordonnées choisissez-vous ? (Forme : 'A 12' ou '1 1')");
			answer = sc.nextLine();
			correct = true;
			int indexEspace = 0;

			if(answer.length()>=3){		//Sécurité anti-débile qui appui sur entrée sans rien mettre	//&& correct==true
				for(int i=0; answer.charAt(i)!=' ' && i<(answer.length()-1) ; i++){
					if(answer.charAt(i+1)==' '){
						indexEspace = i+1; // On choppe la valeur de l'espace.
						correct = true;
					} else {
						correct = false;
					}
				}
				for(int i= 0; correct==true && i<answer.length() ; i++){
					if(i<indexEspace && ((int)(answer.charAt(i))<65 || (int)(answer.charAt(i))>90) && ((int)(answer.charAt(i))<97 || (int)(answer.charAt(i))>122) && ((int)(answer.charAt(i))<48 || (int)(answer.charAt(i))>57)){
						correct = false; // Tant qu'on est pas dans la zone  a....z ou A....Z ou 1...9 on n'en veut pas.
					} 
					if(i>indexEspace && ((int)(answer.charAt(i))<48 || (int)(answer.charAt(i))>57)){
						correct = false; // Tant qu'on est pas dans la zone 1...9 on n'en veut pas.
					}
				}

				if(correct==true){ // Si les données sont correctes
					cordA = answer.substring(0, indexEspace);	//On pose une variable tampon
					cordB = answer.substring(indexEspace+1,answer.length()); //On pose une deuxième variable tampon.

					if(((int)(cordA.charAt(0))>=65 && (int)(cordA.charAt(0))<=90)){
						coordonnes[0] = ((int)(cordA.charAt(0))-65); // Si c'est une majuscule on enlève la valeur d'un A majuscule pour retrouver des coordonnées valables.
					} else if (((int)(cordA.charAt(0))>=97 && (int)(cordA.charAt(0))<=122)){
						coordonnes[0] = ((int)(cordA.charAt(0))-97); // Si c'est une minuscule on enlève la valeur d'un a minuscule pour retrouver des coordonnées valables.
					} else {
						coordonnes[0] = Integer.parseInt(cordA);
					}
					coordonnes[1] = Integer.parseInt(cordB);
				}
			}
		}
		return coordonnes;
		
	}// FIN choixCoordonnes



	/** Méthode qui demande simplement si le joueur veut que son bateau soit horizontal.
	 * @return renvois un boolean qui vaut "true" si l'utilisateur veut que le bateau soit horizontal, et "false" si il veut qu'il soit vertical.
	 */
	public static boolean choixOrientation(){
		boolean horizontal = false;

		// **** DEMANDE AU JOUEUR Si il veut un bateau horizontal****
		System.out.println("Voulez-vous que ce bateau soit horizontal ? O/n");
		horizontal = demanderOuiNon();

		return horizontal;
	}


	/** Méthode qui demande au joueur la taille du bateau qu'il désire.
	 * @param tailleMax : nombre qui fixe la taille maximal du bateau que le joueur peut choisir
	 * @return la taille du bateau, choisie par le joueur.
	 */
	public static int choixTailleBateau(int tailleMax){
		int tailleBateau =0;

		while(tailleBateau <= 0 && tailleBateau>tailleMax){ // Boucle concernant le choix du nombre de bateaux.
			System.out.println("Quelle taille de bateau désirez vous ? ");
			tailleBateau = demanderInt();
		}
		
		return tailleBateau;
	}

	 
	/** Méthode qui demande au joueur les coordonnées du bateau.
	 * @param pseudo : permet de préciser à quel joueur on demande des coordonnées pour son bateau.
	 * @param nombreMaxBateaux : nombre passé en argument, et calculé en dehors de cette classe, qui fixe la limite à ne pas dépasser. Fixe donc la limite haute du choix du joueur.
	 * @return le nombre de bateau choisi par le joueur.
	 */
	/* CETTE METHOD NE SERT A RIEN !
	public static int choixCoordonnéesBateau(String pseudo, int nombreMaxBateaux){
		int nombreBateaux =0;

		while(nombreBateaux <= 0 && nombreBateaux>nombreMaxBateaux){ // Boucle concernant le choix du nombre de bateaux.
			System.out.println(pseudo +": Combien voulez vous de bateau ?");
			nombreBateaux = demanderInt();
		}
		
		return nombreBateaux;
	} // FIN DE " choixCoordonnéesBateau "*/


	/** Méthode qui gère sur qui veut tirer un joueur. Il présente la liste des joueurs et renvois le plateau correspondant choisi par l'utilisateur.
	 * @param tireur : plateau qui va tirer sur un autre joueur de la liste ( c'est pour l'exclure de la liste lors du choix de la cible)
	 * @param listeJoueurs : un liste de type Plateau[] que la méthode va présenter à l'utilisateur
	 * @return le plateau du joueur que le joueur a selectionné.
	 */
	public static Plateau surQuiVoulezVousTirer(Plateau tireur, Plateau[] listeJoueurs){
		Plateau cible = null;
		int numeroTireur = -1;
		int choix = -1;

		System.out.println("Sur qui voulez-vous tirer ? ");
		for(int i= 0; i <listeJoueurs.length ; i++){
			if(tireur != listeJoueurs[i]){
				System.out.println( i + " - " + listeJoueurs[i].name);	
			} else {
				numeroTireur = i;
			}
		}

		while(choix < 0 || choix>=listeJoueurs.length || choix == numeroTireur){ // Boucle concernant le choix du nombre de bateaux.
			if(choix == numeroTireur){
				System.out.println("Vous ne pouvez pas tirer sur vous même ! Recommencez :");
			}
			choix = demanderInt();
		}
		
		System.out.println("Vous avez choisi de tirer sur : " + listeJoueurs[choix].name);
		cible = listeJoueurs[choix];

		return cible;
	} // FIN surQuiVoulezVousTirer

	/** Méthode qui récupère demande au joueur d'entrer des coordonnées, vérifie si cette case n'a pas déjà subit un tir, ou si la case est interdite, tir et renvois la description du résultat du tir.
	 * @param joueur1 : joueur attaquant à qui sera proposé de tirer.
	 * @param joueur2 : joueur cible qui sera touché.
	 */
	public static void recupCoordonnesVerifierTirer(Plateau joueur1, Plateau joueur2){
		boolean correct = false;
		int[] coordonnes = new int[2];	

		while(correct==false){
			coordonnes = Joueur.choixCoordonnes(joueur2);
			if(joueur2.dejaSubiTir(coordonnes[0], coordonnes[1])==false && Joueur.scannerCaseInterdite(joueur2, coordonnes[0], coordonnes[1])==false){
				correct = true;
			} else if (Joueur.scannerCaseInterdite(joueur2, coordonnes[0], coordonnes[1])==true){
				System.out.println("Cette case est hors du plateau de jeu !");
			} else {
				System.out.println("Cette case a déjà subit un tir !");
			}
		}

		joueur1.tirerJoueur(coordonnes, joueur2);
		Joueur.reponseAuTir(joueur2, coordonnes[0], coordonnes[1]);
	} //FIN recupCoordonnesVerifierTirer

	/** Méthode qui fournit une réponse au tir sur le joueur passé en argument, selon les coordonnées du tir. Renvois "plouf, chtong, ou touché, voir coulé"
	 * @param joueur : joueur cible qui a reçu le tir.
	 * @param x : ordonnée du tir.
	 * @param y : abscisse du tir.
	 */
	public static void reponseAuTir(Plateau joueur, int x, int y){
		if(Joueur.scannerCasePresenceBateau(joueur, x, y)==true){
			System.out.print("Vous avec touché un bateau !\n");
			if(joueur.bateaux.getListeBat()[joueur.numeroBateauPresent(x, y)-1].isAlive() == false){
				System.out.print("\b\b et vous l'avez coulé !");
			}
		} else if(Joueur.scannerCasePresenceRecif(joueur, x, y)){
			System.out.println("Chtong, qu'est-ce que c'était ?");
		} else {
			System.out.println("Plouf");
		}
	} // FIN reponseAuTir

	/** Méthode simple qui regarde si un bateau est présent sur la case du plateau du joueur, aux coordonnées indiquées.
	 * @param joueur : plateau du joueur à scanner.
	 * @param x : ordonnée du scan.
	 * @param y : absisse du scan.
	 * @return un boolean qui vaut true si un bateau est présent, false si il n'y a pas de bateau. (si plateau[x][y][0] supérieur à 0 c'est true.)
	 */
	public static boolean scannerCasePresenceBateau(Plateau joueur, int x, int y){
		boolean presenceBateau = false;

		if(joueur.getPlateauValeurs()[x][y][0]>0){
			presenceBateau=true;
		}

		return presenceBateau;
	} // FIN scannerCasePresenceBateau

	/** Méthode simple qui regarde si un récif est présent sur la case du plateau du joueur, aux coordonnées indiquées.
	 * @param joueur : plateau du joueur à scanner.
	 * @param x : ordonnée du scan.
	 * @param y : absisse du scan.
	 * @return un boolean qui vaut true si un récif est présent, false si il n'y a pas de récif. (si plateau[x][y][0] = (-1) c'est true.)
	 */
	public static boolean scannerCasePresenceRecif(Plateau joueur, int x, int y){
		boolean presenceRecif = false;

		if(joueur.getPlateauValeurs()[x][y][0]==(-1)){
			presenceRecif=true;
		}

		return presenceRecif;
	} // FIN scannerCasePresenceRecif

	/** Méthode simple qui regarde si la case est interdite du plateau du joueur, aux coordonnées indiquées.
	 * @param joueur : plateau du joueur à scanner.
	 * @param x : ordonnée du scan.
	 * @param y : absisse du scan.
	 * @return un boolean qui vaut true si un la case est interdite, false si il la case n'est pas interdite.. (si plateau[x][y][0] = (-2) c'est true.)
	 */
	public static boolean scannerCaseInterdite(Plateau joueur, int x, int y){
		boolean presenceInterdite = false;

		if(joueur.getPlateauValeurs()[x][y][0]==(-2)){
			presenceInterdite=true;
		}

		return presenceInterdite;
	} // FIN scannerCaseInterdite

	public static int demanderInt(){
		int resultat = 0;
		String answer = "";
		boolean correct = false;
		Scanner sc = new Scanner(System.in);
		
		while(correct==false){
			answer = sc.nextLine();
			if(answer.length()!=0){		//Sécurité anti-débile qui appui sur entrée sans rien mettre
				try{
					resultat = Integer.parseInt(answer);
					correct = true;
				} catch (NumberFormatException e){
					correct = false;
				}
			}
		}
		
		return resultat;
	}
	
		
	public static boolean demanderOuiNon(){
		boolean resultat = false;
		
		char choix = demanderLettre();
		if(choix=='O'){
			resultat = true;
		} else {
			resultat = false;
		}
		return resultat;
	}
	
	public static char demanderLettre(){
		char resultat = ' ';
		String answer = "";
		boolean correct = false;
		Scanner sc = new Scanner(System.in);
		
		while(correct==false){
			answer = sc.nextLine();
			if(answer.length()!=0){		//Sécurité anti-débile qui appui sur entrée sans rien mettre
					resultat = answer.charAt(0);
					correct = true;
			}
			if(((int)(resultat)<65 || (int)(resultat)>90) && ((int)(resultat)<97 || (int)(resultat)>122)){
				correct = false;
			}
		}
		
		return resultat;
	}
	
	
	// ********** LES GETTERS ET SETTERS ********** //
	
	/**
	 * @return the typeDeFlotte
	 */
	public int[] getTypeDeFlotte() {
		return typeDeFlotte;
	}

	/**
	 * @param typeDeFlotte le typeDeFlotte à "setter"
	 */
	public void setTypeDeFlotte(int[] typeDeFlotte) {
		this.typeDeFlotte = typeDeFlotte;
	}
}