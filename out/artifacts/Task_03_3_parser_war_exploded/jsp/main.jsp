<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Welcome</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <h1>Tourist vouchers</h1>
    <table border="1" class="table">
        <tr>
            <th colspan="5">
                MAIN INFORMATION
            </th>
            <th colspan="7">
                ABOUT HOTEL
            </th>
            <th colspan="2">
                COST
            </th>
        </tr>
        <tr>
            <th>
                country
            </th>
            <th>
                transport
            </th>
            <th>
                type
            </th>
            <th>
                number of days
            </th>
            <th>
                number of nights
            </th>
            <th>
                name
            </th>
            <th>
                food
            </th>
            <th>
                TV
            </th>
            <th>
                WiFi
            </th>
            <th>
                air conditioning
            </th>
            <th>
                amount of stars
            </th>
            <th>
                amount of rooms
            </th>
            <th>
                include
            </th>
            <th>
                price
            </th>
        </tr>
        <jsp:useBean id="touristVouchers" class="by.bsu.mmf.shevcova.servlet.entity.voucher.TouristVoucher"
                     scope="request" type="java.lang.Object"/>
        <jsp:useBean id="hotel" class="by.bsu.mmf.shevcova.servlet.entity.voucher.Hotel"
                     scope="request" type="java.lang.Object"/>
        <c:forEach var="voucher" items="${requestScope.touristVouchers}">
            <jsp:setProperty name="voucher" property="country"/>
            <jsp:setProperty name="voucher" property="transport"/>
            <jsp:setProperty name="voucher" property="type"/>
            <jsp:setProperty name="voucher" property="numberDays"/>
            <jsp:setProperty name="voucher" property="numberNights"/>
            <jsp:setProperty name="voucher" property="hotel"/>
            <c:set var="hotel" value="${voucher.hotel}"/>
            <jsp:setProperty name="hotel" property="name"/>
            <jsp:setProperty name="hotel" property="food"/>
            <jsp:setProperty name="hotel" property="TV"/>
            <jsp:setProperty name="hotel" property="wiFi"/>
            <jsp:setProperty name="hotel" property="airConditioning"/>
            <jsp:setProperty name="hotel" property="amountOfStars"/>
            <jsp:setProperty name="hotel" property="amountOfRoom"/>
            <jsp:setProperty name="voucher" property="cost"/>
            <c:set var="cost" value="${voucher.cost}"/>
            <jsp:setProperty name="cost" property="include"/>
            <jsp:setProperty name="cost" property="price"/>
            <tr>
                <th>
                    <c:out value="${voucher.country}"/>
                </th>
                <th>
                    <c:out value="${voucher.transport}"/>
                </th>
                <th>
                    <c:out value="${voucher.type}"/>
                </th>
                <th>
                    <c:out value="${voucher.numberDays}"/>
                </th>
                <th>
                    <c:out value="${voucher.numberNights}"/>
                </th>
                <th>
                    <c:out value="${hotel.name}"/>
                </th>
                <th>
                    <c:out value="${hotel.food}"/>
                </th>
                <th>
                    <c:if test="${empty hotel.TV}">
                        <c:out value="-"/>
                    </c:if>
                    <c:out value="${hotel.TV}"/>
                </th>
                <th>
                    <c:if test="${empty hotel.wiFi}">
                        <c:out value="-"/>
                    </c:if>
                    <c:out value="${hotel.wiFi}"/>
                </th>
                <th>
                    <c:if test="${empty hotel.airConditioning}">
                        <c:out value="-"/>
                    </c:if>
                    <c:out value="${hotel.airConditioning}"/>
                </th>
                <th>
                    <c:out value="${hotel.amountOfStars}"/>
                </th>
                <th>
                    <c:out value="${hotel.amountOfRoom}"/>
                </th>
                <th>
                    <c:out value="${cost.include}"/>
                </th>
                <th>
                    <c:out value="${cost.price}"/>
                </th>
            </tr>
        </c:forEach>
    </table>

    <div class="link">
    <%--For displaying Previous link except for the 1st page --%>
        <c:if test="${currentPage != 1}">
            <p><a href="controller?page=${currentPage - 1}">Previous</a></p>
        </c:if>
    <%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
        <c:forEach begin="1" end="${amountOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <p>${i}</p>
                </c:when>
                <c:otherwise>
                    <p><a href="controller?page=${i}">${i}</a></p>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <%--For displaying Next link --%>
        <c:if test="${currentPage lt amountOfPages}">
            <p><a href="controller?page=${currentPage + 1}">Next</a></p>
        </c:if>
    </div>
`   <a href="/index.jsp" class="backToMain">back to main</a>
</body>
</html>