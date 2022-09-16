document.addEventListener('DOMContentLoaded', function(){ 
	
	function cancelRsv(){
		this.cancelBtns = document.querySelectorAll(".booking_cancel button");
		this.pop = document.querySelector(".popup_booking_wrapper")
		this.popUp();
	} 
	cancelRsv.prototype = {
			 popUp : function(){
				this.cancelBtns.forEach(function(cancelBtn){
					cancelBtn.addEventListener("click",function(evt){
						if(evt.target.tagName =="BUTTON" || evt.target.tagName == "SPAN"){
							if(evt.target.closest("div").querySelector("span").textContent == unescape("%uCDE8%uC18C")){ //취소일시 진행					
								this.cancelTickets(evt);
								
							} else if (evt.target.closest("div").querySelector("span").textContent == unescape("%uC608%uB9E4%uC790%20%uB9AC%uBDF0%20%uB0A8%uAE30%uAE30")){ // 예매자 리뷰일시 진행
								var reservationInfoId = evt.target.closest("div").querySelector("input[name = reservationInfoId]").value * 1;
								var productId = evt.target.closest("div").querySelector("input[name = productId]").value * 1;
								var reservationTitle = evt.target.closest("article").querySelector("h4.tit").textContent;
								location.href = "reviewWrite?reservationInfoId=" + reservationInfoId+ "&reservationTitle=" + encodeURI(reservationTitle)+"&productId=" + productId;
							} else{
								return
							}
						}
					}.bind(this));
				}.bind(this));
				
			},
			
			cancelTickets : function(prevEvt){
				this.pop.querySelector(".pop_tit").querySelector("span").textContent = prevEvt.target.closest("article").querySelector("h4.tit").textContent;
				this.pop.querySelector(".pop_tit").querySelector("small.sm").textContent = prevEvt.target.closest("article").querySelector("li em.item_dsc").textContent
				this.pop.style.display ="";
				document.querySelector(".btn_gray").autofocus = true;
				this.pop.querySelector(".pop_bottom_btnarea").addEventListener("click",function(evt){
					if(evt.target.textContent == unescape("%uC608") ){ //'예' 일시 진행
						this.sendAjax(prevEvt);
						this.pop.style.display = "none";
						return true
					} else if(evt.target.textContent == unescape("%uC544%uB2C8%uC624") ){ //'아니오' 일시 진행
						this.pop.style.display = "none";
						return false
					} else {
						return
					}
					
				}.bind(this));	
			},

			sendAjax : function(evt){
				var reservationInfoId = evt.target.closest("div").querySelector("input").value * 1;
				var rsvedTicket = evt.target.closest("article");
				
				var oReq = new XMLHttpRequest();
				oReq.addEventListener("load", function() {
					evt.target.closest("div").querySelector("span").textContent = unescape("%uC608%uB9E4%uC790%20%uB9AC%uBDF0%20%uB0A8%uAE30%uAE30");
					evt.target.closest("article").removeChild(evt.target.closest("article").querySelector("a[title = "+unescape("%uACF5%uC720%uD558%uAE30")+"]")); // title = 공유하기
					document.querySelector(".card.used.cancel").appendChild(rsvedTicket);
					document.querySelector("#canceledCount").textContent++;
					document.querySelector("#rsvCount").textContent--;
				});    
				oReq.open("POST", "cancelRsv", true);
			    oReq.setRequestHeader('Content-type', 'application/x-www-form-urlencoded;');
			    oReq.send('reservationInfoId='+reservationInfoId);
			}			
	}
	
	var cancel = new cancelRsv(); 
	
});
