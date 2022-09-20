document.addEventListener('DOMContentLoaded', function(){ 
	


	
	
	var tabMenu = document.querySelector("#tabMenu");
	
	function tabUi(){
		this.tabMenu = document.querySelector("#tabMenu");
		this.moreBtn = document.querySelector(".btn");
		this.tabClick();
		this.moreInfo();
	}
	
	tabUi.prototype = {
			tabClick : function(){
				this.tabMenu.addEventListener("click", function(evt){ 
					if(evt.target.tagName=="ul"){			
					} else{
						var clickedTab = evt.target.closest("li");
						var category = clickedTab.getAttribute("data-category");
						var anchorActive = document.querySelector(".active");
						var pageCount = document.querySelector("#pageCount").value;
						var categroyNum = document.querySelector("#categoryNum").value;
						var lst_left = document.querySelector("#lst_left");
						var lst_right= document.querySelector("#lst_right");
						
						pageCount = 1;
						categoryNum = category;					
						removeClass(anchorActive,"active");
						addClass(clickedTab.querySelector(".anchor"),"active");
						lst_left.innerHTML = "";
						lst_right.innerHTML = "";
						
						this.sendAjax(category,0);
						
						}}.bind(this))},
		
			moreInfo :function(){
				this.moreBtn.addEventListener("click", function(evt){
					var prdCount = Number(document.querySelector("#pageCount").value);
					var category = document.querySelector("#categoryNum").value;
					document.querySelector("#pageCount").value = prdCount+1;
					this.sendAjax(category,prdCount);	
			}.bind(this))},
			
			sendAjax : function(category ,start){
				var data;
				var oReq = new XMLHttpRequest();
				oReq.addEventListener("load", function() {	   
			    	console.log("success");	   
			    	data = JSON.parse(oReq.responseText);
			    	this.showList(data);
				}.bind(this));    
				oReq.open("POST", "booking/tab", true);
			    oReq.setRequestHeader('Content-type', 'application/x-www-form-urlencoded;');
//			    oReq.setRequestHeader('Content-type', 'application/json;');
			    oReq.send('categoryId='+category+"&start="+start*4);
			},
			
			showList : function(data){
				var mainTmpl = document.querySelector("#itemList").innerHTML;
				
				var bindTemplate = Handlebars.compile(mainTmpl);  //bindTemplate은 메서드입니다.
				var length = data.list.length;				
				var start = document.querySelector("#pageCount").value * 4;
				document.querySelector(".pink").innerText = data.count+"개";
				if(start >= data.count) {
					this.moreBtn.style.display = 'none';
				}
				else {
					this.moreBtn.style.display = "";
				}
				
				for(var i=0; i<length; i++){
					var li = document.createElement('li');
					li.classList.add('item');
					li.innerHTML = bindTemplate(data.list[i]);
					
					if( i % 2 == 0){
					document.querySelector("#lst_left").appendChild(li);
					} else if (i % 2 == 1) {
						document.querySelector("#lst_right").appendChild(li);
					}
				}
			}
	};
	

	function promoList (){
		this.promoCount = 0;
		this.promoImg = document.querySelector(".visual_img")
		this.firstSliding();
		this.sliding();
		this.linkInfinite();
	}
	
	promoList.prototype = {
			 //딜레이없이 바로 첫번째 프로모션 슬라이드
			firstSliding : function (){
				
				this.promoImg.style.transition = 'all 1s';
				this.promoImg.style.left =  - 414*(this.promoCount)+"px";
				this.promoCount++;
			},
			
			// 첫번쨰 슬라이드 이후 계속 자동적으로 슬라이드 해줌
			sliding : function (){
				setTimeout(function(){
					
					if(this.promoCount < 11){
						this.promoImg.style.left =  - 414*(this.promoCount)+"px";
					}
					
					else if (this.promoCount === 11) {
						this.promoImg.style.left =  - 414*(this.promoCount)+"px";
						setTimeout(function(){
							this.promoImg.style.transition = '0s';
							this.promoCount=0;
							this.promoImg.style.left =  - 414*(this.promoCount)+"px";
							setTimeout(function(){
								this.firstSliding();
							}.bind(this),1001)
						}.bind(this) ,1001);
					}

					this.promoImg.style.transition = 'all 1s';
					this.promoCount++;
					this.sliding(); 
				}.bind(this) , 2000);
			},
			
			linkInfinite : function(){
				var firstImgCloned = this.promoImg.firstElementChild.cloneNode(true);
				this.promoImg.appendChild(firstImgCloned);
			}
	};

	


	var slide = new promoList();
	var tab = new tabUi();


	
	
	
	
	
	
	
	
	
	

});

	


	

 