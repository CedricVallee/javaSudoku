
public class DancingLinksStructure { //classe servant à créer la structure utilisée dans DancingLinks
	Cell h;
	Cell[] header;
	boolean[][] matrice;

	public DancingLinksStructure(){
		matrice=new boolean[9*81][4*81];
		remplirConditionBloc();
		remplirConditionLigne();
		remplirConditionColonne();
		remplirConditionLigneColonne();
		h=new Cell(null,null,null,null,0,0);
		Cell tamponHeader=h;
		header=new Cell[4*81];

		for (int colonne=0;colonne<4*81;colonne++){   //initialisation des headers
			header[colonne]=new Cell(tamponHeader,null,null,null,0,colonne);
			header[colonne].U=header[colonne];
			header[colonne].D=header[colonne];
			tamponHeader.R=header[colonne];
			tamponHeader=header[colonne];
		}

		h.L=header[4*81-1];  //bouclage de h
		header[4*81-1].R=h;
		for(int ligne=0;ligne<9*81;ligne++){  //init dancing links
			Cell tamponLigne=null;
			Cell premier=null;
			for (int colonne=0;colonne<4*81;colonne++){
				if(matrice[ligne][colonne]){
					Cell element=new Cell(tamponLigne,null,header[colonne].U,header[colonne],ligne,colonne);
					if(premier==null) {premier=element;tamponLigne=element;}

					if(header[colonne].U==null) System.out.println("eat");
					header[colonne].U.D=element;
					header[colonne].U=element;
					header[colonne].row=header[colonne].row+1;
					tamponLigne.R=element;
					tamponLigne=element;
				}
			}

			premier.L=tamponLigne;  //bouclage premier et dernier élément
			tamponLigne.R=premier;
		}
	}

	public static int[] getInfo(int ligne){    //retourne pour un indice de ligne des possilibités, la possiblité dans le sudoku
		int[] possibility=new int[4];
		possibility[0]=ligne/81+1;   //valeur
		int reste=ligne%81;
		possibility[1]=reste/9;  //ligne
		reste=reste%9;
		possibility[2]=reste;   //colonne

		int nombre=ligne%81;
		int dizaine=nombre/27;
		int unité=(nombre%9)/3;
		possibility[3]=unité+3*dizaine;   //bloc
		return possibility;
	}

	private int[] getContrainteLigne(int colonne){
		int[] contrainte=new int[2];
		contrainte[0]=colonne/9+1;  //valeur
		contrainte[1]=colonne%9;  //ligne
		return contrainte;
	}

	private void remplirConditionLigne(){//remplit le premier bloc contrainte de la matrice
		for (int colonne=0;colonne<81;colonne++){
			for(int ligne=0;ligne<729;ligne++){
				int[] info=getInfo(ligne);
				int[] contrainte=getContrainteLigne(colonne);
				if(info[0]==contrainte[0] && info[1]==contrainte[1])
					this.matrice[ligne][colonne]=true;
			}
		}
	}

	private static int[] getContrainteColonne(int colonne){
		int[] contrainte=new int[2];
		contrainte[0]=(colonne-81)/9+1;  //valeur
		contrainte[1]=(colonne-81)%9;  //colonne
		return contrainte;
	}

	private void remplirConditionColonne(){   //remplit le bloc contrainte colonne de la matrice
		for (int colonne=81;colonne<2*81;colonne++){
			int[] contrainte=getContrainteColonne(colonne);
			for(int ligne=0;ligne<9*81;ligne++){
				int[] info=getInfo(ligne);
				if(info[0]==contrainte[0] && info[2]==contrainte[1])
					this.matrice[ligne][colonne]=true;
			}
		}
	}

	private int[] getContrainteLigneColonne(int colonne){
		int[] contrainte=new int[2];
		contrainte[0]=(colonne-2*81)/9;  //ligne
		contrainte[1]=(colonne-2*81)%9;  //colonne
		return contrainte;
	}

	private void remplirConditionLigneColonne(){   //remplit le bloc contrainte ligne colonne de la matrice
		for (int colonne=2*81;colonne<3*81;colonne++){
			for(int ligne=0;ligne<729;ligne++){
				int[] info=getInfo(ligne);
				int[] contrainte=getContrainteLigneColonne(colonne);
				if(info[1]==contrainte[0] && info[2]==contrainte[1])
					this.matrice[ligne][colonne]=true;
			}
		}
	}

	private static int[] getContrainteBloc(int colonne){
		int[] contrainte=new int[2];
		contrainte[0]=(colonne-3*81)/9+1;  //valeur
		contrainte[1]=(colonne-3*81)%9;  //bloc
		return contrainte;
	}

	private void remplirConditionBloc(){   //remplit le bloc contrainte bloc de la matrice
		for (int colonne=3*81;colonne<4*81;colonne++){
			for(int ligne=0;ligne<729;ligne++){
				int[] info=getInfo(ligne);
				int[] contrainte=getContrainteBloc(colonne);
				if(info[0]==contrainte[0] && info[3]==contrainte[1])
					this.matrice[ligne][colonne]=true;
			}
		}
	}
}
