package com.myCompany.service;

import com.myCompany.model.Anc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

@Service
public class BankService {
    @Autowired
    public ClientAccountService clientAccountService;
    @Autowired
    public JavaMailSender javaMailSender;

    private int id;

    private final static int MAX_BALANCE = 5000000;

    public boolean cardAvailable(String idCard, String pin) {
        Anc anc = new Anc(Integer.parseInt(idCard), Short.parseShort(pin));
        int cardId = (int) anc.getIdCard();
        id = cardId;
        int pinCode = anc.getPin();
        try {
            int findIdCard = clientAccountService.retrieveByCardNumber(cardId, pinCode);
            return findIdCard == cardId;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean balanceAvailable(int money) {
        int moneyAvailable = clientAccountService.checkBalance(id);
        return moneyAvailable >= money;
    }

    public int getAvailableBalance() {
        return clientAccountService.getBalance(id);
    }

    public boolean isWithdrawMoney(int removeMoney) {
        String typeOperation = "Withdraw money";
        try {
            clientAccountService.withdrawMoney(id, removeMoney);
            clientAccountService.logging(id, typeOperation, removeMoney);
        } catch (JpaSystemException e) {
            e.printStackTrace();
            return false;
        }
        try {
            sendResultOnEmail(clientAccountService.getEmail(id), typeOperation, removeMoney, clientAccountService.getBalance(id));
        } catch (MailException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean isDepositMoney(int addMoney) {
        String typeOperation = "Deposit money";
        try {
            if (clientAccountService.checkBalance(id) + addMoney > MAX_BALANCE) {
                return false;
            }
            clientAccountService.depositMoney(id, addMoney);
            clientAccountService.logging(id, typeOperation, addMoney);
        } catch (JpaSystemException e) {
            e.printStackTrace();
            return false;
        }
        try {
            sendResultOnEmail(clientAccountService.getEmail(id), typeOperation, addMoney, clientAccountService.getBalance(id));
        } catch (MailException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void sendResultOnEmail(String emailAddress, String typeOperation, int money, int balance) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("ralf.playing@gmail.com");
        message.setReplyTo(emailAddress);
        message.setTo(emailAddress);
        message.setSubject("Operation notification");
        message.setText("An operation has been made on your account: " + typeOperation + " " + money + "RUB" + "\n" + "Your balance is: " + balance);

        javaMailSender.send(message);
    }

}
