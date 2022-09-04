package com.thiago.chamado.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiago.chamado.dto.ColaboradorDTO;
import com.thiago.chamado.entity.Colaborador;
import com.thiago.chamado.entity.enums.Perfil;
import com.thiago.chamado.exceptions.ResourceNotFoundException;
import com.thiago.chamado.repository.ColaboradorRepository;
import com.thiago.chamado.security.UserSS;
import com.thiago.chamado.services.exceptions.AuthorizationException;
import com.thiago.chamado.services.exceptions.DataIntegrityException;

@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorRepository repository;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	/*@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;*/
		
	/*@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;*/
	
	public Colaborador find(Long id) {
		
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Colaborador> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Colaborador.class.getName()));
		
	}
	
	@Transactional
	public Colaborador insert(ColaboradorDTO obj) {
		obj.setId(null);
		obj.setSenha(encoder.encode(obj.getSenha()));
		validaEmail(obj);
		Colaborador newobj = new Colaborador(obj);
		return repository.save(newobj);
		//return obj;
	}
	
	public Colaborador update(Colaborador obj) {
		Colaborador newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há chamados relacionados");
		}
	}
	
	public List<Colaborador> findAll() {
		return repository.findAll();
	}
	
	/*public Colaborador findByEmail(String email) {
		/*UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
	
		Colaborador obj = repository.findByEmail(email);
		/*if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Colaborador.class.getName());
		}
		
		
		return obj;


	}*/
	
	/*public Page<Colaborador> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Colaborador fromDTO(ColaboradorDTO objDto) {
		return null;
		//return new Colaborador(size, prefix, prefix, prefix);
	}*/
	
	/*public Colaborador fromDTO(ClienteNewDTO objDto) {
		Colaborador cli = new Colaborador(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}*/
	
	private void updateData(Colaborador newObj, Colaborador obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	/*public URI uploadProfilePicture(MultipartFile multipartFile) {
		return null;
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		String fileName = prefix + user.getId() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}*/
	
	private void validaEmail(ColaboradorDTO objDTO) {
		
		Optional<Colaborador> obj = repository.findByEmail(objDTO.getEmail());		
		
		if (obj.isPresent()  && obj.get().getId() != objDTO.getId()) {
			
			throw new DataIntegrityViolationException("E-mail já está cadastrado no sistema!");
		}
		
	}

	public Optional<Colaborador> findByEmail(String email) {
		Optional<Colaborador> obj = repository.findByEmail(email);
		return obj;
	}

}
