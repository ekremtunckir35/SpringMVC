package com.tpe.repository;

import com.tpe.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // @Component 'ı da içeriyor, Sipring Framework e bu clasımın repo katmanı olduğunu söylemiş oluyorum
public class StudentRepositoryImpl implements StudentRepository{

    @Autowired// Db katmanında olduğum için SF bağımlılığımı enjekte ettim
    private SessionFactory sessionFactory;

    @Override
    public List<Student> getAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Student> listOfStudents =
                session.createQuery("FROM Student",Student.class).
                        getResultList();

        tx.commit();
        session.close();
        return listOfStudents;
    }

    @Override
    public Optional<Student> findById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Student student = session.get(Student.class,id);
        Optional<Student> opt;
        opt= Optional.ofNullable(student);// eğer dönen değer null ise ,
        // içi boş bir student objesi oluşturup döndürür

        tx.commit();
        session.close();
        return opt;

    }

    @Override
    public void save(Student student) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(student); // Db de bu obje varsa update et, yoksa create et

        tx.commit();
        session.close();

    }

    @Override
    public void update(Student student) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.update(student);

        tx.commit();
        session.close();

    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Student student= session.load(Student.class,id);
        session.delete(student);

        tx.commit();
        session.close();

    }
}