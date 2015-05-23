/**
 * @author Vincent F et Thomas B
 * Classe de gestion du plateau de jeu.
 */

public class Plateau {
	//Les Tableaux de la classe
	int[][][] plateauValeurs; //Initilisation d'un tableau 3D Pour implémentation des deux niveaux de données
	
	//Les variables internes
	
	
	//Les variables externes
	int nbBateaux;
	String name = "Joueur";
	ListeBateau bateaux = new ListeBateau();
	
	public Plateau(String name, int x, int y){
		plateauValeurs = new int[y][x][2];
		plateauValeurs = Type.carre(y,x); // méthode à revoir pour remplir le tableau
		this.name = name;
	}
	
	public Plateau(String name, int x, int y, int numeroDeType){
		plateauValeurs = new int[y][x][2];
		plateauValeurs = Type.forme(x, y, numeroDeType);  // méthode à revoir pour remplir le tableau
		this.name = name;
	}
	
	public void tirer(int x, int y){
		
	}
	
	public void tirerJoueur(int x, int y, Plateau joueur){
		
	}
	
}


/**
* Le plateau est composé de deux niveaux. Un niveau 0 qui sert à connaitre ce qu'il y a sur le le plateau et un niveau 1 qui sert à savoir les actions subit par le plateau.
* Au niveau 0 : 0 correspond à une case vide, -1 à une récif, 1,2,3,...,n au numéro du bateau.
* C'est le plateau qui appelle la méthode du bateau tireur (On devrait faire de la hiérarchie, avec le classe bateau, puis la classe bateau principal, puis bateau tireur)
* Le plateau tire aux coordonnées x,y sur le plateau du joueur Z. La méthode sera donc Tire(Plateau Z, int x, int y)
* Si il touche une case au niveau 0 qui a pour valeur : 0, le tire tombe dans l'eau. -1 le tire ne fait rien (probabilité de rebondir?), 1,2,...n le tire touche un bateau.
* Si le tire touche un bateau, dans ce cas, le bateau en question en est informé. Il met à jour son statut, son état, etc...
* 
* Chaque joueur tire tour à tour.
* Le niveau 1 du plateau permet de savoir quelles cases ont déjà été touchées.
* 
* Evolutions possibles : 
* Ajouter plusieurs points de vie à une même case.
* Permet de gérer la forme du plateau en mettant une valeur -2 qui indique que le plateau n'existe pas à cette endroit.
* Permet de ne pas tirer deux fois au même endroit si la couche 1 du plateau a pour valeur 1. (on pourrait également mettre le numéro du joueur à la palce, de manière à savoir qui a déjà tiré à cet endroit)
* L'IA a gérer est indépendante de ces caractères. PAs plus compliqué à gérer.
* La représentation en fenetre peut se faire facilement grace justement au nombre de la couche 0 ! On pourrait rajouter une troisième couche pour le brouillard de guerre, et ainsi savoir quelles cases cacher au joueur.
*/ 


