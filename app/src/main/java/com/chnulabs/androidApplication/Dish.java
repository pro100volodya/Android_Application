package com.chnulabs.androidApplication;

import java.util.ArrayList;
import java.util.Arrays;

public class Dish {
    private String name;
    private String kindOfDish;
    private String portionWeightByGrams;

    public Dish(String name, String kindOfDish, String portionWeightByGrams) {
        this.name = name;
        this.kindOfDish = kindOfDish;
        this.portionWeightByGrams = portionWeightByGrams;
    }

    public String getName() {
        return name;
    }

    public String getKindOfDish() {
        return kindOfDish;
    }

    public String getPortionWeightByGrams() {
        return portionWeightByGrams;
    }

    private static ArrayList<Dish> dishes = new ArrayList<Dish>(
            Arrays.asList(
                    new Dish("Борщ", "Перші страви", "300 г"),
                    new Dish("Курячий бульйон", "Перші страви", "350 г"),
                    new Dish("Паста Болоньєзе", "Гарячі страви", "270 г"),
                    new Dish("Паста Карбонара", "Гарячі страви", "320 г"),
                    new Dish("Цезар", "Салати", "240 г"),
                    new Dish("Грецький", "Салати", "400 г"),
                    new Dish("Чізкейк", "Десерти", "150 г"),
                    new Dish("Наполеон", "Десерти", "120 г")
            )
    );

    public static ArrayList<Dish> getDishes(String kindOfDish) {
        ArrayList<Dish> stList = new ArrayList<>();
        for (Dish s : dishes)
            if (s.kindOfDish.equals(kindOfDish)) {
                stList.add(s);
            }
        return stList;
    }

    public static ArrayList<Dish> get_Dish() {
        return dishes;
    }

    @Override
    public String toString() {
        return name;
    }
}
