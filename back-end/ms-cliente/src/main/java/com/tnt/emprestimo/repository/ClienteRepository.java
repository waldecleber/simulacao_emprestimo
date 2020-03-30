package com.tnt.emprestimo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tnt.emprestimo.entity.Cliente;

@RepositoryRestResource(collectionResourceRel = "cliente", path = "cliente")
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query(" SELECT c " +
            "   FROM Cliente c " +
            "  WHERE (:name IS NULL OR ( UPPER(c.nome) LIKE %:name%  )) " + 
            "  ORDER BY c.nome ")
	List<Cliente> buscarClientePorNome(String name);
	
	

}
