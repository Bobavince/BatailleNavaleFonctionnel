/**
 * @author Vincent F et Thomas B
 */

/**
 * Classe de gestion de la forme du plateau;
 * Note : le mot clé static permet d'éviter d'instancier la classe pour y avoir accès (aucune sens d'instancier la classe Type)
 */
public class Type {

	/** Classe statique qui créé un plateau carré (tableau 2D avec toutes les cases vides à 0)
	 * @param x : hauteur du plateau de jeu renvoyé (taille vertical)
	 * @param y : largeur du plateau de jeu renvoyé (taille horizontal)
	 * @return un tableau qui est remplis avec un motif carré (que des 0 partout)
	 */
	public static int[][] carre(int x, int y){
		int[][] tab = new int[x][y]; // on créé le tableau qu'on va renvoyer de la taille x(vertical)*y(horizontal)

		for(int i=0 ; i<x; i++){
			for(int j=0 ; j<y ; j++){
				tab[i][j] = 0; // on remplis le tableau de case "0" équivalente à de l'eau.
			}
		}

		return tab; // on renvois le tableau créé.
	}


	/** Classe qui gère l'appel des autres méthodes qui créé les formes de plateaux.
	 * @param x : hauteur du plateau de jeu renvoyé (taille vertical) // rayon pour le rond // diagonale pour le triangle
	 * @param y : largeur du plateau de jeu renvoyé (taille horizontal)
	 * @param numeroDeType : 1 pour rond, 2 pour triangle, 3 pour rectangle, défaut carré
	 * @return le tableau avec des cases d'eau (0) qui correspondent à la forme donnée en argument.
	 */
	public static int[][] forme(int x, int y, int numeroDeType){
		int[][] tab;

		switch (numeroDeType)
		{
		case 0 :
			tab = carre(x,y);
			break;
		case 1:
			tab = rond(x);
			break;  
		case 2:
			tab = triangle(x,y);
			break;
		case 3:
			tab = rectangle(x,y);
			break;
		case 4 :
			tab = recifs(x,y);
			break;
		default:
			tab = rectangle(x,y);    
			break;
		}

		return tab;

	}

	/** Classe qui créer un plateau rond (tableau 2D avec les cases aux angles à -2)
	 * @param r : rayon du cercle à créer
	 * @return un tableau avec un cercle d'eau (de cases à 0)
	 */
	public static int[][] rond(int d){
		int[][] tab = new int[d][d];
		int rayon = (int)((d)*0.5); // On recupère en fait le diamètre !

		for(int i=0 ; i<tab.length; i++){
			for(int j=0 ; j<tab[0].length ; j++){
				if(  (rayon*rayon) <= ( (((i+.5)-rayon)*((i+.5)-rayon)) + (((j+.5)-rayon)*((j+.5)-rayon)) )  ){ // Ne me demande pas pourquoi il faut mettre 0.5, c'est ce qui marche.
					tab[i][j]= -2; // On mes des cases interdites.
				} else {
					tab[i][j] = 0 ; // on remplis le tableau de case "0" équivalente à de l'eau.
				}
			}
		}
		
		return tab;
	}

	/** Classe qui créer un plateau triangle (tableau 2D avec des cases en coin à -2)
	 * @param cote : coté du triangle à créer
	 * @return un tableau avec un trangle d'eau (de cases à 0)
	 */
	public static int[][] triangle(int hauteur, int cote){
		int[][] tab =new int[hauteur][cote] ;
		
		for(int i=0 ; i<tab.length; i++){
			for(int j=0 ; j<tab[0].length ; j++){
				if(j < (-(tab[0].length*0.5)/(double)(tab.length))*i + (int)(tab[0].length*0.5) || j > ((tab[0].length*0.5)/(double)(tab.length))*i + (int)(tab[0].length*0.5)){ // ON NE TOUCHE PAS A CA, CA MARCHE ! 
					tab[i][j]= -2; // On mes des cases interdites.
				} else {
					tab[i][j] = 0 ; // on remplis le tableau de case "0" équivalente à de l'eau.
				}
			}
		}
		
		return tab;
	}

	/** Classe qui créer un plateau rectangle (tableau 2D avec toutes les cases vides à 0)
	 * @param x : hauteur du plateau de jeu renvoyé (taille vertical) 
	 * @param y : largeur du plateau de jeu renvoyé (taille horizontal)
	 * @return un tableau rectangulaire d'eau (de cases à 0)
	 */
	public static int[][] rectangle(int x, int y){
		int[][] tab = new int[x][y]; // on créé le tableau qu'on va renvoyer de la taille x(vertical)*y(horizontal)

		for(int i=0 ; i<x; i++){
			for(int j=0 ; j<y ; j++){
				tab[i][j] = 0; // on remplis le tableau de case "0" équivalente à de l'eau.
			}
		}

		return tab; // on renvois le tableau créé.
	}
	
	/** Classe qui créer un plateau rectangle avec des récifs
	 * @param x : hauteur du plateau de jeu renvoyé (taille vertical) 
	 * @param y : largeur du plateau de jeu renvoyé (taille horizontal)
	 * @return un tableau rectangulaire d'eau (de cases à 0) avec des récifs (-1)
	 */
	public static int[][] recifs(int x, int y){
		int[][] tab = new int[x][y];
		double pourcentage = 0;
		/*
		System.out.println("Quel pourcentage de récifs voulez vous ? (ciffre de 5 à 40 (en pourcent))");
		while(pourcentage < 0.05 || pourcentage > 0.40){
			pourcentage = 0.01*Joueur.demanderInt();
		}*/
		pourcentage = 0.3; // On a des problèmes avec la saisie joueur. Il faudrait déclarer cette classe pour créer en fait un seul pourcentage pour tous les joueurs ! 
		for(int i=0 ; i<x; i++){
			for(int j=0 ; j<y ; j++){
				tab[i][j] = 0; // on remplis le tableau de case "0" équivalente à de l'eau.
			}
		}
		
		for(int i=0 ; i<x; i++){
			for(int j=0 ; j<y ; j++){
				if(Math.random()<pourcentage*0.2){
					tab[i][j] = -2;
					if(Math.random()<0.5){
						if(i+1<x && j+1<y){
							tab[i+1][j+1] = -2; 	
						}
						if(i+1<x && j<y){
							tab[i+1][j] = -2;	
						}		
					} else if (Math.random()<0.1) {
						if(i+1<x && j-1>0){
							tab[i+1][j-1] = -2;	
						}
						if(i-1>0 && j<y){
							tab[i-1][j] = -2;
						}
						if(i-1>0 && j-1>0){
							tab[i-1][j-1] = -2;
						}
					} else if (Math.random()>0.9) {
						if(i+2<x && j<y){
							tab[i+2][j] = -2;
						}
						if(i+1<x && j<y){
							tab[i+1][j] = -2;	
						}
						if(i-1>0 && j-1>0){
							tab[i-1][j-1] = -2;	
						}
					}
				}
			}
		}
		
		return tab;
	}

}