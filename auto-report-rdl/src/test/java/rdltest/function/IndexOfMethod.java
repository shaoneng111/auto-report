package rdltest.function;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

public class IndexOfMethod implements TemplateMethodModelEx {

    @Override
    public Object exec(List list) throws TemplateModelException {
        if(list.size() != 2){
            throw new TemplateModelException("wrong arguments");
        }
        return 1;
    }
}
