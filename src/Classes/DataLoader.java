package Classes;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DataLoader {
    private URL url;
    private List<Currency> list;

    public DataLoader(){}

    public DataLoader(URL url){
        this.url = url;
    }

    public void loadData(String bank) throws ParserConfigurationException, SAXException, IOException {
        if(bank.equals("NBP")){
            String endOfURL = this.getTodaysCurrencyValues();
            this.list = loadDataFromNBP(endOfURL);

        }
    }


    private List<Currency> loadDataFromNBP(String endOfURL) throws IOException, SAXException, ParserConfigurationException {
        List<Currency> res = new LinkedList<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        //System.out.println(this.getCurrentDate());
        // "https://www.nbp.pl/kursy/xml/a007z210113.xml"
        URL url = new URL("https://www.nbp.pl/kursy/xml/" + endOfURL + ".xml");
        InputStream stream = url.openStream();
        Document doc = docBuilder.parse(stream);
        this.list = new LinkedList<>();
        NodeList nodeList = doc.getElementsByTagName("pozycja");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                String val = eElement.getElementsByTagName("kurs_sredni").item(0).getTextContent();
                val = val.substring(0, 1) + '.' + val.substring(2, 5);

                Currency currency = new Currency(
                        eElement.getElementsByTagName("nazwa_waluty").item(0).getTextContent(),
                        Integer.parseInt(eElement.getElementsByTagName("przelicznik").item(0).getTextContent()),
                        eElement.getElementsByTagName("kod_waluty").item(0).getTextContent(),
                        Double.parseDouble(val)
                );
                res.add(currency);

            }
        }
        res.add(new Currency("złoty", 1, "PLN", 1));
        return res;
    }


    private String getTodaysCurrencyValues() throws ParserConfigurationException, IOException, SAXException {
        URL url = new URL("https://www.nbp.pl/kursy/xml/dir.txt");
        BufferedReader read = new BufferedReader(
                new InputStreamReader(url.openStream()));
        String i;
        String resURL = "";
        while ((i = read.readLine()) != null) {
            if(i.charAt(0) == 'a') resURL = i;
        }
        read.close();
        return resURL;
    }


    /**
     * Data is updated on workweek between 11:45-12:15.
     * @return
     */

    private String getCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if(checkTime()) {
            if (dayOfWeek == 1) {
                calendar.add(Calendar.DATE, -2);
                date = calendar.getTime();
            } else if (dayOfWeek == 7) {
                calendar.add(Calendar.DATE, -1);
                date = calendar.getTime();
            }
        }else{
            if (dayOfWeek == 1) {
                calendar.add(Calendar.DATE, -2);
                date = calendar.getTime();
            } else if(dayOfWeek == 2){
                calendar.add(Calendar.DATE, -3);
                date = calendar.getTime();
            }else{
                calendar.add(Calendar.DATE, -1);
                date = calendar.getTime();
            }
        }

        String res = formatter.format(date);
        return res;
    }


    public LinkedList<Double> getYearMonthData(String curr, String year, boolean month, String monthNumber) throws ParserConfigurationException, SAXException, IOException {
        List<Currency> currList = loadYearMonthData(curr, year, month, monthNumber);
        LinkedList<Double> res = new LinkedList<>();
        for(Currency c : currList){
            res.add(c.getCurrentValue());
        }

        return res;
    }


    private List<Currency> loadYearMonthData(String curr, String year, boolean month, String monthNumber) throws IOException, ParserConfigurationException, SAXException {
        URL url = new URL("https://www.nbp.pl/kursy/xml/dir" + year + ".txt");
        BufferedReader read = new BufferedReader(
                new InputStreamReader(url.openStream()));
        String i;

        List<Currency> res = new LinkedList<>();
        List<Currency> temp = new LinkedList<>();
        while ((i = read.readLine()) != null) {
            if(i.charAt(0) == 'a') {
                if(!month){
                    if(!i.substring(7,9).equals(monthNumber)){
                        continue;
                    }
                }
                temp = loadDataFromNBP(i);
                res.add(getCurrFromList(temp, curr));

            }
        }
        read.close();

        return res;
    }


    private Currency getCurrFromList(List<Currency> list, String currName) throws ParserConfigurationException, IOException, SAXException {
        for(Currency c : list){
            if(currName.equals(c.getName())) return c;
        }
        return null;
    }

    private boolean checkTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String toCheck = formatter.format(date);

        if((Integer.parseInt(toCheck.substring(0, 2)) == 12 && Integer.parseInt(toCheck.substring(3, 5)) >= 15) || Integer.parseInt(toCheck.substring(0, 2)) > 12) return true;
        else return false;
    }


    public List<Currency> getListOfCurrencies(){
        return this.list;
    }


    public void setUrl(URL url) {
        this.url = url;
    }
}
