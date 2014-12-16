package codechanger.absyn;

public class PtrTypeName extends TypeName {
	public String label = "PtrTypeName";
	public TypeName typename;
	
	public PtrTypeName(TypeName tn) {
		typename = tn;
	}
	
	public String toString() {
		return typename.toString() + "*";
	}
}
