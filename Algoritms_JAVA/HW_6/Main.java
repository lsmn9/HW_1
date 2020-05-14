package HW_6;


public class Main {
    final static int AMOUNT_OF_TREES=20;
    final static int LEVEL=4; //задаваемая глубина

    public static void main(String[] args) {
        int balance=0; // для подсчета сбалансированных деревьев
        for (int j = 0; j <AMOUNT_OF_TREES ; j++) {
            System.out.println("\nTree # " + (j+1) +"\n");
            Tree<Integer> tree= new TreeImpl<>();
            int current_level=0; // переменная для просмотра уровня текущего элемента
            for (int i = 0; i < (2*LEVEL-1) ; i++) {
                int rnd = (int) (Math.random() * 100);
                tree.add(rnd);
                current_level = tree.levelCounter(rnd);
                tree.balanceConter(rnd);
                if (current_level == 5) {tree.remove(rnd);break;}
        }
            tree.balanceCounter(); // сбалансировано ли дерево
            if (tree.balanceCounter()==1){balance++;}  // если да то +1
            tree.display();
        }
        System.out.println("Процент сбалансированных деревьев: " + (100*balance/AMOUNT_OF_TREES));
    }
}
