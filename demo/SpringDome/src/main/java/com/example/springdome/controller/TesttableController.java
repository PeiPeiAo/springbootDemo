package com.example.springdome.controller;

import com.example.springdome.bean.Testtable;
import com.example.springdome.mappers.TesttableMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesttableController {

    @Autowired
    TesttableMapper testtableMapper;

    @RequestMapping("/getList")
    public Page<Testtable> getTesttableList(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Page<Testtable> list = testtableMapper.getTesttableList();
        return list;
    }
}
