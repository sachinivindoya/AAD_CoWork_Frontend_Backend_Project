
//time
const g_a_time = $('#g_a_time');

//--------------------------------- Set time to UI------------------------------------
$(document).ready(function(){
    // Function to update the time
    function updateTime() {
        const now = new Date();
        const formattedDate = now.toLocaleString();
        g_a_time.text(formattedDate);
    }
    updateTime();
    setInterval(updateTime, 1000);
});


//--------------update profile Settings---------
const p_s_username = $('#p_s_username');
const p_s_email = $('#p_s_email');
const p_s_nameinitial = $('#p_s_nameinitial');
const p_s_nic = $('#p_s_nic');
const p_s_address = $('#p_s_address');
const p_s_image = $('#p_s_image')
const p_s_password = $('#p_s_password');

let pstxtfld1 = false;
let pstxtfld2 = false;
let pstxtfld3 = false;
let pstxtfld4 = false;
let pstxtfld5 = false;
let pstxtfld6 = false;
let pstxtfld7 = true; //image


//validations

function isP_S_UsernameCheckRegex(username) {
    const regex = /^[a-zA-Z0-9_.-]{5,30}$/;
    return regex.test(username);
}

p_s_username.on('keyup', function () {
    if(isP_S_UsernameCheckRegex(p_s_username.val())){
        p_s_username.removeClass('is-invalid');
        p_s_username.addClass('is-valid');
        pstxtfld1 = true;
        settingUpdateBtnTrigger();
    }else{
        p_s_username.addClass('is-invalid');
        p_s_username.removeClass('is-valid');
        pstxtfld1 = false;
        settingUpdateBtnTrigger();
    }
});

function isP_S_nameCheckRegex(text) {
    const regex = /^[a-zA-Z0-9_.-]{5,30}$/;
    return regex.test(text);
    //Enter your name, which must be at least 4 characters long and contain only letters and spaces.
    // Ex:-John Doe / john doe
}
p_s_nameinitial.on('keyup', function () {
    if(isP_S_nameCheckRegex(p_s_nameinitial.val())){
        p_s_nameinitial.removeClass('is-invalid');
        p_s_nameinitial.addClass('is-valid');
        pstxtfld2=true;
        settingUpdateBtnTrigger();
    }else{
        p_s_nameinitial.addClass('is-invalid');
        p_s_nameinitial.removeClass('is-valid');
        pstxtfld2=false;
        settingUpdateBtnTrigger();
    }

});

 function isP_S_emailCheckRegex(email) {
     const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
     return regex.test(email);
}
p_s_email.on('keyup', function () {
    if(isP_S_emailCheckRegex(p_s_email.val())){
        p_s_email.removeClass('is-invalid');
        p_s_email.addClass('is-valid');
        pstxtfld3=true;
        settingUpdateBtnTrigger();
    }else{
        p_s_email.addClass('is-invalid');
        p_s_email.removeClass('is-valid');
        pstxtfld3=false;
        settingUpdateBtnTrigger();
    }
});

function isP_S_addressCheckRegex(text) {
    const regex = /^\S+\s*[a-zA-Z0-9,.-]+\S{0,48}$/;
    return regex.test(text);
}

p_s_address.on('keyup', function () {
    if(isP_S_addressCheckRegex(p_s_address.val())){
        p_s_address.removeClass('is-invalid');
        p_s_address.addClass('is-valid');
        pstxtfld4=true;
        settingUpdateBtnTrigger();
    }else{
        p_s_address.addClass('is-invalid');
        p_s_address.removeClass('is-valid');
        pstxtfld4=false;
        settingUpdateBtnTrigger();
    }
});


function isP_S_nicCheckRegex(text) {
    const regex = /^([0-9]{9}[x|X|v|V]|[0-9]{12})$/;
    return regex.test(text);
}

p_s_nic.on('keyup', function () {
    if(isP_S_nicCheckRegex(p_s_nic.val())){
        p_s_nic.removeClass('is-invalid');
        p_s_nic.addClass('is-valid');
        pstxtfld5=true;
        settingUpdateBtnTrigger();
    }else{
        p_s_nic.addClass('is-invalid');
        p_s_nic.removeClass('is-valid');
        pstxtfld5=false;
        settingUpdateBtnTrigger();
    }
});

 function isP_S_passwordCheckRegex(password) {
    const regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
    return regex.test(password);
}

p_s_password.on('keyup', function () {
    if(isP_S_passwordCheckRegex(p_s_password.val())){
        p_s_password.removeClass('is-invalid');
        p_s_password.addClass('is-valid');
        pstxtfld6=true;
        settingUpdateBtnTrigger();
    }else{
        p_s_password.addClass('is-invalid');
        p_s_password.removeClass('is-valid');
        pstxtfld6=false;
        settingUpdateBtnTrigger();
    }
});

const profileImageInput = document.getElementById('p_s_image_input');
profileImageInput.onchange = () => {
    const minvalidFeedback = $('#img-invalid-feedback');
    const mvalidFeedback = $('#img-valid-feedback');
    const allowedExtensions = ['jpg', 'jpeg', 'png', 'gif'];
    const maxFileSize = 2 * 1024 * 1024; // 2MB


    if (profileImageInput.files.length > 0) {
        const file = profileImageInput.files[0];
        const fileName = file.name.toLowerCase();
        const fileExtension = fileName.split('.').pop();

        if (allowedExtensions.includes(fileExtension) && file.size <= maxFileSize) {
            profileImageInput.classList.remove('is-invalid');
            profileImageInput.classList.add('is-valid');
            mvalidFeedback.css('display', 'block');
            minvalidFeedback.css('display', 'none');
            pstxtfld7 = true;

            settingUpdateBtnTrigger();//trigger to enable
        } else {
            profileImageInput.classList.remove('is-valid');
            profileImageInput.classList.add('is-invalid');
            minvalidFeedback.css('display', 'block');
            mvalidFeedback.css('display', 'none');
            pstxtfld7 = false;
            settingUpdateBtnTrigger();//trigger to enable
        }
    }
};

//------- profile setting image set ------------
$(document).ready(function() {
    $('#p_s_image_input').on('change', function () {
        var newImageFile = this.files[0]; // Get the file when a change occurs in the input

        if (newImageFile) { // Check if a file is selected
            var reader = new FileReader();
            var baseString;
            reader.onload = function () {
                baseString = reader.result;

                // Update the 'src' attribute of the image element
                p_s_image.attr('src', baseString);
            };
            reader.readAsDataURL(newImageFile);
        } else {
            console.error('No file selected');
        }
    });
});

function settingUpdateBtnTrigger(){
    if(pstxtfld1===true && pstxtfld2===true && pstxtfld3===true && pstxtfld4===true && pstxtfld5===true && pstxtfld6===true && pstxtfld7===true){
        // All conditions are true, enable the signup button
        $('#p_s_updateSettingbtn').prop("disabled", false);
    }else{
        $('#p_s_updateSettingbtn').prop("disabled", true);
    }
}
//------------------ update on backend Settings

function saveUpdatedProfileSettings() {

}

//----------------- navigation

const dashboard_container_guide = $("#dashboard_container_guide");
const guide_container = $("#guide_container");
const reports_container = $("#reports_container");
const calenders_container = $("#calenders_container");

function hideAllContainers(){
    dashboard_container_guide.css('display','none');
    guide_container.css('display','none');
    reports_container.css('display','none');
    calenders_container.css('display','none');
}

 function openHomeContainer(){
    hideAllContainers();
     dashboard_container_guide.css('display','block');
}

function openGuideContainer(){
    hideAllContainers();
    guide_container.css('display','block');
}

function openReportsContainer(){
    hideAllContainers();
    reports_container.css('display','block');
}

function openCalenderContainer(){
    hideAllContainers();
    calenders_container.css('display','block');
}


//---------------------------------------------New Guide container - add guide from - save Btn Clicked send data into server-----------------------------

//add guide
const g_a_a_name = $('#g_a_a_name');
const g_a_a_address = $('#g_a_a_address');
const g_a_a_nic = $('#g_a_a_nic');
const g_a_a_tell = $('#g_a_a_tell');
const g_a_a_experience = $('#g_a_a_experience');
const g_a_a_age = $('#g_a_a_age');
const g_a_a_perdayfee = $('#g_a_a_perdayfee');
const g_a_a_remarks = $('#g_a_a_remarks');
const g_a_a_gender = $('#g_a_a_gender');
const g_a_a_image = $('#g_a_a_image');
const g_a_a_image_input = $('#g_a_a_image_input');
const g_a_a_nic_front_image = $('#g_a_a_nic_front_image');
const g_a_a_nic_front_image_input = $('#g_a_a_nic_front_image_input');
const g_a_a_nic_rear_image = $('#g_a_a_nic_rear_image');
const g_a_a_nic_rear_image_input = $('#g_a_a_nic_rear_image_input');
const g_a_a_savebtn = $('#g_a_a_savebtn');
var guideImage_Base64String = "";
var guidNICFrontImage_Base64String = "";
var guidNOCRearImage_Base64String = "";


$(document).ready(function() {
    g_a_a_image_input.on('change', function () {
        var newImageFile = this.files[0]; // Get the file when a change occurs in the input
        if (newImageFile) { // Check if a file is selected
            var reader = new FileReader();
            var baseString;
            reader.onload = function () {
                baseString = reader.result;
                g_a_a_image.attr('src', baseString);
                guideImage_Base64String = baseString;
            };
            reader.readAsDataURL(newImageFile);
        } else {
            console.error('No file selected');
        }
    });
});


//----------------------------------New Guide container -add guide from - guide ID front view image set to view if select using base64--------------------
$(document).ready(function() {
    g_a_a_nic_front_image_input.on('change', function () {
        var newImageFile = this.files[0]; // Get the file when a change occurs in the input
        if (newImageFile) { // Check if a file is selected
            var reader = new FileReader();
            var baseString;
            reader.onload = function () {
                baseString = reader.result;
                g_a_a_nic_front_image.attr('src', baseString);
                guidNICFrontImage_Base64String= baseString;
            };
            reader.readAsDataURL(newImageFile);
        } else {
            console.error('No file selected');
        }
    });
});


//----------------------------------New Guide container -add guide from - guide ID back view image set to view if select using base64--------------------
$(document).ready(function() {
    g_a_a_nic_rear_image_input.on('change', function () {
        var newImageFile = this.files[0]; // Get the file when a change occurs in the input
        if (newImageFile) { // Check if a file is selected
            var reader = new FileReader();
            var baseString;
            reader.onload = function () {
                baseString = reader.result;
                g_a_a_nic_rear_image.attr('src', baseString);
                guidNOCRearImage_Base64String = baseString;
            };
            reader.readAsDataURL(newImageFile);
        } else {
            console.error('No file selected');
        }
    });
});



//----------------------------------New Guide container -add guide from - validation all fields-------------------
let g_a_a_fld_1 = false;
let g_a_a_fld_2 = false;
let g_a_a_fld_3 = false;
let g_a_a_fld_4 = false;
let g_a_a_fld_5 = false;
let g_a_a_fld_6 = false;
let g_a_a_fld_7 = false;
let g_a_a_fld_8 = false;
let g_a_a_fld_9 = false;
let g_a_a_fld_10 = false;
let g_a_a_fld_11 = false;
let g_a_a_fld_12 = false;


//validate txt flds
g_a_a_name.on('keyup', function () {
    if(isP_S_nameCheckRegex(g_a_a_name.val())){
        g_a_a_name.removeClass('is-invalid');
        g_a_a_name.addClass('is-valid');
        g_a_a_fld_1=true;
        addGuideSaveBtnIsEnableTrigger();
    }else{
        g_a_a_name.addClass('is-invalid');
        g_a_a_name.removeClass('is-valid');
        g_a_a_fld_1=false;
        addGuideSaveBtnIsEnableTrigger();
    }
});


g_a_a_address.on('keyup', function () {
    if(isP_S_addressCheckRegex(g_a_a_address.val())){
        g_a_a_address.removeClass('is-invalid');
        g_a_a_address.addClass('is-valid');
        g_a_a_fld_2=true;
        addGuideSaveBtnIsEnableTrigger();
    }else{
        g_a_a_address.addClass('is-invalid');
        g_a_a_address.removeClass('is-valid');
        g_a_a_fld_2=false;
        addGuideSaveBtnIsEnableTrigger();
    }
});

g_a_a_nic.on('keyup', function () {
    if(isP_S_nicCheckRegex(g_a_a_nic.val())){
        g_a_a_nic.removeClass('is-invalid');
        g_a_a_nic.addClass('is-valid');
        g_a_a_fld_3=true;
        addGuideSaveBtnIsEnableTrigger();
    }else{
        g_a_a_nic.addClass('is-invalid');
        g_a_a_nic.removeClass('is-valid');
        g_a_a_fld_3=false;
        addGuideSaveBtnIsEnableTrigger();
    }
});








function addGuideSaveBtnIsEnableTrigger(){
    if(g_a_a_fld_1===true && g_a_a_fld_2===true && g_a_a_fld_3===true && g_a_a_fld_4===true && g_a_a_fld_5===true && g_a_a_fld_6===true && g_a_a_fld_7===true && g_a_a_fld_8===true && g_a_a_fld_9===true && g_a_a_fld_10===true&& g_a_a_fld_11===true && g_a_a_fld_12===true){
        g_a_a_savebtn.prop("disabled", false);
    }else {
        g_a_a_savebtn.prop("disabled", true);
    }
}







function saveNewGuideBtnClicked(){

    var newGuideDTO = new GuideDTO(
        "U001",
        g_a_a_name.val(),
        g_a_a_address.val(),
        g_a_a_nic.val(),
        g_a_a_tell.val(),
        g_a_a_experience.val(),
        g_a_a_age.val(),
        g_a_a_perdayfee.val(),
        g_a_a_remarks.val(),
        g_a_a_gender.val(),
        guideImage_Base64String,
        guidNICFrontImage_Base64String,
        guidNOCRearImage_Base64String
    );

    //print console
    newGuideDTO.toString();

    //add into db testing for view table load data
    GuideObjsLocalDB.push(newGuideDTO);

}