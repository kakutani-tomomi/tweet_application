<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tweet登録</title>
</head>
<body>
	<div>
		<h1>tweet登録</h1>
		<form action="tweet-register" method="post">
			<div>
				<label for="title">名前</label><br>
				<input type="text" name="name" id="title">
			</div>
			<div>
				<label for="message">メッセージ</label><br>
				<textarea name="message" id="message"></textarea>
			</div>
			<div>
				<button type="submit">登録</button>
				<a href="tweet-list">戻る</a>
			</div>
		</form>
	</div>
</body>
</html>
