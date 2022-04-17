package fr.mns.jee.projet.repository;

import java.time.LocalDate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.mns.jee.projet.model.Gender;
import fr.mns.jee.projet.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByBirthDateAfter(LocalDate date);
	List<User> findByGender(Gender gender);
	List<User> getByOrderByBirthDateAsc();


    User findByUsername(String username);
}
