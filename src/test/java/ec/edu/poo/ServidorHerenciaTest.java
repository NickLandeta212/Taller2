package ec.edu.poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServidorHerenciaTest {

    @Test
    void servidorHeredaDeActivoDigital() {
        Servidor servidor = new Servidor("SV001", "Servidor Web", 8, false, "Linux");

        assertTrue(servidor instanceof ActivoDigital);
        assertEquals("SV001", servidor.getCodigo());
        assertEquals("Servidor Web", servidor.getNombre());
        assertEquals(8, servidor.getNivelRiesgo());
        assertEquals("Linux", servidor.getSistemaOperativo());

        servidor.setSistemaOperativo("Windows Server");
        assertEquals("Windows Server", servidor.getSistemaOperativo());
    }
}
