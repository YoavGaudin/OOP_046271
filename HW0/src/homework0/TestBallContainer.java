package homework0;

/**
 * 
 * @author yoavg
 * @Description A class used to test BallContainer and Ball classes
 */
public class TestBallContainer {
	/**
	 * @return None
	 * @requires None
	 * @modifies None
	 * @effects None
	 * 
	 */
	public static void main(String[] args){
		System.out.println("Testing BallContainer...");
		BallContainer container = new BallContainer();
		
		
		double vol_a = 1.1, vol_b = 2.2, vol_c = 1.1;
		Ball a = new Ball(vol_a);
		Ball b = new Ball(vol_b);
		Ball c = new Ball(vol_c);
		
		boolean r;
		System.out.print("Add balls...");
		assert container.add(a);
		assert container.add(b);
		assert container.add(c);
		assert !(r = container.add(null)): r;
		System.out.println("OK");
		
		System.out.print("Check contains()...");
		assert container.contains(a);
		assert container.contains(b);
		assert container.contains(c);
		System.out.println("OK");
		
		System.out.print("Try duplicate...");
		assert !container.add(a);
		System.out.println("OK");
		
		System.out.print("Check size and volume...");
		assert container.size() == 3;
		assert container.getVolume() == vol_a + vol_b + vol_c : "volume is: " + container.getVolume();
		System.out.println("OK");
		
		System.out.print("Try remove and check again...");
		assert container.remove(a);
		assert !container.remove(a);
		assert container.size() == 2;
		double vol = container.getVolume();
		assert vol == vol_b + vol_c : "Wrong container volume. 0.3 != " + vol;
		System.out.println("OK");
		
		System.out.print("Try changing volume of ball...");
		b.setVolume(vol_a);
		vol = container.getVolume();
		assert vol == vol_a + vol_c : "Wrong container volume. " + vol + " != " + (vol_a + vol_c);
		System.out.println("OK");
	}
}
