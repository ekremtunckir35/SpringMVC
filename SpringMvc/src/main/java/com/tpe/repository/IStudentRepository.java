package com.tpe.repository;

import com.tpe.domain.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository {

    List<Student>findAll();

    void saveOrUpdate(Student student);

    void delete(Student student);

    Optional<Student> findById(Long id);//NullPointerException almamak için
                                        //null yerine boş bir optional objesi döner

    //DİKKAT!Null çıkablir.
    //Id olmasa NullPointerException alırız
    //Optional sınıfı null olabilecek bir değeri sarmalar.Yani dikkat null olabilir




}
