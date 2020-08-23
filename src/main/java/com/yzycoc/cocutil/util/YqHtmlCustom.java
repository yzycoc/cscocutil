package com.yzycoc.cocutil.util;

/**
 * @program: cscocutil
 * @description: 公共代码
 * @author: yzy
 * @create: 2020-08-22 12:32
 * @Version 1.0
 **/
public class YqHtmlCustom {


    public static String css  ="";

    public static String htmlAll="";

    static {
        css = "<style type=\"text/css\">\r\n" +
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
                "";
    }
    static {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>\r\n" +
                "	<head>\r\n" +
                "		<title>Clash of Clans Forecaster</title>\r\n" +
                "		<meta charset=\"UTF-8\">\r\n" +
                "		<meta http-equiv=\"Content-type\" content=\"text/html; charset=UTF-8\" />\r\n" +
                "		<meta http-equiv=\"refresh\" content=\"1800\">\r\n" +
                "		<meta name=\"description\" content=\"Farm like a champion in Clash of Clans using this forecasting tool to help predict when the best times for raiding will be.\">\r\n" +
                "		<meta name=\"robots\" content=\"index, follow\">\r\n" +
                "	</head>\r\n" +
                "	<body style=\"overflow-x:hidden;\">\r\n" +
                "		<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"table-layout:auto;\">\r\n" +
                "			<tr>\r\n" +
                "				\r\n" +
                "				<td>\r\n" +
                "					<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"height: 152px;padding-top:8px;\">\r\n" +
                "						<tr>\r\n" +
                "							<td width=\"1%\" style=\"vertical-align: top;padding-right:10px;\">\r\n" +
                "								<table width=\"1%\" cellpadding=\"0\" cellspacing=\"0\" class=\"lootIndexTable\" style=\"background-color:#CCCCCC;border:3px solid #333333;color:#333333;\"\r\n" +
                "								 id=\"tableA\">\r\n" +
                "									<tr>\r\n" +
                "										<td class=\"currentLootAvailable\" style=\"height: 1%;\">Loot Index</td>\r\n" +
                "									</tr>\r\n" +
                "									<tr>\r\n" +
                "										<th id=\"lootIndexStringContainer\"></th>\r\n" +
                "									</tr>\r\n" +
                "									<tr>\r\n" +
                "										<td class=\"onScale\">ON A SCALE FROM 1 TO 10</td>\r\n" +
                "									</tr>\r\n" +
                "								</table>\r\n" +
                "							</td>\r\n" +
                "							<td width=\"1%\" style=\"vertical-align: top;padding-right:10px;\">\r\n" +
                "								<table width=\"1%\" cellpadding=\"0\" cellspacing=\"0\" class=\"forecastTable\" style=\"background-color:#CCCCCC;border:3px solid #333333;color:#333333;\"\r\n" +
                "								 id=\"tableB\">\r\n" +
                "									<tr>\r\n" +
                "										<th style=\"height: 1%;\">Farmer's Forecast</th>\r\n" +
                "									</tr>\r\n" +
                "									<tr>\r\n" +
                "										<td id=\"forecastMessage\"></td>\r\n" +
                "									</tr>\r\n" +
                "								</table>\r\n" +
                "							</td>\r\n" +
                "							<td width=\"1%\" style=\"vertical-align: top;padding-right:10px;\">\r\n" +
                "								<table width=\"1%\" cellpadding=\"0\" cellspacing=\"0\" class=\"worldwideStatsTable\" style=\"background-color:#CCCCCC;border:3px solid #333333;color:#333333;\"\r\n" +
                "								 id=\"tableC\">\r\n" +
                "									<tr>\r\n" +
                "										<td class=\"worldwideStatsHeading\" colspan=\"2\" style=\"height: 1%;\">Worldwide Stats</td>\r\n" +
                "									</tr>\r\n" +
                "									<tr>\r\n" +
                "										<th style=\"font-size:16px;\">Loot Minutes Available:</th>\r\n" +
                "										<td style=\"font-size:17px;\" id=\"currentLootLootMinutes\"></td>\r\n" +
                "									</tr>\r\n" +
                "									<tr>\r\n" +
                "										<th>Net Change This Minute:</th>\r\n" +
                "										<td style=\"font-size:15px;\" id=\"currentLootLootMinuteChange\"></td>\r\n" +
                "									</tr>\r\n" +
                "									<tr>\r\n" +
                "										<th style=\"padding-top: 6px;\" class=\"worldwideStatDetails\">Players Online:</th>\r\n" +
                "										<td style=\"padding-top: 6px;\" class=\"worldwideStatDetails\" id=\"currentLootPlayersOnline\"></td>\r\n" +
                "									</tr>\r\n" +
                "									<tr>\r\n" +
                "										<th class=\"worldwideStatDetails\">Players Offline:</th>\r\n" +
                "										<td class=\"worldwideStatDetails\" id=\"currentLootPlayersOffline\"></td>\r\n" +
                "									</tr>\r\n" +
                "									<tr>\r\n" +
                "										<th class=\"worldwideStatDetails\">Shielded Players:</th>\r\n" +
                "										<td class=\"worldwideStatDetails\" id=\"currentLootShieldedPlayers\"></td>\r\n" +
                "									</tr>\r\n" +
                "									<tr>\r\n" +
                "										<th class=\"worldwideStatDetails\">Attackable Players:</th>\r\n" +
                "										<td class=\"worldwideStatDetails\" id=\"currentLootAttackablePlayers\"></td>\r\n" +
                "									</tr>\r\n" +
                "								</table>\r\n" +
                "							</td>\r\n" +
                "							<td width=\"1%\" style=\"vertical-align: top;padding-right:10px;\">\r\n" +
                "								<table width=\"1%\" cellpadding=\"0\" cellspacing=\"0\" class=\"mostActiveRegionsTable\" style=\"background-color:#CCCCCC;border:3px solid #333333;color:#333333;\"\r\n" +
                "								 id=\"tableD\">\r\n" +
                "									<tr>\r\n" +
                "										<td class=\"mostActiveRegionsHeading\" colspan=\"2\" style=\"height: 1%;\">Most Active Regions</td>\r\n" +
                "									</tr>\r\n" +
                "									<tr>\r\n" +
                "										<td id=\"mostActiveRegionsA\" width=\"50%\"></td>\r\n" +
                "										<td id=\"mostActiveRegionsB\" width=\"50%\" style=\"padding-left:15px;\"></td>\r\n" +
                "									</tr>\r\n" +
                "								</table>\r\n" +
                "							</td>\r\n" +
                "							<td width=\"97%\" style=\"vertical-align: top;\">\r\n" +
                "								<table align=\"right\" width=\"1\" cellpadding=\"0\" cellspacing=\"0\" class=\"forecastTable\" style=\"width:1px;background-color:#333333;border:3px solid #CCCCCC;color:#CCCCCC;\"\r\n" +
                "								 id=\"mainTitleBox\">\r\n" +
                "									<tr>\r\n" +
                "										<td style=\"padding-bottom: 0px;white-space: nowrap;\">\r\n" +
                "											<div class=\"mainHeading\">Clash of Clans</div>\r\n" +
                "											<div class=\"mainHeading2\">Forecaster</div>\r\n" +
                "											<div class=\"peopleBeenHere\"><span id=\"totalHits\"></span> people have been here</div>\r\n" +
                "											<div class=\"peopleHereNow\"><span id=\"activeSessions\"></span> people are here right now</div>\r\n" +
                "\r\n" +
                "										</td>\r\n" +
                "									</tr>\r\n" +
                "								</table>\r\n" +
                "							</td>\r\n" +
                "						</tr>\r\n" +
                "					</table>\r\n" +
                "					<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\r\n" +
                "						<tr>\r\n" +
                "							<td style=\"width: 100%;\">\r\n" +
                "								<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"table-layout: fixed;\">\r\n" +
                "									<tr>\r\n" +
                "										<td>\r\n" +
                "											<div class=\"lootAvailableDiv\">\r\n" +
                "												<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" class=\"graphs\">\r\n" +
                "													<tr style=\"background-color: #EEEEEE;\">\r\n" +
                "														<th class=\"timeScaleHeader\" colspan=\"2\">Time</th>\r\n" +
                "														<td style=\"height: 20px;\">\r\n" +
                "															<div id=\"timeScaleGraphA\"></div>\r\n" +
                "														</td>\r\n" +
                "														<th class=\"timeScaleHeader\" colspan=\"2\">Time</th>\r\n" +
                "													</tr>\r\n" +
                "													<tr style=\"background-color: #DFE9F6;\">\r\n" +
                "														<th>\r\n" +
                "															<div class=\"rotate\">Loot Available</div>\r\n" +
                "														</th>\r\n" +
                "														<td class=\"lootIndexGraphScale\">\r\n" +
                "															<div class=\"rotate\">Loot Index</div>\r\n" +
                "														</td>\r\n" +
                "														<td style=\"height: 250px;padding-left:12px;\">\r\n" +
                "															<div id=\"lootMinutesAvailableTable\">\r\n" +
                "																<div class=\"graphsLoading\">Loading...</div>\r\n" +
                "															</div>\r\n" +
                "														</td>\r\n" +
                "														<td class=\"lootIndexGraphScale right\">\r\n" +
                "															<div class=\"rotate\">Loot Index</div>\r\n" +
                "														</td>\r\n" +
                "														<th>\r\n" +
                "															<div class=\"rotate\">Loot Available</div>\r\n" +
                "														</th>\r\n" +
                "													</tr>\r\n" +
                "													<tr style=\"background-color: #FFE9D1;\">\r\n" +
                "														<th colspan=\"2\">\r\n" +
                "															<div class=\"rotate\">Players Online</div>\r\n" +
                "														</th>\r\n" +
                "														<td style=\"height: 100px;\">\r\n" +
                "															<div id=\"totalPlayersOnline\">\r\n" +
                "																<div class=\"graphsLoading\">Loading...</div>\r\n" +
                "															</div>\r\n" +
                "														</td>\r\n" +
                "														<th colspan=\"2\">\r\n" +
                "															<div class=\"rotate\">Players Online</div>\r\n" +
                "														</th>\r\n" +
                "													</tr>\r\n" +
                "													<tr style=\"background-color: #D6F1F6;\">\r\n" +
                "														<th colspan=\"2\">\r\n" +
                "															<div class=\"rotate\">Shielded Players</div>\r\n" +
                "														</th>\r\n" +
                "														<td style=\"height: 100px;\">\r\n" +
                "															<div id=\"shieldedPlayers\">\r\n" +
                "																<div class=\"graphsLoading\">Loading...</div>\r\n" +
                "															</div>\r\n" +
                "														</td>\r\n" +
                "														<th colspan=\"2\">\r\n" +
                "															<div class=\"rotate\">Shielded Players</div>\r\n" +
                "														</th>\r\n" +
                "													</tr>\r\n" +
                "													<tr style=\"background-color: #EEEEEE;\">\r\n" +
                "														<th class=\"timeScaleHeader\" colspan=\"2\">Time</th>\r\n" +
                "														<td style=\"height: 20px;\">\r\n" +
                "															<div id=\"timeScaleGraphB\"></div>\r\n" +
                "														</td>\r\n" +
                "														<th class=\"timeScaleHeader\" colspan=\"2\">Time</th>\r\n" +
                "													</tr>\r\n" +
                "												</table>\r\n" +
                "											</div>\r\n" +
                "  <div id=\"yzycoc\"></div>										</td>\r\n" +
                "									</tr>\r\n" +
                "								</table>\r\n" +
                "							</td>\r\n" +
                "						</tr>\r\n" +
                "					</table>\r\n" +
                "				</td>\r\n" +
                "			</tr>\r\n" +
                "		</table>\r\n" +
                "		<script language=\"javascript\">\r\n" +
                "			function getAjaxObj() {\r\n" +
                "				if (window.XMLHttpRequest) {\r\n" +
                "					return new XMLHttpRequest();\r\n" +
                "				} else if (window.ActiveXObject) {\r\n" +
                "					return new ActiveXObject('Microsoft.XMLHTTP');\r\n" +
                "				} else {\r\n" +
                "					alert('Your browser does not support AJAX.')\r\n" +
                "					return;\r\n" +
                "				}\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			/**\r\n" +
                "			 * Any values passed in the passThruParameters will be passed to the response function as the second argument\r\n" +
                "			 *\r\n" +
                "			 * @param serverScriptName\r\n" +
                "			 * @param requestVars\r\n" +
                "			 * @param responseHandler\r\n" +
                "			 * @param optionalPassThruParameters\r\n" +
                "			 */\r\n" +
                "			function ajaxRequest(serverScriptName, requestVars, responseHandler, passThruParameters) {\r\n" +
                "				var ajaxObj = getAjaxObj();\r\n" +
                "\r\n" +
                "				ajaxObj.open('POST', serverScriptName, true);\r\n" +
                "				ajaxObj.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');\r\n" +
                "				ajaxObj.setRequestHeader('Content-length', requestVars.length);\r\n" +
                "				ajaxObj.setRequestHeader('Connection', 'close');\r\n" +
                "				ajaxObj.onreadystatechange = function() {\r\n" +
                "					if (ajaxObj.readyState == 4) {\r\n" +
                "						eval(responseHandler + '(ajaxObj.responseText' + ((passThruParameters == undefined) ? '' : (\r\n" +
                "							', passThruParameters')) + ');');\r\n" +
                "					}\r\n" +
                "				}\r\n" +
                "				ajaxObj.send(requestVars);\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function jsonDecode(jsonString) {\r\n" +
                "				if (jsonString) {\r\n" +
                "					return eval('(' + jsonString + ');');\r\n" +
                "				} else {\r\n" +
                "					return false;\r\n" +
                "				}\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			var cachedBrowserType = false;\r\n" +
                "\r\n" +
                "			function getBrowserType() {\r\n" +
                "				if (cachedBrowserType == false) {\r\n" +
                "					if (document.layers) {\r\n" +
                "						browserType = 'nn4'\r\n" +
                "					}\r\n" +
                "					if (document.all) {\r\n" +
                "						browserType = 'ie'\r\n" +
                "					}\r\n" +
                "					if (window.navigator.userAgent.toLowerCase().match('gecko')) {\r\n" +
                "						browserType = 'gecko';\r\n" +
                "					}\r\n" +
                "					cachedBrowserType = browserType;\r\n" +
                "				} else {\r\n" +
                "					browserType = cachedBrowserType;\r\n" +
                "				}\r\n" +
                "				return browserType;\r\n" +
                "			}\r\n" +
                "			// Javascript equivalent to PHP's number_format() function.\r\n" +
                "			function number_format(num, precision, decpoint, sep) {\r\n" +
                "				// apply precision\r\n" +
                "				num = new Number(num);\r\n" +
                "				num = num.toFixed(precision);\r\n" +
                "\r\n" +
                "				// check for missing parameters and use defaults if so\r\n" +
                "				if (arguments.length == 2) {\r\n" +
                "					sep = \",\";\r\n" +
                "				}\r\n" +
                "				if (arguments.length == 1) {\r\n" +
                "					sep = \",\";\r\n" +
                "					decpoint = \".\";\r\n" +
                "				}\r\n" +
                "\r\n" +
                "				// need a string for operations\r\n" +
                "				num = num.toString();\r\n" +
                "				// separate the whole number and the fraction if possible\r\n" +
                "				a = num.split('.');\r\n" +
                "				x = a[0]; // decimal\r\n" +
                "				y = a[1]; // fraction\r\n" +
                "				z = \"\";\r\n" +
                "				if (typeof(x) != \"undefined\") {\r\n" +
                "					// reverse the digits. regexp works from left to right.\r\n" +
                "					for (i = x.length - 1; i >= 0; i--)\r\n" +
                "						z += x.charAt(i);\r\n" +
                "					// add seperators. but undo the trailing one, if there\r\n" +
                "					z = z.replace(/(\\d{3})/g, \"$1\" + sep);\r\n" +
                "					if (z.slice(-sep.length) == sep)\r\n" +
                "						z = z.slice(0, -sep.length);\r\n" +
                "					x = \"\";\r\n" +
                "					// reverse again to get back the number\r\n" +
                "					for (i = z.length - 1; i >= 0; i--)\r\n" +
                "						x += z.charAt(i);\r\n" +
                "					// add the fraction back in, if it was there\r\n" +
                "					if (typeof(y) != \"undefined\" && y.length > 0)\r\n" +
                "						x += decpoint + y;\r\n" +
                "				}\r\n" +
                "				// For negative numbers, remove instances of -,\r\n" +
                "				x = x.replace('-,', '-');\r\n" +
                "				return x;\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function globalEval(src) {\r\n" +
                "				if (window.execScript) {\r\n" +
                "					window.execScript(src);\r\n" +
                "					return;\r\n" +
                "				}\r\n" +
                "				var fn = function() {\r\n" +
                "					window.eval.call(window, src);\r\n" +
                "				};\r\n" +
                "				fn();\r\n" +
                "			};\r\n" +
                "\r\n" +
                "			function jsonEncode(arr) {\r\n" +
                "				var jsonKeys = [];\r\n" +
                "				for (var key in arr) {\r\n" +
                "					var val = arr[key];\r\n" +
                "\r\n" +
                "					// Key\r\n" +
                "					var str = '\"' + key + '\":';\r\n" +
                "\r\n" +
                "					// Value\r\n" +
                "					if (val == null) {\r\n" +
                "						str += 'null';\r\n" +
                "					} else if (typeof(val) == 'object') {\r\n" +
                "						str += jsonEncode(val); // Recursion\r\n" +
                "					} else {\r\n" +
                "						if (typeof(val) == 'number') {\r\n" +
                "							str += val;\r\n" +
                "						} else if (typeof(val) == 'string') {\r\n" +
                "							str += '\"' + escape(val) + '\"';\r\n" +
                "						} else if (typeof(val) === false) {\r\n" +
                "							str += 'false';\r\n" +
                "						} else if (typeof(val) === true) {\r\n" +
                "							str += 'true';\r\n" +
                "						} else if (typeof(val) == null) {\r\n" +
                "							str += 'null';\r\n" +
                "						} else {\r\n" +
                "							str += '\"\"';\r\n" +
                "						}\r\n" +
                "					}\r\n" +
                "					jsonKeys.push(str);\r\n" +
                "				}\r\n" +
                "\r\n" +
                "				return '{' + jsonKeys.join(',') + '}';\r\n" +
                "			}\r\n" +
                "\r\n" +
                "\r\n" +
                "			////////////////////////////////////////////\r\n" +
                "\r\n" +
                "			var timeNow = graphData = null;\r\n" +
                "			var isPortraitOrientation = true;\r\n" +
                "			var isLandscapeBigOrientation = true;\r\n" +
                "			var isPortraitBigOrientation = true;\r\n" +
                "			var offsetDataSets = 0;\r\n" +
                "\r\n" +
                "			// data value constraints\r\n" +
                "			var maxTotalLootMinuteBalance = 49916110;\r\n" +
                "			var maxTotalPlayersOnline = 279083;\r\n" +
                "			var minTotalPlayersOnline = 149972;\r\n" +
                "			var maxShieldedPlayers = 1754595;\r\n" +
                "			var minShieldedPlayers = 1418520;\r\n" +
                "\r\n" +
                "			var firstLoad = true;\r\n" +
                "			var lmChanger = [];\r\n" +
                "			lmChanger['trend'] = false;\r\n" +
                "			lmChanger['regionData'] = false;\r\n" +
                "			lmChanger['balances'] = [];\r\n" +
                "			lmChanger['balances']['total'] = [];\r\n" +
                "			lmChanger['balances']['total']['lootMinutes'] = false;\r\n" +
                "			lmChanger['balances']['total']['playersOnline'] = false;\r\n" +
                "			lmChanger['balances']['total']['playersOffline'] = false;\r\n" +
                "			lmChanger['balances']['regions'] = [];\r\n" +
                "			lmChanger['rate'] = [];\r\n" +
                "			lmChanger['rate']['total'] = []\r\n" +
                "			lmChanger['rate']['total']['lootMinutes'] = false;\r\n" +
                "			lmChanger['rate']['total']['playersOnline'] = false;\r\n" +
                "			lmChanger['rate']['total']['playersOffline'] = false;\r\n" +
                "			lmChanger['rate']['regions'] = [];\r\n" +
                "\r\n" +
                "			function initializeFull() {\r\n" +
                "\r\n" +
                "				//setTimeout('document.getElementById(\\'goFundMe1\\').style.display = \\'inline\\';', 10000);\r\n" +
                "				//setTimeout('document.getElementById(\\'goFundMe2\\').style.display = \\'inline\\';', 10000);\r\n" +
                "\r\n" +
                "				//	setTimeout('document.getElementById(\\'appStore1\\').style.display = \\'block\\';', 5000);\r\n" +
                "				//	setTimeout('document.getElementById(\\'appStore2\\').style.display = \\'block\\';', 5000);\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function initializeMobile() {\r\n" +
                "				// determine orientation\r\n" +
                "				if (window.innerHeight > window.innerWidth) {\r\n" +
                "					// portrait\r\n" +
                "					offsetDataSets = 120;\r\n" +
                "					isPortraitOrientation = true;\r\n" +
                "					isLandscapeBigOrientation = false;\r\n" +
                "					if (window.innerHeight < 600) {\r\n" +
                "						document.getElementById('graphPlayersOnline').style.display = 'none';\r\n" +
                "						isPortraitBigOrientation = false;\r\n" +
                "						document.getElementById('forecastMessage').style.fontSize = '13px';\r\n" +
                "						if (document.getElementById('headerAdGraphSmall') != undefined) {\r\n" +
                "							document.getElementById('headerAdGraphSmall').style.display = 'block';\r\n" +
                "						}\r\n" +
                "					} else if (window.innerWidth < 730) {\r\n" +
                "						if (document.getElementById('headerAdGraphSmall') != undefined) {\r\n" +
                "							document.getElementById('headerAdGraphSmall').style.display = 'block';\r\n" +
                "						}\r\n" +
                "					} else {\r\n" +
                "						if (document.getElementById('headerAdGraphBig') != undefined) {\r\n" +
                "							document.getElementById('headerAdGraphBig').style.display = 'block';\r\n" +
                "						}\r\n" +
                "					}\r\n" +
                "				} else {\r\n" +
                "					// landscape\r\n" +
                "					if (window.innerHeight < 500) {\r\n" +
                "						document.getElementById('lootSummaryBox').style.display = 'none';\r\n" +
                "						isLandscapeBigOrientation = false;\r\n" +
                "					}\r\n" +
                "					isPortraitOrientation = isPortraitBigOrientation = false;\r\n" +
                "					if (document.getElementById('headerAdGraphBig') != undefined) {\r\n" +
                "						document.getElementById('headerAdGraphBig').style.display = 'block';\r\n" +
                "					}\r\n" +
                "				}\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function initializeMobileStats() {\r\n" +
                "				// determine orientation\r\n" +
                "				if (window.innerHeight > window.innerWidth) {\r\n" +
                "					isPortraitOrientation = true;\r\n" +
                "					isLandscapeBigOrientation = false;\r\n" +
                "					if (window.innerHeight < 600 || window.innerWidth < 600) {\r\n" +
                "						isPortraitBigOrientation = false;\r\n" +
                "						isLandscapeBigOrientation = false;\r\n" +
                "						if (document.getElementById('headerAdWorldwideStats') != undefined) {\r\n" +
                "							document.getElementById('headerAdWorldwideStats').style.display = 'block';\r\n" +
                "							document.getElementById('footerAdWorldwideStats').style.display = 'block';\r\n" +
                "						}\r\n" +
                "					} else {\r\n" +
                "						isPortraitBigOrientation = true;\r\n" +
                "						isLandscapeBigOrientation = false;\r\n" +
                "						document.getElementById('worldwideStats').style.display = 'none';\r\n" +
                "						document.getElementById('worldwideStatsBig').style.display = 'table';\r\n" +
                "						document.getElementById('tableRegionStats').style.display = 'none';\r\n" +
                "						document.getElementById('tableRegionStatsLandscape').style.display = 'table';\r\n" +
                "						if (document.getElementById('headerAdWorldwideStatsBig') != undefined) {\r\n" +
                "							document.getElementById('headerAdWorldwideStatsBig').style.display = 'block';\r\n" +
                "							document.getElementById('footerAdWorldwideStatsBig').style.display = 'block';\r\n" +
                "						}\r\n" +
                "					}\r\n" +
                "				} else {\r\n" +
                "					isPortraitOrientation = false;\r\n" +
                "					if (window.innerHeight < 500) {\r\n" +
                "						isLandscapeBigOrientation = false;\r\n" +
                "						isPortraitBigOrientation = false;\r\n" +
                "						document.getElementById('tableRegionStats').style.display = 'none';\r\n" +
                "						document.getElementById('tableRegionStatsLandscape').style.display = 'table';\r\n" +
                "					} else {\r\n" +
                "						isLandscapeBigOrientation = true;\r\n" +
                "						isPortraitBigOrientation = false;\r\n" +
                "						document.getElementById('worldwideStats').style.display = 'none';\r\n" +
                "						document.getElementById('worldwideStatsBig').style.display = 'table';\r\n" +
                "						document.getElementById('tableRegionStats').style.display = 'none';\r\n" +
                "						document.getElementById('tableRegionStatsLandscapeBig').style.display = 'table';\r\n" +
                "						if (document.getElementById('headerAdWorldwideStatsBig') != undefined) {\r\n" +
                "							document.getElementById('headerAdWorldwideStatsBig').style.display = 'block';\r\n" +
                "							document.getElementById('footerAdWorldwideStatsBig').style.display = 'block';\r\n" +
                "						}\r\n" +
                "					}\r\n" +
                "				}\r\n" +
                "				\r\n" +
                "			}\r\n" +
                "\r\n" +
                "\r\n" +
                "			function updateGraph() {\r\n" +
                "				ajaxRequest('TOTALS.json', '', 'updateGraphResponse')\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function updateGraphMobile() {\r\n" +
                "				ajaxRequest('../TOTALS.json', '', 'updateGraphMobileResponse')\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function updateGraphResponse(resp) {\r\n" +
                "				graphData = jsonDecode(resp);\r\n" +
                "				offsetDataSets = 0;\r\n" +
                "				redrawGraph();\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function updateGraphMobileResponse(resp) {\r\n" +
                "				graphData = jsonDecode(resp);\r\n" +
                "				offsetDataSets = 0;\r\n" +
                "				redrawGraphMobile();\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function redrawGraph() {\r\n" +
                "				buildGraph(340, 10, maxTotalLootMinuteBalance, 'lootMinutesAvailableTable', 'DFE9F6', true, graphData['totals'][\r\n" +
                "					'lootMinutes'\r\n" +
                "				], 0);\r\n" +
                "				buildGraph(110, 10, maxTotalPlayersOnline, 'totalPlayersOnline', 'FFE9D1', false, graphData['totals'][\r\n" +
                "					'playersOnline'\r\n" +
                "				], (maxTotalPlayersOnline - ((maxTotalPlayersOnline - minTotalPlayersOnline) * 1.1)));\r\n" +
                "				buildGraph(110, 10, maxShieldedPlayers, 'shieldedPlayers', 'D6F1F6', false, graphData['totals']['shieldedPlayers'],\r\n" +
                "					(maxShieldedPlayers - ((maxShieldedPlayers - minShieldedPlayers) * 1.1)), '00363F');\r\n" +
                "				buildTimeScale('timeScaleGraphA');\r\n" +
                "				buildTimeScale('timeScaleGraphB');\r\n" +
                "				++offsetDataSets;\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function redrawGraphMobile() {\r\n" +
                "				// determine height available for graph depending on client height\r\n" +
                "				var totalHeight = window.innerHeight;\r\n" +
                "				var totalWidth = window.innerWidth;\r\n" +
                "				if (isPortraitOrientation) {\r\n" +
                "					if (isPortraitBigOrientation) {\r\n" +
                "						totalHeight -= 345\r\n" +
                "					} else {\r\n" +
                "						totalHeight -= 245;\r\n" +
                "					}\r\n" +
                "					// save room for bottom ad\r\n" +
                "					if (totalWidth < 730 && document.getElementById('footerAdGraphSmallPortraitBottom') != undefined) {\r\n" +
                "						totalHeight -= 80;\r\n" +
                "						document.getElementById('footerAdGraphSmallPortraitBottom').style.display = 'table-row';\r\n" +
                "					} else if (totalWidth >= 730 && document.getElementById('footerAdGraphBigBottom') != undefined) {\r\n" +
                "						totalHeight -= 15;\r\n" +
                "						document.getElementById('footerAdGraphBigBottom').style.display = 'table-row';\r\n" +
                "					}\r\n" +
                "\r\n" +
                "					if (totalHeight < 130) {\r\n" +
                "						totalHeight = 130;\r\n" +
                "					}\r\n" +
                "\r\n" +
                "					buildGraph(totalHeight, 10, maxTotalLootMinuteBalance, 'lootMinutesAvailableTable', 'D8F0EF', true, graphData[\r\n" +
                "						'totals']['lootMinutes']);\r\n" +
                "					if (isPortraitBigOrientation) {\r\n" +
                "						buildGraph(100, 10, maxTotalPlayersOnline, 'totalPlayersOnline', 'FFE9D1', false, graphData['totals'][\r\n" +
                "							'playersOnline'\r\n" +
                "						]);\r\n" +
                "					}\r\n" +
                "					document.getElementById('lootAvailable1').innerHTML = '<div class=\"rotate\">Loot Available</div>';\r\n" +
                "					document.getElementById('lootAvailable2').innerHTML = '<div class=\"rotate\">Loot Available</div>';\r\n" +
                "					document.getElementById('lootAvailable1').style.paddingTop = '65px';\r\n" +
                "					document.getElementById('lootAvailable2').style.paddingTop = '65px';\r\n" +
                "				} else {\r\n" +
                "					var playersOnHeight = 90;\r\n" +
                "					var height = (totalHeight - playersOnHeight - 80);\r\n" +
                "					if (isLandscapeBigOrientation) {\r\n" +
                "						height -= 80;\r\n" +
                "						if (document.getElementById('footerAdGraphBigBottom') != undefined) {\r\n" +
                "							document.getElementById('footerAdGraphBigBottom').style.display = 'table-row';\r\n" +
                "						}\r\n" +
                "					}\r\n" +
                "					if (height < 120) {\r\n" +
                "						height = 120;\r\n" +
                "						playersOnHeight = (totalHeight - height - 80);\r\n" +
                "						if (playersOnHeight < 50) {\r\n" +
                "							playersOnHeight = 50;\r\n" +
                "							height = 100;\r\n" +
                "						}\r\n" +
                "						if (playersOnHeight < 80) {\r\n" +
                "							document.getElementById('playersOnline1').innerHTML = '<div class=\"rotate\">On</div>';\r\n" +
                "							document.getElementById('playersOnline2').innerHTML = '<div class=\"rotate\">On</div>';\r\n" +
                "						}\r\n" +
                "						document.getElementById('playersOnline1').style.paddingTop = '0px';\r\n" +
                "						document.getElementById('playersOnline2').style.paddingTop = '0px';\r\n" +
                "					}\r\n" +
                "					if (document.getElementById('headerAdGraphBig') != undefined) {\r\n" +
                "						height -= 100;\r\n" +
                "						document.getElementById('headerAdGraphBig').style.display = 'block';\r\n" +
                "					}\r\n" +
                "					if (playersOnHeight > 80) {\r\n" +
                "						height += (playersOnHeight - 80);\r\n" +
                "						playersOnHeight = 80;\r\n" +
                "					}\r\n" +
                "					if (height < 100) {\r\n" +
                "						height = (height + playersOnHeight);\r\n" +
                "						playersOnHeight = false;\r\n" +
                "					}\r\n" +
                "					buildGraph(height, 10, maxTotalLootMinuteBalance, 'lootMinutesAvailableTable', 'D8F0EF', true, graphData['totals']\r\n" +
                "						['lootMinutes']);\r\n" +
                "					if (playersOnHeight) {\r\n" +
                "						buildGraph(playersOnHeight, 10, maxTotalPlayersOnline, 'totalPlayersOnline', 'FFE9D1', false, graphData['totals']\r\n" +
                "							['playersOnline'], (maxTotalPlayersOnline - ((maxTotalPlayersOnline - minTotalPlayersOnline) * 1.1)));\r\n" +
                "					} else {\r\n" +
                "						document.getElementById('graphPlayersOnline').style.display = 'none';\r\n" +
                "					}\r\n" +
                "					document.getElementById('lootAvailable1').innerHTML = '<div class=\"rotate\">Loot<br>Available</div>';\r\n" +
                "					document.getElementById('lootAvailable2').innerHTML = '<div class=\"rotate\">Loot<br>Available</div>';\r\n" +
                "					document.getElementById('lootAvailable1').style.paddingTop = '20px';\r\n" +
                "					document.getElementById('lootAvailable2').style.paddingTop = '20px';\r\n" +
                "				}\r\n" +
                "				buildTimeScale('timeScaleGraphA');\r\n" +
                "				buildTimeScale('timeScaleGraphB');\r\n" +
                "				++offsetDataSets;\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function buildGraph(tableHeight, topMargin, maxValue, destinationDivId, backgroundColor, isMainGraph, dataSet,\r\n" +
                "				offsetFactor, graphColor) {\r\n" +
                "				var lootIndexScaleMarkers = graphData['lootIndexScaleMarkers'];\r\n" +
                "				var lootIndexScaleMarkerColor = '7790C7';\r\n" +
                "\r\n" +
                "				var mainGraphOffsetFactor = 0; // for aesthetics on graph, the higher the number, the higher highs and lower lows there will be\r\n" +
                "				offsetFactor = ((offsetFactor == undefined) ? 0 : offsetFactor);\r\n" +
                "				var factorPixelsPerInterval = ((tableHeight - topMargin) / (maxValue - ((isMainGraph) ? mainGraphOffsetFactor :\r\n" +
                "					offsetFactor)));\r\n" +
                "				var factorIntervalsPerPixel = ((maxValue - ((isMainGraph) ? mainGraphOffsetFactor : offsetFactor)) / (tableHeight -\r\n" +
                "					topMargin));\r\n" +
                "				var bottomOffset = ((isMainGraph) ? mainGraphOffsetFactor : offsetFactor);\r\n" +
                "				var gapHeightBetweenDataAndBgMarkers = 10;\r\n" +
                "				var html = '';\r\n" +
                "				var cnt = 0;\r\n" +
                "				var localTime = new Date();\r\n" +
                "				var secondsOffset = ((localTime.getTimezoneOffset() % 60) * 60);\r\n" +
                "				for (var i in graphData['dtStamps']) {\r\n" +
                "					var dtStamp = (Number(graphData['dtStamps'][i]) + secondsOffset);\r\n" +
                "					if (cnt > offsetDataSets) {\r\n" +
                "						var factor = Number(dataSet[i]);\r\n" +
                "						var isNow = (dtStamp == (timeNow + secondsOffset));\r\n" +
                "						var isHourMarker = ((dtStamp % 3600) == 0);\r\n" +
                "						var isQuarterMarker = ((dtStamp % 900) == 0);\r\n" +
                "						var bgColor = ((isNow) ? '0000AA' : ((isMainGraph) ? graphData['mainColor'][i] : ((graphColor == undefined || !\r\n" +
                "							graphColor) ? '464646' : graphColor)));\r\n" +
                "						var thisHeight = Math.floor(factorPixelsPerInterval * (factor - bottomOffset));\r\n" +
                "						var markerHeight = (tableHeight - thisHeight - gapHeightBetweenDataAndBgMarkers);\r\n" +
                "						html += '<td>';\r\n" +
                "						/*\r\n" +
                "						if (isNow) {\r\n" +
                "							html += '<div style=\"background-color:#0000AA;height:'+(tableHeight - thisHeight)+'px;\"></div>';\r\n" +
                "						} else if (isHourMarker && markerHeight > 0) {\r\n" +
                "							html += '<div style=\"background-color:#FBCBBD;height:'+(markerHeight)+'px;\"></div><div style=\"height:15px;\"></div>';\r\n" +
                "						} else if (isQuarterMarker && markerHeight > 0) {\r\n" +
                "							html += '<div style=\"background-color:#E1E1E1;height:'+(markerHeight)+'px;\"></div><div style=\"height:15px;\"></div>';\r\n" +
                "						}*/\r\n" +
                "						var isFirstColumn = ((i - offsetDataSets) == 1);\r\n" +
                "						var isLastColumn = (i == (graphData['dtStamps'].length - 1));\r\n" +
                "						var htmlPostTd = '';\r\n" +
                "						if (isNow) {\r\n" +
                "							html += '<div style=\"background-color:#0000AA;height:' + (tableHeight - thisHeight) + 'px;\"></div>';\r\n" +
                "						} else {\r\n" +
                "							// determine bg color of vertical bar\r\n" +
                "							var spacerBgColor = ((isHourMarker) ? 'FBC6B6' : ((isQuarterMarker) ? 'DCDCDC' : backgroundColor));\r\n" +
                "\r\n" +
                "							if (isMainGraph) {\r\n" +
                "								// write the loot index labels\r\n" +
                "								if (isFirstColumn || isLastColumn) {\r\n" +
                "									var thisHtml = '';\r\n" +
                "									var startAt = tableHeight;\r\n" +
                "									for (var pixel = startAt, lastPixelOfMarker = startAt, currentMarkerIndex = 9; pixel >= 0; --pixel) {\r\n" +
                "										var thisPixelFactor = Math.ceil(maxValue - ((startAt - pixel) * factorIntervalsPerPixel));\r\n" +
                "										if (thisPixelFactor <= lootIndexScaleMarkers[currentMarkerIndex] && currentMarkerIndex >= 0) {\r\n" +
                "											// marker here\r\n" +
                "\r\n" +
                "											// need to put the background bar\r\n" +
                "											var thisBgBarHeight = (lastPixelOfMarker - pixel);\r\n" +
                "											if (thisBgBarHeight > 0) {\r\n" +
                "												thisHtml += '<div style=\"background-color:#' + spacerBgColor + ';height:' + thisBgBarHeight + 'px;\"></div>';\r\n" +
                "											}\r\n" +
                "\r\n" +
                "											// marker div\r\n" +
                "											var dispMarkerIndex = (currentMarkerIndex + 1);\r\n" +
                "											if (currentMarkerIndex < 9) {\r\n" +
                "												if (isFirstColumn) {\r\n" +
                "													dispMarkerIndex = '&nbsp;&nbsp;' + dispMarkerIndex;\r\n" +
                "												} else {\r\n" +
                "													dispMarkerIndex = dispMarkerIndex + '&nbsp;&nbsp;';\r\n" +
                "												}\r\n" +
                "\r\n" +
                "											}\r\n" +
                "											thisHtml += '<div class=\"' + ((isFirstColumn) ? 'markerIndexLabel' : 'markerIndexLabelRight') + '\">' +\r\n" +
                "												dispMarkerIndex + '</div>';\r\n" +
                "											thisHtml += '<div class=\"' + ((isFirstColumn) ? 'markerIndex' : 'markerIndexRight') +\r\n" +
                "												'\" style=\"background-color:#' + lootIndexScaleMarkerColor + ';\"></div>';\r\n" +
                "											lastPixelOfMarker = pixel;\r\n" +
                "											--currentMarkerIndex;\r\n" +
                "										}\r\n" +
                "\r\n" +
                "									}\r\n" +
                "									// add spacer to the bottom\r\n" +
                "									var thisBgBarHeight = (Math.ceil(lootIndexScaleMarkers[0] * factorPixelsPerInterval) + 1);\r\n" +
                "									thisHtml += '<div style=\"background-color:#' + backgroundColor + ';height:' + thisBgBarHeight +\r\n" +
                "										'px;\"></div></td>';\r\n" +
                "									if (isFirstColumn) {\r\n" +
                "										thisHtml += '<td>';\r\n" +
                "										html += thisHtml;\r\n" +
                "									} else {\r\n" +
                "										htmlPostTd += '<td>' + thisHtml;\r\n" +
                "									}\r\n" +
                "\r\n" +
                "								}\r\n" +
                "\r\n" +
                "								// build the graph now\r\n" +
                "\r\n" +
                "								// index markers\r\n" +
                "								var startAt = lastPixelOfMarker = tableHeight;\r\n" +
                "								var stopAt = (tableHeight - markerHeight);\r\n" +
                "								if (markerHeight > 0 && (i % 4) == 0) {\r\n" +
                "									for (var pixel = startAt, currentMarkerIndex = 9; pixel >= stopAt; --pixel) {\r\n" +
                "										// what factor is this pixel representing?\r\n" +
                "										var thisPixelFactor = Math.ceil(maxValue - ((startAt - (pixel + topMargin)) * factorIntervalsPerPixel));\r\n" +
                "										if (thisPixelFactor <= lootIndexScaleMarkers[currentMarkerIndex] && currentMarkerIndex >= 0) {\r\n" +
                "											// marker here\r\n" +
                "\r\n" +
                "											// need to put the background bar\r\n" +
                "											var thisBgBarHeight = (lastPixelOfMarker - pixel - 1);\r\n" +
                "											if (thisBgBarHeight > 0) {\r\n" +
                "												html += '<div style=\"background-color:#' + spacerBgColor + ';height:' + thisBgBarHeight + 'px;\"></div>';\r\n" +
                "											}\r\n" +
                "\r\n" +
                "											// marker div\r\n" +
                "											html += '<div style=\"background-color:#' + lootIndexScaleMarkerColor + ';height:1px;\"></div>';\r\n" +
                "											lastPixelOfMarker = pixel;\r\n" +
                "											--currentMarkerIndex;\r\n" +
                "										}\r\n" +
                "									}\r\n" +
                "								}\r\n" +
                "\r\n" +
                "								// add final background bar\r\n" +
                "								var thisBgBarHeight = (lastPixelOfMarker - stopAt);\r\n" +
                "							} else {\r\n" +
                "								// regular graph\r\n" +
                "								var thisBgBarHeight = markerHeight;\r\n" +
                "							}\r\n" +
                "							if (thisBgBarHeight > 0) {\r\n" +
                "								html += '<div style=\"background-color:#' + spacerBgColor + ';height:' + thisBgBarHeight + 'px;\"></div>';\r\n" +
                "							}\r\n" +
                "\r\n" +
                "						}\r\n" +
                "\r\n" +
                "						if (isFirstColumn && !isMainGraph) {\r\n" +
                "							// need to put a spacer for the other graphs so everything lines up on the first column\r\n" +
                "							html += '<div style=\"background-color:#' + backgroundColor + ';\"></div></td><td><div style=\"background-color:#' +\r\n" +
                "								backgroundColor + ';\"></div></td><td>';\r\n" +
                "						}\r\n" +
                "\r\n" +
                "						if (!isNow && (tableHeight - thisHeight) >= gapHeightBetweenDataAndBgMarkers) {\r\n" +
                "							html += '<div style=\"height:' + gapHeightBetweenDataAndBgMarkers + 'px;\"></div>';\r\n" +
                "						}\r\n" +
                "						html += '<div style=\"background-color:#' + bgColor + ';height:' + thisHeight + 'px;\"></div></td>' + htmlPostTd;\r\n" +
                "					}\r\n" +
                "					++cnt;\r\n" +
                "				}\r\n" +
                "				if (!isMainGraph) {\r\n" +
                "					html += '<td></td>';\r\n" +
                "				}\r\n" +
                "				html = '<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" class=\"graphData\" style=\"background-color:#' +\r\n" +
                "					backgroundColor + ';height:' + tableHeight + 'px;\"><tr>' + html + '</tr></table>';\r\n" +
                "				document.getElementById(destinationDivId).innerHTML = html;\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function buildTimeScale(destinationDivId) {\r\n" +
                "				var html = '';\r\n" +
                "				var cnt = 0;\r\n" +
                "				var localTime = new Date();\r\n" +
                "				var secondsOffset = ((localTime.getTimezoneOffset() % 60) * 60);\r\n" +
                "				for (var i in graphData['dtStamps']) {\r\n" +
                "					if (cnt > offsetDataSets) {\r\n" +
                "						var dtStamp = (Number(graphData['dtStamps'][i] + secondsOffset));\r\n" +
                "						if (dtStamp == (timeNow + secondsOffset)) {\r\n" +
                "							var bgColor = '0000AA';\r\n" +
                "						} else if (((dtStamp % 3600) == 0)) {\r\n" +
                "							var bgColor = 'FBCBBD';\r\n" +
                "						} else if ((dtStamp % 900) == 0) {\r\n" +
                "							var bgColor = 'DBDBDB';\r\n" +
                "						} else {\r\n" +
                "							var bgColor = 'EEEEEE';\r\n" +
                "						}\r\n" +
                "\r\n" +
                "						//			var markerOffset = ((militaryTime) ? 300 : 360);\r\n" +
                "						var markerOffset = 360;\r\n" +
                "						var hourTextMarker = (getBrowserType() != 'ie' && (((dtStamp + markerOffset) % 3600) == 0));\r\n" +
                "\r\n" +
                "						if (hourTextMarker) {\r\n" +
                "							// determine time for local timezone\r\n" +
                "							var d = new Date(((dtStamp - secondsOffset + markerOffset) * 1000));\r\n" +
                "							var hours = d.getHours();\r\n" +
                "							if (militaryTime) {\r\n" +
                "								var ampm = '';\r\n" +
                "								if (hours < 10) {\r\n" +
                "									hours = '0' + hours;\r\n" +
                "								}\r\n" +
                "							} else {\r\n" +
                "								if (hours > 12) {\r\n" +
                "									hours -= 12;\r\n" +
                "									var ampm = 'p';\r\n" +
                "								} else if (hours == 12) {\r\n" +
                "									var ampm = 'p';\r\n" +
                "								} else if (hours == 0) {\r\n" +
                "									var ampm = 'a';\r\n" +
                "									hours = 12;\r\n" +
                "								} else {\r\n" +
                "									var ampm = 'a';\r\n" +
                "								}\r\n" +
                "							}\r\n" +
                "							hourTextMarker = hours + ampm;\r\n" +
                "						}\r\n" +
                "						html += '<td><div style=\"background-color:#' + bgColor + ';\">' + ((hourTextMarker) ? hourTextMarker : '') +\r\n" +
                "							'</div></td>';\r\n" +
                "					}\r\n" +
                "					++cnt;\r\n" +
                "				}\r\n" +
                "				html += '<td></td>';\r\n" +
                "				html = '<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" class=\"graphData timeGraph\"><tr>' + html +\r\n" +
                "					'</tr></table>';\r\n" +
                "				html += '';\r\n" +
                "				document.getElementById(destinationDivId).innerHTML = html;\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function getStats() {\r\n" +
                "				ajaxRequest('STATS.json', '', 'getStatsResponse');\r\n" +
                "			}\r\n" +
                "\r\n" +
                "\r\n" +
                "			// arrow constants for html\r\n" +
                "			var HTML_ARROWUP = '&#x25B4;';\r\n" +
                "			var HTML_ARROWUPBIG = '&#x25B2;';\r\n" +
                "			var HTML_ARROWDOWN = '&#x25BE;';\r\n" +
                "			var HTML_ARROWDOWNBIG = '&#x25BC;';\r\n" +
                "\r\n" +
                "			// regionstat constants\r\n" +
                "			var STAT_ID = 0;\r\n" +
                "			var STAT_NAME = 1;\r\n" +
                "			var STAT_LOCALTIME = 2;\r\n" +
                "			var STAT_LOCALTIMEMILITARY = 3;\r\n" +
                "			var STAT_ROWCOLOR = 4;\r\n" +
                "			var STAT_TOTALPLAYERS = 5;\r\n" +
                "			var STAT_PLAYERSONLINE = 6;\r\n" +
                "			var STAT_PLAYERSONLINECHANGE = 7;\r\n" +
                "			var STAT_LOOTMINUTEBALANCE = 8;\r\n" +
                "			var STAT_LOOTMINUTECHANGE = 9;\r\n" +
                "			var STAT_SHIELDEDPLAYERS = 10;\r\n" +
                "			var STAT_SHIELDEDPLAYERSCHANGE = 11;\r\n" +
                "			var STAT_ATTACKABLEPLAYERS = 12;\r\n" +
                "			var STAT_ATTACKABLEPLAYERSCHANGE = 13;\r\n" +
                "");

        sb.append("\r\n" +
                "			function getStatsResponse(resp) {\r\n" +
                "				resp = jsonDecode(resp);\r\n" +
                "\r\n" +
                "				// build region stats\r\n" +
                "				var factor = 60; // 60 = 1 second, adjust it according to the timer, 120 = every half second, etc...\r\n" +
                "				var totalPlayersOffline = 0;\r\n" +
                "\r\n" +
                "				// build main stats\r\n" +
                "\r\n" +
                "				// forecast message\r\n" +
                "				var regionData = resp['regionStats'];\r\n" +
                "				regionData.sort(function(a, b) {\r\n" +
                "					var aOnline = (a[STAT_PLAYERSONLINE] / a[STAT_TOTALPLAYERS]);\r\n" +
                "					var bOnline = (b[STAT_PLAYERSONLINE] / b[STAT_TOTALPLAYERS]);\r\n" +
                "					if (aOnline > bOnline) {\r\n" +
                "						return -1;\r\n" +
                "					} else if (aOnline < bOnline) {\r\n" +
                "						return 1;\r\n" +
                "					} else {\r\n" +
                "						return 0;\r\n" +
                "					}\r\n" +
                "				});\r\n" +
                "				var htmlA = htmlB = '';\r\n" +
                "				for (var i in regionData) {\r\n" +
                "					var html = ('<div>' + regionData[i][STAT_NAME].replace(' Time', '') + '</div>');\r\n" +
                "					if (i < 7) {\r\n" +
                "						htmlA += html;\r\n" +
                "					} else if (i < 14) {\r\n" +
                "						htmlB += html;\r\n" +
                "					} else {\r\n" +
                "						break;\r\n" +
                "					}\r\n" +
                "				}\r\n" +
                "				document.getElementById('mostActiveRegionsA').innerHTML = htmlA;\r\n" +
                "				document.getElementById('mostActiveRegionsB').innerHTML = htmlB;\r\n" +
                "\r\n" +
                "\r\n" +
                "				// lmChanger\r\n" +
                "				lmChanger['regionData'] = resp['regionStats'];\r\n" +
                "				lmChanger['trend'] = parseInt(resp['currentLoot']['trend']);\r\n" +
                "				lmChanger['balances']['total']['lootMinutes'] = parseInt(resp['currentLoot']['lootMinutes']);\r\n" +
                "				lmChanger['balances']['total']['playersOnline'] = parseInt(resp['currentLoot']['playersOnline']);\r\n" +
                "				lmChanger['balances']['total']['playersOffline'] = parseInt(totalPlayersOffline);\r\n" +
                "				lmChanger['balances']['total']['shieldedPlayers'] = parseInt(resp['currentLoot']['shieldedPlayers']);\r\n" +
                "				lmChanger['balances']['total']['attackablePlayers'] = parseInt(resp['currentLoot']['attackablePlayers']);\r\n" +
                "				lmChanger['rate']['total']['lootMinutes'] = (resp['currentLoot']['lootMinuteChange'] / factor);\r\n" +
                "				lmChanger['rate']['total']['playersOnline'] = (resp['currentLoot']['playersOnlineChange'] / factor);\r\n" +
                "				lmChanger['rate']['total']['playersOffline'] = ((resp['currentLoot']['playersOnlineChange'] * -1) / factor);\r\n" +
                "				lmChanger['rate']['total']['shieldedPlayers'] = (resp['currentLoot']['shieldedPlayersChange'] / factor);\r\n" +
                "				lmChanger['rate']['total']['attackablePlayers'] = (resp['currentLoot']['attackablePlayersChange'] / factor);\r\n" +
                "\r\n" +
                "				// prepare loot index template\r\n" +
                "				if (firstLoad) {\r\n" +
                "					var htmlArrowLootIndex = ((lmChanger['trend'] > 0) ? HTML_ARROWUPBIG : HTML_ARROWDOWNBIG);\r\n" +
                "					var htmlLootIndex = '<table width=\"1%\" cellspacing=\"0\" cellpadding=\"0\">' +\r\n" +
                "						'<tr>' +\r\n" +
                "						'<td id=\"lootIndexString\"></td>' +\r\n" +
                "						'<td>' +\r\n" +
                "						'<table width=\"1%\" cellpadding=\"0\" cellspacing=\"0\">' +\r\n" +
                "						'<tr>' +\r\n" +
                "						'<td id=\"lootIndexArrow1\" class=\"lootIndexArrow\">' + htmlArrowLootIndex + '</td>' +\r\n" +
                "						'</tr>' +\r\n" +
                "						'<tr>' +\r\n" +
                "						'<td id=\"lootIndexArrow2\" class=\"lootIndexArrow\">' + htmlArrowLootIndex + '</td>' +\r\n" +
                "						'</tr>' +\r\n" +
                "						'<tr>' +\r\n" +
                "						'<td id=\"lootIndexArrow3\" class=\"lootIndexArrow\">' + htmlArrowLootIndex + '</td>' +\r\n" +
                "						'</tr>' +\r\n" +
                "						'</table>' +\r\n" +
                "						'</td>' +\r\n" +
                "						'</tr>' +\r\n" +
                "						'</table>';\r\n" +
                "					document.getElementById('lootIndexStringContainer').innerHTML = htmlLootIndex;\r\n" +
                "				}\r\n" +
                "				document.getElementById('lootIndexString').innerHTML = number_format(Number(resp['lootIndexString']), 1,\r\n" +
                "					decimalChar, commaChar);\r\n" +
                "\r\n" +
                "				// figure out arrows\r\n" +
                "				var htmlArrowPlayersOnline = ((lmChanger['rate']['total']['playersOnline'] >= 0) ? HTML_ARROWUP : HTML_ARROWDOWN);\r\n" +
                "				var htmlArrowPlayersOffline = ((lmChanger['rate']['total']['playersOffline'] >= 0) ? HTML_ARROWUP : HTML_ARROWDOWN);\r\n" +
                "				var htmlArrowShieldedPlayers = ((lmChanger['rate']['total']['shieldedPlayers'] >= 0) ? HTML_ARROWUP :\r\n" +
                "					HTML_ARROWDOWN);\r\n" +
                "				var htmlArrowAttackablePlayers = ((lmChanger['rate']['total']['attackablePlayers'] >= 0) ? HTML_ARROWUP :\r\n" +
                "					HTML_ARROWDOWN);\r\n" +
                "\r\n" +
                "				// update main stats\r\n" +
                "				document.getElementById('currentLootLootMinutes').innerHTML = number_format(lmChanger['balances']['total'][\r\n" +
                "					'lootMinutes'\r\n" +
                "				], 0, decimalChar, commaChar);\r\n" +
                "				document.getElementById('currentLootPlayersOnline').innerHTML = htmlArrowPlayersOnline + ' ' + number_format(\r\n" +
                "					lmChanger['balances']['total']['playersOnline'], 0, decimalChar, commaChar);\r\n" +
                "				document.getElementById('currentLootPlayersOffline').innerHTML = htmlArrowPlayersOffline + ' ' + number_format(\r\n" +
                "					lmChanger['balances']['total']['playersOffline'], 0, decimalChar, commaChar);\r\n" +
                "				document.getElementById('currentLootShieldedPlayers').innerHTML = htmlArrowShieldedPlayers + ' ' + number_format(\r\n" +
                "					lmChanger['balances']['total']['shieldedPlayers'], 0, decimalChar, commaChar);\r\n" +
                "				document.getElementById('currentLootAttackablePlayers').innerHTML = htmlArrowAttackablePlayers + ' ' +\r\n" +
                "					number_format(lmChanger['balances']['total']['attackablePlayers'], 0, decimalChar, commaChar);\r\n" +
                "\r\n" +
                "				// set loot index arrow directions\r\n" +
                "				document.getElementById('lootIndexArrow1').innerHTML = document.getElementById('lootIndexArrow2').innerHTML =\r\n" +
                "					document.getElementById('lootIndexArrow3').innerHTML = ((lmChanger['trend'] > 0) ? HTML_ARROWUPBIG :\r\n" +
                "						HTML_ARROWDOWNBIG);\r\n" +
                "\r\n" +
                "				// colors\r\n" +
                "				document.body.style.backgroundColor = '#' + resp['mainColorShadeNow'];\r\n" +
                "				document.getElementById('lootIndexString').style.color = '#' + resp['fgColor'];\r\n" +
                "				document.getElementById('lootIndexArrow1').style.color = '#' + resp['fgColor'];\r\n" +
                "				document.getElementById('lootIndexArrow2').style.color = '#' + resp['fgColor'];\r\n" +
                "				document.getElementById('lootIndexArrow3').style.color = '#' + resp['fgColor'];\r\n" +
                "				document.getElementById('tableA').style.backgroundColor = '#' + resp['bgColor'];\r\n" +
                "				document.getElementById('tableA').style.border = '3px solid #' + resp['fgColor'];\r\n" +
                "				document.getElementById('tableA').style.color = '#' + resp['fgColor'];\r\n" +
                "				document.getElementById('tableB').style.backgroundColor = '#' + resp['bgColor'];\r\n" +
                "				document.getElementById('tableB').style.border = '3px solid #' + resp['fgColor'];\r\n" +
                "				document.getElementById('tableB').style.color = '#' + resp['fgColor'];\r\n" +
                "				document.getElementById('tableC').style.backgroundColor = '#' + resp['bgColor'];\r\n" +
                "				document.getElementById('tableC').style.border = '3px solid #' + resp['fgColor'];\r\n" +
                "				document.getElementById('tableC').style.color = '#' + resp['fgColor'];\r\n" +
                "				document.getElementById('tableD').style.backgroundColor = '#' + resp['bgColor'];\r\n" +
                "				document.getElementById('tableD').style.border = '3px solid #' + resp['fgColor'];\r\n" +
                "				document.getElementById('tableD').style.color = '#' + resp['fgColor'];\r\n" +
                "				//	document.getElementById('tableE').style.backgroundColor = '#'+resp['bgColor'];\r\n" +
                "				//	document.getElementById('tableE').style.border = '3px solid #'+resp['fgColor'];\r\n" +
                "				//	document.getElementById('tableE').style.color = '#'+resp['fgColor'];\r\n" +
                "				//	document.getElementById('tableF').style.backgroundColor = '#'+resp['bgColor'];\r\n" +
                "				//	document.getElementById('tableF').style.border = '3px solid #'+resp['fgColor'];\r\n" +
                "				//	document.getElementById('tableF').style.color = '#'+resp['fgColor'];\r\n" +
                "\r\n" +
                "				// reversed for title box\r\n" +
                "				document.getElementById('mainTitleBox').style.color = '#' + resp['bgColor'];\r\n" +
                "				document.getElementById('mainTitleBox').style.backgroundColor = '#' + resp['fgColor'];\r\n" +
                "				document.getElementById('mainTitleBox').style.border = '3px solid #' + resp['bgColor'];\r\n" +
                "\r\n" +
                "				if (firstLoad) {\r\n" +
                "					firstLoad = false;\r\n" +
                "				}\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function updateLootMinuteBalances() {\r\n" +
                "				if (lmChanger['balances']['total']['lootMinutes'] != false) {\r\n" +
                "					// data is loaded, apply change and display\r\n" +
                "					lmChanger['balances']['total']['lootMinutes'] += lmChanger['rate']['total']['lootMinutes'];\r\n" +
                "					lmChanger['balances']['total']['playersOnline'] += lmChanger['rate']['total']['playersOnline'];\r\n" +
                "					lmChanger['balances']['total']['playersOffline'] += lmChanger['rate']['total']['playersOffline'];\r\n" +
                "					lmChanger['balances']['total']['shieldedPlayers'] += lmChanger['rate']['total']['shieldedPlayers'];\r\n" +
                "					lmChanger['balances']['total']['attackablePlayers'] += lmChanger['rate']['total']['attackablePlayers'];\r\n" +
                "\r\n" +
                "					var htmlArrowCurrentLootPlayersOnline = ((lmChanger['rate']['total']['playersOnline'] >= 0) ? HTML_ARROWUP :\r\n" +
                "						HTML_ARROWDOWN);\r\n" +
                "					var htmlArrowCurrentLootPlayersOffline = ((lmChanger['rate']['total']['playersOffline'] >= 0) ? HTML_ARROWUP :\r\n" +
                "						HTML_ARROWDOWN);\r\n" +
                "					var htmlArrowCurrentLootShieldedPlayers = ((lmChanger['rate']['total']['shieldedPlayers'] >= 0) ? HTML_ARROWUP :\r\n" +
                "						HTML_ARROWDOWN);\r\n" +
                "					var htmlArrowCurrentLootAttackablePlayers = ((lmChanger['rate']['total']['attackablePlayers'] >= 0) ? HTML_ARROWUP :\r\n" +
                "						HTML_ARROWDOWN);\r\n" +
                "\r\n" +
                "					// update main stats\r\n" +
                "					document.getElementById('currentLootLootMinutes').innerHTML = number_format(lmChanger['balances']['total'][\r\n" +
                "						'lootMinutes'\r\n" +
                "					], 0, decimalChar, commaChar);\r\n" +
                "					document.getElementById('currentLootPlayersOnline').innerHTML = htmlArrowCurrentLootPlayersOnline + ' ' +\r\n" +
                "						number_format(lmChanger['balances']['total']['playersOnline'], 0, decimalChar, commaChar);\r\n" +
                "					document.getElementById('currentLootPlayersOffline').innerHTML = htmlArrowCurrentLootPlayersOffline + ' ' +\r\n" +
                "						number_format(lmChanger['balances']['total']['playersOffline'], 0, decimalChar, commaChar);\r\n" +
                "					document.getElementById('currentLootShieldedPlayers').innerHTML = htmlArrowCurrentLootShieldedPlayers + ' ' +\r\n" +
                "						number_format(lmChanger['balances']['total']['shieldedPlayers'], 0, decimalChar, commaChar);\r\n" +
                "					document.getElementById('currentLootAttackablePlayers').innerHTML = htmlArrowCurrentLootAttackablePlayers + ' ' +\r\n" +
                "						number_format(lmChanger['balances']['total']['attackablePlayers'], 0, decimalChar, commaChar);\r\n" +
                "\r\n" +
                "					// update region balancs\r\n" +
                "					for (var id in lmChanger['balances']['regions']) {\r\n" +
                "						lmChanger['balances']['regions'][id]['lootMinutes'] += lmChanger['rate']['regions'][id]['lootMinutes'];\r\n" +
                "						lmChanger['balances']['regions'][id]['playersOnline'] += lmChanger['rate']['regions'][id]['playersOnline'];\r\n" +
                "						lmChanger['balances']['regions'][id]['playersOffline'] += lmChanger['rate']['regions'][id]['playersOffline'];\r\n" +
                "						lmChanger['balances']['regions'][id]['shieldedPlayers'] += lmChanger['rate']['regions'][id]['shieldedPlayers'];\r\n" +
                "						lmChanger['balances']['regions'][id]['attackablePlayers'] += lmChanger['rate']['regions'][id]['attackablePlayers'];\r\n" +
                "\r\n" +
                "						var htmlArrowLootMinutes = ((lmChanger['rate']['regions'][id]['lootMinutes'] >= 0) ? HTML_ARROWUP :\r\n" +
                "							HTML_ARROWDOWN);\r\n" +
                "						var htmlArrowPlayersOnline = ((lmChanger['rate']['regions'][id]['playersOnline'] >= 0) ? HTML_ARROWUP :\r\n" +
                "							HTML_ARROWDOWN);\r\n" +
                "						var percentPlayersOnline = ((lmChanger['balances']['regions'][id]['playersOnline'] / lmChanger['balances'][\r\n" +
                "							'regions'\r\n" +
                "						][id]['totalPlayers']) * 100);\r\n" +
                "						var htmlArrowPlayersOffline = ((lmChanger['rate']['regions'][id]['playersOnline'] < 0) ? HTML_ARROWUP :\r\n" +
                "							HTML_ARROWDOWN);\r\n" +
                "						var percentPlayersOffline = ((lmChanger['balances']['regions'][id]['playersOffline'] / lmChanger['balances'][\r\n" +
                "							'regions'\r\n" +
                "						][id]['totalPlayers']) * 100);\r\n" +
                "						var htmlArrowShieldedPlayers = ((lmChanger['rate']['regions'][id]['shieldedPlayers'] >= 0) ? HTML_ARROWUP :\r\n" +
                "							HTML_ARROWDOWN);\r\n" +
                "						var percentShieldedPlayers = ((lmChanger['balances']['regions'][id]['shieldedPlayers'] / lmChanger['balances'][\r\n" +
                "							'regions'\r\n" +
                "						][id]['totalPlayers']) * 100);\r\n" +
                "						var htmlArrowAttackablePlayers = ((lmChanger['rate']['regions'][id]['attackablePlayers'] >= 0) ? HTML_ARROWUP :\r\n" +
                "							HTML_ARROWDOWN);\r\n" +
                "						var percentAttackablePlayers = ((lmChanger['balances']['regions'][id]['attackablePlayers'] / lmChanger['balances']\r\n" +
                "							['regions'][id]['totalPlayers']) * 100);\r\n" +
                "						var percentOfWorldLootMinutes = ((lmChanger['balances']['total']['lootMinutes'] == 0) ? 0 : ((lmChanger[\r\n" +
                "							'balances']['regions'][id]['lootMinutes'] / lmChanger['balances']['total']['lootMinutes']) * 100));\r\n" +
                "\r\n" +
                "						document.getElementById('regionLootMinuteBalance_' + id).innerHTML = htmlArrowLootMinutes + ' ' + number_format(\r\n" +
                "							lmChanger['balances']['regions'][id]['lootMinutes'], 0, decimalChar, commaChar);\r\n" +
                "						document.getElementById('regionLootMinutePercentOfWorld_' + id).innerHTML = number_format(\r\n" +
                "							percentOfWorldLootMinutes, 3, decimalChar, commaChar) + ' %';\r\n" +
                "						document.getElementById('regionPlayersOnline_' + id).innerHTML = htmlArrowPlayersOnline + ' ' + number_format(\r\n" +
                "								lmChanger['balances']['regions'][id]['playersOnline'], 0, decimalChar, commaChar) +\r\n" +
                "							'&nbsp;&nbsp;<span class=\"percent\">' + number_format(percentPlayersOnline, 1, decimalChar, commaChar) +\r\n" +
                "							' %</span>';\r\n" +
                "						document.getElementById('regionPlayersOffline_' + id).innerHTML = htmlArrowPlayersOffline + ' ' + number_format(\r\n" +
                "								lmChanger['balances']['regions'][id]['playersOffline'], 0, decimalChar, commaChar) +\r\n" +
                "							'&nbsp;&nbsp;<span class=\"percent\">' + number_format(percentPlayersOffline, 1, decimalChar, commaChar) +\r\n" +
                "							' %</span>';\r\n" +
                "						document.getElementById('regionPlayersShielded_' + id).innerHTML = htmlArrowShieldedPlayers + ' ' + number_format(\r\n" +
                "								lmChanger['balances']['regions'][id]['shieldedPlayers'], 0, decimalChar, commaChar) +\r\n" +
                "							'&nbsp;&nbsp;<span class=\"percent\">' + number_format(percentShieldedPlayers, 1, decimalChar, commaChar) +\r\n" +
                "							' %</span>';\r\n" +
                "						document.getElementById('regionPlayersAttackable_' + id).innerHTML = htmlArrowAttackablePlayers + ' ' +\r\n" +
                "							number_format(lmChanger['balances']['regions'][id]['attackablePlayers'], 0, decimalChar, commaChar) +\r\n" +
                "							'&nbsp;&nbsp;<span class=\"percent\">' + number_format(percentAttackablePlayers, 1, decimalChar, commaChar) +\r\n" +
                "							' %</span>';\r\n" +
                "					}\r\n" +
                "\r\n" +
                "					// loot index arrow handler\r\n" +
                "					if (lmChanger['trend'] > 0) {\r\n" +
                "						if (document.getElementById('lootIndexArrow3').style.visibility == 'hidden') {\r\n" +
                "							// bottom is blank\r\n" +
                "							document.getElementById('lootIndexArrow3').style.visibility = 'visible';\r\n" +
                "						} else if (document.getElementById('lootIndexArrow2').style.visibility == 'hidden') {\r\n" +
                "							// middle is blank\r\n" +
                "							document.getElementById('lootIndexArrow2').style.visibility = 'visible';\r\n" +
                "						} else if (document.getElementById('lootIndexArrow1').style.visibility == 'hidden') {\r\n" +
                "							// top is blank\r\n" +
                "							document.getElementById('lootIndexArrow1').style.visibility = 'visible';\r\n" +
                "						} else {\r\n" +
                "							// all are full\r\n" +
                "							document.getElementById('lootIndexArrow1').style.visibility = 'hidden';\r\n" +
                "							document.getElementById('lootIndexArrow2').style.visibility = 'hidden';\r\n" +
                "							document.getElementById('lootIndexArrow3').style.visibility = 'hidden';\r\n" +
                "						}\r\n" +
                "					} else {\r\n" +
                "						if (document.getElementById('lootIndexArrow1').style.visibility == 'hidden') {\r\n" +
                "							// top is blank\r\n" +
                "							document.getElementById('lootIndexArrow1').style.visibility = 'visible';\r\n" +
                "						} else if (document.getElementById('lootIndexArrow2').style.visibility == 'hidden') {\r\n" +
                "							// middle is blank\r\n" +
                "							document.getElementById('lootIndexArrow2').style.visibility = 'visible';\r\n" +
                "						} else if (document.getElementById('lootIndexArrow3').style.visibility == 'hidden') {\r\n" +
                "							// bottom is blank\r\n" +
                "							document.getElementById('lootIndexArrow3').style.visibility = 'visible';\r\n" +
                "						} else {\r\n" +
                "							// all are full\r\n" +
                "							document.getElementById('lootIndexArrow1').style.visibility = 'hidden';\r\n" +
                "							document.getElementById('lootIndexArrow2').style.visibility = 'hidden';\r\n" +
                "							document.getElementById('lootIndexArrow3').style.visibility = 'hidden';\r\n" +
                "						}\r\n" +
                "					}\r\n" +
                "\r\n" +
                "					// most active regions\r\n" +
                "					var regionData = lmChanger['regionData'];\r\n" +
                "					regionData.sort(function(a, b) {\r\n" +
                "						var aOnline = (a[STAT_PLAYERSONLINE] / a[STAT_TOTALPLAYERS]);\r\n" +
                "						var bOnline = (b[STAT_PLAYERSONLINE] / b[STAT_TOTALPLAYERS]);\r\n" +
                "						if (aOnline > bOnline) {\r\n" +
                "							return -1;\r\n" +
                "						} else if (aOnline < bOnline) {\r\n" +
                "							return 1;\r\n" +
                "						} else {\r\n" +
                "							return 0;\r\n" +
                "						}\r\n" +
                "					});\r\n" +
                "					var htmlA = htmlB = '';\r\n" +
                "					for (var i in regionData) {\r\n" +
                "						var html = ('<div>' + regionData[i][STAT_NAME].replace(' Time', '') + '</div>');\r\n" +
                "						if (i < 7) {\r\n" +
                "							htmlA += html;\r\n" +
                "						} else if (i < 14) {\r\n" +
                "							htmlB += html;\r\n" +
                "						} else {\r\n" +
                "							break;\r\n" +
                "						}\r\n" +
                "					}\r\n" +
                "					document.getElementById('mostActiveRegionsA').innerHTML = htmlA;\r\n" +
                "					document.getElementById('mostActiveRegionsB').innerHTML = htmlB;\r\n" +
                "				}\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function getPageHits(passThruExecuteWhenDone) {\r\n" +
                "				ajaxRequest('PAGEHITS.json', '', 'getPageHitsResponse', passThruExecuteWhenDone);\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function getPageHitsResponse(resp, passThruExecuteWhenDone) {\r\n" +
                "				resp = jsonDecode(resp);\r\n" +
                "				document.getElementById('totalHits').innerHTML = number_format(resp['totalHits'], 0, decimalChar, commaChar);\r\n" +
                "				document.getElementById('activeSessions').innerHTML = number_format(resp['activeSessions'], 0, decimalChar,\r\n" +
                "					commaChar);\r\n" +
                "				globalEval(passThruExecuteWhenDone);\r\n" +
                "\r\n" +
                "				var oldTimeNow = timeNow;\r\n" +
                "				timeNow = resp['minuteNow'];\r\n" +
                "				if (oldTimeNow != timeNow && graphData != null) {\r\n" +
                "					redrawGraph();\r\n" +
                "					getStats();\r\n" +
                "				}\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function updateGraphStatsMobile() {\r\n" +
                "				ajaxRequest('GRAPH_STATS.json', '', 'updateGraphStatsMobileResponse');\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function updateGraphStatsMobileResponse(resp) {\r\n" +
                "				if (resp) {\r\n" +
                "					resp = jsonDecode(resp);\r\n" +
                "\r\n" +
                "					lmChanger['trend'] = parseInt(resp['trend']);\r\n" +
                "\r\n" +
                "					// colors and strings\r\n" +
                "					document.body.style.backgroundColor = '#' + resp['mainColorShadeNow'];\r\n" +
                "					if (document.getElementById('lootSummaryBox').style.display != 'none') {\r\n" +
                "						// assemble the arrow structure\r\n" +
                "						if (firstLoad) {\r\n" +
                "							var htmlArrowLootIndex = ((resp['trend'] > 0) ? HTML_ARROWUPBIG : HTML_ARROWDOWNBIG);\r\n" +
                "							var htmlLootIndex = '<table width=\"1%\" cellspacing=\"0\" cellpadding=\"0\">' +\r\n" +
                "								'<tr>' +\r\n" +
                "								'<td id=\"lootIndexString\"></td>' +\r\n" +
                "								'<td style=\"padding-bottom:5px;\">' +\r\n" +
                "								'<table width=\"1%\" cellpadding=\"0\" cellspacing=\"0\">' +\r\n" +
                "								'<tr>' +\r\n" +
                "								'<td id=\"lootIndexArrow1\" class=\"lootIndexArrow\">' + htmlArrowLootIndex + '</td>' +\r\n" +
                "								'</tr>' +\r\n" +
                "								'<tr>' +\r\n" +
                "								'<td id=\"lootIndexArrow2\" class=\"lootIndexArrow\">' + htmlArrowLootIndex + '</td>' +\r\n" +
                "								'</tr>' +\r\n" +
                "								'<tr>' +\r\n" +
                "								'<td id=\"lootIndexArrow3\" class=\"lootIndexArrow\">' + htmlArrowLootIndex + '</td>' +\r\n" +
                "								'</tr>' +\r\n" +
                "								'</table>' +\r\n" +
                "								'</td>' +\r\n" +
                "								'</tr>' +\r\n" +
                "								'</table>';\r\n" +
                "							document.getElementById('lootIndexStringContainer').innerHTML = htmlLootIndex;\r\n" +
                "						}\r\n" +
                "						document.getElementById('lootIndexString').innerHTML = number_format(Number(resp['lootIndexString']), 1,\r\n" +
                "							decimalChar, commaChar);\r\n" +
                "\r\n" +
                "						// set loot index arrow directions\r\n" +
                "						document.getElementById('lootIndexArrow1').innerHTML = document.getElementById('lootIndexArrow2').innerHTML =\r\n" +
                "							document.getElementById('lootIndexArrow3').innerHTML = ((resp['trend'] > 0) ? HTML_ARROWUPBIG :\r\n" +
                "								HTML_ARROWDOWNBIG);\r\n" +
                "						document.getElementById('forecastMessage').innerHTML = resp['forecastMessage'];\r\n" +
                "						document.getElementById('lootIndexMessage').innerHTML = resp['forecastWordNow'];\r\n" +
                "						document.getElementById('lootIndexContainer').style.borderRight = '3px solid #' + resp['fgColor'];\r\n" +
                "						document.getElementById('lootIndexContainer').style.borderBottom = '3px solid #' + resp['fgColor'];\r\n" +
                "						document.getElementById('lootIndexContainer').style.borderLeft = '3px solid #' + resp['fgColor'];\r\n" +
                "						document.getElementById('lootIndexContainer').style.backgroundColor = '#' + resp['bgColor'];\r\n" +
                "						document.getElementById('lootIndexString').style.color = '#' + resp['fgColor'];\r\n" +
                "						document.getElementById('lootIndexArrow1').style.color = '#' + resp['fgColor'];\r\n" +
                "						document.getElementById('lootIndexArrow2').style.color = '#' + resp['fgColor'];\r\n" +
                "						document.getElementById('lootIndexArrow3').style.color = '#' + resp['fgColor'];\r\n" +
                "						document.getElementById('lootIndexMessage').style.color = '#' + resp['fgColor'];\r\n" +
                "						document.getElementById('forecastMessage').style.border = '3px solid #' + resp['fgColor'];\r\n" +
                "						document.getElementById('forecastMessage').style.backgroundColor = '#' + resp['bgColor'];\r\n" +
                "						document.getElementById('forecastMessage').style.color = '#' + resp['fgColor'];\r\n" +
                "						if (isLandscapeBigOrientation) {\r\n" +
                "							document.getElementById('forecastMessage').style.fontSize = '16px';\r\n" +
                "							document.getElementById('forecastMessage').style.lineHeight = '20px';\r\n" +
                "							document.getElementById('lootIndexContainer').style.borderBottomLeftRadius = '6px';\r\n" +
                "						}\r\n" +
                "					}\r\n" +
                "					document.getElementById('graphContainer').style.borderTop = '3px solid #' + resp['fgColor'];\r\n" +
                "					document.getElementById('graphContainer').style.borderBottom = '3px solid #' + resp['fgColor'];\r\n" +
                "\r\n" +
                "					// time\r\n" +
                "					var oldTimeNow = timeNow;\r\n" +
                "					timeNow = resp['epochMinuteNow'];\r\n" +
                "					if (oldTimeNow != timeNow && graphData != null) {\r\n" +
                "						redrawGraphMobile();\r\n" +
                "					}\r\n" +
                "					if (firstLoad) {\r\n" +
                "						firstLoad = false;\r\n" +
                "					}\r\n" +
                "				}\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function getStatsMobile() {\r\n" +
                "				ajaxRequest('STATS.json', '', 'getStatsMobileResponse');\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function getStatsMobileResponse(resp) {\r\n" +
                "				if (resp) {\r\n" +
                "					resp = jsonDecode(resp);\r\n" +
                "					// colors\r\n" +
                "					document.body.style.backgroundColor = '#' + resp['mainColorShadeNow'];\r\n" +
                "					var obj = ((isLandscapeBigOrientation || isPortraitBigOrientation) ? document.getElementById('worldwideStatsBig') :\r\n" +
                "						document.getElementById('worldwideStats'));\r\n" +
                "					obj.style.border = '3px solid #' + resp['fgColor'];\r\n" +
                "					obj.style.backgroundColor = '#' + resp['bgColor'];\r\n" +
                "					obj.style.color = '#' + resp['fgColor'];\r\n" +
                "					if (isPortraitOrientation) {\r\n" +
                "						document.getElementById('tableRegionStats').style.borderTop = '3px solid #' + resp['fgColor'];\r\n" +
                "					} else {\r\n" +
                "						document.getElementById('tableRegionStatsLandscape').style.borderTop = '3px solid #' + resp['fgColor'];\r\n" +
                "					}\r\n" +
                "\r\n" +
                "					// figure out arrows\r\n" +
                "					var htmlArrowPlayersOnline = ((resp['playersOnlineChange'] >= 0) ? HTML_ARROWUP : HTML_ARROWDOWN);\r\n" +
                "					var htmlArrowPlayersOffline = ((resp['playersOnlineChange'] < 0) ? HTML_ARROWUP : HTML_ARROWDOWN);\r\n" +
                "					var htmlArrowShieldedPlayers = ((resp['shieldedPlayersChange'] >= 0) ? HTML_ARROWUP : HTML_ARROWDOWN);\r\n" +
                "					var htmlArrowAttackablePlayers = ((resp['attackablePlayersChange'] >= 0) ? HTML_ARROWUP : HTML_ARROWDOWN);\r\n" +
                "\r\n" +
                "					// content\r\n" +
                "					var totalPlayersOffline = (resp['totalPlayers'] - resp['playersOnline']);\r\n" +
                "					if (isLandscapeBigOrientation || isPortraitBigOrientation) {\r\n" +
                "						document.getElementById('worldwideLootMinutesAvailableBig').innerHTML = number_format(resp['lootMinutes'], 0,\r\n" +
                "							decimalChar, commaChar);\r\n" +
                "						document.getElementById('worldwideLootMinuteChangeBig').innerHTML = ((resp['lootMinuteChange'] > 0) ? '+' : '') +\r\n" +
                "							number_format(resp['lootMinuteChange'], 0, decimalChar, commaChar);\r\n" +
                "						document.getElementById('worldwideLootMinuteChangeBig').style.color = ((resp['lootMinuteChange'] < 0) ? '#CC0000' :\r\n" +
                "							'#008800');\r\n" +
                "						document.getElementById('worldwidePlayersOnlineBig').innerHTML = htmlArrowPlayersOnline + ' ' + number_format(\r\n" +
                "							resp['playersOnline'], 0, decimalChar, commaChar);\r\n" +
                "						document.getElementById('worldwidePlayersOfflineBig').innerHTML = htmlArrowPlayersOffline + ' ' + number_format(\r\n" +
                "							totalPlayersOffline, 0, decimalChar, commaChar);\r\n" +
                "						document.getElementById('worldwideShieldedPlayersBig').innerHTML = htmlArrowShieldedPlayers + ' ' + number_format(\r\n" +
                "							resp['shieldedPlayers'], 0, decimalChar, commaChar);\r\n" +
                "						document.getElementById('worldwideAttackablePlayersBig').innerHTML = htmlArrowAttackablePlayers + ' ' +\r\n" +
                "							number_format(resp['attackablePlayers'], 0, decimalChar, commaChar);\r\n" +
                "					} else {\r\n" +
                "						document.getElementById('worldwideLootMinutesAvailable').innerHTML = number_format(resp['lootMinutes'], 0,\r\n" +
                "							decimalChar, commaChar);\r\n" +
                "						document.getElementById('worldwideLootMinuteChange').innerHTML = ((resp['lootMinuteChange'] > 0) ? '+' : '') +\r\n" +
                "							number_format(resp['lootMinuteChange'], 0, decimalChar, commaChar);\r\n" +
                "						document.getElementById('worldwideLootMinuteChange').style.color = ((resp['lootMinuteChange'] < 0) ? '#CC0000' :\r\n" +
                "							'#008800');\r\n" +
                "						document.getElementById('worldwidePlayersOnline').innerHTML = htmlArrowPlayersOnline + ' ' + number_format(resp[\r\n" +
                "							'playersOnline'], 0, decimalChar, commaChar);\r\n" +
                "						document.getElementById('worldwidePlayersOffline').innerHTML = htmlArrowPlayersOffline + ' ' + number_format(\r\n" +
                "							totalPlayersOffline, 0, decimalChar, commaChar);\r\n" +
                "						document.getElementById('worldwideShieldedPlayers').innerHTML = htmlArrowShieldedPlayers + ' ' + number_format(\r\n" +
                "							resp['shieldedPlayers'], 0, decimalChar, commaChar);\r\n" +
                "						document.getElementById('worldwideAttackablePlayers').innerHTML = htmlArrowAttackablePlayers + ' ' +\r\n" +
                "							number_format(resp['attackablePlayers'], 0, decimalChar, commaChar);\r\n" +
                "					}\r\n" +
                "\r\n" +
                "					// regions\r\n" +
                "					var factor = 60; // 60 = 1 second, adjust it according to the timer, 120 = every half second, etc...\r\n" +
                "					var html = '';\r\n" +
                "					for (var i in resp['regionStats']) {\r\n" +
                "						var region = resp['regionStats'][i];\r\n" +
                "\r\n" +
                "						// calculate stats\r\n" +
                "						if (isLandscapeBigOrientation || isPortraitBigOrientation || !isPortraitOrientation) {\r\n" +
                "							var playersOffline = (region[STAT_TOTALPLAYERS] - region[STAT_PLAYERSONLINE]);\r\n" +
                "							var htmlArrowLootMinutes = ((region[STAT_LOOTMINUTECHANGE] >= 0) ? HTML_ARROWUP : HTML_ARROWDOWN) + ' ';\r\n" +
                "							var htmlArrowPlayersOnline = ((region[STAT_PLAYERSONLINECHANGE] >= 0) ? HTML_ARROWUP : HTML_ARROWDOWN) + ' ';\r\n" +
                "							var htmlArrowPlayersOffline = ((region[STAT_PLAYERSONLINECHANGE] < 0) ? HTML_ARROWUP : HTML_ARROWDOWN) + ' ';\r\n" +
                "							var htmlArrowShieldedPlayers = ((region[STAT_SHIELDEDPLAYERSCHANGE] >= 0) ? HTML_ARROWUP : HTML_ARROWDOWN + ' ');\r\n" +
                "							var htmlArrowAttackablePlayers = ((region[STAT_ATTACKABLEPLAYERSCHANGE] >= 0) ? HTML_ARROWUP : HTML_ARROWDOWN) +\r\n" +
                "								' ';\r\n" +
                "							var percentPlayersOnline = ((region[STAT_PLAYERSONLINE] / region[STAT_TOTALPLAYERS]) * 100);\r\n" +
                "							var percentPlayersOffline = ((playersOffline / region[STAT_TOTALPLAYERS]) * 100);\r\n" +
                "							var percentShieldedPlayers = ((region[STAT_SHIELDEDPLAYERS] / region[STAT_TOTALPLAYERS]) * 100);\r\n" +
                "							var percentAttackablePlayers = ((region[STAT_ATTACKABLEPLAYERS] / region[STAT_TOTALPLAYERS]) * 100);\r\n" +
                "						} else {\r\n" +
                "							var htmlArrowLootMinutes = htmlArrowPlayersOnline = htmlArrowPlayersOffline = htmlArrowShieldedPlayers =\r\n" +
                "								htmlArrowAttackablePlayers = '';\r\n" +
                "						}\r\n" +
                "\r\n" +
                "						// prep the html\r\n" +
                "						html += '' +\r\n" +
                "							'<tr>' +\r\n" +
                "							'<td class=\"region\">' + region[STAT_NAME] + '</td>' +\r\n" +
                "							'<td class=\"time\">' + region[STAT_LOCALTIME] + '</td>' +\r\n" +
                "							'<td class=\"online\" style=\"color:#' + region[STAT_ROWCOLOR] + ';\" id=\"regionPlayersOnline_' + region[STAT_ID] +\r\n" +
                "							'\">' + htmlArrowPlayersOnline + number_format(region[STAT_PLAYERSONLINE], 0, decimalChar, commaChar) + ((\r\n" +
                "								isLandscapeBigOrientation || isPortraitBigOrientation || !isPortraitOrientation) ? (\r\n" +
                "								'&nbsp;&nbsp;<span class=\"percent\">' + number_format(percentPlayersOnline, 1, decimalChar, commaChar) +\r\n" +
                "								' %</span>') : '') + '</td>' +\r\n" +
                "							((isLandscapeBigOrientation || isPortraitBigOrientation || !isPortraitOrientation) ? (\r\n" +
                "								'<td class=\"offline\" style=\"color:#' + region[STAT_ROWCOLOR] + ';\" id=\"regionPlayersOffline_' + region[STAT_ID] +\r\n" +
                "								'\">' + htmlArrowPlayersOffline + ' ' + number_format(playersOffline, 0, decimalChar, commaChar) + ((\r\n" +
                "									isLandscapeBigOrientation || isPortraitBigOrientation) ? ('&nbsp;&nbsp;<span class=\"percent\">' +\r\n" +
                "									number_format(percentPlayersOffline, 1, decimalChar, commaChar) + ' %</span>') : '') + '</td>') : '') +\r\n" +
                "							'<td class=\"shielded\" style=\"color:#' + region[STAT_ROWCOLOR] + ';\" id=\"regionShieldedPlayers_' + region[STAT_ID] +\r\n" +
                "							'\">' + htmlArrowShieldedPlayers + number_format(region[STAT_SHIELDEDPLAYERS], 0, decimalChar, commaChar) + ((\r\n" +
                "								isLandscapeBigOrientation || isPortraitBigOrientation) ? ('&nbsp;&nbsp;<span class=\"percent\">' + number_format(\r\n" +
                "								percentShieldedPlayers, 1, decimalChar, commaChar) + ' %</span>') : '') + '</td>' +\r\n" +
                "							'<td class=\"attackable\" style=\"color:#' + region[STAT_ROWCOLOR] + ';\" id=\"regionAttackablePlayers_' + region[\r\n" +
                "								STAT_ID] + '\">' + htmlArrowAttackablePlayers + number_format(region[STAT_ATTACKABLEPLAYERS], 0, decimalChar,\r\n" +
                "								commaChar) + ((isLandscapeBigOrientation || isPortraitBigOrientation || !isPortraitOrientation) ? (\r\n" +
                "								'&nbsp;&nbsp;<span class=\"percent\">' + number_format(percentAttackablePlayers, 1, decimalChar, commaChar) +\r\n" +
                "								' %</span>') : '') + '</td>' +\r\n" +
                "							'<td class=\"lootMinutes lmBalance\" id=\"regionLootMinuteBalance_' + region[STAT_ID] + '\">' + htmlArrowLootMinutes +\r\n" +
                "							number_format(region[STAT_LOOTMINUTEBALANCE], 0, decimalChar, commaChar) + '</td>' +\r\n" +
                "							((isLandscapeBigOrientation || isPortraitBigOrientation || !isPortraitOrientation) ? (\r\n" +
                "								'<td class=\"lootMinutes lmBalancePercent\" id=\"regionLootMinutePercentOfWorld_' + region[STAT_ID] + '\">' +\r\n" +
                "								number_format(((region[STAT_LOOTMINUTEBALANCE] / resp['lootMinutes']) * 100), ((isLandscapeBigOrientation) ? 3 :\r\n" +
                "									2), decimalChar, commaChar) + ' %</td>') : '') + '</tr>' +\r\n" +
                "							'';\r\n" +
                "\r\n" +
                "						// lm changer\r\n" +
                "						lmChanger['balances']['regions'][region[STAT_ID]] = [];\r\n" +
                "						lmChanger['balances']['regions'][region[STAT_ID]]['lootMinutes'] = parseInt(region[STAT_LOOTMINUTEBALANCE]);\r\n" +
                "						lmChanger['balances']['regions'][region[STAT_ID]]['totalPlayers'] = parseInt(region[STAT_TOTALPLAYERS]);\r\n" +
                "						lmChanger['balances']['regions'][region[STAT_ID]]['playersOnline'] = parseInt(region[STAT_PLAYERSONLINE]);\r\n" +
                "						lmChanger['balances']['regions'][region[STAT_ID]]['playersOffline'] = parseInt(playersOffline);\r\n" +
                "						lmChanger['balances']['regions'][region[STAT_ID]]['shieldedPlayers'] = parseInt(region[STAT_SHIELDEDPLAYERS]);\r\n" +
                "						lmChanger['balances']['regions'][region[STAT_ID]]['attackablePlayers'] = parseInt(region[STAT_ATTACKABLEPLAYERS]);\r\n" +
                "						lmChanger['rate']['regions'][region[STAT_ID]] = [];\r\n" +
                "						lmChanger['rate']['regions'][region[STAT_ID]]['lootMinutes'] = (region[STAT_LOOTMINUTECHANGE] / factor);\r\n" +
                "						lmChanger['rate']['regions'][region[STAT_ID]]['playersOnline'] = (region[STAT_PLAYERSONLINECHANGE] / factor);\r\n" +
                "						lmChanger['rate']['regions'][region[STAT_ID]]['playersOffline'] = ((region[STAT_PLAYERSONLINECHANGE] * -1) /\r\n" +
                "							factor);\r\n" +
                "						lmChanger['rate']['regions'][region[STAT_ID]]['shieldedPlayers'] = (region[STAT_SHIELDEDPLAYERSCHANGE] / factor);\r\n" +
                "						lmChanger['rate']['regions'][region[STAT_ID]]['attackablePlayers'] = (region[STAT_ATTACKABLEPLAYERSCHANGE] /\r\n" +
                "							factor);\r\n" +
                "					}\r\n" +
                "					if (isLandscapeBigOrientation) {\r\n" +
                "						document.getElementById('regionStatsLandscapeBig').innerHTML = html;\r\n" +
                "					} else if (isPortraitBigOrientation || !isPortraitOrientation) {\r\n" +
                "						document.getElementById('regionStatsLandscape').innerHTML = html;\r\n" +
                "					} else {\r\n" +
                "						document.getElementById('regionStats').innerHTML = html;\r\n" +
                "					}\r\n" +
                "\r\n" +
                "					// main stats lmChanger\r\n" +
                "					lmChanger['balances']['total']['lootMinutes'] = parseInt(resp['lootMinutes']);\r\n" +
                "					lmChanger['balances']['total']['playersOnline'] = parseInt(resp['playersOnline']);\r\n" +
                "					lmChanger['balances']['total']['playersOffline'] = parseInt(totalPlayersOffline);\r\n" +
                "					lmChanger['balances']['total']['shieldedPlayers'] = parseInt(resp['shieldedPlayers']);\r\n" +
                "					lmChanger['balances']['total']['attackablePlayers'] = parseInt(resp['attackablePlayers']);\r\n" +
                "					lmChanger['rate']['total']['lootMinutes'] = parseInt(resp['lootMinuteChange'] / factor);\r\n" +
                "					lmChanger['rate']['total']['playersOnline'] = parseInt(resp['playersOnlineChange'] / factor);\r\n" +
                "					lmChanger['rate']['total']['playersOffline'] = parseInt((resp['playersOnlineChange'] * -1) / factor);\r\n" +
                "					lmChanger['rate']['total']['shieldedPlayers'] = parseInt(resp['shieldedPlayersChange'] / factor);\r\n" +
                "					lmChanger['rate']['total']['attackablePlayers'] = parseInt(resp['attackablePlayersChange'] / factor);\r\n" +
                "				}\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function updateLootMinuteBalancesMobile() {\r\n" +
                "				if (lmChanger['balances']['total']['lootMinutes'] != false) {\r\n" +
                "					// data is loaded, apply change and display\r\n" +
                "					lmChanger['balances']['total']['lootMinutes'] += lmChanger['rate']['total']['lootMinutes'];\r\n" +
                "					lmChanger['balances']['total']['playersOnline'] += lmChanger['rate']['total']['playersOnline'];\r\n" +
                "					lmChanger['balances']['total']['playersOffline'] += lmChanger['rate']['total']['playersOffline'];\r\n" +
                "					lmChanger['balances']['total']['shieldedPlayers'] += lmChanger['rate']['total']['shieldedPlayers'];\r\n" +
                "					lmChanger['balances']['total']['attackablePlayers'] += lmChanger['rate']['total']['attackablePlayers'];\r\n" +
                "\r\n" +
                "					var htmlArrowCurrentLootPlayersOnline = ((lmChanger['rate']['total']['playersOnline'] >= 0) ? HTML_ARROWUP :\r\n" +
                "						HTML_ARROWDOWN);\r\n" +
                "					var htmlArrowCurrentLootPlayersOffline = ((lmChanger['rate']['total']['playersOffline'] >= 0) ? HTML_ARROWUP :\r\n" +
                "						HTML_ARROWDOWN);\r\n" +
                "					var htmlArrowCurrentLootShieldedPlayers = ((lmChanger['rate']['total']['shieldedPlayers'] >= 0) ? HTML_ARROWUP :\r\n" +
                "						HTML_ARROWDOWN);\r\n" +
                "					var htmlArrowCurrentLootAttackablePlayers = ((lmChanger['rate']['total']['attackablePlayers'] >= 0) ? HTML_ARROWUP :\r\n" +
                "						HTML_ARROWDOWN);\r\n" +
                "\r\n" +
                "					// update main stats\r\n" +
                "					if (isLandscapeBigOrientation || isPortraitBigOrientation) {\r\n" +
                "						document.getElementById('worldwideLootMinutesAvailableBig').innerHTML = number_format(lmChanger['balances'][\r\n" +
                "							'total'\r\n" +
                "						]['lootMinutes'], 0, decimalChar, commaChar);\r\n" +
                "						document.getElementById('worldwidePlayersOnlineBig').innerHTML = htmlArrowCurrentLootPlayersOnline + ' ' +\r\n" +
                "							number_format(lmChanger['balances']['total']['playersOnline'], 0, decimalChar, commaChar);\r\n" +
                "						document.getElementById('worldwidePlayersOfflineBig').innerHTML = htmlArrowCurrentLootPlayersOffline + ' ' +\r\n" +
                "							number_format(lmChanger['balances']['total']['playersOffline'], 0, decimalChar, commaChar);\r\n" +
                "						document.getElementById('worldwideShieldedPlayersBig').innerHTML = htmlArrowCurrentLootShieldedPlayers + ' ' +\r\n" +
                "							number_format(lmChanger['balances']['total']['shieldedPlayers'], 0, decimalChar, commaChar);\r\n" +
                "						document.getElementById('worldwideAttackablePlayersBig').innerHTML = htmlArrowCurrentLootAttackablePlayers + ' ' +\r\n" +
                "							number_format(lmChanger['balances']['total']['attackablePlayers'], 0, decimalChar, commaChar);\r\n" +
                "					} else {\r\n" +
                "						document.getElementById('worldwideLootMinutesAvailable').innerHTML = number_format(lmChanger['balances']['total']\r\n" +
                "							['lootMinutes'], 0, decimalChar, commaChar);\r\n" +
                "						document.getElementById('worldwidePlayersOnline').innerHTML = htmlArrowCurrentLootPlayersOnline + ' ' +\r\n" +
                "							number_format(lmChanger['balances']['total']['playersOnline'], 0, decimalChar, commaChar);\r\n" +
                "						document.getElementById('worldwidePlayersOffline').innerHTML = htmlArrowCurrentLootPlayersOffline + ' ' +\r\n" +
                "							number_format(lmChanger['balances']['total']['playersOffline'], 0, decimalChar, commaChar);\r\n" +
                "						document.getElementById('worldwideShieldedPlayers').innerHTML = htmlArrowCurrentLootShieldedPlayers + ' ' +\r\n" +
                "							number_format(lmChanger['balances']['total']['shieldedPlayers'], 0, decimalChar, commaChar);\r\n" +
                "						document.getElementById('worldwideAttackablePlayers').innerHTML = htmlArrowCurrentLootAttackablePlayers + ' ' +\r\n" +
                "							number_format(lmChanger['balances']['total']['attackablePlayers'], 0, decimalChar, commaChar);\r\n" +
                "					}\r\n" +
                "\r\n" +
                "					// update region balancs\r\n" +
                "					for (var id in lmChanger['balances']['regions']) {\r\n" +
                "						lmChanger['balances']['regions'][id]['lootMinutes'] += lmChanger['rate']['regions'][id]['lootMinutes'];\r\n" +
                "						lmChanger['balances']['regions'][id]['playersOnline'] += lmChanger['rate']['regions'][id]['playersOnline'];\r\n" +
                "						lmChanger['balances']['regions'][id]['playersOffline'] += lmChanger['rate']['regions'][id]['playersOffline'];\r\n" +
                "						lmChanger['balances']['regions'][id]['shieldedPlayers'] += lmChanger['rate']['regions'][id]['shieldedPlayers'];\r\n" +
                "						lmChanger['balances']['regions'][id]['attackablePlayers'] += lmChanger['rate']['regions'][id]['attackablePlayers'];\r\n" +
                "\r\n" +
                "						if (isLandscapeBigOrientation || isPortraitBigOrientation || !isPortraitOrientation) {\r\n" +
                "							var htmlArrowLootMinutes = ((lmChanger['rate']['regions'][id]['lootMinutes'] >= 0) ? HTML_ARROWUP :\r\n" +
                "								HTML_ARROWDOWN) + ' ';\r\n" +
                "							var htmlArrowPlayersOnline = ((lmChanger['rate']['regions'][id]['playersOnline'] >= 0) ? HTML_ARROWUP :\r\n" +
                "								HTML_ARROWDOWN) + ' ';\r\n" +
                "							var htmlArrowPlayersOffline = ((lmChanger['rate']['regions'][id]['playersOnline'] < 0) ? HTML_ARROWUP :\r\n" +
                "								HTML_ARROWDOWN) + ' ';\r\n" +
                "							var htmlArrowShieldedPlayers = ((lmChanger['rate']['regions'][id]['shieldedPlayers'] >= 0) ? HTML_ARROWUP :\r\n" +
                "								HTML_ARROWDOWN) + ' ';\r\n" +
                "							var htmlArrowAttackablePlayers = ((lmChanger['rate']['regions'][id]['attackablePlayers'] >= 0) ? HTML_ARROWUP :\r\n" +
                "								HTML_ARROWDOWN) + ' ';\r\n" +
                "\r\n" +
                "							var percentPlayersOnline = ((lmChanger['balances']['regions'][id]['playersOnline'] / lmChanger['balances'][\r\n" +
                "								'regions'\r\n" +
                "							][id]['totalPlayers']) * 100);\r\n" +
                "							var percentPlayersOffline = ((lmChanger['balances']['regions'][id]['playersOffline'] / lmChanger['balances'][\r\n" +
                "								'regions'\r\n" +
                "							][id]['totalPlayers']) * 100);\r\n" +
                "							var percentShieldedPlayers = ((lmChanger['balances']['regions'][id]['shieldedPlayers'] / lmChanger['balances'][\r\n" +
                "								'regions'\r\n" +
                "							][id]['totalPlayers']) * 100);\r\n" +
                "							var percentAttackablePlayers = ((lmChanger['balances']['regions'][id]['attackablePlayers'] / lmChanger[\r\n" +
                "								'balances']['regions'][id]['totalPlayers']) * 100);\r\n" +
                "							var percentOfWorldLootMinutes = ((lmChanger['balances']['total']['lootMinutes'] == 0) ? 0 : ((lmChanger[\r\n" +
                "								'balances']['regions'][id]['lootMinutes'] / lmChanger['balances']['total']['lootMinutes']) * 100));\r\n" +
                "						} else {\r\n" +
                "							var htmlArrowLootMinutes = htmlArrowPlayersOnline = htmlArrowPlayersOffline = htmlArrowShieldedPlayers =\r\n" +
                "								htmlArrowAttackablePlayers = '';\r\n" +
                "						}\r\n" +
                "\r\n" +
                "						document.getElementById('regionPlayersOnline_' + id).innerHTML = htmlArrowPlayersOnline + number_format(lmChanger[\r\n" +
                "							'balances']['regions'][id]['playersOnline'], 0, decimalChar, commaChar) + ((isLandscapeBigOrientation ||\r\n" +
                "							isPortraitBigOrientation || !isPortraitOrientation) ? ('&nbsp;&nbsp;<span class=\"percent\">' + number_format(\r\n" +
                "							percentPlayersOnline, 1, decimalChar, commaChar) + ' %</span>') : '');\r\n" +
                "						if (document.getElementById('regionLootMinutePercentOfWorld_' + id) != undefined) {\r\n" +
                "							document.getElementById('regionLootMinutePercentOfWorld_' + id).innerHTML = number_format(\r\n" +
                "								percentOfWorldLootMinutes, ((isLandscapeBigOrientation) ? 3 : 2), decimalChar, commaChar) + ' %';\r\n" +
                "						}\r\n" +
                "						if (document.getElementById('regionPlayersOffline_' + id) != undefined) {\r\n" +
                "							document.getElementById('regionPlayersOffline_' + id).innerHTML = htmlArrowPlayersOffline + number_format(\r\n" +
                "								lmChanger['balances']['regions'][id]['playersOffline'], 0, decimalChar, commaChar) + ((\r\n" +
                "								isLandscapeBigOrientation || isPortraitBigOrientation) ? ('&nbsp;&nbsp;<span class=\"percent\">' +\r\n" +
                "								number_format(percentPlayersOffline, 1, decimalChar, commaChar) + ' %</span>') : '');\r\n" +
                "						}\r\n" +
                "						document.getElementById('regionShieldedPlayers_' + id).innerHTML = htmlArrowShieldedPlayers + number_format(\r\n" +
                "							lmChanger['balances']['regions'][id]['shieldedPlayers'], 0, decimalChar, commaChar) + ((\r\n" +
                "							isLandscapeBigOrientation || isPortraitBigOrientation) ? ('&nbsp;&nbsp;<span class=\"percent\">' + number_format(\r\n" +
                "							percentShieldedPlayers, 1, decimalChar, commaChar) + ' %</span>') : '');\r\n" +
                "						document.getElementById('regionAttackablePlayers_' + id).innerHTML = htmlArrowAttackablePlayers + number_format(\r\n" +
                "							lmChanger['balances']['regions'][id]['attackablePlayers'], 0, decimalChar, commaChar) + ((\r\n" +
                "							isLandscapeBigOrientation || isPortraitBigOrientation || !isPortraitOrientation) ? (\r\n" +
                "							'&nbsp;&nbsp;<span class=\"percent\">' + number_format(percentAttackablePlayers, 1, decimalChar, commaChar) +\r\n" +
                "							' %</span>') : '');\r\n" +
                "						document.getElementById('regionLootMinuteBalance_' + id).innerHTML = htmlArrowLootMinutes + number_format(\r\n" +
                "							lmChanger['balances']['regions'][id]['lootMinutes'], 0, decimalChar, commaChar);\r\n" +
                "					}\r\n" +
                "				}\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function mobileLootIndexArrowHandler() {\r\n" +
                "				// loot index arrow handler\r\n" +
                "				if (lmChanger['trend'] != false) {\r\n" +
                "					if (lmChanger['trend'] > 0) {\r\n" +
                "						if (document.getElementById('lootIndexArrow3').style.visibility == 'hidden') {\r\n" +
                "							// bottom is blank\r\n" +
                "							document.getElementById('lootIndexArrow3').style.visibility = 'visible';\r\n" +
                "						} else if (document.getElementById('lootIndexArrow2').style.visibility == 'hidden') {\r\n" +
                "							// middle is blank\r\n" +
                "							document.getElementById('lootIndexArrow2').style.visibility = 'visible';\r\n" +
                "						} else if (document.getElementById('lootIndexArrow1').style.visibility == 'hidden') {\r\n" +
                "							// top is blank\r\n" +
                "							document.getElementById('lootIndexArrow1').style.visibility = 'visible';\r\n" +
                "						} else {\r\n" +
                "							// all are full\r\n" +
                "							document.getElementById('lootIndexArrow1').style.visibility = 'hidden';\r\n" +
                "							document.getElementById('lootIndexArrow2').style.visibility = 'hidden';\r\n" +
                "							document.getElementById('lootIndexArrow3').style.visibility = 'hidden';\r\n" +
                "						}\r\n" +
                "					} else {\r\n" +
                "						if (document.getElementById('lootIndexArrow1').style.visibility == 'hidden') {\r\n" +
                "							// top is blank\r\n" +
                "							document.getElementById('lootIndexArrow1').style.visibility = 'visible';\r\n" +
                "						} else if (document.getElementById('lootIndexArrow2').style.visibility == 'hidden') {\r\n" +
                "							// middle is blank\r\n" +
                "							document.getElementById('lootIndexArrow2').style.visibility = 'visible';\r\n" +
                "						} else if (document.getElementById('lootIndexArrow3').style.visibility == 'hidden') {\r\n" +
                "							// bottom is blank\r\n" +
                "							document.getElementById('lootIndexArrow3').style.visibility = 'visible';\r\n" +
                "						} else {\r\n" +
                "							// all are full\r\n" +
                "							document.getElementById('lootIndexArrow1').style.visibility = 'hidden';\r\n" +
                "							document.getElementById('lootIndexArrow2').style.visibility = 'hidden';\r\n" +
                "							document.getElementById('lootIndexArrow3').style.visibility = 'hidden';\r\n" +
                "						}\r\n" +
                "					}\r\n" +
                "				}\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function mobileSettingsSave() {\r\n" +
                "				mobileSettingsToggler();\r\n" +
                "\r\n" +
                "				var d = new Date();\r\n" +
                "				var tzOffset = -(d.getTimezoneOffset() / 60);\r\n" +
                "\r\n" +
                "				// build aryVars\r\n" +
                "				var aryVars = [];\r\n" +
                "				aryVars['timezoneOffset'] = tzOffset;\r\n" +
                "				aryVars['enablePush'] = ((document.getElementById('enablePush').checked) ? 1 : 0);\r\n" +
                "				aryVars['minutesInAdvance'] = document.getElementById('minutesInAdvance').value;\r\n" +
                "				aryVars['lootIndexWarning'] = document.getElementById('lootIndexWarning').value;\r\n" +
                "				aryVars['pushExceptWhen'] = ((document.getElementById('pushExceptWhen').checked) ? 1 : 0);\r\n" +
                "				aryVars['pushExceptStart'] = document.getElementById('pushExceptStart').value;\r\n" +
                "				aryVars['pushExceptEnd'] = document.getElementById('pushExceptEnd').value;\r\n" +
                "\r\n" +
                "				ajaxRequest('settings.php', 'action=SAVE&token=' + encodeURIComponent(document.getElementById('token').value) +\r\n" +
                "					'&aryVars=' + jsonEncode(aryVars), 'mobileSettingsSaveResponse');\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function mobileSettingsSaveResponse(resp) {\r\n" +
                "				if (resp != '1') {\r\n" +
                "					alert('Sorry, but an error has occurred and your settings may not have been saved.');\r\n" +
                "				}\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function mobileSettingsToggler() {\r\n" +
                "				var enablePush = document.getElementById('enablePush').checked;\r\n" +
                "				var pushExceptWhen = document.getElementById('pushExceptWhen').checked;\r\n" +
                "\r\n" +
                "				document.getElementById('minutesInAdvance').disabled = (!enablePush);\r\n" +
                "				document.getElementById('lootIndexWarning').disabled = (!enablePush);\r\n" +
                "				document.getElementById('pushExceptWhen').disabled = (!enablePush);\r\n" +
                "				document.getElementById('pushExceptStart').disabled = (!enablePush || !pushExceptWhen);\r\n" +
                "				document.getElementById('pushExceptEnd').disabled = (!enablePush || !pushExceptWhen);\r\n" +
                "			}\r\n" +
                "\r\n" +
                "			function mobileShowFullAd() {\r\n" +
                "				var o = document.getElementById('mobileFullAd');\r\n" +
                "				if (o != undefined && o.style.display == 'none') {\r\n" +
                "					o.style.display = 'block';\r\n" +
                "					// close button\r\n" +
                "					setTimeout('document.getElementById(\\'closeImageBtn\\').style.visibility = \\'visible\\';', 3000);\r\n" +
                "				}\r\n" +
                "			}\r\n" +
                "		</script>\r\n" +
                "");
        htmlAll = sb.toString();

    }
}

