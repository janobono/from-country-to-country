package sk.janobono.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.janobono.component.CountryParser;
import sk.janobono.component.DataLinkClient;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoutingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoutingService.class);

    private DataLinkClient dataLinkClient;

    private CountryParser countryParser;

    @Autowired
    public void setDataLinkClient(DataLinkClient dataLinkClient) {
        this.dataLinkClient = dataLinkClient;
    }

    @Autowired
    public void setCountryParser(CountryParser countryParser) {
        this.countryParser = countryParser;
    }

    public List<String> findRoute(String origin, String destination) {
        LOGGER.debug("findRoute({},{})", origin, destination);
        List<String> result = new ArrayList<>();
        byte[] data = dataLinkClient.getData();
        Map<String, List<String>> countryMap = countryParser.parseCountryMap(data);
        if (countryMap.containsKey(origin) && countryMap.containsKey(destination)) {
            result = findRoute(origin, destination, countryMap);
        }
        LOGGER.debug("findRoute({},{})={}", origin, destination, result);
        return result;
    }

    public List<String> findRoute(String origin, String destination, Map<String, List<String>> countryMap) {
        LOGGER.debug("findRoute({},{},{})", origin, destination, countryMap);
        HashMap<String, Vertex> graph = createGraph(countryMap);
        List<Vertex> path = findPath(graph.get(origin), graph.get(destination));
        return path.stream().map(Vertex::getCode).collect(Collectors.toList());
    }

    private HashMap<String, Vertex> createGraph(Map<String, List<String>> countryMap) {
        HashMap<String, Vertex> graph = new HashMap<>();
        for (String fromKey : countryMap.keySet()) {
            Vertex from;
            if (graph.containsKey(fromKey)) {
                from = graph.get(fromKey);
            } else {
                from = new Vertex(fromKey);
                graph.put(fromKey, from);
            }

            for (String toKey : countryMap.get(fromKey)) {
                Vertex to;
                if (graph.containsKey(toKey)) {
                    to = graph.get(toKey);
                } else {
                    to = new Vertex(toKey);
                    graph.put(toKey, to);
                }

                from.addEdge(to);
                to.addEdge(from);
            }
        }
        return graph;
    }

    private List<Vertex> findPath(Vertex origin, Vertex destination) {
        LOGGER.debug("findPath({},{})", origin, destination);
        Map<Vertex, Boolean> visited = new HashMap<>();
        Map<Vertex, Vertex> previous = new HashMap<>();

        Queue<Vertex> queue = new LinkedList<>();
        Vertex current = origin;
        queue.add(current);
        visited.put(current, true);
        while (!queue.isEmpty()) {
            current = queue.remove();
            if (current.equals(destination)) {
                break;
            } else {
                for (Edge node : current.getEdges()) {
                    if (!visited.containsKey(node.destination())) {
                        queue.add(node.destination());
                        visited.put(node.destination(), true);
                        previous.put(node.destination(), current);
                    }
                }
            }
        }

        List<Vertex> path = new ArrayList<>();
        if (!current.equals(destination)) {
            LOGGER.warn("can't reach destination");
        } else {
            for (Vertex vertex = destination; vertex != null; vertex = previous.get(vertex)) {
                path.add(vertex);
            }
        }
        Collections.reverse(path);
        LOGGER.debug("findPath({},{})={}", origin, destination, path);
        return path;
    }
}
