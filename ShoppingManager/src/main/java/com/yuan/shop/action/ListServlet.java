package com.yuan.shop.action;

import com.yuan.shop.bean.Article;
import com.yuan.shop.bean.ArticleType;
import com.yuan.shop.bean.Pager;
import com.yuan.shop.service.ShopService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@WebServlet("/list")
public class ListServlet extends HttpServlet {

    /*
    注意这里不能使用注解的形式(如@Autowired、@Resource)对业务层对象进行注入，
    因为Servlet本身对象是没有交给Spring容器对象管理的
     */
//    @Resource
    private ShopService shopService = null;//定义业务层对象

    private HttpServletRequest request;
    private HttpServletResponse response;

    //在Servlet启用之前会调用这个方法
    @Override
    public void init() throws ServletException {
        super.init();
        //在这个方法中获取Spring容器，然后从容器中得到业务层对象
        ServletContext servletContext = this.getServletContext();//首先获得ServletContext上下文对象
        //获取一个Web目录的上下文对象，这其实就是一个Spring容器对象
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        //通过Spring容器得到这个bean（对象），即这个业务层对象
        shopService = (ShopService) webApplicationContext.getBean("shopService");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            this.request = req;
            this.response = res;
            //再获取元素内容之前先设置编码格式
            request.setCharacterEncoding("UTF-8");
            //response.setCharacterEncoding("UTF-8");

            String method = request.getParameter("method");

            switch (method) {
                case "getAll":
                    getAll();//当获取的方式是“getAll”，则调用getAll()方法
                    break;
                case "deleteById":
                    deleteById();
                    break;
                case "preArticle":
                    preArticle();
                    break;
                case "showUpdate":
                    showUpdate();
                    break;
                case "updateArticle":
                    updateArticle();
                    break;
                case "addArticle":
                    addArticle();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addArticle() throws ServletException, IOException, ParseException {
        //获取修改页面提交的信息
        String code = request.getParameter("code");
        String title = request.getParameter("titleStr");
        String supplier = request.getParameter("supplier");
        String locality = request.getParameter("locality");
        String price = request.getParameter("price");
        String putawayDate=request.getParameter("putawayDate");//上架日期
        String storage = request.getParameter("storage");
        String description = request.getParameter("description");

        String imgageUrl = receiveImage();//获取商品封面的地址

        //定义一个商品对象封装界面提交的数据
        Article article = new Article();
        //创建商品类型实体(并将数据封装到商品类型实体中)
        ArticleType articleType = new ArticleType();
        articleType.setCode(code);

        //设置发布时间 2017-10-12 13:20:12 进行时间格式的转换
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        article.setPutawayDate(simpleDateFormat.parse(putawayDate));//转化成Date类型，再设置值

        //把数据封装到商品实体中
        article.setArticleType(articleType);
        article.setTitle(title);
        article.setSupplier(supplier);
        article.setLocality(locality);
        article.setPrice(Double.parseDouble(price));
        article.setStorage(Integer.parseInt(storage));
        article.setImage(imgageUrl);
        article.setDescription(description);


        //将封装了修改数据的商品对象传递给updateArticle方法，完成对数据库的更新
        shopService.saveArticle(article);
        request.setAttribute("tip", "添加商品成功");

        getAll();
    }

    //修改商品信息
    private void updateArticle() throws FileUploadException {
//        try{
//            //判断上传类型是否正确
//            if (!ServletFileUpload.isMultipartContent(request)){
//                throw new RuntimeException("not multipart/form-data");
//            }
//
//            // 1.创建一个磁盘文件项工厂对象
//            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
//            // 2.创建一个核心解析类
//            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
//            // 3.解析request请求，返回的是List集合，List集合中存放的是FileItem对象
//            List<FileItem> list = servletFileUpload.parseRequest(request);
//            //4.通过遍历FileItem取值表单项中的值。
//            for (FileItem fileItem:list)
//            {
//                if(fileItem.isFormField())//如果是普通的表单项
//                {
//                    String code=fileItem.getString("code");
//                    String title=fileItem.getString("titleStr");
//                    String supplier=fileItem.getString("supplier");
//                    String locality=fileItem.getString("locality");
//                    String price=fileItem.getString("price");
//                    String storage=fileItem.getString("storage");
//                    String description=fileItem.getString("description");
//                    String id=fileItem.getString("id");
//                    String picUrl=fileItem.getString("picUrl");
//                }else { //如果是需要上传文件的表单项
//
//                    String code=fileItem.getString("code");
//                    String title=fileItem.getString("titleStr");
//                    String supplier=fileItem.getString("supplier");
//                    String locality=fileItem.getString("locality");
//                    String price=fileItem.getString("price");
//                    String storage=fileItem.getString("storage");
//                    String description=fileItem.getString("description");
//                    String id=fileItem.getString("id");
//                    String picUrl=fileItem.getString("picUrl");
//
//                     /*
//                        如果用户上传了封面，则不会出现异常；
//                        如果没有上传，则这里出现异常
//                     */
//                    Part part=request.getPart("image");
//                    /*
//                    保存到项目的路径中去
//                     */
//                    //获取项目的路径
//                    String sysPath=request.getSession().getServletContext().getRealPath("/resources/images/article");
//                    //定义一个新的图片名称
//                    String fileName=UUID.randomUUID().toString();//通过UUID的形式生成一个随机的名称
//                    //提取图片的类型（后缀）
//                    //上传文件的内容性质
//                    String contentDisposition=part.getHeader("content-disposition");//从图片的请求头中拿到”content-disposition“这样一个参数的值
//                    //获取上传文件的后缀名(从最后一个“.”开始截取，直到最后一个字符)
//                    String suffix=contentDisposition.substring(contentDisposition.lastIndexOf("."),contentDisposition.length()-1);
//                    fileName+=suffix;//图片名称加上后缀
//                    //把图片保存到路径中去
//                    part.write(sysPath+"/"+fileName);
//                    picUrl=fileName;
//                }
//            }
//
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }

        //定义一个商品对象封装界面提交的数据
        Article article = new Article();
        //获取修改页面提交的信息
        String code = request.getParameter("code");
        String title = request.getParameter("titleStr");
        String supplier = request.getParameter("supplier");
        String locality = request.getParameter("locality");
        String price = request.getParameter("price");
        String storage = request.getParameter("storage");
        String description = request.getParameter("description");
        String id = request.getParameter("id");//商品编号
        String picUrl = request.getParameter("picUrl");//商品旧封面地址
        System.out.println(id);
        System.out.println("价格" + price);

        String newImgage = receiveImage();
        picUrl = newImgage != null ? newImgage : picUrl;

        article.setId(Integer.parseInt(id));
        article.setImage(picUrl);

        //创建商品类型实体(并将数据封装到商品类型实体中)
        ArticleType articleType = new ArticleType();
        articleType.setCode(code);

        //把数据封装到商品实体中
        article.setArticleType(articleType);
        article.setTitle(title);
        article.setSupplier(supplier);
        article.setLocality(locality);
        article.setPrice(Double.parseDouble(price));
        article.setStorage(Integer.parseInt(storage));
        article.setDescription(description);


        //将封装了修改数据的商品对象传递给updateArticle方法，完成对数据库的更新
        shopService.updateArticle(article);
        request.setAttribute("tip", "修改商品成功");

        showUpdate();

    }

    private String receiveImage() {
        try {
            /*
                如果用户上传了封面，则不会出现异常；
                如果没有上传，则这里出现异常
             */
            Part part = request.getPart("image");
            /*
            保存到项目的路径中去
             */
            //获取项目的路径
            String sysPath = request.getSession().getServletContext().getRealPath("/resources/images/article");
            //定义一个新的图片名称
            String fileName = UUID.randomUUID().toString();//通过UUID的形式生成一个随机的名称
            //提取图片的类型（后缀）
            //上传文件的内容性质
            String contentDisposition = part.getHeader("content-disposition");//从图片的请求头中拿到”content-disposition“这样一个参数的值
            //获取上传文件的后缀名(从最后一个“.”开始截取，直到最后一个字符)
            String suffix = contentDisposition.substring(contentDisposition.lastIndexOf("."), contentDisposition.length() - 1);
            fileName += suffix;//图片名称加上后缀
            //把图片保存到路径中去
            part.write(sysPath + "/" + fileName);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //展示修改商品信息
    private void showUpdate() {
        try{
            String id=request.getParameter("id");
            Article article=shopService.getArticleById(id);//根据id获取商品
            //查询出所有的商品类型
            List<ArticleType> articleTypes=shopService.getArticleTypes();
            request.setAttribute("article",article);//将查询到的商品对象传送到request域中
            //将查询出来的商品类型传到request域中，用于显示修改信息的时候类型的回写
            request.setAttribute("types",articleTypes);
            //转发到预览商品页面
            request.getRequestDispatcher("/WEB-INF/jsp/updateArticle.jsp").forward(request,response);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    //预览商品
    private void preArticle() {
        try{
            String id=request.getParameter("id");
            Article article=shopService.getArticleById(id);
            request.setAttribute("article",article);//将查询到的商品对象传送到request域中
            //转发到预览商品页面
            request.getRequestDispatcher("/WEB-INF/jsp/preArticle.jsp").forward(request,response);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    //删除商品（下架商品）
    private void deleteById() throws ServletException, IOException {
        try{
            String id=request.getParameter("id");//获取删除的商品编号
            shopService.deleteById(id);//根据商品编号删除商品
            request.setAttribute("tip","删除成功！");
        }catch (Exception e)
        {
            request.setAttribute("tip","删除失败");
            e.printStackTrace();
        }
        //删除成功后还要刷新
        request.getRequestDispatcher("/list?method=getAll").forward(request,response);
    }

    private void getAll() throws ServletException, IOException {

        Pager pager=new Pager();//实例化一个分页数据对象

        //获取当前页码
        String pageIndex=request.getParameter("pageIndex");

        if (!StringUtils.isEmpty(pageIndex))//如果获取的当前页码不为空
        {
            pager.setCurrentPageNum(Integer.parseInt(pageIndex));
        }

        //获取二级类型参数
        String secondType=request.getParameter("secondType");
        //获取搜索框中输入的标题
        String title=request.getParameter("title");

        //把用户点击的二级类型也传过去，以实现下拉框的二级类型的选中显示
        request.setAttribute("secondType",secondType);
        //把用户输入在搜索框中的标题回传回去
        request.setAttribute("title",title);

        //获取一级类型参数
        String typeCode=request.getParameter("typeCode");

        if (!StringUtils.isEmpty(typeCode))//如果所获取的商品类型号不为空
        {
            //根据商品的一级类型获取商品的二级类型(二级类型编号是一级类型编号的长度加4)
            List<ArticleType> secondArticleTypes=shopService.loadSecondArticleTypes(typeCode,typeCode.length()+4);
            request.setAttribute("typeCode",typeCode);//注意：把一级类型也传过去，因为二级类型查询需要根据一级类型（否则会出错）
            request.setAttribute("secondTypes",secondArticleTypes);
        }

        //查询商品的一级类型信息
        List<ArticleType> firstArticleTypes= shopService.loadFirstArticleTypes();

        //查询所有商品
        List<Article> articles=shopService.SearchArticles(typeCode,secondType,title,pager);

        //获取所有的商品类型并传到request域中，用于添加商品时的类型选择框的选择
        request.setAttribute("articleTypes",shopService.getArticleTypes());
        request.setAttribute("firstArticleTypes",firstArticleTypes);
        request.setAttribute("pager",pager);//把分页数据对象传过去
        request.setAttribute("articles",articles);


        request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request,response);

    }
}
