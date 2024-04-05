package br.unipar.husistema.service;

import br.unipar.husistema.dto.AtualizarPessoaDTO;
import br.unipar.husistema.service.exception.ValidarExcecao;

public interface IPessoaService {
    
    public void atualizar(Long id_usuario, Long id_endereco, AtualizarPessoaDTO dto) throws ValidarExcecao;
    public void inativar(Long id) throws ValidarExcecao;
}
