package dio.lab_padroes_projeto_spring.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import dio.lab_padroes_projeto_spring.model.Endereco;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco,String> {
}
