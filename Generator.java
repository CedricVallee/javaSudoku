
public class Generator {
	static int nombre_solutions;
	static int tamp;

	// Cette méthode fournit une grille aléatoire valide.
	public static int[][] generateBasis(int n){
		int[][] a = new int[n*n][n*n];
		for(int k=0;k<50;k++){
			int i = (int)(Math.random()*9);
			int j = (int)(Math.random()*9);
			int v = (int)(Math.random()*9+1);
			if(Backtracking.checkLigneColonneCarre(a,v,i,j)){a[i][j]=v;}
		}
		DancingLinks d=new DancingLinks(Grid.duplicateMatrix(a));
		nombre_solutions=d.solve();
		if(nombre_solutions==0){return generateBasis(n);}
		else{return a;}
	}

	// Cette méthode transforme la grille précédente en grille minimale
	public static Grid generateRandomGrid(int n){
		long debut = System.currentTimeMillis();
		int[][] a = generateBasis(n);
		while(nombre_solutions!=1){
			int i = (int)(Math.random()*9);
			int j = (int)(Math.random()*9);
			int v = (int)(Math.random()*9+1);
			if(((i==8)&&(j==8))||(System.currentTimeMillis()-debut>25)){return generateRandomGrid(n);}
			tamp=a[i][j];
			if(Backtracking.checkLigneColonneCarre(a,v,i,j)){
				a[i][j]=v;
				DancingLinks d1=new DancingLinks(Grid.duplicateMatrix(a));
				nombre_solutions=d1.solve();
				if(nombre_solutions==0){a[i][j]=tamp;}
			}
		}
		System.out.println("Une grille aléatoire à solution unique a été trouvée. La minimalisation est en cours.");
		Grid g =new Grid(n,a);
		Cleaning c=new Cleaning(g);
		c.nettoyage();
		c.retrieveMin();
		return g;	
	}

	static boolean backtrackingPourUnResultat(int[][] t, int n, int position){
		int taille = n*n;
		if (position == taille*taille){return true;}
		int i = position/taille;
		int j = position%taille;
		if (t[i][j]!=0){return backtrackingPourUnResultat(t,n,position+1);}
		for (int v=1;v<=taille;v++){
			if (Backtracking.checkLigneColonneCarre(t,v,i,j)){
				t[i][j] = v;
				if(backtrackingPourUnResultat(t,n,position+1)){return true;}
			}
		}
		t[i][j] = 0;
		return(false);
	}

}
