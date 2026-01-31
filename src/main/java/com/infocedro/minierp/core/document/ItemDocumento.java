package com.infocedro.minierp.core.document;

import java.math.BigDecimal;

public class ItemDocumento {

    private final String descricao;
    private final int quantidade;
    private final BigDecimal valorUnitario;

    public ItemDocumento(String descricao, int quantidade, BigDecimal valorUnitario) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição do item é obrigatória");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        if (valorUnitario == null || valorUnitario.signum() < 0) {
            throw new IllegalArgumentException("Valor unitário inválido");
        }

        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

}
