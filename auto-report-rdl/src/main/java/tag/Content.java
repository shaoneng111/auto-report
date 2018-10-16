package tag;

public class Content extends Component{

    public Content() {
        this.showTitle = false;
    }

    private String template;

    private String content;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
