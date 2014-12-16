package codechanger.absyn;

public class VarDecl extends Declar {
	public String label = "VarDecl";
	public Ty type;
	public InitDecltors initdecltors;
	
	public VarDecl(Ty t, InitDecltors idtors) {
		type = t;
		initdecltors = idtors;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(type.toString() + " ");
		if (initdecltors != null) buf.append(initdecltors.toString());
		buf.append(";");
		return buf.toString();
	}
}
