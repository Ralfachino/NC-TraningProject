package com.myCompany.controller;

import com.myCompany.service.ANCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ANCController {
    @Autowired
    private ANCService ANCService;

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }

    @PostMapping("/card-menu")
    public String getCardMenu(@RequestParam("idCard") String idCard, @RequestParam("pin") String pin, Model model) {
        return (ANCService.isAccessCard(idCard, pin, model));
    }

    @PostMapping("/get-balance")
    public String getCardBalance(Model model) {
        return ANCService.balanceCard(model);
    }

    @PostMapping("/withdraw-money")
    public String withdrawMoney(@RequestParam("money") int removeMoney, Model model) {
        return ANCService.isWithdrawMoney(removeMoney, model);
    }

    @PostMapping("/deposit-money")
    public String depositMoney(@RequestParam("money") int addMoney, Model model) {
        return ANCService.isDepositMoney(addMoney, model);
    }
}

