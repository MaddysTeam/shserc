package com.dianda.shsedu.common;

import java.util.HashMap;
import java.util.Map;

public final class ShseduConstant {

	public static final class State {
		public final static int AUDITSUCCESS = 1024;
		public final static int AUDITFALURE = 1025;
		public final static int NEEDAUDIT = 1023;
		public final static int DISABLED = 1026;
	}

	public static final class NewsType{
		public final static int DEAF=1019;
		public final static int BLIND=1020;
		public final static int ASSISTANCE=1021;
		public final static int OTHERTYPES=1027;
	}


	public static final class OrderPhraseMapping{

		public  final static Map<String,String> getOrderPhraseMapping(){
			Map<String,String> orderPhraseMapping=new HashMap<>(  );
//			// resource
//			orderPhraseMapping.put ( "favorite" ,"favorite_count");
//			orderPhraseMapping.put ( "view" ,"view_count");
//			orderPhraseMapping.put ( "download" ,"download_count");
//			orderPhraseMapping.put ( "comment" ,"comment_count");
//
//			// user
//			orderPhraseMapping.put("activity","login_count");

			return orderPhraseMapping;
		}

	}

	public static final class Route{
		public final static int ROOT = 1;
	}

}
