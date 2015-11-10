$(".main").onepage_scroll({
    sectionContainer: "section",
    easing: "ease",
    animationTime: 1000,
    pagination: true,
    updateURL: false,
    beforeMove: function (index) {
    },
    afterMove: function (index) {
    },
    loop: false,
    keyboard: true,
    responsiveFallback: false,
    direction: "vertical"
});




function doAsyncThing() {
    $.ajax({
        url: "../wcdetails/",
        success: function (data) {
            for (var key in data) {
                var obj = data[key];
                $("#" + obj.id).removeClass();
                $("#" + obj.id).addClass(obj.status);
            }
        },
        complete: function () {
            setTimeout(function () {
                doAsyncThing();
            }, 1000);
        }
    });
}

doAsyncThing();
