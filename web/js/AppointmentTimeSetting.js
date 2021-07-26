window.onload = initEvents()

var calendar = $('#calendar').data('zui.calendar');
$('#calendar').calendar({
    clickCell: function (event) {
        today = new Date()
        val = event.date //点击日历当天
        s = dateFormat(val)
        t = dateFormat(today)
        todayString = event.date.toString()
        todayDate = todayString.substr(0, 16) //当前日期
        console.log(event.date.toString())
        console.log(event.element)
        console.log("初始化时间:" + s + "今天时间" + t)
        object = event;

        getOrderTime();

        if (judgeDate(s, t)) {
            layer.msg("无法预约")
        } else {
            $('#myModal').modal({
                keyboard: false,
                show: true
            })
        }
    },

    clickEvent: function (event) {
        console.log(event.event.start);
        console.log(event.element);
        var a = event.event.start.toString()
        var b = dateFormat(event.event.start)
        console.log(a.substring(16, 25))
        console.log(b + " " + a.substring(16, 24));
        var c = b + " " + a.substring(16, 25)
        layer.confirm("选择操作", {
            btn: ['删除', '取消'] //按钮
        }, function () {
            $.ajax({
                url: 'AppointmentTimeDelEventServlet',
                type: 'post',
                data: {
                    'EventTime': c
                },
                async: true,
                dataType: 'JSON',
                success: function (resp) {
                    layer.msg(resp.message, {icon: 1});
                    initEvents();
                },
                error: function (data) {
                    console.log("联系管理员")
                }
            })

        }, function () {
            layer.msg('取消', {icon: 1});
        });

    },

})

$("#subEvents").click(function (object) {
    var eles = document.getElementsByClassName("checkboxItem");
    orderTime = [];
    for (var i = eles.length - 1; i >= 0; i--) {
        if (eles[i].checked == true) {
            orderTime.push(dateFormat(todayDate) + " " + eles[i].value + ":00:00")
        }
    }
    console.log("预定时间" + orderTime)
    $.ajax({
        url: 'AppointmentTimeServlet',
        type: 'post',
        data: {
            'orderTime[]': orderTime,
        },
        async: true,
        dataType: 'JSON',
        success: function (resp) {
            initEvents();
            layer.msg(resp.message);
        },
        error: function (data) {
            console.log("联系管理员")
        }
    })
})

function judgeDate(s, t) {
    if (s < t) {
        console.log(s)
        console.log(t)
        console.log("无法预约")
        return true
    }
}

function dateFormat(dateData) {
    var date = new Date(dateData)
    var y = date.getFullYear()
    var m = date.getMonth() + 1
    m = m < 10 ? ('0' + m) : m
    var d = date.getDate()
    d = d < 10 ? ('0' + d) : d
    const time = y + '-' + m + '-' + d
    return time
}

// function addEventsEvent(todayDate, count) {
//     var a = todayDate + " " + count + ":00:00 GMT+0800 (中国标准时间)"
//     console.log("a:" + a);
//     var calendar = $('#calendar').data('zui.calendar');
//     var newEvent = {
//         title: '预约',
//         start: a,
//         end: a
//     };
//     calendar.addEvents(newEvent);
// }

function getOrderTime() {
    var eles = document.getElementsByClassName("checkboxItem");
    for (var i = eles.length - 1; i >= 0; i--) {
        var a = todayDate + " " + eles[i].value + ":00:00 GMT+0800 (中国标准时间)"
        var b = dateFormat(a)
        if (b == t) {
            if (eles[i].value <= today.getHours()) {
                eles[i].setAttribute("disabled", "true")
            }
        } else {
            eles[i].removeAttribute("disabled")
        }
    }
}

function initEvents() {
    console.log("初始化事件")
    $.ajax({
        url: 'AppointmentTimeInitEvents',
        type: 'post',
        async: true,
        dataType: 'JSON',
        success: function (resp) {
            var calendar = $('#calendar').data('zui.calendar');
            calendar.resetData({
                calendars: {},
                events: []
            });
            orderArr = resp.datas.calendarInfos;
            console.log(orderArr)
            // var calendar = $('#calendar').data('zui.calendar');
            if (orderArr.length > 0) {
                for (var o of orderArr) {
                    var newEvent = {
                        title: "预约",
                        start: o.calendarTime,
                        end: o.calendarTime
                    };
                    calendar.addEvents(newEvent);
                }
            }
        },
        error: function (data) {
            console.log("联系管理员")
        }
    })
}



