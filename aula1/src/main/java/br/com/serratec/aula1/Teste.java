package br.com.serratec.aula1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Controlador que vai receber e responder as requisições HTTP;

@RestController // Necessário para ser buscado
@RequestMapping("/aulas") // Essa aqui cria o end-point
public class Teste {

    @GetMapping
    public String mensagem(@RequestParam String frase) {
        return frase.toUpperCase();
    }

    @GetMapping("/texto")
    public String texto() {
        return "Segundo Map - colocando mais um comentário na página";
    }

}
