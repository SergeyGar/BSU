package com.example.web7;

public class Cake {
    private String dough = "";
    private String cream = "";
    private String topping = "";


    public String getDough() {
        return dough;
    }

    public void setDough(String dough) {
        this.dough = dough;
    }

    public String getCream() {
        return cream;
    }

    public void setCream(String cream) {
        this.cream = cream;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    @Override
    public String toString(){
        return "Dough: " + dough +
                "\nCream" + cream +
                "\nTopping" + topping;
    }
}
