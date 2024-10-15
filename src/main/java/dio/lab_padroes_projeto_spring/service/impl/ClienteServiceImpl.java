package dio.lab_padroes_projeto_spring.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dio.lab_padroes_projeto_spring.model.Cliente;
import dio.lab_padroes_projeto_spring.model.Endereco;
import dio.lab_padroes_projeto_spring.repository.ClienteRepository;
import dio.lab_padroes_projeto_spring.repository.EnderecoRepository;
import dio.lab_padroes_projeto_spring.service.ClienteService;
import dio.lab_padroes_projeto_spring.service.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> clienteNoBd = clienteRepository.findById(id);
        return clienteNoBd.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteNoBD = clienteRepository.findById(id);
        
        if(clienteNoBD.isPresent()){
            cliente.setId(id);
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();

        Endereco enderecoNoBD = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco enderecoNoViaCEP = viaCepService.consultarCep(cep);
            enderecoRepository.save(enderecoNoViaCEP);
            return enderecoNoViaCEP;
        });

        cliente.setEndereco(enderecoNoBD);
        clienteRepository.save(cliente);
    }

}
