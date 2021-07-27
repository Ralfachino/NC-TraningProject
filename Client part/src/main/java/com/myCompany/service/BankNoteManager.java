package com.myCompany.service;

import com.myCompany.model.BankNote;

import java.util.List;

public class BankNoteManager {

    public static boolean checkAvailabilityBankNote(List<BankNote> bankNoteList, int money) {
        return checkBalanceANC(bankNoteList) >= money;
    }

    public static boolean removeBankNotes(int money) {
        return JsonParser.parsingRemoveBankNotes(money);
    }

    public static long checkBalanceANC(List<BankNote> bankNoteList) {
        int balanceANC = 0;
        for (BankNote bankNote : bankNoteList) {
            balanceANC += bankNote.getDenomination() * bankNote.getQuantity();
        }
        return balanceANC;
    }

}
