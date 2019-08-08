<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>智慧長照居家系統</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link rel="stylesheet" href="css/style.css">
</head>

<body>
  <section class="page-bg">
    <div class="card login-box">
      <div class="card-body p-5 d-inline-flex flex-column ">
        <h1 class="h3 text-center custom-heading">智慧長照居家系統</h1>
        <p class="text-center custom-text my-2 mb-4">遠端關心長輩的遠端關心長輩的好幫手</p>
        <hr class="my-4 mb-4 w-100">
        <h3 class="h5 text-center custom-heading">建立新帳號</h3>
        
        <%
      	if(request.getParameter("account_error")!=null) {
      		String accountError = (String)request.getParameter("account_error");
          	System.out.println(accountError);
      	
      		if(accountError.equals("true")) { %>
	      	<div class="px-2 alert-box">
	          <div class="alert alert-danger alert-dismissible fade show mb-1 mt-2" role="alert">
	            <strong>Something wrong!!</strong> 該Email已經被使用了
	            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	              <span aria-hidden="true">&times;</span>
	            </button>
	          </div>
	        </div>
      	<%} }
      %>
        
        <form action="new_account" class="pt-4 px-4 d-flex flex-column">
          <div class="form-group row">
            <label for="email" class="col-md-3 col-sm-4 col-form-label">Email：</label>
            <div class="col-md-9 col-sm-8">
              <input type="email" class="form-control" id="email" name="email" placeholder="將以Email作為系統登入帳號">
            </div>
          </div>
          <div class="form-group row">
            <label for="password" class="col-md-3 col-sm-4 col-form-label">密 碼：</label>
            <div class="col-md-9 col-sm-8">
              <input type="password" class="form-control" id="password" name="password" placeholder="請輸入密碼">
            </div>
          </div>
          <div class="form-group row">
            <label for="passwordCheck" class="col-md-3 col-sm-4 col-form-label">確認密碼：</label>
            <div class="col-md-9 col-sm-8">
              <input type="password" class="form-control" id="passwordCheck" name="passwordCheck" placeholder="請再輸入一次密碼">
            </div>
          </div>
          <div class="form-group row">
            <label for="name" class="col-md-3 col-sm-4 col-form-label">姓 名：</label>
            <div class="col-md-9 col-sm-8">
              <input type="text" class="form-control" id="name" name="name" placeholder="">
            </div>
          </div>
          <div class="form-group row">
            <label for="phone" class="col-md-3 col-sm-4 col-form-label">聯絡電話：</label>
            <div class="col-md-9 col-sm-8">
              <input type="number" class="form-control" id="phone" name="phone" placeholder="">
            </div>
          </div>
          <input type="submit" value="建立帳號" class="submitBtn align-self-end">
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
</body>

</html>