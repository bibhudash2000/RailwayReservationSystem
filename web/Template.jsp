<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            h1{
               
            }
        </style>
    </head>
    <body>
        <div style="background: #3f51b5; display: flex; align-items: center;">
            <img src="http://localhost:8084/RailwayReservationSystem/img/train_ico.png" style="height: 50px; width: 50px;">&nbsp;
            <h1 style="color: white;">RRS</h1>
        </div>
        <div style="justify-content: center; text-align: center;background: #F6F6F6;">
            <img src="http://localhost:8084/RailwayReservationSystem/img/email.png" style="height: 200px; width:500px;">
        </div>
        <div style="justify-content: center; text-align: center; background: #ffffff; margin-left: 230px;margin-right: 230px;  ">
            <h1 style="font-family: sans-serif; font-weight: 100;">Email Confirmation</h1>
            <p style="font-size: 20px;">Hey User, you're almost ready to start booking your trains.
            Simply click the big blue button below to verify your email address.</p>
            <a href="#" onmouseover="this.style.color='#000'" onmouseout="this.style.color='#FFF'" style="transition: 0.2s ease-in ;background: #3f51b5; color: white;font-size: 20px; text-decoration: none; padding: 12px; margin-top: 5px;border-radius: 5px;">
                Verify email address</a>
        </div>
    </body>
</html>
