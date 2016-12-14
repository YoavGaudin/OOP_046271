package playground;
import java.text.DecimalFormat;

public class DecimalFormatterTest {
	public static void main(String[] args) {
		double n = 123.123;
		DecimalFormat format = new DecimalFormat();
		format.applyPattern("#.#");
		System.out.println(format.format(n));
	}
}
