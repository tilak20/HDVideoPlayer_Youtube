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
        var searchStr = location.search;
        var pathnameStr = location.pathname;
        if (searchStr === '' && pathnameStr.search('/gif/') !== -1) {

            var docHead = document.head;
            var thumbImg = docHead.querySelector('meta[property="og:image"]').content;
            var title = docHead.getElementsByTagName('title')[0].text;
            var mediaUrl = docHead.querySelector('meta[name="twitter:player:stream"]').content;
            var quality = docHead.querySelector('meta[name="twitter:player:height"]').content + 'p';

            var videoArray = [{
                'name': title + ".mp4",
                'thumbnailUrl': thumbImg,
                'sourceUrl': location.href,
                'quality': quality,
                'headerReferer': getRefererContent(),
                'headerUserAgent': ua_global,
                'mediaUrlList': [mediaUrl]
            }];
            var resultData = {
                'status': 'success',
                'fromType': 'pornhub',
                'parseType': 'mp4',
                'dataList': videoArray,
                'fromUrl': location.href,
                'dataSource': 'single===pornhub'
            }
            ADAPTATION_HOLDER.receiveJsParseResult(JSON.stringify(resultData));
            return
        }

        if(searchStr === ''){
            return
        }

        var mobileContainer = document.getElementById('mobileContainer');
        var scriptElements = mobileContainer.getElementsByTagName('script');
        var i;
        for (i = 0; i < scriptElements.length; i++) {
            var sItem = scriptElements[i];
            if (sItem.type !== 'text/javascript') {
                continue;
            }

            var sConentValue = sItem.innerHTML;
            var pattern = /var\sflashvars_.*\s=\s(.*);/;
            var matchValue = sConentValue.match(pattern);
            if (matchValue === null) {
                continue;
            }
            var parseValue = JSON.parse(matchValue[1]);

            var videoTitle = parseValue['video_title'];
            var thumbnailUrl = parseValue['image_url'];
            var duration = parseValue['video_duration'];

            var mediaDefinitions = parseValue['mediaDefinitions'];
            var j;
            var videoArray = []
            var videoRequstUrl;
            for (j = 0; j < mediaDefinitions.length; j++) {
                var itemMedia = mediaDefinitions[j];
                if (itemMedia['defaultQuality']) {
                    continue;
                }
                if (itemMedia['format'] !== 'mp4') {
                    continue;
                }
                videoRequstUrl = itemMedia['videoUrl'];
            }

            var videoArray = [];
            var ajax = new XMLHttpRequest();
            ajax.open('get', videoRequstUrl);
            ajax.cookies = document.cookies;
            ajax.send();

            ajax.onreadystatechange = function () {
                var ajaxStatus = ajax.status;
                // test code
                // ajaxStatus = 404;
                if (ajax.readyState === 4 && ajaxStatus === 200) {

                    var videoListArray = JSON.parse(ajax.responseText);
                    var i;
                    for (i = 0; i < videoListArray.length; i++) {
                        videoArray[i] = {
                            'name': videoTitle + ".mp4",
                            'duration': duration,
                            'thumbnailUrl': thumbnailUrl,
                            'sourceUrl': location.href,
                            'quality': videoListArray[i]['quality'] + "P",
                            'headerReferer': getRefererContent(),
                            'headerUserAgent': ua_global,
                            'fromUrl': location.href,
                            'mediaUrlList': [videoListArray[i]['videoUrl']]
                        }
                    }

                    var pornCookies = document.cookie;

                    var resultData = {
                        'status': 'success',
                        'fromType': 'pornhub',
                        'cookies': pornCookies,
                        'parseType': 'mp4',
                        'dataList': videoArray,
                        'fromUrl': location.href,
                        'dataSource': 'single===pornhub'
                    }
                    ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "phub_parser.js");
                    ADAPTATION_HOLDER.receiveJsParseResult(JSON.stringify(resultData));
                } else if(ajaxStatus >= 400 && ajaxStatus <= 599){
                    ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "phub_parser.js");
                    ADAPTATION_HOLDER.adapterWebsiteParseFail(location.href);
                }
            }
        }
    } catch (error) {
        console.log(error)
    }

}());