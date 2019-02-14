package com.kiah.tule.model;

import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "t_room_customer")
public class TRoomCustomer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * �����
     */
    private Integer jlid;

    /**
     * ��������
     */
    private String xm;

    /**
     * ֤������
     */
    private String zjhm;

    /**
     * �绰
     */
    private String dh;

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
     * @return jlid - �����
     */
    public Integer getJlid() {
        return jlid;
    }

    /**
     * ���÷����
     *
     * @param jlid �����
     */
    public void setJlid(Integer jlid) {
        this.jlid = jlid;
    }

    /**
     * ��ȡ��������
     *
     * @return xm - ��������
     */
    public String getXm() {
        return xm;
    }

    /**
     * ���ÿ�������
     *
     * @param xm ��������
     */
    public void setXm(String xm) {
        this.xm = xm == null ? null : xm.trim();
    }

    /**
     * ��ȡ֤������
     *
     * @return zjhm - ֤������
     */
    public String getZjhm() {
        return zjhm;
    }

    /**
     * ����֤������
     *
     * @param zjhm ֤������
     */
    public void setZjhm(String zjhm) {
        this.zjhm = zjhm == null ? null : zjhm.trim();
    }

    /**
     * ��ȡ�绰
     *
     * @return dh - �绰
     */
    public String getDh() {
        return dh;
    }

    /**
     * ���õ绰
     *
     * @param dh �绰
     */
    public void setDh(String dh) {
        this.dh = dh == null ? null : dh.trim();
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