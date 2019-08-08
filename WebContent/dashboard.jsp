<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>智慧長照居家系統</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="css/style.css">
</head>

<body>
  <section class="dashboard">
    <header class="d-flex justify-content-between">
      <div class="container-fluid d-flex justify-content-between px-5">
        <div>
          <h1 class="h4 custom-heading text-white">智慧長照居家系統</h1>
          <h3 class="h6 custom-heading text-white">遠端關心長輩的好幫手</h3>
        </div>
        <div class="d-flex align-items-center logout__bar">
          <% ServletContext ctx = this.getServletContext(); %>
          <p class="mb-0 mr-3">Welcome back, <b><% ctx.getAttribute("currentUser"); %></b></p>
          <a href="toLogout" class="submitBtn logoutBtn">Logout</a>
        </div>
      </div>
    </header>
    
    <div class="container px-5 mt-5">
      <section class="main row d-flex">
        <div class="col-md-7">
          <div class="card dashboard__card">
            <div class="card-body py-4 d-flex row align-items-center">
              <div class="col-sm-4">
                <h5 class="custom-heading text-center">家庭成員<br>基本資料</h5>
                <h5 class="custom-heading text-center">Members</h5>
              </div>
              <c:forEach var="member" items="${listMembers}">
              	 <div class="col-sm-4 border-left border-right d-flex flex-column align-self-stretch">
	                <div class="member__image mb-3">
	                  <!-- "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/dataimages/" -->
	                  <img src="http://${request.getServerName}:${request.getServerPort()}/${request.getContextPath()}/dataimages/${member.photoPath}">
	                  <!--  <img src="https://fakeimg.pl/100/">  -->
	                </div>
	                <h6 class="text-center custom-heading mb-1">${member.memberName}</h6>
	                <h6 class="text-center custom-heading mb-1">${member.birthday}</h6>
	                <h6 class="text-center custom-heading mb-1">${member.idNumber}</h6>
	              </div>
              </c:forEach>
         <!-- <div class="col-sm-4 d-flex flex-column">
                <div class="member__image mb-3">
                  <img src="https://fakeimg.pl/100/">
                </div>
                <h6 class="text-center custom-heading mb-1">Grandma</h6>
                <h6 class="text-center custom-heading mb-1">1947.03.25</h6>
                <h6 class="text-center custom-heading mb-1">A12345678</h6>
              </div> -->
            </div>
          </div>
        </div>
        <div class="col-md-5">
          <div class="card dashboard__card">
            <div class="card-body py-4 d-flex row align-items-center">
              <div class="col-sm-4">
                <h5 class="custom-heading text-center">緊急聯絡人</h5>
                <h5 class="custom-heading text-center">Emergency<br>Contact</h5>
              </div>
              <c:forEach var="contact" items="${listContact}">
              	<div class="col-sm-8 border-left">
	                <h4 class="text-center custom-heading mb-1">${contact.contactName}</h4>
	                <p class="text-center custom-text mb-1">${contact.relationship}</p>
	                <h6 class="text-center custom-heading mb-1">${contact.phoneNumber}</h6>
	                <h6 class="text-center custom-heading mb-1">${contact.email}</h6>
	              </div>
	              <a href="#" id="editContactBtn" class="submitBtn editBtn">編輯聯絡人</a>
              </c:forEach>
            </div>
          </div>
        </div>
        <div class="col-md-7 mt-5">
          <div class="card dashboard__card py-3 px-4">
            <div class="card-head p-2 border-bottom">
              <h5 class="custom-heading mb-0">智慧藥箱｜用藥紀錄</h5>
              <h5 class="custom-heading">Medicine Records</h5>
            </div>
            <div class="card-body py-0 mb-5">
              <ul class="record__list px-0 d-flex flex-column">
              	<c:forEach var="medicineRecord" items="${listMedicineRecords}">
              		<li class="record__list-item row mx-0">
	                  <div class="col-sm-2 px-0">
	                    <h6 class="custom-heading">${medicineRecord.memberName}</h6>
	                  </div>
	                  <div class="col-sm-4">
	                    <p class="custom-heading mb-0">${medicineRecord.timeStamp}</p>
	                  </div>
	                  <div class="col-sm-6 d-flex justify-content-between px-0">
	                    <p class="custom-heading mb-0">${medicineRecord.medicine}</p>
	                    <span class="record__list-icon record__list-icon--green"><i class="fa fa-check"
	                        aria-hidden="true"></i></span>
	                  </div>
	                </li>
              	</c:forEach>
                <li class="record__list-item row mx-0">
                  <div class="col-sm-2 px-0">
                    <h6 class="custom-heading">Grandma</h6>
                  </div>
                  <div class="col-sm-4">
                    <p class="custom-heading mb-0">2019.07.30 08:31</p>
                  </div>
                  <div class="col-sm-6 d-flex justify-content-between px-0">
                    <p class="custom-heading mb-0">B-complex, 維骨力, 降血壓</p>
                    <span class="record__list-icon record__list-icon--green"><i class="fa fa-check"
                        aria-hidden="true"></i></span>
                  </div>
                </li>
                <li class="record__list-item row mx-0">
                  <div class="col-sm-2 px-0">
                    <h6 class="custom-heading">Grandma</h6>
                  </div>
                  <div class="col-sm-4">
                    <p class="custom-heading mb-0">2019.07.30 08:31</p>
                  </div>
                  <div class="col-sm-6 d-flex justify-content-between px-0">
                    <p class="custom-heading mb-0">B-complex, 維骨力, 降血壓</p>
                    <span class="record__list-icon record__list-icon--green"><i class="fa fa-check"
                        aria-hidden="true"></i></span>
                  </div>
                </li>
                <li class="record__list-item row mx-0">
                  <div class="col-sm-2 px-0">
                    <h6 class="custom-heading">Grandma</h6>
                  </div>
                  <div class="col-sm-4">
                    <p class="custom-heading mb-0">2019.07.30 08:31</p>
                  </div>
                  <div class="col-sm-6 d-flex justify-content-between px-0">
                    <p class="custom-heading mb-0">B-complex, 維骨力, 降血壓</p>
                    <span class="record__list-icon record__list-icon--red"><i class="fa fa-check"
                        aria-hidden="true"></i></span>
                  </div>
                </li>
                <li class="record__list-item row mx-0">
                  <div class="col-sm-2 px-0">
                    <h6 class="custom-heading">Grandma</h6>
                  </div>
                  <div class="col-sm-4">
                    <p class="custom-heading mb-0">2019.07.30 08:31</p>
                  </div>
                  <div class="col-sm-6 d-flex justify-content-between px-0">
                    <p class="custom-heading mb-0">B-complex, 維骨力, 降血壓</p>
                    <span class="record__list-icon record__list-icon--green"><i class="fa fa-check"
                        aria-hidden="true"></i></span>
                  </div>
                </li>
                <li class="record__list-item row mx-0">
                  <div class="col-sm-2 px-0">
                    <h6 class="custom-heading">Grandma</h6>
                  </div>
                  <div class="col-sm-4">
                    <p class="custom-heading mb-0">2019.07.30 08:31</p>
                  </div>
                  <div class="col-sm-6 d-flex justify-content-between px-0">
                    <p class="custom-heading mb-0">B-complex, 維骨力, 降血壓</p>
                    <span class="record__list-icon record__list-icon--green"><i class="fa fa-check"
                        aria-hidden="true"></i></span>
                  </div>
                </li>
                <a href="#" id="medicineRecordBtn" class="detailBtn align-self-end mt-3">顯示完整紀錄</a>
              </ul>
              <a href="#" class="submitBtn editBtn" id="editMedicineBtn">用藥設定</a>
            </div>
          </div>
        </div>
        <div class="col-md-5 mt-5">
          <div class="card dashboard__card dashboard__fall">
            <div class="card-body py-4 d-flex align-items-center">
              <div class="col-sm-4">
                <h5 class="custom-heading text-center">跌倒偵測</h5>
                <h5 class="custom-heading text-center mb-0">Fall<br>Detection</h5>
              </div>
              <div class="col-sm-8 border-left py-1">
                <h1 class="text-center custom-heading--green mb-0">SAFE</h1>
                <p class="text-mute mb-0 text-center custom-heading">Members are all safe</p>
              </div>
            </div>
          </div>
          <div class="card dashboard__card dashboard__door py-3 px-4">
            <div class="card-head p-2 border-bottom">
              <h5 class="custom-heading mb-0">大門進出紀錄</h5>
              <h5 class="custom-heading">Entering / Leaving Records</h5>
            </div>
            <div class="card-body py-0 mb-5">
              <ul class="record__list px-0 d-flex flex-column">
                <li class="record__list-item row mx-0">
                  <div class="col-sm-3 px-0">
                    <h6 class="custom-heading">Grandma</h6>
                  </div>
                  <div class="col-sm-7">
                    <p class="custom-heading mb-0">2019.07.30 08:31</p>
                  </div>
                  <div class="col-sm-2 text-right px-0">
                    <p class="custom-heading mb-0 pr-2">出門</p>
                  </div>
                </li>
                <li class="record__list-item row mx-0">
                  <div class="col-sm-3 px-0">
                    <h6 class="custom-heading">Grandma</h6>
                  </div>
                  <div class="col-sm-7">
                    <p class="custom-heading mb-0">2019.07.30 08:31</p>
                  </div>
                  <div class="col-sm-2 text-right px-0">
                    <p class="custom-heading mb-0 pr-2">出門</p>
                  </div>
                </li>
                <li class="record__list-item row mx-0">
                  <div class="col-sm-3 px-0">
                    <h6 class="custom-heading">Grandma</h6>
                  </div>
                  <div class="col-sm-7">
                    <p class="custom-heading mb-0">2019.07.30 08:31</p>
                  </div>
                  <div class="col-sm-2 text-right px-0">
                    <p class="custom-heading mb-0 pr-2">出門</p>
                  </div>
                </li>
                <a href="#" id="doorRecordBtn" class="detailBtn align-self-end mt-3">顯示完整紀錄</a>
              </ul>
            </div>
          </div>
        </div>
      </section>
    </div>
  </section>

  <div class="overlay__background"></div>

  <div class="overlay__box overlay__contact">
    <div class="card">
      <div class="card-body p-4">
        <h3 class="h4 text-center py-3 custom-heading">編輯聯絡人</h3>
        <hr class="py-3">
        <form name="contactForm" action="dashboard.html" class="px-4 d-flex flex-column">
          <div class="form-group row">
            <label for="contactName" class="col-md-3 col-sm-4 col-form-label">姓 名：</label>
            <div class="col-md-9 col-sm-8">
              <input type="text" class="form-control" id="contactName" name="contactName" placeholder="請輸入聯絡人姓名">
            </div>
          </div>
          <div class="form-group row">
            <label for="relationship" class="col-md-3 col-sm-4 col-form-label">關 係：</label>
            <div class="col-md-9 col-sm-8">
              <input type="text" class="form-control" id="relationship" name="relationship" placeholder="請輸入聯絡人與家庭成員的關係">
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
          <div class="action-bar align-self-end mt-3">
            <input type="reset" value="取消" class="submitBtn cancelBtn mr-1">
            <input type="submit" value="確定修改" class="submitBtn">
          </div>
        </form>
      </div>
    </div>
  </div>

  <div class="overlay__box overlay__medicine">
    <div class="card  card__medicine">
      <div class="card-body card-body__medicine">
        <h3 class="h4 text-center py-3 custom-heading">用藥設定</h3>
        <hr class="py-3">
        <form name="medicineForm" action="#" class="px-4 d-flex flex-column">
          <div class="form-group row">
            <label for="memberName" class="col-md-3 col-sm-4 col-form-label">家庭成員：</label>
            <div class="col-md-9 col-sm-8">
              <select class="form-control" id="memberName">
                <option value="member1">家庭成員1</option>
                <option value="member2">家庭成員2</option>
              </select>
            </div>
          </div>
          <div class="form-group row">
            <label for="alertTime" class="col-md-3 col-sm-4 col-form-label">用藥時間：</label>
            <div class="col-md-9 col-sm-8 row">
              <div class="col-sm-4">
                <select class="form-control" id="alertHour" name="alertHour">
                </select>
              </div>
              <div class="col-sm-4">
                <select class="form-control" id="alertMinute" name="alertMinute">
                </select>
              </div>
            </div>
          </div>
          <div class="form-group row">
            <p class="col-md-3 col-sm-4 col-form-label">用藥設定：</p>
            <div class="col-md-9 col-sm-8">
              <div class="row mb-2 d-flex align-items-center">
                <div class="col-sm-3">
                  <div class="form-check form-check-inline">
                    <input class="form-check-input mr-2" type="checkbox" id="medicineBox1" value="box1">
                    <label class="form-check-label" for="medicineBox1">藥盒1</label>
                  </div>
                </div>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="medicine1" name="medicine1" placeholder="請輸入藥名">
                </div>
              </div>
              <div class="row mb-2 d-flex align-items-center">
                <div class="col-sm-3">
                  <div class="form-check form-check-inline">
                    <input class="form-check-input mr-2" type="checkbox" id="medicineBox2" value="box2">
                    <label class="form-check-label" for="medicineBox2">藥盒2</label>
                  </div>
                </div>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="medicine2" name="medicine2" placeholder="請輸入藥名">
                </div>
              </div>
              <div class="row mb-2 d-flex align-items-center">
                <div class="col-sm-3">
                  <div class="form-check form-check-inline">
                    <input class="form-check-input mr-2" type="checkbox" id="medicineBox3" value="box3">
                    <label class="form-check-label" for="medicineBox3">藥盒3</label>
                  </div>
                </div>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="medicine3" name="medicine3" placeholder="請輸入藥名">
                </div>
              </div>
              <div class="row mb-2 d-flex align-items-center">
                <div class="col-sm-3">
                  <div class="form-check form-check-inline">
                    <input class="form-check-input mr-2" type="checkbox" id="medicineBox4" value="box4">
                    <label class="form-check-label" for="medicineBox4">藥盒4</label>
                  </div>
                </div>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="medicine4" name="medicine4" placeholder="請輸入藥名">
                </div>
              </div>
              <div class="row d-flex align-items-center">
                <div class="col-sm-3">
                  <div class="form-check form-check-inline">
                    <input class="form-check-input mr-2" type="checkbox" id="medicineBox5" value="box5">
                    <label class="form-check-label" for="medicineBox5">藥盒5</label>
                  </div>
                </div>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="medicine5" name="medicine5" placeholder="請輸入藥名">
                </div>
              </div>
            </div>
          </div>
          <div class="action-bar align-self-end mt-3">
            <input type="reset" value="取消" class="submitBtn cancelBtn mr-1">
            <input type="submit" value="新增設定" class="submitBtn">
          </div>
        </form>
        
        <div class="medicine__list">
          <ul class="px-2">
            <li class="medicine__list-item row">
              <div class="col-sm-3">
                <p class="mb-0">Grandma</p>
              </div>
              <div class="col-sm-2">
                <p class="mb-0">08:30</p>
              </div>
              <div class="col-sm-5">
                <p class="mb-0">藥盒1 - B-complex</p>
                <p class="mb-0">藥盒2 - 維骨力</p>
                <p class="mb-0">藥盒4 - 降血壓</p>
              </div>
              <div class="col-sm-2 text-right">
                <a href="#" class="medicine__list-icon medicine__list-icon--delete mr-1"><i class="fa fa-trash-o"
                    aria-hidden="true"></i></a>
                <a href="#" class="medicine__list-icon mr-1"><i class="fa fa-pencil" aria-hidden="true"></i></a>
              </div>
            </li>
            <li class="medicine__list-item row">
              <div class="col-sm-3">
                <p class="mb-0">Grandma</p>
              </div>
              <div class="col-sm-2">
                <p class="mb-0">08:30</p>
              </div>
              <div class="col-sm-5">
                <p class="mb-0">藥盒1 - B-complex</p>
                <p class="mb-0">藥盒2 - 維骨力</p>
                <p class="mb-0">藥盒4 - 降血壓</p>
              </div>
              <div class="col-sm-2 text-right">
                <a href="#" class="medicine__list-icon medicine__list-icon--delete mr-1"><i class="fa fa-trash-o"
                    aria-hidden="true"></i></a>
                <a href="#" class="medicine__list-icon mr-1"><i class="fa fa-pencil" aria-hidden="true"></i></a>
              </div>
            </li>
            <li class="medicine__list-item row">
              <div class="col-sm-3">
                <p class="mb-0">Grandma</p>
              </div>
              <div class="col-sm-2">
                <p class="mb-0">08:30</p>
              </div>
              <div class="col-sm-5">
                <p class="mb-0">藥盒1 - B-complex</p>
                <p class="mb-0">藥盒2 - 維骨力</p>
                <p class="mb-0">藥盒4 - 降血壓</p>
              </div>
              <div class="col-sm-2 text-right">
                <a href="#" class="medicine__list-icon medicine__list-icon--delete mr-1"><i class="fa fa-trash-o"
                    aria-hidden="true"></i></a>
                <a href="#" class="medicine__list-icon mr-1"><i class="fa fa-pencil" aria-hidden="true"></i></a>
              </div>
            </li>
            <li class="medicine__list-item row">
              <div class="col-sm-3">
                <p class="mb-0">Grandma</p>
              </div>
              <div class="col-sm-2">
                <p class="mb-0">08:30</p>
              </div>
              <div class="col-sm-5">
                <p class="mb-0">藥盒1 - B-complex</p>
                <p class="mb-0">藥盒2 - 維骨力</p>
                <p class="mb-0">藥盒4 - 降血壓</p>
              </div>
              <div class="col-sm-2 text-right">
                <a href="#" class="medicine__list-icon medicine__list-icon--delete mr-1"><i class="fa fa-trash-o"
                    aria-hidden="true"></i></a>
                <a href="#" class="medicine__list-icon mr-1"><i class="fa fa-pencil" aria-hidden="true"></i></a>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>

  <div class="overlay__box overlay__medicine-record">
    <div class="card  card__medicine">
      <div class="card-body card-body__medicine">
        <h3 class="h4 text-center py-3 custom-heading">用藥紀錄</h3>
        <a href="#" class="closingBtn">X</a>
        <hr class="mt-3">
        <ul class="record__list px-0 d-flex flex-column">
          <li class="record__list-item row mx-0 mx-0">
            <div class="col-sm-2 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-4">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-6 d-flex justify-content-between px-0">
              <p class="custom-heading mb-0">B-complex, 維骨力, 降血壓</p>
              <span class="record__list-icon record__list-icon--green"><i class="fa fa-check" aria-hidden="true"></i></span>
            </div>
          </li>
          <li class="record__list-item row mx-0">
            <div class="col-sm-2 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-4">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-6 d-flex justify-content-between px-0">
              <p class="custom-heading mb-0">B-complex, 維骨力, 降血壓</p>
              <span class="record__list-icon record__list-icon--green"><i class="fa fa-check" aria-hidden="true"></i></span>
            </div>
          </li>
          <li class="record__list-item row mx-0">
            <div class="col-sm-2 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-4">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-6 d-flex justify-content-between px-0">
              <p class="custom-heading mb-0">B-complex, 維骨力, 降血壓</p>
              <span class="record__list-icon record__list-icon--red"><i class="fa fa-check" aria-hidden="true"></i></span>
            </div>
          </li>
          <li class="record__list-item row mx-0">
            <div class="col-sm-2 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-4">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-6 d-flex justify-content-between px-0">
              <p class="custom-heading mb-0">B-complex, 維骨力, 降血壓</p>
              <span class="record__list-icon record__list-icon--green"><i class="fa fa-check" aria-hidden="true"></i></span>
            </div>
          </li>
          <li class="record__list-item row mx-0">
            <div class="col-sm-2 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-4">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-6 d-flex justify-content-between px-0">
              <p class="custom-heading mb-0">B-complex, 維骨力, 降血壓</p>
              <span class="record__list-icon record__list-icon--green"><i class="fa fa-check" aria-hidden="true"></i></span>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>

  <div class="overlay__box overlay__door">
    <div class="card card__medicine">
      <div class="card-body card-body__medicine">
        <h3 class="h4 text-center py-3 custom-heading">大門進出紀錄</h3>
        <a href="#" class="closingBtn">X</a>
        <hr class="mt-3">
        <ul class="record__list px-0 d-flex flex-column">
          <li class="record__list-item row mx-0">
            <div class="col-sm-3 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-7">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-2 text-right px-0">
              <p class="custom-heading mb-0 pr-2">出門</p>
            </div>
          </li>
          <li class="record__list-item row mx-0">
            <div class="col-sm-3 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-7">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-2 text-right px-0">
              <p class="custom-heading mb-0 pr-2">出門</p>
            </div>
          </li>
          <li class="record__list-item row mx-0">
            <div class="col-sm-3 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-7">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-2 text-right px-0">
              <p class="custom-heading mb-0 pr-2">出門</p>
            </div>
          </li>
          <li class="record__list-item row mx-0">
            <div class="col-sm-3 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-7">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-2 text-right px-0">
              <p class="custom-heading mb-0 pr-2">出門</p>
            </div>
          </li>
          <li class="record__list-item row mx-0">
            <div class="col-sm-3 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-7">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-2 text-right px-0">
              <p class="custom-heading mb-0 pr-2">出門</p>
            </div>
          </li>
          <li class="record__list-item row mx-0">
            <div class="col-sm-3 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-7">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-2 text-right px-0">
              <p class="custom-heading mb-0 pr-2">出門</p>
            </div>
          </li>
          <li class="record__list-item row mx-0">
            <div class="col-sm-3 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-7">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-2 text-right px-0">
              <p class="custom-heading mb-0 pr-2">出門</p>
            </div>
          </li>
          <li class="record__list-item row mx-0">
            <div class="col-sm-3 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-7">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-2 text-right px-0">
              <p class="custom-heading mb-0 pr-2">出門</p>
            </div>
          </li>
          <li class="record__list-item row mx-0">
            <div class="col-sm-3 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-7">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-2 text-right px-0">
              <p class="custom-heading mb-0 pr-2">出門</p>
            </div>
          </li>
          <li class="record__list-item row mx-0">
            <div class="col-sm-3 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-7">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-2 text-right px-0">
              <p class="custom-heading mb-0 pr-2">出門</p>
            </div>
          </li>
          <li class="record__list-item row mx-0">
            <div class="col-sm-3 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-7">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-2 text-right px-0">
              <p class="custom-heading mb-0 pr-2">出門</p>
            </div>
          </li>
          <li class="record__list-item row mx-0">
            <div class="col-sm-3 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-7">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-2 text-right px-0">
              <p class="custom-heading mb-0 pr-2">出門</p>
            </div>
          </li>
          <li class="record__list-item row mx-0">
            <div class="col-sm-3 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-7">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-2 text-right px-0">
              <p class="custom-heading mb-0 pr-2">出門</p>
            </div>
          </li>
          <li class="record__list-item row mx-0">
            <div class="col-sm-3 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-7">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-2 text-right px-0">
              <p class="custom-heading mb-0 pr-2">出門</p>
            </div>
          </li>
          <li class="record__list-item row mx-0">
            <div class="col-sm-3 px-0">
              <h6 class="custom-heading">Grandma</h6>
            </div>
            <div class="col-sm-7">
              <p class="custom-heading mb-0">2019.07.30 08:31</p>
            </div>
            <div class="col-sm-2 text-right px-0">
              <p class="custom-heading mb-0 pr-2">出門</p>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>


  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
    integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
    crossorigin="anonymous"></script>
  <script src="js/script.js"></script>
</body>

</html>