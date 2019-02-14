package com.kiah.tule.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_room_info")
public class TRoomInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * �����
     */
    private String no;

    /**
     * ��������
     */
    private String mc;

    /**
     * ��������
     */
    private String lx;

    /**
     * ״̬
     */
    private String zt;

    /**
     * ��ע
     */
    private String bz;

    /**
     * ����ʱ��
     */
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date cjsj;

    /**
     * ����ʱ��
     */
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date gxsj;

    /**
     * ������
     */
    private String cjr;

    /**
     * �Ƿ�ɾ��
     */
    private String sfsc;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * ��ȡ�����
     *
     * @return no - �����
     */
    public String getNo() {
        return no;
    }

    /**
     * ���÷����
     *
     * @param no �����
     */
    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    /**
     * ��ȡ��������
     *
     * @return mc - ��������
     */
    public String getMc() {
        return mc;
    }

    /**
     * ���÷�������
     *
     * @param mc ��������
     */
    public void setMc(String mc) {
        this.mc = mc == null ? null : mc.trim();
    }

    /**
     * ��ȡ��������
     *
     * @return lx - ��������
     */
    public String getLx() {
        return lx;
    }

    /**
     * ���÷�������
     *
     * @param lx ��������
     */
    public void setLx(String lx) {
        this.lx = lx == null ? null : lx.trim();
    }

    /**
     * ��ȡ״̬
     *
     * @return zt - ״̬
     */
    public String getZt() {
        return zt;
    }

    /**
     * ����״̬
     *
     * @param zt ״̬
     */
    public void setZt(String zt) {
        this.zt = zt == null ? null : zt.trim();
    }

    /**
     * ��ȡ��ע
     *
     * @return bz - ��ע
     */
    public String getBz() {
        return bz;
    }

    /**
     * ���ñ�ע
     *
     * @param bz ��ע
     */
    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    /**
     * ��ȡ����ʱ��
     *
     * @return cjsj - ����ʱ��
     */
    public Date getCjsj() {
        return cjsj;
    }

    /**
     * ���ô���ʱ��
     *
     * @param cjsj ����ʱ��
     */
    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    /**
     * ��ȡ����ʱ��
     *
     * @return gxsj - ����ʱ��
     */
    public Date getGxsj() {
        return gxsj;
    }

    /**
     * ���ø���ʱ��
     *
     * @param gxsj ����ʱ��
     */
    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }

    /**
     * ��ȡ������
     *
     * @return cjr - ������
     */
    public String getCjr() {
        return cjr;
    }

    /**
     * ���ô�����
     *
     * @param cjr ������
     */
    public void setCjr(String cjr) {
        this.cjr = cjr == null ? null : cjr.trim();
    }

    /**
     * ��ȡ�Ƿ�ɾ��
     *
     * @return sfsc - �Ƿ�ɾ��
     */
    public String getSfsc() {
        return sfsc;
    }

    /**
     * �����Ƿ�ɾ��
     *
     * @param sfsc �Ƿ�ɾ��
     */
    public void setSfsc(String sfsc) {
        this.sfsc = sfsc == null ? null : sfsc.trim();
    }
}