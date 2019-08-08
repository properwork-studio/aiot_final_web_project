<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>智慧長照居家系統</title>
  <link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link rel="stylesheet" href="css/style.css">
</head>

<body>
  <section class="page-bg">
    <div class="card login-box init">
      <div class="card-body p-5 d-inline-flex flex-column ">
        <h1 class="h3 text-center custom-heading">智慧長照居家系統</h1>
        <h3 class="h5 text-center custom-heading">系統初始設定</h3>
        <div class="my-4 d-flex justify-content-center init__step">
          <p class="mx-2 init__cell mb-0 current">新增家庭成員</p>
          <p class="mx-2 init__cell mb-0">新增緊急聯絡人</p>
          <p class="mx-2 init__cell mb-0">新增用藥設定</p>
        </div>
        <hr class="my-4 mb-4 w-100">
        <h3 class="h5 text-center custom-heading">新增家庭成員</h3>
        <form method="post" name="memberForm" class="pt-4 px-4 d-flex flex-column" enctype="multipart/form-data">
          <div class="form-group row">
            <label for="memberName" class="col-md-3 col-sm-4 col-form-label">姓 名：</label>
            <div class="col-md-9 col-sm-8">
              <input type="text" class="form-control" id="memberName" name="memberName" placeholder="請輸入家庭成員姓名">
            </div>
          </div>
          <div class="form-group row">
            <label for="nameForTraining" class="col-md-3 col-sm-4 col-form-label">英文代號：</label>
            <div class="col-md-9 col-sm-8">
              <input type="text" class="form-control" id="nameForTraining" name="nameForTraining" placeholder="請輸入不含特殊符號之英文">
            </div>
          </div>
          <div class="form-group row">
            <label for="birthday" class="col-md-3 col-sm-4 col-form-label">生 日：</label>
            <div class="col-md-9 col-sm-8">
              <input class="datepicker form-control" data-date-format="yyyy-mm-dd" data-date-end-date="0d" id="birthday" name="birthday" placeholder="請選擇生日">
            </div>
          </div>
          <div class="form-group row">
            <label for="idNumber" class="col-md-3 col-sm-4 col-form-label">身分證字號：</label>
            <div class="col-md-9 col-sm-8">
              <input type="text" class="form-control" id="idNumber" name="idNumber" placeholder="請輸入身分證字號">
            </div>
          </div>
          <div class="form-group row">
            <label for="photo" class="col-md-3 col-sm-4 col-form-label">照片上傳：</label>
            <div class="col-md-9 col-sm-8">
              <div class="custom-file">
                <input type="file" class="custom-file-input" id="customFile" name="uploadFile">
                <label class="custom-file-label" for="customFile">請選擇一張清晰的正面照片</label>
              </div>
            </div>
          </div>
          <%
	      	if(request.getParameter("add_next")!=null) {
	      		String addNext = (String)request.getParameter("add_next");
	          	System.out.println(addNext);
	      	
	      		if(addNext.equals("false")) { %>
		      	<div class="align-self-end mt-3">
		            <input type="submit" onclick="stepNext()" value="下一步" class="submitBtn align-self-end">
		        </div>
	      	<% }
	      	} else { %>
		      	<div class="align-self-end mt-3">
		            <input type="submit" onclick="addNext()" value="新增另一位家庭成員" class="submitBtn submitBtn--sub align-self-end mr-2">
		            <input type="submit" onclick="stepNext()" value="下一步" class="submitBtn align-self-end">
		        </div>
	      	<% } %>
        </form>
      </div>
    </div>
  </section>



  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
    integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
  <script>
    $('.datepicker').datepicker({
      startDate: '-90y'
    });

    function addNext() {
      document.memberForm.action = "next_member"
      document.memberForm.submit()
    }

    function stepNext() {
      document.memberForm.action = "new_member"
      document.memberForm.submit()
    }
  </script>
</body>

</html>