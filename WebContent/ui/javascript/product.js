$(function(){
	var $addType = $("input[name='addType']");
	
	$addType.on("click",function(){
		var value = $(this).val();
		
		var $subAttrDiv = $("#subAttrDiv");
		var $superAttrDiv = $("#superAttrDiv");
		
		if(value == 'parent'){
			if(!$subAttrDiv.hasClass("hidden")){
				$subAttrDiv.addClass("hidden");
			}
			$superAttrDiv.removeClass("hidden");
		}
		
		if(value == 'sub'){
			if($subAttrDiv.hasClass("hidden")){
				$subAttrDiv.removeClass("hidden");
			}
			$superAttrDiv.addClass("hidden");
			$("#flag").val("true");
		}
		
	});
	
	var $listProductDiv = $("#listProductDiv");
	var url = "/cakeOnline/admin/queryAllParent";
	$("#showProducts").on("click",function(){
		var top = ($(window).height() - $listProductDiv.height())/2;  
        var left = ($(window).width() - $listProductDiv.width())/2;  
        var scrollTop = $(document).scrollTop();  
        var scrollLeft = $(document).scrollLeft();  
        
		$.ajax({
			url:url + "?currentPage=1",
			type:"get",
			success:function(data){
				var result = $.parseJSON(data);
				var results = result.results;
				var pagination  = result.pagination;
				var $tbody = $("#queryProductTbody");
				$tbody.empty();
				for(var i=0;i<results.length;i++){
					var tr = "<tr>";	
					var td1 = "<td>"+(i+1)+"</td>";
					var td2 = "<td>"+results[i].id+"</td>";
					var td3 = "<td>"+results[i].name+"</td>";
					var td4 = "<td>"+results[i].longName+"</td>";
					var td5 = "<td>"+results[i].shape+"</td>";
					var td6 = "<td>"+"</td>";
					var trEnd = tr + td1 + td2 + td3 + td4 + td5 + td6 + "</tr>";
					$tbody.append(trEnd);
				}
				
				var totalPage = pagination.totalPages;
				if(totalPage > 1){
					var page = "<br/>1 <a href='javascript:void(0)' id='nextPage' data='2'>下一页</a><br/>";
				}else{
					var page = "<br>" + 1+"<br/>";
				}
				$("#productTab").after(page);
				$listProductDiv.css( {'top' : top + scrollTop, left : left + scrollLeft } ).show();
			}
		});
	});
	
	$("#nextPage").on("click",function(){
		
	});
	
	$("#closeDiv").on("click",function(){
		$listProductDiv.hide();
	});
});