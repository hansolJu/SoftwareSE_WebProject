<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta name="description" content="Metadata page" />
      <meta name="author" content="Eunjin" />
      <link rel="stylesheet" type="text/css" href="css/demo.css" />
      <link rel="stylesheet" type="text/css" href="css/style.css" />
      <script src="js/modernizr.custom.97074.js"></script>
      <noscript><link rel="stylesheet" type="text/css" href="css/noJS.css"/></noscript>
    </head>
</head>
 <body>
        <div class="container">
            <section>
                <ul id="da-thumbs" class="da-thumbs">
                    <li>
                        <a href="http://www.google.com">
                            <img src="images/1.jpg" />
                            <div><span>test</span></div>
                        </a>
                    </li>
                </ul>
            </section>
        </div>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery.hoverdir.js"></script>
        <script type="text/javascript">
            $(function () {
                $(' #da-thumbs > li ').hoverdir();
            });
        </script>
    </body>
</html>