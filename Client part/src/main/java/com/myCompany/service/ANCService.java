package com.myCompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ANCService {
    @Autowired
    private AdapterService adapterService;

    public String isAccessCard(String idCard, String pin, Model model) {
        if (adapterService.getCardMenu(idCard, pin)) {
            return "card-menu";
        } else {
            model.addAttribute("cardNF", "Card with number " + idCard + " not found or wrong PIN code");
            return "card-error";
        }
    }

    public String balanceCard(Model model) {
        int balance = adapterService.getCardBalance();
        model.addAttribute("operationInfo", "Your balance is " + balance + " RUB");
        return "result-operation";
    }

    public String isWithdrawMoney(int removeMoney, Model model) {
        if (BankNoteManager.checkAvailabilityBankNote(JsonParser.parsing(), removeMoney)) {
            if (adapterService.checkCardBalance(removeMoney)) {
                if (!BankNoteManager.removeBankNotes(removeMoney)) {
                    model.addAttribute("operationInfo", "Something went wrong, use a different ATM");
                    return "result-operation";

                }
                adapterService.withdrawMoney(removeMoney);
                model.addAttribute("operationInfo", "The operation was performed from your account has been withdrawn " + removeMoney + " RUB");
            } else {
                model.addAttribute("operationInfo", "Not enough money in the account");
            }
        } else {
            model.addAttribute("operationInfo", "There is not enough money in the ATM");
        }
        return "result-operation";
    }

    public String isDepositMoney(int addMoney, Model model) {
        if (adapterService.depositMoney(addMoney)) {
            model.addAttribute("operationInfo", "Funds were deposited in the amount of " + addMoney + " RUB");
        } else {
            model.addAttribute("operationInfo", "Your card has exceeded the limit of 5 000 000 RUB");
        }
        return "result-operation";
    }


}
