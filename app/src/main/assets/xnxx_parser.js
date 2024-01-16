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
        var contentStr = document.body.innerHTML;
        var titlePattern = /setVideoTitle*\(\s*([\"'])(?<value>(?:(?!\1).)+)\1/;
        var thumbPattern = /setThumbUrl169*\(\s*([\"'])(?<value>(?:(?!\1).)+)\1/;
        var hlsPattern = /setVideoHLS*\(\s*([\"'])(?<value>(?:(?!\1).)+)\1/;
        var hlsMatch = contentStr.match(hlsPattern);

        if (hlsMatch == null) {
            ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "xnxx_parser.js hlsMatch = null");
            ADAPTATION_HOLDER.adapterWebsiteParseFail(location.href);
            return;
        }
        ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "xnxx_parser.js");
        var hlsLink = hlsMatch[2];
        hlsLink = hlsLink.replace('hls_low', 'hls');
        var videoTitleMatch = contentStr.match(titlePattern)[2];

        var mateElements = document.getElementsByTagName('meta');
        var i;
        var duration = 0;
        for (i = 0; i < mateElements.length; i++) {
            if (mateElements[i].getAttribute('property') !== 'og:duration') {
                continue;
            }
            duration = mateElements[i].getAttribute('content');
        }
        var thumbMatch = contentStr.match(thumbPattern)[2];
        var videoArray = [];
        videoArray[0] = {
            'name': videoTitleMatch + ".mp4",
            'duration': duration,
            'thumbnailUrl': thumbMatch,
            'headerReferer': getRefererContent(),
            'headerUserAgent': ua_global,
            'sourceUrl': location.href,
            'fromUrl': location.href,
            'mediaUrlList': [hlsLink]
        };
        var resultData = {
            'status': 'success',
            'fromType': 'xnxx',
            'mediaUrlIsHls': true,
            'parseType': 'hls',
            'userAgent': ua_global,
            'dataList': videoArray,
            'fromUrl': location.href,
            'dataSource': 'single===xnxx/xvideos'
        }
        ADAPTATION_HOLDER.receiveJsParseResult(JSON.stringify(resultData));
    } catch (error) {
    }

}());