package br.com.challenge.controlefinanceiro.controller;

import br.com.challenge.controlefinanceiro.dto.ResumoMensalDTO;
import br.com.challenge.controlefinanceiro.service.ResumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resumo")
public class ResumoController {

    @Autowired
    private ResumoService resumoService;

    @GetMapping("/{ano}/{mes}")
    public ResumoMensalDTO resumoMes(@PathVariable Integer ano, @PathVariable Integer mes) {

        return resumoService.resumoMes(ano, mes);
    }
}
