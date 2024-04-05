package br.unipar.husistema.mapper;

import br.unipar.husistema.dto.AtualizarPessoaDTO;
import br.unipar.husistema.entity.Paciente;
import br.unipar.husistema.entity.Pessoa;

public class PessoaMapper {
    
    public static Pessoa getEntidade(AtualizarPessoaDTO dto) {
        return new Paciente(
                null, 
                dto.getNome(), 
                null, 
                dto.getTelefone(), 
                true, 
                null,
                null);
    }
}
