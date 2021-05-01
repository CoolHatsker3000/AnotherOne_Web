package com.ngtu.work.dyploma.controllers;

import com.ngtu.work.dyploma.firebase.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/firsttest")
public class TestController {
    private List<String> stuff;
    @Autowired
    private TestData testData;

    @RequestMapping
    public String getTestList(Model model){
        stuff=testData.getStrings();
        model.addAttribute("stuff",stuff);
        return "testPage";

    }

}
