[#escape x as x?html]
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>${message("shop.error.title")}</title>
	<meta name="author" content="Hawods" />
	<meta name="copyright" content="Hawods" />

	<!-- bootstrap core css -->
	<link href="${base}/resources/css/bootstrap.min.css" rel="stylesheet">
	<!-- custom styles for this template -->
	<link href="${base}/resources/main/${theme}/css/common.css" rel="stylesheet" type="text/css" />
	<link href="${base}/resources/main/${theme}/css/error.css" rel="stylesheet" type="text/css" />

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="${base}/resources/js/html5shiv.min.js"></script>
	<script src="${base}/resources/js/respond.min.js"></script>
	<![endif]-->

	<script type="text/javascript" src="${base}/resources/main/${theme}/js/common.js"></script>
</head>
<body>
	[#include "/main/${theme}/include/header.ftl" /]
	<div class="container error">
		<div class="row">
			<div class="main">
				<dl>
					<dt>${message("shop.error.message")}</dt>
					[#if errorMessage?has_content]
						<dd>${errorMessage}</dd>
					[/#if]
					[#if exception?? && exception.message?has_content]
						<dd>${exception.message}</dd>
					[/#if]
					<dd>
						<a href="javascript:;" onclick="history.back(); return false;">&gt;&gt; ${message("shop.error.back")} &lt;&lt;</a>
					</dd>
					<dd>
						<a href="${base}/">&gt;&gt; ${message("shop.error.home")} &lt;&lt;</a>
					</dd>
				</dl>
			</div>
		</div>
	</div>
	[#include "/main/${theme}/include/footer.ftl" /]
    <script type="text/javascript" src="${base}/resources/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="${base}/resources/js/bootstrap.js"></script>
</body>
</html>
[/#escape]