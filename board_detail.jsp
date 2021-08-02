<%@page import="xyz.itwill.dto.MemberDTO"%>
<%@page import="xyz.itwill.dao.BoardDAO"%>
<%@page import="xyz.itwill.dto.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 게시글번호를 전달받아 BOARD 테이블에 저장된 해당 글번호의 게시글을 검색하여 클라이언트
에게 전달하는 JSP 문서 --%>
<%
	//비정상적이 요청에 대한 처리
	if(request.getParameter("num")==null) {
		out.println("<script type='text/javascript'>");
		out.println("location.href='"+request.getContextPath()+"/site/index.jsp?workgroup=error&work=error400'");
		out.println("</script>");
		return;		
	}

	//전달값을 반환받아 저장
	int num=Integer.parseInt(request.getParameter("num"));
	String pageNum=request.getParameter("pageNum");
	String search=request.getParameter("search");
	String keyword=request.getParameter("keyword");

	//글번호를 전달받아 BOARD 테이블에 저장된 게시글을 검색하여 반환하는 DAO 클래스의 메소드 호출
	BoardDTO board=BoardDAO.getDAO().selectNumBoard(num);
	
	//검색된 게시글이 없거나 검색된 게시글이 삭제글인 경우 - 비정상적인 요청
	if(board==null || board.getStatus()==9) {
		out.println("<script type='text/javascript'>");
		out.println("location.href='"+request.getContextPath()+"/site/index.jsp?workgroup=error&work=error400'");
		out.println("</script>");
		return;
	}
	
	//세션에 저장된 권한 관련 정보(회원정보)를 반환받아 저장
	MemberDTO loginMember=(MemberDTO)session.getAttribute("loginMember");
	
	//검색된 게시글이 비밀글인 경우
	if(board.getStatus()==1) {
		//비로그인 사용자이거나 로그인 사용자가 작성자 또는 관리자가 아닌 경우 - 비정상적인 경우 
		if(loginMember==null || !loginMember.getId().equals(board.getId())
				&& loginMember.getStatus()!=9) {
			out.println("<script type='text/javascript'>");
			out.println("location.href='"+request.getContextPath()+"/site/index.jsp?workgroup=error&work=error400'");
			out.println("</script>");
			return;
		}
	}
	
	//글번호를 전달받아 BOARD 테이블에 저장된 해당 글번호의 게시글 조회수를 증가하는 DAO 클래스의 메소드 호출
	BoardDAO.getDAO().updateReadCount(num);
%>    
<style type="text/css">
#board_detail {
	width: 500px;
	margin: 0 auto;
}

table {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	border: 1px solid black;
	padding: 5px;
}

th { width: 100px; }

td { width: 400px; }

.subject, .content { text-align: left; }
	
.content {
	height: 100px;
	vertical-align: middle;
}

#board_menu {
	text-align: right;
	margin: 5px;
}
</style>

<div id="board_detail">
	<h2>게시글</h2>
	<table>
		<tr>
			<th>작성자</th>
			<td>
				<%=board.getWriter() %>
				<% if(loginMember!=null && loginMember.getStatus()==9) {//관리자인 경우 %>
					[<%=board.getIp() %>]
				<% } %>
			</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><%=board.getRegDate() %></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%=board.getReadcount()+1 %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td class="subject">
				<% if(board.getStatus()==1) {//비밀글인 경우 %>
					[비밀글]
				<% } %>
				<%=board.getSubject() %>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td class="content"><%=board.getContent().replace("\n", "<br>") %></td>
		</tr>	
	</table>
	
	<div id="board_menu">
		<%-- 로그인 사용자가 작성자이거나 관리자인 경우에만 삭제와 변경 기능 제공 --%>
		<% if(loginMember!=null && (loginMember.getId().equals
				(board.getId()) || loginMember.getStatus()==9)) { %>
			<button type="button" id="removeBtn">글삭제</button>
			<button type="button" id="updateBtn">글변경</button>
		<% } %>		
		<%-- 로그인 사용자인 경우에만 답글 기능 제공 --%>
		<% if(loginMember!=null) { %>
			<button type="button" id="replyBtn">답글쓰기</button>
		<% } %>
		<button type="button" id="listBtn">글목록</button>
	</div>
	
	<form id="boardForm" method="post">
		<%-- 게시글 변경 입력페이지와 삭제 처리 페이지 요청시 전달값 --%>
		<input type="hidden" name="num" value="<%=board.getNum()%>">
		<%-- 답글 입력페이지 요청시 전달값 --%>
		<input type="hidden" name="ref" value="<%=board.getRef()%>">
		<input type="hidden" name="reStep" value="<%=board.getReStep()%>">
		<input type="hidden" name="reLevel" value="<%=board.getReLevel()%>">
		<%-- 게시글 목록 출력페이지 요청시 전달값 --%>
		<input type="hidden" name="pageNum" value="<%=pageNum%>">
		<input type="hidden" name="search" value="<%=search%>">
		<input type="hidden" name="keyword" value="<%=keyword%>">
	</form>
	
	<script type="text/javascript">
	$("#removeBtn").click(function() {
		if(confirm("정말로 게시글을 삭제 하시겠습니까?")) {
			$("#boardForm").attr("action", "<%=request.getContextPath()%>/site/index.jsp?workgroup=board&work=board_remove_action");
			$("#boardForm").submit();
		}
	});
	
	$("#modifyBtn").click(function() {
		$("#boardForm").attr("action", "<%=request.getContextPath()%>/site/index.jsp?workgroup=board&work=board_modify");
		$("#boardForm").submit();
	});
	
	$("#replyBtn").click(function() {
		$("#boardForm").attr("action", "<%=request.getContextPath()%>/site/index.jsp?workgroup=board&work=board_write");
		$("#boardForm").submit();
	});
	
	$("#listBtn").click(function() {
		$("#boardForm").attr("action", "<%=request.getContextPath()%>/site/index.jsp?workgroup=board&work=board_list");
		$("#boardForm").submit();
	});
	</script>	
</div>

















