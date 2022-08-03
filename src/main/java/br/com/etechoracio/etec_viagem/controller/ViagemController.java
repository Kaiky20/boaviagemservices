package br.com.etechoracio.etec_viagem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.etechoracio.etec_viagem.entity.Viagem;
import br.com.etechoracio.etec_viagem.repository.ViagemRepository;



@RestController
@RequestMapping("/viagens")	
public class ViagemController {
	
	@Autowired
	private  ViagemRepository repository;
	private List<Viagem> dados = new ArrayList<Viagem>();
	@GetMapping
	public List<Viagem> listarTodos(){
		dados = repository.findAll();
		return dados;
	}
		
		@GetMapping("/(id)")
		public ResponseEntity<Viagem> buscarporId (@PathVariable Integer id) {
			
			java.util.Optional<Viagem> tipo = repository.findById(id);
			
			return ResponseEntity.ok(tipo.get());
		}	
      @PostMapping
      public ResponseEntity<Viagem> inserir(@RequestBody Viagem viagem){
    	  repository.save(viagem);
    	  return ResponseEntity.ok(viagem);
      }
      @PutMapping("(/id)")
	public void atualizar (@PathVariable Integer id, @RequestBody Viagem viagem) {
    	  boolean existe = repository.existsById(id);
    	  if(existe)
    	  {
    		  repository.save(viagem);
    	  }
      }
}	    	

