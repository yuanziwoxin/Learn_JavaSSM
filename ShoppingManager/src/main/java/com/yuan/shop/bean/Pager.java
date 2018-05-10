package com.yuan.shop.bean;

/**
 * 分页数据的实体类
 */
public class Pager {
    private int currentPageNum;//当前页码
    private int pageSize=3;//每页存放的数据记录条数
    private int totalCount;//总记录数
    private int totalPages;//总页数

    private int firstParam;//分页的第一个参数（即select查询语句中limit后的第一个参数）

    public int getCurrentPageNum() {
        currentPageNum=currentPageNum <= 0 ? 1:currentPageNum;
        currentPageNum=currentPageNum > this.getTotalPages() ? this.getTotalPages():currentPageNum;
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        /*
        根据总数据的记录数和每页的记录数计算得到总页数
        如 10条记录 每页3条  总共4页
           9          3       3
        总页数=（总记录数-1）/ 每页记录数 + 1
         */
        return (this.getTotalCount()-1)/this.getPageSize()+1;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    //获取select查询语句limit后的第一个参数
    public int getFirstParam()
    {
        return (this.getCurrentPageNum()-1)*this.getPageSize();
    }
}
