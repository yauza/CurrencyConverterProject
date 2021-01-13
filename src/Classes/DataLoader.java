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
            this.loadDataFromNBP();
        }
    }


    private void loadDataFromNBP() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        System.out.println(this.getCurrentDate());

        URL url = new URL("https://www.nbp.pl/kursy/xml/a006z210112.xml");
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
                this.list.add(currency);

            }
        }
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
            //
            // to finish
            //
        }

        String res = formatter.format(date);
        return res;
    }


    private boolean checkTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String toCheck = formatter.format(date);

        if((Integer.parseInt(toCheck.substring(0, 2)) == 12 && Integer.parseInt(toCheck.substring(3, 4)) >= 15) || Integer.parseInt(toCheck.substring(0, 2)) > 12) return true;
        else return false;
    }

    private int dayOfWeek(){

    }

    public List<Currency> getListOfCurrencies(){
        return this.list;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
