<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>

		<div class="px-4">
		    <p class="text-center" style="background-color:#66CC99">変更が完了しました</p>
			<a href="SubjectList.action" class="me-3">科目一覧</a>
			<a href="Menu.action">戻る</a>
		</div>
	</c:param>
</c:import>
