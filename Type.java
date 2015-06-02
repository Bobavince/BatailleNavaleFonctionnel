/**
 * @author Vincent F et Thomas B
 */

/**
 * Classe de gestion de la forme du plateau;
 * Note : le mot cl� static permet d'�viter d'instancier la classe pour y avoir acc�s (aucune sens d'instancier la classe Type)
 */
public class Type {

	/** Classe statique qui cr�� un plateau carr� (tableau 2D avec toutes les cases vides � 0)
	 * @param x : hauteur du plateau de jeu renvoy� (taille vertical)
	 * @param y : largeur du plateau de jeu renvoy� (taille horizontal)
	 * @return un tableau qui est remplis avec un motif carr� (que des 0 partout)
	 */
	public static int[][] carre(int x, int y){
		int[][] tab = new int[x][y]; // on cr�� le tableau qu'on va renvoyer de la taille x(vertical)*y(horizontal)

		for(int i=0 ; i<x; i++){
			for(int j=0 ; j<y ; j++){
				tab[i][j] = 0; // on remplis le tableau de case "0" �quivalente � de l'eau.
			}
		}

		return tab; // on renvois le tableau cr��.
	}


	/** Classe qui g�re l'appel des autres m�thodes qui cr�� les formes de plateaux.
	 * @param x : hauteur du plateau de jeu renvoy� (taille vertical) // rayon pour le rond // diagonale pour le triangle
	 * @param y : largeur du plateau de jeu renvoy� (taille horizontal)
	 * @param numeroDeType : 1 pour rond, 2 pour triangle, 3 pour rectangle, d�faut carr�
	 * @return le tableau avec des cases d'eau (0) qui correspondent � la forme donn�e en argument.
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

	/** Classe qui cr�er un plateau rond (tableau 2D avec les cases aux angles � -2)
	 * @param r : rayon du cercle � cr�er
	 * @return un tableau avec un cercle d'eau (de cases � 0)
	 */
	public static int[][] rond(int d){
		int[][] tab = new int[d][d];
		int rayon = (int)((d)*0.5); // On recup�re en fait le diam�tre !

		for(int i=0 ; i<tab.length; i++){
			for(int j=0 ; j<tab[0].length ; j++){
				if(  (rayon*rayon) <= ( (((i+.5)-rayon)*((i+.5)-rayon)) + (((j+.5)-rayon)*((j+.5)-rayon)) )  ){ // Ne me demande pas pourquoi il faut mettre 0.5, c'est ce qui marche.
					tab[i][j]= -2; // On mes des cases interdites.
				} else {
					tab[i][j] = 0 ; // on remplis le tableau de case "0" �quivalente � de l'eau.
				}
			}
		}
		
		return tab;
	}

	/** Classe qui cr�er un plateau triangle (tableau 2D avec des cases en coin � -2)
	 * @param cote : cot� du triangle � cr�er
	 * @return un tableau avec un trangle d'eau (de cases � 0)
	 */
	public static int[][] triangle(int hauteur, int cote){
		int[][] tab =new int[hauteur][cote] ;
		
		for(int i=0 ; i<tab.length; i++){
			for(int j=0 ; j<tab[0].length ; j++){
				if(j < (-(tab[0].length*0.5)/(double)(tab.length))*i + (int)(tab[0].length*0.5) || j > ((tab[0].length*0.5)/(double)(tab.length))*i + (int)(tab[0].length*0.5)){ // ON NE TOUCHE PAS A CA, CA MARCHE ! 
					tab[i][j]= -2; // On mes des cases interdites.
				} else {
					tab[i][j] = 0 ; // on remplis le tableau de case "0" �quivalente � de l'eau.
				}
			}
		}
		
		return tab;
	}

	/** Classe qui cr�er un plateau rectangle (tableau 2D avec toutes les cases vides � 0)
	 * @param x : hauteur du plateau de jeu renvoy� (taille vertical) 
	 * @param y : largeur du plateau de jeu renvoy� (taille horizontal)
	 * @return un tableau rectangulaire d'eau (de cases � 0)
	 */
	public static int[][] rectangle(int x, int y){
		int[][] tab = new int[x][y]; // on cr�� le tableau qu'on va renvoyer de la taille x(vertical)*y(horizontal)

		for(int i=0 ; i<x; i++){
			for(int j=0 ; j<y ; j++){
				tab[i][j] = 0; // on remplis le tableau de case "0" �quivalente � de l'eau.
			}
		}

		return tab; // on renvois le tableau cr��.
	}
	
	/** Classe qui cr�er un plateau rectangle avec des r�cifs
	 * @param x : hauteur du plateau de jeu renvoy� (taille vertical) 
	 * @param y : largeur du plateau de jeu renvoy� (taille horizontal)
	 * @return un tableau rectangulaire d'eau (de cases � 0) avec des r�cifs (-1)
	 */
	public static int[][] recifs(int x, int y){
		int[][] tab = new int[x][y];
		double pourcentage = 0;
		/*
		System.out.println("Quel pourcentage de r�cifs voulez vous ? (ciffre de 5 � 40 (en pourcent))");
		while(pourcentage < 0.05 || pourcentage > 0.40){
			pourcentage = 0.01*Joueur.demanderInt();
		}*/
		pourcentage = 0.3; // On a des probl�mes avec la saisie joueur. Il faudrait d�clarer cette classe pour cr�er en fait un seul pourcentage pour tous les joueurs ! 
		for(int i=0 ; i<x; i++){
			for(int j=0 ; j<y ; j++){
				tab[i][j] = 0; // on remplis le tableau de case "0" �quivalente � de l'eau.
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