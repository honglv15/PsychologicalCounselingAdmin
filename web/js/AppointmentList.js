limit = 0
offset = 5
page = 5;//查询的数据条数
count = 0; // 一共多少条数据
currPage = 1; // 当前第几页
totalPage = 0; // 一共多少页
Stime = ""
Etime = ""
State = 0

window.onload = GetOrderTable()
$("#SearchBtn").click(function () {
    Stime = $("#Stime").val();
    Etime = $("#Etime").val()
    State = $("#state").val()
    GetOrderTable()
})


function GetOrderTable() {
    Stime = $("#Stime").val();
    Etime = $("#Etime").val()
    State = $("#state").val()
    $.ajax({
        url: 'GetOrderTableServlet',
        type: 'post',
        data: {
            'Stime': Stime,
            'Etime': Etime,
            'State': State,
            'limit': limit,
            'offset': offset
        },
        async: true,
        dataType: 'JSON',
        success: function (resp) {
            count = resp.datas.countOrderTableCount;
            totalPage = count % page == 0 ? count / page : parseInt(count / page + 1);
            var tablepage = document.getElementById('tablepage');
            tablepage.innerText = currPage + "/" + totalPage
            console.log(currPage + "/" + totalPage)
            arr = resp.datas.orderTable;
            console.log(arr)
            mybody.innerHTML = ""
            for (let i = 0; i < arr.length; i++) {
                console.log("遍历表格数组")
                if (arr[i].stateName == '已预约') {
                    bookedBtn = "确认预约"
                    style = ""
                } else if (arr[i].stateName == '已确认') {
                    bookedBtn = "诊断"
                    style = ""
                } else {
                    bookedBtn = ""
                    style = "display:none"
                }
                mybody.innerHTML += `<tr style="text-align: center">
                                <td>${arr[i].orderTime}</td>
                               <td>${arr[i].userAccount}</td>
                           <td>${arr[i].fieldName}</td>
                    <td>${arr[i].orderProblem}</td>
                   <td>${arr[i].stateName}</td>
                    <td>
                        <button type="button" id="bookedBtn" class="btn btn-primary" orderProblem="${arr[i].orderProblem}"  orderTime="${arr[i].orderTime}" userId="${arr[i].userId}" userAccount="${arr[i].userAccount}"  fieldName="${arr[i].fieldName}" value="${bookedBtn}" style="${style}">${bookedBtn}</button>
                        <button type="button" id="DetailBtn" class="btn " userId="${arr[i].userId}" orderTime="${arr[i].orderTime}" value="查看详情"}>查看详情
                        </button>
                    </td>
                </tr>`
            }
        }
    })
}

$("#mybody").click(function (event) {
    var input = event.target;
    var orderTime = input.getAttribute("orderTime");
     userId = input.getAttribute("userId");
    var fieldName = input.getAttribute("fieldName");
    var userAccount = input.getAttribute("userAccount");
    var orderProblem = input.getAttribute("orderProblem");
    console.log(input)
    console.log(orderTime + userId)
    if (input.value == '确认预约') {
        $.ajax({
            url: 'ConfirmAppointmentServlet',
            type: 'post',
            data: {
                'orderTime': orderTime,
                'userId': userId
            },
            async: true,
            dataType: 'JSON',
            success: function (rep) {
                layer.msg(rep.message)
                GetOrderTable()
            },
            error: function (date) {
                layer.msg("请联系管理员")
            }

        })
    } else if (input.value == "诊断") {
        var referee = document.getElementById("referee");
        var field = document.getElementById("field");
        var oTime = document.getElementById("orderTime");
        var ProblemTxt = document.getElementById("ProblemTxt");
        referee.setAttribute("placeholder", userAccount)
        field.setAttribute("placeholder", fieldName)
        oTime.setAttribute("placeholder", orderTime)
        ProblemTxt.innerText = orderProblem

        console.log("userId" + userId)
        $("#submitBtn").attr("userId", userId);

        $('#myModal').modal({
            keyboard: false,
            show: true
        })
    } else if (input.value == "查看详情") {
        console.log("查看详情")
        $.ajax({
            url: 'AppointmentDetailServlet',
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
            error: function (date) {
                layer.msg("请联系管理员")
            }

        })

        $('#DetailModal').modal({
            keyboard: false,
            show: true
        })
    }
})

$("#submitBtn").click(function () {
    var myAnswer = $("#myAnswer").val();
    // var userId = $("#submitBtn").attr("userId");
    console.log(myAnswer + " " + userId)
    $.ajax({
        url: 'DiagnosisUserServlet',
        type: 'post',
        data: {
            'myAnswer': myAnswer,
            'userId': userId
        },
        async: true,
        dataType: 'JSON',
        success: function (resp) {
            layer.msg(resp.message)
            GetOrderTable();
        },
        error: function (resp) {
            layer.msg("联系管理员")
        }
    })


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