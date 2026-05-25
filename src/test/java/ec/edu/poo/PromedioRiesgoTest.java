package ec.edu.poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PromedioRiesgoTest {

    @Test
    void promedioEsCeroSiNoHayActivos() {
        GestorActivos gestor = new GestorActivos();

        assertEquals(0.0, gestor.calcularPromedioRiesgo(), 0.001);
    }

    @Test
    void calculaPromedioDeRiesgo() {
        GestorActivos gestor = new GestorActivos();

        gestor.registrarActivo(new Servidor("SV001", "Servidor", 8, false, "Linux"));
        gestor.registrarActivo(new Firewall("FW001", "Firewall", 6, false, 70));
        gestor.registrarActivo(new ActivoDigital("AD001", "Base de datos", 10, false));

        assertEquals(8.0, gestor.calcularPromedioRiesgo(), 0.001);
    }
}
