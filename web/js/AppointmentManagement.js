limit = 0
offset = 5
page = 5;//查询的数据条数
count = 0; // 一共多少条数据
currPage = 1; // 当前第几页
totalPage = 0; // 一共多少页

consult = ""
userAccount = ""
Stime = ""
Etime = ""

window.onload = initTable();

function initTable() {
    console.log("initTable")

    consult = $("#consult").val();
    userAccount = $("#user").val();
    Stime = $("#Stime").val();
    Etime = $("#Etime").val();

    $.ajax({
        url: 'AppointmentManagement',
        type: 'post',
        data: {
            'consult': consult,
            'userAccount': userAccount,
            'Stime': Stime,
            'Etime': Etime,
            'limit': limit,
            'offset': offset
        },
        async: true,
        dataType: 'JSON',
        success: function (resq) {
            arr = resq.datas.appointmentManagementQos
            console.log(arr)
            mybody.innerHTML = ``
            for (let i = 0; i < arr.length; i++) {
                if (arr[i].stateName == "已预约") {
                    style = ""
                } else {
                    style = "display:none"
                }
                mybody.innerHTML += `<tr align="center">
        <td>${arr[i].orderTime}</td>
        <td>${arr[i].adminAccount}</td>
        <td>${arr[i].userAccount}</td>
        <td>${arr[i].fieldName}</td>
        <td>${arr[i].commentTime}</td>
        <td>${arr[i].stateName}</td>
        <td>
            <button type="button" id="delBtn" class="btn btn-primary"  value="查看详情" userId="${arr[i].userId}" orderTime="${arr[i].orderTime}">查看详情</button>
            <button type="button" id="resetBtn" class="btn btn-danger" value="终止预约" style="${style}" userId="${arr[i].userId}" orderTime="${arr[i].orderTime}" data-target="#DetailManage">终止预约</button>
        </td>
    </tr>`

            }
        },
        error: function () {
            layer.msg("请联系管理员")
        }
    })
}

$("#SearchBtn").click(function () {
    initTable()
})


$("#mybody").click(function (event) {
    var input = event.target;
    console.log(input)
    userId = input.getAttribute("userId");
    orderTime = input.getAttribute("orderTime");
    if (input.value == "终止预约") {
        console.log("终止预约")
        $.ajax({
            url: 'TerminateAppointmentServlet',
            type: 'post',
            data: {
                'orderTime': orderTime,
                'userId': userId
            },
            async: true,
            dataType: 'JSON',
            success: function (resq) {
                initTable()
            },
            error: function (resq) {
                layer.msg("联系管理员")
            }
        })

    } else if (input.value == "查看详情") {
        console.log("查看详情")

        $.ajax({
            url: 'TerminateAppointmentViewDetailsServlet',
            type: 'post',
            data: {
                'orderTime': orderTime,
                'userId': userId
            },
            async: true,
            dataType: 'JSON',
            success: function (resp) {
                arr = resp.datas.orderInfos;
                console.log(arr[0])
                $("#advisoryD").attr("placeholder", "")
                $("#fieldD").attr("placeholder", "")
                $("#oTimeD").attr("placeholder", "")
                $("#ReservationStatusD").attr("placeholder", "")
                $("#feeD").attr("placeholder", "")
                $("#problemD").attr("placeholder", "")
                $("#replyD").attr("placeholder", "")
                $("#replyTime").attr("placeholder", "")
                $("#consultNameD").attr("placeholder", "")
                $("#contents").attr("placeholder", "")

                $("#advisoryD").attr("placeholder", arr[0].userAccount)
                $("#fieldD").attr("placeholder", arr[0].fieldName)
                $("#oTimeD").attr("placeholder", arr[0].orderTime)
                $("#ReservationStatusD").attr("placeholder", arr[0].stateName)
                $("#feeD").attr("placeholder", arr[0].stateName)
                $("#problemD").attr("placeholder", arr[0].orderProblem)
                $("#replyD").attr("placeholder", arr[0].orderReply)
                $("#replyTime").attr("placeholder", arr[0].replyTime)
                $("#consultNameD").attr("placeholder", arr[0].adminAccount)
                $("#contents").attr("placeholder", arr[0].commentContent)
            },
            error: function (resq) {
                layer.msg("联系管理员")
            }
        })

        $("#DetailManage").modal({
            keyboard: false,
            show: true
        })
    }
})

$("#prePage").click(function () {
    if (currPage > 1) {
        currPage--;
        limit -= 5;
        offset -= 5;
        GetOrderTable();
        console.log("上一页")
    } else {
        layer.msg("没有上一页了")
    }
})
$("#nextPage").click(function () {
    if (currPage < totalPage) {
        currPage++;
        console.log("下一页")
        limit += 5;
        offset += 5;
        GetOrderTable()
    } else {
        layer.msg("没有下一页了")
    }
})

$(".form-datetime").datetimepicker(
    {
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        format: "yyyy-mm-dd hh:ii"
    });