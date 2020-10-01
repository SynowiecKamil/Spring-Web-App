package com.synowiec.LoginRegister.web;

import com.synowiec.LoginRegister.model.Fizjoterapeuta;
import com.synowiec.LoginRegister.model.User;
import com.synowiec.LoginRegister.repository.FizjoterapeutaRepository;
import com.synowiec.LoginRegister.service.FizjoterapeutaService;
import com.synowiec.LoginRegister.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    FizjoterapeutaService fizjoterapeutaService;
    @Autowired
    UserService pacjentService;

    @GetMapping("/index")
    public String root() {
        return "index";
    }

    @GetMapping("/pacjent/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/pacjent/dashboard")
    public String userIndex() {
        return "PacjentDashboard";
    }

    @GetMapping("/pacjent/konto")
    public String getPacjent(Model model, Authentication authentication){
        User getPacjent = pacjentService.findByEmail(authentication.getName());
        model.addAttribute("getPacjent", getPacjent);
        return "KontoPacjent";
    }

    @GetMapping("/pacjent/wyszukaj")
    public String PacjentWyszukaj(Model model){
        List<Fizjoterapeuta> listFizjoterapeuta = fizjoterapeutaService.listAll();
        model.addAttribute("listFizjoterapeuta", listFizjoterapeuta);
        return "PacjentWyszukaj";
    }

    @GetMapping("/fizjoterapeuta/FizjoterapeutaLogin")
    public String FizjoterapeutaLogin(Model model) {
        return "FizjoterapeutaLogin";
    }

    @GetMapping("/fizjoterapeuta/dashboard")
    public String FizjoterapeutaDashboard() {
        return "FizjoterapeutaDashboard";
    }

    @GetMapping("/fizjoterapeuta/konto")
    public String getFizjoterapeuta(Model model, Authentication authentication){
        Fizjoterapeuta getFizjoterapeuta = fizjoterapeutaService.findByEmail(authentication.getName());
        model.addAttribute("getFizjoterapeuta", getFizjoterapeuta);
        return "KontoFizjoterapeuta";
    }

    @RequestMapping("/fizjoterapeuta/konto/edytuj/{id}")
    public ModelAndView showEditFizjoterapeutaPage(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("FizjoterapeutaEdytuj");
        Fizjoterapeuta fizjoterapeuta = fizjoterapeutaService.get(id);
        mav.addObject("fizjoterapeuta", fizjoterapeuta);

        return mav;
    }
    @RequestMapping(value = "/FizjoterapeutaEdytuj", method = RequestMethod.POST)
    public String editFizjoterapeuta(@ModelAttribute("fizjoterapeuta")@RequestBody Fizjoterapeuta fizjoterapeuta) {
        fizjoterapeutaService.updateFizjoterapeuta(fizjoterapeuta);

        return "FizjoterapeutaDashboard";
    }



}
