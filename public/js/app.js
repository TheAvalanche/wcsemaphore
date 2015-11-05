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
        url: "example.json",
        success: function (data) {
            for (var key in data.wc) {
                var obj = data.wc[key];
                $("#" + obj.id).removeClass();
                $("#" + obj.id).addClass(obj.status);
            }
        }
    });
}

function ajax_response(response) {
    return function (params) {
        params.success(response);
    };
}

$.ajax = ajax_response({"wc" : [{"id":"wc1-1","status":"closed"}, {"id":"wc1-2","status":"opened"}, {"id":"wc1-3","status":"closed"}]});
doAsyncThing();
