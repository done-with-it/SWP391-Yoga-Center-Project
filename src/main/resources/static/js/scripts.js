/*!
    * Start Bootstrap - SB Admin v7.0.7 (https://startbootstrap.com/template/sb-admin)
    * Copyright 2013-2023 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    // 
// Scripts
// 
const togglePassword = document.querySelector('#togglePassword');
const password = document.querySelector('#passwordField');
const toggleConfirmPassword = document.querySelector('#toggleConfirmPassword');
const confirmPassword = document.querySelector('#confirmPasswordField');

togglePassword.addEventListener('click', function (e) {
    // toggle the type attribute
    const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
    password.setAttribute('type', type);
    // toggle the eye slash icon
    this.classList.toggle('fa-eye-slash');
});

toggleConfirmPassword.addEventListener('click', function (e) {
    // toggle the type attribute
    const type = confirmPassword.getAttribute('type') === 'password' ? 'text' : 'password';
    confirmPassword.setAttribute('type', type);
    // toggle the eye slash icon
    this.classList.toggle('fa-eye-slash');
});


window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});

function calculateAge(dob) {
    var dobDate = new Date(dob);
    var currentDate = new Date();
    var age = currentDate.getFullYear() - dobDate.getFullYear();
    var monthDiff = currentDate.getMonth() - dobDate.getMonth();
  
    if (monthDiff < 0 || (monthDiff === 0 && currentDate.getDate() < dobDate.getDate())) {
      age--;
    }
  
    return age;
  }
  
  function validateAge() {
    var dobInput = document.getElementById("dob").value;
    var age = calculateAge(dobInput);
  
    if (age < 16) {
      var validationMessage = document.getElementById("ageValidationMessage");
      validationMessage.innerText = "You must be over 16 years old.";
      return false;
    } else {
      var validationMessage = document.getElementById("ageValidationMessage");
      validationMessage.innerText = ""; // Xóa thông báo nếu tuổi đủ 16
      return true;
    }
  }
  
  function validatePhone() {
    var phoneInput = document.getElementById("phone").value;
    var phonePattern = /^0\d{7,11}$/;
  
    if (!phonePattern.test(phoneInput)) {
      var validationMessage = document.getElementById("phoneValidationMessage");
      validationMessage.innerText = "Invalid phone number.(Requests start with a zero, are 8 to 12 characters long, and have no letters or special characters).";
      return false;
    } else {
      var validationMessage = document.getElementById("phoneValidationMessage");
      validationMessage.innerText = ""; // Xóa thông báo nếu số điện thoại hợp lệ
      return true;
    }
  }
  
  function validateForm() {
    return validateAge() && validatePhone();
  }
  

