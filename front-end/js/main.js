jQuery(function(){	

	jQuery("#sidebar_btn").toggle(function(){
//		alert('oi');
		jQuery("#main_sidebar").animate({
		    left: '0'
		});
		jQuery(this).animate({
		    left: '+=50%'
		}).html("<");
	}, function(){
		jQuery("#main_sidebar").animate({
		    left: '-50%'
		});
		jQuery(this).animate({
		    left: '-=50%'
		}).html(">");

	});


	jQuery('#sidebar_btn').bind('swiperight', function(){
//		alert('oi');
		jQuery("#main_sidebar").animate({
		    left: '0'
		});
		jQuery(this).animate({
		    left: '+=50%'
		}).html("<");
	});
	jQuery('#sidebar_btn').bind('swipeleft', function(){
//		alert('oi');
		jQuery("#main_sidebar").animate({
		    left: '-50%'
		});
		jQuery(this).animate({
		    left: '-=50%'
		}).html(">");
	});


});