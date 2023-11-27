package br.joaoeliaslv.prototipocadastro.repository;

import br.joaoeliaslv.prototipocadastro.entities.ImovelProprietarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends PagingAndSortingRepository<ImovelProprietarios, Integer>
{
    public ImovelProprietarios findFirstByProtocolo(int protocolo);

    public ImovelProprietarios findFirstByNumeroMatricula(int numeroMatricula);

    public Page<ImovelProprietarios> findByNumeroMatricula(int matricula, Pageable pageable);

    public Page<ImovelProprietarios> findByProtocolo(int protocolo, Pageable pageable);

    public Page<ImovelProprietarios> findByTipoImovelContainingIgnoreCase(String tipoImovel, Pageable pageable);

    public Page<ImovelProprietarios> findByEndereco_RuaContainingIgnoreCase(String rua, Pageable pageable);
}
