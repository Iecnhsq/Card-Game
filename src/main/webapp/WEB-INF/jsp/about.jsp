<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - About Page</title>
        <style type="text/css" media="all">
            body {
                background-image: url(Image/fon.png);
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: 100% 100%;
            }
            div {
                width: 1024px;
                margin: auto;
                border-radius: 8px;
                background-color: rgba(84,82,71,.5);
                color: white;
                font: 14pt Arial, Helvetica, sans-serif;
                padding: 0px 0px 30px 0px;
                text-align: center;
            }
            a {
                text-decoration: none;
                color: white;
                padding: 10px;
                background-color: #545247;
                border-radius: 4px;
            }
            a:hover {
                color: gainsboro;
            }
            p {
                text-align: justify;
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <div>
            <h1><b>About on Card Game</b></h1><br>
            <p>
                As in other collectible card games, the main goal of a player like as 
                <b>Hearthstone</b> is to collect their own card collection, which is replenished 
                by purchasing sets of cards or receiving them as a reward. 
                Matches between players are reduced to playing cards with 
                the task of first reducing the opponent's health points to zero.
            </p>
            <p>
                You only have ten cards from the deck of the hero. You must choose 
                the most winning combination of cards from the deck to win. If you 
                run out of cards, the hero for each move takes away his health. You 
                should play cards as correctly as possible to win.<br><br>Successful fights..! =)
            </p>
            <a href="/CardGame/main.html">Back to main</a>
        </div>
    </body>
</html>
