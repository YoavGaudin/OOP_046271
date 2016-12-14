package playground;

public class AngleDistance {
	public static double getAnglesDiff(double angle1, double angle2) {
  		double diff = angle2 - angle1;
  		if (diff >= -180 && diff <= 180) {
  			return diff;
  		}
  		else {
  			return diff - 360 * Math.signum(diff);
  		}
  	}
	
	public static void main(String[] args) {
		for (double i = 0 ; i < 360 ; i+=90){
			for (double j = 0 ; j < 360 ; j+=10){
				System.out.println(j + " - " + i + " = " + AngleDistance.getAnglesDiff(i,j));
				
			}
		}
		
	}
}
