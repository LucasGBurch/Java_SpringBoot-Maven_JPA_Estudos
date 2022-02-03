package br.com.cod3r.exerciciossb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cod3r.exerciciossb.model.entities.Cliente;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

	@GetMapping(path = "/qualquer")
	public Cliente obterCliente() {
		return new Cliente(28, "Fulano", "123.456.789-00");
	}

	@GetMapping("/{id}") // Usado com pathvariable abaixo. Ficaria tipo: /clientes/ID
	public Cliente obterClientePorId1(@PathVariable int id) {
		return new Cliente(id, "Maria", "987.654.321-00");
	}

	// Pegando via conteúdo da URL: /clientes?id=123 (qualquer número), conhecido como RequestParam
	@GetMapping
	public Cliente obterClientePorId2( // default é pra caso não coloque valor nenhum (/clientes só)
			@RequestParam(name = "id", defaultValue = "1") int id) {
		return new Cliente(id, "João Augusto", "111.222.333-44");
	}
}
