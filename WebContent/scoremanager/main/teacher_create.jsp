<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
<c:param name="title">教員登録</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">

<section class="mx-4">
	<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">教員登録</h2>

	<form action="TeacherCreateExecute.action" method="post" class="mx-4">
		<div class="mb-3">
			<label class="form-label">ID</label>
			<input type="text" name="id" class="form-control" placeholder="例：t0001" required />
		</div>

		<div class="mb-3">
			<label class="form-label">パスワード</label>
			<input type="password" name="password" class="form-control" placeholder="英数字8文字以上" required />
		</div>

		<div class="mb-3">
			<label class="form-label">氏名</label>
			<input type="text" name="name" class="form-control" placeholder="例：山田 太郎" required />
		</div>

		<div class="text-center my-4">
			<button type="submit" class="btn btn-primary px-5">登録</button>
		</div>
	</form>

	<div class="text-center mt-3">
		<a href="TeacherList.action">一覧に戻る</a>
	</div>
</section>

</c:param>
</c:import>
