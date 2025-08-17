package br.com.alura.forumHub.infra.security;

import br.com.alura.forumHub.resposta.RespostaRepo;
import br.com.alura.forumHub.topico.TopicoRepo;
import br.com.alura.forumHub.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("securityService")
public class SecurityService {

    @Autowired
    private TopicoRepo topicoRepo;

    @Autowired
    private RespostaRepo respostaRepo;

    public boolean isAutorDoTopico(Authentication authentication, Long topicoId) {
        var topico = topicoRepo.findById(topicoId).orElse(null);
        if (topico == null) {
            return false;
        }
        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();
        return topico.getAutor().getId().equals(usuarioAutenticado.getId());
    }

    public boolean isAutorDaResposta(Authentication authentication, Long respostaId) {
        var resposta = respostaRepo.findById(respostaId).orElse(null);
        if (resposta == null) {
            return false;
        }
        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();
        return resposta.getAutor().getId().equals(usuarioAutenticado.getId());
    }
}