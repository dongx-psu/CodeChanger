package codechanger;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import codechanger.absyn.Expr;

public class Main {
	public static String selected_func_name = "";
	public static String output_name = "output_source.c";
	public static int post_fix_cnt = 0;
	public static ArrayList<String> func_calls = new ArrayList<String>();
	
	public static void main(String argv[]) {
		if (argv.length != 3) {
			System.err.println("Usage: java -jar CodeChanger.jar /path/to/a/cpp/input/source/file function_name /path/to/the/entire/project/directory");
			System.exit(1);
		} else {
			try {
				InputStream inp = new FileInputStream(argv[0]);
				selected_func_name = argv[1];
				
				Parser parser = new Parser(inp);
				java_cup.runtime.Symbol parseTree = null;
				try {
					parseTree = parser.parse();
				} catch (Throwable e) {
					e.printStackTrace();
					throw new Error(e.toString());
				} finally {
					inp.close();
				}
				
				PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(argv[2] + "/" + output_name)));
				out.println(parseTree.value.toString());
				
				System.out.println(post_fix_cnt);
				System.out.print("[");
				if (func_calls.size() != 0) {
					System.out.print(func_calls.get(0));
					for (int i = 1; i < func_calls.size(); ++i)
						System.out.print(", " + func_calls.get(i));
				}
				System.out.print("]\n");
				
				out.close();
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public static void insertFuncCall(Expr expr) {
		String key = Util.makeFuncCall(expr);
		if (Util.func_call_sit.containsKey(key)) {
			int pos = Util.func_call_sit.get(key);
			if (pos != -1) {
				func_calls.set(pos, key + "-" + Util.func_call_posfix.get(key));
				Util.func_call_sit.put(key, -1);
			}
			func_calls.add(key + "-" + post_fix_cnt);
		} else {
			Util.func_call_sit.put(key, func_calls.size());
			Util.func_call_posfix.put(key, post_fix_cnt);
			func_calls.add(key);
		}
	}
}
