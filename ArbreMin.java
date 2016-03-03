import java.util.LinkedList;


public class ArbreMin {
	LinkedList<ArbreMin> fils;
	ArbreMin pere;
	int[] element;

	ArbreMin(ArbreMin pere, LinkedList<ArbreMin> fils, int[] element){
		this.pere=pere;
		this.fils=fils;
		this.element=element;
	}
}
