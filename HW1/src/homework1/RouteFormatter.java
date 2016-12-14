package homework1;

import java.util.Iterator;


/**
 * A RouteFormatter class knows how to create a textual description of
 * directions from one location to another. The class is abstract to
 * support different textual descriptions.
 */
public abstract class RouteFormatter {
	private enum Turn {
			STRIGHT, RIGHT_SOFT, RIGHT, RIGHT_HARD, UTURN, LEFT_HARD, LEFT, LEFT_SOFT
		}
  	/**
     * Give directions for following this Route, starting at its start point
     * and facing in the specified heading.
     * @requires route != null && 
     * 			0 <= heading < 360
     * @param route the route for which to print directions.
   	 * @param heading the initial heading.
     * @return A newline-terminated directions <tt>String</tt> giving
     * 	       human-readable directions from start to end along this route.
     **/
  	public String computeDirections(Route route, double heading) {
  		String directions = "";
  		Iterator<GeoFeature> gf = route.getGeoFeatures();
  		double new_heading = heading;
  		while (gf.hasNext()) {
  			GeoFeature curr_gf = gf.next(); 			
  			directions = directions.concat(this.computeLine(curr_gf, new_heading));
  			new_heading = curr_gf.getEndHeading();
  		}
  		return directions;
  	}


  	/**
     * Computes a single line of a multi-line directions String that
     * represents the instructions for traversing a single geographic
     * feature.
     * @requires geoFeature != null
     * @param geoFeature the geographical feature to traverse.
   	 * @param origHeading the initial heading.
     * @return A newline-terminated <tt>String</tt> that gives directions
     * 		   on how to traverse this geographic feature.
     */
  	public abstract String computeLine(GeoFeature geoFeature, double origHeading);


  	/**
     * Computes directions to turn based on the heading change.
     * @requires 0 <= oldHeading < 360 &&
     *           0 <= newHeading < 360
     * @param origHeading the start heading.
   	 * @param newHeading the desired new heading.
     * @return English directions to go from the old heading to the new
     * 		   one. Let the angle from the original heading to the new
     * 		   heading be a. The turn should be annotated as:
     * <p>
     * <pre>
     * Continue             if a < 10
     * Turn slight right    if 10 <= a < 60
     * Turn right           if 60 <= a < 120
     * Turn sharp right     if 120 <= a < 179
     * U-turn               if 179 <= a
     * </pre>
     * and likewise for left turns.
     */
  	protected String getTurnString(double origHeading, double newHeading) {
  		// delta represent the angle between newHeading and origHeading
  		// -180 <= delta <= 180
  		double delta = this.getAnglesDiff(origHeading, newHeading);
  		switch (this.getTurnEnum(delta)) {
		case STRIGHT:
			return "Continue";
		case UTURN:
			return "U-turn";
		case RIGHT:
			return "Turn right";
		case RIGHT_HARD:
			return "Turn sharp right";
		case RIGHT_SOFT:
			return "Turn slight right";
		case LEFT:
			return "Turn left";
		case LEFT_HARD:
			return "Turn sharp left";
		case LEFT_SOFT:
			return "Turn slight left";	
		default:
			return "";
		}
  	}
  	
  	/**
  	 * Calculates the difference between two angles
  	 * @requires 0 <= oldHeading < 360 &&
     *           0 <= newHeading < 360
  	 * @param angle1 in degrees
  	 * @param angle2 in degrees
  	 * @return the difference between angle1 and angle2
  	 */
  	private double getAnglesDiff(double angle1, double angle2) {
  		double diff = angle2 - angle1;
  		if (diff >= -180 && diff <= 180) {
  			return diff;
  		}
  		else {
  			return diff - 360 * Math.signum(diff);
  		}
  	}
  	/**
  	 * @requires -180 <= delta <= 180
  	 * @param delta : a difference between heading in degrees
  	 * @return an instance of enum Turn.
  	 */
  	private Turn getTurnEnum(double delta){
  		if (delta <= -179){
  			return Turn.UTURN;
  		}
  		else if (delta <= -120) {
  			return Turn.LEFT_HARD;
  		}
  		else if (delta <= -60) {
  			return Turn.LEFT;
  		}
  		else if (delta <= -10) {
  			return Turn.LEFT_SOFT;
  		}
  		else if (delta < 10) {
  			return Turn.STRIGHT;
  		}
  		else if (delta < 60) {
  			return Turn.RIGHT_SOFT;
  		}
  		else if (delta < 120) {
  			return Turn.RIGHT;
  		}
  		else if (delta < 179) {
  			return Turn.RIGHT_HARD;
  		}
  		else {
  			return Turn.UTURN;
  		}
  	}

}
