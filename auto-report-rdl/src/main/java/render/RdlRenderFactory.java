package render;

/**
 * @author shao
 * @date 18-10-18
 * @time 下午1:21
 */
public class RdlRenderFactory {

    public static IRdlRender getRdlRender() {
        //TODO
        return new RdlRenderDefault();
    }

}
