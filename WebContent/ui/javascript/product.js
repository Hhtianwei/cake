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
	var top = ($(window).height() - $listProductDiv.height())/2;  
    var left = ($(window).width() - $listProductDiv.width())/2;  
    var scrollTop = $(document).scrollTop();  
    var scrollLeft = $(document).scrollLeft();  
    
	$("#showProducts").on("click",function(){
        
		$.ajax({
			url:url + "?currentPage=1",
			type:"get",
			success:function(data){
				var result = $.parseJSON(data);
				var results = result.results;
				var pagination  = result.pagination;
				var $tbody = $("#queryProductTbody");
				$tbody.empty();
				
				var bodyData = getBodyData(results);
				$tbody.append(bodyData);
				
				var paginationData = getPagination(pagination);
				
				$("#paginationDiv").html(paginationData);
				$listProductDiv.css( {'top' : top + scrollTop, left : left + scrollLeft } ).show();
			}
		});
	});
	
	$("body").on("click","a.nextPage,a.prePage",function(){
		console.info("call page data...");
		var page = $(this).attr("data");
		console.info("query data is:" + page);
		
		$.ajax({
			url:url + "?currentPage="+page,
			type:"get",
			success:function(data){
				var result = $.parseJSON(data);
				var results = result.results;
				var pagination  = result.pagination;
				var $tbody = $("#queryProductTbody");
				$tbody.empty();
				var bodyData = getBodyData(results);
				$tbody.append(bodyData);
				var paginationData = getPagination(pagination);
				$("#paginationDiv").html(paginationData);

				$listProductDiv.css( {'top' : top + scrollTop, left : left + scrollLeft } ).show();
			}
		});
		
		
	});
	
	$("#closeDiv").on("click",function(){
		$listProductDiv.hide();
	});
	
	$("body").on("click","a.selectParent",function(){
		var pid = $(this).attr("data");
		var pName = $(this).attr("pValue");
		$("input[name='pName']").val(pName);
		$("input[id='pid']").val(pid);
		$listProductDiv.hide();
	});
	
	//detail 
	$("span.sizePro").on("click",function(){
		$("span.sizePro").removeClass("currentSize");
		$(this).addClass("currentSize");
		var proId = $(this).attr("productId");
		
		$("span.pricePro").each(function(){
			var priceId = $(this).attr("productId");
			if(proId == priceId){
				$(this).addClass("currentPrice");
				$(this).removeClass("hidden");
			}else{
				$(this).removeClass("currentPrice");
				$(this).addClass("hidden");
			}
		});
		
		$("span.stockPro").each(function(){
			var priceId = $(this).attr("productId");
			if(proId == priceId){
				$(this).addClass("currentStock");
				$(this).removeClass("hidden");
			}else{
				$(this).removeClass("currentStock");
				$(this).addClass("hidden");
			}
		});
	});
	
});

function getBodyData(results){
	var trs = "";
	for(var i=0;i<results.length;i++){
		var tr = "<tr>";	
		var td1 = "<td>"+(i+1)+"</td>";
		var td2 = "<td>"+results[i].id+"</td>";
		var td3 = "<td><a href='javascript:void(0)' class='selectParent' pValue="+results[i].name+" data='"+results[i].id+"'>"+results[i].name+"</a></td>";
		var td4 = "<td>"+results[i].longName+"</td>";
		var td5 = "<td>"+results[i].shape+"</td>";
		var td6 = "<td>"+"</td>";
		var trEnd = tr + td1 + td2 + td3 + td4 + td5 + td6 + "</tr>";
		trs += trEnd;
	}
	return trs;
}

function getPagination(pagination){
	var totalPage = pagination.totalPages;
	var currentPage = pagination.currentPage;
	var pageData = "<br>";
	if(currentPage > 1){
		pageData += "<a class='prePage' data='"+(currentPage-1)+"'>上一页</a><br/>";
	}
	pageData += " "+currentPage+" ";
	if(currentPage<totalPage){
		pageData += "<a class='nextPage'  data='"+(currentPage+1)+"'>下一页</a><br/>";
	}
	
	return pageData; 
}