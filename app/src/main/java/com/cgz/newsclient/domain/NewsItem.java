package com.cgz.newsclient.domain;

/**
 * 新闻信息实体bean
 * @author cgz
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class NewsItem {

    // 新闻标题
    private String title;

    // 新闻描述
    private String desc;

    // 新闻路径
    private String imagePath;

    // 新闻类型
    private String type;

    // 新闻的评论数
    private int commentCount;

    //快捷键 command+N 快速生成


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", type='" + type + '\'' +
                ", commentCount=" + commentCount +
                '}';
    }
}
