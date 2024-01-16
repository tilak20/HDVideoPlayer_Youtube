javascript: (function () {
    var isClickCooling = true
    console.log("加载fb解析代码>>>>>>>>>")
    function warn(tag, msg = "") {
        ADAPTATION_HOLDER.warn(tag, msg);
    }
    function receiveJsParseResult(jsonResult) {
        ADAPTATION_HOLDER.receiveJsParseResult(jsonResult);
    }

    var tagMedia = function (e, from, title, offsetTop) {
        //已经注入的标签无须再次处理
        var top = 0
        var obj = null
        if (from == "type") {
            try {
                obj = JSON.parse(e.getAttribute("data-extra"))
            } catch (p) {
                console.log(p)
            }
        } else if (from == "inline") {
            try {
                obj = JSON.parse(e.getAttribute("data-store"))
            } catch (p) {
                console.log(p)
            }
        } else if (from == "image") {

        }
        if (obj && obj.player_origin == "fb_stories") {
            warn("FB_STORY", "fb story is not support well!");
            top = 120
        }
        top += offsetTop
        var name_prefix = "YTD"
        div = document.createElement("DIV")
        //注入下载按钮
        div.className = "fb_dl";
        // 加这个ID 是为了和主标签抢点击事件
        div.setAttribute('data-action-id', '1234')
        div.setAttribute("style", "position:absolute;top:" + top + "px;left:0px;width: 50px; height: 50px; z-index:9999 ");
        div.innerHTML = '<img style="position:absolute;top:15px;bottom:0px;left:10px;right:0px;margin:auto;width: 50px; height: 50px; object-fit: fill;pointer-events: none" src="data:image/png;base64,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" />';
        div.onclick = function (event) {
            if (!isClickCooling) {
                return
            }
            ADAPTATION_HOLDER.clickDownloadButtonEvent(window.location.host)
            isClickCooling = false
            setTimeout(function () {
                isClickCooling = true;
            }, 500);
            event.stopPropagation();
            var timestamp = Date.now()
            var clickTarget;
            //add 0217 解决部分手机事件透传失效问题
            if (event.target.className != "fb_dl") {
                clickTarget = event.target.parentNode.parentNode
            } else {
                clickTarget = event.target.parentNode
            }
            var data = null
            var type = "mp4"
            var isLive = false
            var fromType = "facebook"
            if (clickTarget.getAttribute("data-type") == "video") {
                isLive = clickTarget.getAttribute("data-is-live-streaming") == true;
                if (isLive) {
                    warn("FB_LIVE", "fb live is not support download!");
                    return
                }
                var videoUrl = clickTarget.getAttribute("data-video-url")
                var videoId = clickTarget.getAttribute("data-video-id")
                if (videoUrl && !videoUrl.startsWith("blob")) {
                    var imgs = clickTarget.getElementsByTagName("IMG")
                    var thumbnailUrl = ""
                    if (imgs.length > 1) {
                        thumbnailUrl = imgs[1].src
                    }
                    data = {
                        name: name_prefix + "_" + timestamp + ".mp4",
                        sourceUrl: location.href,
                        quality: "SD",
                        thumbnailUrl: thumbnailUrl,
                        fromUrl: location.href,
                        mediaUrlList: [videoUrl],
                        videoId: videoId,
                    }
                } else {
                    warn("FB_VIDEO_URL_INVALID");
                }
            } else if (clickTarget.getAttribute("data-sigil") == "inlineVideo") {
                var obj = null;
                try {
                    obj = JSON.parse(clickTarget.getAttribute("data-store"))
                } catch (p) {
                    console.log(p)
                    warn("FB_JSON_PARSE_FAILED", "parse json content failed!" + p);
                    return
                }
                isLive = obj.playbackIsLiveStreaming == true;
                if (isLive) {
                    warn("FB_LIVE", "fb live is not support download!");
                    return
                }
                var videoUrl = obj.src
                var videoId = obj.videoID
                if (videoUrl && !videoUrl.startsWith("blob")) {
                    var imgs = clickTarget.getElementsByTagName("I")
                    var thumbnailUrl = ""
                    if (imgs.length > 0) {
                        var background = imgs[0].style.background
                        if (background.indexOf("(") >= 0 && background.indexOf(")") >= 0) {
                            thumbnailUrl = imgs[0].style.background.split("(")[1].split(")")[0].replace(/\"/g, "");
                        }
                    }
                    data = {
                        name: name_prefix + "_" + timestamp + ".mp4",
                        sourceUrl: location.href,
                        quality: "SD",
                        fromUrl: location.href,
                        thumbnailUrl: thumbnailUrl,
                        mediaUrlList: [videoUrl],
                        videoId: videoId,
                    }
                } else {
                    warn("FB_VIDEO_URL_INVALID");
                }
            } else {
                var imgEle = clickTarget.querySelector("img[class='img contain']")
                if (imgEle !== null) {
                    type = "jpg";
                    // 图片类型就不要再请求了
                    fromType = "facebook-img"
                    var imageDownloadPath = imgEle.getAttribute("src")
                    if (imageDownloadPath !== null && imageDownloadPath.length > 0) {
                        data = {
                            name: name_prefix + "_" + timestamp + ".jpg",
                            sourceUrl: location.href,
                            fromUrl: location.href,
                            thumbnailUrl: imageDownloadPath,
                            mediaUrlList: [imageDownloadPath],
                        }
                    }
                }
            }
            if (data) {
                var resultData = {
                    'status': 'time_line',
                    'fromType': fromType,
                    'showDialog': true,
                    'parseType': type,
                    'dataList': [data],
                    'fromUrl': location.href,
                    'dataSource': 'timeline===fb'
                }
                ADAPTATION_HOLDER.setParseBtnType("adapter", "websiteBtn", "detect_fb.js");
                receiveJsParseResult(JSON.stringify(resultData));
            } else {
                warn("FB_NO_DATA");
            }
        }
        e.insertBefore(div, e.firstChild);
        e.setAttribute("tag", 1)
    }

    // 全屏显示时（目前搜索 -> 视频打开）
    var tagScreenRootMedia = function (isShow) {
        var parentNode = document.getElementById("screen-root")
        if (parentNode == null) {
            return;
        }
        var lastFbDl = document.getElementById("screenFbDl")
        if (isShow) {
            if (lastFbDl != null) {
                return;
            }
        } else {
            if (lastFbDl != null) { // 移除
                lastFbDl.parentNode.removeChild(lastFbDl);
            }
            return;
        }
        div = document.createElement("DIV")
        //注入下载按钮
        div.id = "screenFbDl";
        div.setAttribute("style", "position:absolute;top:50px;left:0px;width: 50px; height: 50px; z-index:9999 ");
        div.innerHTML = '<img style="position:absolute;top:15px;bottom:0px;left:10px;right:0px;margin:auto;width: 50px; height: 50px; object-fit: fill; pointer-events: none" src="data:image/png;base64,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" />';
        div.onclick = function (event) {
            if (!isClickCooling) {
                return
            }
            isClickCooling = false
            setTimeout(function () {
                isClickCooling = true;
            }, 500);
            event.stopPropagation();
            var timestamp = Date.now()
            // 找到body下的video
            var bodyChildren = document.body.children;
            var childrenLength = bodyChildren.length
            var videoE = null
            for (var i = 0; i < childrenLength; i++) {
                if (bodyChildren[i].tagName.toLowerCase() == "video") {
                    videoE = bodyChildren[i];
                    break;
                }
            }
            var data = null
            var type = "mp4"
            if (videoE != null) {
                var videoUrl = videoE.src;
                if (videoUrl != null && !videoUrl.startsWith("blob")) {
                    data = {
                        name: name_prefix + "_" + timestamp + ".mp4",
                        sourceUrl: location.href,
                        quality: "sd",
                        fromUrl: location.href,
                        mediaUrlList: [videoUrl],
                    }
                } else {
                    warn("FB_VIDEO_URL_INVALID");
                }
            }
            if (data != null) {
                var resultData = {
                    'status': 'time_line',
                    'fromType': 'facebook',
                    'parseType': type,
                    'dataList': [data],
                    'fromUrl': location.href,
                    'dataSource': 'timeline===fb'
                }
                receiveJsParseResult(JSON.stringify(resultData));
            } else {
                warn("FB_NO_DATA");
            }
        }
        parentNode.insertBefore(div, parentNode.firstChild);
    }

    //获取过的div,直接在顶级的属性中打入tag
    var handleItemType = function (item) {
        if (item.getAttribute("tag") == 1) {
            return;
        }
        var title = ""
        var titles = item.getElementsByTagName("span")
        if (titles.length > 0) {
            title = titles[0].innerText;
        }
        // alt="Facebook wordmark"
        var isReelLogin = document.querySelectorAll("img[alt='Facebook wordmark']").length > 0
        var offsetTop = 0
        if(isReelLogin){
            offsetTop = 60
        }
        var videos = item.querySelectorAll("div[data-type='video']")
        if (videos.length > 0) {
            for (var idx = 0; idx < videos.length; idx++) {
                tagMedia(videos[idx], "type", title, offsetTop)
            }
            item.setAttribute("tag", 1)
        }
    }

    var handleItemImage2 = function (item) {
        try {
            var parentNode = item.parentNode
            var parentActionId = parentNode.getAttribute("data-action-id")
            if (parentActionId === null) {
                var tempChildren = parentNode.children
                if (tempChildren.length > 1) {
                    for (var i = 0; i < tempChildren.length; i++) {
                        if (tempChildren[i].getAttribute("data-mcomponent") !== "ServerImageArea") {
                            continue;
                        }
                        var tempLength = tempChildren[i].children.length
                        if (tempLength !== 1) {
                            continue;
                        }
                        if (tempChildren[i].children[0].nodeName !== "IMG") {
                            continue;
                        }
                        var heightStr = tempChildren[i].style.height
                        if (parseInt(heightStr) < 50) {
                            continue;
                        }
                        if (widthArray[0] < 50)
                            if (tempChildren[i].className.search("img cover") !== -1) {
                                continue;
                            }
                        if (tempChildren[i].getAttribute("tag") == 1) {
                            continue;
                        }
                        var tempImgEle = tempChildren[i].querySelector("img[class='img contain']")
                        if (tempImgEle === null) {
                            continue;
                        }
                        var title = "fb-img"
                        tagMedia(tempChildren[i], "image", title, 0)
                        tempChildren[i].setAttribute("tag", 1)
                    }
                }
                return;
            }
            var tempChildren = parentNode.children;
            if (tempChildren.length > 1) {
                for (var i = 0; i < tempChildren.length; i++) {
                    if (tempChildren[i].getAttribute("data-mcomponent") !== "ServerImageArea") {
                        continue;
                    }
                    var tempChildren2 = tempChildren[i].children;
                    if (tempChildren2.length !== 1) {
                        continue
                    }
                    if (tempChildren2[0].nodeName !== "IMG") {
                        continue
                    }
                    var heightStr = tempChildren[i].style.height
                    if (parseInt(heightStr) < 50) {
                        continue;
                    }
                    if (tempChildren2[0].className.search("img cover") !== -1) {
                        continue;
                    }
                    if (tempChildren[i].getAttribute("tag") == 1) {
                        continue;
                    }
                    var tempImgEle = tempChildren[i].querySelector("img[class='img contain']")
                    if (tempImgEle === null) {
                        return;
                    }
                    var title = "fb-img"
                    tagMedia(tempChildren[i], "image", title, 0)
                    tempChildren[i].setAttribute("tag", 1)
                }
                return;
            }
            var heightStr = item.style.height
            if (parseInt(heightStr) < 50) {
                return;
            }
            if (parentNode.getAttribute("tag") == 1) {
                return;
            }
            var title = "fb-img"
            tagMedia(parentNode, "image", title, 0)
            parentNode.setAttribute("tag", 1)
        } catch (error) {

        }
    }

    var handleItemImage = function (item) {
        try {
            var parent1 = item.parentNode
            var parentActionId = parent1.getAttribute("data-action-id")
            if (parentActionId === null) {
                var tempChildren = parent1.children
                if (tempChildren.length > 1) {
                    for (var i = 0; i < tempChildren.length; i++) {
                        if (tempChildren[i].getAttribute("data-mcomponent") !== "ImageArea") {
                            continue;
                        }
                        var tempLength = tempChildren[i].children.length
                        if (tempLength !== 1) {
                            continue;
                        }
                        if (tempChildren[i].children[0].nodeName !== "IMG") {
                            continue;
                        }
                        var heightStr = tempChildren[i].style.height
                        if (parseInt(heightStr) < 50) {
                            continue;
                        }
                        if (tempChildren[i].className.search("img cover") !== -1) {
                            continue;
                        }
                        if (tempChildren[i].getAttribute("tag") == 1) {
                            continue;
                        }
                        var tempImgEle = tempChildren[i].querySelector("img[class='img contain']")
                        if (tempImgEle === null) {
                            continue;
                        }
                        var title = "fb-img"
                        tagMedia(tempChildren[i], "image", title, 0)
                        tempChildren[i].setAttribute("tag", 1)
                    }
                }
                return;
            }
            var children1 = parent1.parentNode.children
            if (children1.length > 1) {
                return;
            }
            var parentNode = parent1.parentNode.parentNode
            var children = parentNode.children
            if (parentNode.getAttribute("tag") == 1) {
                return;
            }
            var title = "fb-img"
            var imageChildren = item.children

            if (imageChildren.length <= 0) {
                return;
            }
            var heightStr = item.style.height
            if (parseInt(heightStr) < 50) {
                return;
            }
            var tempImgEle = parentNode.querySelector("img[class='img contain']")
            if (tempImgEle === null) {
                return;
            }
            tagMedia(parentNode, "image", title, 0)
            parentNode.setAttribute("tag", 1)
        } catch (error) {

        }

    }

    //获取过的div,直接在顶级的属性中打入tag
    var handleItemDetail = function (item) {
        if (item.getAttribute("tag") == 1) {
            return;
        }
        var title = "fb"
        tagMedia(item, "type", title, 0)
        item.setAttribute("tag", 1)
    }

    //电脑上可以测试
    var handleItemSigil = function (item) {
        if (item.getAttribute("tag") == 1) {
            return;
        }
        var title = ""
        var titles = item.getElementsByClassName("_4vc-")
        if (titles.length > 0) {
            title = titles[0].innerText;
        }

        var inlineVideos = item.querySelectorAll("div[data-sigil='inlineVideo']")
        if (inlineVideos.length > 0) {
            for (var i = 0; i < inlineVideos.length; i++) {
                tagMedia(inlineVideos[i], "inline", title, 0)
            }
            item.setAttribute("tag", 1)
        }
    }

    //获取所有的视频标签
    var findAllMedias = function (target, from) {
        console.log("findAllMedias start ======> ");
//        var items = document.querySelectorAll("div[class='story_body_container']:not([tag='1'])")
//        if (items.length > 0) {
//            for (var i = 0; i < items.length; i++) {
//                handleItemSigil(items[i]);
//            }
//        }
        var inlineVideos = document.querySelectorAll("div[data-sigil='inlineVideo']:not([tag='1'])")
        if (inlineVideos.length > 0) {
            for (var i = 0; i < inlineVideos.length; i++) {
                tagMedia(inlineVideos[i], "inline", "fb-video", 0)
            }
        }
        var items = document.querySelectorAll("div[data-tracking-duration-id]:not([tag='1'])")
        if (items.length > 0) {
            for (var i = 0; i < items.length; i++) {
                handleItemType(items[i]);
            }
        }
        items = document.querySelectorAll("div[data-video-url]:not([tag='1'])")
        if (items.length > 0) {
            for (var i = 0; i < items.length; i++) {
                handleItemDetail(items[i]);
            }
        }
        items = document.querySelectorAll("div[data-mcomponent='ImageArea']")
        if (items.length > 0) {
            for (var i = 0; i < items.length; i++) {
                handleItemImage(items[i])
            }
        }

        items = document.querySelectorAll("div[data-mcomponent='ServerImageArea']")
        if (items.length > 0) {
            for (var i = 0; i < items.length; i++) {
                handleItemImage2(items[i])
            }
        }
        // body下的video标签
        var bodyChildren = document.body.children;
        var childrenLength = bodyChildren.length
        var hasScreenVideo = false;
        for (var i = 0; i < childrenLength; i++) {
            // 同时存在关闭按钮(data-action-id="9")
            if (bodyChildren[i].tagName.toLowerCase() == "video"
                && document.querySelector("div[data-action-id='9']") != null) {
                hasScreenVideo = true;
                break;
            }
        }
        tagScreenRootMedia(hasScreenVideo);
    }

    var observerOptions = {
        childList: true,
        attributes: true, // 观察属性变动
        subtree: true, // 观察后代节点，默认为 false
        attributeFilter: ['style', 'class', 'data-store']
    }

    var observer = new MutationObserver(function (mutations, observer) {
        mutations.forEach((mutation) => {
            switch (mutation.type) {
                case 'childList':
                    mutation.addedNodes.forEach((node) => {
                        if ((node.tagName == "DIV" || node.tagName == "SECTION") && node.className != "fb_dl") {
                            findAllMedias(node, "childList")
                        }
                    });
                    break;
                case 'attributes':
                    var node = mutation.target;
                    if (node.tagName == "DIV" && node.className != "fb_dl") {
                        findAllMedias(node, "attributes")
                    }
                    break;
            }
        });
    });
    console.log("==== 开始监听 ======");
    findAllMedias(document, "all")
    observer.observe(document.body, observerOptions);
    console.log("完成加载 <<<<<<<")
}());