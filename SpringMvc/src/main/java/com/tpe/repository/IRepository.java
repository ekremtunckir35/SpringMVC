package com.tpe.repository;

import com.tpe.domain.Course;

import java.util.List;
import java.util.Optional;

public interface IRepository<T,ID>{

    List<T> findAll();

    void saveOrUpdate(T entity);

    void delete(T entity);

    Optional<T> findById(ID id);

    List<T> findAllByDuration(int duration);
}