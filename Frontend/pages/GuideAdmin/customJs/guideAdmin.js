
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