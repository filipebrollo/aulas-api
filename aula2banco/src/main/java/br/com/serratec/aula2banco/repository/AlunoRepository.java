package br.com.serratec.aula2banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.aula2banco.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    // Chama a classe desejada para fazer o CRUD e depois escolhe o tipo da chave
    // primária
    // Dessa forma se cria o CRUD para a classe desejada - CREATE, READ, PUT e
    // DELETE

}
