package HW_7;

import java.util.*;

public class Graph {
    private Stack way = new Stack(); // добавил стек для вывода
    private final List<Vertex> vertexList;
    private final boolean[][] adjMat;

    public Graph(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.adjMat = new boolean[maxVertexCount][maxVertexCount];
    }

    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    public void addEdges(String start, String second, String... other) {
        addEdge(start, second);
        for (String another : other) {
            addEdge(start, another);
        }
    }

    private void addEdge(String start, String finish) {
        int startIndex = indexOf(start);
        int finishIndex = indexOf(finish);

        if (startIndex == -1 || finishIndex == -1) {
            throw new IllegalArgumentException("Invalid label for edge");
        }

        adjMat[startIndex][finishIndex] = true;
        adjMat[finishIndex][startIndex] = true;
    }

    private int indexOf(String vertexLabel) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexLabel.equals(vertexList.get(i).getLabel())) {
                return i;
            }
        }

        return -1;
    }

    public int getSize() {
        return vertexList.size();
    }

    private void displayVertex(Vertex vertex) {
        System.out.println(vertex.getLabel());
    }

    public void display() {
        for (int i = 0; i < getSize(); i++) {
            System.out.print(vertexList.get(i));
            for (int j = 0; j < getSize(); j++) {
                if (adjMat[i][j]) {
                    System.out.print(" -> " + vertexList.get(j));
                }
            }
            System.out.println();
        }
    }

    /**
     * англ. Depth-first search, DFS
     *
     * @param startLabel
     */
    public void dfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start label");
        }

        Stack<Vertex> stack = new Stack<>();

        Vertex vertex = vertexList.get(startIndex);
        visitVertex(vertex, stack);

        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex != null) {
                visitVertex(vertex, stack);
            }
            else {
                stack.pop();
            }
        }
        resetVertexState();
    }

    /**
     * англ. breadth-first search, BFS
     *
     * @param startLabel
     */
    public void bfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start label");
        }

        Queue<Vertex> queue = new LinkedList<>();

        Vertex vertex = vertexList.get(startIndex);
        visitVertex(vertex, queue);

        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(vertex, queue);
            }
            else {
                queue.remove();
            }
        }
        resetVertexState();
    }
//-------------------------метод поиска кратчайшего пути--------------------------------------------
    public void theClosestWay(String start, String finish) {
        try {
            int startIndex = indexOf(start);
            int finishIndex = indexOf(finish);
            if (startIndex == -1 || finishIndex == -1) {
                throw new IllegalArgumentException("Invalid search label");
            }

            Queue<Vertex> queue = new LinkedList<>();
            Vertex vertex = vertexList.get(startIndex);
            visitVertex(vertex, queue);

            while (!queue.isEmpty()) {
                if (start.equals(finish)) { // базовый случай
                    way.push(start);
                    System.out.println("Минимальное количество остановок на пути "
                            + start + "-" + way.get(0) + "(" + (way.size() - 1) + "):");
                    break;
                }
                vertex = getNearUnvisitedVertex(queue.peek());
                if (vertex != null) {
                    visitVertex(vertex, queue);
                    if (vertex.getLabel().equals(finish)) { // рекурсивный случай
                        way.push(vertex.getLabel()); // если достигли назначения добавляем его в стек
                        resetVertexState();
                        theClosestWay(start, queue.peek().getLabel()); // рекурсия с вершиной, из которой пришли
                        break;
                    }
                } else {
                    queue.remove();
                }
            }
            resetVertexState();
            showWay(way); // выводим маршрут
        } catch (IllegalArgumentException e) {
            System.out.println("\nГорода нет в базе данных. Проверьте корректность написания.");
        }
    }

    private void showWay(Stack stack){ // метод для вывода
        int size = stack.size();
        for (int i = 0; i < size ; i++) {
            if (i== size-1){
                System.out.println(stack.pop() +"\n");}
            else
                System.out.print(stack.pop()+ "--> ");
        }
    }
    //------------------------------------дальше код по вебинару----------------------------------------------------
    private void resetVertexState() {
        for (Vertex vertex : vertexList) {
            vertex.setVisited(false);
        }
    }

    private Vertex getNearUnvisitedVertex(Vertex peek) {
        int peekIndex = vertexList.indexOf(peek);
        for (int i = 0; i < getSize(); i++) {
            if (adjMat[peekIndex][i] && !vertexList.get(i).isVisited()) {
                return vertexList.get(i);
            }
        }

        return null;
    }

    private void visitVertex(Vertex vertex, Stack<Vertex> stack) {
        displayVertex(vertex);
        stack.push(vertex);
        vertex.setVisited(true);
    }

    private void visitVertex(Vertex vertex, Queue<Vertex> queue) {
        //displayVertex(vertex); // чтобы лишнего вывода не было закомитил
        queue.add(vertex);
        vertex.setVisited(true);
    }
}