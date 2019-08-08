
document.addEventListener('DOMContentLoaded', () => {
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
      const alertHour = document.getElementById('alertHour');
      const alertMinute = document.getElementById('alertMinute');

      const openOverlay = () => {
        overlayBg.style.display = 'block';
        document.body.style.overflow = 'hidden';
      }

      const closeOverlay = (e) => {
        e.target.parentElement.parentElement.parentElement.parentElement.parentElement.style.display = 'none';
        document.body.style.overflow = 'scroll';
        overlayBg.style.display = 'none';
      }

      // For opening overlay
      editContactBtn.addEventListener('click', (e) => {
        e.preventDefault();
        openOverlay();
        overlayContact.style.display = 'block';
      });

      editMedicineBtn.addEventListener('click', (e) => {
        e.preventDefault();
        openOverlay();
        overlayMedicine.style.display = 'block';
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

      // For closing overlay
      document.addEventListener('click', (e) => {
        if(e.target.classList.contains('cancelBtn')) {
          e.preventDefault();
          closeOverlay(e);
        }
        if(e.target.classList.contains('closingBtn')) {
          e.preventDefault();
          e.target.parentElement.parentElement.parentElement.style.display = 'none';
          overlayBg.style.display = 'none';
          document.body.style.overflow = 'scroll';
        }
      })

      // For medicine alert setting
      for(let i = 1; i <= 24; i++) {
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
      }
    })