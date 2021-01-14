package UI;
import Classes.*;
import javax.swing.*;
import java.awt.*;

public class AppWindow extends Component {
    private CurrencyTable currencyTable;

    private JFrame frame;
    //private JPanel panel;

    public AppWindow(CurrencyTable currencyTable){
        this.currencyTable = currencyTable;

        frame = new JFrame("Currency Converter");
        JPanel panel = new JPanel();
        panel.setBounds(0, 400, 800, 400);
        panel.setBackground(new Color(186, 46, 46));
        frame.getContentPane();

        AppConverterPanel acp = new AppConverterPanel(this.currencyTable);
        acp.setAppWindow(this);
        acp.setSize(new Dimension(400, 400));

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //panel.add(acp);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.add(acp);
        frame.setSize(800, 800);
        //frame.setPreferredSize(new Dimension(800, 800));
        frame.setVisible(true);
    }


}
