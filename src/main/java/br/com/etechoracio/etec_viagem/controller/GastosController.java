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

import br.com.etechoracio.etec_viagem.entity.Gasto;
import br.com.etechoracio.etec_viagem.repository.GastoRepository;



@RestController
@RequestMapping("/gastos")	

public class GastosController {
	@Autowired
	private  GastoRepository repository;
	private List<Gasto> dados = new ArrayList<Gasto>();
	@GetMapping
	public List<Gasto> listarTodos(){
		dados = repository.findAll();
		return dados;
	}
		
		@GetMapping("/(id)")
		public ResponseEntity<Gasto> buscarporId (@PathVariable Integer id) {
			
			java.util.Optional<Gasto> tipo = repository.findById(id);
			
			return ResponseEntity.ok(tipo.get());
		}	
      @PostMapping
      public ResponseEntity<Gasto> inserir(@RequestBody Gasto gasto){
    	  repository.save(gasto);
    	  return ResponseEntity.ok(gasto);
      }
      @PutMapping("(/id)")
	public void atualizar (@PathVariable Integer id, @RequestBody Gasto gasto) {
    	  boolean existe = repository.existsById(id);
    	  if(existe)
    	  {
    		  repository.save(gasto);
    	  }
      }
}

