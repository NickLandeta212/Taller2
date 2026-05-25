package ec.edu.poo;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayListUsoTest {

    @Test
    void gestorUsaArrayListDeActivoDigitalNoEstatico() throws Exception {
        Field activos = GestorActivos.class.getDeclaredField("activos");

        assertEquals(ArrayList.class, activos.getType());
        assertFalse(Modifier.isStatic(activos.getModifiers()));
    }

    @Test
    void constructorInicializaArrayList() {
        GestorActivos gestor = new GestorActivos();

        assertNotNull(gestor.obtenerActivos());
        assertTrue(gestor.obtenerActivos() instanceof ArrayList);
        assertEquals(0, gestor.obtenerCantidadActivos());
    }

    @Test
    void dosGestoresTienenListasIndependientes() {
        GestorActivos gestor1 = new GestorActivos();
        GestorActivos gestor2 = new GestorActivos();

        gestor1.registrarActivo(new ActivoDigital("AD001", "Activo 1", 5, false));

        assertEquals(1, gestor1.obtenerCantidadActivos());
        assertEquals(0, gestor2.obtenerCantidadActivos());
    }
}
