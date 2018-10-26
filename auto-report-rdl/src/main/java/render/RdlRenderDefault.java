package render;

import engine.IDataProvider;
import engine.IDataProviderFactory;
import tag.Component;
import tag.Content;
import tag.Document;
import tag.Style;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shao
 * @date 18-10-18
 * @time 上午11:05
 */
public class RdlRenderDefault implements IRdlRender{


    @Override
    public String render(Document document, IDataProviderFactory providerFactory) {

        if (document == null) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();

        //header
        stringBuilder.append("<!doctype html>\\n<html>\\n\\t<head>\\n\\t\\t<meta charset=\\\"utf-8\\\" />");

        //css
        stringBuilder.append(this._style(document.getStyle()));
        stringBuilder.append("");
        stringBuilder.append("\\n\\t</head>\\n\\t<body>");

        //标题等级
        List<Integer> titleLevel = new ArrayList<Integer>();

        titleLevel.add(2);
        for (int i = 1; i < 7; i++) {
            titleLevel.add(1);
        }

        //DataProvider
        IDataProvider docDataProvider = providerFactory.getDataProvider(document);

        //children
        List<Component> components = document.getComponents();

        if (components != null && components.size() > 0) {
            for (int i = 0; i < components.size(); i++) {
                Component cmp = components.get(i);
                stringBuilder.append("\n");
                stringBuilder.append(this._component(cmp, docDataProvider, titleLevel, providerFactory));
                stringBuilder.append("\n");
            }
        }
        //html结束
        stringBuilder.append("\n\t</body>\n</html>");
        return stringBuilder.toString();

    }

    private String _component(Component cmp, IDataProvider parent, List<Integer> titleLevel, IDataProviderFactory providerFactory) {

        //内容节点
        if (cmp instanceof Content) {
            Content content = (Content) cmp;
            //
            if (cmp instanceof Content) {
                ITplRender tplRender = TplRenderFactory.getTplRender(content.getTemplate());

                if (tplRender == null) {
                    return content.getContent();
                }

                IDataProvider provider = providerFactory.getDataProvider(cmp, parent);


            }

        }

        return null;
    }

    private String _style(Style style) {
        if (style == null) {
            return null;
        }
        return "<style>" + style.getValue() + "</style>";
    }
}
