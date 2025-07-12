<%@ page import="model.Banner" %>
<%@ page import="model.DAO.VideogiocoDAO" %>
<%@ page import="model.DAO.BannerDAO" %>
<%@ page import="model.Videogioco" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    request.setAttribute("paginaCorrente", "home.jsp");

    VideogiocoDAO videogiocoDAO = new VideogiocoDAO();

    BannerDAO bannerDAO = new model.DAO.BannerDAO();
    Banner banner = bannerDAO.doRetrieveById(1);

    Videogioco videogiocoBanner = null;
    if (banner != null) {
        videogiocoBanner = videogiocoDAO.doRetrieveById(banner.getIdVideogioco());
    }

    request.setAttribute("videogiochi", videogiocoDAO.doRetrieveAllActive());
%>

<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>UniGame</title>

        <%-- css --%>
        <link rel="stylesheet" href="css/home.css">
    </head>
    <body>
        <main>
            <%@ include file="WEB-INF/navbar.jsp" %>

            <%
                if (videogiocoBanner != null) {
            %>
                    <div class="banner" style="background: url('http://cdn.cloudflare.steamstatic.com/steam/apps/<%= videogiocoBanner.getAppIdSteam() %>/library_hero.jpg') center center / cover no-repeat;">

                        <c:if test="${sessionScope.utente != null  and sessionScope.utente.admin}">
                            <div class="action">
                                <button  onclick="apriModaleUpdateBanner()">
                                    <i class="fa-solid fa-pen-nib"></i>
                                </button>
                            </div>

                            <div class="action-mobile">
                                <button onclick="apriModaleUpdateBanner()">
                                    <i class="fa-solid fa-pen-nib"></i>
                                </button>
                            </div>
                        </c:if>
                        <div class="banner-container">
                            <h1><%= videogiocoBanner.getTitolo() %></h1>

                            <div class="price-row">
                                <span class="discount-tag">-<%= videogiocoBanner.getSconto() %>%</span>
                                <p class="price"><%= String.format("%.2f", videogiocoBanner.getPrezzoScontato()) %> €</p>
                            </div>
                        </div>
                    </div>
            <%
                } else {
            %>
                    <div class="banner-not-found">
                        <h1>Banner non disponibile</h1>

                        <c:if test="${sessionScope.utente != null  and sessionScope.utente.admin}">
                            <div class="action">
                                <button>
                                    <i class="fa-solid fa-plus" onclick="apriModaleAddBanner()"></i>
                                </button>
                            </div>

                            <div class="action-mobile">
                                <button>
                                    <i class="fa-solid fa-plus" onclick="apriModaleAddBanner()"></i>
                                </button>
                            </div>
                        </c:if>
                    </div>
            <%
                }
            %>

            <%@ include file="WEB-INF/game-grid.jsp" %>

            <%-- modali --%>
            <%@ include file="WEB-INF/modal-add-banner.jsp" %>

            <%@ include file="WEB-INF/modal-update-banner.jsp" %>

            <%@ include file="WEB-INF/modal-add-game.jsp" %>

        </main>

        <%@ include file="WEB-INF/footer.jsp" %>
    </body>
</html>