<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">
<section class="me-4">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>

<form action="SubjectCreateExecute.action" method="post">
	<div class="px-4">

		<label class="form-label">科目コード</label>
		<input class="form-control" type="text" name="cd" value="${subject.cd}" placeholder="科目コードを入力してください" required />
		<div class="text-warning">${errors.cd}</div>

		<label class="form-label mt-3">科目名</label>
		<input class="form-control" type="text" name="name" value="${subject.name}" placeholder="科目名を入力してください" required />
		<div class="text-warning">${errors.name}</div>

		<div class="mt-4">
			<button class="btn btn-primary" type="submit">登録</button>
		</div>
	</div>
</form>

<div class="px-4 mt-3">
	<a href="SubjectList.action">戻る</a>
</div>
</section>
</c:param>
</c:import>
