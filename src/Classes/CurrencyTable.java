package Classes;

import Interfaces.ICurrencyConverter;

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
        return toBuy.getCurrentValue() * amount / toSell.getCurrentValue();
    }
}
