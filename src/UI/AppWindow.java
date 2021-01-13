package UI;
import Classes.*;
import javax.swing.*;

public class AppWindow {
    private CurrencyTable currencyTable;

    private JFrame frame;
    //private JPanel panel;

    public AppWindow(CurrencyTable currencyTable){
        this.currencyTable = currencyTable;

        frame = new JFrame("Currency Converter");
        JPanel panel = new JPanel();
        frame.getContentPane();



        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }


}
