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
        var data = window.vimeo.clip_page_config;
        var title = data["clip"]["title"];
        var duration = data["clip"]["duration"]["raw"];
        if (duration == 0) {
            duration = -1;
        }
        var loadUrl = data["player"]["config_url"];
        var thumb = data["thumbnail"]["src"]

        if (loadUrl !== null) {
            var ajax = new XMLHttpRequest();
            ajax.open('get', loadUrl);
            ajax.send();
            ajax.onreadystatechange = function () {
                var ajaxStatus = ajax.status;
                if (ajax.readyState === 4 && ajaxStatus === 200) {
                    var videoData = JSON.parse(ajax.responseText);
                    var videoArray = videoData["request"]["files"]["progressive"]
                    var resultVideoArray = [];
                    var i;
                    for (i = 0; i < videoArray.length; i++) {
                        resultVideoArray[i] = {
                            'name': "YTD_" + title + ".mp4",
                            'duration': duration,
                            'thumbnailUrl': thumb,
                            'sourceUrl': location.href,
                            'quality': videoArray[i]['quality'],
                            'fromUrl': location.href,
                            'headerReferer': getRefererContent(),
                            'headerUserAgent': ua_global,
                            'mediaUrlList': [videoArray[i]['url']]
                        }
                    };
                    var resultData = {
                        'status': 'success',
                        'fromType': 'vimeo',
                        'parseType': 'mp4',
                        'dataList': resultVideoArray,
                        'fromUrl': location.href,
                        'dataSource': 'single===vimeo'
                    };
                    ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "vimeo.js");
                    ADAPTATION_HOLDER.receiveJsParseResult(JSON.stringify(resultData));
                } else if (ajaxStatus >= 400 && ajaxStatus <= 599) {
                    ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "vimeo.js");
                    ADAPTATION_HOLDER.adapterWebsiteParseFail(location.href);
                }
            }
        } else {
            ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "vimeo.js 2");
            ADAPTATION_HOLDER.adapterWebsiteParseFail(location.href);
        }
    } catch (error) {
    }

}());