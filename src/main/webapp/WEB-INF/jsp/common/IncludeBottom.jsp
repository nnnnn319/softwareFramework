<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/10/5
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
</div>

<div id="Footer">

    <div id="PoweredBy">&nbsp<a href="www.csu.edu.cn">www.csu.edu.cn</a>
    </div>

    <div id="Banner">
        <c:if test="${sessionScope.account != null}">
            <a href="view_category?categoryId=${sessionScope.account.getFavouriteCategoryId()}">${sessionScope.account.getBannerName()}</a>
        </c:if>
    </div>

</div>

</body>
</html>