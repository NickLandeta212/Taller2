package ec.edu.poo;

public class ActivoDigital {

    private String codigo;
    private String nombre;
    private int nivelRiesgo;
    private boolean parcheAplicado;

    public ActivoDigital(String codigo, String nombre, int nivelRiesgo, boolean parcheAplicado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nivelRiesgo = nivelRiesgo;
        this.parcheAplicado = parcheAplicado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(int nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public boolean isParcheAplicado() {
        return parcheAplicado;
    }

    public void setParcheAplicado(boolean parcheAplicado) {
        this.parcheAplicado = parcheAplicado;
    }

    @Override
    public String toString() {
        return "Código: " + codigo +
                ", Nombre: " + nombre +
                ", Nivel de riesgo: " + nivelRiesgo +
                ", Parche aplicado: " + parcheAplicado;
    }
}
