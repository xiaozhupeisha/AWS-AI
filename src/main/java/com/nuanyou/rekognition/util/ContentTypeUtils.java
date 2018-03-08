package com.nuanyou.rekognition.util;

import java.util.HashMap;
import java.util.Map;

public class ContentTypeUtils {

    private static final Map<String, String> contentTypeMap = new HashMap();

    /**
     * 根据文件后缀名获得Content-Type
     *
     * @param fileName
     * @return
     */
    public static String getContentType(String fileName) {
        if (fileName == null)
            return null;
        int index = fileName.lastIndexOf(".");
        if (index > 0)
            fileName = fileName.substring(index + 1, fileName.length());
        return contentTypeMap.get(fileName.toLowerCase());
    }

    static {
        //image type
        contentTypeMap.put("fax", "image/fax");
        contentTypeMap.put("gif", "image/gif");
        contentTypeMap.put("ico", "image/x-icon");
        contentTypeMap.put("jfif", "image/jpeg");
        contentTypeMap.put("jpe", "image/jpeg");
        contentTypeMap.put("jpeg", "image/jpeg");
        contentTypeMap.put("jpg", "image/jpeg");
        contentTypeMap.put("net", "image/pnetvue");
        contentTypeMap.put("png", "image/png");
        contentTypeMap.put("rp", "image/vnd.rn-realpix");
        contentTypeMap.put("tif", "image/tiff");
        contentTypeMap.put("tiff", "image/tiff");
        contentTypeMap.put("wbmp", "image/vnd.wap.wbmp");

        //video type
        contentTypeMap.put("asf", "video/x-ms-asf");
        contentTypeMap.put("asx", "video/x-ms-asf");
        contentTypeMap.put("avi", "video/avi");
        contentTypeMap.put("ivf", "video/x-ivf");
        contentTypeMap.put("m1v", "video/x-mpeg");
        contentTypeMap.put("m2v", "video/x-mpeg");
        contentTypeMap.put("m4e", "video/mpeg4");
        contentTypeMap.put("movie", "video/x-sgi-movie");
        contentTypeMap.put("mp2v", "video/mpeg");
        contentTypeMap.put("mp4", "video/mpeg4");
        contentTypeMap.put("mpa", "video/x-mpg");
        contentTypeMap.put("mpe", "video/x-mpeg");
        contentTypeMap.put("mpeg", "video/mpg");
        contentTypeMap.put("mpg", "video/mpg");
        contentTypeMap.put("mps", "video/x-mpeg");
        contentTypeMap.put("mpv", "video/mpg");
        contentTypeMap.put("mpv2", "video/mpeg");
        contentTypeMap.put("wm", "video/x-ms-wm");
        contentTypeMap.put("wmv", "video/x-ms-wmv");
        contentTypeMap.put("wmx", "video/x-ms-wmx");

        //audio type
        contentTypeMap.put("acp", "audio/x-mei-aac");
        contentTypeMap.put("aif", "audio/aiff");
        contentTypeMap.put("aifc", "audio/aiff");
        contentTypeMap.put("aiff", "audio/aiff");
        contentTypeMap.put("au", "audio/basic");
        contentTypeMap.put("la1", "audio/x-liquid-file");
        contentTypeMap.put("lavs", "audio/x-liquid-secure");
        contentTypeMap.put("lmsff", "audio/x-la-lms");
        contentTypeMap.put("m3u", "audio/mpegurl");
        contentTypeMap.put("mid", "audio/mid");
        contentTypeMap.put("midi", "audio/mid");
        contentTypeMap.put("mnd", "audio/x-musicnet-download");
        contentTypeMap.put("mns", "audio/x-musicnet-stream");
        contentTypeMap.put("mp1", "audio/mp1");
        contentTypeMap.put("mp2", "audio/mp2");
        contentTypeMap.put("mp3", "audio/mp3");
        contentTypeMap.put("mpga", "audio/rn-mpeg");
        contentTypeMap.put("pls", "audio/scpls");
        contentTypeMap.put("ram", "audio/x-pn-realaudio");
        contentTypeMap.put("rmi", "audio/mid");
        contentTypeMap.put("rmm", "audio/x-pn-realaudio");
        contentTypeMap.put("snd", "audio/basic");
        contentTypeMap.put("wav", "audio/wav");
        contentTypeMap.put("wax", "audio/x-ms-wax");
        contentTypeMap.put("wma", "audio/x-ms-wma");

        //text type
        contentTypeMap.put("asa", "text/asa");
        contentTypeMap.put("asp", "text/asp");
        contentTypeMap.put("biz", "text/xml");
        contentTypeMap.put("cml", "text/xml");
        contentTypeMap.put("css", "text/css");
        contentTypeMap.put("csv", "text/csv");
        contentTypeMap.put("dcd", "text/xml");
        contentTypeMap.put("dtd", "text/xml");
        contentTypeMap.put("ent", "text/xml");
        contentTypeMap.put("fo", "text/xml");
        contentTypeMap.put("htc", "text/x-component");
        contentTypeMap.put("htm", "text/html");
        contentTypeMap.put("html", "text/html");
        contentTypeMap.put("htt", "text/webviewhtml");
        contentTypeMap.put("htx", "text/html");
        contentTypeMap.put("jsp", "text/html");
        contentTypeMap.put("math", "text/xml");
        contentTypeMap.put("mml", "text/xml");
        contentTypeMap.put("mtx", "text/xml");
        contentTypeMap.put("odc", "text/x-ms-odc");
        contentTypeMap.put("plg", "text/html");
        contentTypeMap.put("r3t", "text/vnd.rn-realtext3d");
        contentTypeMap.put("rdf", "text/xml");
        contentTypeMap.put("rt", "text/vnd.rn-realtext");
        contentTypeMap.put("sol", "text/plain");
        contentTypeMap.put("spp", "text/xml");
        contentTypeMap.put("stm", "text/html");
        contentTypeMap.put("svg", "text/xml");
        contentTypeMap.put("tld", "text/xml");
        contentTypeMap.put("tsd", "text/xml");
        contentTypeMap.put("txt", "text/plain");
        contentTypeMap.put("uls", "text/iuls");
        contentTypeMap.put("vcf", "text/x-vcard");
        contentTypeMap.put("vml", "text/xml");
        contentTypeMap.put("vxml", "text/xml");
        contentTypeMap.put("wml", "text/vnd.wap.wml");
        contentTypeMap.put("wsc", "text/scriptlet");
        contentTypeMap.put("wsdl", "text/xml");
        contentTypeMap.put("xdr", "text/xml");
        contentTypeMap.put("xhtml", "text/html");
        contentTypeMap.put("xml", "text/xml");
        contentTypeMap.put("xq", "text/xml");
        contentTypeMap.put("xql", "text/xml");
        contentTypeMap.put("xquery", "text/xml");
        contentTypeMap.put("xsd", "text/xml");
        contentTypeMap.put("xsl", "text/xml");
        contentTypeMap.put("xslt", "text/xml");

        //application type
        contentTypeMap.put("apk", "application/vnd.android.package-archive");
        contentTypeMap.put("awf", "application/vnd.adobe.workflow");
        contentTypeMap.put("bmp", "application/x-bmp");
        contentTypeMap.put("cat", "application/vnd.ms-pki.seccat");
        contentTypeMap.put("dll", "application/x-msdownload");
        contentTypeMap.put("doc", "application/msword");
        contentTypeMap.put("dot", "application/msword");
        contentTypeMap.put("eps", "application/postscript");
        contentTypeMap.put("exe", "application/x-msdownload");
        contentTypeMap.put("gzip", "application/gzip");
        contentTypeMap.put("iii", "application/x-iphone");
        contentTypeMap.put("img", "application/x-img");
        contentTypeMap.put("ipa", "application/vnd.iphone");
        contentTypeMap.put("js", "application/javascript");
        contentTypeMap.put("json", "application/json");
        contentTypeMap.put("lar", "application/x-laplayer-reg");
        contentTypeMap.put("mdb", "application/msaccess");
        contentTypeMap.put("mfp", "application/x-shockwave-flash");
        contentTypeMap.put("mi", "application/x-mi");
        contentTypeMap.put("mil", "application/x-mil");
        contentTypeMap.put("mocha", "application/x-javascript");
        contentTypeMap.put("mpd", "application/vnd.ms-project");
        contentTypeMap.put("mpp", "application/vnd.ms-project");
        contentTypeMap.put("mpt", "application/vnd.ms-project");
        contentTypeMap.put("mpw", "application/vnd.ms-project");
        contentTypeMap.put("mpx", "application/vnd.ms-project");
        contentTypeMap.put("ogg", "application/ogg");
        contentTypeMap.put("pdf", "application/pdf");
        contentTypeMap.put("pot", "application/vnd.ms-powerpoint");
        contentTypeMap.put("ppa", "application/vnd.ms-powerpoint");
        contentTypeMap.put("ppm", "application/x-ppm");
        contentTypeMap.put("pps", "application/vnd.ms-powerpoint");
        contentTypeMap.put("ppt", "application/vnd.ms-powerpoint");
        contentTypeMap.put("prf", "application/pics-rules");
        contentTypeMap.put("pwz", "application/vnd.ms-powerpoint");
        contentTypeMap.put("ra", "audio/vnd.rn-realaudio");
        contentTypeMap.put("rat", "application/rat-file");
        contentTypeMap.put("rec", "application/vnd.rn-recording");
        contentTypeMap.put("rjs", "application/vnd.rn-realsystem-rjs");
        contentTypeMap.put("rjt", "application/vnd.rn-realsystem-rjt");
        contentTypeMap.put("rm", "application/vnd.rn-realmedia");
        contentTypeMap.put("rmf", "application/vnd.adobe.rmf");
        contentTypeMap.put("rmj", "application/vnd.rn-realsystem-rmj");
        contentTypeMap.put("rmp", "application/vnd.rn-rn_music_package");
        contentTypeMap.put("rms", "application/vnd.rn-realmedia-secure");
        contentTypeMap.put("rmvb", "application/vnd.rn-realmedia-vbr");
        contentTypeMap.put("rmx", "application/vnd.rn-realsystem-rmx");
        contentTypeMap.put("rnx", "application/vnd.rn-realplayer");
        contentTypeMap.put("rpm", "audio/x-pn-realaudio-plugin");
        contentTypeMap.put("rsml", "application/vnd.rn-rsml");
        contentTypeMap.put("rtf", "application/msword");
        contentTypeMap.put("rv", "video/vnd.rn-realvideo");
        contentTypeMap.put("sdp", "application/sdp");
        contentTypeMap.put("sis", "application/vnd.symbian.install");
        contentTypeMap.put("sisx", "application/vnd.symbian.install");
        contentTypeMap.put("smi", "application/smil");
        contentTypeMap.put("smil", "application/smil");
        contentTypeMap.put("spc", "application/x-pkcs7-certificates");
        contentTypeMap.put("spl", "application/futuresplash");
        contentTypeMap.put("ssm", "application/streamingmedia");
        contentTypeMap.put("sst", "application/vnd.ms-pki.certstore");
        contentTypeMap.put("stl", "application/vnd.ms-pki.stl");
        contentTypeMap.put("swf", "application/x-shockwave-flash");
        contentTypeMap.put("torrent", "application/x-bittorrent");
        contentTypeMap.put("uin", "application/x-icq");
        contentTypeMap.put("vda", "application/x-vda");
        contentTypeMap.put("vdx", "application/vnd.visio");
        contentTypeMap.put("vsd", "application/vnd.visio");
        contentTypeMap.put("vss", "application/vnd.visio");
        contentTypeMap.put("vst", "application/vnd.visio");
        contentTypeMap.put("vsw", "application/vnd.visio");
        contentTypeMap.put("vsx", "application/vnd.visio");
        contentTypeMap.put("vtx", "application/vnd.visio");
        contentTypeMap.put("wiz", "application/msword");
        contentTypeMap.put("wmd", "application/x-ms-wmd");
        contentTypeMap.put("wpl", "application/vnd.ms-wpl");
        contentTypeMap.put("xls", "application/vnd.ms-excel");
        contentTypeMap.put("zip", "application/zip");
    }
}