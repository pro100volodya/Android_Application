package com.chnulabs.androidApplication;

import java.util.ArrayList;
import java.util.Arrays;

public class Info {
    private int id;
    private String kindOfDish;
    private String nameOfRestaurant;
    private boolean cashExistsFlg;
    private boolean terminalExistsFlg;

    public Info(String kindOfDish, String nameOfRestaurant, boolean cashExistsFlg, boolean terminalExistsFlg) {
        this.kindOfDish = kindOfDish;
        this.nameOfRestaurant = nameOfRestaurant;
        this.cashExistsFlg = cashExistsFlg;
        this.terminalExistsFlg = terminalExistsFlg;
    }

    public Info(int id, String kindOfDish, String nameOfRestaurant, boolean cashExistsFlg, boolean terminalExistsFlg) {
        this(kindOfDish, nameOfRestaurant, cashExistsFlg, terminalExistsFlg);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getKindOfDish() {
        return kindOfDish;
    }

    public void setKindOfDish(String kindOfDish) {
        this.kindOfDish = kindOfDish;
    }

    public String getNameOfRestaurant() {
        return nameOfRestaurant;
    }

    public void setNameOfRestaurant(String price) {
        this.nameOfRestaurant = nameOfRestaurant;
    }

    public boolean isCashExistsFlg() {
        return cashExistsFlg;
    }

    public void setCashExistsFlg(boolean cashExistsFlg) {
        this.cashExistsFlg = cashExistsFlg;
    }

    public boolean isTerminalExistsFlg() {
        return terminalExistsFlg;
    }

    public void setTerminalExistsFlg(boolean terminalExistsFlg) {
        this.terminalExistsFlg = terminalExistsFlg;
    }

    private static ArrayList<Info> information = new ArrayList<Info>(
            Arrays.asList(
                    new Info("Перші страви", "New York Street Pizza", true, false),
                    new Info("Гарячі страви", "Владам", false, true),
                    new Info("Салати", "Cream Soda", true, false),
                    new Info("Десерти", "Антрекот", true, false)
            )
    );

    public static ArrayList<Info> get_Info() {
        return information;
    }

    public static Info get_KindOfDish(String kindOfDish) {
        for (Info m : information) {
            if (m.getKindOfDish().equals(kindOfDish)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return kindOfDish;
    }

    public static void addDish(Info info) {
        information.add(info);
    }
}
