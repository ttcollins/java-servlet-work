<!DOCTYPE html>
<html lang="en">

<head>
    <title>Login V14</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.ico" />
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <!--===============================================================================================-->
</head>

<body>

    <div class="limiter">
        <div class="container-login100">
            <div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
                <div><h6 style="color:red"><%= (request.getAttribute("dont") == null) ? "" : request.getAttribute("dont") %></h6></div>
                <div align="right"><i><a href="/NAD/login.jsp"><h6>Login</h6></a></i></div>
                <form class="login100-form validate-form flex-sb flex-w" method="post" action="register" onsubmit="return validate()">
                    <span class="login100-form-title p-b-32">
						Register Account
					</span>

                    <span class="txt1 p-b-11">
						First Name
					</span>
                    <div class="wrap-input100 validate-input m-b-36" data-validate="Username is required">
                        <input id="fname" class="input100" type="text" name="fname" required autocomplete="fname" autofocus>
                        <span class="focus-input100"></span>
                    </div>

                    <span class="txt1 p-b-11">
						Other Names
					</span>
                    <div class="wrap-input100 validate-input m-b-36" data-validate="Username is required">
                        <input id="other" class="input100" type="text" name="other" required autocomplete="other" autofocus>
                        <span class="focus-input100"></span>
                    </div>

                    <span class="txt1 p-b-11">
						Type of User
					</span>
                    <div class="wrap-input100 validate-input m-b-36" data-validate="Username is required">
                        <select onchange="checkIfStudent()" id="user_type" type="text" class="input100" name="user_type" required autocomplete="user_type" autofocus>
                            <option id="" value="">Choose User Type</option>
                            <option id="Student" value="Student">Student</option>
                            <option id="Faculty Member" value="Faculty Member">Faculty Member</option>
                        </select> <span class="focus-input100"></span>
                    </div>

                    <div id="extra" name="extra" style="display: none">
                        <span class="txt1 p-b-11">
                            Student Number
                        </span>
                        <div class="wrap-input100 m-b-36">
                            <input id="std_number" type="text" class="input100" name="std_number" autocomplete="std_number" autofocus>
                            <span class="focus-input100"></span>
                        </div>

                        <span class="txt1 p-b-11">
                            Registration Number
                        </span>
                        <div class="wrap-input100 m-b-36">
                            <input id="reg_number" type="text" class="input100" name="reg_number" autocomplete="reg_number" autofocus>
                            <span class="focus-input100"></span>
                        </div>

                        <span class="txt1 p-b-11">
                            Cours
                        </span>
                        <div class="wrap-input100 m-b-36">
                            <select id="course" type="text" class="input100" name="course" autocomplete="course" autofocus>
                                <option value="">Select Course</option>
                                <option value="BSC">BSC</option>
                                <option value="BSSE">BSSE</option>
                                <option value="BIST">BIST</option>
                            </select>
                        </div>
                    </div>

                    <div class="wrap-input100 m-b-36"><input type="hidden" class="input100"></div>

                    <span class="txt1 p-b-11">
                        Gender
                    </span>
                    <div class="wrap-input100 validate-input m-b-36" data-validate="Gender is required">
                        <select id="gender" type="text" class="input100" name="gender" value="" required autocomplete="gender" autofocus>
                            <option value="M">Male</option>
                            <option value="F">Female</option>
                        </select>
                        <span class="focus-input100"></span>
                    </div>
                
                    <span class="txt1 p-b-11">
						Phone Number
					</span>
                    <div class="wrap-input100 validate-input m-b-36" data-validate="Phone Number is required">
                        <input id="number" class="input100" type="text" name="number" required autocomplete="number" autofocus>
                        <span class="focus-input100"></span>
                    </div>

                    <span class="txt1 p-b-11">
						Email 
					</span>
                    <div class="wrap-input100 validate-input m-b-36" data-validate="Email is required">
                        <input id="email" class="input100" type="email" name="email" required autocomplete="email" autofocus>
                        <span class=" focus-input100 "></span>
                    </div>

                    <span class="txt1 p-b-11 ">
						Password
					</span>
                    <div class="wrap-input100 validate-input m-b-12 " data-validate="Password is required ">
                        <span class="btn-show-pass ">
							<i class="fa fa-eye "></i>
						</span>
                        <input id="password" class="input100 " type="password" name="password" required autocomplete="new-password ">
                        <span class="focus-input100 "></span>
                    </div>

                    <span class="txt1 p-b-11 ">
						Confirm Password
					</span>
                    <div class="wrap-input100 validate-input m-b-36 " data-validate="Password Confirmation is required ">
                        <input id="password-confirm" class="input100 " type="password" name="password_confirmation" required autocomplete="new-password ">
                        <span class="focus-input100 "></span>
                    </div>
                    <div class="container-login100-form-btn ">
                        <button class="login100-form-btn ">
							Register
						</button>
                    </div>

                </form>
            </div>
        </div>
    </div>


    <div id="dropDownSelect1 "></div>

    <script>
        function checkIfStudent() {
            if (document.getElementById('user_type').value == 'Student') {
                document.getElementById('extra').style.display = '';
                document.getElementById('std_number').disabled = false;
                document.getElementById('reg_number').disabled = false;
                document.getElementById('course').disabled = false;
            } else {
                document.getElementById('extra').style.display = 'none';
                document.getElementById('std_number').disabled = true;
                document.getElementById('reg_number').disabled = true;
                document.getElementById('course').disabled = true;
            }
        }

        function validate() {
            var confirmpass = document.getElementById('password-confirm').value;
            var pass = document.getElementById('password').value;
            var user = document.getElementById('user_type').value;
            var std_number = document.getElementById('std_number').value;
            var reg_number = document.getElementById('reg_number').value;
            var course = document.getElementById('course').value;
            var number = document.getElementById('number').value;
            var phone = /^\d{10}$/;
            var reg = /^\(?([0-9]{2})\)?[/]?([A-z]{1})[/]?([0-9]{5})[/]?([A-Z]{2})$/;
            if(user == "Student"){
                if(std_number==null || std_number==""){
                    alert("Student number should be filled");
                    return false;
                }
                else if(reg_number==null || reg_number==""){
                    alert("Registration number should be filled");
                    return false;
                }
                else if(course==null || course==""){
                    alert("Course should be filled");
                    return false;
                }
                else if(!std_number.match(phone)){
                    alert("Student number should be 10 digits long.");
                    return false;
                }
                else if(!reg_number.match(reg)){
                    alert("Registration number should be in the format XX/X/XXXXX/XX.");
                    return false;
                }
                else if(!number.match(phone)){
                    alert("Phone number should be atleast 10 digits long.");
                    return false;
                }
                else if(pass.length<5){
                    alert("Password must be atleast 5 characters long.");
                    return false;
                }
                else if(confirmpass !=pass){
                    alert("Confirm password should match the password");
                    return false;
                }
            }
            else if(!number.match(phone)){
                alert("Phone number should be atleast 10 digits long.");
                return false;
            }
            else if(pass.length<5){
                alert("Password must be atleast 5 characters long.");
                return false;
            }
            else if(confirmpass !=pass){
                alert("Confirm password should match the password");
                return false;
            }
        }
    </script>


    <!--===============================================================================================-->
    <script src="vendor/jquery/jquery-3.2.1.min.js "></script>
    <!--===============================================================================================-->
    <script src="vendor/animsition/js/animsition.min.js "></script>
    <!--===============================================================================================-->
    <script src="vendor/bootstrap/js/popper.js "></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js "></script>
    <!--===============================================================================================-->
    <script src="vendor/select2/select2.min.js "></script>
    <!--===============================================================================================-->
    <script src="vendor/daterangepicker/moment.min.js "></script>
    <script src="vendor/daterangepicker/daterangepicker.js "></script>
    <!--===============================================================================================-->
    <script src="vendor/countdowntime/countdowntime.js "></script>
    <!--===============================================================================================-->
    <script src="js/main.js "></script>

</body>

</html>

</html>