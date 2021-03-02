package com.luis.poem.bean;


import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class PoemBean {


    /**
     * _id : {"$oid":"5b9a1c78367d5c0f40d2495c"}
     * title : 送孙处士默还黄山
     * dynasty : 清代
     * writer : 朱彝尊
     * content : 芜城客散乱乌啼，别业黄山路不迷。后夜相思秋色远，月明三十二峰西。
     * type : []
     */
    @Id
    private Long id;
    private String title;
    private String content;
    private String remark;
    private String shangxi;
    private String dynasty;
    private String translation;
    private String writer;
    @Unique
    @Property(nameInDb = "O_ID")
    @Convert(columnType = String.class, converter = OidConverter.class)
    private IDbean _id;


    @Generated(hash = 1920520094)
    public PoemBean(Long id, String title, String content, String remark,
                    String shangxi, String dynasty, String translation, String writer,
                    IDbean _id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.remark = remark;
        this.shangxi = shangxi;
        this.dynasty = dynasty;
        this.translation = translation;
        this.writer = writer;
        this._id = _id;
    }

    @Generated(hash = 848703650)
    public PoemBean() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getShangxi() {
        return shangxi;
    }

    public void setShangxi(String shangxi) {
        this.shangxi = shangxi;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public IDbean get_id() {
        return _id;
    }

    public void set_id(IDbean _id) {
        this._id = _id;
    }


}
