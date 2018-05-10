package com.yuan.pagehelper.util;

import java.util.List;

public class PageUtil {
    private int currentPageNum;//当前要查看那一页

    private int pageSize=10;//每页显示记录的条数

    private int totalSize;//总记录条数

    private int startIndex;//查询开始记录的索引 limit ？ ？

    private int totalPageNum;//总页数

    private int prePageNum;//上一页

    private int nextPageNum;//下一页

    private List records;//当前页的记录集


    //用于显示页面上的导航的页号，用户可以自定义

    private int startPageNum;//导航开始的页数

    private int endPageNum;//导航结束的页数

    private String url;

    //使用构造方法，传递必要的两个参数，第一个是当前页码，第二个是总记录条数
    /**
     *
     * @param currentPageNum 当前页码
     * @param totalRecords 总记录的条数
     */
    public PageUtil(int currentPageNum,int totalRecords)
    {
        this.currentPageNum=currentPageNum;
        this.totalSize=totalRecords;

        //计算开始记录索引:（当前页码-1）*每页的记录条数
        startIndex=(currentPageNum-1)*pageSize;

        /*
        计算总页数
        如果总记录条数除以每页记录条数等于0，则总页数=总记录条数 / 每页记录条数；
        否则 总页数=总记录数 / 每页记录条数 + 1 ;
         */
        totalPageNum=totalSize%pageSize==0?totalSize/pageSize:(totalSize/pageSize+1);

        //计算开始和结束页号，这个根据自身需要设计
        if (totalPageNum>9)//当总页数大于9页（总页数大于等于10页）
        {   //让当前页码处于导航页码的中心位置
            startPageNum=currentPageNum-4;
            endPageNum=currentPageNum+4;

            //如果导航的开始页码小于1，即为0(导航页在首页)；
            if (startPageNum<1)
            {
                startPageNum=1;
                endPageNum=startPageNum+8;
            }
            //如果导航的结束页大于总页数（导航页在尾页）
            if (endPageNum>totalPageNum)
            {
                endPageNum=totalPageNum;
                startPageNum=endPageNum-8;
            }
        }
        else //总页数小于等于9页时
        {
            startPageNum=1;
            endPageNum=totalPageNum;
        }
    }

    public int getCurrentPageNum() {
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

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public int getPrePageNum() {
        prePageNum=currentPageNum-1;

        if (prePageNum<=0)//如果当前页为首页（即第一页）时
        {
            prePageNum=1;
        }
        return prePageNum;
    }

    public void setPrePageNum(int prePageNum) {
        this.prePageNum = prePageNum;
    }

    public int getNextPageNum() {

        nextPageNum=currentPageNum+1;
        if (nextPageNum > totalPageNum)
        {//如果当前页为最后一页
            nextPageNum=totalPageNum;
        }
        return nextPageNum;
    }

    public void setNextPageNum(int nextPageNum) {
        this.nextPageNum = nextPageNum;
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }

    public int getStartPageNum() {
        return startPageNum;
    }

    public void setStartPageNum(int startPageNum) {
        this.startPageNum = startPageNum;
    }

    public int getEndPageNum() {
        return endPageNum;
    }

    public void setEndPageNum(int endPageNum) {
        this.endPageNum = endPageNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
