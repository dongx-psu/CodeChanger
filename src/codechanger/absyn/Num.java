package codechanger.absyn;

public class Num extends Expr {
	public String label = "Num";
	public int value;
	
	public Num(int val) {
		value = val;
		has_para_cond = false;
		has_func_call = false;
	}
	
	public String toString() {
		return "" + value;
	}
}
