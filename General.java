package org.example;

public abstract class General {
    private String name; //Название
    private int id; //Id вводить не нужно
    private double weight; // Вес
    private int value; // Количество
    public static int k = 100; //Переменная для автоматического ID

    public String getName() {return name;}

    public int getId() {return id;}

    public double getWeight() {return weight;}

    public int getValue() {return value;}

    public void setName(String name) {this.name = name;}

    public void setId(int id) {this.id = id;}

    public void setWeight(double weight) {this.weight = weight;}

    public void setValue(int value) {this.value = value;}
}
class Pesok extends General {
    private int radioactive; //Радиоактивность

    public Pesok(double weight, int radioactive) {
        this.setName("Песок");
        this.setId(k);
        this.setWeight(weight);
        this.radioactive = radioactive;
        General.k += Math.random()*90 + 10;
    }

    public Pesok(double weight) {
        this.setName("Песок");
        this.setId(k);
        this.setWeight(weight);
        this.radioactive = 0;
        General.k += Math.random()*90 + 10;
    }
    public Pesok() {
        this.setName("Песок");
        this.setId(k);
        this.setWeight(0);
        this.radioactive = 0;
        General.k += Math.random()*90 + 10;
    }

    public void setRadioactive(int radioactive) {this.radioactive = radioactive;}

    public int getRadioactive() {return radioactive;}

    @Override
    public String toString(){
        String stroka = "";
        stroka = "Наименование: " + this.getName() + ";ID: " + this.getId() + ";Вес: " + this.getWeight() + ";Степень радиоактивности: " + this.getRadioactive();
        return stroka;
    }

}
class Cement extends General{
    private int drying_time; //Время высыхания
    private double density; // Плотность

    public Cement(int drying_time, double density, double weight) {
        this.setName("Цемент");
        this.setId(k);
        this.setWeight(weight);
        this.drying_time = drying_time;
        this.density = density;
        General.k += Math.random()*90 + 10;
    }
    public Cement() {
        this.setName("Цемент");
        this.setId(k);
        this.setWeight(0);
        this.drying_time = 0;
        this.density = 0;
        General.k += Math.random()*90 + 10;
    }

    @Override
    public String toString(){
        String stroka = "";
        stroka = "Наименование: " + this.getName() + ";ID: " + this.getId() + ";Вес: " + this.getWeight() + "кг;Время высыхания: " + this.getDrying_time() + " Ч;Плотность: " + this.getDensity() + " м3" ;
        return stroka;
    }

    public int getDrying_time() {return drying_time;}

    public double getDensity() {return density;}

    public void setDrying_time(int drying_time) {this.drying_time = drying_time;}

    public void setDensity(double density) {this.density = density;}
}
class brick extends General{
    private int streight; // Марка прочности

    public int getStreight() {return streight;}

    public void setStreight(int streight) {this.streight = streight;}

    public brick() {
        this.setName("Кирпич");
        this.setId(k);
        this.setWeight(0);
        this.setStreight(0);
        this.setValue(0);
        General.k += Math.random()*90 + 10;
    }

    public brick(int value, int streight, double weight) {
        this.setName("Кирпич");
        this.setId(k);
        this.setWeight(weight);
        this.setStreight(streight);
        this.setValue(value);
        General.k += Math.random()*90 + 10;
    }

    @Override
    public String toString(){
        String stroka = "";
        stroka = "Наименование: " + this.getName() + ";ID: " + this.getId() + ";Вес: " + this.getWeight() + "кг;Прочность: M" + getStreight() + ";Количество: " + getValue() + " штук";
        return stroka;
    }
}
class glass extends General{
    private double fragility; // Хрупкость

    public double getFragility() {return fragility;}

    public void setFragility(double fragility) {this.fragility = fragility;}

    public glass(double fragility, int value, double weight) {
        this.setName("Стекло");
        this.setId(k);
        this.setFragility(fragility);
        this.setWeight(weight);
        this.setValue(value);
        General.k += Math.random()*90 + 10;
    }
    public glass() {
        this.setName("Стекло");
        this.setId(k);
        this.setFragility(0);
        this.setWeight(0);
        this.setValue(0);
        General.k += Math.random()*90 + 10;
    }

    @Override
    public String toString(){
        String stroka = "";
        stroka = "Наименование: " + this.getName() + ";ID: " + this.getId() + ";Вес: " + this.getWeight() + "кг;Хрупкость: " + getFragility() + " см/м3;Количество: " + getValue() + " штук";
        return stroka;
    }
}
