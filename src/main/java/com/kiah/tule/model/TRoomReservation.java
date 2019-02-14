package com.kiah.tule.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_room_reservation")
public class TRoomReservation implements Serializable {
    /**
     * Ԥ����¼id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * �����
     */
    private Integer no;

    /**
     * ��������
     */
    private String mc;

    /**
     * ������
     */
    private String dfr;

    /**
     * Ԥ��ס����
     */
    private Integer rzts;

    /**
     * Ԥ��סʱ��
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date rzsj;

    /**
     * Ԥ�˷�ʱ��
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date tfsj;

    /**
     * ����
     */
    private BigDecimal je;

    /**
     * ����
     */
    private String qd;

    /**
     * ������
     */
    private String jbr;

    /**
     * ��ע
     */
    private String bz;

    /**
     * ����ʱ��
     */
    private Date cjsj;

    /**
     * ����ʱ��
     */
    private Date gxsj;

    private String sfjh;

    private Integer fjid;

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

    private String dfrdhhm;

    public String getDfrdhhm() {
        return dfrdhhm;
    }

    public void setDfrdhhm(String dfrdhhm) {
        this.dfrdhhm = dfrdhhm;
    }

    /**
     * ��ȡԤ����¼id
     *
     * @return id - Ԥ����¼id
     */
    public Integer getId() {
        return id;
    }

    /**
     * ����Ԥ����¼id
     *
     * @param id Ԥ����¼id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * ��ȡ�����
     *
     * @return no - �����
     */
    public Integer getNo() {
        return no;
    }

    /**
     * ���÷����
     *
     * @param no �����
     */
    public void setNo(Integer no) {
        this.no = no;
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
     * ��ȡ������
     *
     * @return dfr - ������
     */
    public String getDfr() {
        return dfr;
    }

    /**
     * ���ö�����
     *
     * @param dfr ������
     */
    public void setDfr(String dfr) {
        this.dfr = dfr == null ? null : dfr.trim();
    }

    /**
     * ��ȡԤ��ס����
     *
     * @return rzts - Ԥ��ס����
     */
    public Integer getRzts() {
        return rzts;
    }

    /**
     * ����Ԥ��ס����
     *
     * @param rzts Ԥ��ס����
     */
    public void setRzts(Integer rzts) {
        this.rzts = rzts;
    }

    /**
     * ��ȡԤ��סʱ��
     *
     * @return rzsj - Ԥ��סʱ��
     */
    public Date getRzsj() {
        return rzsj;
    }

    /**
     * ����Ԥ��סʱ��
     *
     * @param rzsj Ԥ��סʱ��
     */
    public void setRzsj(Date rzsj) {
        this.rzsj = rzsj;
    }

    /**
     * ��ȡԤ�˷�ʱ��
     *
     * @return tfsj - Ԥ�˷�ʱ��
     */
    public Date getTfsj() {
        return tfsj;
    }

    /**
     * ����Ԥ�˷�ʱ��
     *
     * @param tfsj Ԥ�˷�ʱ��
     */
    public void setTfsj(Date tfsj) {
        this.tfsj = tfsj;
    }

    /**
     * ��ȡ����
     *
     * @return je - ����
     */
    public BigDecimal getJe() {
        return je;
    }

    /**
     * ���÷���
     *
     * @param je ����
     */
    public void setJe(BigDecimal je) {
        this.je = je;
    }

    /**
     * ��ȡ����
     *
     * @return qd - ����
     */
    public String getQd() {
        return qd;
    }

    /**
     * ��������
     *
     * @param qd ����
     */
    public void setQd(String qd) {
        this.qd = qd == null ? null : qd.trim();
    }

    /**
     * ��ȡ������
     *
     * @return jbr - ������
     */
    public String getJbr() {
        return jbr;
    }

    /**
     * ���þ�����
     *
     * @param jbr ������
     */
    public void setJbr(String jbr) {
        this.jbr = jbr == null ? null : jbr.trim();
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
}