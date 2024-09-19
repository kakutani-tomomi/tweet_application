<%@page import="model.entity.TweetBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
TweetBean tweet = (TweetBean) request.getAttribute("tweet");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tweet編集</title>
</head>
<body>
	<div>
		<h1>tweet編集</h1>
		<form action="tweet-edit" method="post">
			<div>
				<p>名前：<%=tweet.getName()%></p>
			</div>
			<div>
				<label for="message">メッセージ</label>
				<textarea name="message" id="message"><%=tweet.getMessage()%></textarea>
			</div>
			<div>
				<button type="submit">更新</button>
				<input type="hidden" name="id" value="<%=tweet.getId()%>">
				<a href="tweet-list">戻る</a>
			</div>
		</form>
	</div>
</body>
</html>
