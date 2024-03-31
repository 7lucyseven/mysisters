package com.lucyseven.mysisters.controller;

import com.lucyseven.mysisters.util.SoundDevise;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SettingController {
    @GetMapping("/setting")
    public String setting(Model model) {
        SoundDevise soundDevise = new SoundDevise();
        model.addAttribute("l_device", soundDevise.getList());
        return "setting";
    }
}