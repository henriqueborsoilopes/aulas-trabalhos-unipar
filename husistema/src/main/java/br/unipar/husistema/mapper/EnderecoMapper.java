package br.unipar.husistema.mapper;

import br.unipar.husistema.dto.EnderecoDTO;
import br.unipar.husistema.entity.Endereco;

public class EnderecoMapper {
    
    public static Endereco getEntidade(EnderecoDTO dto) {
        return new Endereco(
                null, 
                dto.getLogradouro(), 
                dto.getNumero(), 
                dto.getComplemento(), 
                dto.getBairro(), 
                dto.getCidade(), 
                dto.getUf(), 
                dto.getCep());
    }
    
    public static EnderecoDTO getDTO(Endereco entity) {
        return new EnderecoDTO( 
                entity.getLogradouro(), 
                entity.getNumero(), 
                entity.getComplemento(), 
                entity.getBairro(), 
                entity.getCidade(), 
                entity.getUf(), 
                entity.getCep());
    }
}
