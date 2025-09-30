package vn.binh.entity;

import java.io.Serializable;

public class RatingId implements Serializable {
    private Integer userid;
    private Integer bookid;

    public RatingId() {
    }

    public RatingId(Integer userid, Integer bookid) {
        this.userid = userid;
        this.bookid = bookid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }
}
