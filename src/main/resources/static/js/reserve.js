document.addEventListener('DOMContentLoaded', function(){ 
	
	function ticketBox(){
		this.ticketBody = document.querySelector(".ticket_body");
		this.cntTicket();
	} 
	ticketBox.prototype = {
			cntTicket : function(){
				this.ticketBody.addEventListener("click", function(evt){
					var cnt = evt.target.closest("div").querySelector("input").value;
					var price = evt.target.closest("div.qty").querySelector("span.price").textContent;
					var ttlCnt = document.querySelector("#totalCount").textContent;
					var cntTmpl = document.querySelector("#cntTmpl").innerText;
					var priceTmpl = document.querySelector("#priceTmpl").innerText;
					var ttlTmpl = document.querySelector("#totalCountTmpl").innerText;
					var priceType;
					if(evt.target.getAttribute("title")== unescape("%uB354%uD558%uAE30")){ //더하기 일시 진행
						cnt++;			
						price = price*cnt;	
								
						priceType = evt.target.closest("div.qty").querySelector("div.qty_info_icon").querySelector("span").textContent.substring(0,1);
						
						this.templetingValues(evt,priceType,price,cnt,priceTmpl,cntTmpl,ttlCnt,ttlTmpl);		
						
						if(cnt == 1){
							evt.target.closest("div").querySelector("a.btn_plus_minus").className = "btn_plus_minus spr_book2 ico_minus3";
							evt.target.closest("div").querySelector("input").className = "count_control_input ";
						}
						
					} else if(evt.target.getAttribute("title")== unescape("%uBE7C%uAE30")){ //빼기일시 진행
						if(cnt<=0){
							return;
						}
						cnt--;
				
						price = price*cnt;			
												
						priceType = evt.target.closest("div.qty")
						.querySelector("div.qty_info_icon")
						.querySelector("span")
						.textContent.substring(0,1);
						
						this.templetingValues(evt,priceType,price,cnt,priceTmpl,cntTmpl,ttlCnt,ttlTmpl);
						
						
						if(cnt == 0){
							addClass(evt.target,"disabled"); //common.js에 구현된 함수
							addClass(evt.target.closest("div").querySelector("input"),"disabled"); //common.js에 구현된 함수
						}
						
					} else{
						return
					}
					
				}.bind(this));
				
			},
			
			templetingValues : function(evt,priceType,price,cnt,priceTmpl,cntTmpl,ttlCnt,ttlCountTmpl){
				document.querySelector("#"+priceType )
				.querySelector("input[name = price]").value = price;
				
				document.querySelector("#" + priceType)
				.querySelector("input[name = count]").value = cnt;
				
				evt.target.closest("div.qty")
				.querySelector("div.individual_price")
				.innerHTML = priceTmpl.replace("{price!}",price);
				
				evt.target.closest("div").querySelector("input").outerHTML = cntTmpl.replace("{val}",cnt);
				
				ttlCnt = 0*1;
				document.querySelectorAll("input[name = count]").forEach(function(item){
					ttlCnt += item.value*1;	
				});
			
				document.querySelector("#totalCount").outerHTML = ttlCountTmpl.replace("{totalCount}", ttlCnt);
				
			}
	}
	
	function validation(){
		this.ticketBody = document.querySelector(".ticket_body");
		this.inputs = document.querySelectorAll("input");
		this.validateForm();
		this.lastCheck();
	}
	
	validation.prototype = {
			activateBtn : function(name,email,tel,ttlCnt,agree){
				
				 if(name.value != "" && email.value.match(/[0-9a-zA-Z]+@[a-zA-Z]+[.][a-zA-Z]+/) != null && tel.value.match(/\d{2,3}-\d{4}-\d{4}/) != null && ttlCnt>0 && agree == true){
					 removeClass(document.querySelector(".bk_btn_wrap"),"disable"); // common.js에서 구현된 함수
					 document.querySelector(".name_msg").style.display = "none";
					 document.querySelector(".tel_msg").style.display = "none";
					 document.querySelector(".email_msg").style.display = "none";
					 
				} 
				 else {
					addClass(document.querySelector(".bk_btn_wrap"),"disable"); // common.js에서 구현된 함수
				}
			},
	
			validateForm : function(){		
				this.inputs.forEach(function(input){
					input.addEventListener("change", function(evt){
						var name = document.querySelector("#name");
						var email = document.querySelector("#email");
					    var tel = document.querySelector("#tel");
					    var ttlCnt = document.querySelector("#totalCount").textContent*1;
					    var agree = document.querySelector("#chk3").checked;
						
					    if(evt.target=== name){
						    if(name.value == ""){
								document.querySelector(".name_msg").style.display = "block";
							}
						 	else if(name.value != ""){
								document.querySelector(".name_msg").style.display = "none";
							}
					    }
					    if(evt.target===tel){
					    	if(tel.value.match(/\d{2,3}-\d{4}-\d{4}/) == null){
								document.querySelector(".tel_msg").style.display = "block";
							} 
							else if(tel.value.match(/\d{2,3}-\d{4}-\d{4}/) != null){
								document.querySelector(".tel_msg").style.display = "none";
							}
					    }
						
						if(evt.target===email){
							if(email.value.match(/[0-9a-zA-Z]+@[a-zA-Z]+[.][a-zA-Z]/) == null){
								document.querySelector(".email_msg").style.display = "block";
							} 
							else if(email.value.match(/[0-9a-zA-Z]+@[a-zA-Z]+[.][a-zA-Z]+/) != null){
								document.querySelector(".email_msg").style.display = "none";
							}
						}

					    this.activateBtn(name,email,tel,ttlCnt,agree);
						
					}.bind(this));
					
					
				}.bind(this));
				
				this.ticketBody.addEventListener("click" , function(evt){
					var name = document.querySelector("#name");
					var email = document.querySelector("#email");
				    var tel = document.querySelector("#tel");
				    var ttlCnt = document.querySelector("#totalCount").textContent*1;
				    var agree = document.querySelector("#chk3").checked;
				    
				    if(evt.target.tagName =="A"){
				    	this.activateBtn(name,email,tel,ttlCnt,agree);
					} else{
						return;
					}
				}.bind(this));
			},

			lastCheck : function(){
				document.querySelector("#submitBtn").addEventListener("click", function(evt){
					var name = document.querySelector("#name");
					var email = document.querySelector("#email");
				    var tel = document.querySelector("#tel");
				    var ttlCnt = document.querySelector("#totalCount").textContent*1;
				    var agree = document.querySelector("#chk3").checked;
				    

				    if(name.value != "" && email.value.match(/[0-9a-zA-Z]+@[a-zA-Z]+[.][a-zA-Z]/) != null && tel.value.match(/\d{2,3}-\d{4}-\d{4}/) != null && ttlCnt>0 && agree == true){
						document.querySelector("#submitBtn").setAttribute("form","form").submit();
					} else if(document.querySelector(".bk_btn_wrap").className == "bk_btn_wrap disable"){
						if(name.value == ""){
							alert(unescape("%uC608%uB9E4%uC790%uB97C%20%uC785%uB825%uD574%uC8FC%uC138%uC694."));
						}
						
						else if(tel.value.match(/\d{2,3}-\d{4}-\d{4}/) == null){
							alert(unescape("%27-%27%uB97C%20%uD3EC%uD568%uD574%uC11C%20%uC804%uD654%uBC88%uD638%uB97C%20%uC785%uB825%uD574%20%uC8FC%uC138%uC694.")
							);
						}
						
						else if(email.value.match(/[0-9a-zA-Z]+@[a-zA-Z]+[.][a-zA-Z]/) == null){
							alert(unescape("%uC774%uBA54%uC77C%20%uD615%uC2DD%uC740%20xxx@naver.xxx%20%uC73C%uB85C%20%uC785%uB825%uD574%uC8FC%uC138%uC694.")
							);
						}
						return;
					}
				});
				
			}
	}
	
	function spreadAgreement(){
		this.spreadBtns = document.querySelectorAll(".btn_agreement");
		this.spreadAgree();
	}
	
	spreadAgreement.prototype= {
			spreadAgree : function(){
				this.spreadBtns.forEach(function(btn){
					btn.addEventListener("click",function(evt){
						if(btn.closest("div").className =="agreement"){
							addClass(btn.closest("div"),"open"); //common.js에 구현한 함수
						} else {
							removeClass(btn.closest("div"),"open"); //common.js에 구현한 함수
						}
					});
					
				});
			}
	};
	
	
	
	var btnAgreement = new spreadAgreement();
	var tickets = new ticketBox();
	var validations = new validation(); 
});
