package codechanger.absyn;

public class InitDecltor {
	public String label = "InitDecltor";
	public Decltor decltor;
	public Initer initer;
	
	public InitDecltor(Decltor dtor, Initer iter) {
		decltor = dtor;
		initer = iter;
	}
	
	public String toString() {
		if (initer == null) return decltor.toString();
		else return decltor.toString() + " = " + initer.toString();
	}
}
