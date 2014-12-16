package codechanger.absyn;

public class TypeSizeExpr extends Expr {
	public String label = "TypeSizeExpr";
	public TypeName ty;
	
	public TypeSizeExpr(TypeName t) {
		ty = t;
		has_para_cond = false;
		has_func_call = true;
	}
	
	public String toString() {
		return "sizeof(" + ty.toString() + ")";
	}
}
