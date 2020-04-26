import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Window{
    public static void main(String[] args) {
        new MyWindow();
    }
}

class MyWindow extends JFrame {
    int cnt = 0; // счетчик хода
    int last_i = 0, last_j = 0; // вводятся для идентификации предыдушей карты
    int pairsCnt = 8; // счетчик НЕугаданных пар
    public static int SIZE = 4; // размер поля
    public static Random rand = new Random(); // вводится для изменения позиций карт
    // ссылки на картинки:
    public static final ImageIcon outSide = new ImageIcon("src/Pictures/Рубашка.jpg");
    public static final ImageIcon Qk = new ImageIcon("src/Pictures/Qk.jpg");
    public static final ImageIcon p6 = new ImageIcon("src/Pictures/6P.jpg");
    public static final ImageIcon b7 = new ImageIcon("src/Pictures/7b.jpg");
    public static final ImageIcon p8 = new ImageIcon("src/Pictures/8p.png");
    public static final ImageIcon Qb = new ImageIcon("src/Pictures/Qb.png");
    public static final ImageIcon Kh = new ImageIcon("src/Pictures/Kh.png");
    public static final ImageIcon Kp = new ImageIcon("src/Pictures/Kp.png");
    public static final ImageIcon Kk = new ImageIcon("src/Pictures/Kk.jpg");

    public MyWindow() {

        ImageIcon[][] arr = new ImageIcon[][]{// первоначальный массив
                    {Qk, p6, b7, p8},
                    {Qb, Kh, Kp, Kk},
                    {Qk, p6, b7, p8},
                    {Qb, Kh, Kp, Kk}};

        ImageIcon[][] pic = new ImageIcon[4][4];  // пустой массив для игры

        // заполяняем массив для игры в случайном порядке
            boolean isCellValid;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE ; j++) {
                    int x, y;
                    do {
                        x = rand.nextInt(SIZE);
                        y = rand.nextInt(SIZE);
                        isCellValid = pic[y][x] != null;
                    } while (isCellValid);
                    pic [y][x] = arr[i][j];}}

            // задаем параметры поля и кнопок
            setTitle("Угадай все пары");
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setBounds(300, 300, 400, 400);
            JButton[][] jbs = new JButton[4][4];
            setLayout(new GridLayout(4, 4));

        // создаем логику игры
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {

                    System.out.print(pic[i][j]+"   "); // для удобства проверки, можно убрать

                    // добавляем кнопки рубашкой вверх(outSide)
                    jbs[i][j] = new JButton();
                    add(jbs[i][j]);
                    jbs[i][j].setIcon(outSide);
                    // создаем копии кпонок, иконок, счетчиков для работы со Слушателем
                    JButton copyButton = jbs[i][j];
                    ImageIcon copyIcon = pic[i][j];
                    int finalI = i;
                    int finalJ = j;
               // создаем Слушатель
                    copyButton.addActionListener(action -> {
                        copyButton.setIcon(copyIcon); // разварачиваем карту
                        copyButton.setEnabled(false); // не позволяем снова выбрать эту же карту(блокируем)
                        copyButton.setDisabledIcon(copyIcon); // чтобы не была на задней фоне (как на уроке)
                        cnt++; // счетчик хода, после второго хода проверяем совпали ли карты - заходим в if

                        if (cnt == 2) {
                            JButton copyLast = jbs[last_i][last_j]; // копия первой открытой карты

                            if (pic[last_i][last_j] == pic[finalI][finalJ]) { // совпадение
                                pairsCnt--; // счетсчик НЕугаданных пар уменьшается
                            } else {
                           // при несовпадении:
                                copyLast.setEnabled(true); // разблокируем первую карту
                                copyButton.setEnabled(true); // разблокируем вторую карту
                                copyLast.setIcon(outSide); // разворачиваем первую карту рубашкой вверх
                                copyButton.setDisabledIcon(outSide); // разворачиваем вторую карту рубашкой вверх
                                copyButton.setIcon(outSide);
                            }
                            cnt = 0; // обнуляем счетчик хода
                        }
                        last_i = finalI; // для идентификации первой карты
                        last_j = finalJ; // также

                        // условие победы:
                        if (pairsCnt == 0) { // Неугаддные пары = 0, код ниже по аналогии с уроком
                            JFrame alert = new JFrame("Поздравляем! Вы всё отыскали!");
                            alert.setLocation(500, 400);
                            alert.setSize(300, 100);
                            JPanel alertPanel = new JPanel(new FlowLayout());
                            JButton newGame = new JButton("Начать сначала");
                            newGame.addActionListener(a -> {
                                this.dispose();
                                alert.dispose();
                                new MyWindow();
                            });
                            JButton close = new JButton("Выйти");
                            close.addActionListener(a -> {
                                dispose();
                                alert.dispose();
                            });
                            alertPanel.add(newGame);
                            alertPanel.add(close);
                            alert.add(alertPanel);
                            alert.setResizable(false);
                            alert.setVisible(true);
                        }
                    });
                } System.out.println(); // для разделения массива на столбцы, можно убрать
            }  setVisible(true);
        }
    }



