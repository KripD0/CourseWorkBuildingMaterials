package org.example;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Operations {
    static ArrayList<Pesok> Pesok = new ArrayList<Pesok>();
    static ArrayList<Cement> Cement = new ArrayList<Cement>();
    static ArrayList<brick> Brick = new ArrayList<brick>();
    static ArrayList<glass> Glass = new ArrayList<glass>();
    static ArrayList massiv = new ArrayList();

    static double weight, density, fragility;
    static int radioactive, drying_time, value, streight, IdDelete, IDChange;
    static boolean flag1 = true, flag2 = true;
    static Scanner vvod = new Scanner(System.in);

    public static void ConvertObjecttoXML(){
        if(!Pesok.isEmpty()){
            for (int i=0; i< Pesok.size(); i++){
                massiv.add(Pesok.get(i));
            }
        }
        if(!Cement.isEmpty()){
            for (int i=0; i< Cement.size(); i++){
                massiv.add(Cement.get(i));
            }
        }
        if(!Brick.isEmpty()){
            for (int i=0; i< Brick.size(); i++){
                massiv.add(Brick.get(i));
            }
        }
        if(!Glass.isEmpty()){
            for (int i=0; i< Glass.size(); i++){
                Glass.add(Glass.get(i));
            }
        }
        for(int i = 0; i<massiv.size(); i++){
            System.out.println(massiv.get(i));
        }

    }

    public static void Sozdat(){
        while (flag1){
            System.out.println("\nВыберите тип объекта для создания: ");
            System.out.println("1.Песок: ");
            System.out.println("2.Цемент: ");
            System.out.println("3.Кирпич: ");
            System.out.println("4.Стекло: ");
            System.out.println("5.Вернуться в основное меню: ");
            switch (vvod.nextInt()) {
                case 1 -> {
                    System.out.println("Введите вес Песка в килограммах: ");
                    weight = vvod.nextDouble();
                    System.out.println("Введите степень радиокактивности (от 1 до 5): ");
                    radioactive = vvod.nextInt();
                    Pesok.add(new Pesok(weight, radioactive));
                    System.out.println("Объект успешно создан.\n");
                }
                case 2 -> {
                    System.out.println("Введите вес Цемента: ");
                    weight = vvod.nextDouble();
                    System.out.println("Введите Время высыхания в часах: ");
                    drying_time = vvod.nextInt();
                    System.out.println("Введите Плотность (м3): ");
                    density = vvod.nextDouble();
                    Cement.add(new Cement(drying_time, density, weight));
                    System.out.println("Объект успешно создан.\n");
                }
                case 3 -> {
                    System.out.println("Введите вес Кирпича в килограммах: ");
                    weight = vvod.nextDouble();
                    System.out.println("Введите Количество: ");
                    value = vvod.nextInt();
                    System.out.println("Введите Марку прочности (от 75М-300М): ");
                    streight = vvod.nextInt();
                    Brick.add(new brick(value, streight, weight));
                    System.out.println("Объект успешно создан.\n");
                }
                case 4 -> {
                    System.out.println("Введите вес Стекла в килограммах: ");
                    weight = vvod.nextDouble();
                    System.out.println("Введите Количество: ");
                    value = vvod.nextInt();
                    System.out.println("Введите хрупкость (см/м3): ");
                    fragility = vvod.nextDouble();
                    Glass.add(new glass(fragility, value, weight));
                    System.out.println("Объект успешно создан.\n");
                }
                case 5 -> flag1 = false;
                default -> System.out.println("Введенно неверное значение.");
            }

        }


    }
    public static void Change(){
        int Index;
        Wiwod();
        flag2 = true;
        while (flag2) {
            System.out.println("\nВыберите тип объекта для изменения: ");
            System.out.println("1.Песок: ");
            System.out.println("2.Цемент: ");
            System.out.println("3.Кирпич: ");
            System.out.println("4.Стекло: ");
            System.out.println("5.Вернуться в основное меню: ");
            switch (vvod.nextInt()) {
                case 1:
                    if (!Pesok.isEmpty()) {
                        for (int i = 0; i < Pesok.size(); i++) {
                            System.out.println(Pesok.get(i) + "");
                        }
                        System.out.println("\nВведите ID Элемента, который хотите изменить:");
                        IDChange = vvod.nextInt();
                        Index = -1;
                        for (int i = 0; i < Pesok.size(); i++) {
                            if(IDChange == Pesok.get(i).getId()){
                                Index = i;
                                break;
                            }
                        }
                        boolean iscorrectp = true;
                        if(Index != -1){
                            Pesok Pesokobj = Pesok.get(Index);
                            while (iscorrectp) {
                                System.out.println("Выберите параметр который хотите изменить.\n 1.Вес.\n 2.Степень Радиоактивности.\n 3.Вернуться назад.");
                                switch (vvod.nextInt()) {
                                    case 1 -> {
                                        System.out.println("Введите новый вес.");
                                        Pesokobj.setWeight(vvod.nextDouble());
                                    }
                                    case 2 -> {
                                        System.out.println("Введите новую Степень Радиокативности.");
                                        Pesokobj.setRadioactive(vvod.nextInt());
                                    }
                                    case 3 -> iscorrectp = false;
                                    default -> System.out.println("Введен неверный параметр.");
                                }
                                Pesok.set(Index, Pesokobj);
                            }
                        }
                        else System.out.println("Элемента с данным индексом не существует."); break;
                    }
                    else System.out.println("Объектов песок нет."); break;
                case 2:
                    if (!Cement.isEmpty()) {
                        for (int i = 0; i < Cement.size(); i++) {
                            System.out.println(Cement.get(i) + "");
                        }
                        System.out.println("\nВведите ID Элемента, который хотите изменить:");
                        IDChange = vvod.nextInt();
                        Index = -1;
                        for (int i = 0; i < Cement.size(); i++) {
                            if(IDChange == Cement.get(i).getId()){
                                Index = i;
                                break;
                            }
                        }
                        boolean iscorrectc = true;
                        if(Index != -1){
                            Cement Cemebtobj = Cement.get(Index);
                            while (iscorrectc) {
                                System.out.println("Выберите параметр который хотите изменить.\n 1.Вес.\n 2.Время засыхания.\n 3.Плотность\n 4.Вернуться назад.");
                                switch (vvod.nextInt()) {
                                    case 1:
                                        System.out.println("Введите новый вес.");
                                        Cemebtobj.setWeight(vvod.nextDouble());
                                        break;
                                    case 2:
                                        System.out.println("Введите новое время засыхания.");
                                        Cemebtobj.setDrying_time(vvod.nextInt());
                                        break;
                                    case 3:
                                        System.out.println("Введите новую плотность.");
                                        Cemebtobj.setDensity(vvod.nextDouble());
                                    case 4: iscorrectc = false; break;
                                    default:
                                        System.out.println("Введен неверный параметр.");
                                        break;
                                }
                                Cement.set(Index, Cemebtobj);
                            }
                        }
                        else System.out.println("Элемента с данным индексом не существует."); break;
                    }
                    else System.out.println("Объектов цемент нет."); break;
                case 3:
                    if (!Brick.isEmpty()) {
                        for (int i = 0; i < Brick.size(); i++) {
                            System.out.println(Brick.get(i) + "");
                        }
                        System.out.println("\nВведите ID Элемента, который хотите изменить:");
                        IDChange = vvod.nextInt();
                        Index = -1;
                        for (int i = 0; i < Brick.size(); i++) {
                            if(IDChange == Brick.get(i).getId()){
                                Index = i;
                                break;
                            }
                        }
                        boolean iscorrectc = true;
                        if(Index != -1){
                            brick Bricktobj = Brick.get(Index);
                            while (iscorrectc) {
                                System.out.println("Выберите параметр который хотите изменить.\n 1.Вес.\n 2.Прочность.\n 3.Количество\n 4.Вернуться назад.");
                                switch (vvod.nextInt()) {
                                    case 1:
                                        System.out.println("Введите новый вес.");
                                        Bricktobj.setWeight(vvod.nextDouble());
                                        break;
                                    case 2:
                                        System.out.println("Введите новую прочность.");
                                        Bricktobj.setStreight(vvod.nextInt());
                                        break;
                                    case 3:
                                        System.out.println("Введите новое количество.");
                                        Bricktobj.setValue(vvod.nextInt());
                                    case 4: iscorrectc = false; break;
                                    default:
                                        System.out.println("Введен неверный параметр.");
                                        break;
                                }
                                Brick.set(Index, Bricktobj);
                            }
                        }
                        else System.out.println("Элемента с данным индексом не существует."); break;
                    }
                    else System.out.println("Объектов кирпич нет."); break;
                case 4:
                    if (!Glass.isEmpty()) {
                        for (int i = 0; i < Glass.size(); i++) {
                            System.out.println(Glass.get(i) + "");
                        }
                        System.out.println("\nВведите ID Элемента, который хотите изменить:");
                        IDChange = vvod.nextInt();
                        Index = -1;
                        for (int i = 0; i < Glass.size(); i++) {
                            if(IDChange == Glass.get(i).getId()){
                                Index = i;
                                break;
                            }
                        }
                        boolean iscorrectc = true;
                        if(Index != -1){
                            glass Glassobj = Glass.get(Index);
                            while (iscorrectc) {
                                System.out.println("Выберите параметр который хотите изменить.\n 1.Вес.\n 2.Хрупкость.\n 3.Количество\n 4.Вернуться назад.");
                                switch (vvod.nextInt()) {
                                    case 1:
                                        System.out.println("Введите новый вес.");
                                        Glassobj.setWeight(vvod.nextDouble());
                                        break;
                                    case 2:
                                        System.out.println("Введите новую хрупкость.");
                                        Glassobj.setFragility(vvod.nextInt());
                                        break;
                                    case 3:
                                        System.out.println("Введите новое количество.");
                                        Glassobj.setValue(vvod.nextInt());
                                    case 4: iscorrectc = false; break;
                                    default:
                                        System.out.println("Введен неверный параметр.");
                                        break;
                                }
                                Glass.set(Index, Glassobj);
                            }
                        }
                        else System.out.println("Элемента с данным индексом не существует."); break;
                    }
                    else System.out.println("Объектов стекло нет."); break;
                case 5: flag2 = false; break;
                default:
                    System.out.println("Введенно неверное значение.");
                    break;
            }
        }

    }
    public static void Delete(){
        boolean FlagDelete = true, Udalil = false;
        Operations.Wiwod();
        System.out.println("Введите ID элемента который хотите удалить:");
        IdDelete = vvod.nextInt();
        while (FlagDelete){
            for (int i = 0; i < Pesok.size(); i++) {
                if (IdDelete == Pesok.get(i).getId()){
                    Pesok.remove(i);
                    FlagDelete = false;
                    Udalil = true;
                    break;
                }
            }
            for (int i = 0; i < Cement.size(); i++) {
                if (IdDelete == Cement.get(i).getId()){
                    Cement.remove(i);
                    FlagDelete = false;
                    Udalil = true;
                    break;
                }
            }
            for (int i = 0; i < Brick.size(); i++) {
                if (IdDelete == Brick.get(i).getId()){
                    Brick.remove(i);
                    FlagDelete = false;
                    Udalil = true;
                    break;
                }
            }
            for (int i = 0; i < Glass.size(); i++) {
                if (IdDelete == Glass.get(i).getId()){
                    Glass.remove(i);
                    FlagDelete = false;
                    Udalil = true;
                    break;
                }
            }
            if (!Udalil){
                System.out.println("Выбранного вами ID не существует.\n");
                FlagDelete = false;
            }
        }
        System.out.print("\nПосле удаления");
        Wiwod();
    }
    public static void Wiwod(){
        if((Pesok.isEmpty()) & (Cement.isEmpty()) & (Brick.isEmpty()) & (Glass.isEmpty())) System.out.println("\nБаза пустая");
        else {
            System.out.println("\nВ базе имеются:");
            if (!Pesok.isEmpty()) {
                for (Pesok pesok : Pesok) {
                    System.out.println(pesok + "");
                }
            }
            if (!Cement.isEmpty()) {
                for (Cement cement : Cement) {
                    System.out.println(cement + "");
                }
            }
            if (!Brick.isEmpty()) {
                for (brick brick : Brick) {
                    System.out.println(brick + "");
                }
            }
            if (!Glass.isEmpty()) {
                for (glass glass : Glass) {
                    System.out.println(glass + "");
                }
            }
            System.out.println();
        }
    }
}

