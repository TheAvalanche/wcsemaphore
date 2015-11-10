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
            calculateFree(data);
        },
        complete: function () {
            setTimeout(function () {
                doAsyncThing();
            }, 1000);
        }
    });
}

doAsyncThing();

function calculateFree(data) {
    var floor1Count = 0;
    var floor2Count = 0;
    var floor3Count = 0;
    for (var key in data) {
        if (data[key].status == "opened") {
            if (stringStartsWith(data[key].id, "wc1")) {
                floor1Count++;
            } else if (stringStartsWith(data[key].id, "wc2")) {
                floor2Count++;
            } else if (stringStartsWith(data[key].id, "wc3")) {
                floor3Count++;
            }
        }

    }
    $("#counter1").text(floor1Count);
    $("#counter2").text(floor2Count);
    $("#counter3").text(floor3Count);
}

function stringStartsWith (str, prefix) {
    if (str.length < prefix.length)
        return false;
    for (var i = prefix.length - 1; (i >= 0) && (str[i] === prefix[i]); --i)
        continue;
    return i < 0;
}
