<%@page import="com.RRS.DAO.UserDAO"%>
<%@page import="com.RRS.Models.Message"%>
<%@page import="com.RRS.Models.User"%>

<%User user = (User) session.getAttribute("admin");%>

<%if (user != null) {%>
<%Boolean validated = UserDAO.validateIfSessionExpired(session.getId());
    if (validated) {
        session.invalidate();
        response.sendRedirect("index.jsp");
    }
%>
<nav class="navbar navbar-expand-lg navbar-dark PrimaryColor">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp"><i class="fa fa-train"></i>&nbsp;RRS<sup><span class="badge rounded-pill bg-danger">ADMIN</span></sup></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/AdminHomeDirectory"><i class="fa fa-home"></i>Home</a>
                </li>


            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link d-flex align-items-center" href="#" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasBottom" aria-controls="offcanvasBottom"><span class="material-icons">
                            switch_account
                        </span>Switch Role</a>
                </li>
                <li class="nav-item dropdown">
                    <form method="POST" action="<%=request.getContextPath()%>/LogoutServlet">
                        <button type="button" class="btn btn-outline-info my-2 my-sm-0" data-target="authDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fa fa-user-circle"></i>&nbsp;<%=user.getName()%>
                        </button>

                        <div class="dropdown-menu dropdown-menu-end" id="authDropdown" >
                            <a class="dropdown-item" href="More.jsp"><i class="fa fa-ellipsis-h" aria-hidden="true"></i>&nbsp;More</a>
                            <div class="dropdown-divider"></div>
                            <button class="dropdown-item" type="submit"><i class="fa fa-sign-out"></i>&nbsp;Logout</button>
                        </div>
                    </form>
                </li>

            </ul>   
        </div>
    </div>

</nav>
<!--Account Active Alert-->
<%if (user.getHasAccountFrozen()) {%>
<div class="container-fluid mt-1" id="accountStatusAlert">
    <div class="alert <%=Message.ALERT_DANGER%> alert-dismissible fade show" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
        <h4 class="alert-heading">The Account is Frozen as per your request</h4>
        <p>You can't book tickets until you unfreeze the account.</p>
        <hr>
        <p class="mb-0">To unfreeze <a href="#">Click here</a></p>
    </div>
</div>
<%}%>
<!--Account Active Alert End-->


<!--Switch Roles Offcanvas-->

<div class="offcanvas offcanvas-bottom" tabindex="-1" id="offcanvasBottom" aria-labelledby="offcanvasBottomLabel">
    <div class="offcanvas-header">
        <h5 class="offcanvas-title d-flex align-items-center" id="offcanvasBottomLabel">
            <span class="material-icons">
                switch_account
            </span>Switch Roles</h5>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body small" style="overflow-y: scroll; height: fit-content;">
        <div class="container-fluid">
            <a class="card link-blue disabled" style="width: 20rem;" >
                <div class="card-body d-flex align-items-center">
                    <span class="material-icons">
                        admin_panel_settings
                    </span>
                    Switch to Admin
                </div>
            </a>
            <a href="#" class="card mt-2 link-blue" style="width: 20rem;">
                <div class="card-body d-flex align-items-center">
                    <span class="material-icons">
                        people_alt
                    </span>Switch to Customer
                </div>
            </a>
        </div>
    </div>
</div>
<!--end Switch Roles Offcanvas-->


<%}%>
