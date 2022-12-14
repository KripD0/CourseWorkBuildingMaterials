package org.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;


@XmlSeeAlso({pesok.class, cement.class, brick.class, glass.class})
public abstract class General {
    private String name; //Название
    private int id; //Id вводить не нужно
    private double weight; // Вес
    private int value; // Количество
    public static int k = 1000; //Переменная для автоматического ID
    @XmlElement
    public String getName() {return name;}
    @XmlElement
    public int getId() {return id;}
    @XmlElement
    public double getWeight() {return weight;}
    @XmlElement
    public int getValue() {return value;}
    public void setName(String name) {this.name = name;}

    public void setId(int id) {this.id = id;}

    public void setWeight(double weight) {this.weight = weight;}

    public void setValue(int value) {this.value = value;}
}
class pesok extends General {
    private int radioactive; //Радиоактивность

    public pesok(double weight, int radioactive) {
        this.setName("Песок");
        pesok.k += Math.random()*90 + 10;
        this.setId(k);
        this.setWeight(weight);
        this.radioactive = radioactive;
    }

    public pesok(double weight) {
        this.setName("Песок");
        pesok.k += Math.random()*90 + 10;
        this.setId(k);
        this.setWeight(weight);
        this.radioactive = 0;
    }
    public pesok() {
        this.setName("Песок");
        pesok.k += Math.random()*90 + 10;
        this.setId(k);
        this.setWeight(0);
        this.radioactive = 0;
    }

    public void setRadioactive(int radioactive) {this.radioactive = radioactive;}
    @XmlElement
    public int getRadioactive() {return radioactive;}

    @Override
    public String toString(){
        String stroka = "";
        stroka = "Наименование: " + this.getName() + ";ID: " + this.getId() + ";Вес: " + this.getWeight() + ";Степень радиоактивности: " + this.getRadioactive();
        return stroka;
    }

}
class cement extends General{
    static int k = 3000;
    private int drying_time; //Время высыхания
    private double density; // Плотность

    public cement(int drying_time, double density, double weight) {
        this.setName("Цемент");
        cement.k += Math.random()*90 + 10;
        this.setId(k);
        this.setWeight(weight);
        this.drying_time = drying_time;
        this.density = density;
    }
    public cement() {
        this.setName("Цемент");
        cement.k += Math.random()*90 + 10;
        this.setId(k);
        this.setWeight(0);
        this.drying_time = 0;
        this.density = 0;
    }


    @Override
    public String toString(){
        String stroka = "";
        stroka = "Наименование: " + this.getName() + ";ID: " + this.getId() + ";Вес: " + this.getWeight() + "кг;Время высыхания: " + this.getDrying_time() + " Часов;Плотность: " + this.getDensity() + " м3" ;
        return stroka;
    }
    @XmlElement
    public int getDrying_time() {return drying_time;}
    @XmlElement
    public double getDensity() {return density;}

    public void setDrying_time(int drying_time) {this.drying_time = drying_time;}

    public void setDensity(double density) {this.density = density;}
}
class brick extends General{
    static int k = 5000;
    private int streight; // Марка прочности
    @XmlElement
    public int getStreight() {return streight;}

    public void setStreight(int streight) {this.streight = streight;}

    public brick() {
        this.setName("Кирпич");
        brick.k += Math.random()*90 + 10;
        this.setId(k);
        this.setWeight(0);
        this.setStreight(0);
        this.setValue(0);
    }

    public brick(int value, int streight, double weight) {
        this.setName("Кирпич");
        brick.k += Math.random()*90 + 10;
        this.setId(k);
        this.setWeight(weight);
        this.setStreight(streight);
        this.setValue(value);
    }

    @Override
    public String toString(){
        String stroka = "";
        stroka = "Наименование: " + this.getName() + ";ID: " + this.getId() + ";Вес: " + this.getWeight() + "кг;Прочность: M" + getStreight() + ";Количество: " + getValue() + " штук";
        return stroka;
    }
}
class glass extends General{
    static int k = 8000;
    private double fragility; // Хрупкость
    @XmlElement
    public double getFragility() {return fragility;}

    public void setFragility(double fragility) {this.fragility = fragility;}

    public glass(double fragility, int value, double weight) {
        this.setName("Стекло");
        glass.k += Math.random()*90 + 10;
        this.setId(k);
        this.setFragility(fragility);
        this.setWeight(weight);
        this.setValue(value);
    }
    public glass() {
        this.setName("Стекло");
        glass.k += Math.random()*90 + 10;
        this.setId(k);
        this.setFragility(0);
        this.setWeight(0);
        this.setValue(0);
    }

    @Override
    public String toString(){
        String stroka = "";
        stroka = "Наименование: " + this.getName() + ";ID: " + this.getId() + ";Вес: " + this.getWeight() + "кг;Хрупкость: " + getFragility() + " см/м3;Количество: " + getValue() + " штук";
        return stroka;
    }
}
