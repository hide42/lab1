package isu.stud.projectwork.controllers;



import isu.stud.projectwork.model.CryptoCurrency;
import isu.stud.projectwork.model.InfoCurrency;
import isu.stud.projectwork.parser.InitParser;
import isu.stud.projectwork.repository.CryptoCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller()
public class MainController {


    @Autowired
    CryptoCurrencyRepository cryptoCurrencyRepository;
    @Autowired
    InitParser parser;

    @PostConstruct
    public void runParser(){
        parser.initSymbols();
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("items", cryptoCurrencyRepository.findAll());
        return "index";
    }
    @RequestMapping("/info")
    public String info(@RequestParam("s") String s, Model model){
        CryptoCurrency currency = cryptoCurrencyRepository.findById(s).get();
        cryptoCurrencyRepository.updatePrices(currency);
        model.addAttribute("items", cryptoCurrencyRepository.findById(s).get().getPrices());
        return "info";
    }


}
