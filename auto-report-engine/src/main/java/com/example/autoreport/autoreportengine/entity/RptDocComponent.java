package com.example.autoreport.autoreportengine.entity;

public class RptDocComponent {

    private long id;

    private long tplId;

    private int ordernum;

    private int titleLevel = 0;

    private String title;

    private short showTitle;

    private String type;

    private short coverPage = 0;

    private String label;

    private short status = 1;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTplId() {
        return tplId;
    }

    public void setTplId(long tplId) {
        this.tplId = tplId;
    }

    public int getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
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

    public short getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(short showTitle) {
        this.showTitle = showTitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public short getCoverPage() {
        return coverPage;
    }

    public void setCoverPage(short coverPage) {
        this.coverPage = coverPage;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }
}
