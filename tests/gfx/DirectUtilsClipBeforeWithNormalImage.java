package gfx;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import com.nokia.mid.ui.*;

public class DirectUtilsClipBeforeWithNormalImage extends MIDlet {
    private Display display;

    class TestCanvas extends Canvas {
        protected void paint(Graphics screenG) {
            Image image = Image.createImage(getWidth(), getHeight());
            Graphics g = image.getGraphics();

            g.setColor(0, 255, 0);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(0, 0, 255);
            g.fillRect(20, 30, 200, 80);

            DirectGraphics dGet = DirectUtils.getDirectGraphics(g);

            short[] pixels = new short[getWidth() * getHeight()];
            dGet.getPixels(pixels, 0, getWidth(), 0, 0, getWidth(), getHeight(), DirectGraphics.TYPE_USHORT_4444_ARGB);

            g.setColor(255, 0, 0);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setClip(20, 30, 200, 80);

            DirectGraphics dDraw = DirectUtils.getDirectGraphics(g);

            dDraw.drawPixels(pixels, true, 0, getWidth(), 0, 0, getWidth(), getHeight(), 0, DirectGraphics.TYPE_USHORT_4444_ARGB);

            screenG.drawImage(image, 0, 0, Graphics.TOP | Graphics.LEFT);

            System.out.println("PAINTED");
        }
    }

    public DirectUtilsClipBeforeWithNormalImage() {
        display = Display.getDisplay(this);
    }

    public void startApp() {
        TestCanvas test = new TestCanvas();
        display.setCurrent(test);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}

