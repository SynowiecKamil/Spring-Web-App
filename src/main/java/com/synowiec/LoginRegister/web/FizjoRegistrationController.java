package com.synowiec.LoginRegister.web;

import com.synowiec.LoginRegister.model.Fizjoterapeuta;
import com.synowiec.LoginRegister.service.FizjoterapeutaService;
import com.synowiec.LoginRegister.web.dto.FizjoRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/FizjoterapeutaRegister")
public class FizjoRegistrationController {

    @Autowired
    private FizjoterapeutaService fizjoService;

    @ModelAttribute("fizjo")
    public FizjoRegistrationDto fizjoRegistrationDto() {
        return new FizjoRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "FizjoterapeutaRegister";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("fizjo") @Valid FizjoRegistrationDto fizjoDto,
                                      BindingResult result){

        Fizjoterapeuta existing = fizjoService.findByEmail(fizjoDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "FizjoterapeutaRegister";
        }

        fizjoService.saveFizjo(fizjoDto);
        return "redirect:/FizjoterapeutaRegister?success";
    }

}
