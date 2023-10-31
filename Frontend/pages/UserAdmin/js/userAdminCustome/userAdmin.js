
//=================================================================================================================
//navigation

//---navigate with other containers-----
const home_container = $("#home_container");
const user_manage_container = $("#user_manage_container");
const client_manage_container = $("#client_manage_container");
const admin_manage_container = $("#admin_manage_container");
const report_container = $("#report_container");


function hideAllContainers(){
    home_container.css('display','none');
    user_manage_container.css('display','none');
    client_manage_container.css('display','none');
    admin_manage_container.css('display','none');
    report_container.css('display','none');
}

$(document).ready(function(){
    openHomeContainer();
});

function openHomeContainer(){
    hideAllContainers();
    home_container.css('display','block');
}

function openClientMngContainer(){
    hideAllContainers();
    client_manage_container.css('display','block');
}

function openAdminMngContainer(){
    hideAllContainers();
    admin_manage_container.css('display','block');
}

function openFinanceDataContainer(){
    hideAllContainers();
    report_container.css('display','block');
}
//==============================================================================================================
//add user  form

//field variable
const addFormName = $('#name');
const addFormaddress = $('#address');
const addFormnic = $('#nic_or_passport');
const addFormemail = $('#email');
const addFormusername = $('#username');
const addFormpassword = $('#password');

const addBtn = $('#addBtn'); //disable submit btn
addBtn.prop("disabled", true);
addBtn.css('background-color', 'rgb(126,203,121)');
addBtn.css('color', 'white');

let txtfld1=false;
let txtfld2=false;
let txtfld3=false;
let txtfld4=false;
let txtfld5=false;
let txtfld6=false;
let txtfld7=false;


//signup name key function
function isSignUpNameCheckedRegex(name) {
    const regex =  /^[a-zA-Z0-9_.-]{4,30}$/;
    return regex.test(name);
}
addFormName.on('keyup', function () {

    if(isSignUpNameCheckedRegex(addFormName.val())){
        addFormName.css('background-color','rgba(193, 237, 255, 0.68)');
        txtfld1 = true;
        triggerCreateAccBtn();//trigger to enable
    }else {
        addFormName.css('background-color','rgba(255, 193, 193, 0.68)');
        txtfld1 = false;
        triggerCreateAccBtn();//trigger to enable
    }
});

//signupAddress key function
function isSignUpNAddressCheckedRegex(addresse) {
    const regex = /^\S+\s*[a-zA-Z0-9,.-]+\S{0,48}$/;
    return regex.test(addresse);
}
addFormaddress.on('keyup', function () {

    if(isSignUpNAddressCheckedRegex(addFormaddress.val())){
        addFormaddress.css('background-color','rgba(193, 237, 255, 0.68)');
        txtfld2 = true;
        triggerCreateAccBtn();//trigger to enable
    }else {
        addFormaddress.css('background-color','rgba(255, 193, 193, 0.68)');
        txtfld2 = false;
        triggerCreateAccBtn();//trigger to enable
    }
});

//signupNic key function
function isSignUpNicOrPassportCheckedRegex(nic) {
    const regex = /^[a-zA-Z0-9_-]+\S{8,11}$/;
    return regex.test(nic);
}
addFormnic.on('keyup', function () {

    if(isSignUpNicOrPassportCheckedRegex(addFormnic.val())){
        addFormnic.css('background-color','rgba(193, 237, 255, 0.68)');
        txtfld3= true;
        triggerCreateAccBtn();//trigger to enable
    }else {
        addFormnic.css('background-color','rgba(255, 193, 193, 0.68)');
        txtfld3 = false;
        triggerCreateAccBtn();//trigger to enable
    }
});

//signupEmail key function
function isSignUpEmailCheckedRegex(email) {
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return regex.test(email);
}
addFormemail.on('keyup', function () {

    if(isSignUpEmailCheckedRegex(addFormemail.val())){
        addFormemail.css('background-color','rgba(193, 237, 255, 0.68)');
        txtfld4 = true;
    }else {
        addFormemail.css('background-color','rgba(255, 193, 193, 0.68)');
        txtfld4 = false;
    }
});

//signupUsername key function
function isUsernameCheckedRegex(username) {
    const regex = /^[a-zA-Z0-9_.-]{5,30}$/;
    return regex.test(username);
}
addFormusername.on('keyup', function () {

    if( isUsernameCheckedRegex(addFormusername.val())){
        addFormusername.css('background-color','rgba(193, 237, 255, 0.68)');
        txtfld5 = true;
        triggerCreateAccBtn();//trigger to enable
    }else {
        addFormusername.css('background-color','rgba(255, 193, 193, 0.68)');
        txtfld5 = false;
        triggerCreateAccBtn();//trigger to enable
    }
});

//signupPassword key function
function isSignUpPasswordCheckedRegex(password) {
    const regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
    return regex.test(password);
}
addFormpassword.on('keyup', function () {

    if(isSignUpPasswordCheckedRegex(addFormpassword.val())){
        addFormpassword.css('background-color','rgba(193, 237, 255, 0.68)');
        txtfld6 = true;
        triggerCreateAccBtn();//trigger to enable
    }else {
        addFormpassword.css('background-color','rgba(255, 193, 193, 0.68)');
        txtfld6 = false;
        triggerCreateAccBtn();//trigger to enable
    }
});

//profileImageInput key function
const addFormprofileImage = document.getElementById('profile_image');
addFormprofileImage.onchange = () => {
    const allowedExtensions = ['jpg', 'jpeg', 'png', 'gif'];
    const maxFileSize = 2 * 1024 * 1024; // 2MB

    if (addFormprofileImage.files.length > 0) {
        const file = addFormprofileImage.files[0];
        const fileName = file.name.toLowerCase();
        const fileExtension = fileName.split('.').pop();

        if (allowedExtensions.includes(fileExtension) && file.size <= maxFileSize) {
            addFormprofileImage .style.backgroundColor = 'rgba(193, 237, 255, 0.68)';
            txtfld7 = true;
            triggerCreateAccBtn();//trigger to enable
        } else {
            addFormprofileImage.style.backgroundColor = 'rgba(255, 193, 193, 0.68)';
            txtfld7 = false;
            triggerCreateAccBtn();//trigger to enable
        }
    }
};

function triggerCreateAccBtn() {
    if (txtfld1 === true && txtfld2 === true && txtfld3 === true && txtfld4 === true && txtfld5 === true && txtfld6 === true && txtfld7 === true) {
        // All conditions are true, enable the signup button
        addBtn.prop("disabled", false);
        addBtn.css('background-color', '#0eb506');
        addBtn.css('color', 'white');
    }else {
        addBtn.prop("disabled", true);
        addBtn.css('background-color', 'rgb(126,203,121)');
        addBtn.css('color', 'white');
    }
}


function addUserBtnOnAction(){
    console.log("clicked")

    // fields variables
    let signup_form_name = signup_name.val();
    let signup_form_address = signup_address.val();
    let signup_form_nic_passport = signup_nic_or_passport.val();
    let signup_form_email = signup_email.valueOf();
    let signup_form_username =  signup_username.val();
    let signup_form_password = signup_password.val();
    let signup_form_profile_image = $('#signupprofile_image')[0].files[0];

    var formData = new FormData();

    formData.append("signup_name" , signup_form_name);
    formData.append("signup_address", signup_form_address);
    formData.append("signup_nic_or_passport" , signup_form_nic_passport);
    formData.append("signup_email" , signup_form_email);
    formData.append("signup_username" , signup_form_username);
    formData.append("signup_password" , signup_form_password);
    formData.append("signupprofile_image" , signup_form_profile_image);

    // send data to the backend endpoint

    $.ajax({

        method: "POST" ,
        async: true ,

    })
}







