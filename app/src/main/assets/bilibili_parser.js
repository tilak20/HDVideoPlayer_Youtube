javascript: (function () {
    console.log('enter bilibili parse');
    var lastMediaUrl = null

    function findMedia() {
        // var player = window.player;
        //    for(let key in player){
        //        console.log('bilibili player key:' + key + " value: " + (player[key]));
        //    }
        var videoE = document.getElementsByTagName("video")[0]
        var thumbnailUrl = ""
        var mediaUrl = ""
        if (videoE != null) {
            mediaUrl = videoE.src;
            if (mediaUrl === lastMediaUrl) {
                return
            }
            thumbnailUrl = videoE.poster;
        }
        console.log("mediaUrl: " + mediaUrl)
        if (mediaUrl == null) {
            // startDefaultParse();
            return
        }
        lastMediaUrl = mediaUrl;
        var titleE = document.getElementsByTagName("title")[0]
        var title = ""
        if (titleE != null) {
            title = titleE.innerText
        }
        console.log("title: " + title)
        // var duration = player.getDuration()
        // if (isNaN(duration)) {
        //     duration = 0;
        // }
        // console.log("duration: " + duration)

        videoArray = [{
            'name': title,
            // 'duration': parseFloat(duration),
            'thumbnailUrl': thumbnailUrl,
            'quality': "HD",
            'sourceUrl': location.href,
            'fromUrl': location.href,
            'mediaUrlList': [mediaUrl]
        }];
        var resultData = {
            'status': 'success',
            'fromType': 'pornfactory',
            'parseType': 'mp4',
            'dataList': videoArray,
            'fromUrl': location.href,
            'dataSource': 'single===bilibili'
        }
        ADAPTATION_HOLDER.receiveJsParseResult(JSON.stringify(resultData));
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
                        if ((node.tagName == "DIV" || node.tagName == "video")) {
                            findMedia()
                        }
                    });
                    break;
                case 'attributes':
                    var node = mutation.target;
                    if (node.tagName == "video") {
                        findMedia()
                    }
                    break;
            }
        });
    });
    console.log(" ======== 开始监听元素变化 ===== ")
    observer.observe(document.body, observerOptions);
    findMedia();
    console.log('exit bilibili parse');
}());