document.addEventListener('DOMContentLoaded', function(){ 
		function rateScore(){
			this.rateBar = document.querySelector("div.rating");
			this.clickStar();
		}
		
		rateScore.prototype = {
				clickStar : function(){
					this.rateBar.addEventListener("click",function(evt){
						if(evt.target.tagName=="INPUT"){
							var score = evt.target.value*1 + 1;
							var point = document.querySelector(".star_rank");
							var ratingData = document.querySelector("input[name=rating]")
							point.textContent = score-1;
							ratingData.value = score-1;
							if(evt.target.checked){
								for(i=2;i<score; i++){
									addClass(document.querySelector("input[name= rating"+i+"]"),"checked");
								}
								for(i=score+1; i<7; i++){
									removeClass(document.querySelector("input[name= rating"+i+"]"),"checked");
									document.querySelector("input[name= rating"+i+"]").checked = false;
								}
								point.className = "star_rank";
							}
							else {
								for(i=2;i<7; i++){								
									removeClass(document.querySelector("input[name= rating"+i+"]"),"checked");
									document.querySelector("input[name= rating"+i+"]").checked = false;
									addClass(point,"gray_star");
									point.textContent = 0;
									ratingData.value = 0;
								}
							}
							
						} else {
							return
						}
						
					}.bind(this));
				}
		};
		
		
		function commentArea(){
			this.reviewWriteInfo = document.querySelector(".review_write_info");
			this.contents = document.querySelector(".review_write_info").querySelectorAll("span");
			this.textArea = document.querySelector("textArea");
			this.inputText();
			this.showDefault();
			this.showChar();
		}
		
		commentArea.prototype = {
				inputText : function(){
					this.reviewWriteInfo.addEventListener("click",function(evt){
						this.reviewWriteInfo.style.display="none";
						this.textArea.focus();
					}.bind(this))
				},
				showDefault : function(){
					this.textArea.addEventListener("blur", function(evt){
						var length  = evt.target.value.length+""; 
						var content = document.querySelector("textArea").value;
						if(length==0){
							this.contents.forEach(function(items){
								items.style.display="";
							}.bind(this));
							this.reviewWriteInfo.style.display="";
						} else if (length.match("^[5-9]{1}$|" + // 5 ~ 9 
												"^[1-9]{1}[0-9]{1}$|" + // 10 ~ 99
												"^[1-3]{1}[0-9]{1}[0-9]{1}$|" + //100 ~ 399
												"^[4]{1}[0]{1}[0]{1}$") == null){ //400
//							alert("현재 글자수 : "+length+ "입니다. \n최소5자 ~ 최대400자까지만 작성하실수 있습니다.");
							document.querySelector("textArea").value = document.querySelector("textArea").value.substring(0,400); 
							
							document.querySelector(".guide_review").querySelector("span").textContent = document.querySelector("textArea").value.length;
							document.querySelector(".comment_msg").style.display="";
						}  else {
							document.querySelector(".comment_msg").style.display="none";
							document.querySelector("textArea").value = document.querySelector("textArea").value.substring(0,400); 
							document.querySelector(".guide_review").querySelector("span").textContent = document.querySelector("textArea").value.length;
						}
						
					}.bind(this),true)
				},
				showChar : function(){
					this.textArea.addEventListener("keyup", function(evt){
						document.querySelector(".guide_review").querySelector("span").textContent = evt.target.value.length;
					})
				}
		};
		
		function uploadFile() {
			this.fileUploader = document.querySelector("#reviewImageFileOpenInput");
			this.inspectFile();
			this.submitBtn = document.querySelector(".bk_btn");
			this.validateComment();
		}
		
		uploadFile.prototype = {
				inspectFile : function(){
					this.fileUploader.addEventListener("change", function(evt){
						var image = evt.target.files[0];
						if(!this.validImageType(image)) { 
							alert("png/jpg 파일만 등록할수있습니다.");
							console.warn("invalide image file type");
							evt.target.value=null;
							return;
						}
						console.warn("invalide image file type");
						var thumb = document.querySelector(".lst_thumb").querySelector("img");
			            thumb.src = window.URL.createObjectURL(image);
			            thumb.closest("li").style.display = "";
					}.bind(this));
				},
				validImageType : function(image){
					var result = ([ 'image/jpeg',
						  			'image/png',
						  			'image/jpg' ].indexOf(image.type) > -1);
					return result;
				},
				
				validateComment : function(){
					this.submitBtn.addEventListener("click",function(evt){
						var length = document.querySelector("textArea").textLength+"";
						var image = document.querySelector("#reviewImageFileOpenInput").files[0];
						if(length.match("^[5-9]{1}$|" +
									    "^[1-9]{1}[0-9]{1}$|" +
									    "^[1-3]{1}[0-9]{1}[0-9]{1}$|" +
									    "^[4]{1}[0]{1}[0]{1}$") == null){
							alert("현재 글자수 : "+length+ "입니다. \n 댓글내용은 무조건 기입하셔야 하며,최소5자 ~ 최대400자까지만 작성하실수 있습니다.");
						} 
							else {				
							document.querySelector("form").submit();
						}
					}.bind(this));
				}	
			   
		};
		
		
		
		
		var file = new uploadFile();
		var comment = new commentArea();
		var rate = new rateScore();
	});
