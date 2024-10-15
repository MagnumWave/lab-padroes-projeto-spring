package dio.lab_padroes_projeto_spring.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dio.lab_padroes_projeto_spring.model.Endereco;

//removi o S do https porque deu um problema de certificado SSL
//a solução mais correta exigia que eu baixasse o certificado e fizesse algumas mudanças na biblioteca.
@FeignClient(name = "viacep", url = "http://viacep.com.br/ws")
public interface ViaCepService {

	@GetMapping("/{cep}/json/")
	Endereco consultarCep(@PathVariable("cep") String cep);
}
