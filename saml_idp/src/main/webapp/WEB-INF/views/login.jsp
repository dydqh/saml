<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
	$(function(){
		$("#targetForm").on("submit", function(e){
			var id = $("input[name=id]").val();
			var pw = $("input[name=password]").val();
			if(id != "admin" || pw != "1234"){
				e.preventDefault();
			}
			else{
				$.ajax({
					url : "http://localhost:8180/saml_idp/data/getSAMLResponse",
					type : "post",
					success : function(resp){
						console.log(resp);
						$("input[name=SAMLResponse]").val(resp);
					}
				});
				
				$.ajax({
					url : "http://localhost:8180/saml_idp/data/getRelayState",
					type : "post",
					success : function(resp){
						console.log(resp);
						$("input[name=RelayState]").val(resp);
					}
				});
			}
		});
	})
</script>

</head>
<body>

	<div style="display: inline-block;">
		<input type="text" name="id" placeholder="ID" autocomplete="off">
		<input type="password" name="password" placeholder="PASSWORD">
	</div>
	<form id="targetForm" action="" method="post" style="display: inline-block;">
		<input type="hidden" name="SAMLResponse">
		<input type="hidden" name="RelayState">
		<input type="submit" value="LOGIN">
	</form>
</body>
</html>