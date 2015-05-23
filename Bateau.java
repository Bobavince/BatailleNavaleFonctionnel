/**
 * @author Vincent F et Thomas B
 * Classe de gestion du plateau des bateaux.
 */

public class Bateau {
	int numero;
	boolean alive = false;
	int longueur;
	int largeur;
	
	public Bateau(int longueur, int largeur){
		
	}
	
	public void creerBateau(int numero){
		this.numero = numero;
	}
	
	public void rendreAlive(){
		alive = true;
	}
	
	public void rendreCouler(){
		alive = false;
	}
	
	
	public boolean tir(Plateau plateauJoueur, int x, int y){
		
		
	}
	
	public int tir(Plateau plateauJoueur, int x, int y){
		
	}

}