/*
 * MFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lym77.ui;

import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author __USER__
 */
public class MFrame extends JFrame {

    /**
     * Creates new form MFrame
     */
    public MFrame() {
        initComponents();
    }

    public void addMsg(String msg) {
        if (taInfo.getText().length() > 10000) {
            taInfo.setText(null);
        }
        taInfo.append(msg + "\n");
        taInfo.setCaretPosition(taInfo.getText().length());
    }

    public void start() {

    }

    public void setCount(int count) {
        jLabel2.setText(count + "");
    }

    public void setBtnEnable(boolean enable) {
        jButton1.setEnabled(enable);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    //GEN-BEGIN:initComponents
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jScrollPane1 = new JScrollPane();
        taInfo = new JTextArea();
        jButton1 = new JButton();
        jButton2 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("消息服务器");

        jLabel1.setFont(new Font("Microsoft YaHei UI", 0, 18));
        jLabel1.setForeground(new Color(255, 51, 51));
        jLabel1.setText("在线人数：");

        jLabel2.setFont(new Font("Microsoft YaHei UI", 0, 18));
        jLabel2.setForeground(new Color(255, 51, 51));
        jLabel2.setText("0");

        taInfo.setBackground(new Color(255, 255, 255));
        taInfo.setColumns(20);
        taInfo.setRows(5);
        taInfo.setDisabledTextColor(new Color(51, 51, 51));
        taInfo.setEnabled(false);
        jScrollPane1.setViewportView(taInfo);

        jButton1.setText("启动服务");
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        jButton2.setText("推出服务");
        jButton2.addActionListener(evt -> jButton2ActionPerformed(evt));

        GroupLayout layout = new GroupLayout(
                getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(
                                GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(29, 29,
                                                29)
                                        .addGroup(layout.createParallelGroup(
                                                GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel1)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(78, 78, 78)
                                        .addComponent(jButton1)
                                        .addGap(69, 69, 69)
                                        .addComponent(jButton2)))
                        .addContainerGap(37, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(
                                GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jButton1).addComponent(jButton2))
                        .addContainerGap(35, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>
    //GEN-END:initComponents

    private void jButton2ActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        start();
    }

    //GEN-BEGIN:variables
    // Variables declaration - do not modify
    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JTextArea taInfo;
    // End of variables declaration//GEN-END:variables

}