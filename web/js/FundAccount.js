limit = 0; // 开始为止
offset = 5; // 每页显示多少条
page = 5;//查询的数据条数
count = 0; // 一共多少条数据
currPage = 1; // 当前第几页
totalPage = 0; // 一共多少页

window.onload = FundAccountGetTable();

function FundAccountGetTable() {
    console.log("window.onload");
    console.log(limit + " " + offset)
    console.log("搜索")
    var $ifStart = $("#ifStart").val()
    console.log("状态" + $ifStart)
    $.ajax({
        url: 'AdmFundAccountGetTableServlet',
        type: 'post',
        data: {
            'limit': limit,
            'offset': offset
        },
        async: true,
        dataType: 'JSON',
        success: function (resp) {
            console.log(resp);
            count = resp.datas.FundAccountCount;
            totalPage = count % page == 0 ? count / page : parseInt(count / page + 1);
            var tablepage = document.getElementById('tablepage');
            tablepage.innerText = currPage + "/" + totalPage
            console.log(currPage + "/" + totalPage)
            console.log(tablepage.innerText)

            arr = resp.datas.GetFundAccountInfo;
            var mybody = document.getElementById('mybody');
            mybody.innerText = '';
            console.log(arr)
            console.log(mybody + "表格")
            for (var i = 0; i < arr.length; i++) {
                mybody.innerHTML += `<tr align="center">
        <td>${arr[i].aincomeTime}</td>
        <td>${arr[i].userAccount}</td>
        <td>${arr[i].aincomeContent}</td>
        <td>${arr[i].aincomeMoney}</td>
    </tr>`


            }

            console.log("余额:" + resp.datas.consultMoney);
            var money = document.getElementById("money");
            money.innerText = resp.datas.consultMoney;
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
        FundAccountGetTable();
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
        FundAccountGetTable()
    } else {
        alert("没有下一页了")
    }
})