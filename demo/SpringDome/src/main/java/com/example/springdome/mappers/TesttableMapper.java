package com.example.springdome.mappers;

import com.example.springdome.bean.Testtable;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Select;

public interface TesttableMapper {
    @Select("select * from testtable")
    Page<Testtable> getTesttableList();
}
