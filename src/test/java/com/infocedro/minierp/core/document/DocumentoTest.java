package com.infocedro.minierp.core.document;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DocumentoTest {

    @Test
    void naoDeveFecharDocumentoSemItens() {
        Documento doc = new Documento(TipoDocumento.COMPRA, null);

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                doc::fechar
        );

        assertEquals("Documento não pode ser fechado sem itens", ex.getMessage());
    }

    @Test
    void deveFecharDocumentoComItem() {
        Documento doc = new Documento(TipoDocumento.COMPRA, null);

        ItemDocumento item = new ItemDocumento(
                "Produto A",
                10,
                BigDecimal.TEN
        );

        doc.adicionarItem(item);
        doc.fechar();

        assertEquals(StatusDocumento.FECHADO, doc.getStatusDocumento());
    }

    @Test
    void naoDeveAdicionarItemEmDocumentoFechado() {
        Documento doc = new Documento(TipoDocumento.COMPRA, null);

        doc.adicionarItem(
                new ItemDocumento("Produto A", 1, BigDecimal.ONE)
        );
        doc.fechar();

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> doc.adicionarItem(
                        new ItemDocumento("Produto B", 1, BigDecimal.ONE)
                )
        );

        assertTrue(ex.getMessage().contains("RASCUNHO"));
    }

    @Test
    void documentoComOrigemDeveManterReferencia() {
        Documento pedido = new Documento(TipoDocumento.COMPRA, null);

        Documento nota = new Documento(TipoDocumento.COMPRA, pedido);

        assertEquals(pedido, nota.getDocumentoOrigem());
    }
}
