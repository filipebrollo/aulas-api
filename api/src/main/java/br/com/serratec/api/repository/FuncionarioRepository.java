package br.com.serratec.api.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.serratec.api.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {
    
    @Query(value = "select f from funcionario f where f.salario between :valorMinimo and :valorMaximo")
    Page<Funcionario> buscarSalarioPorFaixa(Double valorMinimo, Double valorMaximo);

    @Query(value = "select f from funcionario f where nome ilike CONCAT('%',:nome,'%')", nativeQuery = true)
    Page<Funcionario> buscarSalarioPorNome(Pageable pageable, String nome);


}
