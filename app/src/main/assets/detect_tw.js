javascript: (function () {
    var isClickCooling = true
    console.log("加载twitter解析代码>>>>>>>>>")
    function warn(tag, msg = "") {
        console.log("warn:: " + tag + " , " + msg)
        ADAPTATION_HOLDER.warn(tag, msg);
    }
    function receiveJsParseResult(jsonResult) {
        console.log("receiveJsParseResult:: " + jsonResult)
        ADAPTATION_HOLDER.receiveJsParseResult(jsonResult);
    }
    var tagMedia = function (e, container, title, link, thumb, isVideo, marginTop, article) {
        var targetContainer = container
        //已经注入的标签无须再次处理
        if (isVideo) { // 有的页面开始没有视频标签，只有图片标签。运行后视频标签处于下方导致按钮点击的始终是图片btn
            var imgBtn = article.getElementsByClassName("tw_dl_image")[0]
            if (imgBtn != null) { // 移除图片下载按钮
                imgBtn.parentNode.removeChild(imgBtn);
            }
        }
        if (isVideo && container.getElementsByClassName("tw_dl_video").length > 0
            || !isVideo && container.getElementsByClassName("tw_dl_image").length > 0) {
            return false;
        }
        div = document.createElement("DIV")
        //注入下载按钮
        if (isVideo) {
            div.className = "tw_dl_video";
        } else {
            div.className = "tw_dl_image";
        }
        div.setAttribute("style", "position:absolute;top:" + marginTop + "px;left:0px;width: 50px; height: 50px; z-index:99");
        div.innerHTML = '<img class="tw_dl_img" style="position:absolute;top:15px;bottom:0px;left:10px;right:0px;margin:auto;;width: 50px; height: 50px; object-fit: fill; pointer-events: none" src="data:image/png;base64,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" />';

        div.onclick = function (event) {
            if (!isClickCooling) {
                return
            }
            ADAPTATION_HOLDER.clickDownloadButtonEvent(window.location.host)
            isClickCooling = false
            setTimeout(function () {
                isClickCooling = true;
            }, 500);
            //            event.stopPropagation(); 会导致一直刷新，直接去除
            var timestamp = Date.now()
            var name_prefix = "YTD"
            if (title != null) {
                name_prefix = title;
            }
            var type = isVideo ? "tw" : "jpg"
            data = {
                name: name_prefix + "_" + timestamp + (isVideo ? ".mp4" : ".jpg"),
                sourceUrl: link,
                quality: "sd",
                fromUrl: location.href,
                thumbnailUrl: thumb,
                mediaUrlList: isVideo ? [] : [thumb]
            }
            if (data) {
                var resultData = {
                    'status': 'time_line',
                    'fromType': 'tw',
                    'parseType': type,
                    'showDialog': true,
                    'dataList': [data],
                    'fromUrl': location.href,
                    'dataSource': 'timeline===tw'
                }
                ADAPTATION_HOLDER.setParseBtnType("adapter", "websiteBtn", "detect_tw.js");
                receiveJsParseResult(JSON.stringify(resultData));
            } else {
                warn("TW_NO_DATA");
            }
        }
        targetContainer.appendChild(div);
        //        targetContainer.insertBefore(div, targetContainer.firstChild);
        container.setAttribute("tag", 1)
        return true;
    }

    //获取所有的img标签
    var findAllMedias = function (target, from) {
        var articles = target.getElementsByTagName("article")
        if (articles.length > 0) {
            for (var i = 0; i < articles.length; i++) {
                handleArticle(articles[i])
            }
        }
        // 预览界面：无其它标志，当前以查看是否有关闭图标
        var mainEle = document.querySelectorAll("main[role='main']")[0]
        if (mainEle != null) {
            var closeParent = mainEle.querySelectorAll("div[role='presentation']")[0]
            if (closeParent != null) { // 同时确认有唯一的role="button" 和svg标签
                var btnParents = closeParent.querySelectorAll("div[role='button']")
                if (btnParents.length == 1) {
                    var svgs = btnParents[0].getElementsByTagName("svg")
                    if (svgs.length == 1) {
                        var imgEles = mainEle.getElementsByTagName("img")
                        for (var i = 0; i < imgEles.length; i++) {
                            var imgEle = imgEles[i];
                            if (imgEle.className != "tw_dl_img") {
                                tagMedia(imgEle, imgEle.parentNode, "preview", "", imgEle.src, false, 60, mainEle)
                            }
                        }
                    }
                }
            }
        }
    }

    var handleArticle = function (article) {
        // var videoDIV = article.querySelectorAll("div[aria-label='Embedded video']")
        var title = article.getElementsByTagName("span")[0].innerText
        var link = ""
        var hrefs = article.getElementsByTagName("a")
        if (hrefs.length > 0) {
            for (var i = 0; i < hrefs.length; i++) {
                if (hrefs[i].href && hrefs[i].href.indexOf("/status/") >= 0) {
                    link = hrefs[i].href
                    break
                }
            }
        }
        var target = article.querySelectorAll("div[data-testid='videoPlayer']")[0]
        if (target != null) {
            //说明是视频
            //提取缩略图
            var video = target.getElementsByTagName("video")[0]
            var thumb = ""
            if (video) {
                thumb = video.poster
            }
            var container = target.parentNode.parentNode.parentNode
            var isAddSuccess = tagMedia(target, container, title, link, thumb, true, 0, article)
        } else {
            //适配不同的图片样式,card样式的图片，地步完全图片
            var targets = article.querySelectorAll("div[data-testid='card.wrapper'],div[data-testid='tweetPhoto']")
            targets.forEach(target => {
                if (target) {
                    var img = target.getElementsByClassName("css-9pa8cd")[0]
                    var container = target
                    if (target.getAttribute('data-testid') == "tweetPhoto") {
                        //五级，跳出<a>标签
                        container = target.parentNode.parentNode.parentNode
                    } else {
                        target = img
                    }
                    if (img) {
                        var thumb = img.src
                        var isAddSuccess = tagMedia(target, container, title, link, thumb, false, 0, article)
                    }
                }
            });
        }
    }

    var observerOptions = {
        childList: true,
        subtree: true,
    }

    var observer = new MutationObserver(function (mutations, observer) {
        findAllMedias(document, "childList")
    });

    console.log(" ======== 开始监听元素变化 =====:::")
    findAllMedias(document, "all")
    observer.observe(document.body, observerOptions);
    console.log("完成加载 <<<<<<<<<<<<<<<<<<")
}());