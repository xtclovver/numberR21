package org.used;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class div21 extends JFrame {
    // Константы для размера последовательности и ограничения чисел
    private static final int SIZE = 1000;
    private static final int MAX_VALUE = 10000;

    private JLabel resultLabel; // Метка для вывода результата

    public div21() {
        // Настройка окна программы
        setTitle("Min Product Divisible by 21");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI(); // Инициализация пользовательского интерфейса
    }

    private void initUI() {
        // Создаем панель для расположения компонентов
        JPanel panel = new JPanel(new BorderLayout());

        // Создаем кнопку для перезапуска генерации чисел
        JButton regenerateButton = new JButton("Сгенерировать заново");
        regenerateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateAndDisplayResult(); // Перегенерировать числа и вывести результат
            }
        });

        // Добавляем метку для отображения результата
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Добавляем кнопку и метку на панель
        panel.add(regenerateButton, BorderLayout.SOUTH);
        panel.add(resultLabel, BorderLayout.CENTER);

        // Устанавливаем панель как основную
        add(panel);

        generateAndDisplayResult(); // Первый запуск генерации
        setVisible(true);
    }

    private void generateAndDisplayResult() {
        Random random = new Random();

        // Переменные для отслеживания минимальных значений кратных 3, 7, и 21
        int minDivBy3 = Integer.MAX_VALUE;
        int minDivBy7 = Integer.MAX_VALUE;
        int minDivBy21 = Integer.MAX_VALUE;

        // Переменные для хранения минимального произведения и элементов
        int minProduct = Integer.MAX_VALUE;
        int element1 = -1;
        int element2 = -1;
        for (int i = 0; i < SIZE; i++) {
            int number = random.nextInt(MAX_VALUE + 1);  // Генерация случайного числа от 0 до 10000

            if (number == 0) continue;  // Пропускаем нули, так как произведение с ними не имеет смысла

            // Если число кратно 21, проверяем его с предыдущими кратными 21
            if (number % 21 == 0) {
                if (minDivBy21 != Integer.MAX_VALUE) {
                    int product = number * minDivBy21;
                    if (product < minProduct) {
                        minProduct = product;
                        element1 = number;
                        element2 = minDivBy21;
                    }
                }
                minDivBy21 = Math.min(minDivBy21, number);
            }
            // Если число кратно 3, проверяем его с минимальным числом, кратным 7
            else if (number % 3 == 0) {
                if (minDivBy7 != Integer.MAX_VALUE) {
                    int product = number * minDivBy7;
                    if (product % 21 == 0 && product < minProduct) {
                        minProduct = product;
                        element1 = number;
                        element2 = minDivBy7;
                    }
                }
                minDivBy3 = Math.min(minDivBy3, number);
            }
            // Если число кратно 7, проверяем его с минимальным числом, кратным 3
            else if (number % 7 == 0) {
                if (minDivBy3 != Integer.MAX_VALUE) {
                    int product = number * minDivBy3;
                    if (product % 21 == 0 && product < minProduct) {
                        minProduct = product;
                        element1 = number;
                        element2 = minDivBy3;
                    }
                }
                minDivBy7 = Math.min(minDivBy7, number);
            }
        }
        String message;
        if (minProduct != Integer.MAX_VALUE) {
            message = String.format("Минимальное произведение: %d%nЭлементы: %d и %d",
                    minProduct, element1, element2);
        } else {
            message = "Не найдено произведение двух элементов, кратное 21";
        }

        resultLabel.setText("<html>" + message.replace("\n", "<br>") + "</html>");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(div21::new);
    }
}