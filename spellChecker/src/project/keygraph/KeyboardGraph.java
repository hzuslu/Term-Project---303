package project.keygraph;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyboardGraph implements IKeyboardGraph {
    private Map<Character, List<Character>> graph;

    public KeyboardGraph() {
        this.graph = new HashMap<>();
    }

    public void addNode(char node) {
        char lowercaseNode = Character.toLowerCase(node);
        if (!graph.containsKey(lowercaseNode)) {
            graph.put(lowercaseNode, new ArrayList<>());
        }
    }

    public void addEdge(char node1, char node2) {
        char lowercaseNode1 = Character.toLowerCase(node1);
        char lowercaseNode2 = Character.toLowerCase(node2);

        if (graph.containsKey(lowercaseNode1) && graph.containsKey(lowercaseNode2)) {
            graph.get(lowercaseNode1).add(lowercaseNode2);
            graph.get(lowercaseNode2).add(lowercaseNode1);
        }
    }

    public void display() {
        for (char node : graph.keySet()) {
            System.out.print(node + ": ");
            List<Character> neighbors = graph.get(node);
            for (char neighbor : neighbors) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public void buildGraph() {
        for (char c = 'a'; c <= 'z'; c++) {
            addNode(c);
        }

        addEdge('q','w');
        addEdge('q','a');
        addEdge('q','s');
        addEdge('w','e');
        addEdge('w','a');
        addEdge('w','s');
        addEdge('w','d');
        addEdge('e','r');
        addEdge('e','s');
        addEdge('e','d');
        addEdge('e','f');
        addEdge('r','t');
        addEdge('r','d');
        addEdge('r','f');
        addEdge('r','g');
        addEdge('t','y');
        addEdge('t','g');
        addEdge('t','f');
        addEdge('t','h');
        addEdge('y','u');
        addEdge('y','g');
        addEdge('y','h');
        addEdge('y','j');
        addEdge('u','j');
        addEdge('u','h');
        addEdge('u','k');
        addEdge('o','p');
        addEdge('o','k');
        addEdge('o','l');
        addEdge('o','ş');
        addEdge('p','ğ');
        addEdge('p','l');
        addEdge('p','ş');
        addEdge('p','i');
        addEdge('ğ','ş');
        addEdge('ğ','i');
        addEdge('a','s');
        addEdge('a','z');
        addEdge('a','x');
        addEdge('s','d');
        addEdge('s','z');
        addEdge('s','x');
        addEdge('s','c');
        addEdge('d','f');
        addEdge('d','x');
        addEdge('d','c');
        addEdge('d','v');
        addEdge('f','g');
        addEdge('f','c');
        addEdge('f','v');
        addEdge('f','b');
        addEdge('g','h');
        addEdge('g','v');
        addEdge('g','b');
        addEdge('g','n');
        addEdge('h','j');
        addEdge('h','b');
        addEdge('h','n');
        addEdge('h','m');
        addEdge('j','k');
        addEdge('j','n');
        addEdge('j','m');
        addEdge('k','l');
        addEdge('k','m');
        addEdge('l','ş');
        addEdge('ş','i');
        addEdge('z','x');
        addEdge('x','c');
        addEdge('c','v');
        addEdge('v','b');
        addEdge('b','n');
        addEdge('n','m');


    }
    public int calculateMinDistance(char startNode, char endNode) {
        if (graph == null) {
            System.out.println("Graph is null. Make sure to build the graph before calculating distances.");
            return -1;
        }

        Map<Character, Integer> distances = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();
        Set<Character> visited = new HashSet<>();

        for (char node : graph.keySet()) {
            distances.put(node, (node == startNode) ? 0 : Integer.MAX_VALUE);
        }

        queue.add(startNode);

        while (!queue.isEmpty()) {
            char currentNode = queue.poll();
            if (currentNode == endNode) {
                return distances.get(currentNode);
            }

            visited.add(currentNode);

            for (char neighbor : graph.get(currentNode)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    int newDistance = distances.get(currentNode) + 1;
                    if (newDistance < distances.get(neighbor)) {
                        distances.put(neighbor, newDistance);
                    }
                }
            }
        }

        return -1;
    }
    public static void main(String[] args) {
        KeyboardGraph myKeyboardGraph = new KeyboardGraph();
        myKeyboardGraph.buildGraph();
        myKeyboardGraph.display();

        char startNode = 'q';
        char endNode = 'd';

        int minDistance = myKeyboardGraph.calculateMinDistance(startNode, endNode);

        if (minDistance != -1) {
            System.out.println("Minimum yol uzunluğu " + startNode + " -> " + endNode + ": " + minDistance);
        } else {
            System.out.println("Ulaşılamayan düğümler.");
        }
    }


}
