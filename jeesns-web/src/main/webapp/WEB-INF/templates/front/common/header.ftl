<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">JEESNS</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${basePath}/">JEESNS</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="${basePath}/">首页</a></li>
                <li><a href="${basePath}/article/list">文章</a></li>
                <li><a href="${basePath}/weibo/list">微博</a></li>
                <li><a href="${basePath}/group/">群组</a></li>
                <li><a href="${basePath}/action/list">动态</a></li>
            </ul>
            <ul class="nav navbar-top-links navbar-right">
                <div class="nav navbar-nav navbar-nav-right">
                <#if loginMember == null>
                    <li><a href="${basePath}/member/login">登录</a></li>
                    <li><a href="${basePath}/member/register">注册</a></li>
                <#else>
                    <div class="btn-group nav-username">
                        <img src="${basePath}${loginMember.avatar}" class="img-circle" width="25px" height="25px" style="margin-top: 1px;margin-right:5px;"/>
                        <a class="header-action-link" href="javascript:void(0)">
                        ${loginMember.name}
                            <#if unReadMessageNum &gt; 0><i class="fa fa-comment-o text-red"></i></#if>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${basePath}/member/">个人中心</a></li>
                            <li><a href="${basePath}/member/message">私信
                            ${(unReadMessageNum > 0)?string("(<span class='text-red'>"+unReadMessageNum+"</span>)","")}
                            </a></li>
                            <li><a href="${basePath}/member/editInfo">设置</a></li>
                            <#if loginMember?? && loginMember.isAdmin &gt; 0>
                                <li><a href="${managePath}/" target="_blank">管理</a></li>
                            </#if>
                            <li class="divider"></li>
                            <li><a href="${basePath}/member/logout">退出</a></li>
                        </ul>
                        <script>
                            $(function () {
                                $(".nav-username").hover(function(){
                                    $(this).addClass("open");
                                },function(){
                                    $(this).removeClass("open");
                                });
                            })
                        </script>
                    </div>
                </#if>
                </div>
            </ul>
        </div>
    </div>
</nav>