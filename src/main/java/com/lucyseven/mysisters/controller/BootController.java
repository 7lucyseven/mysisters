package com.lucyseven.mysisters.controller;

import com.lucyseven.mysisters.util.SoundDevise;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BootController {
    @PostMapping("/boot")
    public String boot(Model model) {
        SoundDevise soundDevise = new SoundDevise();
        model.addAttribute("l_device", soundDevise.getList());
        return "setting";
    }
}
