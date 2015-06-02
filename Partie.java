/**
 * @author Vincent F
 *	Ne sert que d'interface pour relier l'affichage donné en .class et nos fichiers de jeu.
 */

public class Partie {

	int[][] plateauDeJeu;
	Plateau tireur;
	Plateau cible;
	
	public Partie(Plateau joueur){
		plateauDeJeu = new int[joueur.getPlateauValeurs().length][joueur.getPlateauValeurs()[0].length];
		
		for(int i = 0; i< joueur.getPlateauValeurs().length ; i++){
			for(int j = 0; j< joueur.getPlateauValeurs()[i].length ; j++){
				plateauDeJeu[i][j] = joueur.getPlateauValeurs()[i][j][0];
			}
		}
	}
	
	public void setCible(Plateau tireur, Plateau cible){
		this.tireur = tireur;
		this.cible = cible;
	}
	
	public void coup(int x, int y){
		int[] coordonnes = new int[2];	
		boolean correct = false;
		coordonnes[0] = x;
		coordonnes[1] = y;

		while(correct==false){
			if(cible.dejaSubiTir(coordonnes[0], coordonnes[1])==false && Joueur.scannerCaseInterdite(cible, coordonnes[0], coordonnes[1])==false){
				correct = true;
			} else if (Joueur.scannerCaseInterdite(cible, coordonnes[0], coordonnes[1])==true){
				System.out.println("Cette case est hors du plateau de jeu !");
			} else {
				System.out.println("Cette case a déjà subit un tir !");
			}
		}

		tireur.tirerJoueur(coordonnes, cible);
		Joueur.reponseAuTir(cible, coordonnes[0], coordonnes[1]);
	}
	
}
