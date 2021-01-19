package UI;

import Classes.Currency;
import Classes.CurrencyTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PlotButtonsPanel extends JPanel {
    private List<Currency> valueHist;
    private PlotPanel pp;
    private CurrencyTable cT;

    private JComboBox currencyToPlot;
    private JComboBox yearOrMonth;
    private JComboBox whichYearBox;
    private JComboBox whichMonthBox;

    private JLabel title;

    private JButton draw;

    public PlotButtonsPanel(CurrencyTable cT){
        this.cT = cT;
        this.setBackground(new Color(108, 121, 121, 50));
        this.setSize(new Dimension(800, 600));

        String [] currencies = cT.getListOfCurrencies();
        String [] plotType = {"Roczny", "Miesieczny"};
        String [] whichYear = {"2020", "2019", "2018", "2017", "2016"};
        String [] whichMonth = {"Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień" };

        //-------------------------Combo boxes----------------------

        currencyToPlot = new JComboBox(currencies);
        Dimension size1 = currencyToPlot.getPreferredSize();
        currencyToPlot.setBounds(550, 40, size1.width, size1.height);

        yearOrMonth = new JComboBox(plotType);
        Dimension size2 = yearOrMonth.getPreferredSize();
        yearOrMonth.setBounds(600, 100, size2.width, size2.height);

        whichYearBox = new JComboBox(whichYear);
        Dimension size3 = whichYearBox.getPreferredSize();
        whichYearBox.setBounds(600, 160, size2.width, size2.height);

        whichMonthBox = new JComboBox(whichMonth);
        Dimension size4 = whichMonthBox.getPreferredSize();
        whichMonthBox.setBounds(600, 220, size4.width, size4.height);

        //-------------------------Labels--------------------------

        title = new JLabel("Wykresy walut");
        title.setFont(title.getFont().deriveFont(18.0f));
        Dimension size5 = title.getPreferredSize();
        title.setBounds(315, 5, 200, size5.height);

        //-------------------------Buttons--------------------------

        draw = new JButton("Rysuj");
        Dimension sizeb = draw.getPreferredSize();
        draw.setBounds(610, 280, sizeb.width, sizeb.height);
        draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPlot();
            }
        });

        this.setLayout(null);
        this.add(currencyToPlot);
        this.add(yearOrMonth);
        this.add(whichYearBox);
        this.add(whichMonthBox);
        this.add(title);
        this.add(draw);
    }

    private void drawPlot(){
        pp.repaint();
    }

//    public void paintComponent(Graphics g){
//        super.paintComponent(g);
//        if(getPlotType()){
//            g.drawLine(10, 790, 10, 600);
//        }
//        g.drawLine(20, 790, 20, 600);
//
//    }

    public boolean getPlotType(){
        if(((String) yearOrMonth.getSelectedItem()).equals("Roczny")) return true;
        else return false;
    }

    public String getWhichYear(){
        return (String) whichYearBox.getSelectedItem();
    }

    public String getWhichMonth(){
        return (String) whichMonthBox.getSelectedItem();
    }

    public void setPP(PlotPanel pp){
        this.pp = pp;
    }

}
