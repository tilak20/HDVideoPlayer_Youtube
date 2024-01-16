javascript: (function () {
    var header = document.head;
    var title = header.querySelectorAll('title')[0].textContent;
    var imageEle = header.querySelector('link[rel = "image_src"]');
    var thumbnail = imageEle.href;
    var body = document.body;
    var downloadEle = body.querySelector('div.item-download > a');
    var duration = header.querySelector('meta[property = "video:duration"]').content;
    var quality = header.querySelector('meta[property = "og:video:height"]').content + 'p';
    var href = downloadEle.href + "";
    var index = href.indexOf('http', 10)
    var downloadUrl = href.substring(index, href.length);
    if (downloadUrl === null || downloadUrl === '') {
        ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "freeindianporn_parser.js");
        ADAPTATION_HOLDER.adapterWebsiteParseFail(location.href);
        return;
    }
    ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "freeindianporn_parser.js 2");
    videoArray = [{
        'name': title,
        'duration': parseFloat(duration),
        'thumbnailUrl': thumbnail,
        'sourceUrl': location.href,
        'quality': quality,
        'fromUrl': location.href,
        'mediaUrlList': [downloadUrl]
    }];
    var resultData = {
        'status': 'success',
        'fromType': 'freeindianporn',
        'parseType': 'mp4',
        'dataList': videoArray,
        'fromUrl': location.href,
        'dataSource': 'single===freeindianporn'
    }
    ADAPTATION_HOLDER.receiveJsParseResult(JSON.stringify(resultData));
}());