package com.example.itime;

public class CountdownItem {
    private String countdown;
    private String title;
    private String time;
    private String remark;
    private int imageId;

    public CountdownItem(String countdown,String title,String time,String remark,int imageId){
        this.countdown = countdown;//倒计时显示的文本
        this.title = title;//标题
        this.time = time;//显示的时间
        this.remark = remark;//标签名
        this.imageId = imageId;//图片
    }




    public String getCountdown() {
        return countdown;
    }

    public void setCountdown(String countdown) {
        this.countdown = countdown;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
