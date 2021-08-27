<%@page import="com.RRS.Models.FAQsAnswer"%>
<%@page import="com.RRS.Models.FAQsQuestion"%>
<%@page import="com.RRS.DAO.FAQsDAO"%>
<%@page import="com.RRS.Models.FAQsCategory"%>
<%@page import="com.RRS.Models.FAQsCategory"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>RRS | FAQ's</title>
        <link rel="icon" type="image/icon type" href="img/train_ico.png">
        <%@include file="CommonCSS.jsp"%>
    </head>
    <body style="background-color: #f5f5f5;">
        <%@include file="navbar.jsp" %>
        <div class="container-fluid mt-3 mb-4">
            <div class="row">
                <div class="col-3 d-flex justify-content-center">
                    <div class="card" style="width: 20rem; height: fit-content;">
                        <div class="list-group" id="list-tab" role="tablist">
                            <%List<FAQsCategory> fc = FAQsDAO.getAllFAQsCategoryList();%>
                            <%for (FAQsCategory category : fc) {%>
                            <a class="list-group-item list-group-item-action" id="list-home-list" data-toggle="list" href="#<%=category.getCategoryUniqueName()%>" role="tab">
                                <%=category.getCategoryName()%>
                            </a>
                            <%}%>
                        </div>
                    </div>
                </div>
                <div class="col-9 d-flex justify-content-start">
                    <div class="card px-2" style="width: 45rem; height: max-content;">
                        <h4 class="text-center">FAQs</h4>
                        <div class="tab-content" id="nav-tabContent">
                            <%for (FAQsCategory category : fc) {%>
                            <div class="tab-pane fade show" id="<%=category.getCategoryUniqueName()%>" role="tabpanel">
                                <h5 class="text-center mt-1"><%=category.getCategoryName()%></h5>
                                <hr>
                                <div id="accordion">
                                    <div class="card mb-3">

                                        <%List<FAQsQuestion> fq = FAQsDAO.getAllFAQsQustionList(category);%>
                                        <%for (FAQsQuestion question : fq) {%>
                                        <div class="card-header">
                                            <h5 class="mb-0 d-flex justify-content-between">
                                                <button class="btn btn-link" data-toggle="collapse" data-target="#<%=question.getQuestionUniqueName()%>" aria-expanded="true">
                                                    <%=question.getQuestionContent()%>
                                                </button>
                                                    <button class="btn btn-link btnFAQsDropdown"  data-toggle="collapse" data-target="#<%=question.getQuestionUniqueName()%>" aria-expanded="true">
                                                    <i class="fa fa-caret-down"></i>
                                                </button>

                                            </h5>
                                        </div>

                                        <div id="<%=question.getQuestionUniqueName()%>" class="collapse" data-parent="#accordion">

                                            <div class="card-body">
                                                <%FAQsAnswer ans = FAQsDAO.getAnswerOfFAQ(question);%>
                                                <%if (ans != null) {%>
                                                <%=ans.getAnswerContent()%>
                                                <%try {%>
                                                <%if (ans.getReferenceScreenshot() != null) {%>
                                                <div class="container-fluid mt-3">
                                                    <img data-toggle="modal" data-target="#<%=ans.getAnswer_Unique_Name()%>"
                                                         src="Screenshots/<%=ans.getReferenceScreenshot()%>" class="img-thumbnail rounded mx-auto d-block" >
                                                    <h6 class="text-muted my-1 text-center">Reference Screenshot</h6>
                                                    <!-- Modal -->
                                                    <div class="modal fade" id="<%=ans.getAnswer_Unique_Name()%>" tabindex="-1" role="dialog" aria-hidden="true">
                                                        <div class="modal-dialog modal-dialog-centered" style="max-width: 1000px!important;" role="document" >
                                                            <div class="modal-content" >
                                                                <div class="modal-body">
                                                                    <img src="Screenshots/<%=ans.getReferenceScreenshot()%>" class="img-thumbnail rounded mx-auto d-block" >
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>


                                                <%}%>
                                                <%} catch (Exception e) {%>    

                                                <%}%>
                                                <%} else {%>
                                                NA
                                                <%}%>
                                            </div>
                                        </div>
                                        <%}%>
                                    </div>
                                </div>
                            </div>
                            <%}%>
                        </div>

                    </div>
                </div>
            </div>
        </div>





        <%@include file="CommonJS.jsp" %>
        <script>
            $(window).ready(function(){
                $('.btnFAQsDropdown').on('click',function (){
                    
                    
                });
            });
        </script>
    </body>
</html>
