<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name = "content">
        <c:if test = "${flush != null}">
            <c:forEach var = "error" items = "errors">
                <div id="flush_success">
                     <c:out value="${flush}"></c:out>
                </div>
            </c:forEach>
        </c:if>
        <h2>フォロー　一覧</h2>
        <table id = "follow_list">
            <tbody>
                <tr>
                    <th class = "follow_name">フォローしている社員</th>
                    <th class = "follow_action">操作</th>
                </tr>
                <c:forEach var = "follow" items = "${follows}" varStatus = "status">
                    <tr class = "row${status.count % 2}">
                        <td class = "follow_name"><c:out value = "${follow.follow.name}" /></td>
                        <td class = "follow_action"><a href = "<c:url value = '/follows/show?id=${follow.follow.id}' />">日報を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id = "pagination">
            (${follows_count} 人 フォロー中)
            <c:forEach var = "i" begin = "1" end = "${((follow.count - 1) / 15 ) + 1}" step = "1">
                <c:choose>
                <c:when test = "${i == page}">
                    <c:out value = "${i}" />&nbsp;
                </c:when>
                <c:otherwise>
                    <a href = "<c:url value = '/follows/index?page=${i}' />"><c:out value = "${i}" /></a>&nbsp;
                </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <p><a href = "<c:url value = '/follows/new' />">フォロー社員の登録</a></p>
    </c:param>
</c:import>