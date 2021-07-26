userNameSearch = ''
userIfDel = 0;
limit = 0; // 开始为止
offset = 5; // 每页显示多少条
page = 5;//查询的数据条数
count = 0; // 一共多少条数据
currPage = 1; // 当前第几页
totalPage = 0; // 一共多少页
window.onload = UserInfoGetTable();

function UserInfoGetTable() {
    console.log("window.onload");
    console.log(limit + " " + offset)
    console.log("搜索")
    var $ifStart = $("#ifStart").val()
    console.log("状态" + $ifStart)
    $.ajax({
        url: 'GetTalbeUserInfoServlet',
        type: 'post',
        data: {
            'userNameSearch': userNameSearch,
            'userIfDel': userIfDel,
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
                mybody.innerHTML += `<tr align="center" aaa="${arr[i].userAccount}">
        <td>${arr[i].userAccount}</td>
        <td> ${jugeSex(arr[i].userSex)}</td>
        <td>${arr[i].userAge}</td>
        <td>${arr[i].userPhone}</td>
        <td> ${jugestate(arr[i].userStatus)}</td>
        <td>
            <button type="button" id="delBtn" class="btn btn-danger"  value="删除">删除</button>
            <button type="button"  id="IfPanBtn" class="btn " value="启用/禁用">启用/禁用
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

function jugeSex(sex) {
    if (sex == 1) {
        sex = "男";
    } else if (sex == 0) {
        sex = "女";
    }
    return sex;
}


$("#SearchBtn").click(function () {
    userNameSearch = $("#userNameSearch").val();
    userStatus = $("#ifStart").val();
    $.ajax({
        url: 'SearchUserInfoServlet',
        type: 'post',
        data: {
            'userNameSearch': userNameSearch,
            'userStatus': userStatus,
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
                mybody.innerHTML += `<tr align="center" aaa="${arr[i].userAccount}">
        <td>${arr[i].userAccount}</td>
        <td> ${jugeSex(arr[i].userSex)}</td>
        <td>${arr[i].userAge}</td>
        <td>${arr[i].userPhone}</td>
        <td> ${jugestate(arr[i].userStatus)}</td>
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
    console.log(userNameSearch + " " + userIfDel)
});
$("#prePage").click(function () {
    if (currPage > 1) {
        currPage--;
        limit -= 5;
        offset -= 5;
        UserInfoGetTable();
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
        UserInfoGetTable()
    } else {
        alert("没有下一页了")
    }
})


$("#mybody").click(function (event) {
    var input = event.target
    console.log(input)
    eles = input.parentElement.parentElement.children;
    console.log(eles[0].innerText)
    var Ua = eles[0].innerText;
    if (input.value == "启用/禁用") {
        layer.confirm('对该状态进行操作', {
            btn: ['启用', '禁用'] //按钮
        }, function () {
            var userStatus = 0;
            $.ajax({
                url: "ModifyUserStatusServelet?date=" + new Date().getTime(),
                type: 'post',
                data: {
                    'userAccount': Ua,
                    'userStatus': userStatus,
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
                    UserInfoGetTable();
                },
                error: function (data) {
                    console.log("联系管理员")
                }
            })
        }, function () {
            var userStatus = 1;
            $.ajax({
                url: "ModifyUserStatusServelet?date=" + new Date().getTime(),
                type: 'post',
                data: {
                    'userAccount': Ua,
                    'userStatus': userStatus,
                },
                async: true,
                dataType: 'JSON',
                success: function (resp) {
                    console.log(resp.message)
                    eles = input.parentElement.children
                    console.log(eles[1])
                    console.log("value的值" + eles[1].value)
                    layer.msg('禁用成功')
                    UserInfoGetTable();
                },
                error: function (data) {
                    console.log("联系管理员")
                }
            })
        });
    } else if (input.value == "删除") {
        layer.confirm('是否删除该用户', {
            btn: ['是', '否'] //按钮
        }, function () {
            $.ajax({
                url: "delUserServelet?date=" + new Date().getTime(),
                type: 'post',
                data: {
                    'userAccount': Ua,
                },
                async: true,
                dataType: 'JSON',
                success: function (resp) {
                    console.log(resp.message)
                    UserInfoGetTable();
                    layer.msg("删除成功")
                },
                error: function (data) {
                    console.log("联系管理员")
                }
            })
        }, function () {
            layer.msg("删除取消")
        });




    } else if (input.value == "重置密码") {
        layer.confirm('对该该用户进行密码重置', {
            btn: ['启用', '禁用'] //按钮
        }, function () {
            $.ajax({
                url: "ResetUserPwdServelet?date=" + new Date().getTime(),
                type: 'post',
                data: {
                    'userAccount': Ua,
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
            layer.msg("密码重置取消")
        });
    }
})

