package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphTest {

    @Test
    public void testGetShortestTime_StandardCase() {
        Graph.createGraph(Arrays.asList("SOF,IST", "IST,CMB", "CMB,MLE"));
        int shortestTime = Graph.getShortestTime("SOF", "MLE");

        assertEquals(3, shortestTime);
    }

    @Test
    public void testGetShortestTime_PathDoesNotExist() {
        Graph.createGraph(Arrays.asList("SOF,IST", "CMB,MLE"));
        int shortestTime = Graph.getShortestTime("SOF", "MLE");

        assertEquals(0, shortestTime);
    }

    @Test
    public void testGetShortestTime_FlightsDoNotExist() {
        Graph.createGraph(new ArrayList<>());
        int shortestTime = Graph.getShortestTime("SOF", "MLE");

        assertEquals(0, shortestTime);
    }

    @Test
    public void testGetShortestTimeWithCapacity_StandardCase() {
        List<String> flightsList = Arrays.asList("SOF,MLE,1", "SOF,LON,3", "LON,MLE,1");
        Graph.createGraph(flightsList);
        int shortestTime = Graph.getShortestTimeWithCapacity(flightsList, "SOF", "LON", 3);

        assertEquals(1, shortestTime);
    }

    @Test
    public void testGetShortestTimeWithCapacity_PathDoesNotExist() {
        List<String> flightsList = Arrays.asList("SOF,MLE,1", "SOF,LON,3", "LON,MLE,1");
        Graph.createGraph(flightsList);
        int shortestTime = Graph.getShortestTimeWithCapacity(flightsList, "SOF", "IST", 3);

        assertEquals(0, shortestTime);
    }

    @Test
    public void testGetShortestTimeWithCapacity_NoPassengersArePresent() {
        List<String> flightsList = Arrays.asList("SOF,MLE,1", "SOF,LON,3", "LON,MLE,1");
        Graph.createGraph(flightsList);
        int shortestTime = Graph.getShortestTimeWithCapacity(flightsList, "SOF", "MLE", 0);

        assertEquals(0, shortestTime);
    }

    @Test
    public void testGetShortestTimeWithCapacity_FlightsDoNotExist() {
        List<String> flightsList = new ArrayList<>();
        Graph.createGraph(flightsList);
        int shortestTime = Graph.getShortestTimeWithCapacity(flightsList, "SOF", "MLE", 9);

        assertEquals(0, shortestTime);
    }

    @Test
    public void testGetFlightsWithCapacity_StandardCase() {
        List<String> flightsList = Arrays.asList("SOF,MLE,1", "SOF,LON,3", "LON,MLE,1");
        Map<List<String>, Integer> flightsWithCapacity = Graph.getFlightsWithCapacity(flightsList);

        assertEquals(Integer.valueOf(1), flightsWithCapacity.get(Arrays.asList("SOF", "MLE")));
        assertEquals(Integer.valueOf(3), flightsWithCapacity.get(Arrays.asList("SOF", "LON")));
        assertEquals(Integer.valueOf(1), flightsWithCapacity.get(Arrays.asList("LON", "MLE")));
    }

    @Test
    public void testSubtask1_StandardCase() {
        List<String> flightsList = Arrays.asList("SOF,IST", "IST,CMB", "CMB,MLE");
        int shortestTime = Graph.subtask1(flightsList, "SOF", "MLE");

        assertEquals(3, shortestTime);
    }

    @Test
    public void testSubtask1PathDoesNotExist() {
        List<String> flightsList = Arrays.asList("SOF,IST", "IST,CMB", "CMB,MLE");
        int shortestTime = Graph.subtask1(flightsList, "CMB", "LON");

        assertEquals(0, shortestTime);
    }

    @Test
    public void testSubtask1_NullFlightListInput() {
        int shortestTime = Graph.subtask1(null, "SOF", "MLE");

        assertEquals(0, shortestTime);
    }

    @Test
    public void testSubtask1_InvalidOriginInput() {
        List<String> flightsList = Arrays.asList("SOF,IST", "IST,CMB", "CMB,MLE");
        int shortestTime = Graph.subtask1(flightsList, "?", "MLE");

        assertEquals(0, shortestTime);
    }

    @Test
    public void testSubtask1_NullOriginInput() {
        List<String> flightsList = Arrays.asList("SOF,IST", "IST,CMB", "CMB,MLE");
        int shortestTime = Graph.subtask1(flightsList, null, "MLE");

        assertEquals(0, shortestTime);
    }


    @Test
    public void testSubtask1_InvalidDestinationInput() {
        List<String> flightsList = Arrays.asList("SOF,IST", "IST,CMB", "CMB,MLE");
        int shortestTime = Graph.subtask1(flightsList, "SOF", "?");

        assertEquals(0, shortestTime);
    }

    @Test
    public void testSubtask1_NullDestinationInput() {
        List<String> flightsList = Arrays.asList("SOF,IST", "IST,CMB", "CMB,MLE");
        int shortestTime = Graph.subtask1(flightsList, "SOF", null);

        assertEquals(0, shortestTime);
    }

    @Test
    public void testSubtask3_StandardCase() {
        List<String> flightsList = Arrays.asList("SOF,MLE,1", "SOF,LON,2", "LON,MLE,4");
        int shortestTime = Graph.subtask3(flightsList, "SOF", "MLE", 2);

        assertEquals(2, shortestTime);
    }

    @Test
    public void testSubtask3_PathDoesNotExist() {
        List<String> flightsList = Arrays.asList("SOF,MLE,1", "SOF,LON,2", "LON,MLE,4");
        int shortestTime = Graph.subtask3(flightsList, "SOF", "IST", 2);

        assertEquals(0, shortestTime);
    }

    @Test
    public void testSubtask3_NullFlightListInput() {
        int shortestTime = Graph.subtask3(null, "SOF", "MLE", 2);

        assertEquals(0, shortestTime);
    }

    @Test
    public void testSubtask3_InvalidOriginInput() {
        List<String> flightsList = Arrays.asList("SOF,MLE,1", "SOF,LON,2", "LON,MLE,4");
        int shortestTime = Graph.subtask3(flightsList, "?", "MLE", 2);


        assertEquals(0, shortestTime);
    }

    @Test
    public void testSubtask3_NullOriginInput() {
        List<String> flightsList = Arrays.asList("SOF,MLE,1", "SOF,LON,2", "LON,MLE,4");
        int shortestTime = Graph.subtask3(flightsList, null, "MLE", 2);


        assertEquals(0, shortestTime);
    }

    @Test
    public void testSubtask3_InvalidDestinationInput() {
        List<String> flightsList = Arrays.asList("SOF,MLE,1", "SOF,LON,2", "LON,MLE,4");
        int shortestTime = Graph.subtask3(flightsList, "SOF", "?", 2);


        assertEquals(0, shortestTime);
    }

    @Test
    public void testSubtask3_NullDestinationInput() {
        List<String> flightsList = Arrays.asList("SOF,MLE,1", "SOF,LON,2", "LON,MLE,4");
        int shortestTime = Graph.subtask3(flightsList, "SOF", null, 2);


        assertEquals(0, shortestTime);
    }

    @Test
    public void testSubtask3_NoPassengersPresent() {
        List<String> flightsList = Arrays.asList("SOF,MLE,1", "SOF,LON,2", "LON,MLE,4");
        int shortestTime = Graph.subtask3(flightsList, "SOF", "LON", 0);


        assertEquals(0, shortestTime);
    }

    @Test
    public void testSubtask3_NullPassengersInput() {
        List<String> flightsList = Arrays.asList("SOF,MLE,1", "SOF,LON,2", "LON,MLE,4");
        int shortestTime = Graph.subtask3(flightsList, "SOF", "LON", null);


        assertEquals(0, shortestTime);
    }
}
