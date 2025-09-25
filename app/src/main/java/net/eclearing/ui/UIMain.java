package net.eclearing.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIMain extends JPanel implements ActionListener {
    static JTextField inputField;
    static CustomButton searchButton;
    static CustomButton exportButton;
    static CustomButton unusedButton;
    static JEditorPane viewField;
    Color componentBG = new Color(0x9898B8);
    Color componentBorder = new Color(0x5C5C70);

    ImageIcon searchIco = new ImageIcon("app/Icons/searchIco.png");

    UIMain() { //Create, position and add UI Elements
        //Grid stuff for all components
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(2,2,2,2);

        //Make search bar
        inputField = new JTextField();
        inputField.setBorder(BorderFactory.createLineBorder(componentBorder, 2));
        inputField.setEditable(true);
        inputField.setPreferredSize(new Dimension(700,20));
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputField.setBackground(componentBG);

        //Make Buttons
        searchButton = new CustomButton("");
        searchButton.setPreferredSize(new Dimension(20,20));
        searchButton.setIcon(searchIco);

        exportButton = new CustomButton("Export");
        exportButton.setPreferredSize(new Dimension(80,20));

        unusedButton = new CustomButton("Placeholder");
        unusedButton.setPreferredSize(new Dimension(80,20));

        //Make preview field
        viewField = new JEditorPane();
        viewField.setEditable(false);
        viewField.setBorder(BorderFactory.createLineBorder(componentBorder, 2));
        viewField.setText("Chat Preview will appear here");
        viewField.setPreferredSize(new Dimension(690,390));
        viewField.setBackground(componentBG);


        searchButton.addActionListener(this);
        exportButton.addActionListener(this);
        unusedButton.addActionListener(this);


        searchButton.setActionCommand("Find");
        exportButton.setActionCommand("Export");
        unusedButton.setActionCommand("Placeholder");


        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(inputField,gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(searchButton,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(exportButton,gbc);

        gbc.insets = new Insets(2,85,2,85);
        add(unusedButton,gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(10,0,10,0);
        gbc.gridwidth = 2;
        add(viewField, gbc);

    }

    public void actionPerformed(ActionEvent e){
        switch (e.getActionCommand()){
            case "Find":
                String uInput = inputField.getText();
                //CALL METHOD FOR FINDING A CHAT HERE
            case "Export":
                //CALL METHOD FOR EXPORTING HERE
                break;
        }
    }

    public static void createUI() {
        JFrame window = new JFrame("Michaelsoft Teams Exporter");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        UIMain content = new UIMain();
        content.setBackground(new Color(0x302E40));
        window.setContentPane(content);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setSize(750,520);
        window.setResizable(false);

    }

    public static JTextField getInputField() {
        return inputField;
    }

    public static void main(String[] args) {createUI();}
}