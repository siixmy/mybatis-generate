//资源加载
var Path=document.scripts;
Path=Path[Path.length-1].src.substring(0,Path[Path.length-1].src.lastIndexOf("/")-3);

var Res = {
    include: function (path) {
//        document.scripts[0].src = path;
        var a = document.createElement("script");
        a.type = "text/javascript";
        a.src = Path+"/js" + path;
        var head = document.getElementsByTagName("head")[0];
        head.appendChild(a);
    },

    includeCss: function (path) {
        includeFullCss(Path+"/css" + path);
    },

    includeFullCss: function (path) {
        var a = document.createElement("link");
        a.type = "text/css";
        a.rel = "stylesheet";
        a.href = Path+path;
        var head = document.getElementsByTagName("head")[0];
        head.appendChild(a);
    }

};

//增加遮罩
var MaskLoad = {
    add: function () {
        $("<div class='data-loading'></div>").css({background: "gray", opacity: 0.3, filter: "alpha(opacity=80)", position: "absolute", top: 0, left: 0, width: "100%", height: "100%"}).appendTo("body");
        $("<div class='data-loading'>正在处理,请稍后...</div>").css({display: "block", position: "absolute", left: ($(document.body).outerWidth(true) - 190) / 2, top: 300}).appendTo("body");
    },
    remove: function () {
        $('.data-loading').remove();
    }
}


