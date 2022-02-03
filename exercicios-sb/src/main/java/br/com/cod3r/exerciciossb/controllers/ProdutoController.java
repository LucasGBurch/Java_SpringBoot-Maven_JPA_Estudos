package br.com.cod3r.exerciciossb.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cod3r.exerciciossb.model.entities.Produto;
import br.com.cod3r.exerciciossb.model.repositories.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired // Método de Injeção de Dependência (DI) via interface que extende o Crud do SB:
	private ProdutoRepository produtoRepository; // Esta variável injeta o novo Produto
	
	// @PostMapping Método será chamado quando for feita requisição do tipo Post para o CREATE	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}) // JUNTANDO UPDATE NO MÉTODO QUE CRIA!!!
	public @ResponseBody Produto salvarProduto(@Valid Produto produto) { // Objeto representando todos os atributos
		produtoRepository.save(produto); // Id é fornecido corretamente com o save
		return produto; // Já vai com todos os atributos pra simplificar
	}
	
	@GetMapping // Requisição tipo GET para o READ (todos os registros, um Array de elementos!)
	public Iterable<Produto> obterProdutos() {
		return produtoRepository.findAll();
	}
	
	@GetMapping(path="/nome/{parteNome}") // Parte nome pode ser até mesmo uma única letra!
	public Iterable<Produto> obterProdutosPorNome(
			@PathVariable String parteNome) {
		return produtoRepository.findByNomeContainingIgnoreCase(parteNome);
	}
	
	// MELHOR ALTERNATIVA PARA CONSULTAS EM UM SISTEMA!!
	@GetMapping(path="/pagina/{numeroPagina}/{qtdePagina}") // Consulta paginada
	public Iterable<Produto> obterProdutosPorPagina(
			@PathVariable int numeroPagina,
			@PathVariable int qtdePagina) {
		if (qtdePagina >= 5) qtdePagina = 5;
		Pageable page = PageRequest.of(numeroPagina, qtdePagina);
		return produtoRepository.findAll(page);
	}
	
	@GetMapping(path="/{id}") // pelo id, precisa do path
	public Optional<Produto> obterProdutoPorId(@PathVariable int id) {
		return produtoRepository.findById(id); // Usa-se um Optional pro caso de entregar um id que não existe!!
	}
	
	// @PutMapping // PUT altera um Objeto inteiro, PATCH alguns atributo dele (são os UPDATE)
	// public Produto alterarProduto(@Valid Produto produto) { // Put acaba sendo usado para alterar o Objeto
	// 	produtoRepository.save(produto); // Mesmo que a alteração seja apenar em alguns atributos
	// 	return produto;
	// } ESTE PUT SEPARADO É SUBSTITUÍDO LÁ EM CIMA JUNTO DO POST
	
	@DeleteMapping(path="/{id}") // 8 e o 9 (dados sem nome) foram deletados no teste
	public void excluirProduto(@PathVariable int id) { // existe um "soft delete" também... ele cria chave 0-1
		produtoRepository.deleteById(id); // para "marcar" os que foram deletados, mas segue o registro no banco
	}
}
