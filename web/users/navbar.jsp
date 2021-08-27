<nav class="navbar navbar-expand-lg navbar-dark PrimaryColor">
    <a class="navbar-brand" href="../index.jsp"><i class="fa fa-train"></i>&nbsp;RRS</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="../index.jsp">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Dropdown
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="Book-Ticket.jsp">Book Ticket</a>
                </div>
            </li>
        </ul>
    </div>
    <div class="form-inline my-2 my-lg-0">
        <form method="POST" action="../LogoutServlet">
            <button type="submit" class="buttonStyle my-2 my-sm-0">Logout</button>
        </form>
    </div>

</nav>