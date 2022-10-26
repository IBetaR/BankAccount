//package com.ibetar.capsulachallenge.controller.template;
//
//import com.ibetar.capsulachallenge.controller.impl.BaseControllerImpl;
//import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
//import com.ibetar.capsulachallenge.service.impl.BankAccountServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//@RequestMapping(path = "api/v1/bank/accounts")
//public class TemplateController {
//
//    @Autowired
//    private final BankAccountServiceImpl bankAccountService;
//
//    public TemplateController(BankAccountServiceImpl bankAccountService) {
//        this.bankAccountService = bankAccountService;
//    }
//
//    @GetMapping("")
//    public String getAccountsList(Model model) throws Exception {
//        List<BankAccount> bankAccountList = bankAccountService.findAll();
//        model.addAttribute("bankAccountList", bankAccountList);
//        return "index";
//    }
//
//    @GetMapping("/new")
//    public String createAccount(Model model){
//        BankAccount bankAccount = new BankAccount();
//        model.addAttribute("bankAccount",bankAccount);
//        return "new_account";
//    }
//
//    @GetMapping("/account/{id}")
//    public String getAccount(){
//        return "get_account";
//    }
//}
