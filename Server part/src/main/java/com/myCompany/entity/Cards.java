package com.myCompany.entity;

import javax.persistence.*;

@Entity
@Table(name = "cards")
public class Cards {

    @Id
    @Column(name = "id_card")
    private int idCard;

    @Column(name = "id_account")
    private int accountID;

    private int pin;

    public Cards() {
    }

    public Cards(int accountID, int idCard, int pin) {
        this.accountID = accountID;
        this.idCard = idCard;
        this.pin = pin;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}

