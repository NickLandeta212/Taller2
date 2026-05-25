package ec.edu.poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrarActivoTest {

    @Test
    void registraActivoSiNoExisteCodigo() {
        GestorActivos gestor = new GestorActivos();
        Servidor servidor = new Servidor("SV001", "Servidor Web", 9, false, "Linux");

        assertTrue(gestor.registrarActivo(servidor));
        assertEquals(1, gestor.obtenerCantidadActivos());
        assertSame(servidor, gestor.obtenerActivos().get(0));
    }

    @Test
    void noRegistraCodigoDuplicado() {
        GestorActivos gestor = new GestorActivos();

        assertTrue(gestor.registrarActivo(new Servidor("SV001", "Servidor Web", 9, false, "Linux")));
        assertFalse(gestor.registrarActivo(new Firewall("SV001", "Firewall", 5, false, 50)));
        assertEquals(1, gestor.obtenerCantidadActivos());
    }

    @Test
    void permiteRegistrarMasDeDiezActivosPorSerArrayList() {
        GestorActivos gestor = new GestorActivos();

        for (int i = 1; i <= 12; i++) {
            assertTrue(gestor.registrarActivo(new ActivoDigital("AD" + i, "Activo " + i, i, false)));
        }

        assertEquals(12, gestor.obtenerCantidadActivos());
    }
}
