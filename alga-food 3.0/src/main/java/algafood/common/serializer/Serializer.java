package algafood.common.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jboss.logging.Logger;
import org.yaml.snakeyaml.serializer.SerializerException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Serializer {

    static Logger logger = Logger.getLogger(Serializer.class);
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setDateFormat(new StdDateFormat());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
    }

    public Serializer() {
    }

    public static ObjectMapper getSerializer() {
        return objectMapper;
    }

    public static <T> T deserialize(String jsonString, Class<?>... clazz) {
        try {
            return objectMapper.readValue(jsonString, generateType(clazz));
        } catch (JsonProcessingException exception) {
            throw new SerializerException(exception.getMessage());
        }
    }

    public static String serialize(Object jsonObject) {
        try {
            return objectMapper.writeValueAsString(jsonObject);
        } catch (JsonProcessingException exception) {
            throw new SerializerException(exception.getMessage());
        }
    }

    public static InputStream converterObjetoParaInputStream(Object obj) throws JsonProcessingException {
        try {
            return new ByteArrayInputStream(objectMapper.writeValueAsBytes(obj));
        } catch (JsonProcessingException e) {
            logger.error(e);
            throw e;
        }
    }

    private static JavaType generateType(Class<?>... clazz) {
        if (clazz.length == 0) {
            throw new IllegalArgumentException();
        }
        if (clazz.length == 1) {
            return objectMapper.getTypeFactory().constructType(clazz[0]);
        }
        return gerarTipoRecursivamente(Arrays.asList(clazz));
    }

    private static JavaType gerarTipoRecursivamente(List<Class<?>> classes) {
        if (classes.size() == 2) {
            var classePrincipal = classes.get(0);
            var classeAdicional = classes.get(1);
            return objectMapper.getTypeFactory().constructParametricType(classePrincipal, classeAdicional);
        }
        var classePrincipal = classes.get(0);
        var classesAdicionais = new ArrayList<>(classes);
        classesAdicionais.remove(0);
        var adicional = gerarTipoRecursivamente(classesAdicionais);
        return objectMapper.getTypeFactory().constructParametricType(classePrincipal, adicional);
    }
}
