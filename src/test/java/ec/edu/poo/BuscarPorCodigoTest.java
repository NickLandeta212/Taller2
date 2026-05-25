package ec.edu.poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BuscarPorCodigoTest {

    @Test
    void buscaActivoPorCodigo() {
        GestorActivos gestor = new GestorActivos();
        Servidor servidor = new Servidor("SV001", "Servidor Web", 8, false, "Linux");
        Firewall firewall = new Firewall("FW001", "Firewall Central", 6, false, 100);

        gestor.registrarActivo(servidor);
        gestor.registrarActivo(firewall);

        assertSame(servidor, gestor.buscarPorCodigo("SV001"));
        assertSame(firewall, gestor.buscarPorCodigo("FW001"));
        assertNull(gestor.buscarPorCodigo("NO_EXISTE"));
    }
}
