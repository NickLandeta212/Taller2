package ec.edu.poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AplicarParcheYReiniciarTest {

    @Test
    void aplicaParcheAActivoExistente() {
        GestorActivos gestor = new GestorActivos();
        Servidor servidor = new Servidor("SV001", "Servidor", 9, false, "Linux");

        gestor.registrarActivo(servidor);

        assertTrue(gestor.aplicarParcheActivo("SV001"));
        assertTrue(servidor.isParcheAplicado());
    }

    @Test
    void retornaFalseSiActivoNoExiste() {
        GestorActivos gestor = new GestorActivos();

        assertFalse(gestor.aplicarParcheActivo("NO_EXISTE"));
    }

    @Test
    void reiniciarDejaListaVacia() {
        GestorActivos gestor = new GestorActivos();

        gestor.registrarActivo(new ActivoDigital("AD001", "Activo 1", 5, false));
        gestor.registrarActivo(new ActivoDigital("AD002", "Activo 2", 6, false));

        gestor.reiniciar();

        assertEquals(0, gestor.obtenerCantidadActivos());
        assertNotNull(gestor.obtenerActivos());
        assertTrue(gestor.obtenerActivos().isEmpty());
    }
}
