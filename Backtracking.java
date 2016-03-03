import java.util.ArrayList;
import java.util.List;


public class Backtracking{

	/* Cette m�thode permet de v�rifier qu'une valeur v n'est pas pr�sente dans la ligne, la colonne ou le petit carr� de t[i][j]
	 * (cf. r�gles du sudoku, partie 2 du rapport)
	 */
	static boolean checkLigneColonneCarre (int[][] t, int v, int i, int j){
		for (int k=0;k<9;k++){if((t[i][k]==v)||(t[k][j]==v)){return false;}}
		int a = 3*(i/3);
		int b = 3*(j/3);
		for(int i1=a;i1<a+3;i1++){
			for(int j1=b;j1<b+3;j1++){
				if(t[i1][j1]==v){return false;}
			}
		}
		return true;
	}

	/* Cette m�thode remplit les cases de la matrice t de gauche � droite et de haut en bas, en v�rifiant � chaque ajout que la valeur
	 * n'est pas pr�sente sur la ligne, sur la colonne ou dans le petit carr� de la case t[i][j]. Si ce remplissage m�ne � une impasse
	 * (plus aucun ajout possible et grille non remplie), l'algorithme revient au dernier embranchement non encore emprunt� et continue
	 * son exploration � partir de l�. Si le remplissage termine, on stocke cette solution dans une liste que l'on retourne � la fin du
	 * programme.
	 * La matrice de bool�ens indique quels sont les chiffres initialement pr�sents dans la grille afin de les laisser inchang�s.
	 */
	static List<int[][]> solver(int[][] t, int n, int position){

		boolean[][] initiaux = new boolean[9][9];
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(t[i][j]!=0){initiaux[i][j]=true;}
			}
		}

		List<int[][]> sol = new ArrayList<int[][]>();
		int taille = n*n;
		if (position == taille*taille){sol.add(Grid.duplicateMatrix(t)); return sol;}
		int i = position/taille;
		int j = position%taille;
		if (initiaux[i][j]){return solver(t,n,position+1);}
		for (int v=1;v<=taille;v++){
			if (checkLigneColonneCarre(t,v,i,j)){
				t[i][j] = v;
				sol.addAll(solver(t,n,position+1));
			}
		}
		if(initiaux[i][j]){}
		else{t[i][j] = 0;}
		return(sol);
	}


	// Cette m�thode applique solver et imprime la liste des solutions.
	public static void solveBacktracking(Grid init){
		List<int[][]> l = solver(init.val,init.sudoko,0); int n = l.size();
		if(n>2){System.out.println(n+" solutions ont �t� trouv�es par Backtracking:");}
		else{System.out.println(n+" solution a �t� trouv�e par Backtracking:");}

		for(int[][] t:l){
			Grid.printMatrix(t,init.sudoko);
		}
	}

}