package fr.mns.jee.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.mns.jee.projet.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
