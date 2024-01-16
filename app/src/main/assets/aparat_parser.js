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
        ADAPTATION_HOLDER.startDetectWebsite('aparat');
        var jsonData = "%atlasvARG1atlasv%";
        var ajax = new XMLHttpRequest();
        ajax.open('get', jsonData);
        ajax.setRequestHeader('Referer', "%atlasvARG2atlasv%");
        ajax.setRequestHeader('User-Agent', "%atlasvARG3atlasv%");
        ajax.send();
        ajax.onreadystatechange = function () {
            if (ajax.readyState === 4 && ajax.status === 200) {
                var data = JSON.parse(ajax.response)
                var videoAttr = data["data"]["attributes"];
                var title = videoAttr["title"];
                var duration = parseFloat(videoAttr["duration"]);
                var thumb = videoAttr["small_poster"];
                var videoList = videoAttr["file_link_all"];
                var i;
                var videoArray = [];
                for (i = 0; i < videoList.length; i++) {
                    videoArray[i] = {
                        'name': title,
                        'duration': duration,
                        'thumbnailUrl': thumb,
                        'sourceUrl': videoList[i]["urls"].toString(),
                        'fromUrl': document.location.href,
                        'quality': videoList[i]["profile"],
                        'headerReferer': getRefererContent(),
                        'headerUserAgent': ua_global,
                        'mediaUrlList': videoList[i]["urls"]
                    }
                }
                var resultData = {
                    'status': 'success',
                    'fromType': 'aparat',
                    'parseType': 'mp4',
                    'dataList': videoArray,
                    'fromUrl': location.href,
                    'dataSource': 'single===aparat'
                };
                ADAPTATION_HOLDER.receiveJsParseResult(JSON.stringify(resultData));
            }
        }
    } catch (error) {
    }

}());