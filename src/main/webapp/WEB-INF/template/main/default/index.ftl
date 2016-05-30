[#escape x as x?html]
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    [@seo type = "index"]
        <title>[#if seo.title??][@seo.title?interpret /][#else]${message("shop.index.title")}[/#if]</title>
        <meta name="author" content="Hawods" />
        <meta name="copyright" content="Hawods" />
        [#if seo.keywords??]
            <meta name="keywords" content="[@seo.keywords?interpret /]" />
        [/#if]
        [#if seo.description??]
            <meta name="description" content="[@seo.description?interpret /]" />
        [/#if]
    [/@seo]
    <link href="${base}/favicon.ico" rel="icon shortcut" />

    <!-- bootstrap core css -->
    <link href="${base}/resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- custom styles for this template -->
    <link href="${base}/resources/css/main/${theme}/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/css/main/${theme}/index.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
	<script src="${base}/resources/js/html5shiv.min.js"></script>
	<script src="${base}/resources/js/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	[#include "/main/${theme}/include/header.ftl" /]
	<div class="container">
        main index
	</div>
	[#include "/main/${theme}/include/footer.ftl" /]
    <script type="text/javascript" src="${base}/resources/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="${base}/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${base}/resources/js/jquery.lazyload.min.js"></script>
    <script type="text/javascript" src="${base}/resources/js/main/${theme}/common.js"></script>
    <script type="text/javascript">
        $(function() {
            $("img").lazyload({
                threshold: 10,
                effect: "fadein",
                skip_invisible: true
            });
        });
    </script>
</body>
</html>
[/#escape]