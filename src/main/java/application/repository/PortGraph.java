package application.repository;

import application.model.Port;
import java.util.*;

public class PortGraph {
    private Map<String, Port> ports = new HashMap<>();

    // Agregar un puerto al grafo
    public void addPort(String name) {
        ports.putIfAbsent(name, new Port(name));
    }

    // Conectar dos puertos con una distancia
    public void connectPorts(String port1, String port2, int distance) {
        ports.get(port1).addConnection(port2, distance);
        ports.get(port2).addConnection(port1, distance); // Grafo no dirigido
    }

    // a. Barrido en profundidad (DFS)
    public void depthFirstSearch(String startPort) {
        Set<String> visited = new HashSet<>();
        dfsHelper(startPort, visited);
    }

    private void dfsHelper(String port, Set<String> visited) {
        if (visited.contains(port)) return;
        visited.add(port);
        System.out.println("Visitando: " + port);
        for (String neighbor : ports.get(port).getConnections().keySet()) {
            dfsHelper(neighbor, visited);
        }
    }

    // b. Camino más corto (Dijkstra)
    public List<String> shortestPath(String start, String end) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> prevNodes = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (String port : ports.keySet()) {
            distances.put(port, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(end)) break;

            for (Map.Entry<String, Integer> neighbor : ports.get(current).getConnections().entrySet()) {
                int newDist = distances.get(current) + neighbor.getValue();
                if (newDist < distances.get(neighbor.getKey())) {
                    distances.put(neighbor.getKey(), newDist);
                    prevNodes.put(neighbor.getKey(), current);
                    queue.add(neighbor.getKey());
                }
            }
        }

        List<String> path = new LinkedList<>();
        for (String at = end; at != null; at = prevNodes.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    // c. Eliminar el puerto con más conexiones
    public void removeMostConnectedPort() {
        String mostConnected = null;
        int maxConnections = 0;

        for (Map.Entry<String, Port> entry : ports.entrySet()) {
            int connections = entry.getValue().getConnections().size();
            if (connections > maxConnections) {
                mostConnected = entry.getKey();
                maxConnections = connections;
            }
        }

        if (mostConnected != null) {
            ports.remove(mostConnected);
            System.out.println("Puerto eliminado: " + mostConnected);
        }
    }
}
