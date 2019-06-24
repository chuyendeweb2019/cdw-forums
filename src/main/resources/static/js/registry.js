
	  $( document ).ready(function() {
		  // SUBMIT FORM
		  var formData = {
	        		username : $("#username"),
	        		email :  $("#email"),
	        		password: $("#password"),
	        		confirmPassword: $("#confirmPassword")
	        }
		  Object.keys(formData).forEach( r =>
		  formData[r].keyup(function() { 
			  if( r === 'password'|| r === 'confirmPassword') {
				 // mat khau yeu cau tu 4-20 ki tu
				  if( formData[r].val().length > 3 && formData[r].val().length < 20) {
					  $('#'+ r + 'Error').html("");
					   if(formData['password'].val() != "" && formData['confirmPassword'].val() != "" ) {
						   if(formData['password'].val() === formData['confirmPassword'].val()) {
							   // console.log("hople")
						    	 $('#passwordError').html("");
						    	 $('#confirmPasswordError').html("");
						  } else {
							  $('#'+ r + 'Error').html("Mật khẩu không trùng khớp!")
							  
						  }
					   }
					 
				  } else {
					 // console.log(" lon hon 20 ki tu ")
					  $('#'+ r + 'Error').html("Mật khẩu không hợp lệ!")
				  }
				 
			  }
			  if(r === "email") {
				// console.log(formData[r].val());
				 if(validateEmail(formData[r].val()))  {
					 // send server
					 $.ajax({
					      type : "GET",
					      contentType : "application/json",
					      url : "/isEmail?email=" + formData[r].val(),
					      dataType : 'json',
					      success : function(result) {
					         if(result){
					        	 $('#'+ r + 'Error').html("Email đã tồn tại!")
					         } else {
					        	 $('#'+ r + 'Error').html("Email hợp lệ!")
					        	
					         }
					      },
					      error : function(e) {
					        alert("Error!")
					        console.log("ERROR: ", e);
					      }
					    });
				 } else {
					 $('#'+ r + 'Error').html("Email không hợp lệ!")
				 }
			  }
			  if(r === "username") {
					// console.log(formData[r].val());
					 if(formData[r].val().length !==0)  {
						 // send server
						 $.ajax({
						      type : "GET",
						      contentType : "application/json",
						      url : "/isUsername?username=" + formData[r].val(),
						      dataType : 'json',
						      success : function(result) {
						         if(result){
						        	 $('#'+ r + 'Error').html("Tên người dùng đã tồn tại!")
						         } else {
						        	 $('#'+ r + 'Error').html("Tên người dùng hợp lệ!")
						        	
						         }
						      },
						      error : function(e) {
						        alert("Error!")
						        console.log("ERROR: ", e);
						      }
						    });
					 } else {
						 $('#'+ r + 'Error').html("Tên người dùng đã tồn tại!")
					 }
				  }
			  }));
		  
		    $("#form").submit(function(event) {
		    // Prevent the form from submitting via the browser.
		    event.preventDefault();
		    xulydangky();
		  });
		    // dinh dang email
		    function validateEmail(email) {
		        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		        return re.test(String(email).toLowerCase());
		    }
		    
		    function xulydangky(){
		    	 // PREPARE FORM DATA
		        var formData = {
		        		username : $("#username").val(),
		        		email :  $("#email").val(),
		        		password: $("#password").val(),
		        		confirmPassword: $("#confirmPassword").val()	
		        }
		        $.ajax({
		            type : "POST",
		            contentType : "application/json",
		            url : "/ajax-registration",
		            data : JSON.stringify(formData),
		            dataType : 'json',
		            success : function(result) {
		              if(result){
		                  window.location.href = "/";
		              }else{
		            	  alert("Đăng kí thất bại!");
		              }
		              console.log(result);
		            },
		            error : function(e) {
		              alert("Error!")
		              console.log("ERROR: ", e);
		            }
		          });
		        
		        
		       
		    }
	  });

     