package br.joaoeliaslv.prototipocadastro.validation.pessoa;

import br.joaoeliaslv.prototipocadastro.validation.pessoa.PessoaPutDTOValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PessoaPutDTOValidator.class)
public @interface ValidPessoaPutDTO
{
    String message() default "Pessoa inválida.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };
}
