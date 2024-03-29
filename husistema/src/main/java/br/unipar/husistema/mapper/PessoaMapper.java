package br.unipar.husistema.mapper;

import br.unipar.husistema.dto.AtualizarPessoaDTO;
import br.unipar.husistema.entity.Pessoa;

public class PessoaMapper {
    
    public Pessoa getEntity(AtualizarPessoaDTO dto) {
        return new Pessoa(
                null, 
                dto.getNome(), 
                null, 
                dto.getTelefone(), 
                true, 
                EnderecoMapper.getEntity(dto.getEndereco()));
    }
}
