package com.yuan.ioc.demo3;

import javax.annotation.Resource;

public class ProductService {

    @Resource(name = "productDao")
    private ProductDao productDao;

    @Resource(name = "catagoryDao")
    private CatagoryDao catagoryDao;

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public CatagoryDao getCatagoryDao() {
        return catagoryDao;
    }

    public void setCatagoryDao(CatagoryDao catagoryDao) {
        this.catagoryDao = catagoryDao;
    }

    public void save(){
        System.out.println("ProductService的save方法...");
        productDao.save();
        catagoryDao.save();
    }


}
