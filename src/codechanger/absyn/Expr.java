package codechanger.absyn;

import java.util.LinkedList;

public abstract class Expr extends Absyn {
	public boolean has_para_cond;
	public boolean has_func_call;
	public LinkedList<String> func_names = new LinkedList<String>();
}
