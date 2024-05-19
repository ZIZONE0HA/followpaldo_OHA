
//jquery는 매개변수를 받지 못해서 forEach문에서 반복문을 돌리면
//button이 어떤 contentId값과 매치되는지 알지 못한다 
//그러므로, javascript onclick을 사용하여 매개변수로 id값을 주어 구한다

function check(contentId){
	
	//json 형태로 컨트롤러에 정보를 넘겨주기 위해 json 형태로 데이터값을 받는다
	 var formData = {
          	contentId : contentId, //버튼 매개변수의 contentId값
        	//userId : "${sessionScope.id}" //'${sessionScope.id}' //로그인시 생성한 세션의 id값
          }
     
     $.ajax({
     	
       type: 'POST',
       contentType: 'application/json', //데이터를 JSON 으로 전송한다
       data:JSON.stringify(formData), //JSON.stringify(formData)는 format데이터를 JSON형태로 변환하는 역할
       url: '/zzim', //컨트롤러의 url 주소 매핑
       success: function(response) {
			if (response == "1") {
                alert("찜하기 완료");
            } else {
                alert("찜하기 취소");
            }
       }
     });
}   