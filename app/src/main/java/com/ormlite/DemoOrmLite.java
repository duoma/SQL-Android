package com.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="demo_orm_lite")
public class DemoOrmLite {

    @DatabaseField(generatedId=true)
    private Long _id;
    @DatabaseField
    private Integer ownerid;
    @DatabaseField
    private Integer sectionid;
    @DatabaseField
    private Integer bookid;
    @DatabaseField
    private Integer pageid;
    @DatabaseField
    private Integer type;
    @DatabaseField
    private Integer activityType;
    @DatabaseField
    private Integer activityId;
    @DatabaseField
    private Integer counter;
    @DatabaseField
    private Integer x;
    @DatabaseField
    private Integer y;
    @DatabaseField
    private Float ab_x;
    @DatabaseField
    private Float ab_y;
    @DatabaseField
    private Integer fx;
    @DatabaseField
    private Integer fy;
    @DatabaseField
    private Integer angle;
    @DatabaseField
    private Integer force;
    @DatabaseField
    private Long timelong;
    @DatabaseField
    private Integer color;
    @DatabaseField
    private Float weight;
    @DatabaseField
    private String userId;

    public DemoOrmLite() {

    }

    public DemoOrmLite(Integer ownerid, Integer sectionid, Integer bookid, Integer pageid, Integer type, Integer activityType, Integer activityId, Integer counter, Integer x, Integer y, Float ab_x, Float ab_y, Integer fx, Integer fy, Integer angle, Integer force, Long timelong, Integer color, Float weight, String userId) {
        this.ownerid = ownerid;
        this.sectionid = sectionid;
        this.bookid = bookid;
        this.pageid = pageid;
        this.type = type;
        this.activityType = activityType;
        this.activityId = activityId;
        this.counter = counter;
        this.x = x;
        this.y = y;
        this.ab_x = ab_x;
        this.ab_y = ab_y;
        this.fx = fx;
        this.fy = fy;
        this.angle = angle;
        this.force = force;
        this.timelong = timelong;
        this.color = color;
        this.weight = weight;
        this.userId = userId;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Integer getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(Integer ownerid) {
        this.ownerid = ownerid;
    }

    public Integer getSectionid() {
        return sectionid;
    }

    public void setSectionid(Integer sectionid) {
        this.sectionid = sectionid;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public Integer getPageid() {
        return pageid;
    }

    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Float getAb_x() {
        return ab_x;
    }

    public void setAb_x(Float ab_x) {
        this.ab_x = ab_x;
    }

    public Float getAb_y() {
        return ab_y;
    }

    public void setAb_y(Float ab_y) {
        this.ab_y = ab_y;
    }

    public Integer getFx() {
        return fx;
    }

    public void setFx(Integer fx) {
        this.fx = fx;
    }

    public Integer getFy() {
        return fy;
    }

    public void setFy(Integer fy) {
        this.fy = fy;
    }

    public Integer getAngle() {
        return angle;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

    public Integer getForce() {
        return force;
    }

    public void setForce(Integer force) {
        this.force = force;
    }

    public Long getTimelong() {
        return timelong;
    }

    public void setTimelong(Long timelong) {
        this.timelong = timelong;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
