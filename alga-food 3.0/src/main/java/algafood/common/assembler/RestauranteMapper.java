package algafood.common.assembler;

import algafood.api.dtos.output.RestauranteOutputDTO;
import algafood.common.mapper.Mapper;
import algafood.domain.models.Restaurante;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteMapper {

    @Autowired
    Mapper mapper;

    @PostConstruct
    public void adicionarMapeamentosCustomizados() {


        mapper.addMapping(Restaurante.class, RestauranteOutputDTO.class, expressionMap -> {
            expressionMap.map(Restaurante::getTaxaFrete, RestauranteOutputDTO::setTaxaFrete);
        });

    }
}
