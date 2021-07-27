package com.myCompany.service;

import com.myCompany.entity.ClientAccount;
import com.myCompany.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Date;
import java.util.List;

@Service
public class ClientAccountService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<ClientAccount> retrieveByName(String name) {
        return employeeRepository.retrieveByName(name);
    }

    public int retrieveByCardNumber(int id_card, int pin) throws ConnectException {
        return employeeRepository.retrieveByCardNumber(id_card, pin);
    }

    public void depositMoney(int id_card, int addMoney) {
        employeeRepository.depositMoney(id_card, addMoney);
    }

    public int checkBalance(int id_card) {
        return employeeRepository.checkBalance(id_card);
    }

    public int getBalance(int id_card) {
        return employeeRepository.checkBalance(id_card);
    }

    public void withdrawMoney(int id_card, int removeMoney) {
        employeeRepository.withdrawMoney(id_card, removeMoney);
    }

    public void logging(int id_card, String operationType, int money) {
        Date date = new Date();
        int balanceAfterOperation = getBalance(id_card);
        int balanceBeforeOperation = 0;
        if (operationType.equals("Withdraw money")) {
            balanceBeforeOperation = getBalance(id_card) + money;
        } else if (operationType.equals("Deposit money")) {
            balanceBeforeOperation = getBalance(id_card) - money;
        }
        employeeRepository.logging(id_card, operationType, money, date, balanceBeforeOperation, balanceAfterOperation);
    }

    public String getEmail(int id_card) {
        return employeeRepository.getEmail(id_card);
    }
}
