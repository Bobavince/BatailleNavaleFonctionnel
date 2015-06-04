/**
 * @author Vincent F et Thomas B
 */

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Classe de gestion de l'affichage du plateau de jeu.
 */
public class Affichage {

	/** Méthode qui gère l'affichage en fenetre. NE PAS UTILISER/ PAS AU POINT
	 * 
	 */
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

	/** Méthode qui gère l'affichage du plateau de jeu en tant qu'"omniscient" du plateau passé en paramètre. Tous les bateaux sont découverts. 
	 * @param plateau : plateau qui sera affiché. (sa couche 0)
	 */
	public static void afficherGrille(Plateau plateau){
		String grille = "";
		String tiretDouble = "--";
		String espaceSimple = " ";
		String espaceDouble = "  ";
		String espaceVariable = "";


		// **** GENERATION DES DEUX PREMIERES LIGNES D'ENTETE ****
		for(int i=0 ; i < 2 ; i++){
			grille += "\n";
			for(int j=0 ; j < plateau.getPlateauValeurs()[0].length*2 ; j++){

				if(j==0){
					grille += "  "; // Si on est dans la colonne 0 on met des espaces 
				}

				if(j<20){
					espaceVariable="  ";
				} else {
					espaceVariable=" ";
				}
				if(i==0 && j%2==0){ 
					grille += (int)(j/2); // Sur la première ligne, tous les deux caractères, on met le chiffre corresponand à la colonne.
				} else if(i ==0 && j%2==1) {
					grille += espaceVariable;		  // Sur la première ligne, entres les caractères on met des espaces
				} else if (i==1 && j%2==0) {
					grille += tiretDouble;		  // Sur la deuxième ligne, on mets des tirets et des plus. Ici les tirets.
				} else if (i==1 && j%2==1) {
					grille += "+";		  // Sur la deuxième ligne, on mets des tirets et des plus. Ici les plus.
				}	

			}
		}
		// **** FIN DE LA GENERATION DES DEUX PREMIERES LIGNES D'ENTETE ****


		// **** GENERATION DU RESTE DES LIGNES ****
		// Note : Comme on a déjà marqué les deux premières lignes, et qu'on recommence des boucles, on déplace le repère pour se déplacer selon i/j en prenant comme i=0, la première ligne (la A) et pour de déplacer selon les j, il faut bien aller à +2 car on a toujours les eux premières colonnes avec les lettres qui viennent s'jaouter.
		for(int i=0 ; i < plateau.getPlateauValeurs().length*2 ; i++){ //Le tableau de génération (celi qu'on parcours pour placer les éléments) est bien 2 fois plus grand que le tableau qu'on dessine, à cause de l'épaisseur des traits.
			grille += "\n";
			for(int j=0 ; j < plateau.getPlateauValeurs()[0].length*2 +2; j++){

				if(j==0){ // Dans la première colonne : 
					if(i%2==0){
						grille += (char)(65+(((i)/2)%26)); // si on est sur une ligne paire (nouveau repère!) alors on note les A,B,C ... de 65 à 90, avec un modulo pour gérer les cas supérieurs à 26 lignes)
					} else if(i%2==1){
						grille += espaceSimple; // si on est sur une ligne impaire, on fait simplement un espace.
					}
				} else if(j%2==0) { // Dans les colonnes paires : 
					if(i%2==0){ // Sur les lignes paires, on regarde si on met des bateaux, des récifs, rien, etc ... 
						if(plateau.getPlateauValeurs()[i/2][(j/2)-1][0]!=0){ //On regarde si ce n'est pas de l'eau à la position courante, sur le plateau au niveau 0.
							if(plateau.getPlateauValeurs()[i/2][(j/2)-1][0]<= (-1)){
								grille += "X "; // Si c'est un récif ou un hors plateau, on met un croix. Pour symboliser les récifs.
							} else {
								grille += plateau.getPlateauValeurs()[i/2][(j/2)-1][0]+ " "; //Si ce n'est pas de l'eau, et pas un récif, on affiche la valeur de ce que c'est. 
							}
						} else {
							grille += espaceDouble; // Si on est pas sur une case où se trouve un bateau, on fait simplement un espace.
						}
					} else if (i%2==1){ 
						grille += tiretDouble; // Sur les lignes impaires on met juste des tirets.
					}
				} else if(j%2==1) { // Dans les colonnes impaires : 
					if(i%2==0){
						grille += "|"; // Si on est sur une ligne paire, on met un séparateur vertical
					} else if (i%2==1){
						grille += "+"; // Si on est sur une ligne impaire, on met un plus.
					}
				}

			}
		}// **** FIN DE LA GENERATION DU RESTE DES LIGNES ****

		System.out.println(grille); // Affichage de la grille.
	}

	/** Méthode qui devrait gérer l'affichage d'un plateau ennemi. On devrait voir les tirs (donc la couche 1 du plateau ennemi).
	 * @param plateau : plateau du joueur duquel on va afficher la grille en tant qu'ennemi
	 */
	public static void afficherGrilleEnnemi(Plateau plateau){
		String grille = "";
		String tiretDouble = "--";
		String espaceSimple = " ";
		String espaceDouble = "  ";
		String espaceVariable = "";


		// **** GENERATION DES DEUX PREMIERES LIGNES D'ENTETE ****
		for(int i=0 ; i < 2 ; i++){
			grille += "\n";
			for(int j=0 ; j < plateau.getPlateauValeurs()[0].length*2 ; j++){

				if(j==0){
					grille += "  "; // Si on est dans la colonne 0 on met des espaces 
				}

				if(j<20){
					espaceVariable="  ";
				} else {
					espaceVariable=" ";
				}
				if(i==0 && j%2==0){ 
					grille += (int)(j/2); // Sur la première ligne, tous les deux caractères, on met le chiffre corresponand à la colonne.
				} else if(i ==0 && j%2==1) {
					grille += espaceVariable;		  // Sur la première ligne, entres les caractères on met des espaces
				} else if (i==1 && j%2==0) {
					grille += tiretDouble;		  // Sur la deuxième ligne, on mets des tirets et des plus. Ici les tirets.
				} else if (i==1 && j%2==1) {
					grille += "+";		  // Sur la deuxième ligne, on mets des tirets et des plus. Ici les plus.
				}	

			}
		}
		// **** FIN DE LA GENERATION DES DEUX PREMIERES LIGNES D'ENTETE ****


		// **** GENERATION DU RESTE DES LIGNES ****
		// Note : Comme on a déjà marqué les deux premières lignes, et qu'on recommence des boucles, on déplace le repère pour se déplacer selon i/j en prenant comme i=0, la première ligne (la A) et pour de déplacer selon les j, il faut bien aller à +2 car on a toujours les eux premières colonnes avec les lettres qui viennent s'jaouter.
		for(int i=0 ; i < plateau.getPlateauValeurs().length*2 ; i++){ //Le tableau de génération (celi qu'on parcours pour placer les éléments) est bien 2 fois plus grand que le tableau qu'on dessine, à cause de l'épaisseur des traits.
			grille += "\n";
			for(int j=0 ; j < plateau.getPlateauValeurs()[0].length*2 +2; j++){

				if(j==0){ // Dans la première colonne : 
					if(i%2==0){
						grille += (char)(65+(((i)/2)%26)); // si on est sur une ligne paire (nouveau repère!) alors on note les A,B,C ... de 65 à 90, avec un modulo pour gérer les cas supérieurs à 26 lignes)
					} else if(i%2==1){
						grille += espaceSimple; // si on est sur une ligne impaire, on fait simplement un espace.
					}
				} else if(j%2==0) { // Dans les colonnes paires : 
					if(i%2==0){// Sur les lignes paires, on regarde si on met des bateaux, des récifs, rien, etc ... 
						if(plateau.getPlateauValeurs()[i/2][(j/2)-1][0]==(-2)){
							grille+="HH"; //Si c'est un hors plateau on met des "H" parce que ça remplis bien et que ça fait "Hors Horizon"
						} else {
							if(plateau.getPlateauValeurs()[i/2][(j/2)-1][1]!=0){ // On regarde si quelqu'un a déjà tiré sur la case.
								if(plateau.getPlateauValeurs()[i/2][(j/2)-1][0]==(-1)){ //On regarde si ce qu'il a en dessous est un récif.
									grille += "R "; // Si c'est un récif, on met un R.
								} else if(plateau.getPlateauValeurs()[i/2][(j/2)-1][0]==(0)){ //Si regarde si c'est de l'eau
									grille += "O "; // Si c'est de l'eau on met un O, comme les ronds que font un tir dans l'eau.
								} else { //Si ce n'est pas de l'eau ou pas un récif, alors c'est un bateau. 
									if(plateau.getBateaux().getListeBat()[plateau.numeroBateauPresent((i/2), (j/2)-1)-1].isAlive() == true){ //On regarde si le bateau de la case est vivant.
										grille += "T "; //Si le bateau est vivant, mais qu'on a tiré sur cette case, on met "touché" avec un "T".
									} else {
										grille += "C "; //Si le bateau est mort, et qu'on a tiré sur cette case, on l'a donc coulé. on met donc "C".
									} 
								}
							} else {
								grille += espaceDouble; // Si on est pas sur une case où quelqu'un a tiré, on met des espaces.
							}
						}
					} else if (i%2==1){ 
						grille += tiretDouble; // Sur les lignes impaires on met juste des tirets.
					}
				} else if(j%2==1) { // Dans les colonnes impaires : 
					if(i%2==0){
						grille += "|"; // Si on est sur une ligne paire, on met un séparateur vertical
					} else if (i%2==1){
						grille += "+"; // Si on est sur une ligne impaire, on met un plus.
					}
				}

			}
		}// **** FIN DE LA GENERATION DU RESTE DES LIGNES ****

		System.out.println(grille); // Affichage de la grille.
	}
}