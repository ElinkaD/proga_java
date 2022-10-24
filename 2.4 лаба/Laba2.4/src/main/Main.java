package main;

import java.awt.*; //применяется для размещения компонентов во фрейме
import java.awt.event.*; //  определяются интерфейс ActionListener и класс ActionEvent
import java.util.Random;
import javax.swing.*;

public class Main extends JFrame{ //контейнер верхнего уровня, который обычно применяется в Swing-приложениях

    private JPanel panel = new JPanel(new GridLayout(4, 4, 2, 2)); //поле (GridLayout контейнер, который позволяет создавать табличные представления)
    private int[][] numbers = new int[4][4];

    public Main(){ //конструктор
        super("Игра Пятнашки");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize(); // Получаем размер экрана монитора
        setBounds((screenSize.width/4 + screenSize.width/8), (screenSize.height/2 - screenSize.height/4), 300, 300); //установка позиции и размеров окна
        setResizable(false);   // размеры окна нельзя изменять
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //используется для закрытия JFrame, а также процесса Java.

        createMenu();

        Container container = getContentPane(); //извлекает слой панели контента, чтобы добавить к нему объект.
        panel.setDoubleBuffered(true);
        container.add(panel); //добавление панели
        this.addKeyListener(new MyKeyAdapter()); //позволяет использовать стрелку для перемещения в первый раз
        panel.addKeyListener(new MyKeyAdapter()); //позволяет использовать стрелки для перемещения во всех последующих разах


        generate(); // создание игровых клеток
        repaintField();
    }

    public void generate() { //случайным образом размещает пятнашки на доске 4 на 4
        Random generator = new Random();
        int[] invariants = new int[16];
        do {                //генерация пятнышек
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    numbers[i][j] = 0;
                    invariants[i * 4 + j] = 0;
                }
            }
            for (int i = 1; i < 16; i++) {
                int k, l;
                do {
                    k = generator.nextInt(4);
                    l = generator.nextInt(4);
                }
                while (numbers[k][l] != 0 | (k == 3 && l == 3));
                numbers[k][l] = i;
                invariants[k * 4 + l] = i;
            }

        }
        while (!canBeSolved(invariants)); // цикл создает пятнышки до тех пор, пока поле не заполниться
    }

    private boolean canBeSolved(int[] invariants) { //проверка на решаемость составленной таблицы
        int sum = 3;
        for (int i = 0; i < 16; i++) {
            for (int j = i + 1; j < 16; j++) {
                if (invariants[j] < invariants[i])
                    sum ++;
            }
        }
        return sum % 2 == 0;
    }

    public void repaintField() {  //добавление пятнашек в виде кнопок на панель
        panel.removeAll(); // очистка панели
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JButton button = new JButton(Integer.toString(numbers[i][j]));
                button.setFont(new Font("Serif", Font.PLAIN, 20)); //стиль текста
                button.setFocusable(false);
                panel.add(button);
                panel.grabFocus();   // захватываем фокус
                if (numbers[i][j] == 0) {
                    button.setVisible(false);
                } else
                  button.addActionListener(new Click());
                button.addKeyListener(new MyKeyAdapter());
            }
        }
        panel.validate(); //компановка контейнера
        panel.repaint();
        panel.grabFocus();
    }

    public boolean checkWin() { // возвращает true если пользователь решил головоломку
        boolean status = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 3 && j > 2)
                    break;
                if (numbers[i][j] != i * 4 + j + 1) {
                    status = false;
                }
            }
        }
        return status;
    }

    private void createMenu() { // оформление рамки и определение мнемоник
        JMenuBar menu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F); //установлние мнемоники для File на F

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H); //установлние мнемоники для Help на H

        JMenuItem about = new JMenuItem("About",KeyEvent.VK_A);
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK)); // Создаем ярлык для пунктов меню ctrl + A
        about.setActionCommand("About".toLowerCase());
        about.addActionListener(new NewMenuListener());

        for (String fileItem : new String [] { "New", "Exit" }) {  // роспись пункта file на 2
            JMenuItem item;
            if (fileItem.equals("New")) {
                item = new JMenuItem(fileItem, KeyEvent.VK_N);
                item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK)); // Создаем ярлык для пунктов меню ctrl + N
            }else {
                item = new JMenuItem(fileItem, KeyEvent.VK_ESCAPE);
                item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false)); // Создаем ярлык для пунктов меню esc
            }
            item.addActionListener(new NewMenuListener());
            item.setActionCommand(fileItem.toLowerCase());
            fileMenu.add(item);
        }
        fileMenu.insertSeparator(1);

        menu.add(fileMenu);
        menu.add(helpMenu);
        helpMenu.add(about);
        setJMenuBar(menu);
    }

    private class NewMenuListener implements ActionListener { //прописывание всех действий совершаемых при нажатие на пункты меню
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if ("exit".equals(command)) { // команда выход из приложения
                System.exit(0);
            }
            if ("new".equals(command)) { //перезапуск игры
                generate();
                repaintField();
            }
            if ("about".equals(command)) { //выводит диалоговое окно о разработчике
                JOptionPane.showMessageDialog(null, "Дусаева Элина Р3167 2022 г.", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private class Click implements ActionListener { // позволяет совершать клик по кнопке
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            button.setVisible(false);
            String name = button.getText();
            change(Integer.parseInt(name));
        }
    }

    private class MyKeyAdapter extends KeyAdapter { // внутренний класс для присваивания стрелкам клавиатуры определенных действий по перемещению пятнашек
        @Override
        public void keyPressed(KeyEvent e) { //метод присваивающий действия при нажатие на стрелки
            Coordinate c = findEmpty();
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:  // стрелка вверх
                    try {
                        change(numbers[c.row+1][c.column]);
                        break;
                    } catch (IndexOutOfBoundsException ignored) {
                        return;
                    }
                case KeyEvent.VK_DOWN: // стрелка вниз
                    try {
                        change(numbers[c.row-1][c.column]);
                        break;
                    } catch (IndexOutOfBoundsException ignored) {
                        return;
                    }
                case KeyEvent.VK_RIGHT: // стрелка вправо
                    try {
                        change(numbers[c.row][c.column-1]);
                        break;
                    } catch (IndexOutOfBoundsException ignored) {
                        return;
                    }
                case KeyEvent.VK_LEFT: // стрелка влево
                    try {
                        change(numbers[c.row][c.column+1]);
                        break;
                    } catch (IndexOutOfBoundsException ignored) {
                        return;
                    }
            }
        }
    }

    private class Coordinate { //внутренний класс для передачи двух переменных для местоположения ячейки
        private int row;
        private int column;
        private Coordinate(int i, int j) {
            row = i;
            column = j;
        }
    }

    private Coordinate findEmpty(){ //поиск пустой ячейки и возврат ее строки и столбца
        int k; int l;
        for (k = 0; k < 4; k++) {
            for (l = 0; l < 4; l++) {
                if (numbers[k][l] == 0) {
                    return (new Coordinate(k, l));
                }
            }
        }
        return null;
    }

    public void change(int num) {  //метод для перемещения пятнышек на пустой место
        int i = 0, j = 0;
        for (int k = 0; k < 4; k++) {
            for (int l = 0; l < 4; l++) {
                if (numbers[k][l] == num) {
                    i = k;
                    j = l;
                }
            }
        }
        if (i > 0) {
            if (numbers[i - 1][j] == 0) {
                numbers[i - 1][j] = num;
                numbers[i][j] = 0;
            }
        }
        if (i < 3) {
            if (numbers[i + 1][j] == 0) {
                numbers[i + 1][j] = num;
                numbers[i][j] = 0;
            }
        }
        if (j > 0) {
            if (numbers[i][j - 1] == 0) {
                numbers[i][j - 1] = num;
                numbers[i][j] = 0;
            }
        }
        if (j < 3) {
            if (numbers[i][j + 1] == 0) {
                numbers[i][j + 1] = num;
                numbers[i][j] = 0;
            }
        }

        repaintField();

        if (checkWin()) { // проверка после каждого хода на решаемость головоломки и вывод диалогового окна в случае решения
            JOptionPane.showMessageDialog(null, "ПОБЕДА!!!!!", "Congratulations", JOptionPane.INFORMATION_MESSAGE); //при решение выводит сообщение о победе
            generate();
            repaintField();
        }
    }


    public static void main(String[] args) {
        JFrame app = new Main();
        app.setVisible(true);
    }
}