package homework2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.PriorityQueue;

/**
 * @author yoavg
 *<p>
 * A PathFinder is a class used to find the shortest path in a graph
 *<p>
 * This class implements an algorithm similar to Dijkstra's algorithm.
 * The details of the algorithm are specified in the exercise instructions   
 *</p>
 *<b>The following fields are used in the specification:</b>
 * <pre>
 *   paths : sequence   		// a container of paths
 * </pre>
 *The first generic argument (<tt>N</tt>) is the type of nodes in the path.
 * The second generic argument (<tt>P</tt>) is the type of Path used
 */
public class PathFinder<N, P extends Path<N, P>> {

	/**
	 * PathFinder constructors:
	 * @effects Creates a new PathFinder.
	 */
	public PathFinder() {
	}
	
	/**
	 * findPath: The main method of PathFinder- runs the algorithm to find shortest path in a graph
	 * @requires
	 * <pre>starts != null && is a list of 'one node' paths
	 * && goals != null && is a list of 'one node' paths
	 * && g != null </pre>
	 * @effects 
	 * <pre>Returns a shortest path from one of starts to one of goals if exists
	 * null otherwise </pre>
	 */
	public Path<N ,P> findPath(Graph<N> g, Set<P> starts, Set<P> goals){
		Map<N,P> paths = new HashMap<>();
		for (P node_iter: starts) {
			paths.put(node_iter.getEnd(), node_iter);
		}
		
		PriorityQueue<P> active = new PriorityQueue<P>(starts);
		Set<N> finished = new HashSet<>();
		
		while (active.size() > 0) {
			P queue_min = active.poll();
			N curr_node = queue_min.getEnd();
			for (P goal: goals) {
				if (goal.getEnd().equals(curr_node)) {
					return queue_min;
				}
			}
			Iterator<N> iter = g.getChildren(curr_node);
			while (iter.hasNext()) {
				N child = iter.next();
				P c_path = queue_min.extend(child);
				if (!finished.contains(child) && !active.contains(c_path)) {
					paths.put(child, c_path);
					active.add(c_path);
				}
			}
			finished.add(curr_node);
		}
		return null;
	}
}
