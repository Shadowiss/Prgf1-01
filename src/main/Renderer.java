package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Renderer {

    private BufferedImage img;
    private Canvas canvas;
    private static final int FPS = 1000/30;

    public Renderer(BufferedImage img, Canvas canvas) {
        this.img = img;
        this.canvas = canvas;
        setTimer();
    }

    private void setTimer() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // říct plátnu, aby zobrazil aktuální img
                canvas.getGraphics().drawImage(img, 0, 0, null);
                // co dělá observer - https://stackoverflow.com/a/1684476
            }
        },0,FPS);
    }

    public void drawPixel(int x, int y, int color) {
        // nastavit pixel do img
        img.setRGB(x, y, color);

    }
    public void clear(){
        Graphics g = img.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,800,600);
    }

    public void drawLine(int x1, int y1, int x2, int y2, int color){
        float k = (y2-y1)/(float)(x2-x1);
        float q = y1 - k * x1;

        if(Math.abs(k)<1) {
            if (x1 > x2) {
                int a = x1;
                x1 = x2;
                x2 = a;

                a = y1;
                y1 = y2;
                y2 = a;
            }

            for (int x = x1; x <= x2; x++) {
                int y = Math.round(k * x + q);
                drawPixel(x, y, color);

            }
        }
        if(Math.abs(k)>1) {
            if (y1 < y2) {
                int a = y1;
                y1 = y2;
                y2 = a;

                a = x1;
                x1 = x2;
                x2 = a;
            }

            for (int y = y1; y <= y2; y++) {
                int x = Math.round(k * y + q);
                drawPixel(x, y, color);

            }
        }
    }
}
