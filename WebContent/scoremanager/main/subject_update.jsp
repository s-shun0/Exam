<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>

		<form action="SubjectUpdateExecute.action" method="post" class="px-4">
			<div class="mb-3">
				<label for="cd" class="form-label">科目コード</label>
				<p class="form-control-plaintext">${subject.cd}</p>
				<input type="hidden" name="cd" value="${subject.cd}" />
			</div>

			<div class="mb-3">
				<label for="name" class="form-label">科目名</label>
				<input type="text" class="form-control" id="name" name="name"
					value="${subject.name}" required />
			</div>

			<button type="submit" class="btn btn-primary">変更</button>
			<a href="SubjectList.action" class="ms-3">戻る</a>
		</form>
	</c:param>
</c:import>
