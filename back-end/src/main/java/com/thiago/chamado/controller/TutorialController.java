package com.thiago.chamado.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thiago.chamado.dto.TutorialDTO;
import com.thiago.chamado.entity.Tutorial;
import com.thiago.chamado.services.TutorialService;

@CrossOrigin(origins = "*")
@RestController
public class TutorialController {
	
	@Autowired
    private TutorialService tutorialService;

    @GetMapping("/lives")
    public ResponseEntity<Page<Tutorial>> getAllLives(
    	@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){ 
    	
    	Page<Tutorial> tutorialPage = tutorialService.findAll(pageable);
        
        if(tutorialPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<Page<Tutorial>>(tutorialPage, HttpStatus.OK);
        }
    }

    @GetMapping("/lives/{id}")
    public ResponseEntity<Tutorial> getOneLive(@PathVariable(value="id") String id){
        Optional<Tutorial> tutorialO = tutorialService.findById(id);
        if(!tutorialO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<Tutorial>(tutorialO.get(), HttpStatus.OK);
        }
    }

    @PostMapping("/lives")
    public ResponseEntity<Tutorial> saveTutorial(@Valid @RequestBody TutorialDTO objDTO) {
    	objDTO.setRegistrationDate(LocalDateTime.now());
      
        Tutorial newObj = tutorialService.save(objDTO);       	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/lives/{id}")
    public ResponseEntity<?> deleteLive(@PathVariable(value="id") String id) {
        Optional<Tutorial> tutorialO = tutorialService.findById(id);
        if(!tutorialO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            tutorialService.delete(tutorialO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/lives/{id}")
    public ResponseEntity<Tutorial> updateLive(@PathVariable(value="id") String id,
                                                      @RequestBody @Valid TutorialDTO objDTO) {
        Optional<Tutorial> tutorialO = tutorialService.findById(id);
        if(!tutorialO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
        	objDTO.setId(tutorialO.get().getId());
            return new ResponseEntity<Tutorial>(tutorialService.save(objDTO), HttpStatus.OK);
        }
    }

}
