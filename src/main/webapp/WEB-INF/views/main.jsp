<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>


<html lang="ko">

	<head>
	    <meta charset="utf-8">
	    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
	    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
	    <title>네이버 예약</title>
	    <link href="./css/style.css" rel="stylesheet">
	</head>

	<body>
	    <div id="container">
	        <div class="header">
	            <header class="header_tit">
	                <h1 class="logo">
	                    <a href="https://m.naver.com/" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
	                    <a href="bookinglogin" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
	                </h1>
	                <a  href="bookinglogin" class="btn_my"> <span class="viewReservation" title="예약확인">${email }</span> </a>
<%-- 	                <a href="myreservation?resrv_email = '${email}'" class="btn_my"> <span class="viewReservation" title="예약확인">${email}</span> </a> --%>
	            </header>
	        </div>
	        <div class="event" id = "evntDiv">
	            <div class="section_visual">
	                <div class="group_visual">
	                    <div class="container_visual">
	                        <div class="prev_e" style="display:none;">
	                            <div class="prev_inn">
	                                <a href="#" class="btn_pre_e" title="이전"> <i class="spr_book_event spr_event_pre">이전</i> </a>
	                            </div>
	                        </div>
	                        <div class="nxt_e" style="display:none;">s
	                            <div class="nxt_inn">
	                                <a href="#" class="btn_nxt_e" title="다음"> <i class="spr_book_event spr_event_nxt">다음</i> </a>
	                            </div>
	                        </div>
	                        <div>
	                            <div class="container_visual">
	                                <!-- 슬라이딩기능: 이미지 (type = 'th')를 순차적으로 노출 -->
	                                <ul class="visual_img">
		                            	<c:set var = "count" value = "0"/> 
		                            	<c:forEach items = "${list}" var = "promotion">
		                            	
		                            	<li id="listPromo${count}" >
		            							<p>${count}</p>
												<img src = "http://localhost:8080/booking/displayimgbyId?name=${promotion.getImgId()}" alt ="프로모션 이미지 입니다." >
<%-- 									            <img src = "http://localhost:8080/booking/displayimg?name=${promotion.getProductImageUrl()}" alt ="프로모션 이미지 입니다." > --%>
		                                	                    	
		                                </li>
		                                <c:set var = "count" value = "${count+1}" />
		                                </c:forEach>
	                                </ul>
	                            </div>
	                            <span class="nxt_fix" style="display:none;"></span>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="section_event_tab">
	                <ul class="event_tab_lst tab_lst_min" id = "tabMenu">
	                    <li class="item" data-category="0" name= "전체리스트">
	                        <a class="anchor active"> <span>전체리스트</span> </a>
	                    </li>
	                    <li class="item" data-category="1">
	                        <a  class="anchor"> <span>전시</span> </a>
	                    </li>
	                    <li class="item" data-category="2">
	                        <a  class="anchor"> <span>뮤지컬</span> </a>
	                    </li>
	                    <li class="item" data-category="3">
	                        <a " class="anchor"> <span>콘서트</span> </a>
	                    </li>
	                    <li class="item" data-category="4">
	                        <a  class="anchor"> <span>클래식</span> </a>
	                    </li>
	                    <li class="item" data-category="5">
	                        <a class="anchor"> <span>연극</span> </a>
	                    </li>
	                    <!-- li class="item" data-category="7">
	                        <a class="anchor"> <span>클래스</span> </a>
	                    </li>
	                    <li class="item" data-category="8">
	                        <a class="anchor"> <span>체험</span> </a>
	                    </li>
	                    <li class="item" data-category="9">
	                        <a class="anchor last"> <span>키즈</span> </a>
	                    </li -->
	                </ul>
           		</div>
           
            	<div class="section_event_lst">
                	<p class="event_lst_txt">바로 예매 가능한 행사가 <span class="pink">${totalcount}개</span> 있습니다</p>
	                <div class="wrap_event_box">
	                    <!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->
	                    
	                    <ul class="lst_event_box" id="lst_left">
	                    	<c:set var = "mainCount" value = "0"/>
		                    <c:forEach items = "${mainList}" var = "mainItem" begin="0" end="3" step="1">
		                        <c:if test= "${mainCount % 2 eq 0}">
			                        
			                        <li class="item" id = "main${mainCount}" >
			                            <a href="detail?displayInfoId=${mainItem.getDisplayInfoId()}" class="item_book">
<%-- 			                                <div class="item_preview"> <img alt="${mainItem.getTitle()}" class="img_thumb" src="http://localhost:8080/booking/displayimg?name=${mainItem.getPath()}"> --%>
			                                <div class="item_preview"> <img alt="${mainItem.getTitle()}" class="img_thumb" src="http://localhost:8080/booking/displayimgbyId?name=${mainItem.getImgId()}">
			                                                                    <span class="img_border"></span> </div>
			                                <div class="event_txt">
			                                    <h4 class="event_txt_tit"> <span>${mainItem.getTitle()}</span> <small class="sm">${mainItem.getAddr()}</small> </h4>
			                                    <p class="event_txt_dsc"> ${mainItem.getContent()}
			                                    </p>
			                                </div>
			                            </a>
			                            <input type="hidden" name="displayInfoIdL" id="displayInfoIdL" value="${mainItem.getDisplayInfoId()}">
		                        	</li>                        	
	                        	</c:if>
	                        	<c:set var = "mainCount" value ="${mainCount+1}"/>
	                        </c:forEach>
							
	                    </ul>
	                    <ul class="lst_event_box" id="lst_right">
	                        <c:set var = "mainCount" value = "0"/>
	                        
		                    <c:forEach items = "${mainList}" var = "mainItem"  begin="0" end="3" step="1">
		                        <c:if test= "${mainCount % 2 eq 1}">
		                       		 <li class="item" id = "main${mainCount}" >
			                            <a href="detail?displayInfoId=${mainItem.getDisplayInfoId()}" class="item_book">
<%-- 			                                <div class="item_preview"> <img alt="${mainItem.getTitle()}" class="img_thumb" src="http://localhost:8080/booking/displayimg?name=${mainItem.getPath()}"> --%>
												<div class="item_preview"> <img alt="${mainItem.getTitle()}" class="img_thumb" src="http://localhost:8080/booking/displayimgbyId?name=${mainItem.getImgId()}">				
			                                                                    <span class="img_border"></span> </div>
			                                <div class="event_txt">
			                                    <h4 class="event_txt_tit"> <span>${mainItem.getTitle()}</span> <small class="sm">${mainItem.getAddr()}</small> </h4>
			                                    <p class="event_txt_dsc"> ${mainItem.getContent()}
			                                    </p>
			                                </div>
			                            </a>
			                            <input type="hidden" name="displayInfoIdR" id="displayInfoIdR" value="${mainItem.getDisplayInfoId()}">
		                        	</li>      
		                        </c:if>
		                        <c:set var = "mainCount" value ="${mainCount+1}"/>
	                        </c:forEach>
	                    </ul>
	                    <!-- 더보기 -->
	                    
	                    
	                    <div class="more">
	                        <button class="btn"><span>더보기</span></button>
	                         <input type="hidden" name="pageCount" id="pageCount" value="1">
	                         <input type="hidden" name="categoryNum" id="categoryNum" value="0">
	                         
	                    </div>
	                </div>
            	</div>
        	</div>
    	</div>
	    <footer>
	        <div class="gototop">
	            <a class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
	        </div>
	        <div class="footer">
	            <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
	            <span sclass="copyright">© NAVER Corp.</span>
	        </div>
	    </footer>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.1/handlebars.min.js" integrity="sha256-nGCNYQ+aj0GQsuayEJTyjqObkENoXk6HBIwW+fd8wH0=" crossorigin="anonymous"></script>
	 	<script src="./js/script.js"></script>
	 	<script src="./js/common.js"></script>
	</body>

    <script type="rv-template" id="promotionItem">
        <a href="#"> 
            <div class="event_txt">
				<img src = {path}>
                 <h4 class="event_txt_tit"></h4>
                <p class="event_txt_adr"></p>
                <p class="event_txt_dsc"></p>
            </div>
        </a>
    </script>
    
    
    
    
     <script type="rv-template" id="itemList">
       
	   		<a href="detail?displayInfoId={{displayInfoId}}" class="item_book">
		   	 <div class="item_preview"> <img alt =  {{title}}  class="img_thumb" src= http://localhost:8080/booking/displayimgbyId?name={{imgId}}>
		   			<span class="img_border"></span> </div>
		   	 <div class="event_txt">
		     	    <h4 class="event_txt_tit"> <span> {{title}} </span> <small class="sm">{{addr}}</small> </h4>
		        	 <p class="event_txt_dsc"> {{content}}
		         	</p>
		     </div>
 			<input type="hidden" name="displayInfoId" id="displayInfoId{{displayInfoId}}" value={{displayInfoId}}>
		    </a>
	      
    </script>
    
       
    <script type="rv-template" id="itemList2">
       
	   		<a href="detail" class="item_book">
		   	 <div class="item_preview"> <img alt = img_alt class="img_thumb" src= path>
		   			<span class="img_border"></span> </div>
		   	 <div class="event_txt">
		     	    <h4 class="event_txt_tit"> <span> item_title </span> <small class="sm">addr</small> </h4>
		        	 <p class="event_txt_dsc"> content
		         	</p>
		     </div>
		    </a>
	      
    </script>
	
</html>
    