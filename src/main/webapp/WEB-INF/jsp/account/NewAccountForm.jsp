<%@ include file="../common/IncludeTop.jsp"%>
<link href="../../../css/New.css" rel="stylesheet">
<div id="Catalog">
<%--	<stripes:form--%>
<%--	beanclass="org.mybatis.jpetstore.web.actions.AccountActionBean"--%>
<%--	focus="">--%>
	<form method="post" action="register_ok">

	<h3>User Information</h3>
		<script src="../../../js/jquery-3.4.1.js"></script>
		<script src="../../../js/IdentifyCode.js"></script>
	<table>
		<tr>
			<td >User ID:</td>
			<td><input type="text" name="username" id="newId"/><span id="newAcc"></span></td>
		</tr>
		<tr>
			<td>New password:</td>
			<td><input type="password" name="password" id="y"/></td>
		</tr>
		<tr>
			<td>Repeat password:</td>
			<td><input type="password" name="repeatedPassword" id="n" onblur="x()"/><span id="ff"></span></td>

			<script>
				function x() {
					var y = document.getElementById("y").value;
					var n = document.getElementById("n").value;
					if (y != n) {
						document.getElementById("ff").innerText = 'Please enter the same password';
						document.getElementById("ff").style.color = 'red';
					}
				}
			</script>
		</tr>
	</table>

	<%@ include file="IncludeAccountFields.jsp"%>

	<input type="submit" name="newAccount" value="Save Account Information" />

	</form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>