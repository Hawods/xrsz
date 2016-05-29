[#escape x as x?html]
<div class="header">
	<div class="top">
		<div class="topNav">
			<ul class="left">
				<li>
					<span>${message("shop.header.welcome", setting.siteName)}</span>
					<span id="headerName" class="headerName">&nbsp;</span>
				</li>
				<li id="headerLogin" class="headerLogin">
					<a href="${base}/login.jhtml">${message("shop.header.login")}</a>|
				</li>
				<li id="headerRegister" class="headerRegister">
					<a href="${base}/register.jhtml">${message("shop.header.register")}</a>
				</li>
				<li id="headerLogout" class="headerLogout">
					<a href="${base}/logout.jhtml">[${message("shop.header.logout")}]</a>
				</li>
			</ul>
			<ul class="right">
				<li id="headerCart" class="headerCart">
					<a href="${base}/cart/list.jhtml">${message("shop.header.cart")}</a>
					(<em></em>)
				</li>
			</ul>
		</div>
	</div>
</div>
[/#escape]