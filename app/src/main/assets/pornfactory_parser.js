javascript: (function () {
    var head = document.head;
    var titleEle = head.querySelector('title');
    var title = titleEle.textContent;

    var downloadUrlEle = head.querySelector('meta[property = "og:video:url"]')
    var downloadUrl = downloadUrlEle.content;
    if (downloadUrl === null || downloadUrl === '') {
        ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "pornfactory_parser.js");
        ADAPTATION_HOLDER.adapterWebsiteParseFail(location.href);
        return;
    }

    var durationEle = head.querySelector('meta[property = "video:duration"]');
    var duration = durationEle.content;

    var thumbnailEle = head.querySelector('meta[property = "og:image"]');
    var thumbnail = thumbnailEle.content;

    var qualityEle = head.querySelector('meta[property = "video:duration"]');
    var quality = qualityEle.content + "p";

    videoArray = [{
        'name': title,
        'duration': parseFloat(duration),
        'thumbnailUrl': thumbnail,
        'sourceUrl': location.href,
        'fromUrl': location.href,
        'quality': quality,
        'mediaUrlList': [downloadUrl]
    }];
    var resultData = {
        'status': 'success',
        'fromType': 'pornfactory',
        'parseType': 'mp4',
        'dataList': videoArray,
        'fromUrl': location.href,
        'dataSource': 'single===pornfactory'
    }
    ADAPTATION_HOLDER.receiveJsParseResult(JSON.stringify(resultData));
}());