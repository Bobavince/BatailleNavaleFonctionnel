/**
 * @author Vincent F et Thomas B
 * Classe de gestion de la liste des bateaux sur le plateau.
 */

public class ListeBateau {
	//OBSOLOETE : // Liste des bateaux, avec dans la colonne 1 leur identifiant et la colonne 2 leur �tat (1 pour alive, 0 pour d�truit)
	Bateau[] listeBat; // Liste des bateaux en tant qu'objet.

	/** Cr�� une liste d'objet de type "Bateau"
	 */
	public ListeBateau(int nombreDeBateau){
		listeBat = new Bateau[nombreDeBateau];
	}

	/**
	 * @param numeroBateau : correspond au numero qui sera inscrit sur les cases du plateau, correspond � cet unique bateau
	 * @return Un boolean, vrai si le bateau existe encore, faux si le bateau a �t� d�truit
	 */
	public boolean bateauExiste(int numeroBateau){
		boolean existant = false;

		if(numeroBateau<=listeBat.length){			//s�curit� pour �viter des boundOutOfarray
			existant = listeBat[numeroBateau-1].alive ;
		}

		return existant;
	}

	/** M�thode pour couler un bateau.
	 * @param numeroBateau : correspond au numero qui sera inscrit sur les cases du plateau, correspond � cet unique bateau
	 */
	public void coulerBateau(int numeroBateau){
		if(numeroBateau<listeBat.length){			//s�curit� pour �viter des boundOutOfarray
			listeBat[numeroBateau].rendreCouler();			//On passe le bateau en "d�truit"
		}
	}
}