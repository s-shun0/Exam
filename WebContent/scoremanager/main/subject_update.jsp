<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">

<section class="me-4">
  <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>

  <form action="SubjectUpdateExecute.action" method="post" class="mx-4">
    <!-- 科目コード（不可變更） -->
    <div class="mb-3">
      <label class="form-label">科目コード</label>
      <input type="text" class="form-control" value="${subject.cd}" disabled>
      <input type="hidden" name="cd" value="${subject.cd}">
    </div>

    <!-- 科目名 -->
    <div class="mb-3">
      <label for="name" class="form-label">科目名</label>
      <input type="text" name="name" id="name" class="form-control"
             value="${subject.name}" required maxlength="20">
      <c:if test="${not empty errors.name}">
        <div class="text-warning">${errors.name}</div>
      </c:if>
    </div>

    <div class="text-center">
      <button type="submit" class="btn btn-primary">変更</button>
    </div>
  </form>

  <div class="mt-3 text-center">
    <a href="SubjectList.action">戻る</a>
  </div>
</section>

</c:param>
</c:import>
