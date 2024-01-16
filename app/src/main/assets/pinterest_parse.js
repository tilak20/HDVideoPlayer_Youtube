javascript: (function () {
    var isClickCooling = true

    console.log("enter pinterest parse >>>>>>>>>")

    function warn(tag, msg = "") {
        ADAPTATION_HOLDER.warn(tag, msg);
    }

    function receiveJsParseResult(jsonResult) {
        ADAPTATION_HOLDER.receiveJsParseResult(jsonResult);
    }

    var tagMedia = function (node, parentNode, isStory) {
        var tagName = node.tagName
        var targetContainer = parentNode.parentNode;
        //已经注入的标签无须再次处理
        if (targetContainer.getElementsByClassName("pinterest_dl").length > 0) {
            console.log("pinterest tagMedia 已经注入的标签无须再次处理")
            return;
        }
        console.log("pinterest tagMedia createElement(div)")
        // targetContainer.position = relative;
        div = document.createElement("DIV")
        //注入下载按钮
        div.className = "pinterest_dl";
        var top = "0px";
        var zIndex = 0;
        if (isStory) {
            top = "60px";
            zIndex = 9;
        }
        div.setAttribute("style", "position:absolute;top:" + top + ";left:0px;width: 50px; height: 50px; z-index:" + zIndex);
        div.innerHTML = '<img class="pinterest_img" style="position:absolute;top:15px;bottom:0px;left:10px;right:0px;margin:auto;;width: 50px; height: 50px; object-fit: fill; pointer-events: none" src="data:image/png;base64,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" />';
         
        console.log("pinterest tagMedia setClick")
        div.onclick = function (event) {
            console.log("pinterest tagMedia onclick")
            if (!isClickCooling) {
                return
            }
            isClickCooling = false
            setTimeout(function () {
                isClickCooling = true;
            }, 500);
            console.log("pinterest tagMedia startClick");

            event.stopPropagation();
            var timestamp = Date.now()
            var clickTarget;
            //add 0217 解决部分手机事件透传失效问题
            if (event.target.className != "pinterest_dl") {
                clickTarget = event.target.parentNode.parentNode
            } else {
                clickTarget = event.target.parentNode
            }
            // 采集数据
            console.log("pinterest tagMedia 采集数据");
            var data = null;
            if (isStory) {
                var style = node.currentStyle || window.getComputedStyle(node, false);
                var url = style.backgroundImage.slice(4, -1).replace(/"/g, "");
                console.log("pinterest tagMedia 采集story url: " + url);
                if (url != null && url.length > 0) {
                    console.log("pinterest tagMedia 采集story substring.url: " + url);
                    var fileExtension = url.split('.').pop()
                    data = {
                        name: timestamp + "." + fileExtension,
                        sourceUrl: location.href,
                        quality: "sd",
                        thumbnailUrl: url,
                        fromUrl: location.href,
                        mediaUrlList: [url]
                    }
                }
            } else {
                // title
                var titleE = parentNode.getElementsByTagName('title')[0];
                var title = "";
                if (titleE != null) {
                    title = titleE.innerText + "_"
                }
                console.log("pinterest title: " + title);
                console.log("pinterest 采集数据 图片 ==> ");
                var imgs = clickTarget.getElementsByTagName("img");
                var imgLength = imgs.length;
                var imgUrl = null;
                if (imgLength > 0) { // 优先获取最后一张，植入脚本可能导致内部脚本使之前图片无法删除
                    if (imgs[imgLength - 1].className == "pinterest_img" && imgs.length > 1) {
                        imgUrl = imgs[imgLength - 2].src
                    } else {
                        imgUrl = imgs[imgLength - 1].src
                    }
                }
                if (imgUrl != null) {
                    var fileExtension = imgUrl.split('.').pop()
                    data = {
                        name: title + timestamp + "." + fileExtension,
                        sourceUrl: location.href,
                        quality: "sd",
                        thumbnailUrl: imgUrl,
                        fromUrl: location.href,
                        mediaUrlList: [imgUrl]
                    }
                }
            }
            if (data != null) {
                var resultData = {
                    'status': 'time_line',
                    'fromType': 'pinterest',
                    'showDialog': true,
                    'parseType': "jpg",
                    'dataList': [data],
                    'fromUrl': location.href,
                    'dataSource': 'single===pinterest'
                }
                console.log("pinterest tagMedia 上报数据 " + JSON.stringify(resultData));
                ADAPTATION_HOLDER.setParseBtnType("adapter", "websiteBtn", "pinterest_parse.js");
                receiveJsParseResult(JSON.stringify(resultData));
            } else {
                warn("INS_NO_DATA");
            }
        }
        console.log("pinterest tagMedia appendChild")
        targetContainer.appendChild(div);
        console.log("pinterest tagMedia complete");
    }

    //获取所有的img/video标签
    var findAllMedias = function (target, from) {
        var mediaEs = target.querySelectorAll('div[data-test-id="closeup-body-portrait"]');
        var hasImg = false;
        if (mediaEs.length > 0) {
            for (var i = 0; i < mediaEs.length; i++) {
                var mediaE = mediaEs[i];
                // 找到图片 pin-closeup-image
                var checkImgE = mediaE.querySelectorAll('div[data-test-id="pin-closeup-image"]')[0];
                if (checkImgE != null) {
                    var imgE = checkImgE.getElementsByTagName('img');
                    console.log("pinterest findAllMedias, imgE ");
                    if (imgE.length > 0) {
                        tagMedia(imgE[0], checkImgE, false);
                        hasImg = true;
                    }
                }
            }
        } else { // story图片
            var storyPicRoot = target.querySelectorAll('div[data-test-id="story-pin-full-bleed-slideshow-mweb"]')[0];
            if (storyPicRoot != null) {
                var imgEle = storyPicRoot.querySelectorAll('div[role="img"]');
                if (imgEle != null) {
                    for (var i = 0; i < imgEle.length; i++) {
                        tagMedia(imgEle[i], imgEle[i], true);
                        hasImg = true;
                    }
                }
            }
        }
        if (!hasImg) { // 排查imgViewer
            var imgViewerRoot = target.querySelectorAll('div[data-test-id="imgViewer"]')[0];
            if (imgViewerRoot != null) {
                // 找到图片 pin-closeup-image
                var checkImgE = imgViewerRoot.querySelectorAll('div[data-test-id="pin-closeup-image"]')[0];
                if (checkImgE != null) {
                    var imgEle = checkImgE.querySelectorAll('div[role="img"]');
                    if (imgEle != null && imgEle.length > 0) {
                        tagMedia(imgEle[0], imgEle[0], true);
                        hasImg = true;
                    }
                }
            }
        }
        if (hasImg) {
            ADAPTATION_HOLDER.setParseBtnType("adapter", "websiteBtn", "pinterest_parse.js"); // 仅适配、网页按钮
        } else {
            ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "pinterest_parse.js");
        }
    }

    var observerOptions = {
        childList: true, //story需要添加
        attributes: true, // 观察属性变动
        subtree: true,// 观察后代节点，默认为 false
        attributeFilter: ['style', 'class']
    }

    var observer = new MutationObserver(function (mutations, observer) {
        mutations.forEach((mutation) => {
            switch (mutation.type) {
                case 'childList':
                    mutation.addedNodes.forEach((node) => {
                        if ((node.tagName == "DIV" || node.tagName == "SECTION") && node.className != "ins_dl") {
                            findAllMedias(document, "childList")
                        }
                    });
                    break;
                case 'attributes':
                    var node = mutation.target;
                    if ((node.tagName == "DIV" || node.tagName == "SECTION") && node.className != "ins_dl") {
                        findAllMedias(document, "attributes")
                    }
                    break;
            }
        });

    });
    console.log("pinterest  ======== 开始监听元素变化 ===== ")
    findAllMedias(document, "all")
    observer.observe(document.body, observerOptions);
    console.log("pinterest 完成加载 <<<<<<<<<<<<<<<<<<")
}());