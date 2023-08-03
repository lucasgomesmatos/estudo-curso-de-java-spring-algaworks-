package algafood.common.mapper;

import org.modelmapper.ExpressionMap;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public <T, S> S generalMapper(T input, Class<S> output) {
        return modelMapper.map(input, output);
    }

    public <T, S> List<S> mapCollection(List<T> input, Class<S> output) {
        return input.stream().map(entity -> generalMapper(entity, output))
                .collect(Collectors.toList());
    }

    public <S, T> void addMapping(Class<S> input, Class<T> output, ExpressionMap<S, T> expressionMap) {
        modelMapper.typeMap(input, output).addMappings(expressionMap);
    }


}
