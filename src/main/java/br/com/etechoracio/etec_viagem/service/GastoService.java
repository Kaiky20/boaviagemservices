package br.com.etechoracio.etec_viagem.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.etechoracio.etec_viagem.entity.Gasto;
import br.com.etechoracio.etec_viagem.entity.Viagem;
import br.com.etechoracio.etec_viagem.repository.GastoRepository;
import br.com.etechoracio.etec_viagem.repository.ViagemRepository;


@Service
public class GastoService {

    

    @Autowired
    private GastoRepository gastorepository;

    @Autowired
    private ViagemRepository viagemRepository;

    public List<Gasto> listarTodos() {
        return gastorepository.findAll();

    }

   
    public Optional<Gasto> buscarPorId(int id){
        return gastorepository.findById(id);

    }

    

    public Gasto inserir(Gasto obj) {
    Optional<Viagem> existe = viagemRepository.findById(obj.getViagem().getId());
       if(!existe.isPresent()) {
	   throw new RuntimeException("Viagem Não Encontrada. ");
       }
	   if (existe.get().getChegada().isAfter(obj.getData())) {
		 throw new RuntimeException("Data expirada");  
	   }
	   if (existe.get().getSaida().isBefore(obj.getData())) {
		   throw new RuntimeException("Data inválida"); 
	   }
	   

           return gastorepository.save(obj);
	}
    

    public Optional<Gasto> atualizar(int id, Gasto gasto){

        boolean existe = gastorepository.existsById(id);

        if(!existe) {

            return Optional.empty();

        }

        

        return Optional.of(gastorepository.save(gasto));

    }

}

