package qlhs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import qlhs.model.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Integer> {

}
