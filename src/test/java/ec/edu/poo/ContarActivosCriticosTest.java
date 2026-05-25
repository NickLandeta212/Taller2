package ec.edu.poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContarActivosCriticosTest {

    @Test
    void cuentaActivosConRiesgoMayorOIgualAOcho() {
        GestorActivos gestor = new GestorActivos();

        gestor.registrarActivo(new Servidor("SV001", "Servidor 1", 8, false, "Linux"));
        gestor.registrarActivo(new Servidor("SV002", "Servidor 2", 9, false, "Linux"));
        gestor.registrarActivo(new Firewall("FW001", "Firewall", 7, false, 80));
        gestor.registrarActivo(new ActivoDigital("AD001", "Repositorio", 10, false));

        assertEquals(3, gestor.contarActivosCriticos());
    }
}
