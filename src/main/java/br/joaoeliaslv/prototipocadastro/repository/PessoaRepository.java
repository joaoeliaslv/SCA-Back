package br.joaoeliaslv.prototipocadastro.repository;

import br.joaoeliaslv.prototipocadastro.entities.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Integer>
{
    public Pessoa findFirstByCpf(String cpf);

    public Pessoa findFirstByRg(String rg);

    public Page<Pessoa> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    public Page<Pessoa> findByCpfContaining(String cpf, Pageable pageable);

    public Page<Pessoa> findByRgContainingIgnoreCase(String rg, Pageable pageable);

    public Page<Pessoa> findByFicha(Integer ficha, Pageable pageable);

    public Pessoa findByFicha(Integer ficha);

    @Query(value = "SELECT nextval('pessoa_ficha_seq')", nativeQuery = true)
    public int getNextFicha();

    @Query(value = "SELECT setval('pessoa_ficha_seq', ?1)", nativeQuery = true)
    public void atualizarNextFicha(int nextFicha);
}
