<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
<c:param name="title">
	得点管理システム
</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">

<section class="me-4">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
<form action="TestListExecuteAction" method="post">
	<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
	<div class="col-4">
			<label class="form-label" for="student-entyear-select">入学年度</label>
			<select class="form-select" id="student-entyear-select" name="entyear">
				<option value="0">--------</option>
				<c:forEach var="year" items="${ent_year_set}">
					<option value="${year}" <c:if test="${year == entyear}">selected</c:if>>${year}</option>
				</c:forEach>
			</select>
		</div>

		<div class="col-4">
			<label class="form-label" for="student-class-select">クラス</label>
			<select class="form-select" id="student-class-select" name="class">
				<option value="0">--------</option>
				<c:forEach var="num" items="${class_list}">
					<option value="${num}" <c:if test="${num == class}">selected</c:if>>${num}</option>
				</c:forEach>
			</select>
		</div>

		<div class="col-4">
			<label class="form-label" for="student-subject-select">科目</label>
			<select class="form-select" id="student-subject-select" name="subject">
				<option value="0">--------</option>
				<c:forEach var="num" items="${subject_list}">
					<option value="${num}" <c:if test="${num == subject}">selected</c:if>>${num}</option>
				</c:forEach>
			</select>
		</div>

	<div class="col-2 text-center">
		<button class="btn btn-secondary" id="filter-button">検索</button>
	</div>
	<div class="mt-2 text-warning">${errors.get("f1") }</div>
	</div>
</form>

</section>
</c:param>
</c:import>

