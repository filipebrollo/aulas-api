package br.com.serratec.api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password4j.BcryptPassword4jPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serratec.api.dto.UsuarioRequestDTO;
import br.com.serratec.api.dto.UsuarioResponseDTO;
import br.com.serratec.api.exception.UsuarioException;
import br.com.serratec.api.model.Usuario;
import br.com.serratec.api.model.UsuarioPerfil;
import br.com.serratec.api.repository.PerfilRepository;
import br.com.serratec.api.repository.UsuarioPerfilRepository;
import br.com.serratec.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    private final PerfilService perfilService;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioPerfilRepository usuarioPerfilRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private BcryptPassword4jPasswordEncoder criptografar;

    UsuarioService(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    public List<UsuarioResponseDTO> listarTodos() {
        List<Usuario> usuarios = repository.findAll();
        List<UsuarioResponseDTO> dto = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            dto.add(new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail()));

        }
        return dto;
    }

    @Transactional
    public UsuarioResponseDTO inserir(UsuarioRequestDTO dto) {
        Usuario usuarioBanco = repository.findByEmail(dto.getEmail());
        if (usuarioBanco != null) {
            throw new UsuarioException("Email já cadastrado");
        }
        dto.setSenha(criptografar.encode(dto.getSenha()));

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(criptografar.encode(dto.getSenha()));
        Usuario usuarioSalvo = repository.save(usuario);

        for (UsuarioPerfil up : dto.getUsuarioPerfis()) {
            up.setUsuario(usuarioSalvo);
            up.setPerfil(perfilService.buscar(up.getPerfil().getId()).get());
            up.setDataCriacao(LocalDate.now());
            up.setAtivo(true);

        }

        usuarioPerfilRepository.saveAll(dto.getUsuarioPerfis());

        return new UsuarioResponseDTO(usuarioSalvo.getId(), usuarioSalvo.getNome(), usuarioSalvo.getEmail());
    }

}
