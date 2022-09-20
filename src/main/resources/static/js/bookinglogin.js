document.addEventListener('DOMContentLoaded', function(){ 
	
	function validation(){
		this.submitBtn = document.querySelector("#sbmtBtn");
		this.lastCheck();
	}
	
	
	validation.prototype = {

			lastCheck : function(){
				this.submitBtn.addEventListener("click", function(evt){
					var email = document.querySelector("input[name=resrv_email]");

				    if(email.value.match(/[0-9a-zA-Z]+@[a-zA-Z]+[.][a-zA-Z]+/) != null){
				    	document.querySelector("form").submit();
					} else{
						alert(unescape("%uC774%uBA54%uC77C%20%uD615%uC2DD%uC740%20xxx@naver.xxx%20%uC73C%uB85C%20%uC785%uB825%uD574%uC8FC%uC138%uC694.")
						);
						return;
					}
				}.bind(this));
				
			}
	};
	
	var validations = new validation(); 
});
