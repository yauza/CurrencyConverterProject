package Classes;

import Interfaces.ICurrencyConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class CurrencyTable implements ICurrencyConverter {
    public SortedSet<Currency> table = new TreeSet<>();

    public CurrencyTable(){ }

    public CurrencyTable(TreeSet<Currency> table){
        this.table = table;
    }

    public String toString(){
        System.out.println("---------------------------------------------");
        for(Currency c : table){
            System.out.printf("|%-20s | %-10s | %-6s |\n", c.getName(), c.getCurrencyCode(), c.getCurrentValue());
            System.out.println("---------------------------------------------");
        }
        return "";
    }

    public void addCurrency(Currency currency){
        table.add(currency);
    }

    @Override
    public double buyCurrency(Currency toBuy, Currency toSell, double amount) {
        return round(toBuy.getCurrentValue() * amount / toSell.getCurrentValue(), 4);
    }

    private static double round(double value, int places){
        if(places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
