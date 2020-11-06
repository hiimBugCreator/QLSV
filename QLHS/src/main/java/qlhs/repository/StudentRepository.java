package qlhs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import qlhs.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
	Student findByAccountId(int id_taikhoan);
}
