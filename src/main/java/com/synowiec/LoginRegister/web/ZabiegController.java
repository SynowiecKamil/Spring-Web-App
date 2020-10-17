package com.synowiec.LoginRegister.web;

import com.synowiec.LoginRegister.model.Fizjoterapeuta;
import com.synowiec.LoginRegister.model.Pacjent;
import com.synowiec.LoginRegister.model.Zabieg;
import com.synowiec.LoginRegister.service.FizjoterapeutaService;
import com.synowiec.LoginRegister.service.PacjentService;
import com.synowiec.LoginRegister.service.ZabiegService;
import com.synowiec.LoginRegister.web.dto.FizjoRegistrationDto;
import com.synowiec.LoginRegister.web.dto.ZabiegDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/pacjent/zarejestruj2/{fId}")
public class ZabiegController {


    @Autowired
    private ZabiegService zabiegService;

    @Autowired
    private PacjentService pacjentService;

    @Autowired
    private FizjoterapeutaService fizjoterapeutaService;

    @ModelAttribute("zabieg")
    public ZabiegDto zabiegDto() {
        return new ZabiegDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "ZabiegZarejestruj";
    }

    @RequestMapping
    public String rejestrujZabieg(Authentication authentication, @PathVariable(required = true) long fId, @ModelAttribute("zabieg") @Valid ZabiegDto zabiegDto,
                                         BindingResult result){
        Pacjent getPacjent = pacjentService.findByEmail(authentication.getName());
        Fizjoterapeuta getFizjoterapeuta = fizjoterapeutaService.get(fId);
        zabiegService.save(zabiegDto, getPacjent, getFizjoterapeuta);

        return "PacjentDashboard";
    }


//    @RequestMapping("/pacjent/zarejestruj2/{id}")
//    public ModelAndView showEditPacjentPage(@PathVariable(name = "id") long id) {
//        ModelAndView mav = new ModelAndView("ZabiegZarejestruj");
//
//        return mav;
//    }
//
//    @RequestMapping(value = "/ZabiegZarejestruj", method = RequestMethod.POST)
//    public String editPacjent(@PathVariable(name = "id") long id,
//                              @ModelAttribute("pacjent")@RequestBody Pacjent pacjent,
//                              @ModelAttribute("fizjoterapeuta")@RequestBody Fizjoterapeuta fizjoterapeuta,
//                              @ModelAttribute("zabieg") @Valid ZabiegDto zabiegDto,
//                              Authentication authentication) {
//        fizjoterapeuta = fizjoterapeutaService.get(id);
//        pacjent = pacjentService.findByEmail(authentication.getName());
//        zabiegService.save(zabiegDto, pacjent, fizjoterapeuta);
//        return "PacjentDashboard";
//    }

}
