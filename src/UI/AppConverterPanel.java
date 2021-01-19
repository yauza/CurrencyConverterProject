package UI;

import Classes.Currency;
import Classes.CurrencyTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.SortedSet;
import java.util.TreeSet;

public class AppConverterPanel extends JPanel implements ActionListener{

    private AppWindow appWindow;

    private CurrencyTable cT;

    private JComboBox toBuy;
    private JComboBox toSell;

    private JLabel toBuyLabel;
    private JLabel toSellLabel;

    private JTextField toBuyText;
    private JTextField toSellText;

    private JButton convertButton;

    private JLabel title;

    public AppConverterPanel(CurrencyTable cT){
        this.cT = cT;
        //this.setLayout(null);
        this.setBackground(new Color(144, 143, 143, 255));
        this.setSize(new Dimension(800, 200));
        String [] currencies = cT.getListOfCurrencies();

        //-------------------------Combo boxes----------------------

        toBuy = new JComboBox(currencies);
        Dimension size1 = toBuy.getPreferredSize();
        toBuy.setBounds(450, 50, size1.width, size1.height);

        toSell = new JComboBox(currencies);
        Dimension size2 = toSell.getPreferredSize();
        toSell.setBounds(100, 50, size2.width, size2.height);

        //-------------------------Labels---------------------------

        toBuyLabel = new JLabel("Co chce:");
        Dimension size3 = toBuyLabel.getPreferredSize();
        toBuyLabel.setBounds(400, 100, 100, size3.height);

        toSellLabel = new JLabel("Co mam:");
        Dimension size4 = toSellLabel.getPreferredSize();
        toSellLabel.setBounds(50, 100, 100, size4.height);

        title = new JLabel("Przelicznik walut");
        title.setFont(title.getFont().deriveFont(18.0f));
        Dimension sizet = title.getPreferredSize();
        title.setBounds(300, 5, 200, sizet.height);

        //-------------------------Text fields---------------------

        toBuyText = new JTextField(9);
        Dimension size5 = toBuyText.getPreferredSize();
        toBuyText.setBounds(470, 100, size5.width, size5.height);

        toSellText = new JTextField(9);
        Dimension size6 = toSellText.getPreferredSize();
        toSellText.setBounds(120, 100, size6.width, size6.height);

        //--------------------------Button--------------------------

        convertButton = new JButton("Przelicz");
        Dimension size7 = convertButton.getPreferredSize();
        convertButton.setBounds(330, 150, 100, size7.height);
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setValuesInTextFields();
            }
        });

        this.setLayout(null);
        this.add(toBuy);
        this.add(toSell);
        this.add(toBuyLabel);
        this.add(toSellLabel);
        this.add(toBuyText);
        this.add(toSellText);
        this.add(convertButton);
        this.add(title);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //JComboBox cb = (JComboBox)e.getSource();
        //String petName = (String)cb.getSelectedItem();
        //updateLabel(petName);
    }

//    private String [] getListOfCurrencies(){
//        SortedSet<Currency> ts = this.cT.getTable();
//        String [] res = new String[ts.size()];
//        int i = 0;
//        for(Currency c : ts){
//            res[i] = c.toString();
//            i++;
//        }
//
//        return res;
//    }

    private void setValuesInTextFields(){
        if(toBuyText.getText().equals("") && toSellText.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Uzupełnij jedną z komórek!");
        }else if(toBuyText.getText().equals("")){
            Currency toSellCurr = cT.findInTable((String) toSell.getSelectedItem());
            Currency toBuyCurr = cT.findInTable((String) toBuy.getSelectedItem());
            toBuyText.setText(String.valueOf(cT.sellCurrency(toSellCurr, Double.parseDouble(toSellText.getText()), toBuyCurr)));
        }else if(toSellText.getText().equals("")){
            Currency toSellCurr = cT.findInTable((String) toSell.getSelectedItem());
            Currency toBuyCurr = cT.findInTable((String) toBuy.getSelectedItem());
            toSellText.setText(String.valueOf(cT.buyCurrency(toBuyCurr, Double.parseDouble(toBuyText.getText()), toSellCurr)));
        }else{
            Currency toSellCurr = cT.findInTable((String) toSell.getSelectedItem());
            Currency toBuyCurr = cT.findInTable((String) toBuy.getSelectedItem());
            toBuyText.setText(String.valueOf(cT.sellCurrency(toSellCurr, Double.parseDouble(toSellText.getText()), toBuyCurr)));
        }
    }

    public void setAppWindow(AppWindow appWindow) {
        this.appWindow = appWindow;
    }
}
