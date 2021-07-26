AdmNameSearch = ''
AdmTitle = '';
AdmIfDel = 0;
limit = 0; // 开始为止
offset = 5; // 每页显示多少条
page = 5;//查询的数据条数
count = 0; // 一共多少条数据
currPage = 1; // 当前第几页
totalPage = 0; // 一共多少页
window.onload = BgUserInfoGetTable();

function BgUserInfoGetTable() {
    console.log("window.onload");
    console.log(limit + " " + offset)
    console.log("搜索")
    var $ifStart = $("#ifStart").val()
    var titleSelect = $("#titleSelect").val()
    console.log($ifStart)
    $.ajax({
        url: 'BgUserInfoGetTableServlet',
        type: 'post',
        data: {
            'limit': limit,
            'offset': offset
        },
        async: true,
        dataType: 'JSON',
        success: function (resp) {
            console.log(resp);
            count = resp.datas.userCount;
            totalPage = count % page == 0 ? count / page : parseInt(count / page + 1);
            var tablepage = document.getElementById('tablepage');
            tablepage.innerText = currPage + "/" + totalPage
            console.log(currPage + "/" + totalPage)
            console.log(tablepage.innerText)

            arr = resp.datas.userList;
            var mybody = document.getElementById('mybody');
            mybody.innerText = '';
            console.log(arr)
            console.log(mybody + "表格")
            for (var i = 0; i < arr.length; i++) {
                mybody.innerHTML += `<tr align="center">
        <td>${arr[i].adminAccount}</td>
        <td>${arr[i].titleName}</td>
        <td>${arr[i].consultSchool}</td>
        <td> ${jugestate(arr[i].adminStatue)}</td>
        <td>
            <button type="button" id="delBtn" class="btn btn-danger"  value="删除">删除</button>
            <button type="button"  id="IfPanBtn" class="btn " value="启用/禁用"}>启用/禁用
            </button>
            <button type="button" id="resetBtn" class="btn btn-primary" onclick="test1(this)" value="重置密码">重置密码</button>
        </td>
    </tr>`
            }
        },
        error: function (data) {
            console.log("联系管理员")
        }
    })
}

$("#prePage").click(function () {
    if (currPage > 1) {
        currPage--;
        limit -= 5;
        offset -= 5;
        BgUserInfoGetTable();
        console.log("上一页")
    } else {
        alert("没有上一页了")
    }
})
$("#nextPage").click(function () {
    if (currPage < totalPage) {
        currPage++;
        console.log("下一页")
        limit += 5;
        offset += 5;
        BgUserInfoGetTable()
    } else {
        alert("没有下一页了")
    }
})

$("#mybody").click(function (event) {
    var input = event.target
    console.log(input)
    eles = input.parentElement.parentElement.children;
    console.log(eles[0].innerText)
    var Adm = eles[0].innerText;
    if (input.value == "启用/禁用") {
        layer.confirm('是否对该用户操作', {
            btn: ['禁用', '启用'] //按钮
        }, function () {
            var AdmStatus = 1;
            $.ajax({
                url: "ModifyAdmStatusServelet?date=" + new Date().getTime(),
                type: 'post',
                data: {
                    'AdmAccount': Adm,
                    'AdmStatus': AdmStatus,
                },
                async: true,
                dataType: 'JSON',
                success: function (resp) {
                    console.log(resp.message)
                    eles = input.parentElement.children
                    console.log(eles[1])
                    console.log("value的值" + eles[1].value)
                    layer.msg('禁用成功')
                    BgUserInfoGetTable();
                },
                error: function (data) {
                    console.log("联系管理员")
                }
            })
        }, function () {
            var AdmStatus = 0;
            $.ajax({
                url: "ModifyAdmStatusServelet?date=" + new Date().getTime(),
                type: 'post',
                data: {
                    'AdmAccount': Adm,
                    'AdmStatus': AdmStatus,
                },
                async: true,
                dataType: 'JSON',
                success: function (resp) {
                    console.log(resp.message)
                    eles = input.parentElement.children
                    console.log(eles[1])
                    console.log("value的值" + eles[1].value)
                    layer.msg('启用成功')
                    // eles[1].attr("class")
                    // console.log(eles[1].attr("class"))
                    BgUserInfoGetTable();
                },
                error: function (data) {
                    console.log("联系管理员")
                }
            })
        });
    } else if (input.value == "删除") {
        layer.confirm('是否删除？', {
            btn: ['是', '否'] //按钮
        }, function () {
            $.ajax({
                url: "delAdmServelet?date=" + new Date().getTime(),
                type: 'post',
                data: {
                    'AdmAccount': Adm,
                },
                async: true,
                dataType: 'JSON',
                success: function (resp) {
                    BgUserInfoGetTable();
                    layer.msg("删除成功")
                },
                error: function (data) {
                    console.log("联系管理员")
                }
            })
        }, function () {
            layer.msg("取消删除")
        });
    } else if (input.value == "重置密码") {
        layer.confirm('是否重置该用户的密码？', {
            btn: ['重置', '取消'] //按钮
        }, function () {
            $.ajax({
                url: "ResetAdmPwdServelet?date=" + new Date().getTime(),
                type: 'post',
                data: {
                    'AdmAccount': Adm,
                },
                async: true,
                dataType: 'JSON',
                success: function (resp) {
                    console.log(resp.message)
                    layer.msg("密码重置成功")
                },
                error: function (data) {
                    console.log("联系管理员")
                }
            })
        }, function () {
            layer.msg("你取消了密码的重置");
        });
    }
})

$("#SearchBtn").click(function () {
    AdmNameSearch = $("#AdmNameSearch").val();
    AdmStatus = $("#ifStart").val();
    titleSelect = $("#titleSelect").val();
    $.ajax({
        url: 'SearchAdmInfoServlet',
        type: 'post',
        data: {
            'AdmNameSearch': AdmNameSearch,
            'AdmStatus': AdmStatus,
            'titleSelect': titleSelect,
            'limit': limit,
            'offset': offset
        },
        async: true,
        dataType: 'JSON',
        success: function (resp) {
            console.log(resp);
            count = resp.datas.userCount;
            totalPage = count % page == 0 ? count / page : parseInt(count / page + 1);
            var tablepage = document.getElementById('tablepage');
            tablepage.innerText = currPage + "/" + totalPage
            console.log(currPage + "/" + totalPage)
            console.log(tablepage.innerText)

            arr = resp.datas.userList;
            var mybody = document.getElementById('mybody');
            mybody.innerText = '';
            console.log(arr)
            console.log(mybody + "表格")
            for (var i = 0; i < arr.length; i++) {
                mybody.innerHTML += `<tr align="center">
        <td>${arr[i].adminAccount}</td>
        <td>${arr[i].titleName}</td>
        <td>${arr[i].consultSchool}</td>
        <td> ${jugestate(arr[i].adminStatue)}</td>
        <td>
            <button type="button" id="delBtn" class="btn btn-danger"  value="删除">删除</button>
            <button type="button"  id="IfPanBtn" class="btn " value=${jugestate(arr[i].adminStatue)}>启用/禁用
            </button>
            <button type="button" id="resetBtn" class="btn btn-primary" onclick="test1(this)" value="重置密码">重置密码</button>
        </td>
    </tr>`
            }
        },
        error: function (data) {
            console.log("联系管理员")
        }
    })
    console.log(userNameSearch + " " + userIfDel)
});

function jugestate(state) {
    if (state == "启用") {
        state = 1;
    } else if (state == "禁用") {
        state = 0;
    } else if (state == 0) {
        state = "启用";
    } else if (state == 1) {
        state = "禁用";
    }
    return state
}

$("#admRole").change(function () {
    if ($(this).val() == "1") {
        console.log("管理员")
        $("#consult").hide();
    } else if ($(this).val() == "2") {
        console.log("咨询师")
        $("#consult").show();
    }
})


$("#subBtn").click(function () {
    var $admAccount = $("#admAccount").val();
    var $admPwd = $("#admPwd").val();
    var $admRoleVal = $("#admRole option:selected").val();
    var $selectedVal = $("#title option:selected").val();


    var eles = document.getElementsByClassName("checkboxItem");
    var AddFiledList = [];
    for (var i = eles.length - 1; i >= 0; i--) {
        if (eles[i].checked == true) {
            AddFiledList.push(eles[i].value)
        }
    }

    var $School = $("#School").val();
    var $cost = $("#cost").val();
    var $pbg = $("#pbg").val();
    var $brief = $("#brief").val();

    console.log($admAccount + $admPwd + $selectedVal + $School + $cost + $pbg + $brief)
    console.log("用户id" + $admRoleVal)
    console.log("提交")

    $.ajax({
        url: 'AddAdmInfoServlet',
        type: 'post',
        data: {
            '$admAccount': $admAccount,
            '$admPwd': $admPwd,
            '$admRoleVal': $admRoleVal,
            '$selectedVal': $selectedVal,
            'AddFiledList[]': AddFiledList,
            '$School': $School,
            '$cost': $cost,
            '$pbg': $pbg,
            '$brief': $brief,
        },
        async: true,
        dataType: 'JSON',
        success: function (resp) {
            layer.msg(resp.message)
        },
        error: function (data) {
            console.log("联系管理员")
        }
    })


})





