package br.joaoeliaslv.prototipocadastro.validation.pessoa;

import br.joaoeliaslv.prototipocadastro.entities.Pessoa;
import br.joaoeliaslv.prototipocadastro.entities.dto.pessoa.PessoaPostDTO;
import br.joaoeliaslv.prototipocadastro.service.PessoaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PessoaPostDTOValidator implements ConstraintValidator<ValidPessoaPostDTO, PessoaPostDTO>
{
    private PessoaService pessoaService;
    @Value("${app.maxImgSize}")
    private long imgMaxSize;

    public PessoaPostDTOValidator(PessoaService pessoaService)
    {
        this.pessoaService = pessoaService;
    }

    @Override
    public boolean isValid(PessoaPostDTO dto, ConstraintValidatorContext constraintValidatorContext)
    {
        Pessoa pessoaCPF = pessoaService.buscarPorCPF(dto.getCpf());
        Pessoa pessoaRG = pessoaService.buscarPorRG(dto.getRg());
        boolean ret = true;
        if (pessoaCPF != null)
        {
            ret = false;
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("CPF já existe").addPropertyNode("cpf").addConstraintViolation();
        }

        if (pessoaRG != null)
        {
            ret = false;
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("RG já existe").addPropertyNode("rg").addConstraintViolation();
        }

        return ret;
    }
}
