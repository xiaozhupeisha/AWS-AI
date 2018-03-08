$(function () {

    $(".upload-img").on("click", function () {
        $(".file").click();
    });

    $(".upload-text-img").on("click", function () {
        $(".textFile").click();
    });

    $(".upload-face-img").on("click", function () {
        $(".faceFile").click();
    });

    $(".file").on("change", function () {
        $(".img-form").submit();
    });

    $(".textFile").on("change", function () {
        $(".text-img-form").submit();
    });

    $(".faceFile").on("change", function () {
        $(".face-img-form").submit();
    });

    $(".search-img").on("click", function () {
        var key = $(".img-search-input").val();
        $(".img-form").attr("action", "");
        $(".img-form").attr("method", "get");
        $(".img-form").submit();
    });

    $(".more-function").on("click", function () {
        location.href = "_extend";
    });

    var faceJson = $(".face-json").text();
    var textJson = $(".text-json").text();
    if(faceJson){
        var options = {
            dom: '.face-result' //对应容器的css选择器
        };
        var jf = new JsonFormater(options); //创建对象
        jf.doFormat($(".face-result").text()); //格式化json
    }
    if(textJson){
        var options = {
            dom: '.text-result' //对应容器的css选择器
        };
        var jf = new JsonFormater(options); //创建对象
        jf.doFormat($(".text-result").text()); //格式化json
    }

});