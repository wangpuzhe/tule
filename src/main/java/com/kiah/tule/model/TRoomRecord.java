package com.kiah.tule.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_room_record")
public class TRoomRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 房间号
     */
    private String no;

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
     * 押金
     */
    private BigDecimal yj;

    /**
     * 金额
     */
    private BigDecimal je;

    /**
     * ������
     */
    private String dfr;

    /**
     * 渠道
     */
    private String qd;

    /**
     * 备注
     */
    private String bz;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date cjsj;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date gxsj;

    /**
     * 创建人
     */
    private String cjr;

    /**
     * 是否删除
     */
    private String sfsc;

    /**
     * 实退押金
     */
    private BigDecimal styj;

    /**
     * 归还状态
     */
    private String ghzt;

    /**
     * 是否激活
     */
    private String sfjh;

    /**
     * 预定id
     */
    private Integer ydid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNo() {
        return no;
    }


    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
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


    public BigDecimal getYj() {
        return yj;
    }


    public void setYj(BigDecimal yj) {
        this.yj = yj;
    }


    public BigDecimal getJe() {
        return je;
    }


    public void setJe(BigDecimal je) {
        this.je = je;
    }


    public String getDfr() {
        return dfr;
    }


    public void setDfr(String dfr) {
        this.dfr = dfr == null ? null : dfr.trim();
    }


    public String getQd() {
        return qd;
    }


    public void setQd(String qd) {
        this.qd = qd == null ? null : qd.trim();
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


    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr == null ? null : cjr.trim();
    }


    public String getSfsc() {
        return sfsc;
    }


    public void setSfsc(String sfsc) {
        this.sfsc = sfsc == null ? null : sfsc.trim();
    }


    public BigDecimal getStyj() {
        return styj;
    }


    public void setStyj(BigDecimal styj) {
        this.styj = styj;
    }


    public String getGhzt() {
        return ghzt;
    }


    public void setGhzt(String ghzt) {
        this.ghzt = ghzt;
    }


    public Integer getYdid() {
        return ydid;
    }


    public void setYdid(Integer ydid) {
        this.ydid = ydid;
    }


    public String getSfjh() {
        return sfjh;
    }


    public void setSfjh(String sfjh) {
        this.sfjh = sfjh;
    }
}
