jQuery(function(){	

	jQuery("#sidebar_btn").toggle(function(){
//		alert('oi');
		jQuery("#main_sidebar").animate({
		    left: '0'
		});
		jQuery(this).animate({
		    left: '+=100px'
		}).html("<");
	}, function(){
		jQuery("#main_sidebar").animate({
		    left: '-100px'
		});
		jQuery(this).animate({
		    left: '-=100px'
		}).html(">");

	});


	jQuery('#sidebar_btn').bind('swiperight', function(){
//		alert('oi');
		jQuery("#main_sidebar").animate({
		    left: '0'
		});
		jQuery(this).animate({
		    left: '+=100px'
		}).html("<");
	});


});