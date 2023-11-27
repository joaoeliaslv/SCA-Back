package br.joaoeliaslv.prototipocadastro.validation.proprietario;

import br.joaoeliaslv.prototipocadastro.entities.Proprietario;
import br.joaoeliaslv.prototipocadastro.entities.dto.imovel.ImovelPostDTO;
import br.joaoeliaslv.prototipocadastro.entities.dto.proprietario.ProprietarioPostDTO;
import br.joaoeliaslv.prototipocadastro.service.ProprietarioService;
import br.joaoeliaslv.prototipocadastro.validation.imovel.ValidImovelPostDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class ProprietarioPostDTOValidator implements ConstraintValidator<ValidProprietarioPostDTO, ProprietarioPostDTO>
{
    @Autowired
    private ProprietarioService proprietarioService;

    @Override
    public boolean isValid(ProprietarioPostDTO dto, ConstraintValidatorContext constraintValidatorContext)
    {
        boolean ret = true;
        Proprietario proprietarioRegistro = proprietarioService.buscarPorRegistro(dto.getRegistro());
        Proprietario proprietarioCpf = proprietarioService.buscarPorCPF(dto.getCpf());
        Proprietario proprietarioRg = proprietarioService.buscarPorRG(dto.getRg());

        if (dto.getId() > 0)
        {
            if (proprietarioRegistro != null && proprietarioRegistro.getId() != dto.getId())
            {
                ret = false;
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Registro já existe").addPropertyNode("registro").addConstraintViolation();
            }

            if (proprietarioCpf != null && proprietarioCpf.getId() != dto.getId())
            {
                ret = false;
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("CPF já existe").addPropertyNode("cpf").addConstraintViolation();
            }

            if (proprietarioRg != null && proprietarioRg.getId() != dto.getId())
            {
                ret = false;
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("RG já existe").addPropertyNode("rg").addConstraintViolation();
            }
        }
        else
        {
            if (proprietarioRegistro != null)
            {
                ret = false;
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Registro já existe").addPropertyNode("registro").addConstraintViolation();
            }

            if (proprietarioCpf != null)
            {
                ret = false;
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("CPF já existe").addPropertyNode("cpf").addConstraintViolation();
            }

            if (proprietarioRg != null)
            {
                ret = false;
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("RG já existe").addPropertyNode("rg").addConstraintViolation();
            }
        }

        return ret;
    }
}
