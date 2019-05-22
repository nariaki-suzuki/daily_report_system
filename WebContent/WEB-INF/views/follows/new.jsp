<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:import url = "../layout/app.jsp">
    <c:param name="content">
        <h2>フォロー　登録ページ</h2>

       <h2>従業員　一覧</h2>
        <table id="employee_list">
            <tbody>
                <tr>
                    <th>社員番号</th>
                    <th>氏名</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="employee" items="${employees}" varStatus="status">
                 <tr class="row${status.count % 2}">
                    <c:set var = "flag" value = "0" />
                    <c:forEach var = "follow" items = "${follows}">
                        <c:if test = "${follow.follow.code == employee.code}">
                            <c:set var = "flag" value = "1" />
                            <c:set var = "delete_flag" value = "${follow.id}" />
                        </c:if>
                    </c:forEach>
                        <c:if test = "${login_employee.id != employee.id}">
                            <td><c:out value="${employee.code}" /></td>
                            <td><c:out value="${employee.name}" /></td>
                            <td>
                            <c:choose>
                                <c:when test = "${flag == 0}">
                                    <a href = "<c:url value = '/follows/create?id=${employee.id}' />">フォローする</a>
                                </c:when>
                                <c:otherwise>
                                    <a href = "<c:url value = '/follows/destroy?id=${delete_flag}' />">フォローを解除する</a>
                                </c:otherwise>
                            </c:choose>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <p><a href = "<c:url value = '/follows/index ' />">一覧に戻る</a></p>
    </c:param>
</c:import>