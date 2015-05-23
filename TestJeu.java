import java.awt.*;
import java.util.Scanner;

import javax.swing.JFrame;
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
			    }
			    System.out.println("Vous avez saisi : " + choix);
	
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

  	  //On va cr�er le plateau du joueur 1 et du joueur 2
  	  Plateau joueur1 = new Plateau("Joueur 1", 10, 10); // un tableau 10*10
  	  Plateau joueur2 = new Plateau("Joueur 2", 10, 10); // un tableau 10*10
	    
  	  //On va donner des bateaux au joueur 1
  	  System.out.println("On remplis le plateau du joueur 1.");
  	  joueur1.remplirAleatoirement(5);

  	  //on va donner des bateaux au joueur 2
  	  System.out.println("On remplis le plateau du joueur 2.");
  	  joueur2.remplirAleatoirement(5);
  	  
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
