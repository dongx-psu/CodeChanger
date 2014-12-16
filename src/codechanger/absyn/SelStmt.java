package codechanger.absyn;

import codechanger.Main;
import codechanger.Util;

public class SelStmt extends Stmt {
	public String label = "SelStmt";
	public Exprs cond;
	public Stmt act1, act2;
	
	
	public SelStmt(Exprs e, Stmt s, Stmt x) {
		cond = e;
		act1 = s;
		act2 = x;
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
		}
		buf.append(Util.generateIndention() + "if ");
		buf.append(condName);
		if (!(act1 instanceof CpdStmt)) {
			buf.append("\n");
			++Util.indent;
		} else Util.mark = true;
		buf.append(act1.toString());
		if (!(act1 instanceof CpdStmt)) --Util.indent;
		if (act2 != null) {
			buf.append(" else");
			if (act2 instanceof CpdStmt)
				Util.mark = true;
			else if (act2 instanceof SelStmt && ((SelStmt)act2).cond.has_func_call) {
				buf.append(" {\n");
				++Util.indent;
			} else if (act2 instanceof SelStmt) {
				Util.mark = true;
			} else {
				buf.append("\n");
				++Util.indent;
			}
			buf.append(act2.toString());
			if (!(act2 instanceof SelStmt) && !(act2 instanceof CpdStmt)) --Util.indent;
			if (act2 instanceof SelStmt && ((SelStmt)act2).cond.has_func_call) {
				--Util.indent;
				buf.append("\n" + Util.generateIndention() + "}");
			}
		}
		return buf.toString();
	}
}
