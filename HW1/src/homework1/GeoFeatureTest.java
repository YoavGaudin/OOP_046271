package homework1;

public class GeoFeatureTest {
	private static final double tolerance = 0.01;
	   
  	private GeoPoint gpZivSquare;
  	private GeoPoint gpWest;			// 1 km west to gpZivSquare
  	private GeoPoint gpEast;			// 1 km east to gpZivSquare 
  	private GeoPoint gpNorth;			// 1 km north to gpZivSquare
                                
  	private GeoSegment gsEast;
  	private GeoSegment gsNorth;
  	private GeoSegment gsEast2; 
  	private GeoSegment gsWest2;
  	
  	private GeoFeature gf_west_ziv;
  	private GeoFeature gf_ziv_east;
  
  	public GeoFeatureTest() {
		gpZivSquare = new GeoPoint(32783098,35014528);
		gpWest = new GeoPoint(32783098,35003854);
		gpEast = new GeoPoint(32783098,35025202);
		gpNorth = new GeoPoint(32792115,35014528);
		
    	gsEast = new GeoSegment("East", gpZivSquare, gpEast);
    	gsNorth = new GeoSegment("North", gpZivSquare, gpNorth);
    	gsEast2 = new GeoSegment("East", gpWest, gpZivSquare);
    	gsWest2 = new GeoSegment("West", gpWest, gpZivSquare);
    	
    	gf_ziv_east = new GeoFeature(gsEast);
    	gf_west_ziv = new GeoFeature(gsWest2);
  	}
  	
  	
  	boolean same(double x, double y) {
  		return ((y >= x-tolerance) && (y <= x+tolerance));
  	}
  	
  	
	public void show(String str) {
		System.out.println();
		System.out.println("***** " + str + " *****");
	}
		
		
	public void show(String str, boolean ok) {
		if (ok)
			System.out.print("v ");
		else
			System.out.print("x ");
		System.out.println(str);	
	}
  	
  	
  	public void test() {
  		show("equals()");
  		show("Self equaluty", gf_ziv_east.equals(gf_ziv_east));
  		show("Equal to copy", gf_west_ziv.equals(new GeoFeature(gsWest2)));
  		show("Different objects", ! gf_west_ziv.equals(gf_ziv_east));
  		show("Same segments, different names", ! gf_west_ziv.equals(new GeoFeature(gsEast2)));
  		show("Same names, different segments", ! gf_ziv_east.equals(new GeoFeature(gsEast2)));
  		
  		GeoSegment gsWestTmp = new GeoSegment("West", gpWest, gpZivSquare);
  		GeoFeature gf_west_ziv2 = new GeoFeature(gsWestTmp);
  		show("equals() if segments are equlas", gf_west_ziv.equals(gf_west_ziv2));
  		show("equals(non-GeoFeature) should be false", ! gf_ziv_east.equals("a_string"));
  		show("equals(null) should be false", ! gf_west_ziv.equals(null));
  		
  		show("hashCode()");
  		show(".equals() objects must have the same .hashCode()", 
  				gf_west_ziv.hashCode() == gf_west_ziv2.hashCode());
  		
  		
  		show("getName()");
  		
  		show("getStart(), getEnd()");
  		
  		show("getStartHeading(), getEndHeading()");
  		
  		show("getLength()");
  		
  		show("addSegment()");
  		GeoFeature gf_west_ziv_east = gf_west_ziv.addSegment(gsNorth);
  		show("addSegment() returns a different GeoFeature", 
  				! gf_west_ziv_east.equals(gf_west_ziv));
  		show("Length of new GeoFeature", 
  				gf_west_ziv_east.getLength() == gf_west_ziv.getLength() + gsNorth.getLength());
  		show("Start heading of new feature",
  				gf_west_ziv_east.getStartHeading() == gf_west_ziv.getStartHeading());
  		show("End heading of new feature", 
  				(gf_west_ziv_east.getEndHeading() == gsNorth.getHeading()) &&
  				(gf_west_ziv_east.getEndHeading() != gf_west_ziv.getEndHeading()));
  		show("Start point for new feature", 
  				gf_west_ziv_east.getStart().equals(gf_west_ziv.getStart()));
  		show("End poing for new feature", 
  				gf_west_ziv_east.getEnd().equals(gsNorth.getP2()));

  		show("getGeoSegments(); (iterator)");
  		
  	}
  	
  	public static void main(String[] args) {
  		GeoFeatureTest featureTest = new GeoFeatureTest();
  		featureTest.test();
	}
}
