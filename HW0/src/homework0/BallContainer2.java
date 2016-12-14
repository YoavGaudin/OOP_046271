package homework0;
import java.util.Set;
import java.util.HashSet;

/**
 * A container that can be used to contain Balls. A given Ball may only
 * appear in a BallContainer once.
 */
public class BallContainer2 {
	
	/**
	 * ball_set member will hold balls added to BallContainer.
	 * Thus preventing duplicates as required
	 */
	private Set<Ball> ball_set;
	
	private double volume_sum;
	
    /**
     * @effects Creates a new BallContainer.
     */
    public BallContainer2() {
    	this.ball_set = new HashSet<Ball>();
    	this.volume_sum = 0;
    }


    /**
     * @modifies this
     * @effects Adds ball to the container.
     * @return true if ball was successfully added to the container,
     * 		   i.e. ball is not already in the container; false otherwise.
     */
    public boolean add(Ball ball) {
		if(ball!=null && this.ball_set.add(ball)){
			this.volume_sum += ball.getVolume();
			return true;
		}
		return false;
    }


    /**
     * @modifies this
     * @effects Removes ball from the container.
     * @return true if ball was successfully removed from the container,
     * 		   i.e. ball is actually in the container; false otherwise.
     */
    public boolean remove(Ball ball) {
    	if(ball!=null && this.ball_set.remove(ball)){
        	this.volume_sum -= ball.getVolume();
        	return true;
    	}
    	return false;
    }


    /**
     * @return the volume of the contents of the container, i.e. the
     * 		   total volume of all Balls in the container.
     */
    public double getVolume() {
		return this.volume_sum;
    }


    /**
     * @return the number of Balls in the container.
     */
    public int size() {
		return this.ball_set.size();
    }


    /**
     * @modifies this
     * @effects Empties the container, i.e. removes all its contents.
     */
    public void clear() {
		this.ball_set.clear();
    }


    /**
     * @return true if this container contains ball; false, otherwise.
     */
    public boolean contains(Ball ball) {
		return this.ball_set.contains(ball);
    }

}
