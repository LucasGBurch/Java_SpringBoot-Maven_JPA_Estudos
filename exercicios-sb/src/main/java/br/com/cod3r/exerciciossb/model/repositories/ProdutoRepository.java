package br.com.cod3r.exerciciossb.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.cod3r.exerciciossb.model.entities.Produto;

public interface ProdutoRepository
	extends PagingAndSortingRepository<Produto, Integer> {

	public Iterable<Produto> findByNomeContainingIgnoreCase(String parteNome);
}	// Esse método funciona só ao declarar na interface por já existir na documentação do SpringBoot
	// Para mais métodos, dá pra consultar na documentação deles, que já implementaram os métodos.
	// Essa utilizada acima é similar à searchByNameLike(parteNome), por exemplo.
