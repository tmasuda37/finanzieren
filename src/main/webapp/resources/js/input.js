function showLength(str) {
	var bufsize = 256 - str.value.length;
	document.getElementById("noteinfo").innerHTML = "あと" + bufsize + "文字入力可能";
}
