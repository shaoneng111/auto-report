package rdltest;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import rdltest.function.IndexOfMethod;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TplTest {
    public static void main(String[] args) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        try {
            cfg.setDirectoryForTemplateLoading(new File("auto-report-rdl/src/test/java/resources"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            /* 创建数据模型 */
            Map root = new HashMap();
            root.put("user","Big Joe");
            Map latest = new HashMap();
            root.put("latestProduct", latest);
            latest.put("url","product/abc.html");
            latest.put("name", "abc");


            List<String> countries = new ArrayList();
            countries.add("China");
            countries.add("Amercian");
            countries.add("France");

            //freemarker添加函数
            root.put("indexOf", new IndexOfMethod());

            Template temp = cfg.getTemplate("test.ftl");
            Writer out = new OutputStreamWriter(System.out);
            temp.process(root, out);





        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }


}
