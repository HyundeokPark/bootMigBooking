document.addEventListener('DOMContentLoaded', function(){ 	
	function top(){
		this.topBtn = document.querySelector(".lnk_top");
		this.moveTop();
	}
	
	top.prototype = {
			scrollUp : function (){
				var scrollUpDelay = 1;
				var scrollUpSpeed = 30;
				if(document.documentElement.scrollTop<1)
				{
					return;
				}
				document.documentElement.scrollTop=document.documentElement.scrollTop-scrollUpSpeed;
				setTimeout(this.scrollUp(),scrollUpDelay);
			},
			moveTop : function (){
				this.topBtn.addEventListener("click", function(evt){
					this.scrollUp();
				}.bind(this));
			}
	}
	
	function back(){
		this.btnBack = document.querySelector("a.btn_back");
		this.goBack();	
	}
	
	back.prototype = {
		goBack : function(){
			this.btnBack.addEventListener("click",function(evt){
				window.history.back();
			}.bind(this));
			 
		}
	}
	
	
	
	addClass = function(element, classString) {
        element.className = element
            .className
            .split(' ')
            .filter(function (name) { return name !== classString; })
            .concat(classString)
            .join(' ');
	}
	
	removeClass = function(element, classString) {
        element.className = element
            .className
            .split(' ')
            .filter(function (name) { return name !== classString; })
            .join(' ');
	}
	
	
	
	

	var goTop = new top();
	var goBack = new back();
});

	


	

 