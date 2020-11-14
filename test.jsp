<!--
<html>
    <body>
        <form method="post" action="register">
            First name: <input type="text" name="fname"><br>
            Other name: <input type="text" name="other"><br>
            Type of user: <select type="text" name="user_type" onchange="checkIfStudent()" onmouseover="checkIfStudent()" id="user_type">
                <option>Select user type</option>
                <option value="Student">Student</option>
                <option value="Faculty Member">Faculty Member</option>
            </select><br>

            <div id="extra" name="extra" style="display: none">
                Student number: <input type="text" name="std_number" id="std_number"><br>
                Registration number: <input type="text" name="reg_number" id="reg_number"><br>
                Course: <input type="text" name="course" id="course"><br>
            </div>

            Gender: <select type="text" name="gender">
                <option>Select gender</option>
                <option value="M">Male</option>
                <option value="F">Female</option>
            </select><br>
            Number: <input type="text" name="number"><br>
            Email: <input type="email" name="email"><br>
            Password: <input type="password" name="password"><br>
            <button>register</button>
        </form>
    </body>
</html>
<script>
    function checkIfStudent() {
        if(document.getElementById('user_type').value == 'Student'){
            document.getElementById('extra').style.display = '';
            document.getElementById('std_number').disabled = false;
            document.getElementById('reg_number').disabled = false;
            document.getElementById('course').disabled = false;
        }else{
            document.getElementById('extra').style.display = 'none';
            document.getElementById('std_number').disabled = true;
            document.getElementById('reg_number').disabled = true;
            document.getElementById('course').disabled = true;
        }
    }
</script>
-->

<!--<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:student>--> 
    <%@ include file="top.jsp" %>
    <h1>Welcome</h1>
    <div align="right">
        <form method="post" action="visits">
            <div class="form-group"><label for="date" class=" form-control-label"><b>Date of Visit: <%= session.getAttribute("fname") %></b></label><input name="visit_date" value="" type="date" id="visit_date" required class="input100">
                <button type="submit" class="btn btn-primary" onclick="return validate()">Submit</button>
            </div>
        </form>
    </div>

    <script>
        function validate() {
            var date = document.getElementById('visit_date').value;
            var newdate = new Date(date);
            var now = new Date();
            if(newdate>now){
                alert("A Visit Cannot be made in the Future.");
                return false;
            }
        }
    </script>
    <%@ include file="bottom.jsp" %>
<!--</t:student>-->
<!--
    	<servlet>
        <servlet-name>hello</servlet-name>
        <servlet-class>examples.hello</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>

    <servlet>
        <servlet-name>helloformurl</servlet-name>
        <servlet-class>examples.helloformurl</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>helloformurl</servlet-name>
        <url-pattern>/helloformurl</url-pattern>
    </servlet-mapping>

    <servlet>
        <servlet-name>checkbox</servlet-name>
        <servlet-class>examples.checkbox</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>checkbox</servlet-name>
        <url-pattern>/checkbox</url-pattern>
    </servlet-mapping>
-->
