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
			
			// **** GENERATION DES DEUX PREMIERES LIGNES D'ENTETE ****
			for(int i=0 ; i < 2 ; i++){
				grille += "\n";
				for(int j=0 ; j < plateau.plateauValeurs[0].length*2 ; j++){
					
					if(j==0){
						grille += "  "; // Si on est dans la colonne 0 on met des espaces 
					}
					
					if(i==0 && j%2==0){ 
						grille += (int)(j/2); // Sur la première ligne, tous les deux caractères, on met le chiffre corresponand à la colonne.
					} else if(i ==0 && j%2==1) {
						grille += " ";		  // Sur la première ligne, entres les caractères on met des espaces
					} else if (i==1 && j%2==0) {
						grille += "-";		  // Sur la deuxième ligne, on mets des tirets et des plus. Ici les tirets.
					} else if (i==1 && j%2==1) {
						grille += "+";		  // Sur la deuxième ligne, on mets des tirets et des plus. Ici les plus.
					}
				}
			}
			// **** FIN DE LA GENERATION DES DEUX PREMIERES LIGNES D'ENTETE ****

			
			// **** GENERATION DU RESTE DES LIGNES ****
			// Note : Comme on a déjà marqué les deux premières lignes, et qu'on recommence des boucles, on déplace le repère pour se déplacer selon i/j en prenant comme i=0, la première ligne (la A) et pour de déplacer selon les j, il faut bien aller à +2 car on a toujours les eux premières colonnes avec les lettres qui viennent s'jaouter.
			for(int i=0 ; i < plateau.plateauValeurs.length*2 ; i++){ //Le tableau de génération (celi qu'on parcours pour placer les éléments) est bien 2 fois plus grand que le tableau qu'on dessine, à cause de l'épaisseur des traits.
				grille += "\n";
				for(int j=0 ; j < plateau.plateauValeurs[0].length*2 +2; j++){
					
					if(j==0){ // Dans la première colonne : 
						if(i%2==0){
							grille += (char)(65+(((i)/2)%27)); // si on est sur une ligne paire (nouveau repère!) alors on note les A,B,C ... de 65 à 90, avec un modulo pour gérer les cas supérieurs à 26 lignes)
						} else if(i%2==1){
							grille += " "; // si on est sur une ligne impaire, on fait simplement un espace.
						}
					} else if(j%2==0) { // Dans les colonnes paires : 
						if(i%2==0){ // Sur les lignes paires, on regarde si on met des bateaux, des récifs, rien, etc ... 
							if(plateau.plateauValeurs[i/2][(j/2)-1][0]!=0){ //On regarde si ce n'est pas de l'eau à la position courante, sur le plateau au niveau 0.
								if(plateau.plateauValeurs[i/2][(j/2)-1][0]==-1){
									grille += "X"; // Si c'est un récif, on met un croix. Pour symboliser les récifs.
								} else {
									grille += plateau.plateauValeurs[i/2][(j/2)-1][0]; //Si ce n'est pas de l'eau, et pas un récif, on affiche la valeur de ce que c'est. 
								}
							} else {
								grille += " "; // Si on est pas sur une case où se trouve un bateau, on fait simplement un espace.
							}
						} else if (i%2==1){ 
							grille += "-"; // Sur les lignes impaires on met juste des tirets.
						}
					} else if(j%2==1) { // Dans les colonnes impaires : 
						if(i%2==0){
							grille += "|"; // Si on est sur une ligne paire, on met un séparateur vertical
						} else if (i%2==1){
							grille += "+"; // Si on est sur une ligne impaire, on met un plus.
						}
					}
				}
			}
			// **** FIN DE LA GENERATION DU RESTE DES LIGNES ****
			
			System.out.println(grille); // Affichage de la grille.
		}
		
		public static void afficherGrilleAmi(int[][][] tab){
			
		}
		
		public static void afficherGrilleEnnemi(int[][][] tab){
			
		}
}
