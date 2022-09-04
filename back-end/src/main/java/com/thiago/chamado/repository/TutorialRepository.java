package com.thiago.chamado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiago.chamado.entity.Tutorial;


public interface TutorialRepository extends JpaRepository<Tutorial, String> {

	//Page<Live> findByLiveDateBeforeOrderByLiveDateDesc(LocalDateTime now, Pageable pageable);

	//Page<Live> findByRegistrationDateBeforeOrderByRegistrationDateDesc(LocalDateTime now, Pageable pageable);
}
