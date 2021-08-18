<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 회원정보를 입력받는 JSP 문서 --%>
<%-- => [회원가입]을 클릭한 경우 회원정보 저장 처리페이지(member_join_action.jsp)로 이동 - 회원정보 전달 --%>
<%-- => 회원정보 저장 처리페이지 이동 전 사용자 입력값에 대한 검증 작증 : 입력검증, 형식검증, 중복검증(AJAX) 등 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajax.js"></script>
<style type="text/css">
.title {
	width: 100px;
	padding-right: 10px;
	text-align: center;
	font-weight: bold;
}

.input {
	width: 200px;
}

.message {
	width: 500px;
	color: red;
}

.msg {
	display: none;
}

#btn {
	text-align: center;
}
</style>
</head>
<body>
	<h1>회원가입</h1>
	<hr>
	<form action="member_join_action.jsp" method="post" id="joinForm">
	<table>
		<tr>
			<td class="title">아이디</td>
			<td class="input"><input type="text" name="id" id="id"></td>
			<td class="message">
				<div>
					<span id="idNullMsg" class="msg idMsg">아이디를 입력해 주세요.</span>
					<span id="idValidMsg" class="msg idMsg">아이디를 형식에 맞게 입력해 주세요.</span>
					<span id="idDuplMsg" class="msg idMsg">이미 사용중인 아이디입니다.</span>
				</div>
			</td>
		</tr>
		<tr>
			<td class="title">비밀번호</td>
			<td class="input"><input type="password" name="passwd" id="passwd"></td>
			<td class="message">
				<div>
					<span id="passwdNullMsg" class="msg">비밀번호를 입력해 주세요.</span>
					<span id="passwdValidMsg" class="msg">비밀번호를 형식에 맞게 입력해 주세요.</span>
				</div>
			</td>
		</tr>
		<tr>
			<td class="title">이름</td>
			<td class="input"><input type="text" name="name" id="name"></td>
			<td class="message">
				<div>
					<span id="nameNullMsg" class="msg">이름을 입력해 주세요.</span>
					<span id="nameValidMsg" class="msg">이름을 형식에 맞게 입력해 주세요.</span>
				</div>
			</td>
		</tr>
		<tr>
			<td class="title">이메일</td>
			<td class="input"><input type="text" name="email" id="email"></td>
			<td class="message">
				<div>
					<span id="emailNullMsg" class="msg">이메일을 입력해 주세요.</span>
					<span id="emailValidMsg" class="msg">이메일을 형식에 맞게 입력해 주세요.</span>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2" id="btn">
				<button type="submit">회원가입</button>
			</td>
		</tr>
	</table>
	</form>
	
	<script type="text/javascript">
	document.getElementById("id").focus();
	
	document.getElementById("joinForm").onsubmit=function() {
		//모든 메세지 관련 박스모델 미배치
		var msgList=document.querySelectorAll(".msg");
		for(var i=0;i<msgList.length;i++) {
			msgList.item(i).style.display="none";
		}
		
		//입력값 검증결과를 저장하기 위한 변수
		// => false : 검증 실패(서브밋 취소), true : 검증 성공
		var validResult=true;
		
		var id=document.getElementById("id").value;
		var idReg=/^[a-zA-Z]\w{5,19}$/g;
		if(id=="") {
			document.getElementById("idNullMsg").style.display="block";
			validResult=false;
		} else if(!idReg.test(id)) {
			document.getElementById("idValidMsg").style.display="block";
			validResult=false;
		}
		
		var passwd=document.getElementById("passwd").value;
		var passwdReg=/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*_-]).{6,20}$/g;
		if(passwd=="") {
			document.getElementById("passwdNullMsg").style.display="block";
			validResult=false;
		} else if(!passwdReg.test(passwd)) {
			document.getElementById("passwdValidMsg").style.display="block";
			validResult=false;
		}
		
		var name=document.getElementById("name").value;
		var nameReg=/^[가-힣]{2,10}$/g;
		if(name=="") {
			document.getElementById("nameNullMsg").style.display="block";
			validResult=false;
		} else if(!nameReg.test(name)) {
			document.getElementById("nameValidMsg").style.display="block";
			validResult=false;
		}
		
		var email=document.getElementById("email").value;
		var emailReg=/^([a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+(\.[-a-zA-Z0-9]+)+)*$/g;
		if(email=="") {
			document.getElementById("emailNullMsg").style.display="block";
			validResult=false;
		} else if(!emailReg.test(email)) {
			document.getElementById("emailValidMsg").style.display="block";
			validResult=false;
		}

		//validResult 변수에 false가 저장된 경우 서브밋 이벤트 취소
		return validResult;
	}
	</script>
</body>
</html>