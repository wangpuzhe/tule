package com.kiah.tule.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_room_reservation")
public class TRoomReservation implements Serializable {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 房间号
     */
    private Integer no;

    /**
     * 房间名称
     */
    private String mc;

    /**
     * 订房人
     */
    private String dfr;

    /**
     * 入住天数
     */
    private Integer rzts;

    /**
     * 入住时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date rzsj;

    /**
     * 退房时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date tfsj;

    /**
     * 金额
     */
    private BigDecimal je;

    /**
     * 渠道
     */
    private String qd;

    /**
     * 经办人
     */
    private String jbr;

    /**
     * 备注
     */
    private String bz;

    /**
     * 创建时间
     */
    private Date cjsj;

    /**
     * 更新时间
     */
    private Date gxsj;

    /**
     * 是否激活
     */
    private String sfjh;

    /**
     * 房间id
     */
    private Integer fjid;

    /**
     * 订房人电话号码
     */
    private String dfrdhhm;

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getNo() {
        return no;
    }


    public void setNo(Integer no) {
        this.no = no;
    }


    public String getMc() {
        return mc;
    }


    public void setMc(String mc) {
        this.mc = mc == null ? null : mc.trim();
    }


    public String getDfr() {
        return dfr;
    }


    public void setDfr(String dfr) {
        this.dfr = dfr == null ? null : dfr.trim();
    }


    public Integer getRzts() {
        return rzts;
    }


    public void setRzts(Integer rzts) {
        this.rzts = rzts;
    }


    public Date getRzsj() {
        return rzsj;
    }


    public void setRzsj(Date rzsj) {
        this.rzsj = rzsj;
    }


    public Date getTfsj() {
        return tfsj;
    }


    public void setTfsj(Date tfsj) {
        this.tfsj = tfsj;
    }


    public BigDecimal getJe() {
        return je;
    }


    public void setJe(BigDecimal je) {
        this.je = je;
    }


    public String getQd() {
        return qd;
    }


    public void setQd(String qd) {
        this.qd = qd == null ? null : qd.trim();
    }


    public String getJbr() {
        return jbr;
    }


    public void setJbr(String jbr) {
        this.jbr = jbr == null ? null : jbr.trim();
    }


    public String getBz() {
        return bz;
    }


    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }


    public Date getCjsj() {
        return cjsj;
    }


    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }


    public Date getGxsj() {
        return gxsj;
    }


    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }


    public Integer getFjid() {
        return fjid;
    }


    public void setFjid(Integer fjid) {
        this.fjid = fjid;
    }


    public String getSfjh() {
        return sfjh;
    }


    public void setSfjh(String sfjh) {
        this.sfjh = sfjh;
    }


    public String getDfrdhhm() {
        return dfrdhhm;
    }


    public void setDfrdhhm(String dfrdhhm) {
        this.dfrdhhm = dfrdhhm;
    }
}