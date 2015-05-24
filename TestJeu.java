import java.awt.*;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.Border;


public class TestJeu {
	public static void main(String[] args) {
		
	    //Creations des instances n�cessaires (Scanner, fenetre...)
	    Scanner sc = new Scanner(System.in); 
	    
	    //Creation des variables 
	    String answer = ""; // Utilis�e pour le choix de l'utilsateur du mode de jeu
	    char choix = '0';
	    boolean rejouer = true;
	    
	    System.out.println("**** Bienvenue dans notre jeu ****");
	    
	    while(rejouer){ // Boucle principale qui va se relancer � chaque partie.
	    	
		   System.out.println("A quel mode de jeu voulez vous jouer ?");
		   while(choix != '1' || choix != '2' || choix != '3'){ // Boucle secondaire concernant le choix du mode de jeu.
			    System.out.println("1 - Humain contre Humain \n2 - Humain contre IA\n3 - Humain contre ... contre Humain");
	
			    System.out.println("Veuillez saisir le num�ro de votre r�ponse :");
			    answer = sc.nextLine();
			    if(answer.length()!=0){		//S�curit� anti-d�bile qui appuis sur entr�e sans rien mettre
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
			      default:
			    	  System.out.println("Vous ne savez pas lire =)");   
			    	  break;
			    }	    
		    }
		   
		   System.out.println("Voulez vous rejouer ?"); // Petit probl�me, on arive jamais jusqu'� l� ! :P
		    sc.nextLine();
		    answer = sc.nextLine();
		    if(answer.length()!=0 && answer.charAt(0) == 'n'){		//S�curit� anti-d�bile qui appuis sur entr�e sans rien mettre
		    	if(answer.charAt(0) == 'n'){
		    		rejouer = false;
		    	} else if (answer.charAt(0)== 'O'){
		    		rejouer = true;
		    	}
		    }
		    
	    }
	}

	public static void HvsH() {
		System.out.println("Mode HvsH lanc�");   		  // Ici est g�r� le mode de jeu Humain contre Humain

		//On instancie les variables utiles 
		char choix = ' ';
		String answer = "";
		int tailleX = 0;
		int tailleY = 0;
		int nombreBateau =0;
		int bateauJoueur1 = 0;
		int bateauJoueur2 = 0;
		int nombreBateauMax = 0;
		int type = -1 ;
		String pseudo1 = "";
		String pseudo2 = "";
		Scanner sc = new Scanner(System.in); 
		Plateau joueur1;
		Plateau joueur2 ;

		// **** DEMANDE AU JOUEUR 1 DE S'IDENTIFIER ****
		pseudo1 = Joueur.identification(1);

		// **** DEMANDE AU JOUEUR 2 DE S'IDENTIFIER ****
		pseudo2 = Joueur.identification(2);

		// **** DEMANDE AU JOUEURS LA TYPE DU PLATEAU ****
		type = Joueur.typeDePlateau(pseudo1, pseudo2);

		// **** DEMANDE AU JOUEURS LA TAILLE DU PLATEAU ET LE GENERE ****
		if(type==1){

			tailleX = Joueur.taillePlateauRond(pseudo1, pseudo2);
			//On va cr�er le plateau du joueur 1 et du joueur 2
			joueur1 = new Plateau(pseudo1, tailleX*2, tailleX*2, 1); // C'est un cercle ! Donc de taille r*2 
			joueur2 = new Plateau(pseudo2, tailleX*2, tailleX*2, 1);
			nombreBateauMax=10*tailleX; // Environ 2Pi*r soit le nombre maximal de cases.

		} else if(type==2){

			tailleX = Joueur.taillePlateauTriangle(pseudo1, pseudo2);
			//On va cr�er le plateau du joueur 1 et du joueur 2
			joueur1 = new Plateau(pseudo1, (int)(tailleX*0.88660), tailleX, 1); // Un peu de trigo. Si on consid�re un triangle �quilat�ral, on a la hauteur du triangle qui est �gale � 0,8*cot�.
			joueur2 = new Plateau(pseudo2, (int)(tailleX*0.88660), tailleX, 1);
			nombreBateauMax=(int)(tailleX*tailleX*0.88660*0.5); // Environ la surface d'un triangle de hauteur cote*0,8 

		} else {
			while(tailleX<=0 || tailleY<=0){ 
				answer = "";
				System.out.println( pseudo1 + pseudo2 + "Sur quelle taille de plateau voulez-vous jouer ? Hauteur ?");
				answer = sc.nextLine();
				if(answer.length()!=0){
					tailleX = Integer.parseInt(answer);
				}
				answer = "";
				System.out.println("Largeur ?");
				answer = sc.nextLine();
				if(answer.length()!=0){
					tailleY = Integer.parseInt(answer);
				}
			}

			//On va cr�er le plateau du joueur 1 et du joueur 2
			joueur1 = new Plateau(pseudo1, tailleX, tailleY); 
			joueur2 = new Plateau(pseudo2, tailleX, tailleY); 
			nombreBateauMax = tailleX*tailleY;
		}

		// **** DEMANDE AU JOUEUR 1 LE CHOIX PLACEMENT DE SES BATEAUX ****
		Joueur.utilitairePlacementDesBateaux(joueur1, nombreBateauMax);

		// **** DEMANDE AU JOUEUR 2 LE PLACEMENT DE SES BATEAUX ****
		Joueur.utilitairePlacementDesBateaux(joueur2, nombreBateauMax);

		//Pour tester l'affichage : on affiche le plateau du joueur 1
		Affichage.afficherGrille(joueur1);
		//Pour tester l'affichage : on affiche le plateau du joueur 2
		Affichage.afficherGrille(joueur2);

		//on g�re les tours

		// on g�re les tirs de chacun des joueurs tant que le nombre de bateaux

  	  
  	  
  	  
  	  System.out.println("Mode HvsH stopp�");  
		}
	
	public static void HvsIA() {
  	  System.out.println("Mode HvsIA lanc�");   

		  // Ici est g�r� le mode de jeu Humain contre IA
  	  
  	  System.out.println("Mode HvsIA stopp�");   
		}
	
	public static void HvsvsH() {
  	  System.out.println("Mode Hvsh..vsH lanc�");   

		  // Ici est g�r� le mode de jeu Humain contre .... contre Humain
  	  
  	  System.out.println("Mode Hvsh..vsH stopp�");   

		}
}
