package com.kiah.tule.model;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author kiah
 * @version Version 1.0
 * @date 2018/11/11
 */
public class DataTableResult implements Serializable{
    public DataTableResult(PageInfo pageInfo){
        this.prePage = pageInfo.getPrePage();
        this.nextPage = pageInfo.getNextPage();
        this.recordsTotal = pageInfo.getTotal();
        this.recordsFiltered = pageInfo.getTotal();
        this.data = pageInfo.getList();
    }
    private int prePage;
    private int nextPage;
    private long recordsTotal;
    private long recordsFiltered;
    private List data;

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }


    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
