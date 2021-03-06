package homework2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * A Graph is an abstraction for a mathematical graph with sets of <i>Nodes</i> and <i>Edges</i>.
 * Graphs are immutable.
 * <p>
 * <b>Generics:</b>
 * 	<pre>
 * NODE_T: the class used for the nodes.
 * 	</pre>
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *   nodes : sequence              // 
 * </pre>
 * </p>
 * 
 * @author yoavg
 *
 */
public final class Graph<NODE_T> {
	/**
	 * Abstraction function
	 * <pre>
	 * nodes : Map	//	the keys represent all nodes in the graph. The values are a list of children
	 * and edge between n1 and n2 is represented by the presence of n2 in n1's children list
	 * </pre>
	 * Representation invariant
	 * <pre>
	 * for each node, the list of it's children does not contain duplicate nodes
	 * </pre>
	 */
	private final Map<NODE_T, Set<NODE_T>> nodes;
	private final String name;
	
	/**
	 * Constructor for Graph
	 * @requires name != null && name != ""
	 * @effects Construct a new Graph g with g.name=name && g.nodes is empty
	 */
	public Graph(String name) {
		this.name = name;
		this.nodes = new HashMap<>();
		this.checkRep();
	}
	
	/**
	 * @effects Returns this.name
	 */
	public String getName() {
		this.checkRep();
		return this.name;
	}
	
	/**
	 * Returns an iterator for all nodes in graph
	 * @effects Returns an iterator for all nodes in graph
	 */
	public Iterator<NODE_T> getNodes() {
		this.checkRep();
		Set<NODE_T> nodes = (Set<NODE_T>) ((HashMap<NODE_T, Set<NODE_T>>) this.nodes).keySet();
		return new ArrayList<NODE_T>(nodes).iterator();
	}
	
	/**
	 * @requires node != null
	 * @effects Return an Iterator for node's children
	 */
	public Iterator<NODE_T> getChildren(NODE_T node) {
		this.checkRep();
		this.nodes.get(node).iterator();
		return this.nodes.get(node).iterator();
	}
	
	/**
	 * @requires node != null
	 * @modifies this
	 * @effects adds node to this.nodes and create a new set for it's children
	 */
	public void addNode(NODE_T node) {
		this.checkRep();
		this.nodes.put(node, new HashSet<NODE_T>());
	}
	
	/**
	 * @requires source_node != null
	 * 				&& destination_node != null 
	 * 				&& destination_node is not in source_node.children
	 * 				&& this.nodes has a key 'source_node' with value != null
	 * @modifies source_node
	 * @effects adds destination_node to source_node's children
	 */
	public void addEdge(NODE_T source_node, NODE_T destination_node) {
		this.checkRep();
		Set<NODE_T> children = this.nodes.get(source_node);
		children.add(destination_node);
		this.checkRep();
	}
	
	/**
	 * Compares the specified Object with this Graph for equality.
	 * @effects Returns true iff o is an instance of Graph<NODE_T> &&
	 * 						this.name = o.name
	 */
	public boolean equals(Object o) {
		this.checkRep();
		if (o instanceof Graph<?>) {
			Graph<?> g = (Graph<?>)o;
			return (g.name.equals(this.name) && g.nodes.equals(this.nodes));
		}
		return false;
	}
	
	/**
	 * @effects Returns hash code for this
	 */
	public int hashCode() {
		this.checkRep();
		return this.name.hashCode();
	}
	
	/**
	 * @effects Returns a string representation of this.
	 * @param name
	 */
	public String toString() {
		return this.name;
	}
	
	/**
	 * Checks to see if the representation invariant is being violated.
	 * @effects Throws AssertionError if representation invariant is violated.
	 */
	private void checkRep() {
		// representation invariant is uphold by implementing list of children as a set 
	}
}
