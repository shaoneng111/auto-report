package tag;

import java.util.ArrayList;
import java.util.List;

public class Component {
    protected String name;

    protected int titleLevel = 0;

    protected String title;

    protected boolean showTitle = true;

    protected String showContent = "true";

    protected String path;

    protected String foreach;

    protected String index;

    protected Data data;

    protected Style style;

    protected List<Component> components = new ArrayList<>();

    public void addComponent (Component component) {
        if (this.components == null) {
            this.components = new ArrayList<>();
        }
        this.components.add(component);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTitleLevel() {
        return titleLevel;
    }

    public void setTitleLevel(int titleLevel) {
        this.titleLevel = titleLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isShowTitle() {
        return showTitle;
    }

    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }

    public String getShowContent() {
        return showContent;
    }

    public void setShowContent(String showContent) {
        this.showContent = showContent;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getForeach() {
        return foreach;
    }

    public void setForeach(String foreach) {
        this.foreach = foreach;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
