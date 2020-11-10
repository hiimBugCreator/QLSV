package qlhs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qlhs.model.Student;
import qlhs.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	public List<Student> findAll(){
		Iterable<Student> it = studentRepository.findAll();
		List<Student> students = new ArrayList<Student>();
		it.forEach(student -> students.add(student));
		return students;
	}
	
	public Student findById(int id) {
		Optional<Student> student = studentRepository.findById(id);
		return student.get();
	}
}
