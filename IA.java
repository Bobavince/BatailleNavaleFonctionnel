import java.util.Scanner;

/**
 * @author Vincent F et Thomas B
 * Classe de gestion de l'IA
 */

public class IA {

	int type = -1 ;

	public IA(){
		
	}

	public String typeIA(){
		String pseudo = "" ;
		String answer = "";
		int type = -1;
		Scanner sc = new Scanner(System.in);

		System.out.println("Joueurs, quel type d'IA voulez vous ?");	
		System.out.println("\n1 - D�bile profond\n2 - facile\n3 - moyen\n4 - difficile\n5 - IMPOSSIBIRU");

		while(type != 1 && type != 2 && type != 3 && type != 4){
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

	public void placementBateauIA(Plateau plateauDuJoueur, Joueur listedesBateaux){
		//LE JOUEUR UTILISE LE PLACEMENT AUTOMATIQUE
		System.out.println(" Mise en route du programme de placement automatique des bateaux ...");
		plateauDuJoueur.remplirAleatoirement(listedesBateaux.typeDeFlotte); // On le fait remplir al�atoirement.
	}


	public void tirSelonNiveau(Plateau ia,Plateau ennemi){

	}

}
