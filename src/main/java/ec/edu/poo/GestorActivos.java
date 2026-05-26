package ec.edu.poo;

import java.util.ArrayList;

public class GestorActivos {

    private ArrayList<ActivoDigital> activos;

    public GestorActivos() {
        activos = new ArrayList<>();
    }

    public boolean registrarActivo(ActivoDigital activo) {
        if (buscarPorCodigo(activo.getCodigo()) != null) {
            return false;
        }

        activos.add(activo);
        return true;
    }

    public ActivoDigital buscarPorCodigo(String codigo) {
        for (ActivoDigital activo : activos) {
            if (activo.getCodigo().equalsIgnoreCase(codigo)) {
                return activo;
            }
        }

        return null;
    }
    
    public int contarActivosCriticos() {
        int contador = 0;

        for (ActivoDigital activo : activos) {
            if (activo.getNivelRiesgo() >= 8) {
                contador++;
            }
        }

        return contador;
    }

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

    public boolean aplicarParcheActivo(String codigo) {
        ActivoDigital activo = buscarPorCodigo(codigo);

        if (activo == null) {
            return false;
        }

        activo.setParcheAplicado(true);
        return true;
    }

    public int obtenerCantidadActivos() {
        return activos.size();
    }

    public ArrayList<ActivoDigital> obtenerActivos() {
        return activos;
    }

    public void reiniciar() {
        activos = new ArrayList<>();
    }
}
