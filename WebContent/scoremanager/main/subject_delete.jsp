<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">

<section class="me-4">
  <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>
  <div class="px-4">
    <p>「${subject.name}（${subject.cd}）」を削除してもよろしいですか</p>

    <form action="SubjectDeleteExecute.action" method="post">
      <input type="hidden" name="cd" value="${subject.cd}">
      <button type="submit" class="btn btn-danger">削除</button>
    </form>

    <div class="mt-3">
      <a href="SubjectList.action">戻る</a>
    </div>
  </div>
</section>

</c:param>
</c:import>
