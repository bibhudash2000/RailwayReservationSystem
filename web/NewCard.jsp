<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="CommonCSS.jsp" %>
        <title>New Card</title>

    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <div class="container-fluid d-flex justify-content-center">
            <div class="card my-3" style="width:30rem;">
                <div class="card-header PrimaryColor text-white align-items-center d-flex">
                    <span class="material-icons">
                        payment
                    </span>Add Payment Card</div>
                <div class="card-body">
                    <form class="py-1" id="add-card-form" method="POST" action="AddPaymentCardServlet">

                        <p class="SecondaryColor font-weight-bold">Enter Card Details</p>
                        <div class="form-group">
                            <label for="card-number">Enter Debit/Credit Card Number</label>
                            <input id="card-no" class="form-control" required name="cardNo" id="card-number" aria-describedby="emailHelp" placeholder="Card Number">
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="expiry-month">Expiry Month</label>
                                <input type="number" placeholder="Expiry Month" required name="expiryMonth" min="1" max="12" class="form-control" id="expiry-month">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="expiry-year">Expiry Year</label>
                                <input type="number" placeholder="Expiry Year" required name="expiryYear" class="form-control" id="expiry-year">
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


                    </form>
                </div>
                <div class="card-footer d-flex justify-content-center">
                    <button type="submit" form="add-card-form" id="btnAddCard" class="mx-1 btn PrimaryColor text-white d-flex align-items-center justify-content-center">
                        <span class="material-icons">
                            done
                        </span>
                        Proceed to add</button>
                    <button type="button" class="mx-1 btn btn-secondary d-flex align-items-center justify-content-center">
                        <span class="material-icons">
                            clear
                        </span>Cancel
                    </button>
                </div>
            </div>
        </div>


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
        <script>
            $(window).ready(function () {

            });
        </script>

    </body>
</html>
