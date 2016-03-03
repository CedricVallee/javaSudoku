
public class Cell {
	Cell L,R,U,D;
	int row,col;

	public Cell(Cell L, Cell R, Cell U, Cell D, int row, int col){
		if(L==null) L=this;
		if(R==null) R=this;
		if(U==null) U=this;
		if(D==null) D=this;
		this.L=L;this.R=R;this.U=U;this.D=D;this.row=row;this.col=col;
	}

	void hide(){
		U.D=D;
		D.U=U;
	}

	void unhide(){
		D.U=this;
		U.D=this;
	}

}