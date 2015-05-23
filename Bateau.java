/**
 * @author Vincent F et Thomas B
 * Classe de gestion du plateau des bateaux.
 */

public class Bateau {
	int numero;
	boolean alive = false;
	int longueur;
	int[][] lieuEtat = null; // Deux premi�re colonne pour les coordonn�es, la troisi�me pour l'�tat
	
	/** M�thode qui cr�� un bateau � partir de ses coordonn�es de placement, et qui calculera sa longueur.
	 * @param xDebut : valeur sur les x de d�but du bateau (inclus)
	 * @param yDebut : valeur sur les y de d�but du bateau (inclus)
	 * @param xFin : valeur sur les x de fin du bateau (inclus)
	 * @param yFin : valeur sur les y de fin du bateau (inclus)
	 * @param numero : numero du bateau qui correspond � son identifiant.
	 */
	public Bateau(int xDebut, int yDebut, int xFin, int yFin, int numero){
		
		if(xDebut==xFin){ // si le bateau est horizontal
			for(int i=0 ; i<=(yFin-yDebut) ; i++){
				lieuEtat[i][0] = xDebut;
				lieuEtat[i][1] = yDebut+i;
			}
		} else if (yDebut==yFin) { // si le bateau est vertical
			for(int i=0 ; i<=(xFin-xDebut) ; i++){
				lieuEtat[i][0] = xDebut+i; 
				lieuEtat[i][1] = yDebut;
			}
		}
		
		creerBateau(numero); // on lui attribue un numero
		rendreAlive();	//on le rend alive en mettant toute la colonne 2 � "1"
		
	}
	
	public void creerBateau(int numero){
		this.numero = numero;
	}
	
	public void rendreAlive(){
		for(int i =0; i<lieuEtat.length ; i++){
			lieuEtat[i][2] = 1;						//on passe toute la colonne "de Vie" � 1 comme vivant
		}
		alive = true;
	}
	
	public void rendreCouler(){
		for(int i =0; i<lieuEtat.length ; i++){
			lieuEtat[i][2] = 0;						//on passe toute la colonne "de Vie" � 0 comme mort
		}
		alive = false;
	}
	
	
	public boolean tir(Plateau plateauJoueur, int x, int y){
		
		
	}
	
	public int tir(Plateau plateauJoueur, int x, int y){
		
		
	}

}