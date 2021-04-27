package com.lxd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : Lxd
 * @date : 17:40 2021/4/13
 */
@Controller
public class MusicController {
    @GetMapping("/music")
    public String music(){
        return "music";
    }
}
