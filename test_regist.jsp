<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
  <c:param name="title" value="得点管理システム" />
  <c:param name="scripts" value="" />
  <c:param name="content">
    <section class="me-4">
      <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
      <form action="TestRegist.action" method="post">
        <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
          <div class="col-3">
            <label class="form-label" for="student-entyear-select">入学年度</label>
            <select class="form-select" id="student-entyear-select" name="f1">
              <option value="0">--------</option>
              <c:forEach var="year" items="${ent_year_set}">
                <option value="${year}" <c:if test="${year == ent_year}">selected</c:if>>${year}</option>
              </c:forEach>
            </select>
          </div>

          <div class="col-3">
            <label class="form-label" for="student-class-select">クラス</label>
            <select class="form-select" id="student-class-select" name="f2">
              <option value="0">--------</option>
              <c:forEach var="num" items="${class_list}">
                <option value="${num}" <c:if test="${num == class_num}">selected</c:if>>${num}</option>
              </c:forEach>
            </select>
          </div>

          <div class="col-3">
            <label class="form-label" for="student-subject-select">科目</label>
            <select class="form-select" id="student-subject-select" name="f3">
              <option value="0">--------</option>
              <c:forEach var="num" items="${subject_list}">
                <option value="${num.name}" <c:if test="${num.name == subject}">selected</c:if>>${num.name}</option>
              </c:forEach>
            </select>
          </div>

          <!-- 回数 -->
          <div class="col-2">
            <label class="form-label" for="student-count-select">回数</label>
            <select class="form-select" id="student-count-select" name="f4">
              <option value="0">--------</option>
              <option value="1" <c:if test="${count == 1}">selected</c:if>>1</option>
              <option value="2" <c:if test="${count == 2}">selected</c:if>>2</option>
            </select>
          </div>

          <div class="col-1 text-center">
            <button class="btn btn-secondary" id="filter-button">検索</button>
          </div>
          <div class="mt-2 text-warning">${errors.get("f1") }</div>
        </div>
      </form>


      <c:choose>
        <c:when test="${test.size() > 0}">
          <div>科目:${subject}</div>
          <form action="TestRegistExecute.action" method="post">
 		 	<input type="hidden" name="num" value="${num }" />
 		 	<input type="hidden" name="class_num" value="${class_num }" />

          <table class="table table-hover">
            <tr>
              <th>入学年度</th>
              <th>クラス</th>
              <th>学生番号</th>
              <th>氏名</th>
              <th>点数</th>
            </tr>
            <c:forEach var="test" items="${test}">
              <tr>
                <th>${entYear}
                <input type = "hidden" name="ent_year" value="${entYear }"/> </th>
                <th>${test.classNum}
                <input type = "hidden" name="test.classNum" value="${test.classNum}"/> </th>
                <th>${test.student.no}
                	<input type = "hidden" name="test.studentNo" value="${test.student.no}"/> </th>
                <th>${test.student.name}</th>
                <td>
      				<input type="number" min="0" max="100" class="form-control" name="point" value="${test.point}" />
    			</td>
    			<!-- numの値をpostしたい -->
              </tr>
            </c:forEach>
          	</table>
          	<div class="text-end mt-3">
      			<button type="submit" class="btn btn-primary">登録して終了</button>
    		</div>
 		 </form>
        </c:when>
        <c:otherwise>
          <div>検索の条件を指定してください</div>
        </c:otherwise>
      </c:choose>
    </section>
  </c:param>
</c:import>
