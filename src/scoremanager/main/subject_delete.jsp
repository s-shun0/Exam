<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="scripts"></c:param>
  <c:param name="content">

<section class="me-4">
  <h2 class="h3 mb-3 fw-normal bg-danger bg-opacity-10 py-2 px-4">科目削除確認</h2>

  <form action="SubjectDeleteExecute.action" method="post" class="mx-4">
    <input type="hidden" name="cd" value="${subject.cd}">
    <p>以下の科目情報を削除してもよろしいですか？</p>

    <ul>
      <li>科目コード：${subject.cd}</li>
      <li>科目名：${subject.name}</li>
    </ul>

    <div class="text-center">
      <button type="submit" class="btn btn-danger">削除する</button>
    </div>
  </form>

  <div class="mt-3 text-center">
    <a href="SubjectList.action">戻る</a>
  </div>
</section>

</c:param>
</c:import>
