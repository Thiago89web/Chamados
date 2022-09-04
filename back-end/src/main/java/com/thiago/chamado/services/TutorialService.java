package com.thiago.chamado.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thiago.chamado.dto.TutorialDTO;
import com.thiago.chamado.entity.Tutorial;
import com.thiago.chamado.repository.TutorialRepository;

@Service
public class TutorialService {
	
	@Autowired
    private TutorialRepository tutorialRepository;
	
    public Page<Tutorial> findAll(Pageable pageable){
        
        return tutorialRepository.findAll(pageable);        
    }

    public Optional<Tutorial> findById(String id){
        return tutorialRepository.findById(id);
    }

    public Tutorial save(TutorialDTO ObjDTO){
    	
    	ObjDTO.setId(null);	    	
		return tutorialRepository.save(newTutorial(ObjDTO));
    }
    
    private Tutorial newTutorial(TutorialDTO objDTO) {						
		Tutorial tutorial = new Tutorial();
							
		if(objDTO.getId() != null) {
			tutorial.setId(objDTO.getId());
		}
		
		tutorial.setLiveName(objDTO.getLiveName());
		tutorial.setChannelName(objDTO.getChannelName());
		tutorial.setLiveLink(objDTO.getLiveLink());
		tutorial.setRegistrationDate(objDTO.getRegistrationDate());
			
		return tutorial;
	}
        
    public void delete(Tutorial liveDocument){
    	tutorialRepository.delete(liveDocument);
    }

}
