package com.lucyseven.mysisters.controller;

import com.lucyseven.mysisters.service.BootService;
import com.lucyseven.mysisters.util.SoundDevise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BootController {
    BootService bootService;



    @Autowired
    public BootController(BootService bootService){
        this.bootService = bootService;

    }

    @PostMapping("/boot")
    public String boot(Model model) {

        bootService.test();



        SoundDevise soundDevise = new SoundDevise();
        model.addAttribute("l_device", soundDevise.getList());
        return "setting";
    }
}
