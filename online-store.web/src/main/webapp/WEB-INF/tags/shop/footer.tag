<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<footer>
	<div class="main-footer">
		<div class="container">
			<div class="row">
				<div class="footer-top clearfix">
					<div class="col-md-2 col-sm-6">
						<h2><spring:message code="footer.start.free.account" /></h2>
					</div>
					<div class="col-md-6 col-sm-6">
						<div class="form-box">
						<spring:message code="footer.enter.email" />
							
							<input type="text" placeholder="${footer.enter.email}" />
							<button><spring:message code="footer.continue" /></button>
							
						</div>
					</div>
					<div class="col-md-4 col-sm-12">
						<div class="help-box-f">
							<h4><spring:message code="footer.question" /></h4>
							<p><spring:message code="footer.easy.setup" /></p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="copyright">
		<div class="container">
			<div class="row">
				<div class="col-md-8">
					<p>
						<img width="90" src="/online-store.web/images/logo.png" alt="#"
							style="margin-top: -5px;" /> <spring:message code="footer.all.rights" />
					</p>
				</div>
				<div class="col-md-4">
					<ul class="list-inline socials">
						<li><a href=""> <i class="fa fa-facebook"
								aria-hidden="true"></i>
						</a></li>
						<li><a href=""> <i class="fa fa-twitter"
								aria-hidden="true"></i>
						</a></li>
						<li><a href=""> <i class="fa fa-instagram"
								aria-hidden="true"></i>
						</a></li>
						<li><a href="#"> <i class="fa fa-linkedin"
								aria-hidden="true"></i>
						</a></li>
					</ul>
					<ul class="right-flag">
						<li><a href="#"><img src="/online-store.web/images/flag.png" alt="" /> <span><spring:message code="change" /></span></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</footer>