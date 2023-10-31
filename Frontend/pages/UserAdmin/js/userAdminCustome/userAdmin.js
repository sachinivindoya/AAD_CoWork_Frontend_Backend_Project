
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

//-----logout-----------
function UserManageConsoleLogout(){
    console.log("logout clixked");
}