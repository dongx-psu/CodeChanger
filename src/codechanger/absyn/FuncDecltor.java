package codechanger.absyn;

public class FuncDecltor extends Decltor {
	public String label = "FuncDecltor";
	public Para para;
	public boolean extend;
	
	public FuncDecltor(PlDecltor pd, Para pr, boolean flag) {
		pldecltor = pd;
		para = pr;
		extend = flag;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(pldecltor.toString() + "(");
		if (para != null) buf.append(para.toString());
		if (para != null && extend == true) buf.append(", ...");
		if (para == null && extend == true) buf.append("...");
		buf.append(")");
		return buf.toString();
	}
}
