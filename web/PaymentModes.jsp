<%@page import="com.RRS.Utilities.StringFormatter"%>
<%@page import="com.RRS.Models.SavedUserTransactionCard"%>
<%@page import="com.RRS.DAO.SavedUserTransactionCardDAO"%>
<%@page import="com.RRS.Utilities.Calculator"%>
<%@page import="java.util.List"%>
<%@page import="com.RRS.Models.Passenger"%>

<%@page import="com.RRS.Models.Ticket"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Message msg = (Message) session.getAttribute("userMsg");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="CommonCSS.jsp" %>
        <title>Select Payment Mode</title>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <!--Message Alert-->
        <%if (msg != null) {%>
        <div class="alert <%=msg.getCSS()%>" role="alert">
            <%=msg.getContent()%>
        </div>
        <%}
            session.removeAttribute("userMsg");%>
        <!--Message Alert End-->
        <%Ticket ticket = (Ticket) session.getAttribute("NewTicket");%>
        <% List<Passenger> passengersList = (List<Passenger>) session.getAttribute("PassengersList");%>

        <%if (ticket != null && !passengersList.isEmpty()) {%>
        <%Double GSTAmount = Calculator.getTaxAmount(5.0, ticket.getAmount());%>
        <h5 class="text-center">Choose a payment option :</h5>
        <div class="container-fluid justify-content-center d-flex">

            <div class="card mt-3 mb-2" style="width: 60rem;">
                <div class="card-body">
                    <div class="row">
                        <div class="col-7">
                            <nav>
                                <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                    <a class="nav-item nav-link active" id="nav-card-payment-tab" data-toggle="tab" href="#nav-card-payment" role="tab" aria-controls="nav-card-payment" aria-selected="true">Debit/Credit Card</a>
                                    <a class="nav-item nav-link" id="nav-internet-banking-tab" data-toggle="tab" href="#nav-internet-banking" role="tab" aria-controls="nav-internet-banking" aria-selected="false">Internet-Banking</a>
                                </div>
                            </nav>
                            <div class="tab-content" id="nav-tabContent">
                                <div class="tab-pane fade show active" id="nav-card-payment" role="tabpanel" aria-labelledby="nav-card-payment-tab">
                                    <div class="container-fluid mt-1 mx-0 px-0">

                                        <p class="SecondaryColor font-weight-bold">Saved Card</p>
                                        <div class="card">
                                            <div class="card-body">
                                                <%try {%>
                                                <%SavedUserTransactionCard savedCard = SavedUserTransactionCardDAO.getUserSavedTransactionCard(user);%>
                                                <%if (savedCard != null) {%>
                                                <form class="form-inline" method="POST" action="SavedCardPaymentInitServlet">
                                                    <div class="input-group mb-1">
                                                        <div class="input-group-prepend"style="height: 40px;">
                                                            <div class="input-group-text">Card Ending with <%=StringFormatter.getLast4Digits(savedCard.getCARD_NUMBER())%></div>
                                                        </div>
                                                        <input type="number" name="cvvNo" max="999" style="height: 40px;" maxlength="3" class="form-control" placeholder="Enter CVV">
                                                        <div class="input-group-append"style="height: 48px;">
                                                            <button type="submit" class="btn btn-primary mb-2">Submit</button>
                                                        </div>
                                                    </div>
                                                </form>

                                                <%}%>
                                                <%} catch (Exception e) {%>
                                                <div class="d-flex justify-content-between">
                                                    <h5><%=e.getMessage()%></h5>
                                                    <a href="NewCard.jsp" class="btn PrimaryColor text-white"><i class="fa fa-plus"></i>Add New</a>
                                                </div>

                                                <%}%>
                                            </div>
                                        </div>
                                        <form class="py-3" action="NewTransactionCardPaymentInitServlet" method="POST">

                                            <p class="SecondaryColor font-weight-bold">Enter Card Details</p>
                                            <div class="form-group">
                                                <label for="card-number">Enter Debit/Credit Card Number</label>
                                                <input id="card-no" class="form-control" required name="cardNo" id="card-number" aria-describedby="emailHelp" placeholder="Card Number">
                                            </div>
                                            <div class="form-row">
                                                <div class="form-group col-md-4">
                                                    <label for="expiry-month">Expiry Month</label>
                                                    <select name="expiryMonth" class="form-control" id="expiry-month">
                                                        <option selected disabled>Select Month</option>
                                                        <option value="01">01</option>
                                                        <option value="02">02</option>
                                                        <option value="03">03</option>
                                                        <option value="04">04</option>
                                                        <option value="05">05</option>
                                                        <option value="06">06</option>
                                                        <option value="07">07</option>
                                                        <option value="08">08</option>
                                                        <option value="09">09</option>
                                                        <option value="10">10</option>
                                                        <option value="11">11</option>
                                                        <option value="12">12</option>
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <label for="expiry-year">Expiry Year</label>
                                                    <select name="expiryYear" class="form-control" id="expiry-year">
                                                        <option selected disabled>Select Year</option>
                                                        <option value="2021">2021</option>
                                                        <option value="2022">2022</option>
                                                        <option value="2023">2023</option>
                                                        <option value="2024">2024</option>
                                                        <option value="2025">2025</option>
                                                        <option value="2026">2026</option>
                                                        <option value="2027">2027</option>
                                                        <option value="2029">2029</option>
                                                        <option value="2030">2030</option>
                                                        <option value="2031">2031</option>
                                                        <option value="2031">2031</option>
                                                        <option value="2033">2033</option>
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <label for="cvv">CVV</label>
                                                    <input type="number" placeholder="CVV Number" required name="cvvNo" min="100" max="999" maxlength="3" class="form-control" id="cvv">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="card-holder-name">Card Holder Name</label>
                                                <input type="text" class="form-control" required name="cardHolderName" id="card-holder-name" placeholder="Card Holder Name">
                                            </div>
                                            <div class="form-check d-flex align-items-center">
                                                <input type="checkbox" class="form-check-input" name="chkSaveCard" id="save-card">
                                                <label class="form-check-label" for="save-card">Save my card details</label>
                                            </div>
                                            <button type="submit" class="mt-4 btn PrimaryColor text-white w-100">Proceed to pay ₹<%=ticket.getNetAmount()%></button>
                                        </form>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="nav-internet-banking" role="tabpanel" aria-labelledby="nav-internet-banking-tab">
                                    Internet-Banking
                                </div>

                            </div>
                        </div>
                        <div class="col-5">
                            <div class="card w-100 Color3-bg">
                                <div class="card-body">
                                    <p class="SecondaryColor font-weight-bold">Payable Amount</p>
                                    <div class="justify-content-between d-flex">
                                        <h6>Base Price</h6>
                                        <h6>₹<%=ticket.getAmount()%></h6>
                                    </div>
                                    <div class="justify-content-between d-flex">
                                        <h6>GST (5%)</h6>
                                        <h6>₹<%=GSTAmount%></h6>
                                    </div>
                                    <hr>
                                    <div class="justify-content-between d-flex align-items-center">
                                        <h6 class="font-weight-bold">Total Amount</h6>
                                        <h4>₹<%=ticket.getNetAmount()%></h4>
                                    </div>
                                </div>
                            </div>

                            <div class="card w-100 Color3-bg mt-3">
                                <div class="card-body">
                                    <p class="font-weight-light">We value your privacy.</p>
                                    <p class="font-weight-light">We will not sell or distribute your information <br> Read our <a href="#" data-toggle="modal" data-target="#modal-terms-conditions">Privacy Policy</a></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%} else {%>
        <h5>Something went wrong</h5>
        <%}%>



        <%@include file="CommonJS.jsp" %>
        <script>
            var card_no = document.querySelector("#card-no");
            card_no.addEventListener("input", onChangeTxtCardNumber);

            function onChangeTxtCardNumber(e) {
                var cardNumber = card_no.value;

                // Do not allow users to write invalid characters
                var formattedCardNumber = cardNumber.replace(/[^\d]/g, "");
                formattedCardNumber = formattedCardNumber.substring(0, 16);

                // Split the card number is groups of 4
                var cardNumberSections = formattedCardNumber.match(/\d{1,4}/g);
                if (cardNumberSections !== null) {
                    formattedCardNumber = cardNumberSections.join('-');
                }

                console.log("'" + cardNumber + "' to '" + formattedCardNumber + "'");

                // If the formmattedCardNumber is different to what is shown, change the value
                if (cardNumber !== formattedCardNumber) {
                    card_no.value = formattedCardNumber;
                }
            }
        </script>
    </body>
</html>
