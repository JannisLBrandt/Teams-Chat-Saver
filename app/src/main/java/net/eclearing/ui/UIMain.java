package net.eclearing.ui;

import net.eclearing.controller.ChatController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UIMain extends JPanel implements ActionListener {
    static JTextField inputField;
    static CustomButton searchButton;
    static CustomButton exportButton;
    static CustomButton clearButton;
    static CustomButton darkModeButton;
    static JTextArea viewField;
    static boolean dmActive = false;
    Color componentDarkBG = new Color(0x141420);
    Color componentBG = new Color(0x9898B8);
    Color componentBorder = new Color(0x5C5C70);

    ImageIcon searchIco = new ImageIcon("app/Icons/searchIco.png");

    public UIMain() { //Create, position and add UI Elements
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

        clearButton = new CustomButton("Clear");
        clearButton.setPreferredSize(new Dimension(80,20));

        darkModeButton = new CustomButton("Dark Mode");
        darkModeButton.setPreferredSize(new Dimension(100,20));
        //Make preview field
        viewField = new JTextArea();
        viewField.setEditable(false);
        viewField.setBorder(BorderFactory.createLineBorder(componentBorder, 2));
        viewField.setText("Chat Preview \n");
        //viewField.setPreferredSize(new Dimension(690,390));
        viewField.setBackground(componentBG);

        JScrollPane scrollPane = new JScrollPane(viewField);
        scrollPane.setPreferredSize(new Dimension(690,390));

        //assign action listeners and commands
        searchButton.addActionListener(this);
        exportButton.addActionListener(this);
        clearButton.addActionListener(this);
        darkModeButton.addActionListener(this);

        searchButton.setActionCommand("Find");
        exportButton.setActionCommand("Export");
        clearButton.setActionCommand("Clear");
        darkModeButton.setActionCommand("ToggleDM");

        //Change layout of the ui components (probably the worst layout making ever cause GridBagLayout is weird idk)
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
        add(clearButton,gbc);

        gbc.insets = new Insets(2,168,2,168);
        add(darkModeButton,gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(10,0,10,0);
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

    }

    public void actionPerformed(ActionEvent e){
        switch (e.getActionCommand()){
            case "Find":
                String uInput = inputField.getText();
                ChatController controller = null;
                try {
                    controller = new ChatController(uInput);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                controller.placeHolder(uInput);

                //CALL METHOD FOR FINDING A CHAT HERE
            case "Export":
                String uInputExport= inputField.getText();
                ChatController controllerExport = null;
                try {
                    controller = new ChatController(uInputExport);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                controller.saveToFile();
                break;
            case "Clear":
                viewField.setText("");
                break;
            case "ToggleDM":
                if (!dmActive){
                    dmActive = true;
                    viewField.setBackground(componentDarkBG);
                    viewField.setForeground(Color.WHITE);
                    darkModeButton.setText("Light Mode");
                }else {
                    dmActive = false;
                    viewField.setBackground(componentBG);
                    viewField.setForeground(Color.BLACK);
                    darkModeButton.setText("Dark Mode");
                }
        }
    }

    //Do all the things to make the ui appear and work idk
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
        window.setIconImage(new ImageIcon("app/Icons/appIco.png").getImage());

    }

    public static JTextField getInputField() {
        return inputField;
    }

    public static void updateUI(String date, String user, String message){
        viewField.append("(" + date + ") " + user + ": " + message + "\n");
    }
    public static void badLink(String link, String errorMessage) {
        viewField.setText("An error has occured: " + errorMessage + " (" + link + ")");
    }

    /*FOR TESTING
    public static void main(String[] args) {createUI();}
     */
}