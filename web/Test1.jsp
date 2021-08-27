<%-- 
    Document   : Test1
    Created on : 25 Apr, 2021, 1:15:25 AM
    Author     : HP
--%>

<%@page import="java.util.List"%>
<%@page import="com.RRS.DAO.StationDAO"%>
<%@page import="com.RRS.Models.Station"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%List<Station> list = StationDAO.getStationsList();%>
<!DOCTYPE html>
<html>
    <head>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">-->
        <%@include file="CommonCSS.jsp" %>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

        <!--Let browser know website is optimized for mobile-->
    </head>

    <body>

        <div class="container mt-3">

            <form>
                <input type="text" id="autocomplete-input" class="form-control">
            </form>
        </div>






        <!--JavaScript at end of body for optimized loading-->
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>-->
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>-->
        <!--        <script>
                    $(document).ready(function () {
                        $('#autocomplete-input').on('keydown', function () {
                            var d = $(this).val();
        
                            $.ajax({
                                url: 'TestServlet',
                                type: 'POST',
                                data: {station: d},
                                dataType: 'json',
                                success: function (data) {
        
        //                            console.log(data);
        //                            var d = $.parseJSON(data);
        
                                    $.each(data, function (index, element) {
                                        $('#autocomplete').append($('<li>', {
                                            text: element.Station_Name +"("+ element.Station_Code +")"
                                        },'</li>'));
                                    });
        
        //                            
                                    
        
                                }, error: function (jqXHR, textStatus, errorThrown) {
                                    console.log(errorThrown);
                                }
        
                            });
                        });
                    });
                </script>-->
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {

                $('#autocomplete-input').autocomplete({
                    source: '${pageContext.request.contextPath}/TestServlet'
                });

            });
        </script>
    </body>
</html>
