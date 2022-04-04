package br.com.challenge.controlefinanceiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.challenge.controlefinanceiro.model.Receita;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    Optional<Receita> findByDescricaoAndData(String descricao, LocalDate data);

    @Query("select sum(r.valor) from Receita r where year(r.data) = :ano and month(r.data) = :mes")
    Optional<BigDecimal> valorMes(Integer ano, Integer mes);

    List<Receita> findByDescricao(String descricao);
}
