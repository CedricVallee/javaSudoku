import java.util.LinkedList;


public class Arbre {
	LinkedList<Arbre> fils;
	Arbre pere;
	int nombre_de_solutions;
	Cell cell;

	public Arbre(Arbre pere, LinkedList<Arbre> fils){
		this.pere=pere;
		this.fils=fils;
		this.cell=null;
		this.nombre_de_solutions=0;
	}

	void incr(){
		nombre_de_solutions++;
	}

	void decr(){
		nombre_de_solutions--;
	}

}
