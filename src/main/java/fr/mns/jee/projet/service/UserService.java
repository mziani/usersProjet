package fr.mns.jee.projet.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fr.mns.jee.projet.model.*;
import fr.mns.jee.projet.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User save(User u) {
		
		return repository.save(u);
	}
	
	public Optional<User> getById(Long id){
		return repository.findById(id);
	}
	
	public List<User> findAllOrderByBirthDate(){
		return repository.findAll(Sort.by("birthDate").ascending());
	}

	public List<User> findAllOlderThan20(){
		LocalDate ld=LocalDate.now().minusYears(20);
		
		return repository.findByBirthDateAfter(ld);
	}
	
	public List<User> findByGender(Gender gender){
		return repository.findByGender(gender);
	}

	public User findByUsername(String username) {
		return repository.findByUsername(username);
	}

	public User findById(Long id) {
		return repository.getById(id);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Page<User> findAll(Integer pageNumber, Integer pageSize) {
		return repository.findAll(PageRequest.of(pageNumber, pageSize));
	}
}
