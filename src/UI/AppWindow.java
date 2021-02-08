package UI;
import Classes.*;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class AppWindow extends Component {
    private CurrencyTable currencyTable;
    private DataLoader dataLoader;

    private JFrame frame;
    //private JPanel panel;

    public AppWindow(CurrencyTable currencyTable, DataLoader dataLoader){
        this.currencyTable = currencyTable;
        this.dataLoader = dataLoader;

        frame = new JFrame("Currency Converter");
        //JPanel panel = new JPanel();
        //panel.setBounds(0, 200, 800, 600);
        //panel.setBackground(new Color(186, 46, 46));
        frame.getContentPane();

        AppConverterPanel acp = new AppConverterPanel(this.currencyTable);
        acp.setAppWindow(this);
        acp.setSize(new Dimension(800, 200));

        PlotButtonsPanel pbp = new PlotButtonsPanel(this.currencyTable, this.dataLoader);
        pbp.setBounds(0, 200, 800, 600);

        LinkedList<Double> l = new LinkedList();
        //{4.3, 4.29, 4.279, 4.277, 4.272, 4.283, 4.288, 4.285, 4.284, 4.274, 4.265, 4.26, 4.264, 4.263, 4.26, 4.255, 4.262, 4.262, 4.258, 4.258};
//        l.add(4.3);
//        l.add(4.29);
//        l.add(4.279);
//        l.add(4.277);
//        l.add(4.272);
//        l.add(4.283);
//        l.add(4.288);
//        l.add(4.285);
//        l.add(4.284);
//        l.add(4.274);
//        l.add(4.265);
//        l.add(4.26);
        PlotPanel pp = new PlotPanel(pbp, l, false);
        pp.setBounds(50, 280, 450, 450);
        pbp.setPP(pp);
        pp.setYearGraph(true);

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
