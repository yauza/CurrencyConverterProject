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
        //JPanel panel = new JPanel();
        //panel.setBounds(0, 200, 800, 600);
        //panel.setBackground(new Color(186, 46, 46));
        frame.getContentPane();

        AppConverterPanel acp = new AppConverterPanel(this.currencyTable);
        acp.setAppWindow(this);
        acp.setSize(new Dimension(800, 200));

        PlotButtonsPanel pbp = new PlotButtonsPanel(this.currencyTable);
        pbp.setBounds(0, 200, 800, 600);

        PlotPanel pp = new PlotPanel(pbp);
        pp.setBounds(10, 350, 450, 450);
        pbp.setPP(pp);

        //panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //panel.add(acp);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        //frame.add(panel);
        frame.add(acp);
        frame.add(pbp);
        frame.add(pp);
        frame.setSize(800, 800);
        //frame.setPreferredSize(new Dimension(800, 800));
        frame.setVisible(true);
    }


}
