package br.joaoeliaslv.prototipocadastro.validation.proprietario;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProprietarioPostDTOValidator.class)
public @interface ValidProprietarioPostDTO
{
    String message() default "Proprietario inválido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };
}
