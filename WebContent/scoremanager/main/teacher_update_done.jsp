<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">教員変更完了</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4 text-center">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">教員情報の変更完了</h2>
            <p class="text-center" style="background-color:#66CC99">${teacher.name}（ID：${teacher.id}）の情報を変更しました。</p>
            <div>
                <a href="TeacherList.action" class="btn btn-secondary">一覧に戻る</a>
            </div>
        </section>
    </c:param>
</c:import>
