package com.kiah.tule.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_room_record")
public class TRoomRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * �����
     */
    private String no;

    /**
     * ��ס����
     */
    private Integer rzts;

    /**
     * ��סʱ��
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date rzsj;

    /**
     * �˷�ʱ��
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date tfsj;

    /**
     * Ѻ��
     */
    private BigDecimal yj;

    /**
     * ���
     */
    private BigDecimal je;

    /**
     * ������
     */
    private String dfr;

    /**
     * ����
     */
    private String qd;

    /**
     * ��ע
     */
    private String bz;


    /**
     * ����ʱ��
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date cjsj;

    /**
     * ����ʱ��
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date gxsj;

    /**
     * ������
     */
    private String cjr;

    /**
     * �Ƿ�ɾ��
     */
    private String sfsc;



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
     * @return
     */
    private Integer ydid;

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
     * ��ȡ��ס����
     *
     * @return rzts - ��ס����
     */
    public Integer getRzts() {
        return rzts;
    }

    /**
     * ������ס����
     *
     * @param rzts ��ס����
     */
    public void setRzts(Integer rzts) {
        this.rzts = rzts;
    }

    /**
     * ��ȡ��סʱ��
     *
     * @return rzsj - ��סʱ��
     */
    public Date getRzsj() {
        return rzsj;
    }

    /**
     * ������סʱ��
     *
     * @param rzsj ��סʱ��
     */
    public void setRzsj(Date rzsj) {
        this.rzsj = rzsj;
    }

    /**
     * ��ȡ�˷�ʱ��
     *
     * @return tfsj - �˷�ʱ��
     */
    public Date getTfsj() {
        return tfsj;
    }

    /**
     * �����˷�ʱ��
     *
     * @param tfsj �˷�ʱ��
     */
    public void setTfsj(Date tfsj) {
        this.tfsj = tfsj;
    }

    /**
     * ��ȡѺ��
     *
     * @return yj - Ѻ��
     */
    public BigDecimal getYj() {
        return yj;
    }

    /**
     * ����Ѻ��
     *
     * @param yj Ѻ��
     */
    public void setYj(BigDecimal yj) {
        this.yj = yj;
    }

    /**
     * ��ȡ���
     *
     * @return je - ���
     */
    public BigDecimal getJe() {
        return je;
    }

    /**
     * ���ý��
     *
     * @param je ���
     */
    public void setJe(BigDecimal je) {
        this.je = je;
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