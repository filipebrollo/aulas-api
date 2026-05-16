package br.com.serratec.aula2banco.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


// O entity cria ou faz referência a uma tabela (sendo update ele atualiza uma tabela, sendo create ele cria uma nova lá no applicattion)
@Entity // caso não seja passado o nome da table, ele cria automático com o nome da classe em minúsculo;
@Table(name = "tbl_aluno")
public class Aluno {

    // A especificação é sempre feita uma linha acima;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //(AUTO) - Deixa a responsabilidade da geração da chave pelo API (IDENTITY) - Deixa a responsabilidade da geração do serial para o banco
    private Long id;

    @Column(length = 50, nullable = false, name = "nome")
    private String nome;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private Double cr;

    // O @Transient anula uma parte do código e n desse modo n vai pra tabela;

    @jakarta.persistence.Transient
    private Double media;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getCr() {
        return cr;
    }

    public void setCr(Double cr) {
        this.cr = cr;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }
    
    



}
