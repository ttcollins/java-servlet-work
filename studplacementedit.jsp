<!DOCTYPE html>
<html lang="en">

    <script>
        function validate() {
            var phonenumber = document.getElementById('phonenumber').value;
            var start_date = document.getElementById('start_date').value;
            var end_date = document.getElementById('end_date').value;
            var contact = document.getElementById('contact').value;
            var phone = /^\d{10}$/;
    
            if(!phonenumber.match(phone)){
                alert("Field Supervisor's Phone number should be atleast 10 digits long.");
                return false;
            }else if(start_date>=end_date){
                alert("End Date Cannot be Before or similar to Start Date");
                return false;
            }else if(!contact.match(phone)){
                alert("Organisation's Phone number should be atleast 10 digits long.");
                return false;
            }
        }

    </script>

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Intern</title>

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">
    <% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    if(session.getAttribute("fname")==null)
    {
        response.sendRedirect("login.jsp");
    }
    %>

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="studhome">
        <div class="sidebar-brand-icon rotate-n-15">
        </div>
        <div class="sidebar-brand-text mx-3"><h3>Intern</h3></div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        FILL IN
      </div>

      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="placement">
          <i class="fas fa-fw fa-folder"></i>
          <span>Placement Details</span></a>
      </li>

      <li class="nav-item active">
        <a class="nav-link" href="studreport.jsp">
          <i class="fas fa-fw fa-folder"></i>
          <span>Weekly Reports</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <li class="nav-item active">
        <a class="nav-link" href="visits">
          <i class="fas fa-fw fa-table"></i>
          <span>View Visits</span></a>
      </li>
      
      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

          <!-- Topbar Navbar -->
                <div><h6 style="color:green"><%= (request.getAttribute("placementgo") == null) ? "" : request.getAttribute("placementgo") %></h6></div>
                <div><h6 style="color:red"><%= (request.getAttribute("placementdont") == null) ? "" : request.getAttribute("placementdont") %></h6></div>

          <ul class="navbar-nav ml-auto">           

            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
            <li class="nav-item dropdown no-arrow d-sm-none">
              <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-search fa-fw"></i>
              </a>

              <!-- Dropdown - Messages -->
              <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                <form class="form-inline mr-auto w-100 navbar-search">
                  <div class="input-group">
                    <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="button">
                        <i class="fas fa-search fa-sm"></i>
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </li>
            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-black-600 small" style="color:black"><%= session.getAttribute("fname") %></span>
              </a>
              <!-- Dropdown - User Information -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="updatestudent">
                  <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                  Update Profile
                </a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="logout" data-toggle="modal" data-target="#logoutModal">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  Logout
                </a>
              </div>
            </li>

          </ul>

        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <div class="container">

        <div class="card">
            <div class="card-header"><strong><h3>PLACEMENT</h3></strong></div>
                <div class="card-body card-block">
                    <form method="POST" action="placement" onsubmit="return validate()">

                        <div class="form-group"><label for="field_email" class=" form-control-label">Field Supervisor's Email Address</label><input name="field_email" value="<%= request.getAttribute("field_email") %>" type="email" id="field_email" placeholder="" required class="form-control"></div>

                        <div class="form-group"><label for="field_supervisor_fname" class=" form-control-label">Field Supervisor's First Name</label><input name="field_supervisor_fname" value="<%= request.getAttribute("field_fname") %>" type="text" id="field_supervisor_fname" placeholder="" required class="form-control"></div>

                        <div class="form-group"><label for="field_supervisor_other" class=" form-control-label">Field Supervisor's Other Names</label><input name="field_supervisor_other" value="<%= request.getAttribute("field_other") %>" type="text" id="field_supervisor_lname" placeholder="" required class="form-control"> </div>      

                        <div class="form-group"><label for="phonenumber" class=" form-control-label">Field Supervisor's Phone Number</label><input name="phonenumber" value="<%= request.getAttribute("field_number") %>" type="text" id="phonenumber" placeholder="" required class="form-control"></div>             
                        
                        <div class="form-group"><label for="start_date" class=" form-control-label">Start Date</label><input name="start_date" value="<%= request.getAttribute("start_date") %>" type="date" id="start_date" placeholder="yyyy/MM/dd" required class="form-control"></div>

                        <div class="form-group"><label for="end_date" class=" form-control-label">End Date</label><input name="end_date" value="<%= request.getAttribute("end_date") %>" type="date" id="end_date" placeholder="yyyy/MM/dd" required class="form-control"></div>

                </div>

                        <div class="card-header"><strong><h3>ORGANISATION DETAILS</h3></strong></div>
                        <div class="card-body card-block">
                        <!--need to place google map feature-->
                        <div class="form-group"><label for="organisation" class=" form-control-label">Name</label><input name="organisation" value="<%= request.getAttribute("org_name") %>" type="text" id="organisation" placeholder="" required class="form-control"></div>

                        <div class="form-group"><label for="address" class=" form-control-label">Address</label><input name="address" value="<%= request.getAttribute("org_address") %>" type="text" id="address" placeholder="" required class="form-control"></div>

                        <!--end of google map feature-->
                        <div class="form-group"><label for="additional_information" class=" form-control-label">Additional Address Information</label><input name="additional_information" value="<%= request.getAttribute("additional_address_info") %>" type="text" id="additional_information" placeholder="" required class="form-control"></div>

                        <div class="form-group"><label for="contact" class=" form-control-label">Phone Number</label><input name="contact" value="<%= request.getAttribute("org_contact") %>" type="text" id="contact" placeholder="" required class="form-control"></div>

                        <div class="form-group"><label for="email" class=" form-control-label">E-mail Address</label><input name="email" value="<%= request.getAttribute("org_email") %>" type="email" id="email" placeholder="" required class="form-control"></div>

                        <button type="submit" class="btn btn-primary" onclick="return validate()">Submit</button>
                        <button type="reset" class="btn btn-primary">Refresh</button>
                    </form>

                    </div>
                </div>
            </div>
        </div>
    </div>

      <!-- End of Main Content -->

      <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy; Your Website 2019</span>
          </div>
        </div>
      </footer>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="logout">Logout</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="vendor/chart.js/Chart.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/chart-area-demo.js"></script>
  <script src="js/demo/chart-pie-demo.js"></script>

</body>

</html>