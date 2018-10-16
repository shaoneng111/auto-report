package dom4jTest;

import org.apache.commons.io.IOUtils;
import parser.RdlParserDefault;
import tag.Component;

import java.io.IOException;

public class ContentTest {
    public static void main(String[] args) {
        RdlParserDefault rdlParserDefault = new RdlParserDefault();

        try {
            String str = IOUtils.toString(ContentTest.class.getResourceAsStream("/ContentTest1.xml"),"UTF-8");

            System.out.println(str);
            Component component = rdlParserDefault.parseComponent(str);
            System.out.println("hello");
        } catch (IOException e) {
            e.printStackTrace();
        }


      //  rdlParserDefault.parse();
    }
}
