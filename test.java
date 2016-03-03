
public class test {
	
	public static void testBacktracking(Grid example){
		System.out.println("************************************************************");
		System.out.println("La grille initiale est:");
		example.printGrid();

		Backtracking.solveBacktracking(example);
	}
	
	public static void testDancingLinks(Grid example){
		System.out.println("************************************************************");
		System.out.println("La grille initiale est:");
		example.printGrid();
		
		DancingLinks d=new DancingLinks(example.val);
		int n=d.solve();
		Grid[] sol=d.retrieveResult();
		
		if(n>1){
			System.out.println(n+" solutions ont été trouvées par DancingLinks:");
			for(int i=0;i<d.solutions.nombre_de_solutions;i++){sol[i].printGrid();}
		}
		else{
			System.out.println(n+" solution a été trouvée par DancingLinks:");
			for(int i=0;i<d.solutions.nombre_de_solutions;i++){sol[i].printGrid();}
			
			//Si la solution est unique, on peut chercher la grille minimale correspondante.
			System.out.println("Et la grille minimale correspondante trouvée par DancingLinks est:");
			Grid minimale = Cleaning.getMinimalGrid(example);
			minimale.printGrid();
			DancingLinks de=new DancingLinks(minimale.val);
			int se=de.solve();
			System.out.println("En appliquant DancingLinks à celle-ci, on s'assure qu'il existe bien "+se+" solution.");
		}
	}	
	
	
	public static void testComparaison(Grid example){
		System.out.println("************************************************************");
		System.out.println("La grille initiale est:");
		example.printGrid();
		
		System.out.println("Backtracking en cours");
		long debut1 = System.currentTimeMillis();
		Backtracking.solveBacktracking(example);
		long l1 = System.currentTimeMillis()-debut1;
		
		long debut2 = System.currentTimeMillis();
		System.out.println("DancingLinks en cours");
		DancingLinks d=new DancingLinks(example.val);
		d.solve();
		Grid[] sol=d.retrieveResult();
		for(int i=0;i<d.solutions.nombre_de_solutions;i++){sol[i].printGrid();}
		long l2 = System.currentTimeMillis()-debut2;
		
		System.out.println("Backtracking a terminé en "+l1+"ms alors que DancingLinks a terminé en "+l2+"ms.");
	}
}
