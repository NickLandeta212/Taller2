import ec.edu.poo.ActivoDigital;
import ec.edu.poo.Firewall;
import ec.edu.poo.GestorActivos;
import ec.edu.poo.Servidor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {
    private JTabbedPane tabbedPane1;
    private JPanel Ventana;
    private JPanel Gestoractivos;
    private JPanel Resultados;
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
    private JButton btnReiniciar;
    private JTextArea textArea1;
    private JButton btnCriticos;

    private GestorActivos gestor = new GestorActivos();

    public Ventana() {

        SpinnerNumberModel modeloRiesgo = new SpinnerNumberModel(1, 1, 10, 1);
        spiNivelRiesgo.setModel(modeloRiesgo);

        SpinnerNumberModel modeloReglas = new SpinnerNumberModel(0, 0, 1000, 1);
        spiReglasActivas.setModel(modeloReglas);

        textArea1.setEditable(false);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String tipo = cboTipo.getSelectedItem().toString();
                String codigo = txtCodigo.getText();
                String nombre = txtNombre.getText();
                int nivelRiesgo = (Integer) spiNivelRiesgo.getValue();
                boolean parcheAplicado = chkParche.isSelected();

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
                                    parcheAplicado,
                                    sistemaOperativo
                            );

                            if (gestor.registrarActivo(activo) == false) {

                                JOptionPane.showMessageDialog(null, "No se puede registrar. El código ya existe.");

                            } else {

                                JOptionPane.showMessageDialog(null, "Servidor registrado correctamente.");

                                textArea1.setText("ACTIVO REGISTRADO\n\n" + activo.toString());

                                limpiarCampos();

                                tabbedPane1.setSelectedIndex(1);
                            }
                        }

                    } else {

                        int reglasActivas = (Integer) spiReglasActivas.getValue();

                        activo = new Firewall(
                                codigo,
                                nombre,
                                nivelRiesgo,
                                parcheAplicado,
                                reglasActivas
                        );

                        if (gestor.registrarActivo(activo) == false) {

                            JOptionPane.showMessageDialog(null, "No se puede registrar. El código ya existe.");

                        } else {

                            JOptionPane.showMessageDialog(null, "Firewall registrado correctamente.");

                            textArea1.setText("ACTIVO REGISTRADO\n\n" + activo.toString());

                            limpiarCampos();

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

                        textArea1.setText("ACTIVO ENCONTRADO\n\n" + activo.toString());

                        tabbedPane1.setSelectedIndex(1);

                    } else {

                        JOptionPane.showMessageDialog(null, "No existe un activo con ese código.");
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

                        ActivoDigital activo = gestor.buscarPorCodigo(codigo);

                        JOptionPane.showMessageDialog(null, "Parche aplicado correctamente.");

                        textArea1.setText("ACTIVO ACTUALIZADO\n\n" + activo.toString());

                        tabbedPane1.setSelectedIndex(1);

                    } else {

                        JOptionPane.showMessageDialog(null, "No existe un activo con ese código.");
                    }
                }
            }
        });

        btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gestor.reiniciar();

                limpiarCampos();

                textArea1.setText("La lista de activos fue reiniciada correctamente.");

                tabbedPane1.setSelectedIndex(1);

                JOptionPane.showMessageDialog(null, "Sistema reiniciado.");
            }
        });
        btnCriticos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCriticos.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int cantidad = gestor.contarActivosCriticos();

                        textArea1.setText(
                                "ACTIVOS CRÍTICOS\n\n" +
                                        "Cantidad de activos críticos: " + cantidad + "\n\n" +
                                        "Un activo crítico es aquel que tiene nivel de riesgo mayor o igual a 8."
                        );

                        tabbedPane1.setSelectedIndex(1);
                    }
                });
            }
        });
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
        frame.setSize(750, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
