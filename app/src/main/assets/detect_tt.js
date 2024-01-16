javascript: (function () {
    console.log("加载tiktok解析代码>>>>>>>>>")
    function warn(tag, msg = "") {
        console.log("warn >>>>>>>>>" + msg)
        ADAPTATION_HOLDER.warn(tag, msg);
    }
    function receiveJsParseResult(jsonResult) {
        console.log("receiveJsParseResult >>>>>>>>>" + jsonResult)
        ADAPTATION_HOLDER.receiveJsParseResult(jsonResult);
    }
    var tagMedia = function (e) {
        var targetContainer = e
        //已经注入的标签无须再次处理
        div = document.createElement("DIV")
        var top = "0px"
        //注入下载按钮
        div.className = "tt_dl";
        div.setAttribute("style", "position:absolute;top:" + top + ";right:0px;width: 50px; height: 50px; z-index:9999 ");
        div.innerHTML = '<img style="position:absolute;top:15px;bottom:0px;left:10px;right:0px;margin:auto;;width: 50px; height: 50px; object-fit: fill; pointer-events: none" src="data:image/png;base64,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" />';

        div.onclick = function (event) {
            ADAPTATION_HOLDER.clickDownloadButtonEvent(window.location.host)
            event.stopPropagation();
            var timestamp = Date.now()
            var clickTarget = event.target.parentNode
            var videos = clickTarget.getElementsByTagName("video")
            var data = null
            if (videos.length > 0) {
                type = "mp4"
                var videoUrl = videos[0].src
                if (videoUrl && !videoUrl.startsWith("blob") && /[^\s]/.test(videoUrl)) {
                    var name_prefix = "YTD"
                    //找到激活的div
                    var actives = clickTarget.getElementsByClassName("swiper-slide-active")
                    if (actives.length > 0) {
                        var names = actives[0].getElementsByClassName("bottom-name")
                        if (names.length > 0) {
                            name_prefix = names[0].innerText
                        }
                    }

                    var bgs = clickTarget.getElementsByClassName("blur-background")
                    var thumbnail = ""
                    if (bgs.length > 0) {
                        var background = bgs[0].style['background-image']
                        if (background.indexOf("(") >= 0 && background.indexOf(")") >= 0) {
                            thumbnail = background.split("(")[1].split(")")[0].replace(/\"/g, "");
                        }
                    }
                    data = {
                        name: name_prefix + "_" + timestamp + ".mp4",
                        sourceUrl: location.href,
                        quality: "sd",
                        fromUrl: location.href,
                        thumbnailUrl: thumbnail,
                        mediaUrlList: [videoUrl]
                    }
                } else {
                    warn("TT_VIDEO_URL_INVALID", videoUrl);
                    console.log("非法tiktok地址::: " + videoUrl)
                }

            }
            if (data) {
                var resultData = {
                    'status': 'time_line',
                    'fromType': 'tiktok',
                    'showDialog': true,
                    'parseType': type,
                    'dataList': [data],
                    'fromUrl': location.href
                }
                ADAPTATION_HOLDER.setParseBtnType("adapter", "websiteBtn", "detect_tt.js");
                receiveJsParseResult(JSON.stringify(resultData));
            } else {
                warn("TT_NO_DATA");
            }

        }
        targetContainer.insertBefore(div, e.firstChild);
        targetContainer.setAttribute("tag", 1)
    }
    var findAllMedias = function (target, from) {
        var containers = target.getElementsByClassName("container")
        if (containers.length > 0) {
            tagMedia(containers[0])
        }

    }
    window.onload = function () {
        findAllMedias(document, "all")
    }
    findAllMedias(document, "all")
    console.log("完成加载 <<<<<<<<<<<<<<<<<<")
}());