package com.cykj.base.common.filter;

public class HTMLFilterStrategy {
	private static HTMLFilter baesHtmlFilter;
	static {
		baesHtmlFilter = new HTMLFilter();
	}

	public static HTMLFilter getBaesHtmlFilter() {
		return baesHtmlFilter;
	}

}
