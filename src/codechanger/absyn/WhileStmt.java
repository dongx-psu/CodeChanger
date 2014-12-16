package codechanger.absyn;

import codechanger.Main;
import codechanger.Util;

public class WhileStmt extends IterStmt {
	public String label = "WhileStmt";
	public Exprs cond;
	public Stmt body;
	
	public WhileStmt(Exprs e, Stmt s) {
		cond = e;
		body = s;
		if (cond.has_func_call && Util.checkPara(cond.list.getLast())) {
			Util.insertShortCurcuitToNameSet(cond.list.getLast());
		} else if (cond.has_func_call) {
			Util.result_name_set.add(Util.makeVarName(cond));
		}
	}
	
	public String toString() {
		/*if (cond.has_func_call && Util.checkPara(cond.list.getLast())) {
			Util.insertShortCurcuitToMap(cond.list.getLast());
		} else if (cond.has_func_call) {
			Util.post_fix_map.put(this, ++Util.post_fix_cnt);
		}*/
		StringBuffer buf = new StringBuffer();
		String condName = cond.toString();
		if (Util.selected_func && cond.has_func_call) {
			//Notice only value of the last expression would be meaningful for the predicate
			buf.append(Util.generateIndention() + "while (true) {\n");
			++Util.indent;
			if (Util.checkPara(cond.list.getLast())) {
				condName = Util.handleShortCircuit(cond.list.getLast(), buf);
			} else {
				condName = Util.makeVarName(cond);
				buf.append(Util.generateIndention() + "TRACE_START();\n");
				buf.append(Util.generateIndention() + condName + " = " + cond.toString() + ";\n");
				buf.append(Util.generateIndention() + "TRACE_END(" + (++Main.post_fix_cnt) + ");\n");
				Main.insertFuncCall(cond);
				condName = "(" + condName + ")";
			}
			buf.append(Util.generateIndention() + "if (!" + condName + ") break;\n");
			if (body instanceof CpdStmt) {
				((CpdStmt)body).no_first_bracket = true;
				buf.append(body.toString());
			} else {
				buf.append(body.toString());
				--Util.indent;
				buf.append("}");
			}
			return buf.toString();
		} else {
			buf.append(Util.generateIndention() + "while (" + cond.toString() + ")");
			if (body instanceof CpdStmt) Util.mark = true;
			else {
				buf.append("\n");
				++Util.indent;
			}
			buf.append(body.toString());
			if (!(body instanceof CpdStmt)) --Util.indent;
			return buf.toString();
		}
	}
}
