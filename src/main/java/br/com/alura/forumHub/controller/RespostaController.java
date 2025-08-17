package br.com.alura.forumHub.controller;

import br.com.alura.forumHub.resposta.*;
import br.com.alura.forumHub.topico.TopicoRepo;
import br.com.alura.forumHub.usuario.UsuarioRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    private RespostaRepo repository;

    @Autowired
    private TopicoRepo topicoRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroResposta dados, UriComponentsBuilder uriBuilder) {
        var topico = topicoRepo.getReferenceById(dados.idTopico());
        var autor = usuarioRepo.getReferenceById(dados.idAutor());

        var resposta = new Resposta(dados.mensagem(), topico, autor);
        repository.save(resposta);

        var uri = uriBuilder.path("/respostas/{id}").buildAndExpand(resposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemResposta(resposta));
    }

    @GetMapping("/topico/{idTopico}")
    public ResponseEntity<List<DadosListagemResposta>> listarPorTopico(@PathVariable Long idTopico) {
        var respostas = repository.findAllByTopicoId(idTopico);
        var listagem = respostas.stream().map(DadosListagemResposta::new).toList();
        return ResponseEntity.ok(listagem);
    }

    // --- MÉTODO para ATUALIZAÇÃO ---
    @PutMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN') or @securityService.isAutorDaResposta(authentication, #id)")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoResposta dados) {
        var resposta = repository.getReferenceById(id);
        resposta.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosListagemResposta(resposta));
    }

    // --- MÉTODO para EXCLUSÃO ---
    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN') or @securityService.isAutorDaResposta(authentication, #id)")
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}