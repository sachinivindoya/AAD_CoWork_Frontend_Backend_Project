function GuideDTO(
    id,
    name,
    address,
    nic,
    tell,
    experience,
    dob,
    perdayfee,
    remarks,
    gender,
    profileimage,
    nicefrontimage,
    nicrearimage
) {
    var __guideID = id;
    var __guideName = name;
    var __guideAddress = address;
    var __guideNic = nic;
    var __guideTell = tell;
    var __guideExperience = experience;
    var __guideDob = dob;
    var __guidePerdayfee = perdayfee;
    var __guideRemakrs = remarks;
    var __guideGender = gender;
    var __guideProfileimage = profileimage;
    var __guideNicfrontimage = nicefrontimage;
    var __guideNicrearimage = nicrearimage;

    this.getGuideID = function () {
        return __guideID;
    };
    this.setGuideID = function (newID) {
        __guideID = newID;
    };

    this.getGuideName = function () {
        return __guideName;
    };
    this.setGuideName = function (newName) {
        __guideName = newName;
    };

    this.getGuideAddress = function () {
        return __guideAddress;
    };
    this.setGuideAddress = function (newAddress) {
        __guideAddress = newAddress;
    };

    this.getGuideNic = function () {
        return __guideNic;
    };
    this.setGuideNic = function (newNic) {
        __guideNic = newNic;
    };

    this.getGuideTell = function () {
        return __guideTell;
    };
    this.setGuideTell = function (newTell) {
        __guideTell = newTell;
    };

    this.getGuideExperience = function () {
        return __guideExperience;
    };
    this.setGuideExperience = function (newExperience) {
        __guideExperience = newExperience;
    };

    this.getGuideDob = function () {
        return __guideDob;
    };
    this.setGuideDob = function (newDob) {
        __guideDob = newDob;
    };

    this.getGuidePerdayfee = function () {
        return __guidePerdayfee;
    };
    this.setGuidePerdayfee = function (newPerdayfee) {
        __guidePerdayfee = newPerdayfee;
    };

    this.getGuideRemarks = function () {
        return __guideRemakrs;
    };
    this.setGuideRemarks = function (newRemarks) {
        __guideRemakrs = newRemarks;
    };

    this.getGuideGender = function () {
        return __guideGender;
    };
    this.setGuideGender = function (newGender) {
        __guideGender = newGender;
    };

    this.getGuideProfileimage = function () {
        return __guideProfileimage;
    };
    this.setGuideProfileimage = function (newProfileimage) {
        __guideProfileimage = newProfileimage;
    };

    this.getGuideNicfrontimage = function () {
        return __guideNicfrontimage;
    };
    this.setGuideNicfrontimage = function (newNicfrontimage) {
        __guideNicfrontimage = newNicfrontimage;
    };

    this.getGuideNicrearimage = function () {
        return __guideNicrearimage;
    };
    this.setGuideNicrearimage = function (newNicrearimage) {
        __guideNicrearimage = newNicrearimage;
    };

    this.toString = function () {
        console.log(
            `Guide Details: \nName: ${__guideName}\nAddress: ${__guideAddress}\nNIC: ${__guideNic}\nTelephone: ${__guideTell}\nExperience: ${__guideExperience}\nDOB: ${__guideDob}\nPer Day Fee: ${__guidePerdayfee}\nRemarks: ${__guideRemakrs}\nGender: ${__guideGender}\nProfile Image: ${__guideProfileimage}\nNIC Front Image: ${__guideNicfrontimage}\nNIC Rear Image: ${__guideNicrearimage}`
        );
    };
}