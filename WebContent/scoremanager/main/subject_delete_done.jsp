<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section>
            <h2 class="bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>
            <p class="text-center" style="background-color:#66CC99">削除が完了しました</p>

            <div class="mt-3">
                <a href="SubjectList.action">科目一覧</a>
            </div>
        </section>
    </c:param>
</c:import>
