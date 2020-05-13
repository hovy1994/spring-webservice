<span class="icon-bar"></span> <span class="icon-bar"></span> <span

        class="icon-bar"></span>

</button>

<a class="navbar-brand" href="main.jsp">JSP 게시판</a>

</div>

<div class="collapse navbar-collapse"

     id="#bs-example-navbar-collapse-1">

    <ul class="nav navbar-nav">

        <li><a href="main.jsp">메인</a></li>

        <li class="active"><a href="bbs.jsp">게시판</a></li>

    </ul>





    <%

        //라긴안된경우

        if (userID == null) {

    %>

    <ul class="nav navbar-nav navbar-right">

        <li class="dropdown"><a href="#" class="dropdown-toggle"

                                data-toggle="dropdown" role="button" aria-haspopup="true"



                                aria-expanded="false">접속하기<span class="caret"></span></a>

            <ul class="dropdown-menu">

                <li><a href="login.jsp">로그인</a></li>

                <li><a href="join.jsp">회원가입</a></li>

            </ul></li>

    </ul>

    <%

    } else {

    %>

    <ul class="nav navbar-nav navbar-right">

        <li class="dropdown"><a href="#" class="dropdown-toggle"

                                data-toggle="dropdown" role="button" aria-haspopup="true"



                                aria-expanded="false">회원관리<span class="caret"></span></a>

            <ul class="dropdown-menu">

                <li><a href="logoutAction.jsp">로그아웃</a></li>

            </ul></li>

    </ul>

    <%

        }

    %>

</div>

</nav>

<!-- 게시판 -->

<div class="container">

    <div class="row">

        <table class="table table-striped"

               style="text-align: center; border: 1px solid #dddddd">

            <thead>

            <tr>

                <th style="background-color: #eeeeee; text-align: center;">번호</th>

                <th style="background-color: #eeeeee; text-align: center;">제목</th>



                <th style="background-color: #eeeeee; text-align: center;">작성자</th>

                <th style="background-color: #eeeeee; text-align: center;">작성일</th>

            </tr>

            </thead>

            <tbody>

            <%

                BbsDAO bbsDAO = new BbsDAO();

                ArrayList<Bbs> list = bbsDAO.getList(pageNumber);

                for (int i = 0; i < list.size(); i++) {

            %>

            <tr>

                <td><%=list.get(i).getBbsID()%></td>

                <td><a href="view.jsp?bbsID=<%=list.get(i).getBbsID()%>"><%=list.get(i).getBbsTitle()%></a></td>

                <td><%=list.get(i).getUserID()%></td>

                <td><%=list.get(i).getBbsDate().substring(0, 11) + list.get(i).getBbsDate().substring(11, 13) + "시"

                        + list.get(i).getBbsDate().substring(14, 16) + "분"%></td>

            </tr>



            <%

                }

            %>



            </tbody>

        </table>

        <!-- 페이지 넘기기 -->

        <%

            if (pageNumber != 1) {

        %>

        <a href="bbs.jsp?pageNumber=<%=pageNumber - 1%>"

           class="btn btn-success btn-arrow-left">이전</a>

        <%

            }

            if (bbsDAO.nextPage(pageNumber)) {

        %>

        <a href="bbs.jsp?pageNumber=<%=pageNumber + 1%>"

           class="btn btn-success btn-arrow-left">다음</a>

        <%

            }

        %>





        <!-- 회원만넘어가도록 -->

        <%

            //if logined userID라는 변수에 해당 아이디가 담기고 if not null

            if (session.getAttribute("userID") != null) {

        %>

        <a href="write.jsp" class="btn btn-primary pull-right">글쓰기</a>

        <%

        } else {

        %>

        <button class="btn btn-primary pull-right"

                onclick="if(confirm('로그인 하세요'))location.href='login.jsp';"

                type="button">글쓰기</button>

        <%

            }

        %>



    </div>

</div>









<!-- 애니매이션 담당 JQUERY -->

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

<!-- 부트스트랩 JS  -->

<script src="js/bootstrap.js"></script>



</body>

</html>



출처: https://tbbrother.tistory.com/71 [밑끝없로그]	<span class="icon-bar"></span> <span class="icon-bar"></span> <span

        class="icon-bar"></span>

</button>

<a class="navbar-brand" href="main.jsp">JSP 게시판</a>

</div>

<div class="collapse navbar-collapse"

     id="#bs-example-navbar-collapse-1">

    <ul class="nav navbar-nav">

        <li><a href="main.jsp">메인</a></li>

        <li class="active"><a href="bbs.jsp">게시판</a></li>

    </ul>





    <%

        //라긴안된경우

        if (userID == null) {

    %>

    <ul class="nav navbar-nav navbar-right">

        <li class="dropdown"><a href="#" class="dropdown-toggle"

                                data-toggle="dropdown" role="button" aria-haspopup="true"



                                aria-expanded="false">접속하기<span class="caret"></span></a>

            <ul class="dropdown-menu">

                <li><a href="login.jsp">로그인</a></li>

                <li><a href="join.jsp">회원가입</a></li>

            </ul></li>

    </ul>

    <%

    } else {

    %>

    <ul class="nav navbar-nav navbar-right">

        <li class="dropdown"><a href="#" class="dropdown-toggle"

                                data-toggle="dropdown" role="button" aria-haspopup="true"



                                aria-expanded="false">회원관리<span class="caret"></span></a>

            <ul class="dropdown-menu">

                <li><a href="logoutAction.jsp">로그아웃</a></li>

            </ul></li>

    </ul>

    <%

        }

    %>

</div>

</nav>

<!-- 게시판 -->

<div class="container">

    <div class="row">

        <table class="table table-striped"

               style="text-align: center; border: 1px solid #dddddd">

            <thead>

            <tr>

                <th style="background-color: #eeeeee; text-align: center;">번호</th>

                <th style="background-color: #eeeeee; text-align: center;">제목</th>



                <th style="background-color: #eeeeee; text-align: center;">작성자</th>

                <th style="background-color: #eeeeee; text-align: center;">작성일</th>

            </tr>

            </thead>

            <tbody>

            <%

                BbsDAO bbsDAO = new BbsDAO();

                ArrayList<Bbs> list = bbsDAO.getList(pageNumber);

                for (int i = 0; i < list.size(); i++) {

            %>

            <tr>

                <td><%=list.get(i).getBbsID()%></td>

                <td><a href="view.jsp?bbsID=<%=list.get(i).getBbsID()%>"><%=list.get(i).getBbsTitle()%></a></td>

                <td><%=list.get(i).getUserID()%></td>

                <td><%=list.get(i).getBbsDate().substring(0, 11) + list.get(i).getBbsDate().substring(11, 13) + "시"

                        + list.get(i).getBbsDate().substring(14, 16) + "분"%></td>

            </tr>



            <%

                }

            %>



            </tbody>

        </table>

        <!-- 페이지 넘기기 -->

        <%

            if (pageNumber != 1) {

        %>

        <a href="bbs.jsp?pageNumber=<%=pageNumber - 1%>"

           class="btn btn-success btn-arrow-left">이전</a>

        <%

            }

            if (bbsDAO.nextPage(pageNumber)) {

        %>

        <a href="bbs.jsp?pageNumber=<%=pageNumber + 1%>"

           class="btn btn-success btn-arrow-left">다음</a>

        <%

            }

        %>





        <!-- 회원만넘어가도록 -->

        <%

            //if logined userID라는 변수에 해당 아이디가 담기고 if not null

            if (session.getAttribute("userID") != null) {

        %>

        <a href="write.jsp" class="btn btn-primary pull-right">글쓰기</a>

        <%

        } else {

        %>

        <button class="btn btn-primary pull-right"

                onclick="if(confirm('로그인 하세요'))location.href='login.jsp';"

                type="button">글쓰기</button>

        <%

            }

        %>



    </div>

</div>









<!-- 애니매이션 담당 JQUERY -->

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

<!-- 부트스트랩 JS  -->

<script src="js/bootstrap.js"></script>



</body>

</html>


