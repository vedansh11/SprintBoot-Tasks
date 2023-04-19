package com.example.furniture.controller;

import com.example.furniture.model.UserModel;
import com.example.furniture.service.UserService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class ModelController {

    @Autowired
    UserService userService;

    @GetMapping("getusers")
    public List<UserModel>getallUser(){
        List<UserModel>Users=userService.getallusers();
        return Users;
      //  return userService.getallusers();
    }

    @GetMapping("report/{format}")
    public ResponseEntity<InputStreamResource> generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return userService.exportReport(format);

    }

    @GetMapping("/barcode")
    public void CreateBarcode(){
        userService.createBarCode128("1");
    }

    @GetMapping("/")
    public String home(){
        return "Vedansh";
    }
}
