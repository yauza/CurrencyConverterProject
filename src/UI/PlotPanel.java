package UI;

import javax.swing.*;
import java.awt.*;

public class PlotPanel extends JPanel {

    private PlotButtonsPanel pbp;

    public PlotPanel(PlotButtonsPanel pbp){
        this.pbp = pbp;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //g.setColor(new Color(236, 236, 7));
        g.fillRect(0,400,400,400);

        if(pbp.getPlotType()){
            g.drawLine(10, 790, 10, 600);
        }
        g.drawLine(20, 790, 20, 600);
    }
}
