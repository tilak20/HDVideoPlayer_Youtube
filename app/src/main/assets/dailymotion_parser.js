javascript: (function () {
    var ua_global = 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Mobile Safari/537.36'
    function getRefererContent() {
        var referer = document.referrer;
        if (referer === null || referer === '') {
            referer = location.href;
        }
        return referer;
    }

    function startDetect(){
        try {
            ADAPTATION_HOLDER.startDetectWebsite('dailymotion');
        } catch (error) {

        }
    }

    try {
        startDetect();
        var jsonData = "%atlasvARG1atlasv%";
        var ajax = new XMLHttpRequest();
        ajax.open('get', jsonData);
        ajax.setRequestHeader('Referer', "%atlasvARG2atlasv%");
        ajax.setRequestHeader('User-Agent', "%atlasvARG3atlasv%");
        ajax.send();
        ajax.onreadystatechange = function () {
            if (ajax.readyState === 4 && ajax.status === 200) {
                var videoResponse = JSON.parse(ajax.responseText);

                var title = videoResponse['title'];

                var qualities = videoResponse['qualities']['auto'];

                var i;
                var videoArray = [];
                for (i = 0; i < qualities.length; i++) {
                    videoArray[i] = {
                        'name': "YTD_" + title + ".mp4",
                        'duration': videoResponse['duration'],
                        'thumbnailUrl': videoResponse['posters']['60'],
                        'sourceUrl': document.location.href,
                        'fromUrl': document.location.href,
                        'headerReferer': getRefererContent(),
                        'headerUserAgent': ua_global,
                        'mediaUrlList': [qualities[i]['url']]
                    };
                }

                var resultData = {
                    'status': 'success',
                    'fromType': 'vimeo',
                    'parseType': 'hls',
                    'userAgent': ua_global,
                    'dataList': videoArray,
                    'fromUrl': location.href,
                    'dataSource': 'single===dailymotion'
                };
                ADAPTATION_HOLDER.receiveJsParseResult(JSON.stringify(resultData));
            }
        }
    } catch (error) {
    }

}());