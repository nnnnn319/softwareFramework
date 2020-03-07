<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/10/5
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <link rel="StyleSheet" href="../../../css/jpetstore.css" type="text/css"
          media="screen" />

    <meta name="generator"
          content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org" />
    <title>JPetStore Demo</title>
    <meta content="text/html; charset=windows-1252"
          http-equiv="Content-Type" />
    <meta http-equiv="Cache-Control" content="max-age=0" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="pragma" content="no-cache">





</head>

<body>

<div id="Header">

    <div id="Logo">
        <div id="LogoContent">
            <a href="main"><img src="images/logo-topbar.gif" /></a>
        </div>
    </div>
    <script src="../../../js/jquery-3.4.1.js"></script>
    <script src="../../../js/layer-v3.1.1/layer/layer.js"></script>
    <script src="../../../js/SignIn.js"></script>
    <div id="Menu">
        <div id="MenuContent">
            <a href="viewCart" id="viewCart">
                <img align="middle" name="img_cart" src="images/cart.gif" />
            </a>
            <img align="middle" src="images/separator.gif" />
            <c:if test="${sessionScope.account == null}"><a href="signon"> Sign In </a></c:if>
            <c:if test="${sessionScope.account != null}">
                <a href="sign_out"> Sign Out </a>
                <img align="middle" src="images/separator.gif" />
                <a href="view_account"?userName=${sessionScope.account.username}">My Account</a>
            </c:if>

            <img align="middle" src="images/separator.gif" />

            <a href="../help.html">?</a>
        </div>
    </div>

    <div id="Search">
        <div id="SearchContent">
            <form action="search" method="post">
                <script src="../../../js/jquery-3.4.1.js"></script>
                <script src="../../../js/AutoComplete.js"></script>
                <input type="text" name="keyword" size="14" id = "keyword" list="searchResult" autocomplete="off"/>
                <datalist id="searchResult"></datalist>
                <input type="submit" name="searchProducts" value="Search" />


            </form>
        </div>
    </div>

    <div id="QuickLinks">
        <a href="view_category?categoryId=FISH">
            <img src="images/sm_fish.gif" />
        </a>
        <img src="images/separator.gif" />
        <a href="view_category?categoryId=DOGS">
            <img src="images/sm_dogs.gif" />
        </a>
        <img src="images/separator.gif" />
        <a href="view_category?categoryId=REPTILES">
            <img src="images/sm_reptiles.gif" />
        </a>
        <img src="images/separator.gif" />
        <a href="view_category?categoryId=CATS">
            <img src="images/sm_cats.gif" />
        </a>
        <img src="images/separator.gif" />
        <a href="view_category?categoryId=BIRDS">
            <img src="images/sm_birds.gif" />
        </a>
    </div>

</div>

<div id="Content" >
