package br.joaoeliaslv.prototipocadastro.validation.usuario;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsuarioPutDTOValidator.class)
public @interface ValidUsuarioPutDTO
{
    String message() default "Usuário inválido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };
}
