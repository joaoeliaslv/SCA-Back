package br.joaoeliaslv.prototipocadastro.validation.pessoa;

import br.joaoeliaslv.prototipocadastro.entities.Pessoa;
import br.joaoeliaslv.prototipocadastro.entities.dto.pessoa.PessoaPutDTO;
import br.joaoeliaslv.prototipocadastro.service.PessoaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class PessoaPutDTOValidator implements ConstraintValidator<ValidPessoaPutDTO, PessoaPutDTO>
{
    private PessoaService pessoaService;
    @Value("${app.maxImgSize}")
    private long imgMaxSize;

    public PessoaPutDTOValidator(PessoaService pessoaService)
    {
        this.pessoaService = pessoaService;
    }

    @Override
    public boolean isValid(PessoaPutDTO dto, ConstraintValidatorContext constraintValidatorContext)
    {
        Pessoa pessoaCPF = pessoaService.buscarPorCPF(dto.getCpf());
        Pessoa pessoaRG = pessoaService.buscarPorRG(dto.getRg());
        Pessoa pessoaFicha = pessoaService.buscarPorFicha(dto.getFicha());
        boolean ret = true;
        if (pessoaCPF != null && pessoaCPF.getId() != dto.getId())
        {
            ret = false;
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("CPF já existe").addPropertyNode("cpf").addConstraintViolation();
        }

        if (pessoaRG != null && pessoaRG.getId() != dto.getId())
        {
            ret = false;
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("RG já existe").addPropertyNode("rg").addConstraintViolation();
        }

        if (pessoaFicha != null && pessoaFicha.getId() != dto.getId())
        {
            ret = false;
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Ficha já existente").addPropertyNode("ficha").addConstraintViolation();
        }

        return ret;
    }
}
