package com.myCompany.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankNote {
    private int quantity;
    private int denomination;

    @Override
    public String toString() {
        return "{\"quantity\":" +
                quantity +
                ",\"denomination\":" +
                denomination +
                "}";

    }
}
