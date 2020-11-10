package qlhs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qlhs.model.Chucvu;
import qlhs.model.Student;
import qlhs.repository.ChucvuRepository;

@Service
public class ChucvuService {

	@Autowired
	ChucvuRepository chucvuRepository;
	
	public List<Chucvu> findAll(){
		Iterable<Chucvu> it = chucvuRepository.findAll();
		List<Chucvu> chucvus = new ArrayList<Chucvu>();
		it.forEach(chucvu -> chucvus.add(chucvu));
		return chucvus;
	}
	
	public Chucvu findById(int id) {
		Optional<Chucvu> chucvu = chucvuRepository.findById(id);
		return chucvu.get();
	}
}
