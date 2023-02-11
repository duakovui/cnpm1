/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import models.CSVCModel;
import services.CSVCService;
import utility.ClassTableModel;

/**
 *
 * @author hoan
 */
public class CSVCController {
    private JPanel jpnView;
    private JTextField jtfSearch;
    private CSVCService csvcService;
    private List<CSVCModel> listCSVC;
    private ClassTableModel classTableModel = null;
    private final String[] COLUMNS = {"Loại", "Số lượng", "Trạng thái"};
    private JFrame parentJFrame;
    
    public CSVCController(JPanel jpnView, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.jtfSearch = jtfSearch;
        classTableModel = new ClassTableModel();
        this.csvcService = new CSVCService();
        this.listCSVC = this.csvcService.getListCSVC();
        initAction();
    }
    
    public CSVCController(){
        
    }
    
    public void initAction(){
        this.jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                listCSVC = csvcService.search(key.trim());
                setDataTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                listCSVC = csvcService.search(key.trim());
                setDataTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                listCSVC = csvcService.search(key.trim());
                setDataTable();
            }
        });
    }
    
    public void setDataTable() {
        DefaultTableModel model = classTableModel.setTableCSVC(listCSVC, COLUMNS);
        JTable table = new JTable(model) {
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
            
        };
        
    // thiet ke bang
        
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getColumnModel().getColumn(0).setMaxWidth(80);
        table.getColumnModel().getColumn(0).setMinWidth(80);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scroll);
        jpnView.validate();
        jpnView.repaint();
    }

    public void setParentJFrame(JFrame parentJFrame) {
        this.parentJFrame = parentJFrame;
    }
    
    public void refreshData() {
        this.listCSVC = this.csvcService.getListCSVC();
        setDataTable();
    }
    
    public JPanel getJpnView() {
        return jpnView;
    }

    public void setJpnView(JPanel jpnView) {
        this.jpnView = jpnView;
    }

    public JTextField getJtfSearch() {
        return jtfSearch;
    }

    public void setJtfSearch(JTextField jtfSearch) {
        this.jtfSearch = jtfSearch;
    }
}
