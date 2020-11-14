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
</style>
</head>
<body bgcolor="black">

	<div id="letterBody">
	<div class="middle"> <%= request.getAttribute("fname") %> </div>

	<div id="address">
	<div class="left"> <%= request.getAttribute("other") %></div>
	
	<div class="right"> <%= request.getAttribute("gender") %></div>
	<div class="right"> <%= request.getAttribute("email") %></div>
	<div class="right"> <%= request.getAttribute("number") %></div>
	</div>

	
		<p>Sire,” <%= request.getAttribute("fname")%>  said, dropping to one knee and holding the gold disc
up to him with both hands. “I have returned.” $(lname) fell to both knees
beside him, hands clasped in prayer.
“I see that,” High Priest Craven said, snatching up the disc. “You did as I
instructed?”
“Oh yes, Sire.”
“Exactly as I instructed?”
“I located Ghastly Bespoke’s quarters, let myself in—”
“Let ourselves in,” Thrasher corrected.
“– and then I located the disc. I substituted—”
“We substituted,” Thrasher corrected.
“– the fake disc you had given me, and returned here to you, now, with the
real disc. So now he has the fake disc and you have the real disc. I live only to
serve.”
“We live only to serve,” Thrasher corrected.
“You don’t live,” the Master said, examining his prize. “And nobody saw
you?”
“Nobody, Sire. I was like the wind.”
“We were like the wind,” said Thrasher.
“But I was like the wind more.”
“I was more breeze-like,” Thrasher said, and bowed forward until his
forehead was touching the ground. It was, once again, an unsurprisingly
pathetic display, and one that Scapegrace would have no problem surpassing.
He laid himself flat on the ground, face stuck into the dirt, and waved his
arms in the air. “Give me another order, Master, I beg of you.”
“Me too,” Thrasher said, lying beside Scapegrace, doing his best to
wriggle deeper into the dirt. Furious, Scapegrace started wriggling alongside
him.
</p>

	</div>
</body>
</html>
