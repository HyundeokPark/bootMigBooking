document.addEventListener('DOMContentLoaded', function(){ 
	
	function topImgs() {
		this.nxt_inn = document.querySelector(".nxt_inn");
		this.prev_inn = document.querySelector(".prev_inn");
		this.page = document.querySelector(".figure_pagination .num").innerText;
		this.init();  //상단 이미지 영역 초기화
		this.pagination(); // 상단이미지 영역 이전/다음 버튼에 대한 함수등록
	}
	
	topImgs.prototype = {
			init : function(){ //transition의 첫 동작도 적용 되기 위해 프로퍼티 0px설정 및 ajax를 통한 이미지갯수에 따른 초기화 작업
				document.querySelector(".visual_img.detail_swipe").style.left = 0+"px"
				this.sendAjax();
			},
			
			sendAjax : function(){
				var data;
				var displayInfoId = Number(document.querySelector("#displayInfoId").value);
				var oReq = new XMLHttpRequest();
				oReq.addEventListener("load", function() {	   
			    	console.log("success");	   
			    	data = JSON.parse(oReq.responseText);
			    	var size = data.productImg.length+"";
			    	if(size<=2){	
			    		return
			    	} else{
			    		this.prev_inn.className ="prev_inn";
			    		this.nxt_inn.className="nxt_inn";
			    		document.querySelector(".num.off span").innerText = 2;	
			    		var li = document.createElement('li');
			    		li.innerHTML = document.querySelector(".visual_img.detail_swipe").innerHTML;
//			    		li.querySelector("img").setAttribute("src", "http://localhost:8080/booking/displayimg?name="+data.productImg[2].saveFileName);
			    		li.querySelector("img").setAttribute("src", "http://localhost:8080/booking/displayimgbyId?name="+data.productImg[2].fileInfoId);
			    		document.querySelector(".visual_img.detail_swipe").appendChild(li);		    		
			    				    		
			    	}		   		
				}.bind(this));    
				oReq.open("POST", "detail/topImg", true);
			    oReq.setRequestHeader('Content-type', 'application/x-www-form-urlencoded;');
			    oReq.send('displayInfoId='+displayInfoId);
			},
			
			pagination : function(){ //이전/다음 버튼에 따른 함수 
	
				this.nxt_inn.addEventListener("click", function(evt){
					
					if(this.page==2){
						this.page = 1;
						document.querySelector(".visual_img.detail_swipe").style.left = 0+"px";
						
					} else{
						document.querySelector(".visual_img.detail_swipe").style.left = -414+"px";
						this.page = 2;
					}
					document.querySelector(".figure_pagination .num").innerText = this.page;
				}.bind(this));
				this.prev_inn.addEventListener("click", function(evt){
					if(this.page==1){
						this.page = 2;
						document.querySelector(".visual_img.detail_swipe").style.left = -414+"px";
					} else{
						document.querySelector(".visual_img.detail_swipe").style.left = 0+"px";
						this.page = 1;
					}
					document.querySelector(".figure_pagination .num").innerText = this.page;					
				}.bind(this));			
			},
			
			linkIninite : function(){
				
			}
	};
	
	
	
	function spreadContent(){
		this.spreadBtn = document.querySelector("#bk_more");
		this.closeBtn = document.querySelector("#bk_more_close");
		this.storeDetail = document.querySelector("#storeDetailes");
		
		this.spread();
		this.close();
	}
	
	spreadContent.prototype = {
			 // 펼치기 함수등록
			spread : function(){
				this.spreadBtn.addEventListener("click", function(evt){
				this.storeDetail.classList.remove('close3');
				this.spreadBtn.style.display="none";
				this.closeBtn.style.display = "";
				}.bind(this));
			},
			
			// 접기 함수 등록
			close : function(){
				this.closeBtn.addEventListener("click", function(evt){
				this.storeDetail.classList.add('close3');
				this.closeBtn.style.display="none";
				this.spreadBtn.style.display = "";
				}.bind(this));
			}
	};

	function btmTab(){
		this.tabMenu = document.querySelector(".info_tab_lst");
		this.tabClick();
	}
	
	btmTab.prototype = {
			sendAjax : function(){
				var template = document.querySelector("#tmplBtmTab").innerText;
				var data;
				var displayInfoId = Number(document.querySelector("#displayInfoId").value);
				var oReq = new XMLHttpRequest();
				oReq.addEventListener("load", function() {	   
			    	console.log("success");	   
			    	data = JSON.parse(this.responseText);
			   		var bindTemplate = Handlebars.compile(template); 
			   		 
			   		var resultHTML = bindTemplate(data.displayInfo[0]);
			   		document.querySelector(".detail_area_wrap").innerHTML = resultHTML;
				});    
				oReq.open("POST", "detail/btmTab", true);
			    oReq.setRequestHeader('Content-type', 'application/x-www-form-urlencoded;');
			    oReq.send('displayInfoId='+displayInfoId+"&tabName="+document.querySelector(".anchor.active").text);
			},	
			tabClick : function(){
				this.tabMenu.addEventListener("click", function(evt){
					if(evt.target.tageName == "ul"){
						return
					} else{
						document.querySelector(".anchor.active").className="anchor";
						evt.target.closest("li").querySelector(".anchor").className +=" active";
						this.sendAjax().bind(this);
					}
				}.bind(this));
			}	
	}
	
	function moveRsv(){
		this.rsvBtn = document.querySelector(".bk_btn");
		this.reserveTicket();
	}
	
	moveRsv.prototype = {
			reserveTicket : function(){
				this.rsvBtn.addEventListener("click",function(evt){
					window.location.href = this.rsvBtn.querySelector("a").href;
				}.bind(this));
			}
	}

	var spread = new spreadContent();
	var btmTabUi = new btmTab();
	var topImg = new topImgs();
	var rsv = new moveRsv();
});

	


	
 