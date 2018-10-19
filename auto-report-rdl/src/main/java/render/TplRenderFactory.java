package render;

/**
 * @author shao
 * @date 18-10-19
 * @time 下午1:38
 */
public class TplRenderFactory {

    public static ITplRender getTplRender(String tplType) {

        if (tplType == null || tplType.trim().equals("")) {
            return new TplRenderDefault();
        } else if ("freemarker".equals(tplType.toLowerCase())) {
            return new TplRenderDefault();
        }

        return new TplRenderDefault();
    }
}
