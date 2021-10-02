package com.parlonsdev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parlonsdev.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	Optional<Student> findByEmail(String email);
	Optional<Student> findByPhone(String phone);
	
	boolean existsByEmail(String email);
	boolean existsByPhone(String phone);

}
