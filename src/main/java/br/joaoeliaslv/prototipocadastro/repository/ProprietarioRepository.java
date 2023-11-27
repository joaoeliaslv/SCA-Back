package br.joaoeliaslv.prototipocadastro.repository;

import br.joaoeliaslv.prototipocadastro.entities.Imovel;
import br.joaoeliaslv.prototipocadastro.entities.ImovelProprietarios;
import br.joaoeliaslv.prototipocadastro.entities.Proprietario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProprietarioRepository extends PagingAndSortingRepository<Proprietario, Integer>
{
    public Proprietario findFirstByCpf(String cpf);

    public Proprietario findFirstByRg(String rg);

    public Proprietario findFirstByRegistro(int registro);

    public Page<Proprietario> findByRegistro(int registro, Pageable pageable);

    public Page<Proprietario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    public Page<Proprietario> findByCpfContaining(String cpf, Pageable pageable);

    public Page<Proprietario> findByRgContainingIgnoreCase(String rg, Pageable pageable);

    @Query(value = "SELECT i FROM Imovel i WHERE i.id IN " +
            "(SELECT ip.imovelId FROM ImovelProprietarioRelation ip WHERE ip.proprietarioId = ?1)")
    public List<Imovel> findAllImoveis(int idProprietario);
}
