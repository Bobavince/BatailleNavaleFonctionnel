/**
 * @author Vincent F et Thomas B
 * Classe de gestion des choses qu'on demande au joueur
 */

import java.util.Scanner;


public class Joueur {
	char choix = ' ';
	String answer = "";
	int tailleX = 0;
	int tailleY = 0;
	int nombreBateau =0;
	int nombreBateauMax = 0;
	int type = -1 ;
	String pseudo1 = "";
	String pseudo2 = "";
	Scanner sc = new Scanner(System.in); 
	Plateau joueur1;
	Plateau joueur2 ;

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
	}

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
	}

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
	}

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
	}
	
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
	}
	
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
	}
		
	public static boolean veutunNombreDeBateaux(String pseudo){
		char choix = ' ';
		String answer = "";
		boolean nombreDeBateaux;
		Scanner sc = new Scanner(System.in);
		
		// **** DEMANDE AU JOUEUR 1 LE PLACEMENT DE SES BATEAUX ****
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
	}
  	  
}