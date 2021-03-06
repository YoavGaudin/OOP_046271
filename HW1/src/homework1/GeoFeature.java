package homework1;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * A GeoFeature represents a route from one location to another along a
 * single geographic feature. GeoFeatures are immutable.
 * <p>
 * GeoFeature abstracts over a sequence of GeoSegments, all of which have
 * the same name, thus providing a representation for nonlinear or nonatomic
 * geographic features. As an example, a GeoFeature might represent the
 * course of a winding river, or travel along a road through intersections
 * but remaining on the same road.
 * <p>
 * GeoFeatures are immutable. New GeoFeatures can be constructed by adding
 * a segment to the end of a GeoFeature. An added segment must be properly
 * oriented; that is, its p1 field must correspond to the end of the original
 * GeoFeature, and its p2 field corresponds to the end of the new GeoFeature,
 * and the name of the GeoSegment being added must match the name of the
 * existing GeoFeature.
 * <p>
 * Because a GeoFeature is not necessarily straight, its length - the
 * distance traveled by following the path from start to end - is not
 * necessarily the same as the distance along a straight line between
 * its endpoints.
 * <p>
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *   start : GeoPoint       // location of the start of the geographic feature
 *   end : GeoPoint         // location of the end of the geographic feature
 *   startHeading : angle   // direction of travel at the start of the geographic feature, in degrees
 *   endHeading : angle     // direction of travel at the end of the geographic feature, in degrees
 *   geoSegments : sequence	// a sequence of segments that make up this geographic feature
 *   name : String          // name of geographic feature
 *   length : real          // total length of the geographic feature, in kilometers
 * </pre>
 **/
public final class GeoFeature {
	
	/**
	 * <b>Abstraction Function:</b>
	 * <pre>
	 * 	name 		: String	// name of geographic feature
	 * 	geoSegments 	: LinkedList	// a sequence of segments that make up this geographic feature
	 * </pre>
	 * 
	 * <b>Representation Invariant:</b>
	 * <pre>
	 * 	geoSegments.length > 0 (never empty).
	 * 	all items are GeoSegments with the same name.
	 * 	for each 2 consecutive segments s1 and s2 => s1.p2 = s2.p1 .
	 * </pre>
	 */
	private final LinkedList<GeoSegment> geoSegments;
	private final String name;
	
	/**
     * Constructs a new GeoFeature.
     * @requires gs != null
     * @effects Constructs a new GeoFeature, r, such that
     *	        r.name = gs.name &&
     *          r.startHeading = gs.heading &&
     *          r.endHeading = gs.heading &&
     *          r.start = gs.p1 &&
     *          r.end = gs.p2
     **/
  	public GeoFeature(GeoSegment gs) {
  		this.name = gs.getName();
  		this.geoSegments = new LinkedList<GeoSegment>();
  		this.geoSegments.add(gs);
  		this.checkRep();
  	}
  	
  	
 	/**
 	  * Returns name of geographic feature.
      * @return name of geographic feature
      */
  	public String getName() {
  		this.checkRep();
  		return this.name;
  	}


  	/**
  	 * Returns location of the start of the geographic feature.
     * @return location of the start of the geographic feature.
     */
  	public GeoPoint getStart() {
  		this.checkRep();
  		return this.geoSegments.getFirst().getP1();
  	}


  	/**
  	 * Returns location of the end of the geographic feature.
     * @return location of the end of the geographic feature.
     */
  	public GeoPoint getEnd() {
  		this.checkRep();
  		return this.geoSegments.getLast().getP2();
  	}


  	/**
  	 * Returns direction of travel at the start of the geographic feature.
     * @return 
     * 	<dl>
     * 		<dt>if this.geoSegments.getLast().getLength() != 0:
     * 		<dd>direction (in standard heading) of travel at the start of the
     *         geographic feature, in degrees.
     *      <dt>otherwise: <dd>0
     *	</dl>
     */
  	public double getStartHeading() {
  		this.checkRep();
  		return this.geoSegments.getFirst().getHeading();
  	}


  	/**
  	 * Returns direction of travel at the end of the geographic feature.
     * @return 
     * 	<dl>
     * 		<dt>if this.geoSegments.getLast().getLength() != 0:
     * 		<dd>direction (in standard heading) of travel at the end of the
     *         geographic feature, in degrees.
     *		<dt> otherwise: <dd>0
     *	</dl>
     */
  	public double getEndHeading() {
  		this.checkRep();
  		return this.geoSegments.getLast().getHeading();
  	}


  	/**
  	 * Returns total length of the geographic feature, in kilometers.
     * @return total length of the geographic feature, in kilometers.
     *         NOTE: this is actually the sum of length of all GeoSegments.
     */
  	public double getLength() {
  		this.checkRep();
  		Iterator<GeoSegment> gs = this.geoSegments.iterator();
  		double length = 0;
  		while (gs.hasNext()) {
  			length += gs.next().getLength();
  		}
  		this.checkRep();
  		return length;
  	}


  	/**
   	 * Creates a new GeoFeature that is equal to this GeoFeature with gs
   	 * appended to its end.
     * @requires gs != null && gs.p1 = this.end && gs.name = this.name.
     * @return a new GeoFeature r such that
     *         r.end = gs.p2 &&
     *         r.endHeading = gs.heading &&
     *    	   r.length = this.length + gs.length
     **/
  	public GeoFeature addSegment(GeoSegment gs) {
  		this.checkRep();
  		GeoFeature gf = new GeoFeature(gs);
  		gf.geoSegments.removeFirst();
  		gf.geoSegments.addAll(this.geoSegments);
  		gf.geoSegments.add(gs);
  		this.checkRep();
  		return gf;
  	}


  	/**
     * Returns an Iterator of GeoSegment objects. The concatenation of the
     * GeoSegments, in order, is equivalent to this GeoFeature. All the
     * GeoSegments have the same name.
     * @return an Iterator of GeoSegments such that
     * <pre>
     *      this.start        = a[0].p1 &&
     *      this.startHeading = a[0].heading &&
     *      this.end          = a[a.length - 1].p2 &&
     *      this.endHeading   = a[a.length - 1].heading &&
     *      this.length       = sum(0 <= i < a.length) . a[i].length &&
     *      for all integers i
     *          (0 <= i < a.length-1 => (a[i].name == a[i+1].name &&
     *                                   a[i].p2d  == a[i+1].p1))
     * </pre>
     * where <code>a[n]</code> denotes the nth element of the Iterator.
     * @see homework1.GeoSegment
     */
  	public Iterator<GeoSegment> getGeoSegments() {
  		this.checkRep();
  		@SuppressWarnings("unchecked")
		LinkedList<GeoSegment> copy_of_list = (LinkedList<GeoSegment>) this.geoSegments.clone();
  		this.checkRep();
  		return copy_of_list.iterator();
  	}


  	/**
     * Compares the argument with this GeoFeature for equality.
     * @return o != null && (o instanceof GeoFeature) &&
     *         (o.geoSegments and this.geoSegments contain
     *          the same elements in the same order).
     **/
  	public boolean equals(Object o) {
  		this.checkRep();
  		if (o != null && o instanceof GeoFeature) {
  			Iterator<GeoSegment> o_iter = ((GeoFeature) o).getGeoSegments();
  			Iterator<GeoSegment> this_iter = this.getGeoSegments();
  			while (o_iter.hasNext() || this_iter.hasNext()) {
  				if ((! (o_iter.hasNext() && this_iter.hasNext())) || (! o_iter.next().equals(this_iter.next()))) {
  					this.checkRep();
  					return false;
  				}
  			}
  			this.checkRep();
  			return true;
  		}
  		this.checkRep();
  		return false;
  	}


  	/**
     * Returns a hash code for this.
     * @return a hash code for this.
     **/
  	public int hashCode() {
  		this.checkRep();
  		int hashcode = 1;
  		hashcode = 37 * hashcode + this.name.hashCode();
  		hashcode = 37 * hashcode + this.getStart().hashCode();
  		hashcode = 37 * hashcode + this.getEnd().hashCode();
  		hashcode = 37 * hashcode + Double.valueOf(this.getStartHeading()).hashCode();
  		hashcode = 37 * hashcode + Double.valueOf(this.getEndHeading()).hashCode();
  		hashcode = 37 * hashcode + Double.valueOf(this.getLength()).hashCode();
  		Iterator<GeoSegment> iter = this.getGeoSegments();
  		while (iter.hasNext()) {
  			hashcode = 37 * hashcode + iter.next().hashCode();
  		}
  		this.checkRep();
    	return hashcode;
  	}


  	/**
  	 * Returns a string representation of this.
   	 * @return a string representation of this.
     **/
  	public String toString() {
  		this.checkRep();
  		return this.name;
  	}
  	
  	
  	/**
  	 * Checks to see if the representation invariant is being violated.
  	 * @throws AssertionError if representation invariant is violated.
  	 */
  	private void checkRep() {
  		assert(this.geoSegments.size() > 0) : "Empty segments list";
  		GeoPoint p1 = null;
  		for (GeoSegment gs : this.geoSegments) {
  			assert(this.name.equals(gs.getName()));
  			if (p1 != null) {
  				assert(gs.getP1().equals(p1)) : "Non matching segments";
  			}
  			p1 = gs.getP2();
  		}
  	}
}
