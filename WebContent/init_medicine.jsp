<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.project.model.Medicine, java.util.List" %>
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
  <section class="page-bg page-medicine">
    <div class="card login-box init init__medicine card__medicine">
      <div class="card-body mt-3 d-inline-flex flex-column card-body__medicine">
        <h1 class="h3 text-center custom-heading">智慧長照居家系統</h1>
        <h3 class="h5 text-center custom-heading">系統初始設定</h3>
        <div class="my-4 d-flex justify-content-center init__step">
          <p class="mx-2 init__cell mb-0">新增家庭成員</p>
          <p class="mx-2 init__cell mb-0">新增緊急聯絡人</p>
          <p class="mx-2 init__cell mb-0 current">新增用藥設定</p>
        </div>
        <hr class="my-4 mb-4 w-100">
        <h3 class="h5 text-center custom-heading">新增用藥設定</h3>
        <form name="medicineForm" class="pt-4 px-4 d-flex flex-column">
          <input type="hidden" name="record_id" id="recordId" value="">
          <div class="form-group row">
            <label for="memberName" class="col-md-3 col-sm-4 col-form-label">家庭成員：</label>
            <div class="col-md-9 col-sm-8">
              <select class="form-control" id="memberName" name="memberName">
              	<c:forEach var="member" items="${listAllMembers}">
              		<option value="${member.memberName}">${member.memberName}</option>
              	</c:forEach>
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
                  	<input class="form-check-input mr-2" type="checkbox" id="medicineBox1" name="box_1" value="true">
                    <label class="form-check-label" for="medicineBox1">藥盒1</label>
                  </div>
                </div>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="medicine1" name="medicine_1" placeholder="請輸入藥名">
                </div>
              </div>
              <div class="row mb-2 d-flex align-items-center">
                <div class="col-sm-3">
                  <div class="form-check form-check-inline">
                    <input class="form-check-input mr-2" type="checkbox" id="medicineBox2" name="box_2" value="true">
                    <label class="form-check-label" for="medicineBox2">藥盒2</label>
                  </div>
                </div>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="medicine2" name="medicine_2" placeholder="請輸入藥名">
                </div>
              </div>
              <div class="row mb-2 d-flex align-items-center">
                <div class="col-sm-3">
                  <div class="form-check form-check-inline">
                    <input class="form-check-input mr-2" type="checkbox" id="medicineBox3" name="box_3" value="true">
                    <label class="form-check-label" for="medicineBox3">藥盒3</label>
                  </div>
                </div>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="medicine3" name="medicine_3" placeholder="請輸入藥名">
                </div>
              </div>
              <div class="row mb-2 d-flex align-items-center">
                <div class="col-sm-3">
                  <div class="form-check form-check-inline">
                    <input class="form-check-input mr-2" type="checkbox" id="medicineBox4" name="box_4" value="true">
                    <label class="form-check-label" for="medicineBox4">藥盒4</label>
                  </div>
                </div>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="medicine4" name="medicine_4" placeholder="請輸入藥名">
                </div>
              </div>
              <div class="row d-flex align-items-center">
                <div class="col-sm-3">
                  <div class="form-check form-check-inline">
                    <input class="form-check-input mr-2" type="checkbox" id="medicineBox5" name="box_5" value="true">
                    <label class="form-check-label" for="medicineBox5">藥盒5</label>
                  </div>
                </div>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="medicine5" name="medicine_5" placeholder="請輸入藥名">
                </div>
              </div>
            </div>
          </div>
          <div class="action-bar align-self-end mt-3">
         	<input type="submit" value="新增其他用藥設定" class="submitBtn align-self-end mr-2" id="submitBtn">
  			<input type="submit" value="完成設定" class="submitBtn align-self-end addNewBtn" id="nextBtn">
          </div>
        </form>

        <div class="medicine__list">
          <ul class="px-2" id="medicineList">
          	<c:forEach var="medicine" items="${listAllMedicines}">
          		<li class="medicine__list-item row">
	              <input type="hidden" name="record_id" id="record_${medicine.ruleId}" value="${medicine.ruleId}">
	              <div class="col-sm-3">
	                <p class="mb-0">${medicine.memberName}</p>
	              </div>
	              <div class="col-sm-2">
	                <p class="mb-0">${medicine.alertTime}</p>
	              </div>
	              <div class="col-sm-5">
	              	<input type="hidden" value="${medicine.box_1}"><input type="hidden" value="${medicine.medicine_1}">
	              	<input type="hidden" value="${medicine.box_2}"><input type="hidden" value="${medicine.medicine_2}">
	              	<input type="hidden" value="${medicine.box_3}"><input type="hidden" value="${medicine.medicine_3}">
	              	<input type="hidden" value="${medicine.box_4}"><input type="hidden" value="${medicine.medicine_4}">
	              	<input type="hidden" value="${medicine.box_5}"><input type="hidden" value="${medicine.medicine_5}">
	                <c:set var="box_flag" scope="request" value="${\"1\"}"/>
	              	<c:if test="${medicine.box_1.equals(box_flag)}">
	              		<p class="mb-0">藥盒1 - ${medicine.medicine_1}</p>
	              	</c:if>
	              	<c:if test="${medicine.box_2.equals(box_flag)}">
	              		<p class="mb-0">藥盒2 - ${medicine.medicine_2}</p>
	              	</c:if>
	              	<c:if test="${medicine.box_3.equals(box_flag)}">
	              		<p class="mb-0">藥盒3 - ${medicine.medicine_3}</p>
	              	</c:if>
	              	<c:if test="${medicine.box_4.equals(box_flag)}">
	              		<p class="mb-0">藥盒4 - ${medicine.medicine_4}</p>
	              	</c:if>
	              	<c:if test="${medicine.box_5.equals(box_flag)}">
	              		<p class="mb-0">藥盒5 - ${medicine.medicine_5}</p>
	              	</c:if>
	              </div>
	              <div class="col-sm-2 text-right">
	                <a href="init_delete_medicine?medicine_id=${medicine.ruleId}" class="medicine__list-icon medicine__list-icon--delete mr-1"><i class="fa fa-trash-o delMed"
	                    aria-hidden="true"></i></a>
	                <a href="init_edit_medicine?id=${medicine.ruleId}" class="medicine__list-icon mr-1"><i class="fa fa-pencil editMed" aria-hidden="true"></i></a>
	              </div>
	            </li>
          	</c:forEach>
          </ul>
        </div>
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

  <script>
    // For Generate the "alertHour" and "alertMinute" Field Options
    const alertHour = document.getElementById('alertHour');
    const alertMinute = document.getElementById('alertMinute');
    document.addEventListener('DOMContentLoaded', formInit)
    
    function formInit() {
    	for(let i=1; i<=24; i++) {
            if(i<10) {
              alertHour.innerHTML += "<option value=0" + i + ">0" + i + "</option>"
            } else {
              alertHour.innerHTML += "<option value=" + i + ">" + i + "</option>"
            }
          }

          for(let i=0; i<60; i++) {
            if(i<10) {
              alertMinute.innerHTML += "<option value=0" + i + ">0" + i + "</option>"
            } else {
              alertMinute.innerHTML += "<option value=" + i + ">" + i + "</option>"
            }
          }
    }

    // For View Controller - Add Event Listener to Click - Add New One, Next Page, Edit Medicine Rule, Delete Medicine Rule
    const submitBtn = document.getElementById("submitBtn");
    const nextBtn = document.getElementById("nextBtn");
    let btnFlag = "add";
    
    submitBtn.addEventListener('click', function() {
    	if(btnFlag == "add") {
        	document.medicineForm.action="next_medicine";
        }
        document.medicineForm.submit();
    });
    nextBtn.addEventListener('click', function() {
    	document.medicineForm.action = "init_medicine"
 	    document.medicineForm.submit()
    });
    
    document.addEventListener('click', function(e) {
    	if (e.target.classList.contains('editMed')) {
    	    e.preventDefault();
    	    let id = e.target.parentElement.parentElement.parentElement.children[0].value;
    	    let name = (e.target.parentElement.parentElement.parentElement.children[1].children[0].innerHTML)
    	    let toAlert = (e.target.parentElement.parentElement.parentElement.children[2].children[0].innerHTML)
    	    let box1 = (e.target.parentElement.parentElement.parentElement.children[3].children[0].value)
    	    let box2 = (e.target.parentElement.parentElement.parentElement.children[3].children[2].value)
    	    let box3 = (e.target.parentElement.parentElement.parentElement.children[3].children[4].value)
    	    let box4 = (e.target.parentElement.parentElement.parentElement.children[3].children[6].value)
    	    let box5 = (e.target.parentElement.parentElement.parentElement.children[3].children[8].value)
    	    let med1 = (e.target.parentElement.parentElement.parentElement.children[3].children[1].value)
    	    let med2 = (e.target.parentElement.parentElement.parentElement.children[3].children[3].value)
    	    let med3 = (e.target.parentElement.parentElement.parentElement.children[3].children[5].value)
    	    let med4 = (e.target.parentElement.parentElement.parentElement.children[3].children[7].value)
    	    let med5 = (e.target.parentElement.parentElement.parentElement.children[3].children[9].value)
    	    console.log(e.target.parentElement.parentElement.parentElement.children[2].children[0].innerHTML)
    	    console.log(e.target.parentElement.parentElement.parentElement.children[0].value)
    	    console.log(id)
    	    recordId.value = id;
    	    memberName.value = name;
    	    alertHour.value = toAlert.substring(0,2);
    	    alertMinute.value = toAlert.substring(3,);
    	    if(box1 == 1) {
    	      medicineBox1.checked = true;
    	      medicine1.value = med1;
    	    }
    	    if (box2 == 1) {
    	      medicineBox2.checked = true;
    	      medicine2.value = med2;
    	    }
    	    if (box3 == 1) {
    	      medicineBox3.checked = true;
    	      medicine3.value = med3;
    	    }
    	    if (box4 == 1) {
    	      medicineBox4.checked = true;
    	      medicine4.value = med4;
    	    }
    	    if (box5 == 1) {
    	      medicineBox5.checked = true;
    	      medicine5.value = med5;
    	    }
    	    btnFlag = "edit";
    	    document.medicineForm.action="init_edit_medicine?medicine_id=" + id;
    	    document.getElementById("submitBtn").value = "確認編輯";
    	    console.log(btnFlag);
    	  }
    })
  </script>
</body>

</html>