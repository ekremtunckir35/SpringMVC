package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
@RestController //bu classta restfull serviceler gelistirilecek
@RequestMapping("/api/")
public class StudentRestController {


    private final IStudentService service;

    @Autowired//sadece 1 constructor var ise  @Autowired kullanımı opsiyoneldir.
    public StudentRestController(IStudentService service) {
        this.service = service;
    }

    //tum ogrencileri listeleme
    //request: http://localhost:8080/SpringMvc/api/all + GET
    @RequestMapping("/all")
    public List<Student> allStudents(){
        return service.listAllStudents();
    }
}
