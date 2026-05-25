package ec.edu.poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FirewallHerenciaTest {

    @Test
    void firewallHeredaDeActivoDigital() {
        Firewall firewall = new Firewall("FW001", "Firewall Perimetral", 6, true, 120);

        assertTrue(firewall instanceof ActivoDigital);
        assertEquals("FW001", firewall.getCodigo());
        assertEquals("Firewall Perimetral", firewall.getNombre());
        assertEquals(6, firewall.getNivelRiesgo());
        assertTrue(firewall.isParcheAplicado());
        assertEquals(120, firewall.getReglasActivas());

        firewall.setReglasActivas(150);
        assertEquals(150, firewall.getReglasActivas());
    }
}
