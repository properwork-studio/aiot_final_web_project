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
          <p class="mx-2 init__cell mb-0">新增家庭成員</p>
          <p class="mx-2 init__cell mb-0 current">新增緊急聯絡人</p>
          <p class="mx-2 init__cell mb-0">新增用藥設定</p>
        </div>
        <hr class="my-4 mb-4 w-100">
        <h3 class="h5 text-center custom-heading">新增緊急聯絡人</h3>
        <form name="contactForm" action="new_contact" class="pt-4 px-4 d-flex flex-column">
          <div class="form-group row">
            <label for="contactName" class="col-md-3 col-sm-4 col-form-label">姓 名：</label>
            <div class="col-md-9 col-sm-8">
              <input type="text" class="form-control" id="contactName" name="contactName" placeholder="請輸入聯絡人姓名">
            </div>
          </div>
          <div class="form-group row">
            <label for="relationship" class="col-md-3 col-sm-4 col-form-label">關 係：</label>
            <div class="col-md-9 col-sm-8">
              <input type="text" class="form-control" id="relationship" name="relationship"
                placeholder="請輸入聯絡人與家庭成員的關係">
            </div>
          </div>
          <div class="form-group row">
            <label for="phoneNumber" class="col-md-3 col-sm-4 col-form-label">聯絡電話：</label>
            <div class="col-md-9 col-sm-8">
              <input type="number" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="請輸入手機號碼">
            </div>
          </div>
          <div class="form-group row">
            <label for="contactEmail" class="col-md-3 col-sm-4 col-form-label">E-mail：</label>
            <div class="col-md-9 col-sm-8">
              <input type="email" class="form-control" id="contactEmail" name="contactEmail" placeholder="請輸入聯絡信箱">
            </div>
          </div>
          <input type="submit" value="下一步" class="submitBtn align-self-end mt-3">
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