javascript: (function () {
    console.log('enter capcut parse');
    var hasFind = false;

    function findMedia() {
        if (hasFind) {
            return;
        }
        var titleE = document.getElementsByClassName("VD22lu202vitpJYvi0OV")[0]
        var title = ""
        if (titleE != null) {
            title = titleE.innerText
        } else {
            title = "capcut_" + Date.now()
        }
        var videoEles = document.getElementsByTagName("video");
        if (videoEles.length > 0) {
            var videoE = videoEles[0];
            var mediaUrl = videoE.src;
            if (mediaUrl != null) {
                var thumb = videoE.poster;
                var videoArray = [{
                    'name': title,
                    'thumbnailUrl': thumb,
                    'quality': "HD",
                    'fromUrl': location.href,
                    'sourceUrl': location.href,
                    'mediaUrlList': [mediaUrl]
                }];
                var resultData = {
                    'status': 'success',
                    'fromType': 'capcut',
                    'parseType': 'mp4',
                    'dataList': videoArray,
                    'fromUrl': location.href,
                    'dataSource': 'single===capcut'
                }
                hasFind = true;
                ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "capcut_parser.js");
                ADAPTATION_HOLDER.receiveJsParseResult(JSON.stringify(resultData));
                return;
            }
        }
        ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "capcut_parser.js");
    }
    findMedia();
    if (!hasFind) {
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
                            if ((node.tagName == "DIV" || node.tagName == "video")) {
                                findMedia()
                            }
                        });
                        break;
                    case 'attributes':
                        var node = mutation.target;
                        if ((node.tagName == "DIV" || node.tagName == "video")) {
                            findMedia()
                        }
                        break;
                }
            });
        });
        console.log(" ======== 开始监听元素变化 ===== ")
        observer.observe(document.body, observerOptions);
    }
    console.log('exit capcut parse');
}());