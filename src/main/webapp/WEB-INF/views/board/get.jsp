<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix = "c" %> <!-- jstl 코어태그 -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %> <!-- jstl 포메팅 태그용 -->
    
<%@include file = "../inckudes/header.jsp" %>
<!-- list.jsp에서 /board/register 경로를 호출하면 get 메서드가 실행 폼 박스가 나옴 -->
<!-- 입력완료가 나오면 vo객체가 만들어져서 /board/register에 post메서드가 실행됨 -->

<div class ="row">
   <div class="col-lg-12">
       <h1 class="page-header">게시판 글 상세보기 페이지</h1>
   </div> <!-- .col-lg-12 end -->
</div> <!-- .row end -->

<div class="row">
   <div class="col-lg-12">
     <div class="panel panel-default">
       <div class="panel-heading">Board Read page</div>
       <div class="panel-body">
         <!-- form박스 만들고 submit 처리 -->
         <!-- <form role="form" action="/board/register" method="post"> -->
             
             <div class="form-group">
                <label>번호</label>
                <input class="form-control" name="bno" value='<c:out value="${board.bno } "/>' readonly="readonly"/>
             </div>
             
             <div class="form-group">
                <label>제목</label> <input class="form-control" name="title" value='<c:out value="${board.title } "/>' readonly="readonly"/>
             </div> <!-- .form-grop end -->
         
               <div class="form-group">
                  <label>내용</label>
                  <textarea class="form-control" rows="3"  name="content" readonly="readonly"><c:out value="${board.content } "/>
                  </textarea>
               </div> <!-- .form-group end -->
               
               <div class="form-group">
                  <label>작성자</label>
                  <input class="form-control" name="writer" value='<c:out value="${board.writer } "/>' readonly="readonly"/>
               </div> <!-- write .form-grop end -->
               
               <button data-oper='modify' class="btn btn-primary" >수정</button>
               <%-- onclick="location.href='/board/modify?bno=<c:out value="${ board.bno}"/>'" --%>
               
               <!-- <button data-oper='delete' class="btn btn-danger">삭제</button> -->
               <button data-oper='list' class="btn btn-info">리스트</button>
                 <!-- onclick="location.href='/board/list'" -->
                 
                 <!-- 수정 버튼을 클릭시 bno를 가지고 가야됨 -->
                <form id="operForm" action="/board/modify" modify="get">
                   <input type="hidden" id="bno" name="bno" value='<c:out value="${board.bno}"/>'>
                
                </form>

       <!-- </div> .panel body end  -->
     </div> <!-- .panel panel-default end -->
   </div> <!-- .col-lg-12 end -->
</div> <!-- .row end -->

<script type="text/javascript">
   $(document).ready(function(){
	   
	   var operForm = $("#operForm"); // <form id="operForm" action="/board/modify" modify="get">
	   
	   $("button[data-oper='modify']").on("click",function(e){
		   operForm.attr("action", "/board/modify").submit();
	   });
	   
	   $("button[data-oper='list']").on("click",function(e){
		   operForm.find("#bno").remove(); // input에 있는 bno를 삭제
		   operForm.attr("action", "/board/list").submit();
	   });
   });
</script>

