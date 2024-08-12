package com.example.capstone1.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api/test")
@RestController
public class TestRestController {

    //List<String> list_title = new ArrayList<String>();

    @GetMapping("/abc")
    public Map<String, Object> abc(@RequestParam Map<String, Object> param) {
        System.out.println(param);
        /*list_title.add(param.get("title") + "");
        Map<String, Object> returnValue = new HashMap<>();
        returnValue.put("result", "200");
        returnValue.put("size", list_title.size());
        returnValue.put("list_title", list_title.toArray());*/



        return null;
    }
    @GetMapping("/def")
    public String def(@Valid String title) {
        System.out.println(title);
        return "ok";
    }

}