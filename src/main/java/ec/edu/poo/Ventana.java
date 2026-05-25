package ec.edu.poo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {

    private JPanel Ventana;
    private JComboBox cboTipo;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JSpinner spiNivelRiesgo;
    private JTextField txtSistemaOperativo;
    private JSpinner spiReglasActivas;
    private JCheckBox chkParche;
    private JButton btnRegistrar;
    private JButton btnBuscar;
    private JButton btnAplicarParche;
    private JButton btnCriticos;
    private JButton btnPromedio;
    private JButton btnListar;
    private JButton btnReiniciar;
    private JButton btnLimpiar;
    private JTabbedPane tabbedPane1;
    private JTextArea areaResultado;

    private GestorActivos gestor = new GestorActivos();

    public Ventana() {

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String tipo = cboTipo.getSelectedItem().toString();
                String codigo = txtCodigo.getText();
                String nombre = txtNombre.getText();
                int nivelRiesgo = (Integer) spiNivelRiesgo.getValue();
                boolean parche = chkParche.isSelected();

                if (codigo.equals("") || nombre.equals("")) {
                    JOptionPane.showMessageDialog(null, "Ingrese código y nombre.");
                } else {

                    ActivoDigital activo;

                    if (tipo.equals("Servidor")) {

                        String sistemaOperativo = txtSistemaOperativo.getText();

                        if (sistemaOperativo.equals("")) {
                            JOptionPane.showMessageDialog(null, "Ingrese el sistema operativo.");
                        } else {

                            activo = new Servidor(
                                    codigo,
                                    nombre,
                                    nivelRiesgo,
                                    parche,
                                    sistemaOperativo
                            );

                            if (gestor.registrarActivo(activo) == false) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Revise el código. No se permiten activos duplicados."
                                );
                            } else {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Servidor registrado correctamente."
                                );

                                limpiarCampos();
                                listar();
                                tabbedPane1.setSelectedIndex(1);
                            }
                        }

                    } else {

                        int reglasActivas = (Integer) spiReglasActivas.getValue();

                        activo = new Firewall(
                                codigo,
                                nombre,
                                nivelRiesgo,
                                parche,
                                reglasActivas
                        );

                        if (gestor.registrarActivo(activo) == false) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Revise el código. No se permiten activos duplicados."
                            );
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Firewall registrado correctamente."
                            );

                            limpiarCampos();
                            listar();
                            tabbedPane1.setSelectedIndex(1);
                        }
                    }
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String codigo = txtCodigo.getText();

                if (codigo.equals("")) {
                    JOptionPane.showMessageDialog(null, "Ingrese el código para buscar.");
                } else {

                    ActivoDigital activo = gestor.buscarPorCodigo(codigo);

                    if (activo != null) {

                        txtCodigo.setText(activo.getCodigo());
                        txtNombre.setText(activo.getNombre());
                        spiNivelRiesgo.setValue(activo.getNivelRiesgo());
                        chkParche.setSelected(activo.isParcheAplicado());

                        if (activo instanceof Servidor) {

                            Servidor servidor = (Servidor) activo;

                            cboTipo.setSelectedItem("Servidor");
                            txtSistemaOperativo.setText(servidor.getSistemaOperativo());
                            spiReglasActivas.setValue(0);

                        } else {

                            Firewall firewall = (Firewall) activo;

                            cboTipo.setSelectedItem("Firewall");
                            txtSistemaOperativo.setText("");
                            spiReglasActivas.setValue(firewall.getReglasActivas());
                        }

                        areaResultado.setText(activo.toString());
                        tabbedPane1.setSelectedIndex(1);

                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "No existe un activo con ese código."
                        );
                    }
                }
            }
        });

        btnAplicarParche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String codigo = txtCodigo.getText();

                if (codigo.equals("")) {
                    JOptionPane.showMessageDialog(null, "Ingrese el código del activo.");
                } else {

                    if (gestor.aplicarParcheActivo(codigo) == true) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Parche aplicado correctamente."
                        );

                        listar();
                        tabbedPane1.setSelectedIndex(1);

                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "No existe un activo con ese código."
                        );
                    }
                }
            }
        });

        btnCriticos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int cantidad = gestor.contarActivosCriticos();

                areaResultado.setText(
                        "ACTIVOS CRÍTICOS\n\n" +
                                "Cantidad de activos críticos: " + cantidad + "\n\n" +
                                "Se considera crítico un activo con nivel de riesgo mayor o igual a 8."
                );

                tabbedPane1.setSelectedIndex(1);
            }
        });

        btnPromedio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double promedio = gestor.calcularPromedioRiesgo();

                areaResultado.setText(
                        "PROMEDIO DE RIESGO\n\n" +
                                "Promedio de riesgo de los activos registrados: " + promedio
                );

                tabbedPane1.setSelectedIndex(1);
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                listar();
                tabbedPane1.setSelectedIndex(1);
            }
        });

        btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gestor.reiniciar();
                limpiarCampos();

                areaResultado.setText("La lista de activos fue reiniciada correctamente.");
                tabbedPane1.setSelectedIndex(1);
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                limpiarCampos();
            }
        });
    }

    private void listar() {

        if (gestor.obtenerCantidadActivos() == 0) {

            areaResultado.setText("No hay activos registrados.");

        } else {

            String texto = "";

            texto += "REPORTE DE ACTIVOS DIGITALES\n\n";
            texto += "Cantidad total de activos: " + gestor.obtenerCantidadActivos() + "\n";
            texto += "Activos críticos: " + gestor.contarActivosCriticos() + "\n";
            texto += "Promedio de riesgo: " + gestor.calcularPromedioRiesgo() + "\n\n";

            texto += "DETALLE DE ACTIVOS\n\n";

            for (ActivoDigital activo : gestor.obtenerActivos()) {
                texto += activo.toString() + "\n\n";
            }

            areaResultado.setText(texto);
        }
    }

    private void limpiarCampos() {

        txtCodigo.setText("");
        txtNombre.setText("");
        spiNivelRiesgo.setValue(1);
        txtSistemaOperativo.setText("");
        spiReglasActivas.setValue(0);
        chkParche.setSelected(false);
        cboTipo.setSelectedIndex(0);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Sistema de Gestión de Activos Digitales");
        frame.setContentPane(new Ventana().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}