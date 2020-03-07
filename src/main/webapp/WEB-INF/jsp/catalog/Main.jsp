<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/10/5
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp" %>
<div id="Welcome">
    <div id="WelcomeContent">
        <c:if test="${sessionScope.account != null}">
            Welcome to MyPetStore ${sessionScope.account.firstName}!
        </c:if>
        <c:if test="${sessionScope.account == null}">
            Welcome to MyPetStore !
        </c:if>
    </div>
</div>

<div id="Main">
    <div id="Sidebar">
        <div id="SidebarContent">
            <a href="view_category?categoryId=FISH"><img src="images/fish_icon.gif" /></a>
            <br/> Saltwater, Freshwater <br/>
            <a href="view_category?categoryId=DOGS"><img src="images/dogs_icon.gif" /></a>
            <br /> Various Breeds <br />
            <a href="view_category?categoryId=CATS"><img src="images/cats_icon.gif" /></a>
            <br /> Various Breeds, Exotic Varieties <br />
            <a href="view_category?categoryId=REPTILES"><img src="images/reptiles_icon.gif" /></a>
            <br /> Lizards, Turtles, Snakes <br />
            <a href="view_category?categoryId=BIRDS"><img src="images/birds_icon.gif" /></a>
            <br /> Exotic Varieties
        </div>
    </div>

    <script src="../../../js/jquery-3.4.1.js"></script>
    <script src="../../../js/FloatWindow.js"></script>

    <div id="MainImage">
        <div id="MainImageContent">
            <map name="estoremap">
                <area alt="Birds" coords="72,2,280,250" href="view_category?categoryId=BIRDS" shape="rect" id="birds" class="float"/>
                <area alt="Fish" coords="2,180,72,250" href="view_category?categoryId=FISH" shape="rect" id="fish" class="float"/>
                <area alt="Dogs" coords="60,250,130,320" href="view_category?categoryId=DOGS" shape="rect" id="dogs" class="float"/>
                <area alt="Reptiles" coords="140,270,210,340" href="view_category?categoryId=REPTILES" shape="rect" id="reptiles" class="float"/>
                <area alt="Cats" coords="225,240,295,310" href="view_category?categoryId=CATS" shape="rect" id="cats" class="float"/>
                <area alt="Birds" coords="280,180,350,250" href="view_category?categoryId=BIRDS" shape="rect" id="bird" class="float"/>
            </map>
            <img height="355" src="images/splash.gif" align="middle" usemap="#estoremap" width="350" />
        </div>
        <style type="text/css">
            #float
            {
                /*position: absolute;*/
                /*background-color: #cfc;*/
                /*padding: 4px;*/
                /*border: 2px solid #9c9;*/
                /*-webkit-border-radius: 4px;*/
                /*-moz-border-radius: 4px;*/

                /*bottom:5px;*/
                /*width:140px;*/
                /*right:390px;*/
                /*border:2px solid #6FA833;*/
                /*padding:10px;*/
                /*background-color:#fff;*/
                /*border-radius:5px 5px 5px 5px !important;*/
                /*box-shadow:0 0 0 1px #5F5A4B, 1px 1px 6px 1px rgba(10, 10, 0, 0.5);*/

                padding: 10px;
                filter:alpha(Opacity=80);
                position: absolute;
                background-color: rgba(50, 50, 50, 0.6);
                font-size: 0.373333rem;
                border-radius: 5px;
                color: #ffffff;
                z-index: 9999;
                max-width: 50%;
                display: none;
                overflow-y: scroll;
                height: 170px;
            }
        </style>
        <div id="float" >specific information</div>
    </div>
    <div id="Separator">&nbsp;</div>
</div>
<%@ include file="../common/IncludeBottom.jsp"%>
