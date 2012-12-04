	  	
	  	// Additional JS functions here
	  	window.fbAsyncInit = function() {
	    	FB.init({
	      	appId      : '173457916129382', // App ID
	      	channelUrl : '//www.belanus.com.br/sandbox/channel.html', // Channel File
	      	status     : true, // check login status
	      	cookie     : true, // enable cookies to allow the server to access the session
	      	xfbml      : true  // parse XFBML
	    	});

	    	// Additional init code here
	    	FB.getLoginStatus(function(response) {
				if (response.status === 'connected') {
			    	window.alert("Conectado");
			    	testAPI();
			  	} 
			 });
	  	};
		

	  	function testAPI() {
    		window.alert('Welcome!  Fetching your information.... ');
    		FB.api('/me', function(response) {
	        
    		$("#userid").html(response.id);
    		$("#name").html(response.name);
    		$("#link").html(response.link);
    		$("#username").html(response.username);
    		
    		foto = 'https://graph.facebook.com/' + response.username + '/picture?type=large';
    		

			$("img").attr({
				src: foto,
				border: '0'
			});
    	});
		}

	  	function login() {
		    FB.login(function(response) {
		        if (response.authResponse) {
		            window.alert("Conectado");
		            window.alert("Não Conectado");
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

