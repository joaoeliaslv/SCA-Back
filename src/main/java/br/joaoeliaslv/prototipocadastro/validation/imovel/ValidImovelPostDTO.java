package br.joaoeliaslv.prototipocadastro.validation.imovel;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImovelPostDTOValidator.class)
public @interface ValidImovelPostDTO
{
    String message() default "Imóvel inválido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };
}
