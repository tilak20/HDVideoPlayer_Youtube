javascript: (function () {

    var ua_global = 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Mobile Safari/537.36'
    function getRefererContent() {
        var referer = document.referrer;
        if (referer === null || referer === '') {
            referer = location.href;
        }
        return referer;
    }

    var headDom = document.head;
    var titleName = headDom.getElementsByTagName('title')[0].text;

    var qualityStr = headDom.querySelector('meta[property="og:image:height"]').content + 'p';

    var bodyDom = document.body;
    var videoDiv = bodyDom.querySelector('div[id="VideoPlayerPage_1"]');
    var videoDom = videoDiv.getElementsByTagName('video')[0];
    var mediaUrl = videoDom.src;

    var thumbImg = headDom.querySelector('meta[property="twitter:image"]').content;

    var durationStr = bodyDom.querySelector('span[class="video-info-details__video-duration"]').innerHTML;
    var duration = new Date(durationStr).getTime();
    
    var videoArray = [{
        'name': titleName,
        'thumbnailUrl': thumbImg,
        'sourceUrl': location.href,
        'quality': qualityStr,
        'fromUrl': location.href,
        'headerReferer': getRefererContent(),
        'headerUserAgent': ua_global,
        'mediaUrlList': [mediaUrl]
    }];

    var resultData = {
        'status': 'success',
        'fromType': 'faphouse',
        'parseType': 'mp4',
        'dataList': videoArray,
        'fromUrl': location.href,
        'dataSource': 'single===faphouse'
    }
    ADAPTATION_HOLDER.receiveJsParseResult(JSON.stringify(resultData));
}());