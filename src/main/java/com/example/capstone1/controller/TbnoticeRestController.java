package com.example.capstone1.controller;

import com.example.capstone1.dto.DefaultDto;
import com.example.capstone1.util.FileUpload;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/test")
@RestController
public class TbnoticeRestController {
    private final TbnoticeRepository tbnoticeRepository;
    public TbnoticeRestController(TbnoticeRepository tbnoticeRepository) {
        this.tbnoticeRepository = tbnoticeRepository;
    }

    @GetMapping("/create")
    public Map<String, Object> create(@RequestParam Map<String, Object> param) {
        System.out.println(param);

        Tbnotice tbnotice = new Tbnotice();
        tbnotice.setId(param.get("id") + "");
        tbnotice.setTitle(param.get("title") + "");
        tbnotice.setContent(param.get("content") + "");

        tbnoticeRepository.save(tbnotice);

        Map<String, Object> returnValue = new HashMap<>();
        returnValue.put("result", "200");

        return returnValue;
    }

    @GetMapping("/list")
    public Map<String, Object> list() {

        List<Tbnotice> list = tbnoticeRepository.findAll();

        Map<String, Object> returnValue = new HashMap<>();
        returnValue.put("result", "200");
        returnValue.put("data", list);

        return returnValue;
    }

}
