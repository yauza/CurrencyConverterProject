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
    double buyCurrency(Currency toBuy, double amount, Currency toSell);

    /**
     *
     * Returns the amount of toBuy currency possible to buy with the
     * amount of toSell currency.
     *
     * @param toSell
     * @param amount
     * @param toBuy
     * @return
     */

    double sellCurrency(Currency toSell, double amount, Currency toBuy);
}
