import Classes.Currency;
import Classes.CurrencyTable;

public class Application {
    public static void main(String [] args){
        Currency dollar = new Currency("dolar amerykański", "1 USD", 3.6998);
        Currency euro = new Currency("euro", "1 EUR", 4.5325);
        Currency bat = new Currency("bat (Tajlandia)", "1 THB", 0.1237);
        CurrencyTable table = new CurrencyTable();
        table.addCurrency(dollar);
        table.addCurrency(euro);
        table.addCurrency(bat);
        table.toString();
        System.out.println(table.buyCurrency(euro, dollar, 10));
        System.out.println(table.buyCurrency(dollar, euro, 10));
    }
}
