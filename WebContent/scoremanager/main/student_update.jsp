<%-- 学生登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>


    <c:param name="content">
    <section class="me-4">
        <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>
        <form action="StudentUpdateExecute.action" method="post">
            <!-- 入学年度 -->
            <label class="form-label" for="student-f1-select">入学年度</label>
            <p class="form-control-plaintext">${ent_year}</p>
            <input type="hidden" name="ent_year" value="${ent_year}">

            <!-- 学生番号 -->
            <label class="form-label" for="student-f2-input">学生番号</label>
            <p class="form-control-plaintext">${no}</p>
            <input type="hidden" name="no" value="${no}">

            <!-- 氏名 -->
            <label class="form-label" for="student-name-input">氏名</label>
            <!-- Set the value of the input to the existing name -->
            <input type="text" id="name" name="name" class="form-control" placeholder="氏名" value="${name}" required>

            <!-- クラス -->
            <label class="form-label" for="student-f2-select">クラス</label>
            <select class="form-select" id="class_num" name="class_num">
                <option value="0">--------</option>
                <c:forEach var="num" items="${list}">
                    <!-- Set the selected class number -->
                    <option value="${num}" <c:if test="${num == class_num}">selected</c:if>>${num}</option>
                </c:forEach>
            </select>

            <!-- 在学中 -->
            <label class="form-label" for="student-attend-check">在学中</label>
            <input type="checkbox" id="attend" name="attend"
                <c:if test="${is_attend}">checked</c:if> >

            <!-- 変更ボタン -->
            <div class="d-flex justify-content-start mt-3">
                <button type="submit" name="login" class="btn btn-primary btn-sm">変更</button>
            </div>
        </form>

        <!-- 戻るリンク -->
        <div class="mt-3">
            <a href="student_list.jsp" class="btn btn-secondary btn-sm">戻る</a>
        </div>
    </section>
    </c:param>
</c:import>
