javascript:(function () {
    var bodyWidth = document.body.offsetHeight;
    for (var c = 0, d = Date.now(), g = location.href, f = function (p, w) {
        var div = document.createElement("DIV");
        if ("fb_stories" == w.playerOrigin) {
            var r = p.childNodes;
            for (var y = 0, B = r.length; y < B; y++) {
                var z = r[y], A = z.getAttribute("data-sigil");
                if (A && /m-video-play-button/i.test(A)) {
                    z.style.marginLeft = "30px";
                    break
                }
            }
            r = "bottom:50%;top:50%;left:50%;margin:auto 0px auto -60px;"
        } else r = "top:0px;left:0px;margin-left:-2px;margin-top:-2px;";
        var sw = 100;
        if(isNaN(w.width)){
            if(isNaN(bodyWidth)){
               bodyWidth = document.body.offsetHeight;
            }
            sw  = bodyWidth * 0.07;
        }else{
            sw = Math.round(w.width * 0.14);
        }
        div.setAttribute("style", "position:absolute;" + r + "height:"+sw+"px;width:"+sw+"px;background-color:rgba(255,255,255,0.9);z-index:9999;border-bottom-right-radius:10px;border:2px solid #EEE;");
        div.innerHTML = '<img style="position:absolute;top:0px;bottom:0px;left:0px;right:0px;margin:auto;height:64%;" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADwAAABOCAYAAAB8FnW4AAAFGUlEQVR4Xu1bTUxUVxT+zn04/ISCpDaG1G50YWKMaaWJyE9hoLWpLSsDae0GASeWhMYaFyakndCG1IWxpCbUjIBuahuIK1qS2sJA+RGTYhtjTFzopjbE1Aaxhp+Bd0/zaMcAgXnz7tzRMb1vRfLO/c75vvPOu9+7GQhJuArr+1kH7HhnJenAWY6hHdABN4QV22Q6rCiceaQ1CLcCwsywoqJmhhWFMzOsQTgzwzpENDOsQUVjLTWIaLy0qohmhlWVW7bOzLAGEc0Mq4poZlhVOTPDGpRbDmG+hxUFNTOsKJz5HtYgnPke1iGimWENKhovrUFE46VVRTQzrKqc8dIalDNeWoOIZoY1iGj2YQ0imn1YVUQzw6rKmX1Yg3JmH9YgoplhDSL+//bhvQ3hz5nlUQAZGgRMZYg5ItFGbzWN50zNzpwEy3qAfKlcsXptHAGJzrzMrBNLj3R5ILxpXsrzzHhHHTR1VxLhu3QhDg2G/Pcfz3B5443s+ci9PslcTCCRuuXHXxmDpSAaTfdt3j/YvvORs3LFS6vk0I9bF4XoAFAG4FknLQEMpUnZMHL+jTtRmVa9pZlK6sK7bMGXmLEtfi1TL5IIty1JB0a6/NcBevwL/TW3pZKGgQIb6GXm/NSj4l4REU1aQNVIR8XE6uh19+GiQP8+KdGOZ63ThNtCoHEsVHl5LWnWJVxd3W3dzX2+Box2Bja66/r0Iwh4AELjlum/unt6amxPhKPBexvCR5ntVoCynj6lWBXwDJHVfKXD3xYryt1aBoOi8G7ZcbBsBlFOSpJmfggSreNbhk6hpcV5O697uRMGsPejsUz8PfsJM44BSDU3FiHCaTyX+emVL4pm3RoSF+ElN9YYzp6PcAjMNQxYbsBP4j4BNoi6030UGGz3LxkLtytuwg5QyQfDeXJh4YxkfhdPn7QtiL4VGzY0jXxVOuVGdB3j4b6sNPBzvm0vnpVAFcCeBHNHjzeCWAC9lpV2ZDj02mS8q5w4pYKL6wa22wIXwbzbSzJtsUTXLImDo10Vt7xiKhF2khQdDr/MUnYx8IrXpInEE/ArCVE3ds7/mwqOMmEnWWmgf/eijYsMbFdJ7nUNAbfSLBwcDlVe87pWeYZXJaLCwwNVJOVZBiXVdxN4koU4Mn6uoheA8r/rJtRhh3wwyOKH3wffg+AzYM5TVT7mOqIpSGp686Xyb1paKKaxcMufMGEnwTLfHWIg2y2pl/sEPAIhEMsfe8TzEr5+7I7qbl9u7gvHGPJjMPT4bsIMQXw2Pf3n6Zs9NREdlWrpcLSQPe/35VhZGc2S+Tg4wRMTgnM8c8qemWu9+vX+hzrIKu/DsZIXBH7J8snpVmY4R7/KFxHaIiK3eSL06owyyBoLtXY4il9eG944nybbmVGjYEFtInSnL4rGwQv+BzrJJqXD0QKLAv3b2F46PNjnpWgCLpO1dGJx28u6eGOT0uFocsd3L8jFXjAXxFUQ0cQGkVbl1R/Hhf1fUFIJOzmcTksblwDsiuHdHSNxXVg4kKzORkVJOmEEg2LPpL9M2HYHA1vX6gYBd6RlNVzNDw+5nVh46eY6uRKFcF/vuLGf/hgqlrD7mFcaEyI8ErD2v/5i2WiiLsq9EsXPw3iA14opqh94WxIugHnT0n2i+4JRO9ZZ8b0qptd1yX+kl1W0o/qGLyf3Xj0YJ/8ljBMPpzd33uzZqcVFxUP+iRJ2CiqvDWfM+fhD5++MCH05eME/F0+humL+AXuzJtz3jQhMAAAAAElFTkSuQmCC" />';
        (function (C, D) {
            C.onclick = function () {
                event.stopPropagation();
                var E = JSON.stringify({js_cb_tag: "dl_click", data: [D]});
                FBDJS.onDataReceived(E)
            }
        })(div, w);
        p.insertBefore(div, p.firstChild);
        r = p.getBoundingClientRect();
        r = Math.round(r.width);
        150 > r && (70 > r ? (div.style.height = "100%", div.style.width = "100%", div.style.marginTop = "0px", div.style.marginLeft = "0px") : (div.style.height =
            "46px", div.style.width = "46px"))
    }, m = [], n = document.querySelectorAll("div[data-type='video']"), h = 0, a = n.length; h < a; h++) {
        var e = n[h];
        if (!e.getAttribute("leo_attr")) {
            var b = null;
            try {
                  b = {
                    videoID:e.getAttribute("data-video-id"),
                    src:e.getAttribute("data-video-url"),
                    playbackIsLiveStreaming:e.getAttribute("data-is-live-streaming"),
                    width:e.offsetWidth
                  }
            } catch (p) {
                console.log(p)
            }
            m.push({elm: e, obj: b})
        }
    }
    for(n = document.querySelectorAll("div[data-sigil='inlineVideo']"), h = 0, a = n.length; h < a; h++) {
            var e = n[h];
            if (!e.getAttribute("leo_attr")) {
                var b = null;
                try {
                    b = JSON.parse(e.getAttribute("data-store"))
                } catch (p) {
                    console.log(p)
                }
                if (b && b.videoID) {
                    if (!b.src) {
                        var k = e.href;
                        k && (k = /video_redirect.+?src=(.+)/i.exec(k)) && (b.src = decodeURIComponent(k[1]))
                    }
                    b.width = isNaN(e.offsetWidth)?432:e.offsetWidth;
                    b.src && m.push({elm: e, obj: b})
                }
            }
    }

    n = m.length;
    for (h = 0; h < n; h++) {
        b = m[h];
        e = b.elm;
        b = b.obj;
        k = a = null;
        e.setAttribute("leo_attr", !0);
        var l =
            e.getElementsByTagName("i")[0];
        if (l && (l = l.getAttribute("style")) && ((l = /url.+?['"](.+?)['"]/i.exec(l)) && (a = l[1]), a)) {
            a = a.replace(/\\/g, "%").replace(/\s/g, "");
            try {
                a = decodeURIComponent(a)
            } catch (p) {
                console.log(p)
            }
        }
        !a && (l = e.getElementsByTagName("img")[0]) && (a = l.src);
        l = e.childNodes;
        for (var t = 0, x = l.length; t < x; t++) {
            var u = l[t], v = u.nodeName;
            if (v && "SPAN" == v.toUpperCase()) {
                k = u.textContent;
                break
            }
        }
        b = {
            name: "FBD_V_" + d + ++c + ".mp4",
            from: g,
            videoID: b.videoID,
            videoURL: b.src,
            playerOrigin: b.playerOrigin,
            isLive :b.playbackIsLiveStreaming,
            width:b.width,
            dashManifest:b.dashManifest
        };
        a && (b.thumb =
            a);
        k && (b.duration = k);
        f(e, b)
    }
    if(n<=0){
        FBDJS.startDetectVideo(document.location.href);
        m = document.getElementsByTagName("VIDEO");
        e = 0;
        for (h = m.length; e < h; e++)
            if (a = m[e], !a.getAttribute("leo_attr"))
                if (a.parentNode.getAttribute("leo_attr"))
                    a.setAttribute("leo_attr", !0);
                else if (b = a.src) {
                    n += 1;
            a.setAttribute("leo_attr", !0);
            b = {name: "FBD_V_" + d + ++c + ".mp4", from: g, videoURL: b};
            b.width = isNaN(e.offsetWidth)?432:e.offsetWidth;
            if (k = a.poster)
                b.thumb = k;
            a = a.parentNode;
            k = /story_fbid=(\d+)/i.exec(a.innerHTML);
            null != k && (b.videoID = k[1]);
            f(a, b)
        }
        FBDJS.finishDetectVideo(document.location.href,n);
        0 == n ? FBDJS.warningNoVideoDetect() :
            (80 < n , c = JSON.stringify({js_cb_tag: "new_video_detect"}), FBDJS.onDataReceived(c))
    }

}());