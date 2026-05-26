package ec.edu.poo;

import java.util.ArrayList;

public class GestorActivos {

    private ArrayList<ActivoDigital> activos;
    /** Clase encargada de gestionar los activos digitales del sistema.
     Esta clase utiliza un ArrayList para almacenar objetos de tipo ActivoDigital.
     También permite registrar, buscar, contar activos críticos, calcular el promedio de riesgo, aplicar parches y reiniciar la lista de activos.*/

    public GestorActivos() {
        activos = new ArrayList<>();
    }
    /**Registra un nuevo activo digital en la lista.
     Antes de agregar el activo, se verifica que no exista otro activo con el mismo código. Esto evita registros duplicados.*/
    public boolean registrarActivo(ActivoDigital activo) {
        if (buscarPorCodigo(activo.getCodigo()) != null) {
            return false;
        }

        activos.add(activo);
        return true;
    }

   /**Busca un activo digital dentro de la lista usando su código.
    El método recorre todos los activos registrados y compara el código ingresado con el código de cada activo.*/

    public ActivoDigital buscarPorCodigo(String codigo) {
        for (ActivoDigital activo : activos) {
            if (activo.getCodigo().equalsIgnoreCase(codigo)) {
                return activo;
            }
        }

        return null;
    }

    /** Cuenta la cantidad de activos crticos registrados.
      Si es mayor a 8 el activo se convierte en critico*/

    public int contarActivosCriticos() {
        int contador = 0;

        for (ActivoDigital activo : activos) {
            if (activo.getNivelRiesgo() >= 8) {
                contador++;
            }
        }

        return contador;
    }
    /**Calcula el promedio de riesgo de todos los activos registrados.*/

    public double calcularPromedioRiesgo() {
        if (activos.isEmpty()) {
            return 0;
        }

        int suma = 0;

        for (ActivoDigital activo : activos) {
            suma += activo.getNivelRiesgo();
        }

        return (double) suma / activos.size();
    }

    /**plica un parche a un activo digital usando su código.
     Primero se busca el activo. Si existe, se cambia el valor de parcheAplicado a true. */

    public boolean aplicarParcheActivo(String codigo) {
        ActivoDigital activo = buscarPorCodigo(codigo);

        if (activo == null) {
            return false;
        }

        activo.setParcheAplicado(true);
        return true;
    }
    /**Obtiene la cantidad total de activos registrados.*/

    public int obtenerCantidadActivos() {
        return activos.size();
    }
    /**Devuelve la lista completa de activos digitales.*/

    public ArrayList<ActivoDigital> obtenerActivos() {
        return activos;
    }
    /**Reinicia la lista de activos digitales.*/

    public void reiniciar() {
        activos = new ArrayList<>();
    }
}
