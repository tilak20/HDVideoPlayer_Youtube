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
        ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "bangspank_parser.default");
        ADAPTATION_HOLDER.adapterWebsiteParseFail(location.href);
    }
    
    try {
        var divEle = document.getElementById('inner_content');
        if (divEle === null) {
            startDefaultParse();
            return;
        }

        var regMediaLink = /(?<=var\sstream_data\s=\s).+(?=;)/;
        var content = divEle.textContent;
        var mediaMatch = content.match(regMediaLink);
        if (mediaMatch === null) {
            startDefaultParse();
            return;
        }
        var mediaLinkData = mediaMatch[0];
        if (mediaLinkData === null) {
            startDefaultParse();
            return;
        }
        var trueString = mediaLinkData.replace(/'/g, "\"")
        var mediaLinkDataO = JSON.parse(trueString)

        var thumbnail = mediaLinkDataO['thumbnail'];
        var duration = mediaLinkDataO['length'];

        var mediaDetailEle = divEle.querySelector('script[type = "application/ld+json"]')
        if (mediaDetailEle === null) {
            startDefaultParse();
            return;
        }

        var mediaDetailData = JSON.parse(mediaDetailEle.textContent);
        var title = mediaDetailData['name'];
        var qualityArray = ['240p', '320p', '480p', '720p', '1080p', '4k'];
        var videoArray = [];
        var index = 0;
        for (let i = 0; i < qualityArray.length; i++) {
            var qItemArray = mediaLinkDataO[qualityArray[i]];
            if (qItemArray !== null && qItemArray.length !== 0) {
                videoArray[index] = {
                    'name': title + ".mp4",
                    'duration': duration,
                    'thumbnailUrl': thumbnail,
                    'sourceUrl': location.href,
                    'quality': qualityArray[i],
                    'headerReferer': getRefererContent(),
                    'headerUserAgent': ua_global,
                    'fromUrl': location.href,
                    'mediaUrlList': [qItemArray[0]]
                }
                index++;
            }
        }

        if (videoArray.length === 0) {
            startDefaultParse();
            return;
        }
        ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "bangspank_parser.js");
        var resultData = {
            'status': 'success',
            'fromType': 'spankbang',
            'parseType': 'mp4',
            'dataList': videoArray,
            'fromUrl': location.href,
            'dataSource': 'single===spankbang'
        }

        ADAPTATION_HOLDER.receiveJsParseResult(JSON.stringify(resultData));
    } catch (error) {
        startDefaultParse();
    }
}());