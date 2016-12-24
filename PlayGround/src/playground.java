import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class playground {
	
	
	public static void main(String args[]) {
		List<String> names = new LinkedList<>();
		names.add("n3");
		names.add("n2");
		names.add("n1");
		Collections.sort(names);
		String str = names.toString();
		str = str.replaceAll(",|\\[|\\]", "");
		System.out.println("names: " + names + " ## " + str);
	}
}
