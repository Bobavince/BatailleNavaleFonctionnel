/**
 * @author Vincent F et Thomas B
 * Classe de gestion des choses qu'on demande au joueur
 */
import java.util.Scanner;


/**
 * Classe qui permet de demander au joueur des choses diverses. (Gestion du jeu "haut-niveau")
 */
public class Joueur {

	//Variables internes de la classe 
	int[] typeDeFlotte;

	/** Methode qui demande au joueur de s'identifier en lui demandant son nom.
	 * @param numeroJoueur : sert simplement � afficher le N� du joueur lors de la requ�te "Joueur x, quel est votre pseudo ? " 
	 * @return le pseudo du joueur sous forme de String.
	 */
	public static String identification(int numeroJoueur){
		String pseudo = "";
		Scanner sc = new Scanner(System.in);
		String answer = "";

		// **** DEMANDE AU JOUEUR DE S'IDENTIFIER ****
		while(pseudo.length()==0){ 
			System.out.println("Joueur" + numeroJoueur + ", quel est votre pseudo ? (15 caract�res maximum) ");
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

	/** Methode qui demande au joueur le type de plateau voulu. 
	 * @param pseudo1 simplement utile pour dire, "Joueur 1", "Joueur 2" quel plateau voulez vous ? 
	 * @param pseudo2 de m�me.
	 * @return un int ayant pour valeur : 0 - carr� // 1 - rond // n2 - triangle // n3 - rectangle
	 */
	public static int typeDePlateau(String pseudo1, String pseudo2){
		int type = -1 ;
		Scanner sc = new Scanner(System.in);
		String answer = "";

		// **** DEMANDE AU JOUEURS LA TYPE DU PLATEAU ****
		while(type!= 0 && type != 1 && type!=2 && type!=3){ 
			System.out.println( pseudo1 + " , "+ pseudo2 + " : Sur quel type de plateau voulez-vous jouer ? 0/1/2/3 ");
			System.out.println("0 - carr� \n1 - rond \n2 - triangle\n3 - rectangle");
			answer = sc.nextLine();
			if(answer.length()!=0){
				if(((int)(answer.charAt(0))>=48 && ((int)(answer.charAt(0))<48+4))){
					type = ((int)(answer.charAt(0))-48); //Correspond � la r�presentation en ASCII des chiffres.
				}
			}
		}
		return type;
	} // FIN DE " typeDePlateau " 

	/** M�thode qui demande la taille d'un plateau rond (donc le rayon)
	 * @param pseudo1 simplement utile pour dire, "Joueur 1", "Joueur 2" quel taille voulez vous ? 
	 * @param pseudo2 de m�me.
	 * @return Le rayon du plateau sous forme d'un INT.
	 */
	public static int taillePlateauRond(String pseudo1, String pseudo2){
		int tailleX = 0;
		String answer = "";
		Scanner sc = new Scanner(System.in);

		// **** DEMANDE AU JOUEURS LA TAILLE DU PLATEAU ET LE GENERE ****
		while(tailleX<=0){ 
			System.out.println( pseudo1 + " , "+ pseudo2 + " Sur quelle taille de plateau voulez-vous jouer ? Rayon ? ");
			answer = sc.nextLine();
			if(answer.length()!=0){
				tailleX = Integer.parseInt(answer);
			}
		}
		return tailleX;
	} // FIN DE " taillePlateauRond "

	/** M�thode qui demande la taille d'un plateau triangulaire (donc le cot�)
	 * @param pseudo1 simplement utile pour dire, "Joueur 1", "Joueur 2" quel taille voulez vous ? 
	 * @param pseudo2 de m�me.
	 * @return Le cot� du plateau sous forme d'un INT.
	 */
	public static int taillePlateauTriangle(String pseudo1, String pseudo2){
		int tailleX = 0;
		String answer = "";
		Scanner sc = new Scanner(System.in);

		while(tailleX<=0){ 
			System.out.println( pseudo1 + " , " + pseudo2 + "Sur quelle taille de plateau voulez-vous jouer ? Cot� ? ");
			answer = sc.nextLine();
			if(answer.length()!=0){
				tailleX = Integer.parseInt(answer);
			}
		}
		return tailleX;
	} // FIN DE " taillePlateauTriangle "

	/** M�thode qui demande la taille d'un plateau rectangulaire (donc les cot�s)
	 * @param pseudo1 simplement utile pour dire, "Joueur 1", "Joueur 2" quel taille voulez vous ? 
	 * @param pseudo2 de m�me.
	 * @return Le cot� du plateau sous forme d'un INT. PROBLEME ON DEVRAIT EN RENVOYER 2 ...  NE PAS UTILISER CETTE CLASSE DONC =) 
	 */
	public static int taillePlateauRectangle(String pseudo1, String pseudo2){
		int tailleX = 0;
		int tailleY = 0;
		String answer ="";
		Scanner sc = new Scanner(System.in);

		while(tailleX<=0 || tailleY<=0){ 
			answer ="";
			System.out.println( pseudo1 + " , " + pseudo2 + "Sur quelle taille de plateau voulez-vous jouer ? Hauteur ?");
			answer = sc.nextLine();
			if(answer.length()!=0){
				tailleX = Integer.parseInt(answer);
			}
			answer ="";
			System.out.println("Largeur ?");
			answer = sc.nextLine();
			if(answer.length()!=0){
				tailleY = Integer.parseInt(answer);
			}
		}
		return tailleX;
	} // FIN DE " taillePlateauRectangle "


	/** M�thode qui demande si le joueur veut placer ses bateaux
	 * @param pseudo simplement utile pour dire, "Joueur 1" voulez vous placer vos bateaux ? 
	 * @return retourne un boolean qui vaut true si il veut placer ses bateaux
	 */
	public static boolean veutPlacerSesBateaux(String pseudo){
		char choix = ' ';
		String answer = "";
		boolean placerSesBateaux;
		Scanner sc = new Scanner(System.in);

		// **** DEMANDE AU JOUEUR 1 LE PLACEMENT DE SES BATEAUX ****
		while(choix ==' '){ // Boucle concernant le choix de placer ses bateaux.
			System.out.println(pseudo +": Voulez-vous placer vos bateaux ? O/n");
			answer = sc.nextLine();
			if(answer.length()!=0){		//S�curit� anti-d�bile qui appui sur entr�e sans rien mettre
				choix = answer.charAt(0);
			}  
		}
		if(choix=='O'){
			placerSesBateaux = true;
		} else {
			placerSesBateaux = false;
		}

		return placerSesBateaux;
	} // FIN DE " veutPlacerSesBateaux " 


	/** M�thode qui demande si les joueurs veulent choisir leur flotte // A NOTER : IL FAUDRAIT RAJOUTER UNE SECURITE POUR EVITER LES DEBILES QUI METTENT 40 BATEAUX SUR UN PLATEAU 4*4. LE PLACEMENT ALEATOIRE NE POURRA JAMAIS ABOUTIR ET ON AURA UNE BOUCLE INFINIE 
	 * @return retourne un boolean qui vaut true si il veut choisir sa flotte.
	 */
	public static boolean veutChoisirCompositionDeFlotte(){
		char choix = ' ';
		String answer = "";
		boolean veutChoisirFlotte;
		Scanner sc = new Scanner(System.in);

		// **** DEMANDE AU JOUEUR "PSEUDO" LE PLACEMENT DE SES BATEAUX ****
		while(choix ==' '){ // Boucle concernant le choix de placer ses bateaux.
			System.out.println(" Voulez-vous composer la flotte de jeu ? O/n");
			answer = sc.nextLine();
			if(answer.length()!=0){		//S�curit� anti-d�bile qui appui sur entr�e sans rien mettre
				choix = answer.charAt(0);
			}  
		}
		if(choix=='O'){
			veutChoisirFlotte = true;
		} else {
			veutChoisirFlotte = false;
		}
		return veutChoisirFlotte;
	} // FIN DE " veutChoisirCompositionDeFlotte " 


	/** M�thode qui demande au joueur "pseudo" le nombre de bateaux qu'il d�sire.
	 * @param pseudo : pseudo du joueur, juste pour lui demander " "Joueur 1" combien voulez vous de bateaux ? " 
	 * @param nombreMaxBateaux : nombre pass� en argument, et calcul� en dehors de cette classe, qui fixe la limite � ne pas d�passer. Fixe donc la limite haute du choix du joueur.
	 * @return le nombre de bateau choisi par le joueur.
	 */
	public static int choixNombreDeBateaux(int nombreMaxBateaux){
		String answer = "";
		int nombreBateaux = -1;
		Scanner sc = new Scanner(System.in);

		while(nombreBateaux <= 0 || nombreBateaux>nombreMaxBateaux){ // Boucle concernant le choix du nombre de bateaux.
			System.out.println("Combien voulez vous de bateau(x) ?");
			answer = sc.nextLine();
			if(answer.length()!=0){		//S�curit� anti-d�bile qui appui sur entr�e sans rien mettre
				nombreBateaux = Integer.parseInt(answer);
			}
		}
		return nombreBateaux;
	} // FIN DE " choixNombreDeBateaux " 

	public static int calculNombreBateauxOptimal(int type, int nombreBateauxMax){
		int nombreBateaux = 5;

		switch (type){
		case 1:
			//rond : calcul le nombre optimal de bateauxx pour un rond
			break;
		case 2 :
			//triangle : calcul le nombre optimal de bateaux pour un triangle
			break;
		case 3 :
			//rectangle : calcul le nombre optimal de bateau pour un rectangle
			break;
		default :
			//carr� : calcul le nombe optimal de bateau pour un carr�
			break;
		}

		return nombreBateaux;
	}

	/** M�thode qui g�re les demandes du joueur quand � son choix de placer ses bateaux, et leur nombre. Tout est automatis�. 
	 * @param plateauDuJoueur : Plateau concern� par le placement des bateaux.
	 * @param nombreBateauMax : Nombre calcul� en amont, qui fixe la limite haute du nombre de bateaux sur le plateau de jeu. (Suivant le type de plateau, le calcul devra �tre diff�rent)
	 * @return Le nombre de bateaux du joueur, choisi par lui, ou par d�faut : 5.
	 */
	public void utilitairePlacementDesBateaux(Plateau plateauDuJoueur, int nombreBateau, int nombreMaxDeBateaux, int tailleMaxBateau){

		if(Joueur.veutPlacerSesBateaux(plateauDuJoueur.name)){
			//LE JOUEUR VEUT PLACER SES BATEAUX
			System.out.println(" Mise en route du programme de placement des bateaux ...");
			plateauDuJoueur.remplirManuellement(nombreBateau, this.typeDeFlotte);

		} else {
			//LE JOUEUR VEUT PAS PLACER SES BATEAUX
			System.out.println(" Mise en route du programme de placement automatique des bateaux ...");
			plateauDuJoueur.remplirAleatoirement(this.typeDeFlotte);
		}

	} // FIN DE " utilitairePlacementDesBateaux "


	public int utilitaireTypeDeFlotte(int tailleMaxBateau, int nombreMaxBateau){
		String answer = "";
		int tailleBateau = 0;
		int nbBateau = choixNombreDeBateaux(nombreMaxBateau); // On demande aux joueurs combien de bateaux ils veulent que leur flotte soit compos�e. Le nombre de bateau SERA INFERIEUR AU nombre de bateau maximum autoris� ! 
		this.typeDeFlotte = new int[nbBateau];
		Scanner sc = new Scanner(System.in);


		System.out.println(" **** Initilisation du programme de cr�ation de flotte ****");
		System.out.println(" Vous allez choisir un par un chaque type de bateau en rentrant leur taille.");

		for (int i=0; i<nbBateau ; i++){


			if(i==0){ // On g�re le premier bateau � part. C'est le bateau 'maitre'/Tireur	
				System.out.println("Le premier bateau est votre bateau tireur. C'est le bateau principal de votre jeu. Si vous le perdez, vous avez perdu.");

				while(tailleBateau <= 0 || tailleBateau>tailleMaxBateau){ // Boucle concernant le choix de l'utilisateur.
					System.out.println(" Quelle taille de bateau d�sirez vous pour votre bateau N�" + (i+1) + " ? ");
					answer = sc.nextLine();
					if(answer.length()!=0){		//S�curit� anti-d�bile qui appui sur entr�e sans rien mettre
						tailleBateau = Integer.parseInt(answer);
					}
				}

				this.typeDeFlotte[i]=tailleBateau; // On range la taille du bateau dans le tableau de la classe joueur.
				tailleBateau =0;

			} else { // Les autres bateaux

				while(tailleBateau <= 0 || tailleBateau>tailleMaxBateau){ // Boucle concernant le choix de l'utilisateur.
					System.out.println(" Quelle taille de bateau d�sirez vous pour votre bateau N�" + (i+1) + " ? ");
					answer = sc.nextLine();
					if(answer.length()!=0){		//S�curit� anti-d�bile qui appui sur entr�e sans rien mettre
						tailleBateau = Integer.parseInt(answer);
					}
				}
				this.typeDeFlotte[i]=tailleBateau; // On range la taille du bateau dans le tableau de la classe joueur.
				tailleBateau =0;
			}

		}
		return nbBateau;
	}


	public void utilitaireFlotteParDefaut(int nombreDeBateau){

		int tailleBateau = 0;
		this.typeDeFlotte = new int[nombreDeBateau];

		for (int i=0; i<nombreDeBateau ; i++){
			switch(i){ // On d�finit la taille du bateau suivant le i de la boucle for principal.
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

	}

	public static int[] choixCoordonnes(Plateau plateau){
		int[] coordonnes = new int[2];
		String answer = "";
		String cordA ;
		String cordB ; 
		boolean correct = false;
		Scanner sc = new Scanner(System.in);

		coordonnes[0]=-1;
		coordonnes[1]=-1;

		while(coordonnes[0]>=plateau.plateauValeurs.length || coordonnes[0]<0 || coordonnes[1]<0 || coordonnes[1]>=plateau.plateauValeurs[0].length || correct==false){ // Boucle concernant le choix du nombre de bateaux.
			System.out.println(" Quelles coordonnes choisissez-vous ? (Forme : 'A 12' ou '1 1')");
			answer = sc.nextLine();
			correct = true;
			int indexEspace = 0;

			if(answer.length()>=3 && correct==true){		//S�curit� anti-d�bile qui appui sur entr�e sans rien mettre	
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

				if(correct==true){ // Si les donn�es sont correctes
					cordA = answer.substring(0, indexEspace);	//On pose une variable tampon
					cordB = answer.substring(indexEspace+1,answer.length()); //On pose une deuxi�me variable tampon.
					if(((int)(cordA.charAt(0))>65 || (int)(cordA.charAt(0))<90)){
						coordonnes[0] = ((int)(cordA.charAt(0))-65); // Si c'est une majuscule on enl�ve la valeur d'un A majuscule pour retrouver des coordonn�es valables.
					} else if (((int)(cordA.charAt(0))>97 || (int)(cordA.charAt(0))<122)){
						coordonnes[0] = ((int)(cordA.charAt(0))-97); // Si c'est une minuscule on enl�ve la valeur d'un a minuscule pour retrouver des coordonn�es valables.
					} else {
						coordonnes[0] = Integer.parseInt(cordA);
					}
					coordonnes[1] = Integer.parseInt(cordB);
				}
			}
		}

		return coordonnes;
	}


	public static boolean choixOrientation(){
		boolean horizontal = false;
		char choix = ' ';
		String answer = "";
		Scanner sc = new Scanner(System.in);

		// **** DEMANDE AU JOUEUR Si il veut un bateau horizontal****
		while(choix ==' '){ // Boucle concernant le choix de placer ses bateaux.
			System.out.println(" Voulez-vous que ce bateau soit horizontal ? O/n");
			answer = sc.nextLine();
			if(answer.length()!=0){		//S�curit� anti-d�bile qui appui sur entr�e sans rien mettre
				choix = answer.charAt(0);
			}  
		}
		if(choix=='O'){
			horizontal = true;
		} else {
			horizontal = false;
		}

		return horizontal;
	}

	/** M�thode qui demande au joueur la taille du bateau qu'il d�sire.
	 * @param tailleMax : nombre qui fixe la taille maximal du bateau que le joueur peut choisir
	 * @return la taille du bateau, choisie par le joueur.
	 */
	public static int choixTailleBateau(int tailleMax){
		String answer = "";
		int tailleBateau =0;
		Scanner sc = new Scanner(System.in);

		while(tailleBateau <= 0 && tailleBateau>tailleMax){ // Boucle concernant le choix du nombre de bateaux.
			System.out.println(" Quelle taille de bateau d�sirez vous ? ");
			answer = sc.nextLine();
			if(answer.length()!=0){		//S�curit� anti-d�bile qui appui sur entr�e sans rien mettre
				tailleBateau = Integer.parseInt(answer);
			}
		}
		return tailleBateau;
	} // FIN DE " choixTailleBateau "

	/** M�thode qui demande au joueur les coordonn�es du bateau.
	 * @param nombreMaxBateaux : nombre pass� en argument, et calcul� en dehors de cette classe, qui fixe la limite � ne pas d�passer. Fixe donc la limite haute du choix du joueur.
	 * @return le nombre de bateau choisi par le joueur.
	 */
	public static int choixCoordonn�esBateau(String pseudo, int nombreMaxBateaux){
		char choix = ' ';
		String answer = "";
		int nombreBateaux =0;
		Scanner sc = new Scanner(System.in);

		answer = "";
		while(nombreBateaux <= 0 && nombreBateaux>nombreMaxBateaux){ // Boucle concernant le choix du nombre de bateaux.
			System.out.println(pseudo +": Combien voulez vous de bateau ?");
			answer = sc.nextLine();
			if(answer.length()!=0){		//S�curit� anti-d�bile qui appui sur entr�e sans rien mettre
				nombreBateaux = Integer.parseInt(answer);
			}
		}
		return nombreBateaux;
	} // FIN DE " choixCoordonn�esBateau "

	public static Plateau surQuiVoulezVousTirer(Plateau[] listeJoueurs){
		Plateau cible = null;
		String answer = "";
		int choix =0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Sur qui voulez-vous tirer ? ");
		for(int i= 0; i <listeJoueurs.length ; i++){
			System.out.println( i + " - " + listeJoueurs[i].name);
		}

		while(choix <= 0 && choix>listeJoueurs.length){ // Boucle concernant le choix du nombre de bateaux.
			answer = sc.nextLine();
			if(answer.length()!=0){		//S�curit� anti-d�bile qui appui sur entr�e sans rien mettre
				choix = Integer.parseInt(answer);
			}
		}
		cible = listeJoueurs[choix];
		
		return cible;
	}
	
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
				System.out.println("Cette case a d�j� subit un tir !");
			}
		}
		joueur1.tirerJoueur(coordonnes, joueur2);
		Joueur.reponseAuTir(joueur2, coordonnes[0], coordonnes[1]);
		
	}
	
	public static void reponseAuTir(Plateau joueur, int x, int y){
		if(Joueur.scannerCasePresenceBateau(joueur, x, y)==true){
			System.out.print("Vous avec touch� un bateau !\n");
			if(joueur.bateaux.listeBat[joueur.numeroBateauPresent(x, y)].alive == false){
				System.out.print("\b\b et vous l'avez coul� !");
			}
		} else if(Joueur.scannerCasePresenceRecif(joueur, x, y)){
			System.out.println("Chtong, qu'est-ce que c'�tait ?");
		} else {
			System.out.println("Plouf");
		}
	}
	
	public static boolean scannerCasePresenceBateau(Plateau joueur2, int x, int y){
		boolean presenceBateau = false;
		
		if(joueur2.plateauValeurs[x][y][0]>0){
			presenceBateau=true;
		}
		
		return presenceBateau;
	}
	
	public static boolean scannerCasePresenceRecif(Plateau joueur2, int x, int y){
		boolean presenceRecif = false;
		
		if(joueur2.plateauValeurs[x][y][0]==(-1)){
			presenceRecif=true;
		}
		
		return presenceRecif;
	}
	
	public static boolean scannerCaseInterdite(Plateau joueur2, int x, int y){
		boolean presenceInterdite = false;
		
		if(joueur2.plateauValeurs[x][y][0]==(-2)){
			presenceInterdite=true;
		}
		
		return presenceInterdite;
	}
}


