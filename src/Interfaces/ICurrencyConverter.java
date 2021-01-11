package Interfaces;
import Classes.*;

public interface ICurrencyConverter {
    /**
     *
     * Returns the price of the amount of toBuy currency in toSell currency.
     * ex. 2 euro -> 2.44 dollars
     * @param toBuy
     * @param toSell
     * @return
     */
    double buyCurrency(Currency toBuy, Currency toSell, double amount);
}
