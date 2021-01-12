import Classes.Currency;
import Classes.CurrencyTable;
import Classes.DataLoader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Application {
    public static void main(String [] args){
        Currency dollar = new Currency("dolar amerykański", 1,"USD", 3.6998);
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
            table.toString();
            System.out.println(table.buyCurrency(won, 2, rup));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
