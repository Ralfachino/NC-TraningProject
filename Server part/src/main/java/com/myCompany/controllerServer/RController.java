package com.myCompany.controllerServer;


import com.myCompany.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RController {

    @Autowired
    private BankService bankService;

    @PostMapping("/anc")
    public boolean checkCard(@RequestParam("idCard") String idCard, @RequestParam("pin") String pin/*, @RequestBody Response response*/) {
        return bankService.cardAvailable(idCard, pin);
    }

    @PostMapping("/check-balance")
    public boolean checkBalance(@RequestParam("money") int money) {
        return bankService.balanceAvailable(money);
    }

    @PostMapping("/get-balance")
    public int getBalance() {
        return bankService.getAvailableBalance();
    }

    @PostMapping("/withdraw-money")
    public boolean withdrawMoney(@RequestParam("removeMoney") int removeMoney) {
        return bankService.isWithdrawMoney(removeMoney);
    }

    @PostMapping("/deposit-money")
    public boolean depositMoney(@RequestParam("addMoney") int addMoney) {
        return bankService.isDepositMoney(addMoney);
    }
}

