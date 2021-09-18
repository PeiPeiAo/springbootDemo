package com.example.springdome.bean;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
public class Testtable {

  private long id;
  private String name;
  private long age;
  private String sex;
  private String memo;

}
