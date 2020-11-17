package com.yzycoc.cocutil.util;

/**
 * @program: cscocutil
 * @description: 公共代码
 * @author: yzy
 * @create: 2020-08-22 12:32
 * @Version 1.0
 **/
public class YqHtmlCustom {


    public static StringBuffer css  = new StringBuffer();


    static {
        css.append("<style type=\"text/css\">\r\n" +
                "body {\r\n" +
                "	font-family: Arial;\r\n" +
                "	font-size:12px;\r\n" +
                "	background-color: #CCCCCC;\r\n" +
                "	margin: 5px;\r\n" +
                "}\r\n" +
                ".bold {\r\n" +
                "	font-weight:bold;\r\n" +
                "}\r\n" +
                ".lootAvailableDiv {\r\n" +
                "	-moz-border-radius: 6px;\r\n" +
                "	border-radius: 6px;\r\n" +
                "	-webkit-border-radius: 6px;\r\n" +
                "	border:1px solid #333333;\r\n" +
                "	overflow-x:scroll;\r\n" +
                "	overflow-y:hidden;\r\n" +
                "	margin-top:10px;\r\n" +
                "}\r\n" +
                ".graphs th {\r\n" +
                "	vertical-align: middle;\r\n" +
                "	padding:10px;\r\n" +
                "	font-variant: small-caps;\r\n" +
                "	font-size:18px;\r\n" +
                "	font-weight:bold;\r\n" +
                "	max-width:55px;\r\n" +
                "}\r\n" +
                ".graphs th.timeScaleHeader {\r\n" +
                "	font-size:12px;\r\n" +
                "	padding:0px;\r\n" +
                "}\r\n" +
                ".graphs td {\r\n" +
                "	width:100%;\r\n" +
                "	text-align: center;\r\n" +
                "}\r\n" +
                ".graphs .graphsLoading {\r\n" +
                "	font-variant: small-caps;\r\n" +
                "	font-weight: bold;\r\n" +
                "	font-size:16px;\r\n" +
                "}\r\n" +
                ".graphs td.lootIndexGraphScale {\r\n" +
                "	vertical-align: bottom;\r\n" +
                "	padding-bottom:50px;\r\n" +
                "}\r\n" +
                ".graphs td.lootIndexGraphScale div {\r\n" +
                "	margin-left:-10px;\r\n" +
                "	font-size:10px;\r\n" +
                "	max-width:0px;\r\n" +
                "	white-space: nowrap;\r\n" +
                "	text-align: left;\r\n" +
                "}\r\n" +
                ".graphs td.lootIndexGraphScale.right div {\r\n" +
                "	margin-left:22px !important;\r\n" +
                "}\r\n" +
                "\r\n" +
                ".graphData td {\r\n" +
                "	min-width:1px;\r\n" +
                "	max-width:1px;\r\n" +
                "	vertical-align:bottom;\r\n" +
                "}\r\n" +
                ".graphData td div {\r\n" +
                "	width:1px;\r\n" +
                "}\r\n" +
                "\r\n" +
                ".graphData div.markerIndex {\r\n" +
                "	height:1px;\r\n" +
                "	width:5px;\r\n" +
                "	float:right;\r\n" +
                "	margin-top:1px;\r\n" +
                "}\r\n" +
                ".graphData div.markerIndexLabel {\r\n" +
                "	font-size:30px;\r\n" +
                "	font-variant: small-caps;\r\n" +
                "	float:left;\r\n" +
                "	margin-top:-15px;\r\n" +
                "	vertical-align:top;\r\n" +
                "	margin-left:-20px;\r\n" +
                "	color:#253355;\r\n" +
                "	font-weight:bold;\r\n" +
                "FONT-FAMILY: '隶书';\r\n" +
                "}\r\n" +
                ".graphData div.markerIndexRight {\r\n" +
                "	height:1px;\r\n" +
                "	width:5px;\r\n" +
                "	float:left;\r\n" +
                "	margin-top:1px;\r\n" +
                "}\r\n" +
                ".graphData div.markerIndexLabelRight {\r\n" +
                "	font-size:12px;\r\n" +
                "	font-weight:bold;\r\n" +
                "	font-variant: small-caps;\r\n" +
                "	float:right;\r\n" +
                "	vertical-align:top;\r\n" +
                "	margin-top:-5px;\r\n" +
                "	margin-right:-8px;\r\n" +
                "	color:#444444;\r\n" +
                "	font-style: italic;\r\n" +
                "}\r\n" +
                "\r\n" +
                ".timeGraph {\r\n" +
                "	background-color:#EEEEEE;\r\n" +
                "	height:20px;\r\n" +
                "}\r\n" +
                ".timeGraph td div {\r\n" +
                "	height:20px;\r\n" +
                "	font-size:12px;\r\n" +
                "	font-weight:bold;\r\n" +
                "	font-variant: small-caps;\r\n" +
                "	line-height:20px;\r\n" +
                "	overflow:none;\r\n" +
                "}\r\n" +
                ".lootAvailableNow {\r\n" +
                "	border:1px solid #000000;\r\n" +
                "}\r\n" +
                "#lootIndexStringContainer {\r\n" +
                "	vertical-align: middle;\r\n" +
                "}\r\n" +
                "#lootIndexString {\r\n" +
                "	white-space: nowrap;\r\n" +
                "	text-align: center;\r\n" +
                "	font-size:84px;\r\n" +
                "	line-height: 84px;\r\n" +
                "	font-weight:bold;\r\n" +
                "	letter-spacing:2px;\r\n" +
                "}\r\n" +
                ".lootIndexArrow {\r\n" +
                "	padding: 6px 0px;\r\n" +
                "	visibility: hidden;\r\n" +
                "}\r\n" +
                ".lootIndexTable {\r\n" +
                "	padding:10px;\r\n" +
                "	-moz-border-radius: 6px;\r\n" +
                "	border-radius: 6px;\r\n" +
                "	-webkit-border-radius: 6px;\r\n" +
                "	height:100%;\r\n" +
                "}\r\n" +
                ".lootIndexTable td {\r\n" +
                "	line-height: 10px;\r\n" +
                "}\r\n" +
                ".lootIndexTable td.currentLootAvailable {\r\n" +
                "	font-variant: small-caps;\r\n" +
                "	font-weight: bold;\r\n" +
                "	white-space: nowrap;\r\n" +
                "	text-align: center;\r\n" +
                "	letter-spacing: 1px;\r\n" +
                "	font-size:22px;\r\n" +
                "	padding-top:5px;\r\n" +
                "}\r\n" +
                ".lootIndexTable td.onScale {\r\n" +
                "	font-size:10px;\r\n" +
                "	white-space: nowrap;\r\n" +
                "	text-align: center;\r\n" +
                "}\r\n" +
                "\r\n" +
                ".worldwideStatsTable {\r\n" +
                "	padding:10px;\r\n" +
                "	-moz-border-radius: 6px;\r\n" +
                "	border-radius: 6px;\r\n" +
                "	-webkit-border-radius: 6px;\r\n" +
                "	height:100%;\r\n" +
                "	width:355;\r\n" +
                "}\r\n" +
                ".worldwideStatsTable th {\r\n" +
                "	white-space: nowrap;\r\n" +
                "	font-size:14px;\r\n" +
                "	text-align: left;\r\n" +
                "	font-variant: small-caps;\r\n" +
                "}\r\n" +
                ".worldwideStatsTable .worldwideStatsHeading {\r\n" +
                "	font-size:22px;\r\n" +
                "	letter-spacing: 1px;\r\n" +
                "	font-variant: small-caps;\r\n" +
                "	text-align: center;\r\n" +
                "	padding-bottom:6px;\r\n" +
                "}\r\n" +
                ".worldwideStatsTable td {\r\n" +
                "	white-space: nowrap;\r\n" +
                "	font-size:14px;\r\n" +
                "	font-weight:bold;\r\n" +
                "	letter-spacing:2px;\r\n" +
                "	text-align: right;\r\n" +
                "	padding-left:20px;\r\n" +
                "}\r\n" +
                ".worldwideStatsTable td.worldwideStatDetails {\r\n" +
                "	line-height:7px;\r\n" +
                "}\r\n" +
                ".forecastTable {\r\n" +
                "	width:340px;\r\n" +
                "	padding:7px;\r\n" +
                "	-moz-border-radius: 6px;\r\n" +
                "	border-radius: 6px;\r\n" +
                "	-webkit-border-radius: 6px;\r\n" +
                "	height:100%;\r\n" +
                "}\r\n" +
                ".forecastTable th {\r\n" +
                "	font-size:22px;\r\n" +
                "	font-weight:bold;\r\n" +
                "	padding-bottom: 10px;\r\n" +
                "	padding-top:1px;\r\n" +
                "	letter-spacing: 1px;\r\n" +
                "	font-variant: small-caps;\r\n" +
                "	text-align: center;\r\n" +
                "}\r\n" +
                ".forecastTable td {\r\n" +
                "	padding-bottom: 10px;\r\n" +
                "	font-size:15px;\r\n" +
                "	font-weight:bold;\r\n" +
                "	font-variant: small-caps;\r\n" +
                "	text-align: center;\r\n" +
                "}\r\n" +
                ".forecastTable td div.mainHeading {\r\n" +
                "	text-align: center;\r\n" +
                "	font-weight: bold;\r\n" +
                "	font-variant: small-caps;\r\n" +
                "	font-size:24px;\r\n" +
                "	letter-spacing: 1px;\r\n" +
                "}\r\n" +
                ".forecastTable td div.mainHeading2 {\r\n" +
                "	text-align: center;\r\n" +
                "	font-weight: bold;\r\n" +
                "	font-size:30px;\r\n" +
                "	letter-spacing: 2px;\r\n" +
                "}\r\n" +
                ".forecastTable td div.peopleBeenHere {\r\n" +
                "	font-variant: small-caps;\r\n" +
                "	background-color:#0072BC;\r\n" +
                "	color:#63CCF6;\r\n" +
                "	font-weight:bold;\r\n" +
                "	font-style: italic;\r\n" +
                "	padding:2px 10px;\r\n" +
                "	font-size:12px;\r\n" +
                "	border:1px solid #63CCF6;\r\n" +
                "	-moz-border-radius: 6px;\r\n" +
                "	border-radius: 6px;\r\n" +
                "	-webkit-border-radius: 6px;\r\n" +
                "	margin-right:5px;\r\n" +
                "	text-align: center;\r\n" +
                "	cursor:default;\r\n" +
                "	margin:10px 5px 2px 5px;\r\n" +
                "}\r\n" +
                ".forecastTable td div.peopleHereNow {\r\n" +
                "	font-variant: small-caps;\r\n" +
                "	background-color:#F3702F;\r\n" +
                "	color:#FCD2BD;\r\n" +
                "	font-weight:bold;\r\n" +
                "	font-style: italic;\r\n" +
                "	padding:2px 10px;\r\n" +
                "	font-size:12px;\r\n" +
                "	border:1px solid #FAC2A7;\r\n" +
                "	-moz-border-radius: 6px;\r\n" +
                "	border-radius: 6px;\r\n" +
                "	-webkit-border-radius: 6px;\r\n" +
                "	margin-right:5px;\r\n" +
                "	text-align: center;\r\n" +
                "	cursor:default;\r\n" +
                "	margin:0px 5px;\r\n" +
                "}\r\n" +
                "\r\n" +
                "\r\n" +
                ".mostActiveRegionsTable {\r\n" +
                "	padding:10px;\r\n" +
                "	-moz-border-radius: 6px;\r\n" +
                "	border-radius: 6px;\r\n" +
                "	-webkit-border-radius: 6px;\r\n" +
                "	height:100%;\r\n" +
                "}\r\n" +
                ".mostActiveRegionsTable .mostActiveRegionsHeading {\r\n" +
                "	font-size:22px;\r\n" +
                "	letter-spacing: 1px;\r\n" +
                "	font-variant: small-caps;\r\n" +
                "	text-align: center;\r\n" +
                "	padding-bottom:6px;\r\n" +
                "	font-weight:bold;\r\n" +
                "	white-space: nowrap;\r\n" +
                "}\r\n" +
                ".mostActiveRegionsTable td {\r\n" +
                "	font-variant: small-caps;\r\n" +
                "	font-size:14px;\r\n" +
                "	white-space: nowrap;\r\n" +
                "	font-weight: bold;\r\n" +
                "	text-align: left;\r\n" +
                "}\r\n" +
                ".mostActiveRegionsTable span.regionTimezone {\r\n" +
                "	font-size:12px;\r\n" +
                "}\r\n" +
                "\r\n" +
                "\r\n" +
                "\r\n" +
                ".lootDetailsTable {\r\n" +
                "	border:1px solid #333333;\r\n" +
                "	-moz-border-radius: 6px;\r\n" +
                "	border-radius: 6px;\r\n" +
                "	-webkit-border-radius: 6px;\r\n" +
                "}\r\n" +
                ".lootDetailsTable th {\r\n" +
                "	border-right:1px solid #333333;\r\n" +
                "	background-color: #A187BE;\r\n" +
                "	padding:3px 3px;\r\n" +
                "	font-size:16px;\r\n" +
                "	font-variant: small-caps;\r\n" +
                "	white-space: nowrap;\r\n" +
                "}\r\n" +
                ".lootDetailsTable th.online {\r\n" +
                "	background-color: #F7977A;\r\n" +
                "}\r\n" +
                ".lootDetailsTable th.offline {\r\n" +
                "	background-color: #F5EE94;\r\n" +
                "}\r\n" +
                ".lootDetailsTable th.shielded {\r\n" +
                "	background-color: #75CEDF;\r\n" +
                "}\r\n" +
                ".lootDetailsTable th.attackable {\r\n" +
                "	background-color: #82CA9D;\r\n" +
                "}\r\n" +
                ".lootDetailsTable th.lootMinutes {\r\n" +
                "	background-color: #7EA7D8;\r\n" +
                "}\r\n" +
                ".lootDetailsTable th div.percentOfRegion {\r\n" +
                "	font-variant: normal;\r\n" +
                "	font-weight: normal;\r\n" +
                "	font-size:10px;\r\n" +
                "	font-style: italic;\r\n" +
                "	padding-top:3px;\r\n" +
                "	color:#444444;\r\n" +
                "}\r\n" +
                ".lootDetailsTable td {\r\n" +
                "	border-top:1px solid #333333;\r\n" +
                "	border-right:1px solid #333333;\r\n" +
                "	padding:5px 3px;\r\n" +
                "	text-align: center;\r\n" +
                "	background-color: #D0C3DF;\r\n" +
                "	white-space: nowrap;\r\n" +
                "	font-size:14px;\r\n" +
                "}\r\n" +
                ".lootDetailsTable td.region {\r\n" +
                "	white-space: normal;\r\n" +
                "}\r\n" +
                ".lootDetailsTable td.online {\r\n" +
                "	background-color: #FBCBBD;\r\n" +
                "}\r\n" +
                ".lootDetailsTable td.offline {\r\n" +
                "	background-color: #F5F3D1;\r\n" +
                "}\r\n" +
                ".lootDetailsTable td.shielded {\r\n" +
                "	background-color: #D6F1F6;\r\n" +
                "}\r\n" +
                ".lootDetailsTable td.attackable {\r\n" +
                "	background-color: #C1E5CE;\r\n" +
                "}\r\n" +
                ".lootDetailsTable td.lootMinutes {\r\n" +
                "	background-color: #BFD3EC;\r\n" +
                "	font-weight: bold;\r\n" +
                "}\r\n" +
                "\r\n" +
                ".lootDetailsTable .percent {\r\n" +
                "	font-size:10px;\r\n" +
                "	font-style: italic;\r\n" +
                "}\r\n" +
                ".lootDetailsTable .lightRightBorder {\r\n" +
                "	border-right:1px dotted #999999;\r\n" +
                "}\r\n" +
                ".lootDetailsTable .darkerRightBorder {\r\n" +
                "	border-right:1px dotted #000000;\r\n" +
                "}\r\n" +
                ".lootDetailsTable .produced {\r\n" +
                "	font-size:11px !important;\r\n" +
                "	color: #157E47;\r\n" +
                "	font-weight: normal !important;\r\n" +
                "}\r\n" +
                ".lootDetailsTable .consumed {\r\n" +
                "	font-size:11px !important;\r\n" +
                "	color: #AA292C;\r\n" +
                "	font-weight: normal !important;\r\n" +
                "}\r\n" +
                ".lootDetailsTable span.regionTimezone {\r\n" +
                "	font-size:12px;\r\n" +
                "}\r\n" +
                ".infoTable {\r\n" +
                "	font-style:italic;\r\n" +
                "	border:1px solid #333333;\r\n" +
                "	-moz-border-radius: 6px;\r\n" +
                "	border-radius: 6px;\r\n" +
                "	-webkit-border-radius: 6px;\r\n" +
                "	font-size:14px;\r\n" +
                "	padding:0px 12px;\r\n" +
                "	background-color:#FFFBCD;\r\n" +
                "	width:360px;\r\n" +
                "}\r\n" +
                ".adHolder {\r\n" +
                "	/*\r\n" +
                "	width:120px;\r\n" +
                "	height:600px;\r\n" +
                "	border:1px dotted #777777;\r\n" +
                "	*/\r\n" +
                "}\r\n" +
                "\r\n" +
                "\r\n" +
                "div.appStoreContainer {\r\n" +
                "/*	background-color: #333333;*/\r\n" +
                "/*	background-color: #f8f6f3;*/\r\n" +
                "	background-color: #FAE6C4;\r\n" +
                "	padding:10px 4px;\r\n" +
                "	-moz-border-radius: 6px;\r\n" +
                "	border-radius: 6px;\r\n" +
                "	-webkit-border-radius: 6px;\r\n" +
                "/*	border: 3px solid #EEEEEE;*/\r\n" +
                "	border: 3px solid #eeb655;\r\n" +
                "}\r\n" +
                "\r\n" +
                "div.appStoreContainer div {\r\n" +
                "	text-align: center;\r\n" +
                "	color:#000000;\r\n" +
                "}\r\n" +
                "\r\n" +
                "div.appStoreContainer .appImage {\r\n" +
                "	padding:5px;\r\n" +
                "}\r\n" +
                "div.appStoreContainer .nowAvailable {\r\n" +
                "	font-weight: bold;\r\n" +
                "	font-size:16px;\r\n" +
                "}\r\n" +
                "\r\n" +
                "div.appStoreContainer .free {\r\n" +
                "	 text-decoration: underline;\r\n" +
                "	 font-size:26px;\r\n" +
                "	 font-weight: bold;\r\n" +
                "}\r\n" +
                "\r\n" +
                "div.appStoreContainer .inThe {\r\n" +
                "	font-weight: bold;\r\n" +
                "	font-size:16px;\r\n" +
                "}\r\n" +
                "\r\n" +
                "div.appStoreContainer .appStore {\r\n" +
                "	font-weight: bold;\r\n" +
                "	font-size:20px;\r\n" +
                "}\r\n" +
                "\r\n" +
                "div.appStoreContainer .separator {\r\n" +
                "	border-top:1px solid #eeb655;\r\n" +
                "}\r\n" +
                "\r\n" +
                "div.appStoreContainer .pushAlert {\r\n" +
                "	font-weight: bold;\r\n" +
                "	font-variant: small-caps;\r\n" +
                "	font-size:14px;\r\n" +
                "	white-space: nowrap;\r\n" +
                "}\r\n" +
                "\r\n" +
                "div.appStoreContainer .comingSoon {\r\n" +
                "	font-weight: bold;\r\n" +
                "	font-size:14px;\r\n" +
                "	padding-bottom:4px;\r\n" +
                "}\r\n" +
                "\r\n" +
                "div.appStoreContainer div.comingSoonDevice {\r\n" +
                "	font-weight: bold;\r\n" +
                "	font-variant: small-caps;\r\n" +
                "	font-size:16px;\r\n" +
                "	line-height: 14px;\r\n" +
                "}\r\n" +
                "\r\n" +
                "div.appStoreContainer div.pushMessageExample {\r\n" +
                "	margin-top:2px;\r\n" +
                "}\r\n" +
                "\r\n" +
                "div.appStoreContainer div.pushMessageExample img {\r\n" +
                "	-moz-border-radius: 4px;\r\n" +
                "	border-radius: 4px;\r\n" +
                "	-webkit-border-radius: 4px;\r\n" +
                "\r\n" +
                "}\r\n" +
                "\r\n" +
                ".rotate {\r\n" +
                "  -webkit-transform: rotate(-90deg);\r\n" +
                "  -moz-transform: rotate(-90deg);\r\n" +
                "  -ms-transform: rotate(-90deg);\r\n" +
                "  -o-transform: rotate(-90deg);\r\n" +
                "  transform: rotate(-90deg);\r\n" +
                "\r\n" +
                "  /* also accepts left, right, top, bottom coordinates; not required, but a good idea for styling */\r\n" +
                "  -webkit-transform-origin: 50% 50%;\r\n" +
                "  -moz-transform-origin: 50% 50%;\r\n" +
                "  -ms-transform-origin: 50% 50%;\r\n" +
                "  -o-transform-origin: 50% 50%;\r\n" +
                "  transform-origin: 50% 50%;\r\n" +
                "\r\n" +
                "  /* Should be unset in IE9+ I think. */\r\n" +
                "  filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=3);\r\n" +
                "FONT-FAMILY: 隶书;FONT-SIZE: 20px;}\r\n" +
                "\r\n" +
                ".languageSelect {\r\n" +
                "	margin-top:3px;\r\n" +
                "	letter-spacing: 2px;\r\n" +
                "	padding:0px 5px;\r\n" +
                "	font-size:12px;\r\n" +
                "	font-weight: bold;\r\n" +
                "	-moz-border-radius: 3px;\r\n" +
                "	border-radius: 3px;\r\n" +
                "	-webkit-border-radius: 3px;\r\n" +
                "	border:1px solid #000000;\r\n" +
                "	background-color: #D9E5F4;\r\n" +
                "}\r\n" +
                "</style>\r\n" +
                "");
    }

}

