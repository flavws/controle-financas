package br.com.challenge.controlefinanceiro.repository;

import br.com.challenge.controlefinanceiro.dto.GastoCategoriaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.challenge.controlefinanceiro.model.Despesa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>{

    Optional<Despesa> findByDescricaoAndData(String descricao, LocalDate data);

    @Query("select sum(d.valor) from Despesa d where year(d.data) = :ano and month(d.data) = :mes")
    Optional<BigDecimal> valorMes(Integer ano, Integer mes);

    @Query("select new br.com.challenge.controlefinanceiro.dto.GastoCategoriaDTO (d.categoria, sum(d.valor))"
            +"from Despesa d where YEAR(d.data) = :ano and MONTH(d.data) = :mes group by d.categoria")
    List<GastoCategoriaDTO> valorAgrupado(Integer ano, Integer mes);

    @Query(value = "SELECT * FROM Despesa WHERE descricao= :descricao", nativeQuery = true)
    Page<Despesa> findByDescricaoDespesas(@Param("descricao") String descricao, Pageable paginacao);

    List<Despesa> findByDescricao(String descricao);


}
