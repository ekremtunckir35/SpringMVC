package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller//requestler bu classta karşılanacak ve ilgili metodlarla maplenecek
@RequestMapping("/students")//http:localhost:8080/SpringMvc/students/....
//class:tüm metodlar için geçerli olur
//method:sadece ilgili metodu requestle mapler
public class StudentController {

    @Autowired
    private IStudentService service;


    //NOT:controllerda metodlar geriye mav veya String data tipi döndürebilir.
    //http:localhost:8080/SpringMvc/students/hi + GET--okuma
    //http:localhost:8080/SpringMvc/students/hi + POST--kayıt
    //@RequestMapping("/students")



    @GetMapping("/hi")
    public ModelAndView sayHi(){
        //response u hazırlayacak
        ModelAndView mav=new ModelAndView();
        mav.addObject("message","Hi,");
        mav.addObject("messagebody","I'm a Student Management System");
        mav.setViewName("hi");
        return mav;
    }

    //view resolver : /WEB-INF/views/hi.jsp dosyasını bulur ve mav içindeki modelı
    //dosyaya bind eder ve clienta gönderir.

    //1-tüm öğrencileri listeleme:
    //http://localhost:8080/SpringMvc/students + GET

    @GetMapping
    public ModelAndView getStudents(){
        List<Student> allStudent=service.listAllStudents();
        ModelAndView mav=new ModelAndView();
        mav.addObject("studentList",allStudent);
        mav.setViewName("students");
        return mav;
    }


    //ogrenciyi kaydetme
    //request: http://localhost:8080/SpringMvc/students/new + GET
    //response: new.jsp form gostermek

    @GetMapping("/new")
    public String sendForm(@ModelAttribute("student") Student student){
        return "studentForm";
    }

//ModelAttribute anatasyonu view katmanı ile controller arasında modelın transferini sağlar.
//view katmanında formda girilen verileri controllera taşır.

//işlem sonunda: studentın firstname,lastname ve grade değerleri set edilmiş halde
//controller classında yer alır

       //2-b formun  icindeki ogrenciyi kaydetme
    //request: http://localhost:8080/SpringMvc/students/saveStudent + POST
    //response:  ogrenciyi tabloya kaydedecegiz ve Liste gonderecegiz

    @PostMapping("/saveStudent")
    public String addStudent(@Valid  @ModelAttribute Student student, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "studentForm";
        }

        service.addOrUpdateStudent(student);

        return "redirect:/students";//students urline yönlendirme yapar
                                    //http://localhost:8080/SpringMvc/students + GET

    }

        //3-ogrenciyi inceleyelim
    //request: http://localhost:8080/SpringMvc/students/update?id =3 + GET
    //response: update icin id si verilen ogrencinin bilgilerini formda goster
    //id si verilen ogrenciyi bulmamiz gerekiyor ...

        @GetMapping("/update")
        public ModelAndView sendFormUpdate(@RequestParam("id") Long identity){

        Student foundStudent=service.findStudentById(identity);

            ModelAndView mav=new ModelAndView();
            mav.addObject("student",foundStudent);
            mav.setViewName("studentForm");
            return mav;

//@RequestParam: request parametresi alır ve ilgili parametreyi ilgili metoda mapler
//http://localhost:8080/SpringMvc/students/update?id=3
        }





}