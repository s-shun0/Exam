<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
<c:param name="title">教員登録完了</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">

<section class="text-center">
    <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">教員登録完了</h2>

    <div class="mt-4">教員ID「${teacher.id}」の登録が完了しました。</div>

    <div class="mt-4">
        <a href="TeacherList.action">教員一覧に戻る</a>　
        <a href="Menu.action">メニューへ</a>
    </div>
</section>

</c:param>
</c:import>
