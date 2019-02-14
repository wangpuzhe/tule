package com.kiah.tule.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_room_customer")
public class TRoomCustomer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * id
     */
    private Integer jlid;

    /**
     * 记录id
     */
    private String xm;

    /**
     * 客人姓名
     */
    private String zjhm;

    /**
     * 证件号码
     */
    private String dh;

    /**
     * 电话
     */
    private String bz;

    /**
     * 备注
     */
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date cjsj;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date gxsj;

    /**
     * 更新时间
     */
    private String cjr;

    /**
     * 创建人
     */
    private String sfsc;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getJlid() {
        return jlid;
    }


    public void setJlid(Integer jlid) {
        this.jlid = jlid;
    }


    public String getXm() {
        return xm;
    }


    public void setXm(String xm) {
        this.xm = xm == null ? null : xm.trim();
    }


    public String getZjhm() {
        return zjhm;
    }


    public void setZjhm(String zjhm) {
        this.zjhm = zjhm == null ? null : zjhm.trim();
    }


    public String getDh() {
        return dh;
    }


    public void setDh(String dh) {
        this.dh = dh == null ? null : dh.trim();
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
}