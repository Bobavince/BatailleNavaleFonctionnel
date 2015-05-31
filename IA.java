import java.util.Scanner;

/**
 * @author Vincent F et Thomas B
 */

/**
 * Classe de gestion de l'IA
 */
public class IA {

	int type = -1 ;

	public IA(){
		
	}

	/** M�thode qui demande au joueur quel type d'IA il d�sire et met � jour le type.
	 * @return un String qui contient le "pseudo" de l'IA
	 */
	public String typeIA(){
		String pseudo = "" ;
		String answer = "";
		int type = -1;
		Scanner sc = new Scanner(System.in);

		System.out.println("Joueur(s), quel type d'IA voulez vous ?");	
		System.out.println("\n1 - D�bile profond\n2 - facile\n3 - moyen\n4 - difficile\n5 - IMPOSSIBIRU");

		while(type != 1 && type != 2 && type != 3 && type != 4 && type != 5){
			answer = sc.nextLine();
			if(answer.length()!=0){		//S�curit� anti-d�bile qui appui sur entr�e sans rien mettre
				type = Integer.parseInt(answer);
			} else {
				System.out.println("Vous ne savez pas lire !");
			}
		}
		
		switch(type){
		case 1:
			pseudo = "D�bileProfond";
			break;
		case 2:
			pseudo = "IA facile";
			break;
		case 3:
			pseudo = " IA Moyen";
			break;
		case 4:
			pseudo = "IA Difficile";
			break;
		case 5 :
			pseudo = "IA IMPOSSIBLE";
			break;
		default :
			pseudo =" IA RANDOM ";
			break;
		}

		this.type = type;
		return pseudo;
	}

	/** M�thode qui g�re le placement des bateaux de l'IA. Renvois sur le placement automatique.
	 * @param plateauDuJoueur : plateau de l'IA. 
	 * @param listedesBateaux : liste des bateaux communs � tous les joueurs.
	 */
	public void placementBateauIA(Plateau plateauDuJoueur, Joueur listedesBateaux){
		//LE JOUEUR UTILISE LE PLACEMENT AUTOMATIQUE
		System.out.println(" Mise en route du programme de placement automatique des bateaux ...");
		plateauDuJoueur.remplirAleatoirement(listedesBateaux); // On le fait remplir al�atoirement.
	}


	/** M�thode qui g�re le tir de l'IA selon son niveau de difficult�e (type)
	 * @param ia : plateau de l'IA
	 * @param ennemi : plateau sur lequel tirer.
	 */
	public void tirSelonNiveau(Plateau ia,Plateau ennemi){
		int[] coordonnes = new int[2];
		
		switch(type){
		case 1:
			coordonnes = this.tirDebileProfond(ia, ennemi);
			break;
		case 2:
			coordonnes = this.tirFacile(ia, ennemi);
			break;
		case 3:
			coordonnes = this.tirMoyen(ia, ennemi);;
			break;
		case 4:
			coordonnes = this.tirDifficile(ia, ennemi);;
			break;
		case 5 :
			coordonnes = this.tirImpossibru(ia, ennemi);;
			break;
		default :
			coordonnes = this.tirFacile(ia, ennemi);
			break;
		}
		
		ia.tirerJoueur(coordonnes, ennemi);
		Joueur.reponseAuTir(ennemi, coordonnes[0], coordonnes[1]);
	}

	/** M�thode qui g�re le tir en tant que d�bile profond.
	 * @param ia : plateau de l'IA
	 * @param ennemi : plateau sur lequel tirer.
	 * @return un tableau de 2 int qui repr�sente x et y.
	 */
	public int[] tirDebileProfond(Plateau ia,Plateau ennemi){
		boolean correct = false;
		int[] coordonnes = new int[2];
		int iterations = 0;
		int taillePlateauEnnemi = ennemi.plateauValeurs.length*ennemi.plateauValeurs[0].length ;

		while(correct==false){	
			int x = 0;
			int y = 0;

			// **** ON CHOISIT LES COORDONNES AU RANDOM **** //

			x = (int)(Math.random()*(ennemi.plateauValeurs.length)); // on choisi une case sur les x.
			y = (int)(Math.random()*(ennemi.plateauValeurs[0].length)); // on rep�re une case sur les y.

			// **** On v�rifie qu'elle ne sont pas sur un bateau ennemi (mode d�bile profond) ****//
			if(ennemi.dejaSubiTir(x,y)==false && Joueur.scannerCaseInterdite(ennemi, x, y)==false && ennemi.bateauPresent(x, y)==false){ // **** On v�rifie qu'un peu tirer sur la case ****//
				correct = true;
				coordonnes[0]=x;
				coordonnes[1]=y;
				iterations = 0;
			} else if (ennemi.bateauPresent(x, y)==true && iterations > taillePlateauEnnemi*2){ // **** si on a d�j� tir� sur toutes les cases sans bateaux.
				correct = true;
				coordonnes[0]=x;
				coordonnes[1]=y;
				iterations = 0;
			} else {
				correct = false;
				iterations++;
			}

		}// fin du while (bateauPossible==false)

		return coordonnes;
	}
	
	/** M�thode qui g�re le tir en tant que Facile.
	 * @param ia : plateau de l'IA
	 * @param ennemi : plateau sur lequel tirer.
	 * @return un tableau de 2 int qui repr�sente x et y.
	 */
	public int[] tirFacile(Plateau ia,Plateau ennemi){
		boolean correct = false;
		int[] coordonnes = new int[2];
		int iterations = 0;

		while(correct==false){	
			int x = 0;
			int y = 0;

			// **** ON CHOISIT LES COORDONNES AU RANDOM **** //

			x = (int)(Math.random()*(ennemi.plateauValeurs.length)); // on choisi une case sur les x.
			y = (int)(Math.random()*(ennemi.plateauValeurs[0].length)); // on rep�re une case sur les y.

			// **** On v�rifie qu'elle sont sur un bateau ennemi (mode IMPOSSIBLE) ****//
			if(ennemi.dejaSubiTir(x,y)==false && Joueur.scannerCaseInterdite(ennemi, x, y)==false && ennemi.bateauPresent(x, y)==false && Math.random()>0.4){ // **** On v�rifie qu'un peu tirer sur la case ****//
				correct = true;
				coordonnes[0]=x;
				coordonnes[1]=y;
				iterations = 0;
			} else if (ennemi.dejaSubiTir(x,y)==false && Joueur.scannerCaseInterdite(ennemi, x, y)==false && ennemi.bateauPresent(x, y)==true && Math.random()<0.4){ // **** si on a d�j� tir� sur toutes les cases avec bateaux. Pas possible mais bon.
				correct = true;
				coordonnes[0]=x;
				coordonnes[1]=y;
				iterations = 0;
			} else {
				correct = false;
				iterations++;
			}

		}// fin du while (bateauPossible==false)

		return coordonnes;
	}
	
	/** M�thode qui g�re le tir en tant que Moyen.
	 * @param ia : plateau de l'IA
	 * @param ennemi : plateau sur lequel tirer.
	 * @return un tableau de 2 int qui repr�sente x et y.
	 */
	public int[] tirMoyen(Plateau ia,Plateau ennemi){
		boolean correct = false;
		int[] coordonnes = new int[2];
		int iterations = 0;

		while(correct==false){	
			int x = 0;
			int y = 0;

			// **** ON CHOISIT LES COORDONNES AU RANDOM **** //

			x = (int)(Math.random()*(ennemi.plateauValeurs.length)); // on choisi une case sur les x.
			y = (int)(Math.random()*(ennemi.plateauValeurs[0].length)); // on rep�re une case sur les y.

			// **** On v�rifie qu'elle sont sur un bateau ennemi (mode IMPOSSIBLE) ****//
			if(ennemi.dejaSubiTir(x,y)==false && Joueur.scannerCaseInterdite(ennemi, x, y)==false){ // **** On v�rifie qu'un peu tirer sur la case ****//
				correct = true;
				coordonnes[0]=x;
				coordonnes[1]=y;
				iterations = 0;
			} else {
				correct = false;
				iterations++;
			}

		}// fin du while (bateauPossible==false)

		return coordonnes;
	}
	
	/** M�thode qui g�re le tir en tant que Difficile.
	 * @param ia : plateau de l'IA
	 * @param ennemi : plateau sur lequel tirer.
	 * @return un tableau de 2 int qui repr�sente x et y.
	 */
	public int[] tirDifficile(Plateau ia,Plateau ennemi){
		boolean correct = false;
		int[] coordonnes = new int[2];
		int iterations = 0;

		while(correct==false){	
			int x = 0;
			int y = 0;

			// **** ON CHOISIT LES COORDONNES AU RANDOM **** //

			x = (int)(Math.random()*(ennemi.plateauValeurs.length)); // on choisi une case sur les x.
			y = (int)(Math.random()*(ennemi.plateauValeurs[0].length)); // on rep�re une case sur les y.

			// **** On v�rifie qu'elle sont sur un bateau ennemi (mode IMPOSSIBLE) ****//
			if(ennemi.dejaSubiTir(x,y)==false && Joueur.scannerCaseInterdite(ennemi, x, y)==false && ennemi.bateauPresent(x, y)==true && Math.random()>0.4){ // **** On v�rifie qu'un peu tirer sur la case ****//
				correct = true;
				coordonnes[0]=x;
				coordonnes[1]=y;
				iterations = 0;
			} else if (ennemi.dejaSubiTir(x,y)==false && Joueur.scannerCaseInterdite(ennemi, x, y)==false && ennemi.bateauPresent(x, y)==false && Math.random()<0.4){ // **** si on a d�j� tir� sur toutes les cases avec bateaux. Pas possible mais bon.
				correct = true;
				coordonnes[0]=x;
				coordonnes[1]=y;
				iterations = 0;
			} else {
				correct = false;
				iterations++;
			}

		}// fin du while (bateauPossible==false)

		return coordonnes;
	}
	
	/** M�thode qui g�re le tir en tant que Impossibru.
	 * @param ia : plateau de l'IA
	 * @param ennemi : plateau sur lequel tirer.
	 * @return un tableau de 2 int qui repr�sente x et y.
	 */
	public int[] tirImpossibru(Plateau ia,Plateau ennemi){
		boolean correct = false;
		int[] coordonnes = new int[2];
		int iterations = 0;
		int taillePlateauEnnemi = ennemi.plateauValeurs.length*ennemi.plateauValeurs[0].length ;

		while(correct==false){	
			int x = 0;
			int y = 0;

			// **** ON CHOISIT LES COORDONNES AU RANDOM **** //

			x = (int)(Math.random()*(ennemi.plateauValeurs.length)); // on choisi une case sur les x.
			y = (int)(Math.random()*(ennemi.plateauValeurs[0].length)); // on rep�re une case sur les y.

			// **** On v�rifie qu'elle sont sur un bateau ennemi (mode IMPOSSIBLE) ****//
			if(ennemi.dejaSubiTir(x,y)==false && Joueur.scannerCaseInterdite(ennemi, x, y)==false && ennemi.bateauPresent(x, y)==true){ // **** On v�rifie qu'un peu tirer sur la case ****//
				correct = true;
				coordonnes[0]=x;
				coordonnes[1]=y;
				iterations = 0;
			} else if (ennemi.dejaSubiTir(x,y)==false && Joueur.scannerCaseInterdite(ennemi, x, y)==false && ennemi.bateauPresent(x, y)==false && iterations > taillePlateauEnnemi*2){ // **** si on a d�j� tir� sur toutes les cases avec bateaux. Pas possible mais bon.
				correct = true;
				coordonnes[0]=x;
				coordonnes[1]=y;
				iterations = 0;
			} else {
				correct = false;
				iterations++;
			}

		}// fin du while (bateauPossible==false)

		return coordonnes;
	}
}
