package codechanger.absyn;

public class PlIniter extends Initer {
	public String label = "PlIniter";
	public Expr aexpr;
	
	public PlIniter(Expr e) {
		aexpr = e;
	}
	
	public String toString() {
		return aexpr.toString();
	}
} 
