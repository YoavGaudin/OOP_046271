package homework2;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * This class contains a set of test cases that can be used to test the graph
 * and shortest path finding algorithm implementations of homework assignment
 * #2.
 */
public class GraphTests extends ScriptFileTests {

	// black-box test are inherited from super
	public GraphTests(java.nio.file.Path testFile) {
		super(testFile);
	}

	@Test
	public void testEquals() {
		// setup objects:
		Graph<WeightedNode> g1 = new Graph<WeightedNode>("g1");
		Graph<WeightedNode> g1_2 = new Graph<WeightedNode>("g1");
		Graph<WeightedNode> g2 = new Graph<WeightedNode>("g2");
		WeightedNode n1 = new WeightedNode("n1", 1);
		WeightedNode n2 = new WeightedNode("n2", 1);
		
		// test non-equality for non Graph object
		assertNotEquals("Integer can't be equal to Graph", g1, new Integer(1));
		// test equality for empty graphs
		assertEquals("Empty graphs should be equals", g1, g1_2);
		// test non-equality for different names
		assertNotEquals("Graph with different names can't be equals", g1, g2);
		
		// test non-equality for different nodes list 
		g1.addNode(n1);
		assertNotEquals("Graphs with different node list must differ", g1, g1_2);
		
		// test equality for same node list no edges
		g1_2.addNode(n1);
		assertEquals("Graphs with same name and same node list should be equal", g1, g1_2);
		
		// test non-equality for same nodes list but different edges
		g1.addEdge(n1, n1);
		assertNotEquals("Graphs with different edges must differ", g1, g1_2);

		// test equality for same nodes list and same edges
		g1.addEdge(n1, n2);
		g1_2.addEdge(n1, n1);
		g1_2.addEdge(n1, n2);
		assertEquals("Graphs should be equal", g1, g1_2);
	}
}
