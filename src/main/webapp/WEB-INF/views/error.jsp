<%--
  Created by IntelliJ IDEA.
  User: swi
  Date: 2022-12-14
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
에러 - ${msg}<br>
<%--exception - ${exception}--%>
<!--
- timestamp : 오류 발생 시각
- status : HTTP 상태 코드
- error : 오류 발생 이유
- exception : 예외 클래스 이름
- message : 예외 메시지
- errors : BindingResult 예외로 발생한 모든 오류
- trace : 예외 스택 트레이스
- path : 오류가 발생했을때 요청한 URL 경로
-->

오류 발생 시각 : ${timestamp} <br>
HTTP 상태 코드 : ${status} <br>
오류 발생 이유 : ${error} <br>
예외 클래스 이름 : ${exception} <br>
예외 메시지 : ${message} <br>
오류가 발생했을때 요청한 URL 경로 : ${path} <br>
BindingResult 예외로 발생한 모든 오류 : ${errors} <br>
예외 스택 트레이스 : ${trace} <br>


</body>
</html>
