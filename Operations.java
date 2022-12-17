package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import static javax.xml.bind.JAXBContext.newInstance;

public class Operations {
    static ArrayList<pesok> Pesok = new ArrayList<pesok>(); //Массив для хранения объектов "Песок"
    static ArrayList<cement> Cement = new ArrayList<cement>();//Массив для хранения объектов "Цемент"
    static ArrayList<brick> Brick = new ArrayList<brick>();//Массив для хранения объектов "Кирпич"
    static ArrayList<glass> Glass = new ArrayList<glass>();//Массив для хранения объектов "Стекло"

    static double weight, density, fragility;
    static int radioactive, drying_time, value, streight, IdDelete, IDChange;
    static boolean flag1 = true, flag2 = true;
    static Scanner vvod = new Scanner(System.in); //Сканер для ввода с клавиатуры.
    final static String filename = "myxml.xml"; //Название файла для XML.

    public static void showMenu(){ //Основное меню программы.
        boolean flag = true;
        while (flag){
            System.out.println("\nВыберите действие: ");
            System.out.println("1.Создать объект: ");
            System.out.println("2.Изменить характеристики объекта: ");
            System.out.println("3.Удалить объект: ");
            System.out.println("4.Вывести список объектов: ");
            System.out.println("5.Сохранить состояния объектов в XML файл:");
            System.out.println("6.Считать состояния объектов из XML файла:");
            System.out.println("7.Полностью очистить XML файл.");
            System.out.println("8.Завершить программу: ");
            switch (inputNumInt()) {
                case 1 -> {
                    Operations.flag1 = true;
                    Sozdat();
                }
                case 2 -> {
                    Operations.flag2 = true;
                    Change();
                }
                case 3 -> {
                    Delete();
                }
                case 4 -> {
                    boolean flagforwiwod = true;
                    while (flagforwiwod){
                        System.out.println("\nВыберите действие: ");
                        System.out.println("1.Вывести все объекты песка: ");
                        System.out.println("2.Вывести все объекты цемента: ");
                        System.out.println("3.Вывести все объекты кирпича: ");
                        System.out.println("4.Вывести все объекты стекла: ");
                        System.out.println("5.Вывести всю базу данных:");
                        System.out.println("6.Вернуться назад:");
                        switch (inputNumInt()){
                            case 1: wiwodpesok(); break;
                            case 2:wiwodcement();break;
                            case 3:wiwodbrick();break;
                            case 4:wiwodglass();break;
                            case 5:WiwodAll();break;
                            case 6: flagforwiwod = false;break;
                            default:
                                System.out.println("Введено неверное значение.");
                                break;
                        }
                    }
                }
                case 5 -> {
                    Operations.ConvertObjecttoXML();
                }
                case 6 -> {
                    Operations.ConvertXmltoObject();
                }
                case 7 -> clearXmlfile();
                case 8 -> flag = false;
                default -> {
                    System.out.println("Введено неверное значение:");
                }
            }
        }
    }

    public static void ConvertObjecttoXML(){ //Функция для сохранения объектов в XML.
        ForXML perevodtoxml = new ForXML();
        if(!Pesok.isEmpty()){
            for (int i=0; i< Pesok.size(); i++){
                perevodtoxml.spisok.add(Pesok.get(i));
            }
        }
        if(!Cement.isEmpty()){
            for (int i=0; i< Cement.size(); i++){
                perevodtoxml.spisok.add(Cement.get(i));
            }
        }
        if(!Brick.isEmpty()){
            for (int i=0; i< Brick.size(); i++){
                perevodtoxml.spisok.add(Brick.get(i));
            }
        }
        if(!Glass.isEmpty()){
            for (int i=0; i< Glass.size(); i++){
                perevodtoxml.spisok.add(Glass.get(i));
            }
        }
        if (perevodtoxml.spisok.isEmpty()){
            System.out.println("База данных пустая, чтобы сохранить объекты в XML, создайте их.");
        }
        else {
            try { //Данный try catch добавляет все в XML файл.
                JAXBContext context = newInstance(General.class, ForXML.class, pesok.class, cement.class, brick.class, glass.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(perevodtoxml, new File(filename));
                System.out.println("Состояния объектов успешно сохранено в Xml файл.");
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }
    public static void ConvertXmltoObject() {//Функция для считывания объектов с XML.
        ForXML vseobjects;
        try {//Данный try catch считывает все из XML файла.
            JAXBContext dada = JAXBContext.newInstance(General.class, ForXML.class, pesok.class, cement.class, brick.class, glass.class);
            Unmarshaller un = dada.createUnmarshaller();
            vseobjects = (ForXML) un.unmarshal(new File(filename));
            System.out.println("Данные из XML файла успешно считаны.");
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        //Дальше идет добавление объектов в специальные Arraylistы.
        for (Object obj : vseobjects.spisok) {
            if (obj instanceof pesok) {
                Pesok.add((pesok) obj);
            }
            if (obj instanceof cement) {
                Cement.add((cement) obj);
            }
            if (obj instanceof brick) {
                Brick.add((brick) obj);
            }
            if (obj instanceof glass) {
                Glass.add((glass) obj);
            }
        }
    }
    public static void clearXmlfile(){ //Функция для очистки XML файла.
        boolean flagclear = true;
        String danet = "";
        ForXML clearXml = new ForXML();
        System.out.println("\nВы уверены что хотите очистить XML файл?");
        System.out.println("Введите Да/Нет.");
        danet = vvod.nextLine();
        while (flagclear){
            danet = vvod.nextLine();
            if (danet.toLowerCase().equals("да")){
                try { //Данный try catch делает пустым XML файл.
                    JAXBContext context = newInstance(General.class, ForXML.class, pesok.class, cement.class, brick.class, glass.class);
                    Marshaller marshaller = context.createMarshaller();
                    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    marshaller.marshal(clearXml, new File(filename));
                    System.out.println("Xml файл успешно очищен.");
                    flagclear = false;
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }
            else if(danet.toLowerCase().equals("нет")){
                flagclear = false;
            }
            if (flagclear) {
              System.out.println("Вы ввели неверно. Введите Да/Нет.");
           }
        }
    }

    public static void Sozdat(){ //Функция создания объектов, она сразу добавляет их в нужный Arraylist.
        while (flag1){
            System.out.println("\nВыберите тип объекта для создания: ");
            System.out.println("1.Песок: ");
            System.out.println("2.Цемент: ");
            System.out.println("3.Кирпич: ");
            System.out.println("4.Стекло: ");
            System.out.println("5.Вернуться в основное меню: ");
            switch (inputNumInt()) {
                case 1 -> {
                    System.out.println("Введите вес Песка в килограммах: ");
                    weight = inputNumDouble();
                    System.out.println("Введите степень радиокактивности (от 1 до 5): ");
                    boolean flagvvod = true;
                    while (flagvvod){//Проверка промежутка ввода данных
                        try {
                            radioactive = inputNumInt();
                            if ( 1 <= radioactive && radioactive <= 5) {
                                    flagvvod = false;
                            }
                            else
                                throw new IllegalArgumentException("Введёна неправильная степень радиоактивности. ");

                        }
                        catch (IllegalArgumentException e){
                            System.out.println("Введёна неправильная степень радиоактивности.");
                            System.out.println("Введите степень радиоактивности (от 1 до 5)");
                        }
                    }
                    Pesok.add(new pesok(weight, radioactive));
                    System.out.println("Объект успешно создан.\n");
                }
                case 2 -> {
                    System.out.println("Введите вес Цемента: ");
                    weight = inputNumDouble();
                    System.out.println("Введите Время высыхания в часах: ");
                    drying_time = inputNumInt();
                    System.out.println("Введите Плотность (м3): ");
                    density = inputNumDouble();
                    Cement.add(new cement(drying_time, density, weight));
                    System.out.println("Объект успешно создан.\n");
                }
                case 3 -> {
                    System.out.println("Введите вес Кирпича в килограммах: ");
                    weight = inputNumDouble();
                    System.out.println("Введите Количество: ");
                    value = inputNumInt();
                    System.out.println("Введите Марку прочности (от 75М-300М): ");
                    boolean flagvvod = true;
                    while (flagvvod) {//Проверка промежутка ввода данных
                        try {
                            streight = inputNumInt();
                            if (75 <= streight && streight <= 300) {
                                flagvvod = false;
                            } else
                                throw new IllegalArgumentException("Введёна неправильная Марка прочности. ");

                        } catch (IllegalArgumentException e) {
                            System.out.println("Введёна неправильная Марка прочности.");
                            System.out.println("Введите Марку прочности (от 75М-300М)");
                        }
                    }
                    Brick.add(new brick(value, streight, weight));
                    System.out.println("Объект успешно создан.\n");
                }
                case 4 -> {
                    System.out.println("Введите вес Стекла в килограммах: ");
                    weight = inputNumDouble();
                    System.out.println("Введите Количество: ");
                    value = inputNumInt();
                    System.out.println("Введите хрупкость (см/м3): ");
                    fragility = inputNumDouble();
                    Glass.add(new glass(fragility, value, weight));
                    System.out.println("Объект успешно создан.\n");
                }
                case 5 -> flag1 = false;
                default -> System.out.println("Введенно неверное значение.");
            }

        }
    }
    public static int inputNumInt() { //Функция того что бы пользователь мог ввести только int.
        int numInt;
        try {
            numInt = vvod.nextInt();
            if (vvod.hasNextLine()) {
                vvod.skip("");
            }
        } catch (InputMismatchException e) {
            vvod.nextLine();
            System.out.println("!!! Вы ввели не число !!!");
            System.out.println("Введите заново: ");
            numInt = inputNumInt();
        }
        return numInt;
    }
    public static double inputNumDouble() { //Функция того что бы пользователь мог ввести только double.
        double numDouble;
        try {
            if (vvod.hasNextDouble()) {
                numDouble = vvod.nextDouble();
                vvod.nextLine();
            } else throw new InputMismatchException("!!! Вы ввели не число: !!!");
        } catch (InputMismatchException e) {
            vvod.nextLine();
            System.out.println("!!! Вы ввели не число !!!");
            System.out.println("Введите заново: ");
            vvod.nextLine();
            numDouble = inputNumDouble();
        }
        return numDouble;
    }
    public static void Change(){ //Функция для изменения объектов.
        int Index;
        WiwodAll();
        flag2 = true;
        while (flag2) {
            System.out.println("\nВыберите тип объекта для изменения: ");
            System.out.println("1.Песок: ");
            System.out.println("2.Цемент: ");
            System.out.println("3.Кирпич: ");
            System.out.println("4.Стекло: ");
            System.out.println("5.Вернуться в основное меню: ");
            switch (inputNumInt()) {
                case 1:
                    if (!Pesok.isEmpty()) {
                        wiwodpesok();
                        System.out.println("\nВведите ID Элемента, который хотите изменить:");
                        IDChange = inputNumInt();
                        Index = -1;
                        for (int i = 0; i < Pesok.size(); i++) {
                            if(IDChange == Pesok.get(i).getId()){
                                Index = i;
                                break;
                            }
                        }
                        boolean iscorrectp = true;
                        if(Index != -1){
                            pesok Pesokobj = Pesok.get(Index);
                            while (iscorrectp) {
                                System.out.println("Выберите параметр который хотите изменить.\n 1.Вес.\n 2.Степень Радиоактивности.\n 3.Вернуться назад.");
                                switch (inputNumInt()) {
                                    case 1 -> {
                                        System.out.println("Введите новый вес.");
                                        Pesokobj.setWeight(inputNumDouble());
                                    }
                                    case 2 -> {
                                        System.out.println("Введите новую степень радиоактивности (от 1 до 5)");
                                        boolean flagchange = true;
                                        while (flagchange){//Проверка промежутка ввода данных
                                            try {
                                                radioactive = inputNumInt();
                                                if ( 1 <= radioactive && radioactive <= 5) {
                                                    flagchange = false;
                                                }
                                                else
                                                    throw new IllegalArgumentException("Введёна неправильная степень радиоактивности. ");

                                            }
                                            catch (IllegalArgumentException e){
                                                System.out.println("Введёна неправильная степень радиоактивности.");
                                                System.out.println("Введите степень радиоактивности (от 1 до 5)");
                                            }
                                        }
                                        Pesokobj.setRadioactive(radioactive);
                                    }
                                    case 3 -> iscorrectp = false;
                                    default -> System.out.println("Введен неверный параметр.");
                                }
                            }
                            Pesok.set(Index, Pesokobj);
                            System.out.println("Параметры объекта успешно изменены.");
                            System.out.println(Pesokobj + "\n");
                        }
                        else System.out.println("Элемента с данным индексом не существует."); break;
                    }
                    else System.out.println("Объектов 'Песок' в базе нет."); break;
                case 2:
                    if (!Cement.isEmpty()) {
                        wiwodcement();
                        System.out.println("\nВведите ID Элемента, который хотите изменить:");
                        IDChange = inputNumInt();
                        Index = -1;
                        for (int i = 0; i < Cement.size(); i++) {
                            if(IDChange == Cement.get(i).getId()){
                                Index = i;
                                break;
                            }
                        }
                        boolean iscorrectc = true;
                        if(Index != -1){
                            cement Cemebtobj = Cement.get(Index);
                            while (iscorrectc) {
                                System.out.println("Выберите параметр который хотите изменить.\n 1.Вес.\n 2.Время засыхания.\n 3.Плотность\n 4.Вернуться назад.");
                                switch (inputNumInt()) {
                                    case 1:
                                        System.out.println("Введите новый вес.");
                                        Cemebtobj.setWeight(inputNumDouble());
                                        break;
                                    case 2:
                                        System.out.println("Введите новое время засыхания.");
                                        Cemebtobj.setDrying_time(inputNumInt());
                                        break;
                                    case 3:
                                        System.out.println("Введите новую плотность.");
                                        Cemebtobj.setDensity(inputNumDouble());
                                    case 4: iscorrectc = false; break;
                                    default:
                                        System.out.println("Введен неверный параметр.");
                                        break;
                                }
                            }
                            Cement.set(Index, Cemebtobj);
                            System.out.println("Параметры объекта успешно изменены.");
                            System.out.println(Cemebtobj + "\n");
                        }
                        else System.out.println("Элемента с данным индексом не существует."); break;
                    }
                    else System.out.println("Объектов 'Цемент' в базе нет."); break;
                case 3:
                    if (!Brick.isEmpty()) {
                        wiwodbrick();
                        System.out.println("\nВведите ID Элемента, который хотите изменить:");
                        IDChange = inputNumInt();
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
                                switch (inputNumInt()) {
                                    case 1:
                                        System.out.println("Введите новый вес.");
                                        Bricktobj.setWeight(inputNumDouble());
                                        break;
                                    case 2:
                                        System.out.println("Введите новую Марку прочности (от 75М до 300М)");
                                        boolean flagchange = true;
                                        while (flagchange){//Проверка промежутка ввода данных
                                            try {
                                                streight = inputNumInt();
                                                if ( 75 <= streight && streight <= 300) {
                                                    flagchange = false;
                                                }
                                                else
                                                    throw new IllegalArgumentException("Введёна неправильная Марка прочности. ");

                                            }
                                            catch (IllegalArgumentException e){
                                                System.out.println("Введёна неправильная Марка прочности.");
                                                System.out.println("Введите Марку прочности (от 75М до 300М)");
                                            }
                                        }
                                        Bricktobj.setStreight(streight);
                                        break;
                                    case 3:
                                        System.out.println("Введите новое количество.");
                                        Bricktobj.setValue(inputNumInt());
                                    case 4: iscorrectc = false; break;
                                    default:
                                        System.out.println("Введен неверный параметр.");
                                        break;
                                }
                            }
                            Brick.set(Index, Bricktobj);
                            System.out.println("Параметры объекта успешно изменены.");
                            System.out.println(Bricktobj + "\n");
                        }
                        else System.out.println("Элемента с данным индексом не существует."); break;
                    }
                    else System.out.println("Объектов 'Кирпич' в базе нет."); break;
                case 4:
                    if (!Glass.isEmpty()) {
                        wiwodglass();
                        System.out.println("\nВведите ID Элемента, который хотите изменить:");
                        IDChange = inputNumInt();
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
                                switch (inputNumInt()) {
                                    case 1:
                                        System.out.println("Введите новый вес.");
                                        Glassobj.setWeight(inputNumDouble());
                                        break;
                                    case 2:
                                        System.out.println("Введите новую хрупкость.");
                                        Glassobj.setFragility(inputNumInt());
                                        break;
                                    case 3:
                                        System.out.println("Введите новое количество.");
                                        Glassobj.setValue(inputNumInt());
                                    case 4: iscorrectc = false; break;
                                    default:
                                        System.out.println("Введен неверный параметр.");
                                        break;
                                }
                            }
                            Glass.set(Index, Glassobj);
                            System.out.println("Параметры объекта успешно изменены.");
                            System.out.println(Glassobj + "\n");
                        }
                        else System.out.println("Элемента с данным индексом не существует."); break;
                    }
                    else System.out.println("Объектов 'Стекло' в базе нет."); break;
                case 5: flag2 = false; break;
                default:
                    System.out.println("Введенно неверное значение.");
                    break;
            }
        }
    }
    public static void Delete(){ //Функция удаления объектов.
        boolean FlagDelete = true, Udalil = false;
        Operations.WiwodAll();
        System.out.println("Введите ID элемента который хотите удалить:");
        IdDelete = inputNumInt();
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
        WiwodAll();
    }
    public static void wiwodpesok(){//Функция для вывода объектов "Песок".
        if (!Pesok.isEmpty()) {
            System.out.println("Все объекты 'Песок'.");
            for (pesok pesok : Pesok) {
                System.out.println(pesok + "");
            }
            System.out.println();
        }
        else System.out.println("--Объектов 'Песок' в базе нет.--\n");
    }
    public static void wiwodcement(){//Функция для вывода объектов "Цемент".
        if (!Cement.isEmpty()) {
            System.out.println("Все объекты 'Цемент'.");
            for (cement cement : Cement) {
                System.out.println(cement + "");
            }
            System.out.println();
        }
        else System.out.println("--Объектов 'Цемент' в базе нет.--\n");
    }
    public static void wiwodbrick(){//Функция для вывода объектов "Кирпич".
        if (!Brick.isEmpty()) {
            System.out.println("Все объекты 'Кирпич'.");
            for (brick brick : Brick) {
                System.out.println(brick + "");
            }
            System.out.println();
        }
        else System.out.println("--Объектов 'Кирпич' в базе нет.--\n");
    }
    public static void wiwodglass(){//Функция для вывода объектов "Стекло".
        if (!Glass.isEmpty()) {
            System.out.println("Все объекты 'Стекло'.");
            for (glass glass : Glass) {
                System.out.println(glass + "");
            }
            System.out.println();
        }
        else System.out.println("--Объектов 'Стекло' в базе нет.--\n");

    }
    public static void WiwodAll(){
        if((Pesok.isEmpty()) & (Cement.isEmpty()) & (Brick.isEmpty()) & (Glass.isEmpty())) System.out.println("\nБаза пустая");
        else {
            System.out.println("\nВ базе имеются:\n");
            wiwodpesok();
            wiwodcement();
            wiwodbrick();
            wiwodglass();
            System.out.println();
        }
    }
}

