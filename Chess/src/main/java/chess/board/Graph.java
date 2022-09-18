package chess.board;

import java.util.*;

public class Graph<T> implements Iterable<T> {

    private class GraphNode<T> {
        public T value;
        public LinkedList<GraphNode<T>> next;

        public GraphNode(T value, LinkedList<GraphNode<T>> next) {
            this.value = value;
            this.next = next;
        }

        public GraphNode(T value) {
            this(value, null);
        }

        public GraphNode() {
            this(null);
        }

    }

    private GraphNode<T> head = null;

    public Graph() {
        head = new GraphNode<>();
    }

    public void addVertex(T vertex) {
        if (head.next == null) {
            head.next = new LinkedList<>();
        }
        GraphNode<T> newNode = new GraphNode<T>(vertex);
        head.next.addLast(newNode);
    }

    @Override
    public String toString() {
        String str = "";
        for (GraphNode<T> element : head.next) {
            str = str.concat(element.value.toString());
        }
        return str;
    }

    public void removeVertex(T vertex) {
        int v = findIndex(vertex);
        head.next.remove(v);
    }

    public void createEdge(T vertex1, T vertex2) {
        int v1 = findIndex(vertex1);
        int v2 = findIndex(vertex2);
        if (head.next.get(v1).next == null) {
            head.next.get(v1).next = new LinkedList<>();
        }
        if (head.next.get(v2).next == null) {
            head.next.get(v2).next = new LinkedList<>();
        }
        head.next.get(v1).next.add(head.next.get(v2));
        head.next.get(v2).next.add(head.next.get(v1));
    }

    public void createEdgeFromV1ToV2(T vertex1, T vertex2) {
        int v1 = findIndex(vertex1);
        int v2 = findIndex(vertex2);
        if (head.next.get(v1).next == null) {
            head.next.get(v1).next = new LinkedList<>();
        }
        head.next.get(v1).next.add(head.next.get(v2));
    }

    private int findIndex(T vertex) {
        for (int i = 0; i < head.next.size(); i++) {
            if (vertex.equals(head.next.get(i).value)) {
                return i;
            }
        }
        return -1;
    }

    public LinkedList<T> allEdges(T vertex) {
        GraphNode<T> next = head;
        LinkedList<T> returnable = new LinkedList<>();
        for (GraphNode<T> element : head.next) {
            if (element.value.equals(vertex) && element.next != null) {
                for (GraphNode<T> element2 : element.next) {
                    returnable.add(element2.value);
                }
                return returnable;
            }
        }
        return returnable;
    }

    public void removeEdges(T vertex) {
        for (GraphNode<T> element : head.next) {
            if (element.value.equals(vertex)) {
                if (element.next != null) {
                    element.next.clear();
                }
                return;
            }
        }
    }

    public boolean isEdge(T vertex1, T vertex2) {

        int v1 = findIndex(vertex1);
        int v2 = findIndex(vertex2);

        if (v1 == -1 || v2 == -1) {
            return false;
        }
        if (head.next.get(v1).next == null || head.next.get(v2).next == null) {
            return false;
        }
        return head.next.get(v1).next.contains(head.next.get(v2));
    }

    public void removeEdge(T vertex1, T vertex2) {
        int v1 = findIndex(vertex1);
        int v2 = findIndex(vertex2);

        head.next.get(v1).next.remove(head.next.get(v2));
        //head.next.get(v2).next.remove(head.next.get(v1));
    }

    public T getVertex(int i) {
        return head.next.get(i).value;
    }

    public void replaceVertices(T oldVertex, T newVertex) throws Exception {
        int v = findIndex(oldVertex);
        if (v < 0) {
            throw new Exception("oldVertex is not found");
        }
        head.next.get(v).value = newVertex;
    }


    @Override
    public Iterator<T> iterator() {
        class GraphIterator implements Iterator<T> {
            ListIterator<GraphNode<T>> iter;

            public GraphIterator(GraphNode<T> head) {
                iter = head.next.listIterator();
            }

            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public T next() {
                return iter.next().value;
            }
        }

        return new GraphIterator(head);
    }
}
