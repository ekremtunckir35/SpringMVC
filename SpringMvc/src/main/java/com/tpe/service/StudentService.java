package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.StudentNotFoundException;
import com.tpe.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service //bu kat is katmani,burada manupilasyon yapilir
public class StudentService implements IStudentService{

    @Autowired
    private IStudentRepository repository;

    //1c adimi
    @Override
    public List<Student> listAllStudents() {
        return  repository.findAll();
    }

     //2-c
    @Override
    public void addOrUpdateStudent(Student student) {
        repository.saveOrUpdate(student);

    }
  //3-b
    @Override
    public Student findStudentById(Long id) {
       Student student = repository.findById(id).
               orElseThrow(()->new StudentNotFoundException("Student not found by ID:"+id));//supplier:get

        //findById metodunun geriye döndürdüğü optional içinde
        //student varsa student değişkenine atar.
        //optional objesi boşsa orElseThrow custom exception fırlatılabilir.
        //get():NoSuchElementException fırlatır. boyle bir eleman bulunamadi anlaminda

//supplier interfaceini implemente eden bir class oluşturup , objesinin
//get metodunu kullanmak yerine kısaca lambda exp ile get metodunu override ediyoruz.

        return  student;
    }

    @Override
    public void deleteStudent(Long id) {

    }
}
