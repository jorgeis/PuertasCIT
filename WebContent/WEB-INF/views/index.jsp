<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/views/headfoot/headerm.jsp" />

		<!-- Banner Wrapper -->
			<div id="banner-wrapper">

				<!-- Banner -->
					<div class="5grid-layout">
						<div class="row">
							<div class="12u">
								<div id="banner">
									<div class="viewer">
										<div class="nav-next">Next</div>
										<div class="nav-previous">Previous</div>
										<div class="reel">
										
											<!-- Slide 1 -->
												<div class="slide">
													<h2 class="caption-1">Título 1</h2>
													<p class="caption-2">Este es el texto de descripción de la imagen mostrada, el cual cambia junto con la imagen.</p>
													<a href="#" class="link">More ...</a>
													<img src='<c:url value = "/res/images/slide01.jpg" />' alt="" />
												</div>

											<!-- Slide 2 -->
												<div class="slide">
													<h2 class="caption-1">Título 2</h2>
													<p class="caption-2">Este es el texto de descripción de la imagen mostrada, el cual cambia junto con la imagen.</p>
													<a href="#" class="link">More ...</a>
													<img src='<c:url value = "/res/images/slide02.jpg" />' alt="" />
												</div>

											<!-- Slide 3 -->
												<div class="slide">
													<h2 class="caption-1">Título 3</h2>
													<p class="caption-2">Este es el texto de descripción de la imagen mostrada, el cual cambia junto con la imagen.</p>
													<a href="#" class="link">More ...</a>
													<img src='<c:url value = "/res/images/slide03.jpg" />' alt="" />
												</div>

										</div>
									</div>
									<div class="captions">
										<span class="caption-line-1"></span>
										<span class="caption-line-2"></span>
									</div>
								</div>
							</div>
						</div>
					</div>
					
			</div>
				
		<!-- Main -->
			<div id="main-wrapper">
				<div class="5grid-layout">
					<div class="row">
						<div class="8u mobileUI-main-content">
							
							<!-- Content -->
								<div id="content">
								
									<!-- Article -->
										<article class="featured">
											<header>
												<h2>Lorem feugiat et dolore</h2>
												<span class="byline">Dolor sit amet adipiscing consequat veroeros</span>
											</header>
											<p>Phasellus dapibus convallis scelerisque. Donec tempus augue id tortor ultricies eget 
											pellentesque turpis ultrices. Vestibulum ut nulla sem, vel iaculis arcu. Nulla vel lorem 
											nisl, quis ultricies tellus. Nunc imperdiet elit sed dolore lacus venenatis rutrum. 
											Vivamus vulputate urna dolor est sagittis purus, in laoreet. Nunc imperdiet elit sed dolore 
											lacus venenatis dolore vulputate eros non nisl.</p>
											<div class="5grid grid-spaced">
												<div class="row">
													<section class="6u">
														<a href="#" class="image image-fit"><img src='<c:url value = "/res/images/pic01.jpg" />' alt="" /></a>
														<h3>Dolore nisl vivamus consequat</h3>
														<p>Nulla sem, vel iaculis arcu. Nulla vel lorem nisl, quis ultricies 
														tellus vulputate urna dolor est sagittis purus, in laoreet. Nunc imper diet 
														elit sed dolore lacus dolore. <a href="#">Learn more ...</a></p>
													</section>
													<section class="6u">
														<a href="#" class="image image-fit"><img src='<c:url value = "/res/images/pic02.jpg" />' alt="" /></a>
														<h3>Magna phasellus nisl lorem</h3>
														<p>Nulla sem, vel iaculis arcu. Nulla vel lorem nisl, quis ultricies 
														tellus vulputate urna dolor est sagittis purus, in laoreet. Nunc imper diet 
														elit sed dolore lacus dolore. <a href="#">Learn more ...</a></p>
													</section>
												</div>
												<div class="row">
													<section class="6u">
														<a href="#" class="image image-fit"><img src='<c:url value = "/res/images/pic03.jpg" />' alt="" /></a>
														<h3>Volutpat sed sagittis laoreet</h3>
														<p>Nulla sem, vel iaculis arcu. Nulla vel lorem nisl, quis ultricies 
														tellus vulputate urna dolor est sagittis purus, in laoreet. Nunc imper diet 
														elit sed dolore lacus dolore. <a href="#">Learn more ...</a></p>
													</section>
													<section class="6u">
														<a href="#" class="image image-fit"><img src='<c:url value = "/res/images/pic04.jpg" />' alt="" /></a>
														<h3>Tellus sem imperdiet sed nunc</h3>
														<p>Nulla sem, vel iaculis arcu. Nulla vel lorem nisl, quis ultricies 
														tellus vulputate urna dolor est sagittis purus, in laoreet. Nunc imper diet 
														elit sed dolore lacus dolore. <a href="#">Learn more ...</a></p>
													</section>
												</div>
											</div>
										</article>
								
									<!-- Section 1 -->
										<section>
											<header>
												<h2>Magna rutrum sed consequat</h2>
											</header>
											<a href="#" class="image image-style1 align-left image-fit-mobileOnly"><img src='<c:url value = "/res/images/pic05.jpg" />' alt="" /></a>
											<h3>Feugiat veroeros consequat lorem facilis nullam</h3>
											<p>Donec tincidunt tincidunt velit, eu viverra augue lacinia sed. Maecenas a 
											ante in mauris aliquam tempus. Pellentesque euismod aliquam dolor. Praesent 
											congue, Curabitur lacinia, erat at venenatis aliquam, odio nisi ultricies ipsum, 
											in gravida augue mi eget libero.  Nulla id urna vel purus mattis volutpat id ac 
											ligula. Pellentesque sit amet feugiat donec tincidunt tincidunt viverra.</p>
											<a href="#" class="button">Continue Reading</a>
										</section>

									<!-- Section 2 -->
										<section>
											<header>
												<h2>Urna tempus lorem venenatis</h2>
											</header>
											<div class="5grid grid-spaced">
												<div class="row">
													<section class="6u">
														<a href="#" class="image image-style2 align-left"><img src='<c:url value = "/res/images/pic06.jpg" />' alt="" /></a>
														<h3>Dolore nisl vivamus consequat</h3>
														<p>Nulla sem, vel amet iaculis arcu. Nulla vel lorem imperdiet elit sed 
														dolore amet et blandit tempus. <a href="#">Learn more ...</a></p>
													</section>
													<section class="6u">
														<a href="#" class="image image-style2 align-left"><img src='<c:url value = "/res/images/pic07.jpg" />' alt="" /></a>
														<h3>Dolore nisl vivamus consequat</h3>
														<p>Nulla sem, vel amet iaculis arcu. Nulla vel lorem imperdiet elit sed 
														dolore amet et blandit tempus. <a href="#">Learn more ...</a></p>
													</section>
												</div>
												<div class="row">
													<section class="6u">
														<a href="#" class="image image-style2 align-left"><img src='<c:url value = "/res/images/pic08.jpg" />' alt="" /></a>
														<h3>Dolore nisl vivamus consequat</h3>
														<p>Nulla sem, vel amet iaculis arcu. Nulla vel lorem imperdiet elit sed 
														dolore amet et blandit tempus. <a href="#">Learn more ...</a></p>
													</section>
													<section class="6u">
														<a href="#" class="image image-style2 align-left"><img src='<c:url value = "/res/images/pic09.jpg" />' alt="" /></a>
														<h3>Dolore nisl vivamus consequat</h3>
														<p>Nulla sem, vel amet iaculis arcu. Nulla vel lorem imperdiet elit sed 
														dolore amet et blandit tempus. <a href="#">Learn more ...</a></p>
													</section>
												</div>
												<div class="row">
													<section class="6u">
														<a href="#" class="image image-style2 align-left"><img src='<c:url value = "/res/images/pic10.jpg" />' alt="" /></a>
														<h3>Dolore nisl vivamus consequat</h3>
														<p>Nulla sem, vel amet iaculis arcu. Nulla vel lorem imperdiet elit sed 
														dolore amet et blandit tempus. <a href="#">Learn more ...</a></p>
													</section>
													<section class="6u">
														<a href="#" class="image image-style2 align-left"><img src='<c:url value = "/res/images/pic11.jpg" />' alt="" /></a>
														<h3>Dolore nisl vivamus consequat</h3>
														<p>Nulla sem, vel amet iaculis arcu. Nulla vel lorem imperdiet elit sed 
														dolore amet et blandit tempus. <a href="#">Learn more ...</a></p>
													</section>
												</div>
											</div>
										</section>
								
								</div>

						</div>
						<div class="4u">
							<div class="right-sidebar">

								<!-- Sidebar -->
									<div id="sidebar">
									
										<!-- Section 1 -->
											<section class="blocks">
												<a href="#" class="one">
													<h2>Amet phasellus</h2>
													<p>Phasellus convallis sed nullam donec tempus augue tortor.</p>
												</a>
												<a href="#" class="two">
													<h2>Feugiat et purus</h2>
													<p>Phasellus convallis sed nullam donec tempus augue tortor.</p>
												</a>
												<a href="#" class="three">
													<h2>Nullam sagittis</h2>
													<p>Phasellus convallis sed nullam donec tempus augue tortor.</p>
												</a>
											</section>

										<!-- Section 2 -->
											<section>
												<header>
													<h2>Aliquam imperdiet venenatis</h2>
												</header>
												<ul class="style1 posts">
													<li>
														<a href="#" class="image align-left"><img src='<c:url value = "/res/images/pic12.jpg" />' alt="" /></a>
														<h3>Etiam nisl cursus dolore?</h3>
														<p>Magna feugiat lorem adipiscing ipsum.</p>
														<span class="date">6 hours ago</span>
													</li>
													<li>
														<a href="#" class="image align-left"><img src='<c:url value = "/res/images/pic13.jpg" />' alt="" /></a>
														<h3>Phasellus lorem ipsum amet</h3>
														<p>Lorem ipsum dolor amet consequat.</p>
														<span class="date">12 hours ago</span>
													</li>
													<li>
														<a href="#" class="image align-left"><img src='<c:url value = "/res/images/pic14.jpg" />' alt="" /></a>
														<h3>Tempus facilis lorem ipsum</h3>
														<p>Curabitur viverra felis vel vehicula.</p>
														<span class="date">18 hours ago</span>
													</li>
													<li>
														<a href="#" class="image align-left"><img src='<c:url value = "/res/images/pic15.jpg" />' alt="" /></a>
														<h3>Phasellus lorem ipsum amet</h3>
														<p>Lorem ipsum dolor amet consequat.</p>
														<span class="date">1 day ago</span>
													</li>
													<li>
														<a href="#" class="image align-left"><img src='<c:url value = "/res/images/pic16.jpg" />' alt="" /></a>
														<h3>Tempus facilis lorem ipsum</h3>
														<p>Curabitur viverra felis vel vehicula.</p>
														<span class="date">2 days ago</span>
													</li>
												</ul>
												<a href="#" class="button">More Articles</a>
											</section>

										<!-- Section 3 -->
											<section>
												<header>
													<h2>@untitled</h2>
												</header>
												<ul class="style1 tweets">
													<li>
														<p>Imperdiet elit sed dolore lacus venenatis rutrum. Vivamus vulputate 
														urna dolor est sagittis laoreet. <a href="#">t.co/xxxxxxxxxx</a></p>
														<span class="date">3 hours ago</span>
													</li>
													<li>
														<p><a href="#">@4templates</a> Urna dolor est sagittis laoreet veroeros blandit 
														sed etiam magna lorem ipsum venenatis etiam.</p>
														<span class="date">5 hours ago</span>
													</li>
													<li>
														<p>Laoreet veroeros blandit sed etiam magna lorem sed ipsum venenatis etiam 
														<a href="#">@4templates</a> magna tempus lorem.</p>
														<span class="date">12 hours ago</span>
													</li>
												</ul>
												<a href="#" class="button">More Tweets</a>
											</section>
									
									</div>
							
							</div>
						</div>
					</div>
				</div>
			</div>

<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />
		