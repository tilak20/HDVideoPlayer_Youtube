javascript: (function () {
    var isClickCooling = true
    console.log("加载ins解析代码>>>>>>>>>")
    function warn(tag, msg = "") {
        ADAPTATION_HOLDER.warn(tag, msg);
    }
    function receiveJsParseResult(jsonResult) {
        ADAPTATION_HOLDER.receiveJsParseResult(jsonResult);
    }

    function isEmpty(obj) {
        if (typeof obj == "undefined" || obj == null || obj == "") {
            return true;
        } else {
            return false;
        }
    }

    var createBtn = function (top) {
        var div = document.createElement("DIV")
        div.className = "ins_dl";
        div.setAttribute("style", "position:absolute;top:" + top + ";left:0px;width: 50px; height: 50px; z-index:9999");
        div.innerHTML = '<img style="position:absolute;top:15px;bottom:0px;left:10px;right:0px;margin:auto;;width: 50px; height: 50px; object-fit: fill; pointer-events: none" src="data:image/png;base64,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" />';
        return div;
    }

    var tagMedia = function (rootNode, t, parentNode, isStory) {
        var tagName = rootNode.tagName
        var targetContainer = parentNode
        //已经注入的标签无须再次处理
        if (targetContainer.getAttribute("tag") == 1) {
            return;
        }
        var top = "0px"
        if (isStory) {
            top = "100px"
        }
        var div = createBtn(top)
        //注入下载按钮
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
            var namePrefix = "YTD"
            if (t != null) {
                namePrefix = t;
            }
            // // 判断是否采集当前article
            var data = null;
            var type = "jpg"
            var videoTag = parentNode.getElementsByTagName("video")[0];
            if (videoTag != null) {
                type = "mp4"
                var videoUrl = videoTag.src;
                if (!videoUrl) {
                    var sources = videoTag.getElementsByTagName("source")
                    if (sources.length > 0) {
                        videoUrl = sources[0].src
                    }
                }
                var thumbnailUrl = videoTag.poster
                if (thumbnailUrl && thumbnailUrl.indexOf("base64") >= 0) {
                    //story视频
                    var imgs = parentNode.getElementsByTagName("IMG")
                    var thumbnailUrl = ""
                    if (imgs.length > 0) {
                        thumbnailUrl = imgs[0].src
                    }
                }
                if (videoUrl && !videoUrl.startsWith("blob") && /[^\s]/.test(videoUrl)) {
                    var timestamp = Date.now()
                    data = {
                        name: timestamp + ".mp4",
                        sourceUrl: location.href,
                        quality: "sd",
                        fromUrl: location.href,
                        thumbnailUrl: thumbnailUrl,
                        mediaUrlList: [videoUrl]
                    };
                    console.log("videoData thumbnailUrl: " + data.thumbnailUrl + " mediaUrlList: " + data.mediaUrlList)
                } else {
                    warn("INS_VIDEO_URL_INVALID", videoUrl);
                    console.log("非法ins地址::: " + videoUrl)
                    return;
                }
            } else {
                var imgs = parentNode.getElementsByTagName("img")
                if (imgs.length > 0) {
                    if (isEmpty(imgs[0].className) && imgs.length > 1) {
                        imgUrl = imgs[1].src
                    } else {
                        imgUrl = imgs[0].src
                    }
                    console.log("imgUrl: " + imgUrl);
                    if (!isEmpty(imgUrl)) {
                        var timestamp = Date.now()
                        data = {
                            name: timestamp + ".jpg",
                            sourceUrl: location.href,
                            quality: "sd",
                            thumbnailUrl: imgUrl,
                            fromUrl: location.href,
                            mediaUrlList: [imgUrl]
                        }
                        if (data != null) {
                            console.log("imgData thumbnailUrl: " + data.thumbnailUrl + " mediaUrlList: " + data.mediaUrlList);
                        }
                    }
                }
            }
            if (data != null) {
                var resultData = {
                    'status': 'time_line',
                    'fromType': 'ins',
                    'showDialog': true,
                    'parseType': type,
                    'dataList': [data],
                    'fromUrl': location.href,
                    'dataSource': 'timeline===ins'
                }
                ADAPTATION_HOLDER.setParseBtnType("adapter", "websiteBtn", "detect_ins.js");
                receiveJsParseResult(JSON.stringify(resultData));
            } else {
                warn("INS_NO_DATA");
            }
        }
        targetContainer.appendChild(div);
        targetContainer.setAttribute("tag", 1)
    }

    function tagRealVideo(videoSrc, videoParent) {
    	var targetContainer = videoParent
    	//已经注入的标签无须再次处理
    	if (isEmpty(videoSrc) || targetContainer.getAttribute("tag") == 1) {
    		return;
    	}
    	div = createBtn("0px")
    	//注入下载按钮
    	div.onclick = function (event) {
    		if (!isClickCooling) {
    			return
    		}
    		isClickCooling = false
    		setTimeout(function () {
    			isClickCooling = true;
    		}, 1500);
    		event.stopPropagation();
    		// 采集用户信息
    		var videoUrl = videoSrc;
    		console.log("detect_ins videoUrl: " + videoUrl)
            var timestamp = Date.now()
            data = {
                name: timestamp + ".mp4",
                sourceUrl: location.href,
                quality: "sd",
                fromUrl: location.href,
                mediaUrlList: [videoUrl]
            };
            var resultData = {
                'status': 'time_line',
                'fromType': 'ins',
                'showDialog': true,
                'parseType': "mp4",
                'dataList': [data],
                'fromUrl': location.href,
                'dataSource': 'timeline===ins'
            }
            ADAPTATION_HOLDER.setParseBtnType("adapter", "websiteBtn", "detect_ins.reel.js");
            receiveJsParseResult(JSON.stringify(resultData));
    	}
    	targetContainer.appendChild(div);
    	targetContainer.setAttribute("tag", 1)
    }

    //获取所有的img标签
    var findAllMedias = function (target, from) {
        var currUrl = window.location.href;
        if (/https?:\/\/.*instagram\.com\/stories\.*\//.test(currUrl)) {
            //story页面 查找所有的section标签
            if (target.className == "section") {
                handleSection(target)
            } else {
                var sections = target.getElementsByTagName("section")
                if (sections.length > 0) {
                    for (var i = 0; i < sections.length; i++) {
                        handleSection(sections[i])
                    }
                }
            }
        } else if (/https?:\/\/.*instagram\.com\/reels\/videos\.*\//.test(currUrl)) {
            var mainE = document.getElementsByTagName("main")[0]
            if (mainE != null) {
                var mainChildOne = mainE.children[0]
                if (mainChildOne != null) {
                    var listParent = mainChildOne.children[0]
                    if (listParent != null) {
                        var list = listParent.childNodes
                        for (var i = 0; i < list.length; i++) {
                            var mediaItemE = list[i];
                            var videoInfoPPE = mediaItemE.children[0]
                            if (videoInfoPPE != null) {
                                var videoE = videoInfoPPE.getElementsByTagName("video")[0] // 视频信息
                                if(videoE != null){
                                    tagRealVideo(videoE.src, mediaItemE)
                                }
                            }
                        }
                    }
                }
            }
        } else {
            //找到所有的article标签
            if (target.className == "article") {
                handleArticle(target)
            } else {
                var articles = target.getElementsByTagName("article")
                if (articles.length > 0) {
                    for (var i = 0; i < articles.length; i++) {
                        handleArticle(articles[i])
                    }
                }
            }
        }
    }

    var handleArticle = function (article) {
        var isValid = false
        var ulE = article.getElementsByTagName("ul")[0]
        if (ulE != null) {
            var liEs = article.getElementsByTagName("li")
            if (liEs.length > 0) {
                for (var idx = 0; idx < liEs.length; idx++) {
                    var liE = liEs[idx]
                    var imgE = liE.getElementsByTagName("img")[0]
                    if (imgE != null) {
                        isValid = true
                        tagMedia(article.children[0], imgE, liE, false)
                    } else {
                        var videoE = liE.getElementsByTagName("video")[0]
                        if (videoE != null) {
                            isValid = true
                            tagMedia(article.children[0], videoE, liE, false)
                        }
                    }
                }
            }
        } else {
            var rootNode = article.children[0]
            if (rootNode != null) {
                var contentRoot = rootNode.children[1]
                if (contentRoot != null) {
                    var imgs = contentRoot.getElementsByTagName("img")
                    var imgE = imgs[0]
                    if (imgs.length == 1 && imgE.parentNode.className != "ins_dl") {
                        tagMedia(rootNode, imgs[0], imgs[0].parentNode, false)
                    } else {
                        var videos = contentRoot.getElementsByTagName("video")
                        if (videos.length == 1) {
                            tagMedia(rootNode, videos[0], videos[0].parentNode, false)
                        }
                    }
                }
            }
        }
        return isValid;
    }

    var handleSection = function (section) {
        var videoE = section.getElementsByTagName("video")[0];
        if (videoE != null) {
            tagMedia(section, videoE, videoE.parentNode, true)
        } else {
            var imgE = section.getElementsByTagName("img")[0];
            if (imgE != null) {
                tagMedia(section, imgE, imgE.parentNode, true)
            }
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
                        if ((node.tagName == "DIV" || node.tagName == "SECTION" || node.tagName == "LI" || node.tagName == "ARTICLE" || node.tagName == "IMG") && node.className != "ins_dl") {
                            findAllMedias(document, "childList")
                        }
                    });
                    break;
//                case 'attributes':
//                    var node = mutation.target;
//                    if ((node.tagName == "DIV" || node.tagName == "SECTION") && node.className != "ins_dl") {
//                        findAllMedias(document, "attributes")
//                    }
//                    break;
            }
        });
    });
    console.log(" ======== 开始监听元素变化 ===== ")
    findAllMedias(document, "all")
    observer.observe(document.body, observerOptions);
    console.log("完成加载 <<<<<<<<<<<<<<<<<<")
}());