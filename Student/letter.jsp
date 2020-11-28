<%@ page language="java" contentType="text/html;charset= UTF-8" pageEncoding = "UTF-8" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www/w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset= Utf-8">
<title>University Letter of Introduction</title>
<style>
	#letterBody{
		background-color:white;
		width:70%;
		height:100%;
		
		position:relative;
		top:0;
		bottom:0;
		left:0;
		right:0;
		margin:auto;
		margin-top:50px;
		padding:100px 50px 100px 50px;
		
	}

	.left{
		
		float:left;
		width:50%;
	}
	.middle{
		text-align:center;

	}

	.right{
		float:right;
		width:50%;
		text-align:right;

	}

	#address{
		width:100%;
		margin:50px 0px 50px 0px;
		padding-bottom:30px;

	}

	

	#mainPart{
		margin:50px 50px 50px 50px;	
	}

	span{
		
	}

	#body{
		
	}
</style>
</head>
<body bgcolor="black" onclick="window.print()">

	
	<div id="letterBody">
	
	<div id="official">
	<div >
	College of Computing and Information Sciences, 
	Block A,
	<br/>
	Makerere University, 
	<br/>
	P.O.Box 7772,
	<br/>
	Kampala, Uganda.
	<br/>
	3rd April 2020.
	</div>	
	</div>

	<!--
	<div class="middle"> <%= request.getAttribute("fname") %> </div>

	<div id="address">
	<div class="left"> <%= request.getAttribute("other") %></div>
	
	<div class="right"> <%= request.getAttribute("gender") %></div>
	<div class="right"> <%= request.getAttribute("email") %></div>
	<div class="right"> <%= request.getAttribute("number") %></div>
	</div>
	-->
	<br/>
	<div id="body">
	<p>Good day to you Madam/Sir,</p>
<h3> RE : <u>LETTER OF INTRODUCTION FROM MAKERERE UNIVERSITY TO PLACE OF INTERNSHIP</u></h3>


		<p>
		This is to introduce a student of our university  <span><%= request.getAttribute("fname")%> <%= request.getAttribute("other") %></span> at the College of Computing and Information Systems to carry out their internship at your establishment for the duration of the period MAY 2020 - JULY 2020. 

<br/>
<br/>
		We do hope you will consider <span><%= request.getAttribute("fname")%> <%= request.getAttribute("other") %></span> as an addition to your fine establishment.

</p>
</div>
	<div class="left">
	Yours faithfully,
	<br/>
	
	<i>Dean of College's signature</i>
	
	<br/>
	name,
	<br/>
	Dean of College of Computing and Information Sciences.
	<br/>
	</div>


	<div class="right">
	Yours faithfully,
	<br/>
	
	<i>Vice Chancellor's signature</i>
	<br/>
	name,
	<br/>
	Vice-Chancellor of Makerere University.
	</div>	



	</div>

	

</body>
</html>
