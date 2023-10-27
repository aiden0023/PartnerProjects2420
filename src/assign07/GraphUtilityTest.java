package assign07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static assign07.GraphUtility.buildListsFromDot;
import static org.junit.jupiter.api.Assertions.*;

public class GraphUtilityTest {

    ArrayList<String> sources = new ArrayList<>();
    ArrayList<String> destinations = new ArrayList<>();
    ArrayList<String> emptySources = new ArrayList<>();
    ArrayList<String> emptyDestinations = new ArrayList<>();
    ArrayList<String> singleSources = new ArrayList<>();
    ArrayList<String> singleDestinations = new ArrayList<>();

    @BeforeEach
    public void setup() {
        buildListsFromDot("src/assign07/digraphG", sources, destinations);
        buildListsFromDot("src/assign07/oneDigraph", singleSources, singleDestinations);
    }

    @Test
    public void testAreConnected() {
        assertTrue(GraphUtility.areConnected(sources, destinations, "2", "1"));
        assertTrue(GraphUtility.areConnected(sources, destinations, "3", "4"));
        assertFalse(GraphUtility.areConnected(sources, destinations, "1", "2"));
        assertFalse(GraphUtility.areConnected(sources, destinations, "5", "2"));
        assertThrows(IllegalArgumentException.class, ()->{GraphUtility.areConnected(sources, destinations, "6", "7");});
        assertThrows(IllegalArgumentException.class, ()->{GraphUtility.areConnected(emptySources, emptyDestinations, "1", "2");});
        assertThrows(IllegalArgumentException.class, ()->{GraphUtility.areConnected(singleSources, singleDestinations, "1", "2");});
    }

    @Test
    public void testShortestPath() {
        List<String> path1 = GraphUtility.shortestPath(sources, destinations, "2", "1");
        List<String> path2 = GraphUtility.shortestPath(sources, destinations, "3", "1");

        assertEquals(Arrays.asList("2", "1"), path1);
        assertEquals(Arrays.asList("3", "4", "1"), path2);
        assertThrows(IllegalArgumentException.class, ()->{GraphUtility.shortestPath(sources, destinations, "1", "3");});

    }

    @Test
    public void testSort() {
        List<String> sorted = GraphUtility.sort(sources, destinations);

        Set<String> validOrder1 = new HashSet<>(Arrays.asList("2", "3", "4", "5", "1"));

        assertTrue(validOrder1.containsAll(sorted) && sorted.containsAll(validOrder1));
    }
}
