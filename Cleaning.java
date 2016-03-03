import java.util.LinkedList;


public class Cleaning {

	int[][] elements;//infos sur les éléments initiaux
	ArbreMin recherche;//arbre de recerche
	int quant;//nombre d'éléments du sudoku a la base
	int depth=0;//indice donnant la profondeur de la recherche
	LinkedList<int[]> change;//les changements à faire sur le sudoku de base
	Grid g;//grid de travail


	public Cleaning(Grid g){
		this.g=g;

		int tr=0;//compte le nombre de valeurs prédéfinies
		for(int ligne=0;ligne<9;ligne++){
			for(int colonne=0;colonne<9;colonne++){
				if(g.val[ligne][colonne]!=0) {
					tr++;
				}
			}
		}
		elements=new int[tr][3];
		quant=tr; //nombre de valeurs prédéfinies
		int t=0; //met dans element les éléments prédéfinis
		for(int ligne=0;ligne<9;ligne++){
			for(int colonne=0;colonne<9;colonne++){
				if(g.val[ligne][colonne]!=0) {
					elements[t][0]=g.val[ligne][colonne];
					elements[t][1]=ligne;
					elements[t][2]=colonne;
					t++;
				}
			}
		}
		recherche=new ArbreMin(null,new LinkedList<ArbreMin>(),null);
		change=new LinkedList<int[]>(); //les modifications pour rendre minimal
	}

	public void nettoyage(){
		nettoyagerec(recherche,0);
	}

	public void nettoyagerec(ArbreMin a, int indice){ //créer un arbre pour trouver minimal

		while(indice<quant){
			int[] data=elements[indice];
			ArbreMin f=new ArbreMin(a,new LinkedList<ArbreMin>(),data);
			g.val[data[1]][data[2]]=0;

			DancingLinks d=new DancingLinks(g.val);
			d.precontraintes(g.val);

			if(d.solve()==1) {
				a.fils.add(f);
				nettoyagerec(f,indice+1);
			}
			g.val[data[1]][data[2]]=data[0];
			indice++;
		}
	}

	public void retrieveMin(){
		retrieveMinRec(recherche,0,new LinkedList<int[]>()); 	//initie les modifications
		for(int[] data:change){									//rends les modifications effectives
			g.val[data[1]][data[2]]=0;
		}
	}

	@SuppressWarnings("unchecked")
	private void retrieveMinRec(ArbreMin a, int courant, LinkedList<int[]> tampon){
		for(ArbreMin b:a.fils){
			tampon.addFirst(b.element);
			if(courant>depth) {change= (LinkedList<int[]>) tampon.clone();depth++;
			}
			retrieveMinRec(b,courant+1,tampon);
			tampon.poll();
		}
	}

	public static Grid getMinimalGrid(Grid complete){
		Grid minimale=new Grid(complete.sudoko,Grid.duplicateMatrix(complete.val));
		Cleaning c=new Cleaning(minimale);
		c.nettoyage();
		c.retrieveMin();
		return minimale;
	}

}
