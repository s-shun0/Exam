<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
  <c:param name="title" value="得点管理システム" />
  <c:param name="scripts" value="" />
  <c:param name="content">
    <section class="me-4">
      <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>

      <form action="TestListSubjectExecute.action" method="post">
       <label class="form-label">科目情報</label>
        <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
          <div class="col-3">
            <label class="form-label" for="student-entyear-select">入学年度</label>
            <select class="form-select" id="student-entyear-select" name="ent_year">
              <option value="0">--------</option>
              <c:forEach var="year" items="${ent_year_set}">
                <option value="${year}" <c:if test="${year == ent_year}">selected</c:if>>${year}</option>
              </c:forEach>
            </select>
          </div>

          <div class="col-3">
            <label class="form-label" for="student-class-select">クラス</label>
            <select class="form-select" id="student-class-select" name="class_num">
              <option value="0">--------</option>
              <c:forEach var="num" items="${class_list}">
                <option value="${num}" <c:if test="${num == class_num}">selected</c:if>>${num}</option>
              </c:forEach>
            </select>
          </div>

          <div class="col-3">
            <label class="form-label" for="student-subject-select">科目</label>
            <select class="form-select" id="student-subject-select" name="subject">
              <option value="0">--------</option>
              <c:forEach var="num" items="${subject_list}">
                <option value="${num.name}" <c:if test="${num.name == subject}">selected</c:if>>${num.name}</option>
              </c:forEach>
            </select>
          </div>
          <div class="col-1 text-center">
            <button class="btn btn-secondary" id="filter-button" name="button">検索</button>
          </div>
          <div class="mt-2 text-warning">${errors.get("f1") }</div>
        </div>
      </form>


      <!-- 学生情報フォーム -->
      <form action="TestListStudentExecute.action" method="post" class="my-3">
        <label class="form-label me-2 mb-0">学生情報</label>
        <div class="d-flex align-items-center">
          <input type="text" name="student_no" class="form-control me-2" value="${student_no}" placeholder="学生番号を入力">
          <button class="btn btn-secondary" id="filter-button" name="button">検索</button>
        </div>
        <div class="mt-2 text-warning">${errors.get("f1") }</div>
      </form>

      <!-- 結果表示 -->
      <c:choose>
        <c:when test="${list.size() > 0}">
          <div>氏名: ${student_name} (${student_no})</div>
          <table class="table table-hover">
            <tr>
              <th>科目名</th>
              <th>科目コード</th>
              <th>回数</th>
              <th>点数</th>
            </tr>
            <c:forEach var="student" items="${list}">
              <tr>
                <th>
                  ${student.subjectName}
                  <input type="hidden" name="ent_year" value="${student.subjectName}" />
                </th>
                <th>
                  ${student.subjectCd}
                  <input type="hidden" name="test.classcd" value="${student.subjectCd}" />
                </th>
                <th>
                  ${student.num}
                  <input type="hidden" name="test.num" value="${student.num}" />
                </th>
                <th>
                  ${student.point}
                  <input type="hidden" name="point" value="${student.point}" />
                </th>
              </tr>
            </c:forEach>
          </table>
        </c:when>
        <c:otherwise>
          <div>検索の条件を指定してください</div>
        </c:otherwise>
      </c:choose>
    </section>
  </c:param>
</c:import>
