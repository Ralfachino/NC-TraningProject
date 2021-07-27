package com.myCompany.service;

public interface AdapterService {
    String getStartMenu();

    Boolean getCardMenu(String idCard, String pin);

    Boolean checkCardBalance(int money);

    Integer getCardBalance();

    Boolean withdrawMoney(int removeMoney);

    Boolean depositMoney(int addMoney);
}
