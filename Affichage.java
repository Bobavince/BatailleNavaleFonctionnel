import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Vincent F et Thomas B
 * Classe de gestion de l'affichage du plateau de jeu.
 */

public class Affichage {

		public static void fenetre() {
			//************* ESSAI : On affiche la grille dans la fenetre : *************
		  	
		  	  //MORCEAU DE CODE POUR ESSAYER L'AFFICHAGE EN FENETRE. A FAIRE EN DERNIER
			    JFrame fenetre = new JFrame();
			    
			    //Définit un titre pour notre fenêtre
			    fenetre.setTitle("BatailleNAVALE");
			    //Définit sa taille : 400 pixels de large et 100 pixels de haut
			    fenetre.setSize(800, 600);
			    //Nous demandons maintenant à notre objet de se positionner au centre
			    fenetre.setLocationRelativeTo(null);
			    //Termine le processus lorsqu'on clique sur la croix rouge
			    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  	  JPanel grille ;  
		  	  grille = new JPanel (new GridLayout (10,10));
		  	  	grille.setLayout(new GridLayout(3, // nb de cases dans une ligne
		  	      3, // nb de cases dans une colonne
		  	      1, // épaisseur des traits horizontaux
		  	      1)); // épaisseur des traits verticaux
		  		grille.setBackground(Color.WHITE); // Couleur des traits
			    fenetre.add(grille);
			    //Et enfin, la rendre visible        
			    fenetre.setVisible(true);
			  //************* FIN DE L'ESSAI SUR LES FENETRE *************
		}
		
		public static void afficherGrille(Plateau plateau){
			String grille = "";
			
			// On génère les deux premières lignes :
			for(int i=0 ; i < 2 ; i++){
				grille += "\n";
				for(int j=0 ; j < plateau.plateauValeurs[0].length*2 ; j++){
					
					// Dans la colonne 0 on met des espaces.
					if(j==0){
						grille += "  ";
					}
					
					if(i==0 && j%2==0){
						grille += (int)(j/2);
					} else if(i ==0 && j%2==1) {
						grille += " ";
					} else if (i==1 && j%2==0) {
						grille += "-";
					} else if (i==1 && j%2==1) {
						grille += "+";
					}
				}
			}
			
			// On génère les autres lignes (on repose un repère où 0/0 des boucles d'ici correspondent bien aux coordonées du plateau/2 (car on a aussi les lignes pour les traits)
			for(int i=0 ; i < plateau.plateauValeurs.length*2-2 ; i++){
				grille += "\n";
				for(int j=0 ; j < plateau.plateauValeurs[0].length*2+2 ; j++){
					
					if(j==0){ // si on est dans la première colonne (A,B,C ... de 65 à 90, avec un modulo pour gérer les cadre supérieur à 26 lignes)
						if(i%2==0){
							grille += (char)(65+(((i)/2)%27));
						} else if(i%2==1){
							grille += " ";
						}
					} else if(j%2==0) {
						if(i%2==0){
							if(plateau.bateauPresent(((i/2)), (j/2))==true){
								grille += plateau.numeroBateauPresent(((i/2)), (j/2));
							} else {
								grille += " ";
							}
						} else if (i%2==1){
							grille += "-";
						}
					} else if(j%2==1) {
						if(i%2==0){
							grille += "|";
						} else if (i%2==1){
							grille += "+";
						}
					}
				}
			}
			
			/*
			for(int i=0 ; i < plateau.plateauValeurs.length ; i++){
				grille += "\n";
				for(int j=0 ; j < plateau.plateauValeurs[0].length ; j++){
					
					// Dans la case 0/0 on met un espace.
					if(i==0 && j==0){
						grille += " ";
					}
					
					// Dans les case impaires/impaires on met des croix.
					if(j%2==1 && i%2==1){
						grille += "+";
					}
					
					// On met dans les cases paires/paires 
					if(j%2==0 && i%2==0 ){
						
						//Dans la première ligne
						if(j==0){
							if(i!=0){ // Si on est pas à la position 0/0 on met des lettres (A,B,C ... de 65 à 90, avec un modulo pour gérer les cadre supérieur à 26 lignes)
								grille += (char)(65+(((i-2)/2)%27));
							}
						} else {
							if(plateau.bateauPresent(((i/2)-1), (j/2)-1)==true){
								grille += "X";
							}	
						}
						
						//Dans la première colonne
						if(i==0){
							if(j!=0){ // Si on est pas à la position 0/0 on met des chiffres correspondant à la colonne (1,2,3,4 ...)
								grille += (int)(j/2);
							}
						} else {
							if(plateau.bateauPresent(((i/2)-1), (j/2)-1)==true){
								grille += "X";
							}	
						}
						
					}
					
					// Dans les cases paires/impaires on met des barres verticales ou des espaces si on est dans la première colonne.
					if(j%2==1 && i%2==0){
						if(i==0){
							grille += " ";	
						} else {
							grille += "|";	
						}
					}
					
					// Dans les cases impaires/paires on met des barres horizontalles ou des espaces si on est dans la première ligne.
					if(j%2==0 && i%2==1){
						if(j==0){
							grille += " ";	
						} else {
							grille += (char)(45);	
						}
					}
					
					if(i==(plateau.plateauValeurs.length-1)){
						grille += (char)(45)+(char)(45);
					}
				}
				
			}
			*/
			System.out.println(grille);
		}
		
		public static void afficherGrilleAmi(int[][][] tab){
			
		}
		
		public static void afficherGrilleEnnemi(int[][][] tab){
			
		}
}
