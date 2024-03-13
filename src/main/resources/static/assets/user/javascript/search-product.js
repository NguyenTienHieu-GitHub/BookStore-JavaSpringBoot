function search() {
	var search = $("#search").val();
	var url = "/tim-kiem" + "?q=" + search;
	//if(search !== ""){
	//	url = url + "?q=" + search; 
	//}
	window.location.href = url;
}