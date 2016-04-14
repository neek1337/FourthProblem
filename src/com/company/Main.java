package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите действие:");
        System.out.println("1 - Вычисление частотных таблиц языка;");
        System.out.println("2 - Шифрование текста");
        System.out.println("3 - Расшифрование языка");
        System.out.println("4 - Перебор возможных ключей;");
        System.out.println("5 - Шифрование текста.");

        int n = scanner.nextInt();

        switch (n) {
            case (1): {
                new Frequency().run();
                break;
            }

            case (2): {
                new Encrypt().run();
                break;
            }

            case (3): {
                new Decrypt().run(scanner);
                break;
            }

            case (4): {

                break;
            }

            case (5): {

                break;
            }

            default: {
                System.out.println("Введеное число должно быть в диапозоне от 1 до 5.");
            }
            //


            // new Encrypt().run();
        }


    }
}
