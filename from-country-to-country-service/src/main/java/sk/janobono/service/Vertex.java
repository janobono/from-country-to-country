package sk.janobono.service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Vertex {

    private String code;

    private Set<Edge> edges;

    public Vertex(String code) {
        this.code = code;
        edges = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(code, vertex.code);
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }

    public void addEdge(Vertex vertex) {
        edges.add(new Edge(vertex));
    }

    public String getCode() {
        return code;
    }

    public Edge[] getEdges() {
        Edge[] result = new Edge[edges.size()];
        return edges.toArray(result);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "code='" + code + '\'' +
                '}';
    }
}
