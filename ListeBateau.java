/**
 * @author Vincent F et Thomas B
 * Classe de gestion de la liste des bateaux sur le plateau.
 */

public class ListeBateau {
	//OBSOLOETE : // Liste des bateaux, avec dans la colonne 1 leur identifiant et la colonne 2 leur �tat (1 pour alive, 0 pour d�truit)
	Bateau[] listeBat; // Liste des bateaux en tant qu'objet.
			
	/**
	 * 
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


/* PREMIERE VERSION :


public ListeBateau(){
//Premi�re version de cette m�thode qui cr� la liste comme une liste normale.
int[][] liste = new int[5][5];

//on remplis le tableau avec 5 bateaux num�rot�s 1 � liste.length+1
for(int i = 0; i<liste.length ; i++){
	liste[i][0]=i+1; // On num�rote de 1 � liste.length+1 (ici 5)
	liste[i][1]=1;	// on rend les bateaux sur le statut "alive"
}
}

public boolean bateauExiste(int numeroBateau){
boolean existant = false;

if(numeroBateau<=liste.length){			//s�curit� pour �viter des boundOutOfarray
	if(liste[numeroBateau-1][1]==1){	//Si le bateau est alive
		existant = true;				//on dit qu'il existe
	}
}

return existant;
}

public void coulerBateau(int numeroBateau){
if(numeroBateau<=liste.length){			//s�curit� pour �viter des boundOutOfarray
	liste[numeroBateau][1]=0;			//On passe le bateau en "d�truit"
}
}

*/