$('#treeMenu').tree({
    animate: true
})

$.ajax({
    url: 'GetAdmMenuServlet',
    type: 'post',
    dataType: 'JSON',
    success: function (msg) {
        console.log(msg.datas.menuInfos);
        var arr = msg.datas.menuInfos;
        var resArr = [];
        // 递归：方法调用方法自己
        diGui(0, arr, resArr); // 我要找arr集合中父级id是0的城市信息存放到resArr中
        var tree = $('#treeMenu').data('zui.tree');
        // 更新数据
        console.log(tree + "--" + resArr)
        tree.reload(resArr);
    },
    error: function (msg) {
        alert("请联系管理员");
    }
});

function diGui(menuPid, arr, resArr) {
    for (var i = 0; i < arr.length; i++) {
        if (arr[i].menuPid == menuPid) {
            var blnVal = hasChildren(arr[i].menuId, arr);
            if (blnVal == true) {
                var jsonObj = {
                    html: '<a href="javascript:void(0);"  ><i class="open icon icon-' + arr[i].menuIcon + '"></i>' + arr[i].menuName + '</a>',
                    children: []
                };
                // 将生成的对象保存到一开始的数组中
                resArr.push(jsonObj);
                // 既然有儿子开始查儿子信息
                diGui(arr[i].menuId, arr, jsonObj.children);
            } else {
                var jsonObj = {html: '<a href="javascript:void(0);" aaa="' + arr[i].menuUrl + '" onclick="change(this)" ><i class="icon icon-' + arr[i].menuIcon + '"></i>' + arr[i].menuName + '</a>'};
                resArr.push(jsonObj);
            }
        }
    }
}

function hasChildren(menuId, arr) {
    for (var i = 0; i < arr.length; i++) {
        if (arr[i].menuPid == menuId) {
            return true;
        }
    }
    return false;
}

function change(page) {
    if ($(page).attr("aaa") != "") {
        var ifr = document.getElementById("ifr");
        ifr.src = $(page).attr("aaa") + "?=" + Math.random();
        console.log(ifr.src)
    }
}

