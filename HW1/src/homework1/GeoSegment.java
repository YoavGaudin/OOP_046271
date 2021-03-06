package homework1;

/**
 * A GeoSegment models a straight line segment on the earth. GeoSegments 
 * are immutable.
 * <p>
 * A compass heading is a nonnegative real number less than 360. In compass
 * headings, north = 0, east = 90, south = 180, and west = 270.
 * <p>
 * When used in a map, a GeoSegment might represent part of a street,
 * boundary, or other feature.
 * As an example usage, this map
 * <pre>
 *  Trumpeldor   a
 *  Avenue       |
 *               i--j--k  Hanita
 *               |
 *               z
 * </pre>
 * could be represented by the following GeoSegments:
 * ("Trumpeldor Avenue", a, i), ("Trumpeldor Avenue", z, i),
 * ("Hanita", i, j), and ("Hanita", j, k).
 * </p>
 * 
 * </p>
 * A name is given to all GeoSegment objects so that it is possible to
 * differentiate between two GeoSegment objects with identical
 * GeoPoint endpoints. Equality between GeoSegment objects requires
 * that the names be equal String objects and the end points be equal
 * GeoPoint objects.
 * </p>
 *
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *   name : String       // name of the geographic feature identified
 *   p1 : GeoPoint       // first endpoint of the segment
 *   p2 : GeoPoint       // second endpoint of the segment
 *   length : real       // straight-line distance between p1 and p2, in kilometers
 *   heading : angle     // compass heading from p1 to p2, in degrees
 * </pre>
 **/
public class GeoSegment  {

	/**
	 * <b>Abstraction Function:</b>
	 * <pre>
	 * 	name 	: String	// name of the geographic feature identified
	 * 	p1 	: GeoPoint	// first endpoint of the segment
	 * 	p2 	: GeoPoint	// second endpoint of the segment
	 * </pre>
	 * <b>Representation Invariant:</b>
	 * <pre>
	 * 	0 <= heading < 360
	 * 	length >= 0
	 * </pre>
	 */
	private String name;
	private GeoPoint p1, p2;
	private double length;
	private double heading;
	
  	/**
     * Constructs a new GeoSegment with the specified name and endpoints.
     * @requires name != null && p1 != null && p2 != null
     * @effects constructs a new GeoSegment with the specified name and endpoints.
     **/
  	public GeoSegment(String name, GeoPoint p1, GeoPoint p2) {
  		this.name = new String(name);
  		this.p1 = p1;
  		this.p2 = p2;
  		this.length = this.p1.distanceTo(this.p2);
  		this.heading = this.p1.headingTo(this.p2);
  		this.checkRep();
  	}


  	/**
     * Returns a new GeoSegment like this one, but with its endpoints reversed.
     * @return a new GeoSegment gs such that gs.name = this.name
     *         && gs.p1 = this.p2 && gs.p2 = this.p1
     **/
  	public GeoSegment reverse() {
  		this.checkRep();
  		return new GeoSegment(this.name, p2, p1);
  	}


  	/**
  	 * Returns the name of this GeoSegment.
     * @return the name of this GeoSegment.
     */
  	public String getName() {
  		this.checkRep();
  		return this.name;
  	}


  	/**
  	 * Returns first endpoint of the segment.
     * @return first endpoint of the segment.
     */
  	public GeoPoint getP1() {
  		this.checkRep();
  		return this.p1;
  	}


  	/**
  	 * Returns second endpoint of the segment.
     * @return second endpoint of the segment.
     */
  	public GeoPoint getP2() {
  		this.checkRep();
  		return this.p2;
  	}


  	/**
  	 * Returns the length of the segment.
     * @return the length of the segment, using the flat-surface, near the
     *         Technion approximation.
     */
  	public double getLength() {
  		this.checkRep();
  		return this.length;
  	}


  	/**
  	 * Returns the compass heading from p1 to p2.
     * 
     * @return 
     * 	<dl>
     * 		<dt>if this.length != 0: 
     * 		<dd>the compass heading from p1 to p2, in degrees, using the
     *        	 flat-surface, near the Technion approximation.
     * 		<dt> otherwise: <dd>0
     * 	</dl>
     *         
     **/
  	public double getHeading() {
  		this.checkRep();
  		return this.heading;
  	}


  	/**
     * Compares the specified Object with this GeoSegment for equality.
     * @return gs != null && (gs instanceof GeoSegment)
     *         && gs.name = this.name && gs.p1 = this.p1 && gs.p2 = this.p2
   	 **/
  	public boolean equals(Object gs) {
  		this.checkRep();
  		if (gs != null && gs instanceof GeoSegment){
  			GeoSegment gs_tmp = (GeoSegment) gs;
  			this.checkRep();
  			return this.getName().equals(gs_tmp.getName()) && 
  					this.getP1().equals(gs_tmp.getP1()) &&
  					this.getP2().equals(gs_tmp.getP2());
  		}
  		this.checkRep();
  		return false;
  	}


  	/**
  	 * Returns a hash code value for this.
     * @return a hash code value for this.
     **/
  	public int hashCode() {
  		this.checkRep();
  		int hashcode = 11;
  		hashcode = 37 * hashcode + this.getName().hashCode();
  		hashcode = 37 * hashcode + this.getP1().hashCode();
  		hashcode = 37 * hashcode + this.getP2().hashCode();
  		this.checkRep();
    	return hashcode;
  	}


  	/**
  	 * Returns a string representation of this.
     * @return a string representation of this.
     **/
  	public String toString() {
  		this.checkRep();
  		return "(\"" + this.getName() + "\"," + this.getP1() + "," + this.getP2() + ")";
  	}
  	
  	
  	/**
  	 * Checks to see if the representation invariant is being violated.
  	 * @throws AssertionError if representation invariant is violated.
  	 */
  	private void checkRep() {
  		assert(this.length >= 0) : "Got negative distance";
  		assert(this.heading < 360 && this.heading >= 0) : "Got Ilegal heading";  	
  	}
}

