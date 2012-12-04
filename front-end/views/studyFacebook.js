	  	/*******************************************************************************************
		*file: studyFacebook.js
		*
		*Description: Make the login on Facebook with the JavaScript SDK.
		*
		*-------------------------------------------------------------------------------------------
	  	*Author: Joao Santos
	  	*Create Date: 03/12/2012
	  	********************************************************************************************/
	  	
	  	/*******************************************************************************************
	  	*History:
	  	*
	  	*03-12-2012: Create the script
	  	*04-12-2012: Create methods and add to GitHub
	  	********************************************************************************************/

	  	//Load the APP
	  	window.fbAsyncInit = function() {
	    	FB.init({
	      	appId      : '173457916129382', // App ID
	      	channelUrl : '//http://localhost/studymap/front-end/views/channel.html', // Channel File
	      	status     : true, // check login status
	      	cookie     : true, // enable cookies to allow the server to access the session
	      	xfbml      : true  // parse XFBML
	    	});

	    	//Get the Login Status 
	    	FB.getLoginStatus(function(response) {
				if (response.status === 'connected') {
			    	loadData();
			    	//Other actions if connected
			  	} 
			 });
	  	};
		
	  	//Example of one function to load user information and show in the view
	  	function loadData() {
    		window.alert('Welcome!  Fetching your information.... ');
    		FB.api('/me', function(response) {
	        
    		$("#userid").html(response.id);
    		$("#name").html(response.name);
    		$("#link").html(response.link);
    		$("#username").html(response.username);
    		
    		foto = 'https://graph.facebook.com/' + response.username + '/picture?type=large';
    		

			$("#profile_thumb").attr({
				src: foto,
				border: '0'
			});
    	});
		}

		//Login function (call in the view)
	  	function login() {
		    FB.login(function(response) {
		        if (response.authResponse) {
		            window.alert("Conectado");
		            loadData();
		        	//Actions when connected
		        }else{
		            window.alert("Não Conectado");
		        	//Actions when not connected
		        }
		    });
		}

	  	// Load the SDK Asynchronously
	  	(function(d){
	     	var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
	     	if (d.getElementById(id)) {return;}
	     		js = d.createElement('script'); js.id = id; js.async = true;
	     		js.src = "//connect.facebook.net/en_US/all.js";
	     		ref.parentNode.insertBefore(js, ref);
	   	}(document));

