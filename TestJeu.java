import java.util.Scanner;

import javax.swing.JFrame;


/**
 * @author Vincent F
 *
 */

public class TestJeu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	 	//MORCEAU DE CODE POUR ESSAYER L'AFFICHAGE EN FENETRE. A FAIRE EN DERNIER
	    JFrame fenetre = new JFrame();
	    //Creations des instances nécessaires (Scanner, fenetre...)
	    Scanner sc = new Scanner(System.in); 
	    //Creation des variables 
	    String answer = " "; // Utilisée pour le choix de l'utilsateur du mode de jeu
	    char choix = '0';
	    boolean rejouer = true;
	   
	    
	    //Définit un titre pour notre fenêtre
	    fenetre.setTitle("BatailleNAVALE");
	    //Définit sa taille : 400 pixels de large et 100 pixels de haut
	    fenetre.setSize(800, 600);
	    //Nous demandons maintenant à notre objet de se positionner au centre
	    fenetre.setLocationRelativeTo(null);
	    //Termine le processus lorsqu'on clique sur la croix rouge
	    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //Et enfin, la rendre visible        
	    fenetre.setVisible(true);
	    
	    System.out.println("**** Bienvenue dans notre jeu ****");
	    
	    while(rejouer){ // Boucle principale qui va se relancer à chaque partie.
	    	
		   System.out.println("A quel mode de jeu voulez vous jouer ?");
		   while(choix != '1' || choix != '2' || choix != '3' ){ // Boucle secondaire concernant le choix du mode de jeu.
			    System.out.println("1 - Humain contre Humain \n2 - Humain contre IA\n3 - Humain contre ... contre Humain");
	
			    System.out.println("Veuillez saisir le numéro de votre réponse :");
			    answer = sc.nextLine();
			    choix = answer.charAt(0);
			    System.out.println("Vous avez saisi : " + choix);
	
			    switch (choix)
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
	    }

	}

	public static void HvsH() {
		  // Ici est géré le mode de jeu Humain contre Humain
		}
	
	public static void HvsIA() {
		  // Ici est géré le mode de jeu Humain contre IA
		}
	
	public static void HvsvsH() {
		  // Ici est géré le mode de jeu Humain contre .... contre Humain
		}
}
