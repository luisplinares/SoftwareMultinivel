package com.loyder.software.views;

import java.text.ParseException;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Luis Perez
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form Ventana
     * @throws java.text.ParseException
     */
    
    private final IncomesView incomesView;
    private final UsersView usersView;
    private final SalesView salesView;
    private final BonusesView bonusesView;
    private final ProductsView productsView;
    private final PercentagesView percentagesView;
    private final CategoriesView categoriesView;
    
    private final UserInfoView userInfoView;
    private final SaleInfoView saleInfoView;
    private final UserPurchasesView userPurchasesView;
    
    private final UserRegisterView registerUserView;
    private final SaleRegisterView registerSaleView;
    
    private final MultiLevelNetworkDrawer multiLevelNetworkDrawer;
    
    public MainWindow() throws ParseException {
        initComponents();
        
        this.incomesView = new IncomesView();
        this.usersView = new UsersView(this.usersPanel);
        this.salesView = new SalesView(this.salesPanel);
        this.bonusesView = new BonusesView();
        this.productsView = new ProductsView();
        this.categoriesView = new CategoriesView(this.usersView);
        this.percentagesView = new PercentagesView();
        
        this.saleInfoView = new SaleInfoView(this.salesPanel);
        this.userInfoView = new UserInfoView(this.usersPanel);
        this.userPurchasesView = new UserPurchasesView(this.usersPanel, this.salesPanel, this.saleInfoView, this.jTabbedPane1);
        
        this.registerUserView = new UserRegisterView(this.usersPanel);
        this.registerSaleView = new SaleRegisterView(this.salesPanel);
        
        this.multiLevelNetworkDrawer = new MultiLevelNetworkDrawer(this.usersPanel);
        
        this.incomesPanel.add(this.incomesView);
        
        this.usersPanel.add(this.usersView, UsersView.class.getName());
        this.usersPanel.add(this.userInfoView, UserInfoView.class.getName());
        this.usersPanel.add(this.registerUserView, UserRegisterView.class.getName());
        this.usersPanel.add(this.multiLevelNetworkDrawer, MultiLevelNetworkDrawer.class.getName());
        this.usersPanel.add(this.userPurchasesView, UserPurchasesView.class.getName());
        
        this.salesPanel.add(this.salesView, SalesView.class.getName());
        this.salesPanel.add(this.saleInfoView, SaleInfoView.class.getName());
        this.salesPanel.add(this.registerSaleView, SaleRegisterView.class.getName());
        
        this.percentagesPanel.add(this.percentagesView);
        
        this.categoriesPanel.add(this.categoriesView);
        
        this.bonusesPanel.add(this.bonusesView);
        
        this.productsPanel.add(this.productsView);
        
        //Card Layout Views
        
        this.usersView.setRecommenderInfoView(userInfoView);
        this.usersView.setRegisterRecommenderView(registerUserView);
        this.usersView.setMultiLevelNetworkDrawer(multiLevelNetworkDrawer);
        this.usersView.setUserPurchasesView(userPurchasesView);
        
        this.salesView.setSaleInfoView(saleInfoView);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        usersPanel = new javax.swing.JPanel();
        salesPanel = new javax.swing.JPanel();
        productsPanel = new javax.swing.JPanel();
        categoriesPanel = new javax.swing.JPanel();
        percentagesPanel = new javax.swing.JPanel();
        bonusesPanel = new javax.swing.JPanel();
        incomesPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Software Multinivel");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new java.awt.Dimension(600, 400));

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setToolTipText("");

        usersPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        usersPanel.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Usuarios", usersPanel);

        salesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ventas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        salesPanel.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Ventas", salesPanel);

        productsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        productsPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Productos", productsPanel);

        categoriesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Categorías", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        categoriesPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Categorías", categoriesPanel);

        percentagesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Porcentajes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        percentagesPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Porcentajes", percentagesPanel);

        bonusesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bonificaciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        bonusesPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Bonificaciones", bonusesPanel);

        incomesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingresos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        incomesPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Ingresos", incomesPanel);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bonusesPanel;
    private javax.swing.JPanel categoriesPanel;
    private javax.swing.JPanel incomesPanel;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel percentagesPanel;
    private javax.swing.JPanel productsPanel;
    private javax.swing.JPanel salesPanel;
    private javax.swing.JPanel usersPanel;
    // End of variables declaration//GEN-END:variables
}
