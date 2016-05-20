var socket = new WebSocket(window.url + ":1899");
/*var socket = {
	send: function(string){
		console.log("fake socket message : " + JSON.stringify(string));
	}
}*/
var events = {};
function sendData(event){
	var name = $(this).attr("name");
	var value = $(this).val();
	var dataobj = {};
	dataobj[name] = value
	socket.send(dataobj);
}
socket.onmessage = function(message){
	var event = JSON.parse(message.data);
	var line = events[event.name] || $("<div></div>")
				.addClass("displayObject")
				.css(event.style || {});
	if((event.type || line.type) == "output"){
		line.type = "output";
		line.text(event.name + " : " + event.data);
	}else if((event.type||line.type) == "input"){
		line.text(event.name + " : ");
		line.append($("<input></input>")
						.prop("type","text")
						.val(event.data)
						.attr("name",event.name)
						.on("blur", sendData)
						.on("keyup", function(event){
								if(event.keyCode == 13)
									$(this).blur();
						}));
	}else{
		console.log(event);
	}
	events[event.name] = line;
	line.appendTo("body");
}