package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean flag = true;
        int vibor;
        while (flag){
            System.out.println("\nВыберите действие: ");
            System.out.println("1.Создать объект: ");
            System.out.println("2.Изменить характеристики объекта: ");
            System.out.println("3.Удалить объект: ");
            System.out.println("4.Вывести список объектов: ");
            System.out.println("5.Сохранить состояния объектов в XML файл:");
            System.out.println("6.Считать состояния объектов из XML файла:");
            System.out.println("7.Завершить программу: ");
            vibor = in.nextInt();
            switch (vibor) {
                case 1 -> {
                    Operations.flag1 = true;
                    Operations.Sozdat();
                }
                case 2 -> {
                    Operations.flag2 = true;
                    Operations.Change();
                }
                case 3 -> Operations.Delete();
                case 4 -> Operations.Wiwod();
                case 5 -> Operations.ConvertObjecttoXML();
                case 7 -> flag = false;
                default -> System.out.println("Введено неверное значение:");
            }
        }
    }
}