<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">教員変更</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">教員変更</h2>

            <form action="TeacherUpdateExecute.action" method="post" class="px-4">
                <div class="mb-3">
                    <label class="form-label">教員ID</label>
                    <p class="form-control-plaintext">${teacher.id}</p>
                    <input type="hidden" name="id" value="${teacher.id}">
                </div>
                <div class="mb-3">
                    <label class="form-label">パスワード</label>
                    <input type="password" name="password" class="form-control" value="${teacher.password}">
                </div>
                <div class="mb-3">
                    <label class="form-label">氏名</label>
                    <input type="text" name="name" class="form-control" value="${teacher.name}">
                </div>
                <div class="mb-3">
                    <label class="form-label">所属学校</label>
                    <select name="school_cd" class="form-select">
                        <c:forEach var="school" items="${schools}">
                            <option value="${school.cd}" <c:if test="${school.cd == teacher.school.cd}">selected</c:if>>${school.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="text-end">
                    <button class="btn btn-primary">変更実行</button>
                </div>
            </form>
        </section>
    </c:param>
</c:import>
