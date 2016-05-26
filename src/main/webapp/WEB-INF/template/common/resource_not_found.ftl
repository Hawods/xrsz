[#escape x as x?html]
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>${message("shop.resourceNotFound.title")}[#if showPowered] - Powered By Movitech[/#if]</title>
    <meta name="author" content="Movitech Team" />
    <meta name="copyright" content="Movitech" />

    <!-- bootstrap core css -->
    <link href="${base}/resources/shop/${theme}/css/bootstrap.min.css" rel="stylesheet">
    <!-- custom styles for this template -->
    <link href="${base}/resources/shop/${theme}/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/shop/${theme}/css/error.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
	<script src="${base}/resources/shop/${theme}/js/html5shiv.min.js"></script>
	<script src="${base}/resources/shop/${theme}/js/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="${base}/resources/shop/${theme}/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="${base}/resources/shop/${theme}/js/bootstrap.js"></script>
    <script type="text/javascript" src="${base}/resources/shop/${theme}/js/common.js"></script>
</head>
<body>
	[#include "/shop/${theme}/include/header.ftl" /]
	<div class="container error">
		<div class="row">
			<div class="main">
				<dl>
					[#noescape]
						${message("shop.resourceNotFound.message")}
					[/#noescape]
					<dd>
						<a href="javascript:;" onclick="history.back(); return false;">&gt;&gt; ${message("shop.resourceNotFound.back")} &lt;&lt;</a>
					</dd>
					<dd>
						<a href="${base}/">&gt;&gt; ${message("shop.resourceNotFound.home")} &lt;&lt;</a>
					</dd>
				</dl>
			</div>
		</div>
	</div>
	[#include "/shop/${theme}/include/footer.ftl" /]
</body>
</html>
[/#escape]