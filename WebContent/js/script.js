
// For Contorling Overlay
const overlayBg = document.querySelector('.overlay__background');
const overlayContact = document.querySelector('.overlay__contact');
const overlayMedicine = document.querySelector('.overlay__medicine');
const overlayMedicineRecord = document.querySelector('.overlay__medicine-record');
const overlayDoor = document.querySelector('.overlay__door');
const cancelBtn = document.getElementById('cancelBtn');
const closingBtn = document.getElementById('closingBtn');
const editContactBtn = document.getElementById('editContactBtn');
const editMedicineBtn = document.getElementById('editMedicineBtn');
const medicineRecordBtn = document.getElementById('medicineRecordBtn');
const doorRecordBtn = document.getElementById('doorRecordBtn');

// For Selecting Form Fields
const medicineList = document.getElementById('medicineList');
const alertHour = document.getElementById('alertHour');
const alertMinute = document.getElementById('alertMinute');
const memberName = document.getElementById('memberName'); // <select></select>
const medicineBox1 = document.getElementById('medicineBox1'); // checkbox
const medicineBox2 = document.getElementById('medicineBox2'); // checkbox
const medicineBox3 = document.getElementById('medicineBox3'); // checkbox
const medicineBox4 = document.getElementById('medicineBox4'); // checkbox
const medicineBox5 = document.getElementById('medicineBox5'); // checkbox
const medicine1 = document.getElementById('medicine1');
const medicine2 = document.getElementById('medicine2');
const medicine3 = document.getElementById('medicine3');
const medicine4 = document.getElementById('medicine4');
const medicine5 = document.getElementById('medicine5');
const recordId = document.getElementById('recordId');

const openOverlay = () => {
  overlayBg.style.display = 'block';
  document.body.style.overflow = 'hidden';
}

const closeOverlay = (e) => {
  e.target.parentElement.parentElement.parentElement.parentElement.parentElement.style.display = 'none';
  overlayBg.style.display = 'none';
}

editContactBtn.addEventListener('click', (e) => {
  e.preventDefault();
  openOverlay();
  overlayContact.style.display = 'block';
});

editMedicineBtn.addEventListener('click', (e) => {
  e.preventDefault();
  openOverlay();
  overlayMedicine.style.display = 'block';
  let lastId = medicineList.children[0].children[0].value;
  document.getElementById('recordId').value = parseInt(lastId) + 1;
})

medicineRecordBtn.addEventListener('click', (e) => {
  e.preventDefault();
  openOverlay();
  overlayMedicineRecord.style.display = 'block';
})

doorRecordBtn.addEventListener('click', (e) => {
  e.preventDefault();
  openOverlay();
  overlayDoor.style.display = 'block';
})

document.addEventListener('click', (e) => {
  if (e.target.classList.contains('cancelBtn')) {
    e.preventDefault();
    closeOverlay(e);
  }
  if (e.target.classList.contains('closingBtn')) {
    e.preventDefault();
    e.target.parentElement.parentElement.parentElement.style.display = 'none';
    overlayBg.style.display = 'none';
  }
  
  if (e.target.classList.contains('addNewBtn')) {
    e.preventDefault();
    console.log("click")
    let row = "";
    // For Storing Data for Backend
    if (medicineBox1.checked == false) {
      row += `<input type="hidden" name="record_box1" id="record_medBox1" value="0"><input type="hidden" name="record_med1" id="med1" value="">`;
    } else {
      row += `<input type="hidden" name="box1" id="record_medBox1" value="1"><input type="hidden" name="record_med1" id="med1" value="${medicine1.value}">`;
    }
    if (medicineBox2.checked == false) {
      row += `<input type="hidden" name="record_box2" id="record_medBox1" value="0"><input type="hidden" name="record_med2" id="med2" value="">`;
    } else {
      row += `<input type="hidden" name="box2" id="record_medBox2" value="1"><input type="hidden" name="record_med2" id="med2" value="${medicine2.value}">`;
    }
    if (medicineBox3.checked == false) {
      row += `<input type="hidden" name="record_box3" id="record_medBox1" value="0"><input type="hidden" name="record_med3" id="med3" value="">`;
    } else {
      row += `<input type="hidden" name="box3" id="record_medBox3" value="1"><input type="hidden" name="record_med3" id="med3" value="${medicine3.value}">`;
    }
    if (medicineBox4.checked == false) {
      row += `<input type="hidden" name="record_box4" id="record_medBox1" value="0"><input type="hidden" name="record_med4" id="med4" value="">`;
    } else {
      row += `<input type="hidden" name="box4" id="record_medBox4" value="1"><input type="hidden" name="record_med4" id="med4" value="${medicine4.value}">`;
    }
    if (medicineBox5.checked == false) {
      row += `<input type="hidden" name="record_box5" id="record_medBox1" value="0"><input type="hidden" name="record_med5" id="med5" value="">`;
    } else {
      row += `<input type="hidden" name="box5" id="record_medBox5" value="1"><input type="hidden" name="record_med5" id="med5" value="${medicine5.value}">`;
    }
    // For Show Data in Frontend
    if(medicineBox1.checked) {
      row += `<p class="mb-0">藥盒1 - ${medicine1.value}</p>`;
      medicineBox1.checked = false;
      medicine1.value = "";
    }
    if (medicineBox2.checked) {
      row += `<p class="mb-0">藥盒2 - ${medicine2.value}</p>`;
      medicineBox2.checked = false;
      medicine2.value = "";
    }
    if (medicineBox3.checked) {
      row += `<p class="mb-0">藥盒3 - ${medicine3.value}</p>`;
      medicineBox3.checked = false;
      medicine3.value = "";
    }
    if (medicineBox4.checked) {
      row += `<p class="mb-0">藥盒4 - ${medicine4.value}</p>`;
      medicineBox4.checked = false;
      medicine4.value = "";
    }
    if (medicineBox5.checked) {
      row += `<p class="mb-0">藥盒5 - ${medicine5.value}</p>`;
      medicineBox5.checked = false;
      medicine5.value = "";
    }
    
    let newRow = document.createElement('li');
    newRow.classList = "medicine__list-item row";
    newRow.innerHTML = `
      <input type="hidden" name="record_id" id="record_${recordId.value}" value="${recordId.value}">
      <div class="col-sm-3">
        <p class="mb-0">${memberName.value}</p>
      </div>
      <div class="col-sm-2">
        <p class="mb-0">${alertHour.value}:${alertMinute.value}</p>
      </div>
      <div class="col-sm-5">` + row + `
      </div>
      <div class="col-sm-2 text-right">
        <a href="delete_medicine?id=${recordId.value}" class="medicine__list-icon medicine__list-icon--delete mr-1"><i class="fa fa-trash-o delMed"
            aria-hidden="true"></i></a>
        <a href="edit_medicine?id=${recordId.value}" class="medicine__list-icon mr-1"><i class="fa fa-pencil editMed" aria-hidden="true"></i></a>
      </div>
      <input type="hidden" name="current_id" id="record_1" value="${recordId.value}"
    `;
    medicineList.insertBefore(newRow, medicineList.childNodes[0]);
    memberName.options[0].selected = true;
    alertHour.options[0].selected = true;
    alertMinute.options[0].selected = true;

    document.medicineForm.action="new_medicine";
    document.medicineForm.submit();
  }

  if (e.target.classList.contains('delMed')) {
    e.preventDefault();
    let currentId = e.target.parentElement.parentElement.parentElement.children[0].value;
    e.target.parentElement.parentElement.parentElement.remove();

    document.medicineForm.action=`delete_medicine?id=${currentId}`;
    document.medicineForm.submit();
  }

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
    document.medicineForm.action=`edit_medicine?id=${currentId}`;
  }
  // console.log(e.target.parentElement.parentElement.parentElement.children[1].children[0].innerHTML)
})

// For medicine alert setting
for (let i = 1; i <= 24; i++) {
  if (i < 10) {
    alertHour.innerHTML += `<option value="0${i}">0${i}</option>`
  } else {
    alertHour.innerHTML += `<option value="${i}">${i}</option>`
  }
}

for (let i = 0; i < 60; i++) {
  if (i < 10) {
    alertMinute.innerHTML += `<option value="0${i}">0${i}</option>`
  } else {
    alertMinute.innerHTML += `<option value="${i}">${i}</option>`
  }
}s