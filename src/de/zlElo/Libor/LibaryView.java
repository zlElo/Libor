package de.zlElo.Libor;
import javax.swing.*;

public class LibaryView extends JFrame {
    public LibaryView(String selectedItem) {
        setTitle("Libary-Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 630);
        System.out.println("Got following item: " + selectedItem);
    }

    public static void main(String[] args) {
        LibaryView gui = new LibaryView(args[0]);
        gui.setVisible(true);
    }
}
