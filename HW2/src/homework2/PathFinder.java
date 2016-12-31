package homework2;

import java.util.*;

public class PathFinder<N,P extends Path<N,P>> {

	private Graph<N> m_g;
	
	/*
	 * constructs a new PathFinder.
	 * @requires g != null
	 * @effects creates a PathFinder with the relevant Graph in it.
	 */
	public PathFinder(Graph<N> g){
		this.m_g= g;
	}	
	
	/*
	 *Return the shortest path from one element of starts to one
	 *element of goals in a node-weighted graph.
	 *@requires starts, goals != null.
	 *@return the shortest path from one of the starts to one of the goals. null if o such path exists.
	*/
	
	public P findPath(List<P> starts, List<N> goals){
		
		Map<N,P> paths = new HashMap<N,P>();
		PriorityQueue<P> active = new PriorityQueue<P>();
		List<N> finished = new ArrayList<N>();	// The set of finished nodes are those for which we know the shortest
								// paths from starts and whose children we have already examined	
		// maps nodes -> paths
		// The priority queue contains nodes with priority equal to the cost
		// of the shortest path to reach that node. Initially it contains the
		// start nodes
		for (int i = 0; i<starts.size(); i++){
			paths.put(starts.get(i).getEnd(), starts.get(i));
			active.add(starts.get(i));
		}
		
		while (!active.isEmpty()){			
			// queueMin is the element of active with shortest path
			P queueMin = active.poll();
			N nodeMin = queueMin.getEnd();//last node in the path extracted
			P queueMinPath = paths.get(nodeMin);//path to the nodeMin
			
			if (goals.contains(nodeMin)) {
				return queueMinPath;
			}
			
			// iterate over edges (queueMin, c) in queueMin.edges
			Iterator<N> nodeIterator = m_g.getChildren(nodeMin);
			
			while(nodeIterator.hasNext()){
				N c = nodeIterator.next();
				P cPath  = queueMinPath.extend(c);
							
				if(!finished.contains(c)){//c isn't in finished
					//all of this is done because we had to use pathes instead of nodes 
					//in active, and there for finding whether or not a node is the final
					//node in a path and still an active node, became relatively complicated.					
					Iterator<P> pathIterator = active.iterator();
					int flag = 0;
					while(pathIterator.hasNext()){
						if(c == pathIterator.next().getEnd()){
							flag = 1;
						}
					}
					
					if(flag ==0){// means the node isn't active yet
						paths.put(c, cPath);
						active.add(cPath);			
					}
				}	
			}
			
			finished.add(nodeMin);
		}

	return null;
	};
}

