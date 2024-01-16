javascript: (function () {
    var ua_global = 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Mobile Safari/537.36'

    function getRefererContent() {
        var referer = document.referrer;
        if (referer === null || referer === '') {
            referer = location.href;
        }
        return referer;
    }

    try {
        var videoArray = []

        var dataContent = window.initials;
        var videoEntity = dataContent["videoEntity"];
        var titleStr = videoEntity["title"];
        var duration = videoEntity["duration"];
        var thumbLink = videoEntity["thumbBig"];

        var xplayerData = dataContent["xplayerSettings"];
        var sourcesData = xplayerData["sources"];
        var vda = sourcesData["standard"]["mp4"];
        var i;
        var index = 0;
        for (i = 0; i < vda.length; i++) {
            var item = vda[i]
            var qualityStr = item["quality"]
            if (qualityStr === 'auto') {
                continue;
            }
            index++;
            var mediaUrl = item.url.toString();
            if (mediaUrl.search(',http') !== -1) {
                mediaUrl = mediaUrl.split(',http')[0];
            }
            var resultItem = {
                'name': titleStr + ".mp4",
                'duration': duration,
                'thumbnailUrl': thumbLink,
                'sourceUrl': location.href,
                'quality': qualityStr,
                'fromUrl': location.href,
                'headerReferer': getRefererContent(),
                'headerUserAgent': ua_global,
                'mediaUrlList': [mediaUrl]
            };
            videoArray[index - 1] = resultItem;
        }
        if (videoArray.length === 0) {
            ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "xham_parser.js");
            ADAPTATION_HOLDER.adapterWebsiteParseFail(location.href);
            return;
        }
        var resultData = {
            'status': 'success',
            'fromType': 'xhamster',
            'parseType': 'mp4',
            'dataList': videoArray,
            'fromUrl': location.href,
            'dataSource': 'single===xhamster'
        }
        ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "xham_parser.js");
        ADAPTATION_HOLDER.receiveJsParseResult(JSON.stringify(resultData));
    } catch (error) {
    }

}());