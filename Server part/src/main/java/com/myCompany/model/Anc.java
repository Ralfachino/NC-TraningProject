package com.myCompany.model;

public class Anc {
    private int idCard;
    private int pin;

    public Anc() {
    }

    public Anc(int idCard, int pin) {
        this.idCard = idCard;
        this.pin = pin;
    }

    public int getIdCard() {
        return idCard;
    }

    public int getPin() {
        return pin;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
