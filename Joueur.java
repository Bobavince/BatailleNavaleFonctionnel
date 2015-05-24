/**
 * @author Vincent F et Thomas B
 * Classe de gestion des choses qu'on demande au joueur
 */
import java.util.Scanner;


/**
 * Classe qui permet de demander au joueur des choses diverses. (Gestion du jeu "haut-niveau")
 */
public class Joueur {

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

	/** Methode qui demande au joueur le type de plateau voulu. 
	 * @param pseudo1 simplement utile pour dire, "Joueur 1", "Joueur 2" quel plateau voulez vous ? 
	 * @param pseudo2 de même.
	 * @return un int ayant pour valeur : 0 - carré // 1 - rond // n2 - triangle // n3 - rectangle
	 */
	public static int typeDePlateau(String pseudo1, String pseudo2){
		int type = -1 ;
		Scanner sc = new Scanner(System.in);
		String answer = "";

		// **** DEMANDE AU JOUEURS LA TYPE DU PLATEAU ****
		while(type!= 0 && type != 1 && type!=2 && type!=3){ 
			System.out.println( pseudo1 + ", "+ pseudo2 + " : Sur quel type de plateau voulez-vous jouer ? 0/1/2/3 ");
			System.out.println("0 - carré \n1 - rond \n2 - triangle\n3 - rectangle");
			answer = sc.nextLine();
			if(answer.length()!=0){
				if(((int)(answer.charAt(0))>=48 && ((int)(answer.charAt(0))<48+4))){
					type = ((int)(answer.charAt(0))-48); //Correspond à la répresentation en ASCII des chiffres.
				}
			}
		}
		return type;
	} // FIN DE " typeDePlateau " 

	/** Méthode qui demande la taille d'un plateau rond (donc le rayon)
	 * @param pseudo1 simplement utile pour dire, "Joueur 1", "Joueur 2" quel taille voulez vous ? 
	 * @param pseudo2 de même.
	 * @return Le rayon du plateau sous forme d'un INT.
	 */
	public static int taillePlateauRond(String pseudo1, String pseudo2){
		int tailleX = 0;
		String answer = "";
		Scanner sc = new Scanner(System.in);

		// **** DEMANDE AU JOUEURS LA TAILLE DU PLATEAU ET LE GENERE ****
		while(tailleX<=0){ 
			System.out.println( pseudo1 + pseudo2 + "Sur quelle taille de plateau voulez-vous jouer ? Rayon ? ");
			answer = sc.nextLine();
			if(answer.length()!=0){
				tailleX = Integer.parseInt(answer);
			}
		}
		return tailleX;
	} // FIN DE " taillePlateauRond "

	/** Méthode qui demande la taille d'un plateau triangulaire (donc le coté)
	 * @param pseudo1 simplement utile pour dire, "Joueur 1", "Joueur 2" quel taille voulez vous ? 
	 * @param pseudo2 de même.
	 * @return Le coté du plateau sous forme d'un INT.
	 */
	public static int taillePlateauTriangle(String pseudo1, String pseudo2){
		int tailleX = 0;
		String answer = "";
		Scanner sc = new Scanner(System.in);

		while(tailleX<=0){ 
			System.out.println( pseudo1 + pseudo2 + "Sur quelle taille de plateau voulez-vous jouer ? Coté ? ");
			answer = sc.nextLine();
			if(answer.length()!=0){
				tailleX = Integer.parseInt(answer);
			}
		}
		return tailleX;
	} // FIN DE " taillePlateauTriangle "

	/** Méthode qui demande la taille d'un plateau rectangulaire (donc les cotés)
	 * @param pseudo1 simplement utile pour dire, "Joueur 1", "Joueur 2" quel taille voulez vous ? 
	 * @param pseudo2 de même.
	 * @return Le coté du plateau sous forme d'un INT. PROBLEME ON DEVRAIT EN RENVOYER 2 ...  NE PAS UTILISER CETTE CLASSE DONC =) 
	 */
	public static int taillePlateauRectangle(String pseudo1, String pseudo2){
		int tailleX = 0;
		int tailleY = 0;
		String answer ="";
		Scanner sc = new Scanner(System.in);

		while(tailleX<=0 || tailleY<=0){ 
			answer ="";
			System.out.println( pseudo1 + pseudo2 + "Sur quelle taille de plateau voulez-vous jouer ? Hauteur ?");
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


	/** Méthode qui demande si le joueur veut placer ses bateaux
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
			if(answer.length()!=0){		//Sécurité anti-débile qui appui sur entrée sans rien mettre
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

	/** Méthode qui demande si le joueur veut choisir son nombre de bateaux // A NOTER : IL FAUDRAIT RAJOUTER UNE SECURITE POUR EVITER LES DEBILES QUI METTENT 40 BATEAUX SUR UN PLATEAU 4*4. LE PLACEMENT ALEATOIRE NE POURRA JAMAIS ABOUTIR ET ON AURA UNE BOUCLE INFINIE
	 * @param pseudo simplement utile pour dire, "Joueur 1" voulez vous choisir le nombre de vos bateaux ? 
	 * @return retourne un boolean qui vaut true si il veut choisir son nombre de bateaux.
	 */
	public static boolean veutunNombreDeBateaux(String pseudo){
		char choix = ' ';
		String answer = "";
		boolean nombreDeBateaux;
		Scanner sc = new Scanner(System.in);

		// **** DEMANDE AU JOUEUR "PSEUDO" LE PLACEMENT DE SES BATEAUX ****
		while(choix ==' '){ // Boucle concernant le choix de placer ses bateaux.
			System.out.println(pseudo +": Voulez-vous choisir le nombre de vos bateaux ? O/n");
			answer = sc.nextLine();
			if(answer.length()!=0){		//Sécurité anti-débile qui appui sur entrée sans rien mettre
				choix = answer.charAt(0);
			}  
		}
		if(choix=='O'){
			nombreDeBateaux = true;
		} else {
			nombreDeBateaux = false;
		}
		return nombreDeBateaux;
	} // FIN DE " veutunNombreDeBateaux " 

	/** Méthode qui demande au joueur "pseudo" le nombre de bateaux qu'il désire.
	 * @param pseudo : pseudo du joueur, juste pour lui demander " "Joueur 1" combien voulez vous de bateaux ? " 
	 * @param nombreMaxBateaux : nombre passé en argument, et calculé en dehors de cette classe, qui fixe la limite à ne pas dépasser. Fixe donc la limite haute du choix du joueur.
	 * @return le nombre de bateau choisi par le joueur.
	 */
	public static int choixNombreDeBateaux(String pseudo, int nombreMaxBateaux){
		char choix = ' ';
		String answer = "";
		int nombreBateaux =0;
		Scanner sc = new Scanner(System.in);

		answer = "";
		while(nombreBateaux <= 0 && nombreBateaux>nombreMaxBateaux){ // Boucle concernant le choix du nombre de bateaux.
			System.out.println(pseudo +": Combien voulez vous de bateau ?");
			answer = sc.nextLine();
			if(answer.length()!=0){		//Sécurité anti-débile qui appui sur entrée sans rien mettre
				nombreBateaux = Integer.parseInt(answer);
			}
		}
		return nombreBateaux;
	} // FIN DE " choixNombreDeBateaux " 

	/** Méthode qui gère les demandes du joueur quand à son choix de placer ses bateaux, et leur nombre. Tout est automatisé. 
	 * @param plateauDuJoueur : Plateau concerné par le placement des bateaux.
	 * @param nombreBateauMax : Nombre calculé en amont, qui fixe la limite haute du nombre de bateaux sur le plateau de jeu. (Suivant le type de plateau, le calcul devra être différent)
	 * @return Le nombre de bateaux du joueur, choisi par lui, ou par défaut : 5.
	 */
	public static int utilitairePlacementDesBateaux(Plateau plateauDuJoueur, int nombreBateauMax){

		int nombreBateauxChoisi = 5; // Par défaut le nombre de bateaux d'un joueur est de 5.

		if(Joueur.veutPlacerSesBateaux(plateauDuJoueur.name)){
			// **** DEMANDE AU JOUEUR 1 LE CHOIX DU NOMBRE DE SES BATEAUX ****
			if(Joueur.veutunNombreDeBateaux(plateauDuJoueur.name)){
				//LE JOUEUR VEUT PLACER SES BATEAUX ET CHOISIR LEUR NOMBRE
				System.out.println(" Mise en route du programme de choix du nombre de bateaux : ");
				nombreBateauxChoisi=Joueur.choixNombreDeBateaux(plateauDuJoueur.name, nombreBateauMax);

				System.out.println(" Mise en route du programme de placement des bateaux ...");
				//ICI COMPLETER PAR ...

			} else {
				//LE JOUEUR VEUT PLACER SES BATEAUX MAIS PAS CHOISIR LEUR NOMBRE

				System.out.println(" Mise en route du programme de placement des bateaux ...");
				//ICI COMPLETER PAR ...

			}
		} else {
			// **** DEMANDE AU JOUEUR 1 LE CHOIX DU NOMBRE DE SES BATEAUX ****
			if(Joueur.veutunNombreDeBateaux(plateauDuJoueur.name)){
				//LE JOUEUR NE VEUT PAS PLACER SES BATEAUX MAIS CHOISIR LEUR NOMBRE
				System.out.println(" Mise en route du programme de choix du nombre de bateaux : ");
				nombreBateauxChoisi=Joueur.choixNombreDeBateaux(plateauDuJoueur.name, nombreBateauMax);

				System.out.println(" Mise en route du programme de placement automatique des bateaux");
				plateauDuJoueur.remplirAleatoirement(nombreBateauxChoisi);

			} else {
				//LE JOUEUR VEUT NI PLACER SES BATEAUX NI CHOISIR LEUR NOMBRE
				System.out.println(" Mise en route du programme de placement automatique des bateaux ...");
				plateauDuJoueur.remplirAleatoirement(nombreBateauxChoisi);
			}
		}
		
		return nombreBateauxChoisi;
	} // FIN DE " utilitairePlacementDesBateaux "


}

