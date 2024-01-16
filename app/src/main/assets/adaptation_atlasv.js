javascript: (function () {

    function getWebUrl() {
        return ADAPTATION_HOLDER.getWebUrl()
    }

    function getHost() {
        return ADAPTATION_HOLDER.getHost()
    }

    var date = new Date()
    console.log("enter adaptation_atlasv.js " + date + " curHref: " + getWebUrl())

    var ua_global = 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Mobile Safari/537.36'
    var regPornhub = RegExp(/https?:\/\/(?:(?:[^\/]+\.)?(?:(pornhub(?:premium)?\.(?:com|net|org))|pornhubvybmsymdol4iibwgwtkpwmeyd6luq2gxajgjzfjvotyt5zhyd\.onion)\/(?:(?:view_video\.php|video\/show)\?viewkey=|embed\/)|(?:www\.)?thumbzilla\.com\/video\/)([\da-z]+)/);
    // var regFreeindianporn = RegExp(/freeindianporn\d/);
    // var regArabxn = RegExp(/.*\.arab.*xn\..*/);
    // var regPornfactory = RegExp(/pornfactory/);
    var regXhamster = RegExp(/https?:\/\/(?:.+?\.)?(?:xhamster\.(?:com|one|desi)|xhms\.pro|xhamster\d+\.com|xhday\.com)\/(?:movies\/([\dA-Za-z]+)\/([^\/]*)\.html|videos\/([^\/]*)-([\dA-Za-z]+))/);
    var regXnxx = RegExp(/https?:\/\/(?:video|www)\.xnxx3?\.[com|tv]+\/video\-?(?:[0-9a-z]+)\//);
    // var regXnxxClub = RegExp(/xnxx\.club/);
    // var regXvideosBlog = RegExp(/xvideosporno\.blog\.br/);
    var regXvideos = RegExp(/https?:\/\/(?:(?:[^/]+\.)?xvideos2?\.com\/video|(?:www\.)?xvideos\.es\/video|(?:www|flashservice)\.xvideos\.com\/embedframe\/|static-hw\.xvideos\.com\/swf\/xv-player\.swf\?.*?\bid_video=)([0-9]+)/);
    var regVimeo = RegExp(/vimeo\.com/);
    var regDailymotion = RegExp(/dailymotion\.com/);
    // var regSpankbang = RegExp(/spankbang/);
    var regFaphouse = RegExp(/faphouse/);
    var regCapcut = RegExp(/capcut/);
    var regBilibli = RegExp(/bilibili\./);
    var regYoutube = RegExp(/youtube/i);
    var regIjavhd = RegExp(/https?:\/\/ijavhd\.(com|net|org)\/video/)
    var regPornogramxxx = RegExp(/https?:\/\/pornogramxxx\.(com|net|org)\/sexvideo/)
    var regXxxtube = RegExp(/https?:\/\/xxxtube.*\.(com|net|org)\/v\//)

    //add timeline website tanpan@20220328
    var reg9gag = RegExp(/9gag/)
    var regIns = RegExp(/instagram/);
    var reg500px = RegExp(/500px/);
    var regFaceBook = RegExp(/facebook/);
    var regTwitter = RegExp(/twitter/)
    var regTubidy = RegExp(/tubidy/);
    var regPinterest = RegExp(/pinterest/);

    function isTargetXnxxWebsite(href) {
        return href.match(regXnxx)
    }

    function isTargetXvideosWebsite(href) {
        return href.match(regXvideos)
    }

    function isXhaWebsite(href) {
        return href.match(regXhamster)
    }

    function isTargetPornhubWebsite(href) {
        return href.match(regPornhub)
    }

    function isTargetIjavhdWebsite(href) {
        return href.match(regIjavhd)
    }

    function isTargetPornogramxxxWebsite(href) {
        return href.match(regPornogramxxx)
    }

    function isTargetXxxtubeWebsite(href) {
        return href.match(regXxxtube)
    }

    var oldHref = getWebUrl();
    var oldHost = getHost();

    function getRefererContent() {
        var referer = document.referrer;
        if (referer === null || referer === '') {
            referer = getWebUrl();
        }
        return referer;
    }

    function reportAdapterRule(ruleString) {
        var host = getHost()
        var link = getWebUrl()
        var data = {
            'rule': ruleString,
            'host': host,
            'link': link
        }
        ADAPTATION_HOLDER.reportAdapterRule(JSON.stringify(data));
    }

    //=============================监听代码放这里-start========================================

    function reportXnxxParseFail2GoogleForm(errorLine, loadingState) {
        var href = location.href
        if (isTargetXnxxWebsite(href)) {
            ADAPTATION_HOLDER.reportXnxxParseFail2GoogleForm(errorLine, loadingState, href)
        }
    }

    checkUrlChanged = function (curHost, curHref) {
        console.log("adaptation_atlasv.js checkUrlChanged date: " + date + ", document state: " + document.readyState);
        if (document.readyState === 'loading') {
            return;
        }
        if (document.body === null) {
            return;
        }
        if (document.body.innerHTML.length <= 0) {
            return;
        }
        console.log("href changed curHref:" + curHref + ", oldHref: " + oldHref)
        if (curHref.match(regDailymotion)) {
            parseDailymotion();
        } else if (curHref.match(regVimeo)) {
            parseVimeo(curHref);
        } else if (curHref.match(regBilibli)) {
            parseBilibili();
        } else if (curHref.match(regTubidy)) {
            parseTubidy()
        } else if (curHost.match(regPinterest)) {
            ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "pinterest");
        } else if (!isAdaptationWebsite(curHost, curHref)) {
            console.log("未适配的网站：", curHost)
            ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "atlasv 未适配的网站");
        }
        oldHref = curHref;
        oldHost = curHost;
    }

    // 统一适配解析
    adapterUniteCallback = function () {
        var isAdapterUnite = parseXhamster(false)
            || parseXnxx(false)
            || parseSpankbang()
            || parseFreeindianporn()
            || parsePornfactory()
            || parseArabxn()
            || parseXvideosporno()
            || parseTenor()
        ADAPTATION_HOLDER.adapterUniteCallback(isAdapterUnite);
    }

    /**
     * 监听 界面刷新 关闭 路由等动作
     */
    window.addEventListener('beforeunload', clearVideoListData(getWebUrl()));
    console.log("atlasv onreadystatechange init state: " + document.readyState)
    if (document.readyState === 'interactive') {
        loadSinglePlayWebsite();
    } else if (document.readyState === 'complete') {
        loadTimeLineWebsite();
    }

    document.addEventListener('readystatechange', (event) => {
        console.log("atlasv onreadystatechange new state: " + document.readyState)
        if (event.target.readyState === 'interactive') {
            loadSinglePlayWebsite();
        } else if (event.target.readyState === 'complete') {
            loadTimeLineWebsite();
        }
    });

    function loadTimeLineWebsite() {
        var sourceUrl = getHost();
        if (sourceUrl === null) {
            console.log("loadTimeLineWebsite sourceUrl is empty");
            return;
        }
        if (sourceUrl.match(reg9gag)) {
            parse9gag();
        } else if (sourceUrl.match(regFaceBook)) {
            parseFacebook();
        } else if (sourceUrl.match(regIns)) {
            parseIns();
        } else if (sourceUrl.match(reg500px)) {
            parse500px();
        } else if (sourceUrl.match(regTwitter)) {
            parseTwitter();
        } else if (sourceUrl.match(regCapcut)) {
            parseCapcut();
        } else if (sourceUrl.match(regYoutube)) {
            // youtube不支持解析
            ADAPTATION_HOLDER.setParseBtnType("none", "none", "atlasv youtube不支持解析");
        }
    }

    function loadSinglePlayWebsite() {
        var sourceUrl = getHost();
        var sourceHref = getWebUrl();
        if (sourceUrl === null || sourceHref == null) {
            console.log("loadSinglePlayWebsite sourceUrl is empty");
            return;
        }
        if (sourceUrl.match(regCapcut)) {
            // parseCapcut();
        } else if (sourceUrl.match(reg9gag)) {
            // parse9gag();
        } else if (sourceUrl.match(regFaceBook)) {
            // parseFacebook();
        } else if (sourceUrl.match(regIns)) {
            // parseIns();
        } else if (sourceUrl.match(reg500px)) {
            // parse500px();
        } else if (sourceUrl.match(regTwitter)) {
            // parseTwitter();
        } else if (sourceUrl.match(regYoutube)) {
            // youtube不支持解析
            ADAPTATION_HOLDER.setParseBtnType("none", "none", "atlasv youtube不支持解析 Single");
        } else if (isTargetPornhubWebsite(sourceHref)) {
            parsePornhub();
        } else if (sourceUrl.match(regVimeo)) {
            parseVimeo(sourceHref);
        } else if (sourceUrl.match(regDailymotion)) {
            parseDailymotion();
        } else if (sourceUrl.match(regFaphouse)) {
            parseFaphouse();
        } else if (sourceHref.match(regBilibli)) {
            parseBilibili();
        } else if (isTargetXnxxWebsite(sourceHref)) {
            startDetect('Xnxx')
            parseXnxx(true)
        } else if (isTargetXvideosWebsite(sourceHref)) {
            startDetect('Xvideos')
            parseXnxx(true)
        } else if (isXhaWebsite(sourceHref)) {
            startDetect('Xhamster')
            parseXhamster(true)
        } else if (sourceUrl.match(regTubidy)) {
            parseTubidy()
        } else if (sourceUrl.match(regPinterest)) {
            ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "pinterest");
        } else {
            console.log("loadSinglePlayWebsite", "未解析到:" + sourceUrl + " sourceHref: " + sourceHref);
            ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "loadSinglePlayWebsite other");
        }
    }

    /**
     * 
     * @returns 改网站是否已适配
     */
    function isAdaptationWebsite(curHost, curHref) {
        console.log("isAdaptationWebsite: ", curHost)
        return (isTargetXnxxWebsite(curHref) ||
            isTargetXvideosWebsite(curHref) ||
            isXhaWebsite(curHref) ||
            curHost.match(regVimeo) ||
            curHost.match(regDailymotion) ||
            curHost.match(regIns) ||
            curHost.match(reg9gag) ||
            curHost.match(regTwitter) ||
            curHost.match(regFaphouse) ||
            curHost.match(regCapcut) ||
            curHref.match(regBilibli) ||
            curHref.match(regTubidy) ||
            curHost.match(regYoutube) ||
            curHost.match(regFaceBook)) ||
            curHost.match(regPinterest)
    }

    //=============================监听代码放这里-end==========================================

    //=============================原生交互代码写这里-start=====================================

    /**
     * 清空界面缓存数据
     */
    function clearVideoListData(url) {
        ADAPTATION_HOLDER.userChangePage(url);
    }

    /**
     * 通知原生 处理解析数据
     * @param {原生处理的json数据 字符型} data 
     */
    function notifyNativeDealData(data) {
        ADAPTATION_HOLDER.receiveJsParseResult(data);
    }

    //=============================原生交互代码写这里-end=======================================


    //=============================适配过的网站写这里-start=====================================

    function startDetect(website) {
        try {
            ADAPTATION_HOLDER.startDetectWebsite(website);
        } catch (error) {

        }
    }

    function parseTenor() {
        return false;
        try {
            var header = document.head;

            var videoEle = header.querySelector('meta[property="og:video"]');
            var downloadUrl = videoEle.content;
            if (downloadUrl === null || downloadUrl === '') {
                return false;
            }
            reportAdapterRule("all_fit_reveal_pass8")
            var videoTypeEle = header.querySelector('meta[property="og:video:type"]');
            if (videoTypeEle === null || videoTypeEle.content.search("video/") === -1) {
                return false;
            }
            var title = header.querySelectorAll('title')[0].textContent;
            var quality = header.querySelector('meta[property = "og:video:height"]').content + 'p';
            var href = getWebUrl();
            videoArray = [{
                'name': title + ".mp4",
                'sourceUrl': href,
                'quality': quality,
                'fromUrl': href,
                'mediaUrlList': [downloadUrl]
            }];
            var resultData = {
                'status': 'success',
                'fromType': 'tenor',
                'parseType': 'mp4',
                'dataList': videoArray,
                'fromUrl': href,
                'dataSource': 'adapter_unite===tenor'
            }
            console.log("tenor: " + href)
            ADAPTATION_HOLDER.setParseBtnType("adapter_unite", "nativeBtn", "tenor");
            notifyNativeDealData(JSON.stringify(resultData))
            reportAdapterRule("all_fit_reveal_success8")
            return true
        } catch (error) {
            return false;
        }
    }

    function parseDailymotion() {
        var searchStr = location.search;
        var pathnameStr = location.pathname;
        var regPathname = RegExp(/(\/video\/|\/playlist\/).+/);
        if (pathnameStr.match(regPathname) === null) {
            console.log("parseDailymotion not match")
            ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "not match dailymotion pathname");
            return;
        }
        ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "atlasv parseDailymotion");
        var resultData = {
            'status': 'success',
            'fromType': 'dailymotion',
            'parseType': 'json',
            'jsName': 'dailymotion_parser.js',
            'webNativeParseData': {
                'urlRegStr': "https://www.dailymotion.com/player/metadata/video(.*)",
                'dealResultStr': "parseAparatUrl",
                'placeHolderArray': [{
                    '%atlasvARG1atlasv%': "websiteurl"
                },
                {
                    '%atlasvARG2atlasv%': document.referrer
                },
                {
                    '%atlasvARG3atlasv%': ua_global
                },
                ]
            },
            'fromUrl': getWebUrl()
        };
        notifyNativeDealData(JSON.stringify(resultData));
    }

    function isNullOrEmpty(content) {
        return content === null || content.length <= 0;
    }

    function dealVimeoWebsiteContent(ajaxResponseText) {
        var targetMatch = ajaxResponseText.match(/window\.vimeo\.clip_page_config = (.*)\;/)
        if (targetMatch === null || targetMatch.length === 0) {
            return false
        }
        var targetStr = targetMatch[1]
        var data = JSON.parse(targetStr)

        if (data === null) {
            return false;
        }
        var playerData = data["player"];
        if (playerData === null) {
            return false;
        }
        var configUrl = playerData["config_url"];
        if (configUrl === null || configUrl.length <= 0) {
            return false;
        }
        ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "vimeo.js");
        startDetect('vimeo')
        var ajax = new XMLHttpRequest();
        ajax.open('get', configUrl);
        ajax.send();
        ajax.onreadystatechange = function () {
            var ajaxStatus = ajax.status;
            if (ajax.readyState === 4 && ajaxStatus === 200) {
                var configData = JSON.parse(ajax.responseText);
                var videoInfo = configData['video']

                var title = videoInfo["title"];
                title = title.replace(' ', '')
                var duration = videoInfo["duration"];
                if (duration == 0) {
                    duration = -1;
                }
                var thumb = videoInfo["thumbs"]["base"]

                var fileData = configData["request"]["files"]

                var videoArray = fileData["progressive"]
                if (videoArray !== null && videoArray.length > 0) {
                    var resultVideoArray = [];
                    var i;
                    for (i = 0; i < videoArray.length; i++) {
                        resultVideoArray[i] = {
                            'name': title + ".mp4",
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
                } else {
                    var cdnsData = fileData["dash"]["cdns"];

                    var masterJsonData = cdnsData["akfire_interconnect_quic"]

                    var masterJsonStr = masterJsonData["url"]

                    if (isNullOrEmpty(masterJsonStr)) {
                        return
                    }
                    ajax = new XMLHttpRequest();
                    ajax.open('get', masterJsonStr);
                    ajax.send();
                    ajax.onreadystatechange = function () {
                        if (ajax.readyState === 4 && ajax.status === 200) {
                            var responseData = JSON.parse(ajax.responseText);
                            var pathRootTemp = responseData["base_url"]
                            var responseVideoArray = responseData["video"]
                            if (responseVideoArray === null || responseVideoArray.length <= 0) {
                                ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "adaptation_vimeo");
                                ADAPTATION_HOLDER.adapterWebsiteParseFail(location.href);
                                return false;
                            }
                            var rootTempArray = pathRootTemp.split("../")
                            var midlePathArray = [];
                            var midelIndex = 0;
                            for (let i = 0; i < rootTempArray.length; i++) {
                                if (rootTempArray[i] !== '') {
                                    midlePathArray[midelIndex] = rootTempArray[i]
                                    midelIndex++
                                }
                            }
                            var masterUrl = new URL(masterJsonStr)
                            var masterPath = masterUrl.pathname;
                            var masterHostName = masterUrl.hostname;
                            var preUrl = "https://" + masterHostName + "/"

                            var masterPathArray = masterPath.split('/')
                            preUrl += (masterPathArray[1] + '/' + masterPathArray[2] + '/')
                            for (let i = 0; i < midlePathArray.length; i++) {
                                preUrl += (midlePathArray[i])
                            }
                            var resultVideoArray = [];
                            for (let i = 0; i < responseVideoArray.length; i++) {
                                var videoItem = responseVideoArray[i];
                                var tempChildPath = videoItem['base_url']
                                var tempChildPathArray = tempChildPath.split('../')
                                var childMidelPathArray = [];
                                var childMidelPathIndex = 0;
                                for (let j = 0; j < tempChildPathArray.length; j++) {
                                    if (tempChildPathArray[j] !== '') {
                                        childMidelPathArray[childMidelPathIndex] = tempChildPathArray[j]
                                        childMidelPathIndex++
                                    }
                                }
                                var targetUrl = preUrl;
                                for (let j = 0; j < childMidelPathArray.length; j++) {
                                    targetUrl += (childMidelPathArray[j])
                                }
                                var mediaUrlList = []
                                var indexSegment = videoItem['index_segment']
                                if (indexSegment !== null && indexSegment !== undefined) {
                                    indexSegment = indexSegment.split('\\u0026')[0]
                                    indexSegment = indexSegment.split('&')[0]
                                    targetUrl += indexSegment
                                    mediaUrlList[0] = targetUrl
                                } else {
                                    var segmentsList = videoItem['segments']
                                    for (let k = 0; k < segmentsList.length; k++) {
                                        mediaUrlList[k] = targetUrl + segmentsList[k]
                                    }
                                }

                                resultVideoArray[i] = {
                                    'name': title + ".mp4",
                                    'duration': duration,
                                    'thumbnailUrl': thumb,
                                    'sourceUrl': location.href,
                                    'quality': videoItem['height'] + "P",
                                    'fromUrl': location.href,
                                    'headerReferer': getRefererContent(),
                                    'headerUserAgent': ua_global,
                                    'mediaUrlList': mediaUrlList
                                }
                            }
                            var responseAudioArray = responseData['audio']
                            var resultAudioArray = [];
                            var audioIndex = 0;
                            for (let i = 0; i < responseAudioArray.length; i++) {
                                var audioItem = responseAudioArray[i];
                                var tempChildPath = audioItem['base_url']
                                var tempChildPathArray = tempChildPath.split('../')
                                var childMidelPathArray = [];
                                var childMidelPathIndex = 0;
                                for (let j = 0; j < tempChildPathArray.length; j++) {
                                    if (tempChildPathArray[j] !== '') {
                                        childMidelPathArray[childMidelPathIndex] = tempChildPathArray[j]
                                        childMidelPathIndex++
                                    }
                                }
                                var targetUrl = preUrl;
                                for (let j = 0; j < childMidelPathArray.length; j++) {
                                    targetUrl += (childMidelPathArray[j])
                                }
                                var indexSegment = audioItem['index_segment']
                                if (indexSegment !== undefined) {
                                    indexSegment = indexSegment.split('\\u0026')[0]
                                    indexSegment = indexSegment.split('&')[0]
                                    targetUrl += indexSegment
                                    resultAudioArray[audioIndex] = {
                                        'name': title + ".mp3",
                                        'duration': duration,
                                        'thumbnailUrl': thumb,
                                        'sourceUrl': location.href,
                                        'fromUrl': location.href,
                                        'headerReferer': getRefererContent(),
                                        'headerUserAgent': ua_global,
                                        'mediaUrlList': [targetUrl]
                                    }
                                    audioIndex++
                                }
                            }
                            var resultData = {
                                'status': 'success',
                                'fromType': 'vimeo',
                                'parseType': 'mp4',
                                'dataList': resultVideoArray,
                                'audioList': resultAudioArray,
                                'fromUrl': location.href,
                                'dataSource': 'single===vimeo'
                            };
                            ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "adaptation_vimeo_dash");
                            ADAPTATION_HOLDER.receiveJsParseResult(JSON.stringify(resultData));

                        } else if (ajax.status >= 400 && ajax.status <= 599) {
                            ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "adaptation_vimeo");
                            ADAPTATION_HOLDER.adapterWebsiteParseFail(location.href);
                        }
                    }
                }
            } else if (ajaxStatus >= 400 && ajaxStatus <= 599) {
                ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "adaptation_vimeo");
                ADAPTATION_HOLDER.adapterWebsiteParseFail(location.href);
            }
        }
    }

    function parseVimeo(curHref) {
        if (location.href.search('chrome-error') !== -1) {
            return false
        }
        try {
            var websiteAjax = new XMLHttpRequest();
            websiteAjax.open('get', curHref);
            websiteAjax.send();
            websiteAjax.onreadystatechange = function () {
                var ajaxStatus = websiteAjax.status;
                if (websiteAjax.readyState === 4 && ajaxStatus === 200) {
                    var success = false
                    try {
                        success = dealVimeoWebsiteContent(websiteAjax.responseText)
                    } catch (error) {
                    }
                    if (success === false) {
                        ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "adaptation_vimeo");
                        ADAPTATION_HOLDER.adapterWebsiteParseFail(location.href);
                    }
                }
            }
        } catch (error) {
            return false;
        }
    }

    function parseXvideosporno() {
        try {
            var bodyDom = document.body;

            var videoDom = bodyDom.querySelector('div[class="videosection"]');
            if (videoDom === null) {
                return false;
            }
            reportAdapterRule("all_fit_reveal_pass7")
            var pathDom = videoDom.getElementsByTagName('source')[0];
            if (pathDom === null) {
                return false;
            }
            var mediaUrl = pathDom.src;
            if (mediaUrl === null || mediaUrl === '') {
                return false;
            }

            var videoContentDom = bodyDom.querySelector('div[id="video-content"]');

            var quality = videoContentDom.querySelector('meta[itemprop="height"]').content + 'p';
            var thumb = videoContentDom.querySelector('link[itemprop="thumbnailUrl"]').href;

            var headDom = document.head;
            var durationStr = headDom.querySelector('meta[property="video:duration"]').content;
            var duration = parseFloat(durationStr);
            var title = headDom.getElementsByTagName('title')[0].text;

            var href = getWebUrl();
            videoArray = [{
                'name': title + ".mp4",
                'duration': duration,
                'thumbnailUrl': thumb,
                'quality': quality,
                'sourceUrl': href,
                'fromUrl': href,
                'mediaUrlList': [mediaUrl]
            }];
            var resultData = {
                'status': 'success',
                'fromType': 'xvideosporno',
                'parseType': 'mp4',
                'dataList': videoArray,
                'fromUrl': href,
                'dataSource': 'adapter_unite===xvideosporno'
            }
            console.log("parseXvideosporno: " + href)
            ADAPTATION_HOLDER.setParseBtnType("adapter_unite", "nativeBtn", "XvideoPorn");
            notifyNativeDealData(JSON.stringify(resultData))
            reportAdapterRule("all_fit_reveal_success7")
            return true
        } catch (error) {
            return false;
        }

    }

    function parseCapcut() {
        var parseReg = RegExp(/(\/sharevideo\/|\/watch\/).+/);
        var searchStr = location.search;
        var pathnameStr = location.pathname;
        console.log("parseCapcut", "pathnameStr" + pathnameStr + " searchStr: " + searchStr + " pathnameStr.match(parseReg): " + (pathnameStr.match(parseReg)))
        if (pathnameStr.match(parseReg) === null) {
            console.log("parseCapcut return")
            ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "atlasv parseCapcut");
            return;
        }
        ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "atlasv parseCapcut");
        startDetect('capcut');
        ADAPTATION_HOLDER.loadLocalJsFile('capcut_parser.js');
    }

    function parseBilibili() {
        console.log("parseBilibili")
        var parseVideoReg = RegExp(/\/video\//)
        var parseBangumiReg = RegExp(/\/bangumi\//)
        var pathnameStr = location.pathname;
        if (pathnameStr.match(parseVideoReg) === null) {
            if (pathnameStr.match(parseBangumiReg) != null) {
                ADAPTATION_HOLDER.setParseBtnType("common", "nativeBtn", "atlasv parseBilibili");
            } else { // 不支持显示
                ADAPTATION_HOLDER.setParseBtnType("none", "websiteBtn", "atlasv parseBilibili");
            }
            return;
        }
        ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "atlasv parseBilibili");
        startDetect('bilibili');
        ADAPTATION_HOLDER.loadLocalJsFile('bilibili_parser.js');
    }

    function parseFaphouse() {
        ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "atlasv parseFaphouse");
        ADAPTATION_HOLDER.loadLocalJsFile('faphouse_parser.js');
    }

    function parseArabxn() {
        try {
            var body = document.body;
            var videoEle = body.querySelector('video[id = "my-video"]');
            if (videoEle === null) {
                return false;
            }
            reportAdapterRule("all_fit_reveal_pass6")
            var thumbnail = videoEle.poster;
            var videoEles = videoEle.children
            if (videoEles === null || videoEles.length === 0) {
                return false;
            }

            var head = document.head;
            var titleEle = head.querySelector('meta[name = "title"]');
            var title = titleEle.content;
            var href = getWebUrl();

            var videoArray = [];
            for (let i = 0; i < videoEles.length; i++) {
                var item = videoEles[i]
                var downloadUrl = item.src;
                var quality = item.title;
                videoArray[i] = {
                    'name': title + ".mp4",
                    'thumbnailUrl': thumbnail,
                    'sourceUrl': href,
                    'quality': quality,
                    'fromUrl': href,
                    'headerReferer': getRefererContent(),
                    'headerUserAgent': ua_global,
                    'mediaUrlList': [downloadUrl]
                }
            }
            var resultData = {
                'status': 'success',
                'fromType': 'arabxn',
                'parseType': 'mp4',
                'dataList': videoArray,
                'fromUrl': href,
                'dataSource': 'adapter_unite===arabxn'
            }
            console.log("arabxn: " + href)
            ADAPTATION_HOLDER.setParseBtnType("adapter_unite", "nativeBtn", "arabxn");
            notifyNativeDealData(JSON.stringify(resultData))
            reportAdapterRule("all_fit_reveal_success6")
            return true
        } catch (error) {
            return false;
        }
    }

    function parseSpankbang() {
        try {
            var divEle = document.getElementById('inner_content');
            if (divEle === null) {
                return false;
            }
            var regMediaLink = /(?<=var\sstream_data\s=\s).+(?=;)/;
            var content = divEle.textContent;
            var mediaMatch = content.match(regMediaLink);
            if (mediaMatch === null) {
                return false;
            }
            reportAdapterRule("all_fit_reveal_pass3");
            var mediaLinkData = mediaMatch[0];
            if (mediaLinkData === null) {
                return false;
            }
            var trueString = mediaLinkData.replace(/'/g, "\"")
            var mediaLinkDataO = JSON.parse(trueString)

            var thumbnail = mediaLinkDataO['thumbnail'];
            var duration = mediaLinkDataO['length'];

            var mediaDetailEle = divEle.querySelector('script[type = "application/ld+json"]')
            if (mediaDetailEle === null) {
                return false;
            }

            var mediaDetailData = JSON.parse(mediaDetailEle.textContent);
            var title = mediaDetailData['name'];
            var qualityArray = ['240p', '320p', '480p', '720p', '1080p', '4k'];
            var videoArray = [];
            var href = getWebUrl();
            var index = 0;
            for (let i = 0; i < qualityArray.length; i++) {
                var qItemArray = mediaLinkDataO[qualityArray[i]];
                if (qItemArray !== null && qItemArray.length !== 0) {
                    videoArray[index] = {
                        'name': title + ".mp4",
                        'duration': duration,
                        'thumbnailUrl': thumbnail,
                        'sourceUrl': href,
                        'quality': qualityArray[i],
                        'headerReferer': getRefererContent(),
                        'headerUserAgent': ua_global,
                        'fromUrl': href,
                        'mediaUrlList': [qItemArray[0]]
                    }
                    index++;
                }
            }

            if (videoArray.length === 0) {
                return false;
            }
            var resultData = {
                'status': 'success',
                'fromType': 'spankbang',
                'parseType': 'mp4',
                'dataList': videoArray,
                'fromUrl': href,
                'dataSource': 'adapter_unite===spankbang'
            }
            console.log("spankbang: " + href)
            ADAPTATION_HOLDER.setParseBtnType("adapter_unite", "nativeBtn", "bangspank_parser.js");
            notifyNativeDealData(JSON.stringify(resultData));
            reportAdapterRule("all_fit_reveal_success3");
            return true
        } catch (error) {
            return false;
        }
    }

    function parsePornfactory() {
        try {
            var head = document.head;
            var downloadUrlEle = head.querySelector('meta[property = "og:video:url"]')
            var downloadUrl = downloadUrlEle.content;
            if (downloadUrl === null || downloadUrl === '') {
                return false;
            }
            reportAdapterRule("all_fit_reveal_pass5")
            var videoType = head.querySelector('meta[property = "og:video:type"]')
            if (videoType === null || videoType.content.search("video/") === -1) {
                return false;
            }
            var titleEle = head.querySelector('title');
            var title = titleEle.textContent;

            var durationEle = head.querySelector('meta[property = "video:duration"]');
            var duration = durationEle.content;

            var thumbnailEle = head.querySelector('meta[property = "og:image"]');
            var thumbnail = thumbnailEle.content;

            var qualityEle = head.querySelector('meta[property = "video:duration"]');
            var quality = qualityEle.content + "p";
            var href = getWebUrl();
            videoArray = [{
                'name': title,
                'duration': parseFloat(duration),
                'thumbnailUrl': thumbnail,
                'sourceUrl': href,
                'fromUrl': href,
                'quality': quality,
                'mediaUrlList': [downloadUrl]
            }];
            var resultData = {
                'status': 'success',
                'fromType': 'pornfactory',
                'parseType': 'mp4',
                'dataList': videoArray,
                'fromUrl': href,
                'dataSource': 'adapter_unite===pornfactory'
            }
            console.log("pornfactory: " + href)
            ADAPTATION_HOLDER.setParseBtnType("adapter_unite", "nativeBtn", "all_fit_reveal_success5");
            notifyNativeDealData(JSON.stringify(resultData))
            reportAdapterRule("all_fit_reveal_success5")
            return true
        } catch (error) {
            return false;
        }
    }

    function parseFreeindianporn() {
        try {
            var header = document.head;

            var videoEle = header.querySelector('meta[property="og:video:url"]');
            var downloadUrl = videoEle.content;
            if (downloadUrl === null || downloadUrl === '') {
                return false;
            }
            reportAdapterRule("all_fit_reveal_pass4")
            var videoTypeEle = header.querySelector('meta[property="og:video:type"]');
            if (videoTypeEle === null || videoTypeEle.content.search("video/") === -1) {
                return false;
            }
            var title = header.querySelectorAll('title')[0].textContent;
            if (!isNullOrEmpty(title)) {
                title = title.replace(' ', '') + '.mp4'
            } 
            var imageEle = header.querySelector('meta[property="og:image"]')
            var thumbnail = '';
            if (imageEle !== null) {
                thumbnail = imageEle.content;
            }
            var duration = header.querySelector('meta[property = "video:duration"]').content;
            var quality = header.querySelector('meta[property = "og:video:height"]').content + 'p';
            var href = getWebUrl();
            videoArray = [{
                'name': title,
                'duration': parseFloat(duration),
                'thumbnailUrl': thumbnail,
                'sourceUrl': href,
                'quality': quality,
                'fromUrl': href,
                'mediaUrlList': [downloadUrl]
            }];
            var resultData = {
                'status': 'success',
                'fromType': 'freeindianporn',
                'parseType': 'mp4',
                'dataList': videoArray,
                'fromUrl': href,
                'dataSource': 'adapter_unite===freeindianporn'
            }
            console.log("freeindianporn: " + href)
            ADAPTATION_HOLDER.setParseBtnType("adapter_unite", "nativeBtn", "Freeindianporn");
            notifyNativeDealData(JSON.stringify(resultData))
            reportAdapterRule("all_fit_reveal_success4")
            return true
        } catch (error) {
            return false;
        }
    }

    function parseHlsXhaVideo(isAdapter) {
        try {
            var videoArray = []
            var dataContent = window.initials;
            var videoEntity = dataContent["videoEntity"];
            reportAdapterRule("all_fit_reveal_pass1");
            var titleStr = videoEntity["title"];
            titleStr = titleStr.replaceAll(' ', '');
            titleStr += '.mp4'
            var duration = videoEntity["duration"];
            var thumbLink = videoEntity["thumbBig"];

            var xplayerData = dataContent["xplayerSettings"];
            var sourcesData = xplayerData["sources"];
            var vda = sourcesData["standard"]["mp4"];
            var i;
            var index = 0;
            var href = getWebUrl();
            for (i = 0; i < vda.length; i++) {
                var item = vda[i]
                var qualityStr = item["quality"]
                if (qualityStr === 'auto') {
                    continue;
                }
                index++;
                var mediaUrl = item.url.toString();
                if (mediaUrl.search(',http') !== -1) {
                    mediaUrl = mediaUrl.split(',http')[0];
                }
                var resultItem = {
                    'name': titleStr,
                    'duration': duration,
                    'thumbnailUrl': thumbLink,
                    'sourceUrl': href,
                    'quality': qualityStr,
                    'fromUrl': href,
                    'headerReferer': getRefererContent(),
                    'headerUserAgent': ua_global,
                    'mediaUrlList': [mediaUrl]
                };
                videoArray[index - 1] = resultItem;
            }
            if (videoArray.length === 0) {
                return false;
            }
            var dataSource = "single===xhamster"
            if (isAdapter) {
                dataSource = "adapter_unite===xhamster"
            }
            var resultData = {
                'status': 'success',
                'fromType': 'xhamster',
                'parseType': 'mp4',
                'dataList': videoArray,
                'fromUrl': href,
                'dataSource': dataSource
            }
            console.log("parseXhamster: " + href)
            if (isAdapter) {
                ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "adapter_xham_parser");
            } else {
                ADAPTATION_HOLDER.setParseBtnType("adapter_unite", "nativeBtn", "adapter_unite_xham_parser");
            }
            notifyNativeDealData(JSON.stringify(resultData))
            reportAdapterRule("all_fit_reveal_success1");
            return true;
        } catch (error) {
            return false;
        }
    }

    function parseNormalXhaVideo(isAdapter) {
        try {
            var content = document.getElementsByTagName("noscript")[0].textContent
            var videoPathR = /src="(.*)"\sp/
            var videoMatch = content.match(videoPathR)
            var videoPath = videoMatch[1]
            videoPath = videoPath.replaceAll('&amp;', '&')
            if (videoPath.length > 0) {
                var headEle = document.head
                var title = document.title
                title = title.replaceAll(' ', '')
                title += '.mp4'
                var thumb = headEle.querySelector('meta[property="og:image"]').content
                var href = getWebUrl();
                var videoArray = [
                    {
                        'name': title,
                        'thumbnailUrl': thumb,
                        'sourceUrl': href,
                        'fromUrl': href,
                        'headerReferer': getRefererContent(),
                        'headerUserAgent': ua_global,
                        'mediaUrlList': [videoPath]
                    }
                ]
                var dataSource = "adapter_video===xhamster"
                var resultData = {
                    'status': 'success',
                    'fromType': 'xhamster',
                    'parseType': 'mp4',
                    'dataList': videoArray,
                    'fromUrl': href,
                    'dataSource': dataSource
                }
                ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", dataSource);
                notifyNativeDealData(JSON.stringify(resultData))
                reportAdapterRule("all_fit_reveal_success1");
                return true
            } else {
                return false
            }
        } catch (error) {
            return false
        }
    }

    function parseXhaImageData() {
        try {
            var bodyEle = document.body
            var pictureEle = bodyEle.querySelector('div[class="picture_view"]')
            if (pictureEle === null) {
                return false;
            }
            var picBlockEle = pictureEle.querySelector('div[class="pictures_block"]')
            if (picBlockEle === null) {
                return false;
            }
            var itemsEle = picBlockEle.getElementsByClassName('items')
            if (itemsEle.length <= 0) {
                return false
            }
            var childrenEle = itemsEle[0].children
            var title = document.head.title
            title = title.replaceAll(' ', '')
            //data-gallery_id
            var imgEle = childrenEle[0].querySelector('img[class="thumb"]')
            var galleryId = imgEle.getAttribute('data-gallery_id')
            var host = location.host
            var page = 1
            var requestPath = 'https://' + host + '/ajax.php?act=slide&galleryId=' + galleryId + '&page='
            var ajax = new XMLHttpRequest();
            var path = requestPath + page
            ajax.open('get', path);
            ajax.setRequestHeader('referer', document.referrer)
            ajax.send();
            ajax.onreadystatechange = function () {
                var ajaxStatus = ajax.status;
                if (ajax.readyState === 4 && ajaxStatus === 200) {
                    var imagesData = JSON.parse(ajax.responseText);
                    var dataList = imagesData['list']
                    var imageArray = []
                    var index = 0
                    for (let i = 0; i < dataList.length; i++) {
                        imageArray[index] = {
                            'name': title + '_' + index + '.jpg',
                            'thumbnailUrl': dataList[i]['small'],
                            'sourceUrl': document.href,
                            'fromUrl': document.href,
                            'headerReferer': getRefererContent(),
                            'headerUserAgent': ua_global,
                            'mediaUrlList': [dataList[i]['big']]
                        }
                        index++
                    }
                    var dataSource = "adapter_img===xhamster"
                    var resultData = {
                        'status': 'success',
                        'fromType': 'xhamster',
                        'parseType': 'img',
                        'dataList': imageArray,
                        'fromUrl': document.href,
                        'dataSource': dataSource
                    }
                    ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", dataSource);
                    notifyNativeDealData(JSON.stringify(resultData))
                    reportAdapterRule("all_fit_reveal_success1");
                }
            }
            //https://zh.xhamster.com/ajax.php?act=slide&galleryId=15996875&page=2
            // for (let i = 0; i < childrenEle.length; i++) {
            //     var imgEle = childrenEle[i].querySelector('img[class="thumb"]')
            //     if (imgEle === null) {
            //         continue;
            //     }
            //     var imgPath = imgEle.src
            // imageArray[index] = {
            //     'name': title + '_' + index + '.jpg',
            //     'thumbnailUrl': imgPath,
            //     'sourceUrl': document.href,
            //     'fromUrl': document.href,
            //     'headerReferer': getRefererContent(),
            //     'headerUserAgent': ua_global,
            //     'mediaUrlList': [imgPath]
            // }
            // index++
            // }
            // if (imageArray.length <= 0) {
            //     return false
            // }
            // var dataSource = "adapter_img===xhamster"
            // var resultData = {
            //     'status': 'success',
            //     'fromType': 'xhamster',
            //     'parseType': 'img',
            //     'dataList': imageArray,
            //     'fromUrl': document.href,
            //     'dataSource': dataSource
            // }
            // ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", dataSource);
            // notifyNativeDealData(JSON.stringify(resultData))
            // reportAdapterRule("all_fit_reveal_success1");
            return true
        } catch (error) {
            return false
        }
    }

    /**
     * 解析 xhamster 
     */
    function parseXhamster(isAdapter) {
        console.log("parseXhamster: start ==> ")
        var foundHlsVideo = parseHlsXhaVideo(isAdapter)
        if (!foundHlsVideo) {
            foundHlsVideo = parseNormalXhaVideo(isAdapter)
        }
        if (!foundHlsVideo) {
            foundHlsVideo = parseXhaImageData()
        }
        return foundHlsVideo
    }

    /**
     * 解析 Pornhub
     */
    function parsePornhub() {
        ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "atlasv parsePornhub");
        startDetect('pornhub');
        ADAPTATION_HOLDER.loadLocalJsFile('phub_parser.js');
    }

    function getXnxxVideoItem(title, duration, thumb, sourchUrl, fromUrl, quality, mediaArray) {
        return {
            'name': title,
            'duration': duration,
            'thumbnailUrl': thumb,
            'headerReferer': getRefererContent(),
            'headerUserAgent': ua_global,
            'sourceUrl': sourchUrl,
            'fromUrl': fromUrl,
            'quality': quality,
            'mediaUrlList': mediaArray
        }
    }

    function parseXnxx(isAdapter) {
        try {
            var href = getWebUrl();

            var titleDocContent = document.querySelector('meta[property="og:title"]').content
            var title = titleDocContent.replace(' ', '') + ".mp4"
            var duration = document.querySelector('meta[property="og:duration"]').content

            var contentStr = document.body.innerHTML;
            var player = window.html5player
            var hlsLink = ''
            var videoArray = [];
            var isHlsLink = false
            if (player !== null) {
                hlsLink = player.url_hls

                var lowUrl = player.url_low
                var highUrl = player.url_high

                var thumb = player.url_thumb169
                if (isNullOrEmpty(thumb)) {
                    thumb = player.url_thumb
                }
                var alternativeList = [];
                if (!isNullOrEmpty(hlsLink)) {
                    videoArray[0] = getXnxxVideoItem(title, duration, thumb, href, href, null, [hlsLink]);
                    isHlsLink = true
                    var videoIndex = 0;
                    if (!isNullOrEmpty(lowUrl)) {
                        alternativeList[videoIndex] = getXnxxVideoItem(title, duration, thumb, href, href, '360P', [lowUrl]);
                        videoIndex++
                    }
                    if (!isNullOrEmpty(highUrl)) {
                        alternativeList[videoIndex] = getXnxxVideoItem(title, duration, thumb, href, href, '720P', [highUrl])
                    }
                } else {
                    var videoIndex = 0;
                    if (!isNullOrEmpty(lowUrl)) {
                        videoArray[videoIndex] = getXnxxVideoItem(title, duration, thumb, href, href, '360P', [lowUrl]);
                        videoIndex++
                    }
                    if (!isNullOrEmpty(highUrl)) {
                        videoArray[videoIndex] = getXnxxVideoItem(title, duration, thumb, href, href, '720P', [highUrl]);
                    }
                }
            }
            if (videoArray.length !== 0) {
                var parseType = ''
                if (isHlsLink) {
                    parseType = 'hls'
                } else {
                    parseType = 'mp4'
                }

                var resultData = {
                    'status': 'success',
                    'fromType': 'xnxx',
                    'mediaUrlIsHls': isHlsLink,
                    'parseType': parseType,
                    'userAgent': ua_global,
                    'dataList': videoArray,
                    'alternativeList': alternativeList,
                    'fromUrl': href,
                    'dataSource': 'single===xnxx/xvideos'
                }

                if (isAdapter) {
                    ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "xnxx-or-xvideos");
                } else {
                    ADAPTATION_HOLDER.setParseBtnType("adapter_unite", "nativeBtn", "xnxx-or-xvideos");
                }
                notifyNativeDealData(JSON.stringify(resultData));
                reportAdapterRule("all_fit_reveal_success2");
                return true
            }
            var hlsPattern = /setVideoHLS*\(\s*([\"'])(?<value>(?:(?!\1).)+)\1/;
            var hlsMatch = contentStr.match(hlsPattern);
            var dataSource = "single===xnxx/xvideos"
            if (isAdapter) {
                dataSource = "adapter_unite===xnxx/xvideos"
            }
            if (hlsMatch == null) {
                var titleDocContent = document.querySelector('meta[property="og:title"]').content
                var title = titleDocContent.replace(' ', '') + ".mp4"
                var duration = document.querySelector('meta[property="og:duration"]').content

                var lowUrl = player.url_low
                var highUrl = player.url_high
                var thumbMatch = ''
                var videoArray = [];
                var alternativeList = [];
                if (isNullOrEmpty(lowUrl) && isNullOrEmpty(highUrl)) {
                    var scriptDoc = document.querySelector('script[type="application/ld+json"]')
                    if (scriptDoc === null) {
                        return false;
                    }
                    var scriptContent = scriptDoc.textContent
                    if (isNullOrEmpty(scriptContent)) {
                        return false
                    }
                    var xnxxData = JSON.parse(scriptContent)
                    var videoUrl = xnxxData['contentUrl']
                    if (isNullOrEmpty(videoUrl)) {
                        reportXnxxParseFail2GoogleForm("1027", document.readyState)
                        return false
                    }
                    thumbMatch = xnxxData['thumbnailUrl'].toString();
                    videoArray[0] = getXnxxVideoItem(title, duration, thumbMatch, href, href, '360P', [videoUrl]);
                } else {
                    thumbMatch = player.url_thumb

                    var index = 0;
                    if (!isNullOrEmpty(lowUrl)) {
                        videoArray[index] = getXnxxVideoItem(title, duration, thumbMatch, href, href, '360P', [lowUrl]);
                        index++
                    }
                    if (!isNullOrEmpty(highUrl)) {
                        videoArray[index] = getXnxxVideoItem(title, duration, thumbMatch, href, href, '720P', [highUrl]);
                    }
                }
                if (videoArray.length == 0) {
                    reportXnxxParseFail2GoogleForm("1072", document.readyState)
                    return false
                }
                var resultData = {
                    'status': 'success',
                    'fromType': 'xnxx',
                    'mediaUrlIsHls': true,
                    'parseType': 'mp4',
                    'userAgent': ua_global,
                    'dataList': videoArray,
                    'fromUrl': href,
                    'dataSource': dataSource
                }
                if (isAdapter) {
                    ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "xnxx-or-xvideos");
                } else {
                    ADAPTATION_HOLDER.setParseBtnType("adapter_unite", "nativeBtn", "xnxx-or-xvideos");
                }
                notifyNativeDealData(JSON.stringify(resultData));
                reportAdapterRule("all_fit_reveal_success2");
                return true
            }
            reportAdapterRule("all_fit_reveal_pass2");
            var titlePattern = /setVideoTitle*\(\s*([\"'])(?<value>(?:(?!\1).)+)\1/;
            var thumbPattern = /setThumbUrl169*\(\s*([\"'])(?<value>(?:(?!\1).)+)\1/;
            var hlsLink = hlsMatch[2];
            hlsLink = hlsLink.replace('hls_low', 'hls');
            if (hlsLink === '') {
                reportXnxxParseFail2GoogleForm("814", document.readyState)
                return false;
            }

            var lowUrl = '';
            var highUrl = '';
            try {
                // 通过正则的方式 尝试获取 low and high url
                var lowUrlPattern = /setVideoUrlLow*\(\s*([\"'])(?<value>(?:(?!\1).)+)\1/;
                var highUrlPattern = /setVideoUrlHigh*\(\s*([\"'])(?<value>(?:(?!\1).)+)\1/;
                lowUrl = contentStr.match(lowUrlPattern)[2];
                highUrl = contentStr.match(highUrlPattern)[2];
            } catch (error) {

            }

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
            videoArray[0] = getXnxxVideoItem(videoTitleMatch + ".mp4", duration, thumbMatch, href, href, null, [hlsLink]);
            var alternativeList = [];
            var tempIndex = 0;
            if (!isNullOrEmpty(lowUrl)) {
                alternativeList[tempIndex] = getXnxxVideoItem(videoTitleMatch + ".mp4", duration, thumbMatch, href, href, '360P', [lowUrl]);
                tempIndex++;
            }
            if (!isNullOrEmpty(highUrl)) {
                alternativeList[tempIndex] = getXnxxVideoItem(videoTitleMatch + ".mp4", duration, thumbMatch, href, href, '720P', [highUrl]);
            }
            var resultData = {
                'status': 'success',
                'fromType': 'xnxx',
                'mediaUrlIsHls': true,
                'parseType': 'hls',
                'userAgent': ua_global,
                'dataList': videoArray,
                'alternativeList': alternativeList,
                'fromUrl': href,
                'dataSource': dataSource
            }
            if (isAdapter) {
                ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "xnxx-or-xvideos");
            } else {
                ADAPTATION_HOLDER.setParseBtnType("adapter_unite", "nativeBtn", "xnxx-or-xvideos");
            }
            notifyNativeDealData(JSON.stringify(resultData));
            reportAdapterRule("all_fit_reveal_success2");
            console.log("parseXnxx: end ==> ")
            return true
        } catch (error) {
            reportXnxxParseFail2GoogleForm("catch", document.readyState)
            return false;
        }
    }

    function parse9gag() {
        ADAPTATION_HOLDER.setParseBtnType("adapter", "websiteBtn", "atlasv parse9gag");
        ADAPTATION_HOLDER.loadLocalJsFile('detect_9gag.js');
    }

    function parseFacebook() {
        ADAPTATION_HOLDER.setParseBtnType("adapter", "websiteBtn", "atlasv parseFacebook");
        ADAPTATION_HOLDER.loadLocalJsFile('detect_fb.js');
    }

    function parseIns() {
        ADAPTATION_HOLDER.setParseBtnType("adapter", "websiteBtn", "atlasv parseIns");
        ADAPTATION_HOLDER.loadLocalJsFile('detect_ins.js');
    }

    function parse500px() {
        ADAPTATION_HOLDER.setParseBtnType("adapter", "websiteBtn", "atlasv parse500px");
        ADAPTATION_HOLDER.loadLocalJsFile('detect_500px.js');
    }

    function parseTwitter() {
        ADAPTATION_HOLDER.setParseBtnType("adapter", "websiteBtn", "atlasv parseTwitter");
        ADAPTATION_HOLDER.loadLocalJsFile('detect_tw.js');
    }

    function parseTubidy() {
        ADAPTATION_HOLDER.setParseBtnType("adapter", "nativeBtn", "atlasv parseTubidy");
        startDetect('parseTubidy')
        var titleStr = ""
        var titleE = document.getElementsByClassName("video-title-selected")[0]
        if (titleE != null) {
            titleStr = titleE.textContent;
            console.log("titleStr: " + titleStr)
        }
        // 找到下载地址
        var downloadUrl = ""
        var downloadLis = document.getElementsByClassName("list-group-item")
        var length = downloadLis.length
        var qualityStr = ""
        for (var index = 0; index < length; index++) {
            var downloadLi = downloadLis[index]
            var isDownloadE = downloadLi.getElementsByClassName("fa-download")[0] != null
            if (isDownloadE) {
                var aE = downloadLi.getElementsByTagName("a")[0]
                if (aE != null) {
                    downloadUrl = aE.getAttribute("href")
                    var innerText = downloadLi.innerText.toLowerCase()
                    if (innerText.indexOf("audio") != -1) {
                        titleStr = titleStr + ".mp3"
                        if (innerText.indexOf("mp3") != -1) {
                            qualityStr = "Mp3 Audio"
                        } else {
                            qualityStr = "Mp4 Audio"
                        }
                    } else {
                        titleStr = titleStr + ".mp4"
                        qualityStr = "Mp4 Video"
                    }
                }
            }
        }
        if (!isEmpty(downloadUrl)) {
            var videoArray = [];
            var href = getWebUrl();
            videoArray[0] = {
                'name': titleStr,
                'sourceUrl': href,
                'fromUrl': href,
                'quality': qualityStr,
                'mediaUrlList': [downloadUrl]
            };
            var resultData = {
                'status': 'success',
                'fromType': 'tubidy',
                'parseType': 'mp4',
                'dataList': videoArray,
                'fromUrl': href,
                'dataSource': "single===tubidy"
            }
            notifyNativeDealData(JSON.stringify(resultData));
        }
    }

    function isEmpty(obj) {
        if (typeof obj == "undefined" || obj == null || obj == "") {
            return true;
        } else {
            return false;
        }
    }

    //=============================适配过的网站写这里-end=======================================
    console.log("exit adaptation_atlasv.js")
}());