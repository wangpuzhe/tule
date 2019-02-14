package com.kiah.tule.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_room_info")
public class TRoomInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 房间号
     */
    private String no;

    /**
     * 房间名称
     */
    private String mc;

    /**
     * 房间类型
     */
    private String lx;

    /**
     * 房间状态
     */
    private String zt;

    /**
     * 备注
     */
    private String bz;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date cjsj;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date gxsj;

    /**
     * 创建人
     */
    private String cjr;

    /**
     * 是否删除
     */
    private String sfsc;


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


    public String getMc() {
        return mc;
    }


    public void setMc(String mc) {
        this.mc = mc == null ? null : mc.trim();
    }


    public String getLx() {
        return lx;
    }


    public void setLx(String lx) {
        this.lx = lx == null ? null : lx.trim();
    }


    public String getZt() {
        return zt;
    }


    public void setZt(String zt) {
        this.zt = zt == null ? null : zt.trim();
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