package br.unipar.husistema.service;

import br.unipar.husistema.dto.AtualizarPessoaDTO;
import br.unipar.husistema.service.exception.BancoDadosExcecao;
import br.unipar.husistema.service.exception.ValidacaoExcecao;

public interface IPessoaService {
    
    public void atualizar(Long id_usuario, Long id_endereco, AtualizarPessoaDTO dto) throws BancoDadosExcecao, ValidacaoExcecao;
    public void inativar(Long id) throws BancoDadosExcecao;
}
