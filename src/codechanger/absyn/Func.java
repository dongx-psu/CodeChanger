package codechanger.absyn;

import codechanger.Main;
import codechanger.Util;

public class Func extends Decl {
	public String label = "Func";
	public Ty typespec;
	public PlDecltor pldector;
	public Para para;
	public boolean extend;
	public CpdStmt body;
	
	public Func(Ty ts, PlDecltor pdtor, Para pr, boolean flag, CpdStmt cs) {
		typespec = ts;
		pldector = pdtor;
		para = pr;
		extend = flag;
		body = cs;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(Util.generateIndention() + typespec.toString() + " " + pldector.toString() + "(");
		if (para != null) buf.append(para.toString());
		if (extend == true) buf.append(", ...");
		if (para == null && extend == true) buf.append("...");
		buf.append(")\n");
		
		if (pldector.toString().equals(Main.selected_func_name)) {
			body.flag = true;
			Util.selected_func = true;
		}
		buf.append(body.toString());
		if (pldector.toString().equals(Main.selected_func_name)) Util.selected_func = false;
		return buf.toString();
	}
}
