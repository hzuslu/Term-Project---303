package project.keygraph;

import java.util.*;

/**
 * Interface for representing a keyboard graph with nodes and edges.
 */
public interface IKeyboardGraph {

    /**
     * Adds a node to the keyboard graph.
     *
     * @param node The character representing the node to be added.
     */
    void addNode(char node);

    /**
     * Adds an edge between two nodes in the keyboard graph.
     *
     * @param node1 The first node to connect.
     * @param node2 The second node to connect.
     */
    void addEdge(char node1, char node2);

    /**
     * Displays the keyboard graph by printing each node and its neighbors.
     */
    void display();

    /**
     * Builds the keyboard graph with predefined nodes and edges.
     */
    void buildGraph();

    /**
     * Calculates the minimum distance between two nodes in the keyboard graph.
     *
     * @param startNode The starting node for distance calculation.
     * @param endNode   The ending node for distance calculation.
     * @return          The minimum distance between the start and end nodes, or -1 if unreachable.
     */
    int calculateMinDistance(char startNode, char endNode);
}
