
public class Grid {

	int sudoko;
	public int[][] val;

	public Grid(int n, int[][] v){
		if(v.length==n*n){
		sudoko=n;	
		val=v;
		}
		else{System.out.println("Paramètres non compatibles");}
	}

	static void bordure(int n){
		int bord= (n*n-1)*n-1;
		System.out.print(" ");
		for(int j=0;j<bord;j++){System.out.print("-");}
		System.out.print(" \n");
	}

	public static void printMatrix(int[][] t, int n){
		bordure(n);
		for(int k=0;k<n;k++){
			for(int i=0;i<n;i++){
				System.out.print("|");
				for(int j=0;j<n;j++){
					System.out.print(" "+t[i+3*k][3*j]+" ");
					System.out.print(t[i+3*k][3*j+1]+" ");
					System.out.print(t[i+3*k][3*j+2]+" |");
				}	
				System.out.println();
			}
			bordure(n);
		}
	}

	void printGrid(){
		int n = sudoko;
		int[][] t = val;
		printMatrix(t,n);
	}

	public static int[][] duplicateMatrix(int[][] t){
		int taille = t.length;
		int[][] copie = new int[taille][taille];
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
				copie[i][j]=t[i][j];
			}
		}
		return copie;
	}

}