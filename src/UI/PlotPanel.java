package UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import Classes.Currency;

public class PlotPanel extends JPanel {

    private PlotButtonsPanel pbp;
    private LinkedList<Double> values;
    private boolean vis;

    private boolean yearGraph;

    public PlotPanel(PlotButtonsPanel pbp, LinkedList<Double> values, boolean vis){
        this.pbp = pbp;
        this.values = values;
        this.vis = vis;
        System.out.println(values);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(vis) {
            //g.setColor(new Color(236, 236, 7));
            //g.fillRect(0,400,400,400);

            double xScale = ((double) getWidth() - (80)) / (values.size() - 1);
            double yScale = ((double) getHeight() - 80) / (getMaxValue() - getMinValue());

            ArrayList<Point> points = new ArrayList<>();
            for (int i = 0; i < values.size(); i++) {
                int x1 = (int) (i * xScale + 40);
                int y1 = (int) ((getMaxValue() - values.get(i)) * yScale + 40);
                points.add(new Point(x1, y1));
            }

            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, 450, 450);
            g2.setColor(Color.BLACK);

            for (int i = 0; i < 11; i++) {
                int a1 = 40;
                int a2 = 40 + 4;
                int b1 = getHeight() - ((i * (getHeight() - 40 * 2)) / 10 + 40);
                int b2 = b1;
                String label = ((int) ((getMinValue() + (getMaxValue() - getMinValue()) * ((i * 1.0) / 10)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(label);
                g2.drawString(label, a1 - labelWidth - 2, b1 + (metrics.getHeight() / 2) - 3);

                g2.drawLine(a1, b1, a2, b2);
            }

            int n;
            String [] xLab;
            if (this.yearGraph){
                n = 13;
                xLab = new String[]{"S", "L", "M", "K", "M", "C", "L", "S", "W", "P", "L", "G"};
            }
            else {
                n = 4;
                xLab = new String[]{"1/3", "2/3", "3/3" };
            }

            for (int i = 0; i < n; i++) {
                int a1 = i * (getWidth() - 40 * 2) / (n - 1) + 40;
                int a2 = a1;
                int b1 = getHeight() - 40;
                int b2 = b1 - 4;

                if(i < n-1) g2.drawString(xLab[i], (a1 + (i+1) * (getWidth() - 40 * 2) / (n - 1) + 40)/2, b1 + 15);

                g2.drawLine(a1, b1, a2, b2);
            }


            g2.drawLine(40, getHeight() - 40, 40, 40);
            g2.drawLine(40, getHeight() - 40, getWidth() - 40, getHeight() - 40);

            Stroke oldStroke = g2.getStroke();
            g2.setColor(new Color(14, 208, 234));
            g2.setStroke(new BasicStroke(2f));
            for (int i = 0; i < points.size() - 1; i++) {
                int a1 = points.get(i).getX();
                int a2 = points.get(i + 1).getX();
                int b1 = points.get(i).getY();
                int b2 = points.get(i + 1).getY();

                g2.drawLine(a1, b1, a2, b2);
            }
        }else{
            g2.setColor(Color.WHITE);
            g.fillRect(0,0,450,450);
        }
    }


    private double getMinValue(){
        double res = 100000000;
        for(Double d : this.values){
            res = Math.min(res, d);
        }

        return res;
    }


    private double getMaxValue(){
        double res = 0;
        for(Double d : this.values){
            res = Math.max(res, d);
        }

        return res;
    }


    public void setYearGraph(boolean yearGraph) {
        this.yearGraph = yearGraph;
    }

    public void setVis(boolean vis) {
        this.vis = vis;
    }

    public void setValues(LinkedList<Double> values) {
        this.values = values;
    }
}
