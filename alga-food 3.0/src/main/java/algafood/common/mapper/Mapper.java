package algafood.common.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
    }

    public static <T, S> S generalMapper(T entity, Class<S> convertClass) {
        return modelMapper.map(entity, convertClass);
    }

    public <T, S> List<S> mapCollection(List<T> listEntity, Class<S> convertClass) {
        return listEntity.stream().map(entity -> generalMapper(entity, convertClass))
                .collect(Collectors.toList());
    }

}
