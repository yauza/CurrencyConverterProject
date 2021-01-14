package Classes;

public class Currency implements Comparable<Currency>{
    private String name;
    private int conversionRate;
    private String currencyCode;
    private double currentValue;

    public Currency(String name, int conversionRate, String currencyCode, double currentValue){
        this.name = name;
        this.conversionRate = conversionRate;
        this.currencyCode = currencyCode;
        this.currentValue = currentValue;
    }

    public String toString(){
        return this.name;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setConversionRate(int conversionRate){ this.conversionRate = conversionRate; }

    public String getName(){
        return this.name;
    }

    public double getCurrentValue(){
        return this.currentValue;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public int getConversionRate() {return conversionRate; }

    @Override
    public int compareTo(Currency o) {
        return this.name.compareTo(o.name);
    }
}
