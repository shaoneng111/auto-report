package Service;

import render.RdlRenderFactory;
import tag.Document;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * @author shao
 * @date 18-10-18
 * @time 上午10:47
 */
public class Engine {

    public Engine() {

        Font font = null;
        BufferedInputStream fb = new BufferedInputStream(Engine.class.getResourceAsStream("/msyh.ttf"));
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fb);
            fb.close();
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String process(Document doc) {
        //构建data provider
        //渲染xml
        String htmlStr = null;
        htmlStr = RdlRenderFactory.getRdlRender().render(doc, new DataProviderFactory());
        return htmlStr;
    }


}
