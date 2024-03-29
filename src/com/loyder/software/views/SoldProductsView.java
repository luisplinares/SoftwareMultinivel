/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loyder.software.views;

import com.loyder.software.main.ApplicationStarter;
import com.loyder.software.model.dao.config.DatabaseConfig.SaleState;
import com.loyder.software.model.dao.config.DatabaseConfig.SaleType;
import com.loyder.software.model.dao.config.DatabaseConnection;
import com.loyder.software.model.entities.Sale;
import com.loyder.software.model.entities.User;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Redes
 */
public class SoldProductsView extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane tableScroll;
    private JPanel searchPane;
    private JPanel searchPaneFlowLayout;
    private JPanel searchPaneFlowLayoutGridLayout;
    private JPanel footer;
    private JPanel footerBoxLayout;
    private JPanel footerFlowLayoutTotalSales;
    private JPanel goBackPanel;
    private JLabel idSearchLabel;
    private JLabel dateSearchLabel;
    private JButton dateSearchButton;
    private JButton idSearchButton;
    private JButton buttonShowAllSales;
    private JFormattedTextField idTextField;
    private JFormattedTextField date1TextField;
    private JFormattedTextField date2TextField;
    private JPanel datesPane;

    private JLabel totalSalesLabel;
    private JLabel totalSales;
    private JButton goBackButton;

    private static final String[] TABLE_MODEL_IDENTIFIERS = {"Código Producto", "Nombre Producto", "Unidades Vendidas", "Precio Unidad", "Total"};
    private static final String[][] EMPTY_TABLE = new String[0][0];

    private final JPanel panelParent;

    public SoldProductsView(JPanel panelParent1) throws ParseException {
        initComponents();
        this.panelParent = panelParent1;
        dateSearchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                soldProductSearchByDateRange(e);
            }
        });
        idSearchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                soldProductSearchById(e);
            }
        });
        buttonShowAllSales.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showAllSoldProducts(e);
            }
        });

        this.goBackButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ((CardLayout) panelParent.getLayout()).show(panelParent, SalesView.class.getName());
            }
        });

    }

    public void soldProductSearchById(MouseEvent e) {
        Long id = (Long) idTextField.getValue();
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Código digitado inválido.");
            return;
        }

        idTextField.setText("");
        idTextField.setValue(null);
        
        Double totalSalesSum = 0d;
        ArrayList<Sale.SoldProduct> sales = DatabaseConnection.getSaleDao().getSoldProductById(id);
        Object[][] data = new Object[sales.size()][5];
        if (sales != null && !sales.isEmpty()) {
            for (int i = 0; i < sales.size(); i++) {
                
                Double totalSold = sales.get(i).getQuantity()*sales.get(i).getPrice();
                
                data[i][0] = sales.get(i).getProductId();
                data[i][1] = sales.get(i).getProductName();
                data[i][2] = sales.get(i).getQuantity();
                data[i][3] = ApplicationStarter.CURRENCY_FORMAT.format(sales.get(i).getPrice());
                data[i][4] = ApplicationStarter.CURRENCY_FORMAT.format(totalSold);

                totalSalesSum += totalSold;
            }

            totalSales.setText(ApplicationStarter.CURRENCY_FORMAT.format(totalSalesSum));
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron resultados.");
            totalSales.setText("");
        }
        tableModel.setDataVector(data, TABLE_MODEL_IDENTIFIERS);

    }

    public void soldProductSearchByDateRange(MouseEvent e) {
        Date date1 = ((Date) date1TextField.getValue());
        Date date2 = ((Date) date2TextField.getValue());
        if (date1 == null || date2 == null) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto.");
            return;
        }

        date1TextField.setValue(null);
        date2TextField.setValue(null);
        date1TextField.setText("");
        date2TextField.setText("");

        Double totalSalesSum = 0d;
        ArrayList<Sale.SoldProduct> sales = DatabaseConnection.getSaleDao().getAllSoldProductsInDateRange(date1.getTime(), date2.getTime());
        Object[][] data = new Object[sales.size()][5];
        if (sales != null && !sales.isEmpty()) {
            for (int i = 0; i < sales.size(); i++) {
                
                Double totalSold = sales.get(i).getQuantity()*sales.get(i).getPrice();
                
                data[i][0] = sales.get(i).getProductId();
                data[i][1] = sales.get(i).getProductName();
                data[i][2] = sales.get(i).getQuantity();
                data[i][3] = ApplicationStarter.CURRENCY_FORMAT.format(sales.get(i).getPrice());
                data[i][4] = ApplicationStarter.CURRENCY_FORMAT.format(totalSold);

                totalSalesSum += totalSold;
            }

            totalSales.setText(ApplicationStarter.CURRENCY_FORMAT.format(totalSalesSum));
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron resultados.");
            totalSales.setText("");
        }
        tableModel.setDataVector(data, TABLE_MODEL_IDENTIFIERS);

    }

    public void showAllSoldProducts(MouseEvent e) {
        Double totalSalesSum = 0d;

        ArrayList<Sale.SoldProduct> sales = DatabaseConnection.getSaleDao().getAllSoldProducts();
        Object[][] data = new Object[sales.size()][5];
        if (sales != null && !sales.isEmpty()) {
            for (int i = 0; i < sales.size(); i++) {
                
                Double totalSold = sales.get(i).getQuantity()*sales.get(i).getPrice();
                
                data[i][0] = sales.get(i).getProductId();
                data[i][1] = sales.get(i).getProductName();
                data[i][2] = sales.get(i).getQuantity();
                data[i][3] = ApplicationStarter.CURRENCY_FORMAT.format(sales.get(i).getPrice());
                data[i][4] = ApplicationStarter.CURRENCY_FORMAT.format(totalSold);

                totalSalesSum += totalSold;
            }

            totalSales.setText(ApplicationStarter.CURRENCY_FORMAT.format(totalSalesSum));
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron resultados.");
            totalSales.setText("");
        }
        tableModel.setDataVector(data, TABLE_MODEL_IDENTIFIERS);

    }

    public void initComponents() throws ParseException {
        this.setLayout(new BorderLayout());
        tableModel = new DefaultTableModel(TABLE_MODEL_IDENTIFIERS, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        table = new JTable(tableModel);
        table.setDragEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(400, 400));
        EmptyBorder eb = new EmptyBorder(new Insets(20, 20, 20, 20));
        searchPane = new JPanel(new BorderLayout());
        searchPane.setBorder(eb);
        footer = new JPanel(new BorderLayout());
        footer.setBorder(eb);

        idSearchLabel = new JLabel("Código producto: ");
        idTextField = new JFormattedTextField(0L);
        idTextField.setValue(null);
        idTextField.setPreferredSize(new Dimension(250, 30));
        idSearchButton = new JButton("Buscar");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        date1TextField = new JFormattedTextField(sdf);
        date2TextField = new JFormattedTextField(sdf);
        dateSearchButton = new JButton("Buscar");
        dateSearchLabel = new JLabel("Fecha Inicial (dd/mm/yyyy) - Fecha Final (dd/mm/yyyy): ");

        buttonShowAllSales = new JButton("Mostrar todo");

        searchPaneFlowLayout = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPaneFlowLayoutGridLayout = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        searchPaneFlowLayoutGridLayout.add(idSearchLabel, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 0;
        searchPaneFlowLayoutGridLayout.add(idTextField, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 2;
        gbc.gridy = 0;
        searchPaneFlowLayoutGridLayout.add(idSearchButton, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        searchPaneFlowLayoutGridLayout.add(dateSearchLabel, gbc);
        datesPane = new JPanel();
        BoxLayout bl = new BoxLayout(datesPane, BoxLayout.X_AXIS);
        datesPane.setLayout(bl);
        datesPane.add(date1TextField);
        datesPane.add(date2TextField);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 1;
        searchPaneFlowLayoutGridLayout.add(datesPane, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 2;
        gbc.gridy = 1;
        searchPaneFlowLayoutGridLayout.add(dateSearchButton, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 2;
        searchPaneFlowLayoutGridLayout.add(buttonShowAllSales, gbc);

        searchPaneFlowLayout.add(searchPaneFlowLayoutGridLayout);
        searchPane.add(searchPaneFlowLayout);

        totalSales = new JLabel();
        totalSalesLabel = new JLabel("Total Ventas: ");

        goBackButton = new JButton("Atrás");
        
        goBackPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        goBackPanel.add(goBackButton);
        
        searchPane.add(goBackPanel, BorderLayout.NORTH);

        footerBoxLayout = new JPanel();
        BoxLayout b = new BoxLayout(footerBoxLayout, BoxLayout.X_AXIS);
        footerBoxLayout.setLayout(b);
        footerFlowLayoutTotalSales = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        footerFlowLayoutTotalSales.add(totalSalesLabel);
        footerFlowLayoutTotalSales.add(totalSales);


        footerBoxLayout.add(footerFlowLayoutTotalSales);

        footer.add(footerBoxLayout);

        this.add(this.tableScroll, BorderLayout.CENTER);
        this.add(searchPane, BorderLayout.NORTH);
        this.add(footer, BorderLayout.SOUTH);

    }
}
