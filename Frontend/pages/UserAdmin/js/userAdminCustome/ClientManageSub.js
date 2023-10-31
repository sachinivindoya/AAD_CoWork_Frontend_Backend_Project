

//-----Save New Client--------------
const c_m_s_a_username = $('#c_m_s_a_username');
const c_m_s_a_nameinitial = $('#c_m_s_a_nameinitial');
const c_m_s_a_password = $('#c_m_s_a_password');
const c_m_s_a_email = $('#c_m_s_a_email');
const c_m_s_a_address = $('#c_m_s_a_address');
const c_m_s_a_nic = $('#c_m_s_a_nic');
const c_m_s_a_image = $('#c_m_s_a_image');
const c_m_s_a_image_input = $('#c_m_s_a_image_input');
const c_m_s_a_nic_front_image = $('#c_m_s_a_nic_front_image');
const c_m_s_a_nic_rear_image = $('#c_m_s_a_nic_rear_image');
const c_m_s_a_gender = $('#c_m_s_a_gender');
const c_m_s_a_age = $('#c_m_s_a_age');
const c_m_s_a_tell = $('#c_m_s_a_tell');
const c_m_s_a_remarks = $('#c_m_s_a_remarks');
const c_m_s_a_nic_front_image_input = $('#c_m_s_a_nic_front_image_input');
const c_m_s_a_nic_rear_image_input = $('#c_m_s_a_nic_rear_image_input');
const c_m_s_a_savebtn = $('#c_m_s_a_savebtn');


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

//signupUsername key function
function usernameCheckClientMngRegex(username) {
    const regex = /^[a-zA-Z0-9_.-]{5,30}$/;
    return regex.test(username);
}
c_m_s_a_username.on('keyup', function () {

    if( usernameCheckClientMngRegex(c_m_s_a_username.val())){
        c_m_s_a_username.css('background-color','rgba(193, 237, 255, 0.68)');
        txtfld1 = true;
        triggerCreateAccBtn();//trigger to enable
    }else {
        c_m_s_a_username.css('background-color','rgba(255, 193, 193, 0.68)');
        txtfld1 = false;
        triggerCreateAccBtn();//trigger to enable
    }
});























function saveAddClient(){
    console.log("done");

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



c_m_s_a_username.on('keyup', function () {
    console.log("ddddd");
});
c_m_s_a_nameinitial.on('keyup', function () {
    console.log("ddddd");
});
c_m_s_a_password.on('keyup', function () {
    console.log("ddddd");
});
c_m_s_a_email.on('keyup', function () {
    console.log("ddddd");
});
c_m_s_a_address.on('keyup', function () {
    console.log("ddddd");
});
c_m_s_a_nic.on('keyup', function () {
    console.log("ddddd");
});
c_m_s_a_gender.on('keyup', function () {
    console.log("ddddd");
});
c_m_s_a_age.on('keyup', function () {
    console.log("ddddd");
});
c_m_s_a_tell.on('keyup', function () {
    console.log("ddddd");
});
c_m_s_a_remarks.on('keyup', function () {
    console.log("ddddd");
});

