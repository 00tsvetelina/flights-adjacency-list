package org.example;

import java.util.*;

public class Graph {

    private final static Map<String, List<String>> adjList = new HashMap<>();

    public static void main(String[] args) {
        Graph.subtask1(getList(), "SOF", "MLE");
        Graph.subtask3(getListWithCapacity(),"SOF", "MLE", 3);
    }

    static List<String> getList() {
        List<String> list = new ArrayList<>();

        list.add("SOF,IST");
        list.add("IST,CMB");
        list.add("CMB,MLE");

        return list;
    }

    static List<String> getListWithCapacity() {
        List<String> list = new ArrayList<>();

        list.add("SOF,MLE,2");
        list.add("SOF,LON,3");
        list.add("LON,MLE,4");

        return list;
    }

    static int subtask1(List<String> flightList, String origin, String destination) {
        Graph.createGraph(flightList);
        int shortestTime = getShortestTime(origin, destination);

        if (shortestTime != 0) {
            System.out.println("Shortest time to reach destination: " + shortestTime);
        } else {
            System.out.println("Destination not reachable: " + shortestTime);
        }

        return shortestTime;
    }

    static int subtask3(List<String> flightList, String origin, String destination, Integer passengers) {
        Graph.createGraph(flightList);
        int shortestTime = Graph.getShortestTimeWithCapacity(flightList, origin, destination, passengers);

        if (shortestTime != 0) {
            System.out.println("Shortest time to reach destination: " + shortestTime);
        } else {
            System.out.println("Destination not reachable: " + shortestTime);
        }

        return shortestTime;
    }

    static void createGraph(List<String> flights) {
        adjList.clear();
        if (flights != null) {

            for (String flight : flights) {
                String[] flightArr = flight.split(",");

                addVertex(flightArr[0]);
                addVertex(flightArr[1]);
                addEdge(flightArr[0], flightArr[1]);
            }
        }
    }

    static void addVertex(String vertex) {
        if (!adjList.containsKey(vertex)) {
            adjList.put(vertex, new ArrayList<>());
        }
    }

    static void addEdge(String origin, String destination) {
        if (adjList.containsKey(origin) && adjList.containsKey(destination)) {
            adjList.get(origin).add(destination);
            adjList.get(destination).add(origin);
        } else {
            System.out.println("Both Vertices must exist to create an edge!");
        }
    }


    static int getShortestTime(String origin, String destination) {
        if (!validateSubtask1(origin, destination)) {
            return 0;
        }

        Map<String, Integer> distance = new HashMap<>();
        for (String vertex : adjList.keySet()) {
            distance.put(vertex, Integer.MAX_VALUE);
        }
        distance.put(origin, 0);

        Queue<String> queue = new LinkedList<>();
        queue.offer(origin);
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(destination)) {
                return distance.get(current);
            }

            if (adjList.get(current) == null) {
                return 0;
            }

            for (String neighbor : adjList.get(current)) {
                if (distance.get(neighbor) == Integer.MAX_VALUE) {
                    distance.put(neighbor, distance.get(current) + 1);
                    queue.offer(neighbor);
                }
            }
        }
        return 0;
    }

    static boolean validateSubtask1(String origin, String destination) {
        if (adjList.isEmpty()) {
            return false;
        }

        if (origin == null || destination == null) {
            return false;
        }

        return true;
    }

    static int getShortestTimeWithCapacity(List<String> flightsList, String origin, String destination, Integer passengers) {
        if (!validateInputsSubtask3(flightsList, origin, destination, passengers)) {
            return 0;
        }

        Map<String, Integer> distance = new HashMap<>();
        for (String vertex : adjList.keySet()) {
            distance.put(vertex, Integer.MAX_VALUE);
        }
        distance.put(origin, 0);

        Queue<String> queue = new LinkedList<>();
        queue.offer(origin);
        Map<List<String>, Integer> flightsWithCapacity = getFlightsWithCapacity(flightsList);
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(destination)) {
                return distance.get(current);
            }

            if (adjList.get(current) == null) {
                return 0;
            }

            for (String neighbor : adjList.get(current)) {
                if (distance.get(neighbor) == Integer.MAX_VALUE) {

                    List<String> key = List.of(current, neighbor);
                    if (flightsWithCapacity.get(key) >= passengers) {
                        distance.put(neighbor, distance.get(current) + 1);
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return 0;
    }

    static boolean validateInputsSubtask3(List<String> flightsList, String origin, String destination,
        Integer passengers) {
        if (flightsList == null || flightsList.isEmpty()) {
            return false;
        }

        if (origin == null || destination == null || passengers == null) {
            return false;
        }

        if (passengers == 0) {
            return false;
        }

        if (adjList.isEmpty()) {
            return false;
        }

        return true;
    }

    static Map<List<String>, Integer> getFlightsWithCapacity(List<String> flightsAndCapacity) {
        Map<List<String>, Integer> flightsCapacity = new HashMap<>();

        for (String flight : flightsAndCapacity) {
            String[] flightArr = flight.split(",");
            flightsCapacity.put(Arrays.asList(flightArr[0], flightArr[1]), Integer.valueOf(flightArr[2]));
        }

        return flightsCapacity;
    }
}