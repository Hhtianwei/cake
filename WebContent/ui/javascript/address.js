var loactionUrl = "/cakeOnline/my-account/getLocationInfo";

$(function(){
	var $province = $("#province");
	var $city = $("#city");
	var $area = $("#area");
	
	
	getLocationInfo(1,$province);
	
	$province.on("change",function(){
		initLocation($city);
		initLocation($area);
		var code = $(this).val();
		getLocationInfo(code,$city);
	});
	
	$city.on("change",function(){
		initLocation($area);
		var code = $(this).val();
		getLocationInfo(code,$area);
	});
	
});

function initLocation(element){
	var initOption = "<option>---请选择---</option>";
	element.empty();
	element.append(initOption);
}

function getLocationInfo(superCode,element){
	$.ajax({
		url:loactionUrl,
		type:"get",
		data:{"superCode":superCode},
		success:function(result){
			var provinceData = $.parseJSON(result);
			for(var i=0;i<provinceData.length;i++){
				var option = "<option value='"+provinceData[i].code+"'>"+provinceData[i].name+"</option>";
				element.append(option);
			}
		}
	});
}