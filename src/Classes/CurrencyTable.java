package Classes;

import Interfaces.ICurrencyConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class CurrencyTable implements ICurrencyConverter {
    private SortedSet<Currency> table = new TreeSet<>();

    public CurrencyTable(){ }

    public CurrencyTable(TreeSet<Currency> table){
        this.table = table;
    }

    public String toString(){
        System.out.println("-------------------------------------------------------------");
        for(Currency c : table){
            System.out.printf("|%-36s | %-10s | %-6s |\n", c.getName(), c.getConversionRate() + " " + c.getCurrencyCode(), c.getCurrentValue());
            System.out.println("-------------------------------------------------------------");
        }
        return "";
    }

    public void addCurrency(Currency currency){
        table.add(currency);
    }

    public void updateTable(List<Currency> list){
        this.table = new TreeSet<>();
        for(Currency c : list){
            table.add(c);
        }
    }


    private static double round(double value, int places){
        if(places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public double buyCurrency(Currency toBuy, double amount, Currency toSell) {
        if(!table.contains(toSell) || !table.contains(toBuy)) throw new IllegalArgumentException("Te waluty nie są obsługiwane!");
        return round((amount * toBuy.getCurrentValue() * toSell.getConversionRate())/ (toSell.getCurrentValue() * toBuy.getConversionRate()), 4);
    }

    @Override
    public double sellCurrency(Currency toSell, double amount, Currency toBuy) {
        if(!table.contains(toSell) || !table.contains(toBuy)) throw new IllegalArgumentException("Te waluty nie są obsługiwane!");
        return round((amount * toSell.getCurrentValue() * toBuy.getConversionRate())/ (toBuy.getCurrentValue() * toSell.getConversionRate()), 4);
    }

    public Currency findInTable(String name){
        for(Currency c : table){
            if(c.getName().equals(name)) return c;
        }
        return null;
    }

    public SortedSet<Currency> getTable(){
        return this.table;
    }
}
