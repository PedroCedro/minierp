package com.infocedro.minierp.core.conference;

import com.infocedro.minierp.core.conference.ConferenciaDocumento;
import com.infocedro.minierp.core.conference.ResultadoConferencia;
import com.infocedro.minierp.core.conference.StatusConferencia;
import com.infocedro.minierp.core.document.Documento;
import com.infocedro.minierp.core.document.ItemDocumento;
import com.infocedro.minierp.core.document.TipoDocumento;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ConferenciaDocumentoTest {

    @Test
    void deveDetectarDivergenciaQuandoQuantidadeDiferente() {
        Documento pedido = new Documento(TipoDocumento.COMPRA, null);
        pedido.adicionarItem(
                new ItemDocumento("Produto A", 10, BigDecimal.TEN)
        );

        Documento nota = new Documento(TipoDocumento.COMPRA, pedido);
        nota.adicionarItem(
                new ItemDocumento("Produto A", 8, BigDecimal.TEN)
        );

        ConferenciaDocumento conferencia =
                new ConferenciaDocumento(nota, pedido);

        ResultadoConferencia resultado = conferencia.executar();

        assertEquals(StatusConferencia.DIVERGENTE, resultado.getStatus());
        assertTrue(resultado.possuiDivergencias());
    }
}
