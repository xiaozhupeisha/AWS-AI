/**
 * Created by Byron on 2017/9/5.
 */
function getDate(a, b) {
    if (a == null || a.length == 0) {
        return ""
    }
    return new Date(a).format(b)
};
function getPrice(a) {
    if (a == null) {
        return ""
    }
    return new Number(a).toFixed(2)
};
Date.prototype.format = function (a) {
    var d = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12,
        "H+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        S: this.getMilliseconds()
    };
    var c = {
        "0": "/u65e5",
        "1": "/u4e00",
        "2": "/u4e8c",
        "3": "/u4e09",
        "4": "/u56db",
        "5": "/u4e94",
        "6": "/u516d"
    };
    if (/(y+)/.test(a)) {
        a = a.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length))
    }
    if (/(E+)/.test(a)) {
        a = a.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f" : "/u5468") : "") + c[this.getDay() + ""])
    }
    for (var b in d) {
        if (new RegExp("(" + b + ")").test(a)) {
            a = a.replace(RegExp.$1, (RegExp.$1.length == 1) ? (d[b]) : (("00" + d[b]).substr(("" + d[b]).length)))
        }
    }
    return a
};
Array.prototype.indexOf = function (b) {
    for (var a = 0; a < this.length; a++) {
        if (this[a] == b) {
            return a
        }
    }
    return -1
};
Array.prototype.removevalue = function (b) {
    var a = this.indexOf(b);
    if (a > -1) {
        this.splice(a, 1)
    }
};
String.prototype.getWidth = function (b) {
    var a = document.getElementById("__getwidth");
    if (a == null) {
        a = document.createElement("span");
        a.id = "__getwidth";
        document.body.appendChild(a);
        a.style.visibility = "hidden";
        a.style.whiteSpace = "nowrap"
    }
    a.innerText = this;
    a.style.fontSize = b + "px";
    return a.offsetWidth
};
function chinsesLength(c) {
    var b = /[^\u4E00-\u9FA5\uf900-\ufa2d]/g;
    var a = c.replace(b, "");
    return a.length
};
function covertChinese(c) {
    var d = parseInt(c.length);
    var b = chinsesLength(c);
    var a = d - b;
    var e = b + Math.ceil(a / 2);
    return e
};
function calFontSize(a, b, d) {
    var c = Math.ceil(a / covertChinese(b));
    return Math.max(c, d)
};
function compareArrayLen(b, a) {
    if (b == null && a == null) {
        return 0
    } else {
        if (b == null) {
            return -1
        } else {
            if (a == null) {
                return 1
            } else {
                if (b.length > a.length) {
                    return 1
                } else {
                    if (b.length == a.length) {
                        return 0
                    } else {
                        return -1
                    }
                }
            }
        }
    }
};
function getFieldValue(c, b) {
    var a = c[b];
    return a == null ? "" : a
};
function isExistField(c, b) {
    var a = c[b];
    return a == null ? false : true
};
function getFileNameByUrl(a) {
    return a.substring(a.lastIndexOf("/") + 1)
};