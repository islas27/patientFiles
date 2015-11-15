	var changeState = function(id){
	    if($('#'+id).is(":visible")){
	        $('#'+id).hide(500);
	    }else
	    {
	        $('#'+id).show(500);
	    }
	};