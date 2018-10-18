package parser;

/**
 * @author shao
 * @date 18-10-18
 * @time 上午9:32
 */
public class RdlParserFactory {

    public static IRdlParser getRdlParser() {
        //TODO
        return new RdlParserDefault();
    }
}
