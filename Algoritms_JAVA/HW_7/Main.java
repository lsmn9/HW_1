package HW_7;

public class Main {
    public static void main(String[] args) {
        testClosestWay();
    }

    private static void testClosestWay() {
        Graph graph = new Graph(10);
        graph.addVertex("Воронеж");
        graph.addVertex("Москва");
        graph.addVertex("Калуга");
        graph.addVertex("Тула");
        graph.addVertex("Липецк");
        graph.addVertex("Орёл");
        graph.addVertex("Курск");
        graph.addVertex("Тамбов");
        graph.addVertex("Рязань");
        graph.addVertex("Саратов");

        graph.addEdges("Москва", "Тула", "Калуга", "Рязань");
        graph.addEdges("Тула", "Липецк");
        graph.addEdges("Орёл", "Калуга", "Курск");
        graph.addEdges("Тамбов", "Рязань", "Саратов");
        graph.addEdges("Воронеж","Липецк", "Курск", "Саратов");

        graph.theClosestWay("Тамбов", "Липецк");
        graph.theClosestWay("Москва", "Воронеж");
        graph.theClosestWay("Тула","Орёл");
    }
}
