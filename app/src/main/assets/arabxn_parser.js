javascript: (function () {
    var ua_global = 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Mobile Safari/537.36'

    function getRefererContent() {
        var referer = document.referrer;
        if (referer === null || referer === '') {
            referer = location.href;
        }
        return referer;
    }

    function startDefaultParse() {
        ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "arabxn_parser.js Default");
        ADAPTATION_HOLDER.adapterWebsiteParseFail(location.href);
    }

    var head = document.head;
    var titleEle = head.querySelector('meta[name = "title"]');
    var title = titleEle.content;

    var body = document.body;
    var videoEle = body.querySelector('video[id = "my-video"]');
    if (videoEle === null) {
        startDefaultParse();
        return;
    }
    var thumbnail = videoEle.poster;
    var videoEles = videoEle.children
    if (videoEles === null || videoEles.length === 0) {
        startDefaultParse();
        return;
    }
    ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "arabxn_parser.js");
    var videoArray = [];
    for (let i = 0; i < videoEles.length; i++) {
        var item = videoEles[i]
        var downloadUrl = item.src;
        var quality = item.title;
        videoArray[i] = {
            'name': title + ".mp4",
            'thumbnailUrl': thumbnail,
            'sourceUrl': location.href,
            'quality': quality,
            'fromUrl': location.href,
            'headerReferer': getRefererContent(),
            'headerUserAgent': ua_global,
            'mediaUrlList': [downloadUrl]
        }
    }
    var resultData = {
        'status': 'success',
        'fromType': 'arabxn',
        'parseType': 'mp4',
        'dataList': videoArray,
        'fromUrl': location.href,
        'dataSource': 'single===arabxn'
    }
    ADAPTATION_HOLDER.receiveJsParseResult(JSON.stringify(resultData));
}());