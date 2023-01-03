package com.algaworks.algalog.common;

import com.algaworks.algalog.dto.EntregaDto;
import com.algaworks.algalog.model.Entrega;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssemblerMapper {

    @Autowired
    private ModelMapper mapper;

    public EntregaDto toModel(Entrega entrega){
        return mapper.map(entrega, EntregaDto.class);
    }
}
