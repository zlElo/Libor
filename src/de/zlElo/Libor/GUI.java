package de.zlElo.Libor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import de.zlElo.Libor.worker.dbWriter;
import de.zlElo.Libor.LibaryView;

public class GUI extends JFrame {
    public GUI() {
        // Set the title for the window
        setTitle("Libor Library Manager");

        // Set the action on closing the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the window
        setSize(500, 230);

        // Initialize the database list
        DefaultListModel<String> dbs = new DefaultListModel<>();

        // Load all files from a directory into the list
        File directory = new File("data/db/");
        Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                .map(File::getName)
                .forEach(dbs::addElement);

        JList<String> listDb = new JList<>(dbs);
        listDb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listDb.setVisibleRowCount(6);
        JScrollPane scrollPane = new JScrollPane(listDb);
        scrollPane.setPreferredSize(new Dimension(400, 150));

        // event for double-click on item in list
        listDb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = listDb.getSelectedIndex();
                    if (selectedIndex != -1) {
                        String selectedItem = dbs.getElementAt(selectedIndex);
                        // debug: System.out.println("Selected item: " + selectedItem);
                        new LibaryView(selectedItem);
                    }
                }
            }
        });

        // Create a label and a button
        JLabel label = new JLabel("Create a new library:");
        JButton button = new JButton("Create");
        button.setPreferredSize(new Dimension(120, 30));

        // Create panels
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();

        // Add components to panels
        panel.add(scrollPane, BorderLayout.CENTER);
        panel2.add(label, BorderLayout.WEST);
        panel2.add(button, BorderLayout.EAST);

        // Add panels to the main window
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(panel2, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setVisible(true);
    }
}