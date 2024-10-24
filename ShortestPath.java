import java.util.*;

public class ShortestPath {
    
    public static class Node {
        int vertex;
        int weight;
        
        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    
    public static Map<Integer, Integer> dijkstra(Map<Integer, List<Node>> graph, int start) {
        Map<Integer, Integer> distances = new HashMap<>();
        Map<Integer, Integer> previousNodes = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.weight));
        
        for (int node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        
        pq.add(new Node(start, 0));
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentNode = current.vertex;
            
            for (Node neighbor : graph.get(currentNode)) {
                int newDist = distances.get(currentNode) + neighbor.weight;
                if (newDist < distances.get(neighbor.vertex)) {
                    distances.put(neighbor.vertex, newDist);
                    previousNodes.put(neighbor.vertex, currentNode);
                    pq.add(new Node(neighbor.vertex, newDist));
                }
            }
        }
        
        return previousNodes;
    }
    
    public static List<Integer> shortestPath(Map<Integer, Integer> previousNodes, int start, int destination) {
        List<Integer> path = new ArrayList<>();
        Integer currentNode = destination;
        
        // Check if there's a path to the destination
        if (!previousNodes.containsKey(destination)) {
            return path; // Empty path means destination not reachable
        }
        
        while (currentNode != null) {
            path.add(currentNode);
            currentNode = previousNodes.get(currentNode);
        }
        
        Collections.reverse(path);
        return path;
    }
    
    public static void main(String[] args) {
        Map<Integer, List<Node>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(new Node(2, 4), new Node(3, 1)));
        graph.put(2, Arrays.asList(new Node(1, 4), new Node(3, 2), new Node(4, 5)));
        graph.put(3, Arrays.asList(new Node(1, 1), new Node(2, 2), new Node(4, 8), new Node(5, 3)));
        graph.put(4, Arrays.asList(new Node(2, 5), new Node(3, 8), new Node(5, 2)));
        graph.put(5, Arrays.asList(new Node(3, 3), new Node(4, 2)));
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the source node: ");
        int source = scanner.nextInt();
        
        System.out.print("Enter the destination node: ");
        int destination = scanner.nextInt();
        
        Map<Integer, Integer> previousNodes = dijkstra(graph, source);
        List<Integer> path = shortestPath(previousNodes, source, destination);
        
        if (!path.isEmpty()) {
            System.out.println("The shortest path from node " + source + " to node " + destination + " is: " + path.size());
            System.out.println("The path is: " + String.join(" -> ", path.stream().map(String::valueOf).toArray(String[]::new)));
        } else {
            System.out.println("Node " + destination + " is not reachable from node " + source + ".");
        }
        
        scanner.close();
    }
}
