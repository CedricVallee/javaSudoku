
public class Main {

	// La grille 1 possède 3 solutions.
	static int[][] valeurs1 = 
		{{9,0,0,1,0,0,0,0,5},
		{0,0,5,0,9,0,2,0,1},
		{8,0,0,0,4,0,0,9,0},
		{7,0,0,0,8,0,0,0,0},
		{0,0,0,7,0,0,0,0,0},
		{1,3,4,0,2,6,0,0,9},
		{2,0,0,3,0,0,0,0,6},
		{0,0,0,2,0,0,9,0,0},
		{0,0,1,9,0,4,5,7,0}};
	static Grid example1 = new Grid(3,valeurs1);

	// La grille 2 possède une unique solution mais n'est pas minimale.
	static int[][] valeurs2 = 
		{{5,3,0,0,0,0,0,0,0},
		{6,0,0,1,9,5,0,0,0},
		{0,9,8,0,0,0,0,6,0},
		{8,0,0,0,6,0,0,0,3},
		{4,0,0,8,0,3,0,0,1},
		{7,0,0,0,2,0,0,0,6},
		{0,6,0,0,0,0,2,8,0},
		{0,0,0,4,1,9,0,0,5},
		{0,0,0,0,8,0,0,7,9}};
	static Grid example2 = new Grid(3,valeurs2);

	// La grille 3 possède 3 solutions.
	static int[][] valeurs3 = 
		{{0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,3,0,8,5},
		{0,0,1,0,2,0,0,0,0},
		{0,0,0,5,0,7,0,0,0},
		{0,0,4,0,0,0,1,0,0},
		{0,9,0,0,0,0,0,0,0},
		{5,0,0,0,0,0,0,7,3},
		{0,0,2,0,1,0,0,0,0},
		{0,0,0,0,4,0,0,0,9}};
	static Grid example3 = new Grid(3,valeurs3);


	public static void main(String[] args) {
		long debut = System.currentTimeMillis();

		//	test.testBacktracking(example1);
		//	test.testBacktracking(example2);

		//	test.testDancingLinks(example1);
		//	test.testComparaison(example3);
		
		//	test.testDancingLinks(example3);

			(Generator.generateRandomGrid(3)).printGrid();

		System.out.println("Le test entier a été effectué en "+(System.currentTimeMillis()-debut)+" millisecondes");
	}

}
