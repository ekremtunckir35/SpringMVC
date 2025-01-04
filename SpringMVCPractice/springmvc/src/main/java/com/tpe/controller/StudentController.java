package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller // http://127.0.0.1:8080/students
@RequestMapping("/students") // students endpointi ile gelen requestler için bu sınıfa bakılacağını belirtiyorum
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/hi") // students/hi
    public ModelAndView sayHi() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message","Merhaba");
        mav.addObject("messagebody","Ben bir Öğrenci Yönetim Sistemiyim");
        mav.setViewName("hi"); // hi.jsp
        return mav;
    }

    @GetMapping("/new")  //  students/new
    public String sendStudentForm(@ModelAttribute("student") Student student) {
        return "studentForm"; // @ModelAttribute = Model e student isminde bir attibute ekledik
    }

    @PostMapping("/saveStudent")  // students/saveStudent
    public String createStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping
    public ModelAndView getStudents(){
        List<Student> list = studentService.getAllStudent();
        ModelAndView mav = new ModelAndView();
        mav.addObject("students",list);
        mav.setViewName("students");
        return mav;
    }

    // http://localhost:8080/sipringmvc/students/update?id=1
    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("id") Long id, Model model) {
        Student student = studentService.findStudentById(id);
        model.addAttribute(student);
        return "studentForm"; // studentForm.jsp sayfasını client tarafına gönderiyorum
    }

    //  http://localhost:8080/sipringmvc/students/delete/1
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return "redirect:/students";
    }





}