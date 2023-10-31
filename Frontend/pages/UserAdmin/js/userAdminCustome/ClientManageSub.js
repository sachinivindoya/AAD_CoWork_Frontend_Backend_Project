//
// function closeIconClicked(){
//  c_m_s_a_model.css('display','none');
//
// }



//-----Save New Client--------------
const c_m_s_a_image = $('#c_m_s_a_image');
const c_m_s_a_image_input = $('#c_m_s_a_image_input');
const c_m_s_a_username = $('#c_m_s_a_username');
const c_m_s_a_nameinitial = $('#c_m_s_a_nameinitial');
const c_m_s_a_password = $('#c_m_s_a_password');
const c_m_s_a_email = $('#c_m_s_a_email');
const c_m_s_a_address = $('#c_m_s_a_address');
const c_m_s_a_nic = $('#c_m_s_a_nic');
const c_m_s_a_nic_front_image = $('#c_m_s_a_nic_front_image');
const c_m_s_a_nic_front_image_input = $('#c_m_s_a_nic_front_image_input');
const c_m_s_a_nic_rear_image = $('#c_m_s_a_nic_rear_image');
const c_m_s_a_nic_rear_image_input = $('#c_m_s_a_nic_rear_image_input');
const c_m_s_a_gender = $('#c_m_s_a_gender');
const c_m_s_a_age = $('#c_m_s_a_age');
const c_m_s_a_tell = $('#c_m_s_a_tell');
const c_m_s_a_remarks = $('#c_m_s_a_remarks');
const c_m_s_a_savebtn = $('#c_m_s_a_savebtn');
const c_m_s_a_model = $('#client-manage-container-add-client-model');

//add if input client image image set into view
$(document).ready(function() {
    c_m_s_a_image_input.on('change', function () {
        var newImageFile = this.files[0]; // Get the file when a change occurs in the input

        if (newImageFile) { // Check if a file is selected
            var reader = new FileReader();
            var baseString;
            reader.onload = function () {
                baseString = reader.result;

                // Update the 'src' attribute of the image element
                c_m_s_a_image.attr('src', baseString);
            };
            reader.readAsDataURL(newImageFile);
        } else {
            console.error('No file selected');
        }
    });
});

//add if input front nic image set into view
$(document).ready(function() {
    c_m_s_a_nic_front_image_input.on('change', function () {
        var newImageFile = this.files[0]; // Get the file when a change occurs in the input

        if (newImageFile) { // Check if a file is selected
            var reader = new FileReader();
            var baseString;
            reader.onload = function () {
                baseString = reader.result;

                // Update the 'src' attribute of the image element
                c_m_s_a_nic_front_image.attr('src', baseString);
            };
            reader.readAsDataURL(newImageFile);
        } else {
            console.error('No file selected');
        }
    });
});

//add if input rear nic image set into view
$(document).ready(function() {
    c_m_s_a_nic_rear_image_input.on('change', function () {
        var newImageFile = this.files[0]; // Get the file when a change occurs in the input

        if (newImageFile) { // Check if a file is selected
            var reader = new FileReader();
            var baseString;
            reader.onload = function () {
                baseString = reader.result;

                // Update the 'src' attribute of the image element
                c_m_s_a_nic_rear_image.attr('src', baseString);
            };
            reader.readAsDataURL(newImageFile);
        } else {
            console.error('No file selected');
        }
    });
});


//------add form validations-----------
let addtxtfld1 = false;
let addtxtfld2 = false;
let addtxtfld3 = false;
let addtxtfld4 = false;
let addtxtfld5 = false;
let addtxtfld6 = false;
let addtxtfld7 = false;
let addtxtfld8 = false;
let addtxtfld9 = false;
let addtxtfld10 = false;
let addtxtfld11 = false;
let addtxtfld12 = false;
let addtxtfld13 = false;

// const c_m_s_a_image_input = document.getElementById('c_m_s_a_image_input');
// c_m_s_a_image_input.onchange = () => {
//     const allowedExtensions = ['jpg', 'jpeg', 'png', 'gif'];
//     const maxFileSize = 2 * 1024 * 1024; // 2MB
//
//     if (c_m_s_a_image_input.files.length > 0) {
//         const file = c_m_s_a_image_input.files[0];
//         const fileName = file.name.toLowerCase();
//         const fileExtension = fileName.split('.').pop();
//
//         if (allowedExtensions.includes(fileExtension) && file.size <= maxFileSize) {
//             c_m_s_a_image_input.css('background-color','rgba(30,213,22,0.74)');
//             addtxtfld1=true;
//             triggerAddNewClientSaveBtn();//trigger to enable
//         } else {
//             c_m_s_a_image_input.css('background-color','rgba(200,23,23,0.68)');
//             addtxtfld1=false;
//             triggerAddNewClientSaveBtn();//trigger to enable
//         }
//     }
// };

//signupUsername key function
function usernameCheckClientMngRegex(username) {
    const regex = /^[a-zA-Z0-9_.-]{5,30}$/;
    return regex.test(username);
}
c_m_s_a_username.on('keyup', function () {

    if( usernameCheckClientMngRegex(c_m_s_a_username.val())){
        c_m_s_a_username.css('background-color','rgba(30,213,22,0.74)');
        addtxtfld2 = true;
        triggerAddNewClientSaveBtn();//trigger to enable
    }else {
        c_m_s_a_username.css('background-color','rgba(200,23,23,0.68)');
        addtxtfld2 = false;
        triggerAddNewClientSaveBtn();//trigger to enable
    }
});

function nameCheckClientMngRegex(name) {
    const regex =  /^[a-zA-Z0-9_.-]{4,30}$/;
    return regex.test(name);
}
c_m_s_a_nameinitial.on('keyup', function () {

    if(nameCheckClientMngRegex(c_m_s_a_nameinitial.val())){
        c_m_s_a_nameinitial.css('background-color','rgba(51,225,44,0.74)');
        addtxtfld3 = true;
        triggerAddNewClientSaveBtn();//trigger to enable
    }else {
        c_m_s_a_nameinitial.css('background-color','rgba(209,29,29,0.68)');
        addtxtfld3 = false;
        triggerAddNewClientSaveBtn();//trigger to enable
    }
});

function passwordCheckClientMngRegex(password) {
    const regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
    return regex.test(password);
}
c_m_s_a_password.on('keyup', function () {

    if(passwordCheckClientMngRegex(c_m_s_a_password.val())){
        c_m_s_a_password.css('background-color','rgba(44,223,36,0.74)');
        addtxtfld4 = true;
        triggerAddNewClientSaveBtn();//trigger to enable
    }else {
        c_m_s_a_password.css('background-color','rgba(213,14,14,0.68)');
        addtxtfld4 = false;
        triggerAddNewClientSaveBtn();//trigger to enable
    }
});


function emailCheckClientMngRegex(email) {
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return regex.test(email);
}
c_m_s_a_email.on('keyup', function () {

    if(emailCheckClientMngRegex(c_m_s_a_email.val())){
        c_m_s_a_email.css('background-color','rgba(41,202,35,0.74)');
        addtxtfld5 = true;
        triggerAddNewClientSaveBtn();
    }else {
        c_m_s_a_email.css('background-color','rgba(223,21,21,0.68)');
        addtxtfld5 = false;
        triggerAddNewClientSaveBtn();
    }
});

function addressCheckClientMngRegex(address) {
    const regex = /^\S+\s*[a-zA-Z0-9,.-]+\S{0,48}$/;
    return regex.test(address);
}
c_m_s_a_address.on('keyup', function () {

    if(addressCheckClientMngRegex(c_m_s_a_address.val())){
        c_m_s_a_address.css('background-color','rgba(30,223,22,0.74)');
        addtxtfld6 = true;
        triggerAddNewClientSaveBtn();//trigger to enable
    }else {
        c_m_s_a_address.css('background-color','rgba(193,21,21,0.68)');
        addtxtfld6 = false;
        triggerAddNewClientSaveBtn();//trigger to enable
    }
});

function nicCheckClientMngRegex(nic) {
    const regex = /^[a-zA-Z0-9_-]+\S{8,11}$/;
    return regex.test(nic);
}
c_m_s_a_nic.on('keyup', function () {

    if(nicCheckClientMngRegex(c_m_s_a_nic.val())){
        c_m_s_a_nic.css('background-color','rgba(31,213,23,0.74)');
        addtxtfld7= true;
        triggerAddNewClientSaveBtn();//trigger to enable
    }else {
        c_m_s_a_nic.css('background-color','rgba(218,13,13,0.68)');
        addtxtfld7 = false;
        triggerAddNewClientSaveBtn();//trigger to enable
    }
});
//
// const c_m_s_a_nic_front_image_input = document.getElementById('p_s_image_input');
// c_m_s_a_nic_front_image_input.onchange = () => {
//     const minvalidFeedback = $('#img-invalid-feedback');
//     const mvalidFeedback = $('#img-valid-feedback');
//     const allowedExtensions = ['jpg', 'jpeg', 'png', 'gif'];
//     const maxFileSize = 2 * 1024 * 1024; // 2MB
//
//     if (c_m_s_a_nic_front_image_input.files.length > 0) {
//         const file = c_m_s_a_nic_front_image_input.files[0];
//         const fileName = file.name.toLowerCase();
//         const fileExtension = fileName.split('.').pop();
//
//         if (allowedExtensions.includes(fileExtension) && file.size <= maxFileSize) {
//             c_m_s_a_nic_front_image_input.classList.remove('is-invalid');
//             profileImageInput.classList.add('is-valid');
//             mvalidFeedback.css('display','block');
//             minvalidFeedback.css('display','none');
//
//             txtfld7=true;
//
//             settingUpdateBtnTrigger();//trigger to enable
//         } else {
//             profileImageInput.classList.remove('is-valid');
//             profileImageInput.classList.add('is-invalid');
//             minvalidFeedback.css('display','block');
//             mvalidFeedback.css('display','none');
//
//             txtfld7=false;
//
//             settingUpdateBtnTrigger();//trigger to enable
//         }
//     }
// };
// //
// const c_m_s_a_nic_rear_image_input = document.getElementById('c_m_s_a_nic_rear_image_input');
// c_m_s_a_nic_rear_image_input.onchange = () => {
//     const allowedExtensions = ['jpg', 'jpeg', 'png', 'gif'];
//     const maxFileSize = 2 * 1024 * 1024; // 2MB
//
//     if (c_m_s_a_nic_rear_image_input.files.length > 0) {
//         const file = c_m_s_a_nic_rear_image_input.files[0];
//         const fileName = file.name.toLowerCase();
//         const fileExtension = fileName.split('.').pop();
//
//         if (allowedExtensions.includes(fileExtension) && file.size <= maxFileSize) {
//             c_m_s_a_nic_rear_image_input.css('background-color','rgba(30,213,22,0.74)');
//             addtxtfld9=true;
//             triggerAddNewClientSaveBtn();//trigger to enable
//         } else {
//             c_m_s_a_nic_rear_image_input.css('background-color','rgba(200,23,23,0.68)');
//             addtxtfld9=false;
//             triggerAddNewClientSaveBtn();//trigger to enable
//         }
//     }
// };

function genderCheckClientMngRegex(gender) {
    const regex =  /^[a-zA-Z0-9_.-]{4,30}$/;
    return regex.test(gender);
}
c_m_s_a_gender.on('keyup', function () {

    if(genderCheckClientMngRegex(c_m_s_a_gender.val())){
        c_m_s_a_gender.css('background-color','rgba(22,222,14,0.74)');
        addtxtfld10 = true;
        triggerAddNewClientSaveBtn();//trigger to enable
    }else {
        c_m_s_a_gender.css('background-color','rgba(229,28,28,0.68)');
        addtxtfld10 = false;
        triggerAddNewClientSaveBtn();//trigger to enable
    }
});

function ageCheckClientMngRegex(age) {
    const regex = /^\S+\s*[a-zA-Z0-9,.-]+\S{0,48}$/;
    return regex.test(age);
}
c_m_s_a_age.on('keyup', function () {

    if(ageCheckClientMngRegex(c_m_s_a_age.val())){
        c_m_s_a_age.css('background-color','rgba(28,222,19,0.74)');
        addtxtfld11 = true;
        triggerAddNewClientSaveBtn();//trigger to enable
    }else {
        c_m_s_a_age.css('background-color','rgba(241,20,20,0.68)');
        addtxtfld11 = false;
        triggerAddNewClientSaveBtn();//trigger to enable
    }
});

function telCheckClientMngRegex(tel) {
    const regex =  /^(?:\+94|0)[1-9]\d{8}$/;
    return regex.test(tel);
}
c_m_s_a_tell.on('keyup', function () {

    if(telCheckClientMngRegex(c_m_s_a_tell.val())){
        c_m_s_a_tell.css('background-color','rgba(28,222,19,0.74)');
        addtxtfld12 = true;
        triggerAddNewClientSaveBtn();//trigger to enable
    }else {
        c_m_s_a_tell.css('background-color','rgba(241,20,20,0.68)');
        addtxtfld12 = false;
        triggerAddNewClientSaveBtn();//trigger to enable
    }
});

function remarkCheckClientMngRegex(remark) {
    const regex =  /^[a-zA-Z0-9_.-]{4,30}$/;
    return regex.test(remark);
}
c_m_s_a_remarks.on('keyup', function () {

    if(remarkCheckClientMngRegex(c_m_s_a_remarks.val())){
        c_m_s_a_remarks.css('background-color','rgba(28,222,19,0.74)');
        addtxtfld13 = true;
        triggerAddNewClientSaveBtn();//trigger to enable
    }else {
        c_m_s_a_remarks.css('background-color','rgba(241,20,20,0.68)');
        addtxtfld13 = false;
        triggerAddNewClientSaveBtn();//trigger to enable
    }
});



function saveAddClient(){
    console.log("done");
    console.log(c_m_s_a_image_input);
    console.log(c_m_s_a_username);
    console.log(c_m_s_a_nameinitial);
    console.log(c_m_s_a_password);
    console.log(c_m_s_a_email);
    console.log(c_m_s_a_address);
    console.log(c_m_s_a_nic);
    console.log(c_m_s_a_nic_front_image_input);
    console.log(c_m_s_a_nic_rear_image_input)
    console.log(c_m_s_a_gender);
    console.log(c_m_s_a_age);
    console.log(c_m_s_a_tell);
    console.log(c_m_s_a_remarks);
}

function triggerAddNewClientSaveBtn(){
    if(
        addtxtfld1===true && addtxtfld2===true && addtxtfld3===true && addtxtfld4===true && addtxtfld5===true && addtxtfld6===true && addtxtfld7===true && addtxtfld8===true && addtxtfld9===true && addtxtfld10===true && addtxtfld11===true && addtxtfld12===true && addtxtfld13===true
    ){
        c_m_s_a_savebtn.prop("disabled", false);
    }else {
        c_m_s_a_savebtn.prop("disabled", true);
    }
}


