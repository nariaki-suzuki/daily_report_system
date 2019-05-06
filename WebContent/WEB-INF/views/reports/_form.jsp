<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test = "${error != null}">
    <div id = "flush_error">
        <h2>入力内容にエラーがあります</h2>
        <c:forEach var = "error" items = "${errors}">
            ・<c:out value = "${error}" /><br />
        </c:forEach>
    </div>
</c:if>
<label for = "report_date">日付</label>
<input type="date" name="report_date" value="<fmt:formatDate value='${report.report_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for = "name">氏名</label>
<c:out value = "${sessionScope.login_employee.name}" />
<br /><br />

<label for = "title">タイトル</label>
<input type = "text" name = "report_title" value = "${reports.title}" />
<br /><br />

<label for = "content">内容</label>
<textarea name = "content" rows = "10" cols = "50">${report.content}</textarea>
<br /><br />

<input type = "hidden" name = "_token" value = "${_token}" />
<button type = "submit">投稿</button>