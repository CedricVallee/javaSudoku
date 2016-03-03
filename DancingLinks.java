import java.util.LinkedList;


public class DancingLinks {
	int[][] matrice;
	public Cell h;
	public Cell[] header;
	public Arbre solutions;
	int[][] values;

	public DancingLinks(int[][] value){
		values=value;
		DancingLinksStructure structure =new DancingLinksStructure();
		h=structure.h;
		header=structure.header;
		precontraintes(value);
		solutions=new Arbre(null, new LinkedList<Arbre>());
	}

	public int solve(){
		return solveInterne(solutions);
	}

	int solveInterne(Arbre a){
		if(h.R==h) return 1;
		Cell min=getMin();
		if(min.row!=0){
			cover(min.col);
			int res=0;
			for(Cell tamponColonne=min.D;tamponColonne!=min;tamponColonne=tamponColonne.D){
				Arbre fils=new Arbre(a, new LinkedList<Arbre>());
				fils.cell=tamponColonne;
				a.fils.addFirst(fils);

				for(Cell tamponLigne=tamponColonne.R;tamponLigne!=tamponColonne;tamponLigne=tamponLigne.R)
					cover(tamponLigne.col);

				int sous_res=solveInterne(fils);
				res+=sous_res;

				for(Cell tamponLigne=tamponColonne.L;tamponLigne!=tamponColonne;tamponLigne=tamponLigne.L){uncover(tamponLigne.col);}

				if(sous_res==0) a.fils.poll();
				a.nombre_de_solutions=res;
			}
			uncover(min.col);
			return res;
		}
		else return 0;
	}


	Cell getMin(){ //donne la colonne avec le minimum de possibilités restantes
		int s=16;
		Cell c=null;
		for(Cell q=h.R;q!=h;q=q.R)
			if(q.row<s){
				c=q;
				s=q.row;
			}
		return c;
	}

	void cover(int colonne){
		Cell c=header[colonne];
		(c.L).R=c.R;
		(c.R).L=c.L;
		for(Cell tamponColonne=c.D;tamponColonne!=c;tamponColonne=tamponColonne.D){
			for(Cell tamponLigne=tamponColonne.R;tamponLigne!=tamponColonne;tamponLigne=tamponLigne.R){
				tamponLigne.hide();
				header[tamponLigne.col].row--;
			}
		}	
	}

	void uncover(int colonne){
		Cell c=header[colonne];
		for(Cell tamponColonne=c.U;tamponColonne!=c;tamponColonne=tamponColonne.U){
			for(Cell tamponLigne=tamponColonne.L;tamponLigne!=tamponColonne;tamponLigne=tamponLigne.L){
				header[tamponLigne.col].row++;
				tamponLigne.unhide();
			}
		}
		(c.L).R=c;
		(c.R).L=c;
	}


	public Grid[] retrieveResult(){   //remplit les solutions dans Grid[]
		int nombre_solutions=solutions.nombre_de_solutions;
		Grid[] reponse=new Grid[nombre_solutions];

		int[][][] sudoku=new int[nombre_solutions][9][9];
		for(int nb=0;nb<nombre_solutions;nb++)
			for (int ligne=0;ligne<9;ligne++)
				for(int colonne=0;colonne<9;colonne++)
					if(values[ligne][colonne]!=0){sudoku[nb][ligne][colonne]=values[ligne][colonne];}

		retrieveResultIntern(sudoku, 0, nombre_solutions-1, solutions);

		for(int i=0;i<nombre_solutions;i++){
			reponse[i]=new Grid(3,sudoku[i]);
		}
		return reponse;
	}


	private void retrieveResultIntern(int[][][] sudoku, int premier, int dernier, Arbre a){   
		//remplit des tableaux de sudoku et pas des grid directement

		if(a.pere!=null){
			int[] addData=DancingLinksStructure.getInfo(a.cell.row);
			if(a.fils.isEmpty()){
				sudoku[premier][addData[1]][addData[2]]=addData[0];
				return;
			}
			for(int i=premier;i<=dernier;i++){
				sudoku[i][addData[1]][addData[2]]=addData[0];
			}
		}
		int temp=premier;
		for(Arbre b:a.fils){
			retrieveResultIntern(sudoku, temp, temp+b.nombre_de_solutions-1, b);
			temp=temp+b.nombre_de_solutions;
		}
	}


	public void precontraintes(int [][] val){
		for (int ligne=0;ligne<9;ligne++){
			for (int colonne=0;colonne<9;colonne++){
				if(val[ligne][colonne]!=0){
					Cell destruct=findCell(val[ligne][colonne],ligne,colonne);
					cover(destruct.col);
					for(Cell tamponLigne=destruct.R;tamponLigne!=destruct;tamponLigne=tamponLigne.R)
						cover(tamponLigne.col);
				}
			}
		}
	}


	private Cell findCell(int valeur, int ligne, int colonne){  //find cell in Ligne contraintes dans la dancing link
		int LigneMat=(valeur-1)*81+9*ligne+colonne;
		int ColMat=(valeur-1)*9+ligne;
		Cell tampon=header[ColMat].D;
		while(tampon.row!=LigneMat){
			tampon=tampon.D;
		}
		return tampon;
	}
}
