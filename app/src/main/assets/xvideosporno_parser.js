javascript: (function () {

    function startDefaultParse() {
        ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "xvideosporno_parser.js default");
        ADAPTATION_HOLDER.adapterWebsiteParseFail(location.href);
    }

    var headDom = document.head;
    var durationStr = headDom.querySelector('meta[property="video:duration"]').content;
    var duration = parseFloat(durationStr);
    var title = headDom.getElementsByTagName('title')[0].text;

    var bodyDom = document.body;
    var videoContentDom = bodyDom.querySelector('div[id="video-content"]');
    var quality = videoContentDom.querySelector('meta[itemprop="height"]').content + 'p';
    var thumb = videoContentDom.querySelector('link[itemprop="thumbnailUrl"]').href;

    var videoDom = bodyDom.querySelector('div[class="videosection"]');
    if (videoDom === null) {
        startDefaultParse();
        return;
    }
    var pathDom = videoDom.getElementsByTagName('source')[0];
    if (pathDom === null) {
        startDefaultParse();
        return;
    }
    var mediaUrl = pathDom.src;
    if (mediaUrl === null || mediaUrl === '') {
        startDefaultParse();
        return;
    }
    ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "xvideosporno_parser.js");
    videoArray = [{
        'name': title + ".mp4",
        'duration': duration,
        'thumbnailUrl': thumb,
        'quality': quality,
        'sourceUrl': location.href,
        'fromUrl': location.href,
        'mediaUrlList': [mediaUrl]
    }];
    var resultData = {
        'status': 'success',
        'fromType': 'xvideosporno',
        'parseType': 'mp4',
        'dataList': videoArray,
        'fromUrl': location.href,
        'dataSource': 'single===xvideosporno'
    }
    ADAPTATION_HOLDER.receiveJsParseResult(JSON.stringify(resultData));
}());