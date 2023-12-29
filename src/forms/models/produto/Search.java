/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.models.produto;

import dao.connection.Connection;
import forms.main.MainFrame;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fill
 */
public class Search extends javax.swing.JInternalFrame {

    /**
     * Creates new form Search
     */
    public Search() {
        initComponents();
        search("SELECT codigo,produto,lote,unidade,quantidademin FROM produto");
    }

    //MÉTODO RESPONSÁVEL POR PREENCHER A TABELA COM OS REGISTOS DO BANCO DE DADOS
    protected void search(String sql) {
        String[] colunas = {"Código", "Produto", "Lote", "Unidade", "Quantidade Minima"};

        DefaultTableModel model = new DefaultTableModel(colunas, 0) {

        };
        dao.connection.Connection connection = new Connection();
        try {
            connection.open();

            connection.exec(sql);
            connection.resultSet.first();

            do {
                model.addRow(new Object[]{connection.resultSet.getInt(1), connection.resultSet.getString(2), connection.resultSet.getInt(3), connection.resultSet.getString(4), connection.resultSet.getInt(5)});
            } while (connection.resultSet.next());

        } catch (SQLException ex) {

        } finally {
            connection.close();
        }
        jTable1.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonClose = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jButtonRefresh = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Consultar Produtos");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButtonClose.setText("Fechar");

        jButtonDelete.setText("Deletar");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonEdit.setText("Editar");
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });

        jButtonRefresh.setText("Atualizar");
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed

        search("SELECT codigo,produto,lote,unidade,quantidademin FROM produto");

    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed

        int row = jTable1.getSelectedRow();
        if (row != -1) {
            int codigo = (int) jTable1.getValueAt(row, 0);
            String produto = jTable1.getValueAt(row, 1).toString();
            int lote = (int) jTable1.getValueAt(row, 2);
            String unidade = jTable1.getValueAt(row, 3).toString();
            int quantidade = (int) jTable1.getValueAt(row, 4);

            forms.models.produto.Add add = new Add(codigo, produto, lote, unidade, quantidade);
            forms.main.MainFrame.jDesktopPane1.add(add);
            add.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "SELECIONE UM REGISTRO", "ALERTA", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_jButtonEditActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed

        int row = jTable1.getSelectedRow();
        if (row != -1) {
            int message = JOptionPane.showConfirmDialog(null, "VOCÊ REALMENTE QUER DELETAR O REGISTRO?", "ALERTA", JOptionPane.YES_NO_OPTION);

            if (message == JOptionPane.YES_OPTION) {

                int codigo = (int) jTable1.getValueAt(row, 0);

                dao.connection.Connection connection = new Connection();
                connection.open();

                try {
                    PreparedStatement pst = connection.connection.prepareStatement("DELETE FROM produto WHERE codigo=" + codigo);
                    pst.execute();
                    search("SELECT * FROM produto");
                } catch (SQLException ex) {
                    Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

            }

        } else {
            JOptionPane.showMessageDialog(null, "SELECIONE UM REGISTRO", "ALERTA", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_jButtonDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}