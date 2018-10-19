package render;

import engine.IDataProvider;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


public class TplRenderDefault implements ITplRender{

    @Override
    public String render(String tpl, Object privider) {
        return this._render(tpl, privider);
    }

    @Override
    public String render(String tpl, IDataProvider provider) {
        return this._render(tpl, provider);
    }

    private String _render(String tpl, Object provider) {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        try {
            Template template = new Template(String.valueOf(this.hashCode()), tpl, cfg);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Writer out = new OutputStreamWriter(outputStream, "UTF-8");
            template.process(provider, out);
            String outStr = new String(outputStream.toByteArray(), "UTF-8");
            return outStr;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return tpl;
    }

    public static void main(String[] args) {
        String tpl = "${showContent}";
        Map provider = new HashMap(16);
        provider.put("showContent", "hello");

        TplRenderDefault renderDefault = new TplRenderDefault();
        String retStr = renderDefault._render(tpl, provider);

        System.out.println(retStr);


    }

}
