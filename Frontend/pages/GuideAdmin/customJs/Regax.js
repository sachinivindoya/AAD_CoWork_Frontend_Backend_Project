// function isSignUpEmailCheckedRegex(email) {
//     const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
//     return regex.test(email);
// }
//
// function isSignUpNameWithInitialCheckedRegex(name) {
//     const regex = /([A-Z])\w+\s([A-Z])\w*\s*([A-Z])*(?=,*)/;
//     return regex.test(name);
// }
//
// function isSignUpPasswordCheckedRegex(password) {
//     const regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
//     return regex.test(password);
// }
//
// function isSignUpNicOrPassportCheckedRegex(nic) {
//     const regex = /^[a-zA-Z0-9_-]+\S{8,11}$/;
//     return regex.test(nic);
// }
// function isSignUpNAddressCheckedRegex(addresse) {
//     const regex = /^\S+\s*[a-zA-Z0-9,.-]+\S{0,48}$/;
//     return regex.test(addresse);
// }

function isNameRegaxChecked(text) {
    const regex = /^[a-zA-Z0-9_.-]{5,30}$/;
    return regex.test(text);
    //Enter your name, which must be at least 4 characters long and contain only letters and spaces.
    // Ex:-John Doe / john doe
}

function isAddressRegaxChecked(text) {
    const regex = /^\S+\s*[a-zA-Z0-9,.-]+\S{0,48}$/;
    return regex.test(text);
    //Enter your address, which must be at least 4 characters long and contain only letters, commas, spaces like these examples.
    //Ex:- 123 Main,Street / 456 Elm Street, Apt. 7B / 789 Oak Lane, Suite
}

function isNICRegaxChecked(text) {
    const regex = /^([0-9]{9}[x|X|v|V]|[0-9]{12})$/;
    return regex.test(text);
    //Enter your valid Srilankan National ID card number.
    //Ex:- xxxxxxxxxxxx / xxxxxxxxxv
}

function isTellRegaxChecked(text) {
    const regex = /^(?:7|0|(?:\+94))[0-9]{9,10}$/;
    return regex.test(text);
    //Enter your srilankan telephone number, which must included pattern like these example.
    //Ex:- 0xxxxxxxxx / +94xxxxxxxxx
}

function isDOBRegaxChecked(text) {
    const regex = /^\d{4}-\d{2}-\d{2}$/;
    return regex.test(text);
    //Enter your Birthday, which must included pattern like these example.
    //Ex:- year-month-day
}

function isTextRegaxChecked(text) {
    const regex = /^[a-zA-Z0-9, ]+$/;
    return regex.test(text);
    //Enter your Remarks, which must include letters, numbers, commas, spaces,pullstop.
    //Ex:- lorem lorem.
}
function isOnlyNumberRegaxChecked(text) {
    const regex = /^(?=.*[0-9]).*$/;
    return regex.test(text);
    //Enter your valid Srilankan Telephone number.
    //Ex:- 10 / 4
}
function isGenderRegaxChecked(text) {
    const regex = /^(male|female|trans)$/;
    return regex.test(text);
}
function isNameWithInitialRegaxChecked(name) {
    const regex = /^[A-Za-z. ]+$/;
    return regex.test(name);
// *Enter valid Name with initial.
//     *It must contains dots , letters like these pattern.
//     *Ex : H.K.Pam Lowev / C.F.Kamal Perera
}

function isEmailRegaxChecked(email) {
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return regex.test(email);
}

function isPasswordRegaxChecked(password) {
    const regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
    return regex.test(password);
}
