package Classes;

public class Currency implements Comparable<Currency>{
    private String name;
    private String currencyCode;
    private double currentValue;

    public Currency(String name, String currencyCode, double currentValue){
        this.name = name;
        this.currentValue = currentValue;
        this.currencyCode = currencyCode;
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

    public String getName(){
        return this.name;
    }

    public double getCurrentValue(){
        return this.currentValue;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    @Override
    public int compareTo(Currency o) {
        return this.name.compareTo(o.name);
    }
}
