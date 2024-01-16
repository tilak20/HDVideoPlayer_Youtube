javascript: (function () {
        //add timeline website tanpan@20220328
        var urlHost = location.host;
        var urlHref = location.href;

        var reg9gag = RegExp(/9gag/i);
        var regIns = RegExp(/instagram/i);
        var regFaceBook = RegExp(/facebook/i);
        var regTikTok = RegExp(/tiktok==========/i);
        var regTwitter = RegExp(/twitter/i);
        var regYoutube = RegExp(/youtube/i);
        var regTubidy = RegExp(/tubidy/i);
        var regBilibili =  RegExp(/bilibili/i);
        var regBilibiliVideo =  RegExp(/bilibili\.com\/video/i);
        var regBilibiliBangumi =  RegExp(/bilibili\.com\/bangumi/i);

        console.log("regBilibili: " + (urlHost.match(regBilibili) != null));
        console.log("regBilibiliVideo: " + (urlHref.match(regBilibiliVideo) != null));
        console.log("regBilibiliBangumi: " + (urlHref.match(regBilibiliBangumi) != null));
        console.log("result: " + (urlHost.match(regBilibili) != null && urlHref.match(regBilibiliVideo) == null && urlHref.match(regBilibiliBangumi) == null));

        if (urlHost.match(reg9gag) || urlHost.match(regFaceBook) || urlHost.match(regIns) || urlHost.match(regTikTok)
            || urlHost.match(regTwitter)|| urlHost.match(regYoutube)|| urlHost.match(regTubidy)
            || (urlHost.match(regBilibili) != null && urlHref.match(regBilibiliVideo) == null && urlHref.match(regBilibiliBangumi) == null)  // 哔哩哔哩首页不支持展示（内部页切换到首页，JS脚本还会执行，导致首页也显示Video，这里进行屏蔽）
            ) {
                return false
        } else {
                return true
        }

}());