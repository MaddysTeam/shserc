package com.dianda.shsedu.common;

import java.util.HashMap;
import java.util.Map;

public final class ShseduConstant {

	public static final class State {
//		public final static int DELETED = 171;
//		public final static int AUDITSUCCESS = 129;
//		public final static int AUDITFALURE = 130;
//		public final static int ENABLED = 13;
//		public final static int DISABLED = 14;
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
