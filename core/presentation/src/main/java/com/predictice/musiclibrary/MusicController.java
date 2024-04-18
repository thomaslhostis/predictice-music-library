package com.predictice.musiclibrary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MusicController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
