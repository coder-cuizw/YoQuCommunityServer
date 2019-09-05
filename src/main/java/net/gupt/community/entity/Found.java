package net.gupt.community.entity;

import java.io.Serializable;
import java.util.Date;

public class Found implements Serializable {
    private Integer id;

    private Integer uid;

    private String title;

    private Date postTime = new Date();

    private String contactNumber;

    private String lostTime;

    private String lostName;

    private String lostClass;

    private String address;

    private Boolean articleState = false;

    private String lostDescribe;
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber == null ? null : contactNumber.trim();
    }

    public String getLostTime() {
        return lostTime;
    }

    public void setLostTime(String lostTime) {
        this.lostTime = lostTime == null ? null : lostTime.trim();
    }

    public String getLostName() {
        return lostName;
    }

    public void setLostName(String lostName) {
        this.lostName = lostName == null ? null : lostName.trim();
    }

    public String getLostClass() {
        return lostClass;
    }

    public void setLostClass(String lostClass) {
        this.lostClass = lostClass == null ? null : lostClass.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Boolean getArticleState() {
        return articleState;
    }

    public void setArticleState(Boolean articleState) {
        this.articleState = articleState;
    }

    public String getLostDescribe() {
        return lostDescribe;
    }

    public void setLostDescribe(String lostDescribe) {
        this.lostDescribe = lostDescribe == null ? null : lostDescribe.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Found other = (Found) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getPostTime() == null ? other.getPostTime() == null : this.getPostTime().equals(other.getPostTime()))
                && (this.getContactNumber() == null ? other.getContactNumber() == null : this.getContactNumber().equals(other.getContactNumber()))
                && (this.getLostTime() == null ? other.getLostTime() == null : this.getLostTime().equals(other.getLostTime()))
                && (this.getLostName() == null ? other.getLostName() == null : this.getLostName().equals(other.getLostName()))
                && (this.getLostClass() == null ? other.getLostClass() == null : this.getLostClass().equals(other.getLostClass()))
                && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
                && (this.getArticleState() == null ? other.getArticleState() == null : this.getArticleState().equals(other.getArticleState()))
                && (this.getLostDescribe() == null ? other.getLostDescribe() == null : this.getLostDescribe().equals(other.getLostDescribe()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getPostTime() == null) ? 0 : getPostTime().hashCode());
        result = prime * result + ((getContactNumber() == null) ? 0 : getContactNumber().hashCode());
        result = prime * result + ((getLostTime() == null) ? 0 : getLostTime().hashCode());
        result = prime * result + ((getLostName() == null) ? 0 : getLostName().hashCode());
        result = prime * result + ((getLostClass() == null) ? 0 : getLostClass().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getArticleState() == null) ? 0 : getArticleState().hashCode());
        result = prime * result + ((getLostDescribe() == null) ? 0 : getLostDescribe().hashCode());
        return result;
    }
}