package UI;

import Classes.Currency;
import Classes.CurrencyTable;
import Classes.DataLoader;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
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

    private DataLoader dl;

    public PlotButtonsPanel(CurrencyTable cT, DataLoader dl){
        this.cT = cT;
        this.dl = dl;
        this.setBackground(new Color(108, 121, 121, 50));
        this.setSize(new Dimension(800, 600));

        String [] currencies = cT.getListOfCurrencies();
        String [] plotType = {"Roczny", "Miesieczny"};
        String [] whichYear = {"2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003"};
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
                try {
                    drawPlot();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (SAXException saxException) {
                    saxException.printStackTrace();
                } catch (ParserConfigurationException parserConfigurationException) {
                    parserConfigurationException.printStackTrace();
                }
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

    private void drawPlot() throws IOException, SAXException, ParserConfigurationException {
        boolean flag = getPlotType();
        pp.setYearGraph(flag);
        LinkedList<Double> list = new LinkedList<>();
        list = dl.getYearMonthData(getWhichCurr(), getWhichYear(), flag, getWhichMonth());
        pp.setValues(list);
        pp.setVis(true);
        pp.repaint();
    }

    public boolean getPlotType(){
        if(((String) yearOrMonth.getSelectedItem()).equals("Roczny")) return true;
        else return false;
    }

    public String getWhichCurr(){ return (String) currencyToPlot.getSelectedItem(); }

    public String getWhichYear(){
        return (String) whichYearBox.getSelectedItem();
    }

    public String getWhichMonth(){
        switch((String)whichMonthBox.getSelectedItem()){
            case "Styczeń":
                return "01";
            case "Luty":
                return "02";
            case "Marzec":
                return "03";
            case "Kwiecień":
                return "04";
            case "Maj":
                return "05";
            case "Czerwiec":
                return "06";
            case "Lipiec":
                return "07";
            case "Sierpień":
                return "08";
            case "Wrzesień":
                return "09";
            case "Październik":
                return "10";
            case "Listopad":
                return "11";
            case "Grudzień":
                return "12";
            default:
                return "";
        }
    }

    public void setPP(PlotPanel pp){
        this.pp = pp;
    }

}
