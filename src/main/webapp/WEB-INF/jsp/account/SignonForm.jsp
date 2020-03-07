<%@ include file="../common/IncludeTop.jsp"%>
<link href="../../../css/New.css" rel="stylesheet">
<div id="BackLink">
	<a href="main">Return to Main Menu</a>
</div>


<div id="Catalog">
	<fieldset>
		<legend>Sign on</legend>
	<form action="view_signOn_main" method="post" id="form_sign">
		<p>${sessionScope.message_from_cart}</p>
<%--		<p id="mes_p">Please enter your username and password.</p>--%>
		<p id="username_p"><span class="login_span">Username:&nbsp&nbsp&nbsp&nbsp&nbsp</span><input type="text" class="input" name="username" id="username" onblur="checkUsername();"/>
			<span id="isExistInfo"></span><br><br>
			<span class="login_span">Password:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span><input type="password" name="password" class="input"/>

		<script>
			var xhr;
			function checkUsername() {
				var username = document.getElementById("username").value;
				xhr = new XMLHttpRequest();
				xhr.onreadystatechange = process;
				xhr.open("GET","usernameExist?username_check=" + username,true);
				xhr.send(null);
			}
			function process() {
				if(xhr.readyState==4){
					if(xhr.status==200){
						var responseInfo = xhr.responseText;
						var msg = document.getElementById("isExistInfo");
						if(responseInfo == 'No exist'){
							msg.innerText = "No exist";
							msg.style.color = 'red';
							msg.addClass('no');
						}
					}
				}
			}
		</script>
		<br>
		<span id="identify_p"><span class="login_span">Identify Code:</span>
			<input type="text" value=""   id ="text" name = "identify" class="input" onblur="checkIdentifyCode()"/>
			<input type="hidden" id = "code_user" name = "code_user" />
			<input type="hidden" id = "code_true" name = "code_true" />
<%--			<canvas id="canvas" width="100" height="43" onclick="dj()" style="border: 1px solid #ccc; border-radius: 5px;" name = "try">--%>
			<canvas id="canvas" width="80" height="33" onclick="dj()" style="border: 1px solid #ccc; border-radius: 5px;" name = "try">
			</canvas>
			<span id="checkICode"></span>

			<script>
				var show_num = [];
				draw(show_num);
				function dj(){
					draw(show_num);
				}
				function sublim(){
					var val=document.getElementById("text").value;
					var num = show_num.join("");
					if(val==''){
						// alert('Please enter a validation code！');
						document.getElementById("code_user").value = "0";
					}
					else if(val == num){
						document.getElementById("code_user").value = "1";
						// alert('success！');
						document.getElementById(".input-val").val('');
						draw(show_num);
					}
					else{
						// alert('error！\nyour input:  '+val+"\ncorrect:  "+num+'\nplease input again！');
						document.getElementById("text").value='';
						draw(show_num);
						document.getElementById("code_user").value = "2";
					}
				}
				function draw(show_num) {
					var canvas_width=document.getElementById('canvas').clientWidth;
					var canvas_height=document.getElementById('canvas').clientHeight;
					var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
					var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
					canvas.width = canvas_width;
					canvas.height = canvas_height;
					var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0,q,w,e,r,t,y,u,i,o,p,a,s,d,f,g,h,j,k,l,z,x,c,v,b,n,m";
					var aCode = sCode.split(",");
					var aLength = aCode.length;//获取到数组的长度
					for (var i = 0; i <= 3; i++) {
						var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
						var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
						var txt = aCode[j];//得到随机的一个内容
						show_num[i] = txt;
						// var x = 10 + i * 20;//文字在canvas上的x坐标
						// var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
						var x = 2 + i * 20;//文字在canvas上的x坐标
						var y = 16 + Math.random() * 8;//文字在canvas上的y坐标
						context.font = "bold 23px 微软雅黑";
						context.translate(x, y);
						context.rotate(deg);
						context.fillStyle = randomColor();
						context.fillText(txt, 0, 0);
						context.rotate(-deg);
						context.translate(-x, -y);
					}
					for (var i = 0; i <= 5; i++) { //验证码上显示线条
						context.strokeStyle = randomColor();
						context.beginPath();
						context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
						context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
						context.stroke();
					}
					for (var i = 0; i <= 30; i++) { //验证码上显示小点
						context.strokeStyle = randomColor();
						context.beginPath();
						var x = Math.random() * canvas_width;
						var y = Math.random() * canvas_height;
						context.moveTo(x, y);
						context.lineTo(x + 1, y + 1);
						context.stroke();
					}
				}
				function randomColor() {//得到随机的颜色值
					var r = Math.floor(Math.random() * 256);
					var g = Math.floor(Math.random() * 256);
					var b = Math.floor(Math.random() * 256);
					return "rgb(" + r + "," + g + "," + b + ")";
				}
				function checkIdentifyCode() {
					var val=document.getElementById("text").value;
					var num = show_num.join("");
					if(val==''){
						document.getElementById("checkICode").innerText = 'Please enter a validation code';

					}
					else if(val == num) {
						// alert('success');
						document.getElementById("checkICode").innerText = 'success';
						document.getElementById("checkICode").style.color = 'green';
					}
					else{
						document.getElementById("checkICode").innerText = 'error';
						document.getElementById("checkICode").style.color = 'red';
						draw(show_num);
					}
				}
			</script>
		</span>
		</p>
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		<input type="submit" name="signon" value="Login" onclick="sublim()" id="submit_i"/>
	</form>
	</fieldset>
	<p>${sessionScope.message}</p>
	<div id="register_a">
	Need a user name and password?
	<a href="goto_register" >Register Now!</a>
	</div>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>

