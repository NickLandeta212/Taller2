package ec.edu.poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActivoDigitalConstructorTest {

    @Test
    void constructorYAccesoresFuncionan() {
        ActivoDigital activo = new ActivoDigital("AD001", "Base de datos", 7, false);

        assertEquals("AD001", activo.getCodigo());
        assertEquals("Base de datos", activo.getNombre());
        assertEquals(7, activo.getNivelRiesgo());
        assertFalse(activo.isParcheAplicado());

        activo.setCodigo("AD002");
        activo.setNombre("Servidor DNS");
        activo.setNivelRiesgo(9);
        activo.setParcheAplicado(true);

        assertEquals("AD002", activo.getCodigo());
        assertEquals("Servidor DNS", activo.getNombre());
        assertEquals(9, activo.getNivelRiesgo());
        assertTrue(activo.isParcheAplicado());
    }
}
