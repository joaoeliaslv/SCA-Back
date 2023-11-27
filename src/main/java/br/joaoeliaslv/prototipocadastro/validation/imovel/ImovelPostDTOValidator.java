package br.joaoeliaslv.prototipocadastro.validation.imovel;

import br.joaoeliaslv.prototipocadastro.entities.ImovelProprietarios;
import br.joaoeliaslv.prototipocadastro.entities.dto.imovel.ImovelPostDTO;
import br.joaoeliaslv.prototipocadastro.service.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImovelPostDTOValidator implements ConstraintValidator<ValidImovelPostDTO, ImovelPostDTO>
{
    @Autowired
    private ImovelService imovelService;

    @Override
    public boolean isValid(ImovelPostDTO imovelPostDTO, ConstraintValidatorContext constraintValidatorContext)
    {
        boolean ret = true;
        ImovelProprietarios imovelProprietariosMatricula = imovelService.buscarPorMatricula(imovelPostDTO.getNumeroMatricula());
        ImovelProprietarios imovelProprietariosProtocolo = imovelService.buscarPorProtocolo(imovelPostDTO.getProtocolo());

        if (imovelPostDTO.getId() > 0)
        {
            if (imovelProprietariosMatricula != null && imovelProprietariosMatricula.getId() != imovelPostDTO.getId())
            {
                ret = false;
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Matrícula já existe").addPropertyNode("numeroMatricula").addConstraintViolation();
            }

            if (imovelProprietariosProtocolo != null && imovelProprietariosProtocolo.getId() != imovelPostDTO.getId())
            {
                ret = false;
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Protocolo já existe").addPropertyNode("protocolo").addConstraintViolation();
            }
        }
        else
        {
            if (imovelProprietariosMatricula != null)
            {
                ret = false;
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Matrícula já existe").addPropertyNode("numeroMatricula").addConstraintViolation();
            }

            if (imovelProprietariosProtocolo != null)
            {
                ret = false;
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Protocolo já existe").addPropertyNode("protocolo").addConstraintViolation();
            }
        }

        return ret;
    }

}
