import java.util.ArrayList;

public class GraphCC<V,E> extends AdjacencyMapGraph<V,E> {

    public GraphCC(boolean directed){super(directed);}

    private ArrayList<Vertex<V>> visited = new ArrayList<Vertex<V>>();
    private boolean isCyclic = false;


    /**
     * Returns true if there is a cycle in the passed in graph
     * @param G directed graph 
     * @return true is contains cycle, false if doesn't
     */
    public boolean containsCycle(AdjacencyMapGraph<V,E> G){

        // Gets the first vertex from the graph and passes it into the iterative
        //  function.
        for (Vertex<V> vertex : G.vertices()) {
            return containsCycleUtil(G, vertex);
        }
        return isCyclic;
    }
   
    /**
     * Used by continsCycle to iteratively find a Cycle
     * @param G directed graph
     * @param vert vertex used to find connected vertices
     * @return true if contains cycle, false if doesn't
     */
    public boolean containsCycleUtil(AdjacencyMapGraph<V,E> G, Vertex<V> vert){
         
        // Mark current vertex as visited 
        visited.add(vert);

        // Check the vertices connected  to the current vertex
        for (Edge<E> edge : G.outgoingEdges(vert)) {
            // Vertices connected to the edge
            Vertex<V>[] temp = G.endVertices(edge);

            // If the next vertex has already been visited, there is a Cycle
            for (int j = 0; j < visited.size(); j++) {
                if(temp[1].equals(visited.get(j))){ return isCyclic = true;}
            }
            
            // Search the next vertex
            containsCycleUtil(G, temp[1]);
        }

        // Once a path has been explored and no cycle is found, remove that path from visited.
        visited.remove(visited.size() -1);
        return isCyclic;
    }

   
    
    public static void main(String[] args) {
        GraphCC useless = new GraphCC<>(false);
        
        System.out.println("**** START OF TEST ****\n");

        // VV Testing a graph with no loop 
        AdjacencyMapGraph testGraphNL = new AdjacencyMapGraph<>(true);
        Vertex testVert1 = testGraphNL.insertVertex(1);
        Vertex testVert2 = testGraphNL.insertVertex(2);
        testGraphNL.insertEdge(testVert1, testVert2, "A"); // Point 1 to 2
        testVert1 = testGraphNL.insertVertex(3);
        testGraphNL.insertEdge(testVert2, testVert1, "B"); // Point 2 to 3
        testVert2 = testGraphNL.insertVertex(4);
        testGraphNL.insertEdge(testVert1, testVert2, "C"); // Point 3 to 4
        testVert2 = testGraphNL.insertVertex(5);
        testGraphNL.insertEdge(testVert1, testVert2, "D"); // Point 3 to 5

        System.out.println("Cycle TesT, No Loop....\nResult: " + useless.containsCycle(testGraphNL));
        System.out.print("\n");
        System.out.println("Graph ToString...");
        System.out.println(testGraphNL.toString());
        System.out.print("\n\n");


        // VV Testing a graph with a false loop (two pointing to the same vertex)
        AdjacencyMapGraph testGraphFL = new AdjacencyMapGraph<>(true);
        testVert1 = testGraphFL.insertVertex(1);
        testVert2 = testGraphFL.insertVertex(2);
        testGraphFL.insertEdge(testVert1, testVert2, "A"); // Point 1 to 2
        Vertex testVert3 = testGraphFL.insertVertex(3);
        testGraphFL.insertEdge(testVert1, testVert3, "B"); // Point 1 to 3
        testVert1 = testGraphFL.insertVertex(4);
        testGraphFL.insertEdge(testVert2, testVert1, "C"); // Point 2 to 4
        testGraphFL.insertEdge(testVert3, testVert1, "D"); // Point 3 to 4
        testVert2 = testGraphFL.insertVertex(5);
        testGraphFL.insertEdge(testVert1, testVert2, "E"); // Point 4 to 5

        System.out.println("Cycle TesT, False Loop....\nResult: " + useless.containsCycle(testGraphFL));
        System.out.print("\n");
        System.out.println("Graph ToString...");
        System.out.println(testGraphNL.toString());
        System.out.print("\n\n");

        
        // VV Testing a graph with a loop
        AdjacencyMapGraph testGraphL = new AdjacencyMapGraph<>(true);
        testVert1 = testGraphL.insertVertex(1);
        testVert2 = testGraphL.insertVertex(2);
        testGraphL.insertEdge(testVert1, testVert2, "A"); // Point 1 to 2
        testVert3 = testGraphL.insertVertex(3);
        testGraphL.insertEdge(testVert2, testVert3, "B"); // Point 2 to 3
        Vertex testVert4 = testGraphL.insertVertex(4);
        testGraphL.insertEdge(testVert3, testVert4, "C"); // Point 3 to 4
        testGraphL.insertEdge(testVert4, testVert1, "D"); // Point 4 to 1
        testVert1 = testGraphL.insertVertex(5);
        testGraphL.insertEdge(testVert4, testVert1, "E"); // Point 4 to 5

        System.out.println("Cycle TesT, Has Cycle....\nResult: " + useless.containsCycle(testGraphL));
        System.out.print("\n");
        System.out.println("Graph ToString...");
        System.out.println(testGraphNL.toString());
        System.out.print("\n\n");


        System.out.print("**** END OF TEST ****");

    }

}