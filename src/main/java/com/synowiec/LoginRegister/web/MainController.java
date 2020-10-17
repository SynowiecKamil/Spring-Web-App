package com.synowiec.LoginRegister.web;

import com.synowiec.LoginRegister.model.Fizjoterapeuta;
import com.synowiec.LoginRegister.model.Pacjent;
import com.synowiec.LoginRegister.model.Zabieg;
import com.synowiec.LoginRegister.repository.FizjoterapeutaRepository;
import com.synowiec.LoginRegister.service.FizjoterapeutaService;
import com.synowiec.LoginRegister.service.PacjentService;
import com.synowiec.LoginRegister.service.ZabiegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    PacjentService pacjentService;
    @Autowired
    private ZabiegService zabiegService;

    @GetMapping("/index")
    public String root() {
        return "index";
    }

    @GetMapping("/pacjent/login")
    public String login(Model model) {
        return "PacjentLogin";
    }

    @GetMapping("/pacjent/dashboard")
    public String pacjentIndex() {
        return "PacjentDashboard";
    }

    @GetMapping("/pacjent/konto")
    public String getPacjent(Model model, Authentication authentication){
        Pacjent getPacjent = pacjentService.findByEmail(authentication.getName());
        model.addAttribute("getPacjent", getPacjent);
        return "KontoPacjent";
    }

    @RequestMapping("/pacjent/konto/edytuj/{id}")
    public ModelAndView showEditPacjentPage(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("PacjentEdytuj");
        Pacjent pacjent = pacjentService.get(id);
        mav.addObject("pacjent", pacjent);
        return mav;
    }

    @RequestMapping(value = "/PacjentEdytuj", method = RequestMethod.POST)
    public String editPacjent(@ModelAttribute("pacjent")@RequestBody Pacjent pacjent) {
        pacjentService.updatePacjent(pacjent);
        return "PacjentDashboard";
    }

    @GetMapping("/pacjent/wyszukaj")
    public String PacjentWyszukaj(Model model, @Param("keyword") String keyword){
        List<Fizjoterapeuta> listFizjoterapeuta = fizjoterapeutaService.listAll(keyword);
        model.addAttribute("listFizjoterapeuta", listFizjoterapeuta);
        model.addAttribute("keyword", keyword);
        return "PacjentWyszukaj";
    }
    @GetMapping("/pacjent/wizyty")
    public String PacjentWizyty(Model model, Authentication auth){
        List<Zabieg> listZabieg = zabiegService.listAll("pacjent", auth.getName());
        model.addAttribute("listZabieg", listZabieg);
        return "PacjentWizyty";
    }
    @RequestMapping("/pacjent/wizyty/{id}")
    public ModelAndView showZabiegPage(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("PacjentZabieg");
        Zabieg zabieg = zabiegService.get(id);
        mav.addObject("zabieg", zabieg);
        return mav;
    }

    @RequestMapping(value = "/ZabiegEdytuj", method = RequestMethod.POST)
    public String editZabieg(@ModelAttribute("zabieg")@RequestBody Zabieg zabieg) {
        zabiegService.updateZabieg(zabieg);
        return "PacjentWizyty";
    }
    @RequestMapping(value = "/PacjentZabiegUsun")
    public String deletePacjentZabieg(@ModelAttribute("zabieg")@RequestBody Zabieg zabieg) {
        zabiegService.deleteZabieg(zabieg);
        return "PacjentWizyty";
    }


    @GetMapping("/fizjoterapeuta/login")
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
    @GetMapping("/fizjoterapeuta/wizyty")
    public String FizjoterapeutaWizyty(Model model, Authentication auth){
        List<Zabieg> listZabieg = zabiegService.listAll("fizjoterapeuta",auth.getName());
        model.addAttribute("listZabieg", listZabieg);
        return "FizjoterapeutaWizyty";
    }
    @RequestMapping("/fizjoterapeuta/wizyty/{id}")
    public ModelAndView showZabiegFizjoPage(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("FizjoterapeutaZabieg");
        Zabieg zabieg = zabiegService.get(id);
        mav.addObject("zabieg", zabieg);
        return mav;
    }
    @RequestMapping(value = "/FizjoterapeutaZabiegUsun")
    public String deleteFizjoZabieg(@ModelAttribute("zabieg")@RequestBody Zabieg zabieg) {
        zabiegService.deleteZabieg(zabieg);
        return "FizjoterapeutaDashboard";
    }

}
