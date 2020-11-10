package qlhs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qlhs.model.Student;
import qlhs.model.Teacher;
import qlhs.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;
	
	public List<Teacher> findAll(){
		Iterable<Teacher> it = teacherRepository.findAll();
		List<Teacher> teachers = new ArrayList<Teacher>();
		it.forEach(teacher -> teachers.add(teacher));
		return teachers;
	}
	
	public Teacher findById(int id) {
		Optional<Teacher> teacher = teacherRepository.findById(id);
		return teacher.get();
	}
}
