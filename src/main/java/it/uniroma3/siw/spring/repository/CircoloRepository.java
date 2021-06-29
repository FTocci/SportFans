package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Circolo;

public interface CircoloRepository extends CrudRepository<Circolo, Long> {

	public List<Circolo> findByNome(String nome);

	public List<Circolo> findByNomeAndIndirizzo(String nome, String indirizzo);

}