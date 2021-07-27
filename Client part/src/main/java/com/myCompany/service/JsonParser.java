package com.myCompany.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myCompany.model.BankNote;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonParser {

    public static List<BankNote> parsing() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(JsonParser.getJsonString(), new TypeReference<List<BankNote>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean parsingRemoveBankNotes(int money) {

        final int maxBankNoteCounter = 40;
        int bankNoteCounter = 0;
        int equalizer = 6;

        try {
            File file = new File("anc-balance.json");

            ObjectMapper objectMapper = new ObjectMapper();
            List<BankNote> noteList = objectMapper.readValue(JsonParser.getJsonString(), new TypeReference<List<BankNote>>() {
            });

            while (money != 0) {
                equalizer--;
                for (BankNote bankNote : noteList) {

                    int quantity = bankNote.getQuantity();
                    int denomination = bankNote.getDenomination();

                    int quantityRemove = money / denomination;
                    int sumRemove = 0;

                    if (money >= denomination && equalizer > -1 && equalizer < quantity) {
                        if (quantityRemove > quantity - equalizer) {
                            int allowedToRemove = quantity - equalizer;
                            sumRemove = allowedToRemove * denomination;
                            bankNote.setQuantity(quantity - allowedToRemove);
                            bankNoteCounter += allowedToRemove;
                        } else {
                            sumRemove = quantityRemove * denomination;
                            bankNote.setQuantity(quantity - quantityRemove);
                            bankNoteCounter += quantityRemove;
                        }
                        money -= sumRemove;
                    }
                    if (bankNoteCounter > maxBankNoteCounter) {
                        return false;
                    }
                    if (money == 0) {
                        break;
                    }
                }
                if (equalizer == 0 && money != 0) {
                    return false;
                }
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(noteList.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String getJsonString() {
        File file = new File("anc-balance.json");
        try {
            Scanner scanner = new Scanner(file);
            String strJson = scanner.nextLine();
            scanner.close();
            return strJson;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}




