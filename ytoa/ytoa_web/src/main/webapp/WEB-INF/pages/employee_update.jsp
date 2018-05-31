<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="top.jsp"/>
<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 修改员工 </h2>
            <p class="lead"></p>
        </div>
        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
            <div class="panel heading-border">
                <!--modelAttribute表示""-->
                <form:form action="/employee/update" modelAttribute="employee"  id="admin-form" name="addForm">
                    <div class="panel-body bg-light">
                        <div class="section-divider mt20 mb40">
                            <span> 基本信息 </span>
                        </div>
                        <form:hidden path="password"/>
                        <div class="section row">
                            <div class="col-md-6">
                                <label for="ename" class="field prepend-icon">
                                    <!--path="ename"表示id="ename"和 name="ename"-->
                                    <form:input path="ename" cssClass="gui-input" placeholder="编号..." readonly="true"/>
                                    <label for="ename" class="field-icon">
                                        <i class="fa fa-user"></i>
                                    </label>
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="name" class="field prepend-icon">
                                    <form:input path="name" cssClass="gui-input" placeholder="姓名..." />
                                    <label for="name" class="field-icon">
                                        <i class="fa fa-user"></i>
                                    </label>
                                </label>
                            </div>
                        </div>
                        <div class="section">
                            <div class="section row">
                                <div class="col-md-6">
                                    <%--????--%>
                                    <label for="ename" class="field select">
                                        <form:select path="departmentName" items="${dlist}" itemLabel="name" itemValue="dname" cssClass="gui-input" placeholder="所属部门..."/>
                                        <i class="arrow double"></i>
                                    </label>
                                </div>
                                <div class="col-md-6">
                                    <label for="post" class="field select">
                                        <form:select path="post" items="${plist}" cssClass="gui-input" placeholder="职务..."/>
                                        <i class="arrow double"></i>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer text-right">
                            <button type="submit" class="button"> 保存 </button>
                            <button type="button" class="button" onclick="javascript:window.history.go(-1);"> 返回 </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</section>

<jsp:include page="bottom.jsp"/>