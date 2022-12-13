<%--
  Created by IntelliJ IDEA.
  User: swi
  Date: 2022-12-05
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form name="frm" action="/login/process" method="post">
    <label>
        아이디:<input type="text" name="loginId" value="____MASTER____">
    </label>
    <label>
        비밀번호:<input type="text" name="password" value="@1234">
    </label>
    <label>
        <input type="button" name="loginBtn" value="로그인" onclick="form.submit();">
    </label>

</form>
</body>
</html>
