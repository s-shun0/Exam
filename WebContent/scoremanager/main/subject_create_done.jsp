<%-- 科目登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="scripts"></c:param>
  <c:param name="content">

    <section class="me-4">
      <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>

      <p class="text-center" style="background-color:#66CC99">登録が完了しました</p>

      <div class="text-center">
        <a href="SubjectCreate.action" class="me-3">戻る</a>
        <a href="SubjectList.action">科目一覧</a>
      </div>
    </section>

  </c:param>
</c:import>
