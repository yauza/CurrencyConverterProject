import Classes.Currency;
import Classes.CurrencyTable;
import Classes.DataLoader;
import UI.AppWindow;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Application {
    public static void main(String [] args){
        Currency zloty = new Currency("złoty", 1,"PLN", 1);
        Currency euro = new Currency("euro", 1, "EUR", 4.5325);
        Currency rup = new Currency("rupia indonezyjska", 10000,"IDR", 2.631);
        Currency won = new Currency("won południowokoreański", 100, "KRW", 0.338);
        CurrencyTable table = new CurrencyTable();
//        table.addCurrency(dollar);
//        table.addCurrency(euro);
//        table.addCurrency(bat);
//        table.toString();
//        System.out.println(table.buyCurrency(euro, dollar, 10));
//        System.out.println(table.buyCurrency(dollar, euro, 10));
        DataLoader dl = new DataLoader();
        try {
            dl.loadData("NBP");
            table.updateTable(dl.getListOfCurrencies());

            //System.out.println(dl.getYearData(euro, "2019"));

            //table.toString();
            //System.out.println(table.buyCurrency(euro, 2, zloty));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        AppWindow app = new AppWindow(table, dl);
    }
}
