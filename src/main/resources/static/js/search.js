$(document).ready(function() {
	
	$("#searchWord").keyup(function(event) {
		if($("#searchWord").val() === ""){
			$("tr").remove();

		} else {
		
		event.preventDefault();
		$("tr").remove();

		fire_ajax_submit();
		}

	});
	

	$("#searchIn").change(function(event) {

		event.preventDefault();
		$("tr").remove();

		fire_ajax_submit();

	});

});

function fire_ajax_submit() {

	var search = {
		searchWord : $("#searchWord").val(),
		searchIn : $('#searchIn').val()
	// searchIn : $('#searchIn').find(":selected").text()
	}
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/api/timkiem",
		data : JSON.stringify(search),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(result) {
			var data = result;
			if (data.length === 0) {
				$("#feedback").html("Không tìm thấy kết quả")
			} else {
				$("#feedback").html("")
				var tr;
				for (var i = 0; i < data.length; i++) {
					tr = $('<tr/>');
					tr.append("<td><a>" + data[i].title + "</a><br><span>"
							+ data[i].text + "</span></td>");
					$('#section	').append(tr);
				}
			}
			data = [];

		},
		error : function(e) {
			console.log("ERROR : ", e);

		}
	});

}

function xoaThe() {

}
