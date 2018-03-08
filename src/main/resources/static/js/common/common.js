/**
 * Created by Byron on 2017/8/31.
 */
$(function () {
    // select2
    $('.select2').select2();

    // select2多項
    $('.select2-multiple').select2({
        tags: true,
        multiple: true
    });

    $('[data-toggle="tooltip"]').tooltip({
        trigger: "hover",
        placement: "right"
    });

});

(function (a) {
    window.jPaginator = function (data, callback) {
        $.jqPaginator("#pagination", {
            totalPages: data.totalPages,
            visiblePages: 8,
            currentPage: data.number + 1,
            totalCounts: data.totalElements,
            first: '<li class="first"><a href="javascript:;">首页</a></li>',
            prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
            next: '<li class="next"><a href="javascript:;">下一页</a></li>',
            last: '<li class="last"><a href="javascript:;">末页</a></li>',
            page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
            onPageChange: function (num, type) {
                if (type != "init") {
                    callback(num);
                }
            }
        });
    }
})(jQuery);

function LoadingTask() {
}

LoadingTask.showLoading = function (a) {
    $(a).mask('<img src="../img/loading.gif"/>');
};

LoadingTask.hideLoading = function (a) {
    $(a).unmask();
};
