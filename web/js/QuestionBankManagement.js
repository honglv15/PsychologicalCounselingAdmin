limit = 0
offset = 5
page = 5;//查询的数据条数
count = 0; // 一共多少条数据
currPage = 1; // 当前第几页
totalPage = 0; // 一共多少页

$("#FieldSelection").click(function (event) {
    var selectValue = $('#FieldSelection option:selected').val();
    console.log(selectValue)

    $.ajax({
        url: 'InitQuestionBankManagementTableServlet',
        type: 'post',
        data: {
            'selectValue': selectValue,
            'limit': limit,
            'offset': offset
        },
        async: true,
        dataType: 'JSON',
        success: function (resq) {
        },
        error: function (resq) {
        }
    })
})