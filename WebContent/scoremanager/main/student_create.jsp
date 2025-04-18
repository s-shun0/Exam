<%-- 学生登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
<c:param name="title">
	得点管理システム
</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">

<section class="me-4">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生管理</h2>
<form action="StudentCreateExecute.action" method="post">
	<!-- 入学年度 -->
	<label class="form-label" for="student-f1-select">入学年度</label>
	<select class="form-select" id="f1" name="f1" required>
		<option value="0">--------</option>
		<c:forEach var="year" items="${ent_year_set}">
			<option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
		</c:forEach>
	</select>

	<!-- 学生番号 -->
	<label class="form-label" for="student-f2-input">学生番号</label>
	<input type="text" id="no" name="no" class="form-control" placeholder="学生番号を入力してください" required>

	<!-- 氏名 -->
	<label class="form-label" for="student-name-input">氏名</label>
	<input type="text" id="name" name="name" class="form-control" placeholder="氏名を入力してください" required>

	<!-- クラス -->
	<label class="form-label" for="student-f2-select">クラス</label>
	<select class="form-select" id="f2" name="f2">
		<option value="0">--------</option>
		<c:forEach var="num" items="${class_num_set}">
			<option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
		</c:forEach>
	</select>

	<!-- 登録して終了ボタン -->
	<div class="d-flex justify-content-start mt-3">
		<button type="submit" class="btn btn-primary btn-sm">登録して終了</button>
	</div>
</form>

<!-- 戻るリンク -->
<div class="mt-3">
	<a href="StudentList.action" class="btn btn-secondary btn-sm">戻る</a>
</div>
</section>
</c:param>
</c:import>