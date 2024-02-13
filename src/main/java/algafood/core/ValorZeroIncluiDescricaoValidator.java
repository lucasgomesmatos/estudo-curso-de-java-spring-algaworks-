package algafood.core;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {

   private String valorField;
   private String descricaoField;
   private String descricaoObrigatorio;
    @Override
    public void initialize(ValorZeroIncluiDescricao constraintAnnotation) {
        this.valorField = constraintAnnotation.valorField();
        this.descricaoField = constraintAnnotation.descricaoField();
        this.descricaoObrigatorio = constraintAnnotation.descricaoObrigatorio();
    }

    @Override
    public boolean isValid(Object objectoValidacao, ConstraintValidatorContext context) {
        boolean valido = true;

        try {
            var valor = (BigDecimal) BeanUtils
                    .getPropertyDescriptor(objectoValidacao.getClass(), valorField)
                    .getReadMethod()
                    .invoke(objectoValidacao);
            var descricao = (String) BeanUtils
                    .getPropertyDescriptor(objectoValidacao.getClass(), descricaoField)
                    .getReadMethod()
                    .invoke(objectoValidacao);

            if(valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null) {
                valido = descricao.toLowerCase().contains(this.descricaoObrigatorio.toLowerCase());
            }

            return valido;

        } catch (Exception e) {
            throw new ValidationException(e);
        }

    }
}
