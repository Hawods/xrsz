[#escape x as x?html]
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta property="qc:admins" content="36002676726301245335140636" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
[@seo type = "index"]
	<title>[#if seo.title??][@seo.title?interpret /][#else]${message("shop.index.title")}[/#if][#if showPowered] - Powered By Movitech[/#if]</title>
	<meta name="author" content="Movitech Team" />
	<meta name="copyright" content="Movitech" />
	[#if seo.keywords??]
		<meta name="keywords" content="[@seo.keywords?interpret /]" />
	[/#if]
	[#if seo.description??]
		<meta name="description" content="[@seo.description?interpret /]" />
	[/#if]
[/@seo]
	<link href="${base}/favicon.ico" rel="icon" type="image/x-icon" />

    <!-- bootstrap core css -->
    <link href="${base}/resources/shop/${theme}/css/bootstrap.min.css" rel="stylesheet">
    <!-- custom styles for this template -->
	<link href="${base}/resources/shop/${theme}/slider/slider.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/shop/${theme}/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/shop/${theme}/css/index.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
	<script src="${base}/resources/shop/${theme}/js/html5shiv.min.js"></script>
	<script src="${base}/resources/shop/${theme}/js/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="${base}/resources/shop/${theme}/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="${base}/resources/shop/${theme}/js/bootstrap.js"></script>
    <script type="text/javascript" src="${base}/resources/shop/${theme}/js/jquery.tools.js"></script>
	<script type="text/javascript" src="${base}/resources/shop/${theme}/js/common.js"></script>
<script type="text/javascript">
$(function() {
	var $image = $(".container .ad img");

    $image.lazyload({
		threshold: 10,
		effect: "fadeIn",
		skip_invisible: true
	});

});
</script>
</head>
<body>
	[#include "/shop/${theme}/include/header.ftl" /]
	<div class="container">
		<div class="ad topAd row">
			[@ad_position id = 1 /]
		</div>
        <div class="ad upsideAd row">
			[@ad_position id = 2 /]
        </div>
		<div class="ad middleAd row">
			[@ad_position id = 3 /]
		</div>
        <div class="ad downsideAd row">
			[@ad_position id = 4 /]
        </div>
        <div class="ad bottomAd row">
			[@ad_position id = 5 /]
        </div>
	</div>
	[#include "/shop/${theme}/include/footer.ftl" /]
	<script type="text/javascript">
        $(function() {
            var $sliderHammer = new Hammer($("#slider")[0]);
            $sliderHammer.on("panend", function (ev) {
                var $current = $(".nivo-controlNav").children(".nivo-control.active");
                var $next = ev.deltaX < 0 ? $current.nextAll(".nivo-control").first() : $current.prevAll(".nivo-control").first();
                if ($next.size() == 0) {
                    $next = ev.deltaX < 0 ? $current.parent().children(".nivo-control").first() : $current.parent().children(".nivo-control").last();
                }
                if ($next.size() > 0) {
                    $next.trigger("click");
                }
            });
        });
	</script>
</body>
</html>
[/#escape]