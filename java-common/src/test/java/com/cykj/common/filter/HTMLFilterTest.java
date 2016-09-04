package com.cykj.common.filter;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cykj.base.common.filter.HTMLFilter;
import com.cykj.base.common.filter.HTMLFilterStrategy;

/**
 *
 */
public class HTMLFilterTest {
	protected HTMLFilter vFilter;

	@Before
	public void setUp() {
		vFilter = HTMLFilterStrategy.getBaesHtmlFilter();
	}

	@After
	public void tearDown() {
		vFilter = null;
	}

	@Test
	public void testBasics() {
		assertEquals(vFilter.filter(""), "");
		assertEquals(vFilter.filter("hello"), "hello");
	}

	@Test
	public void testBalancingTags() {
		assertEquals(vFilter.filter("<b>hello"), "<b>hello</b>");
		assertEquals(vFilter.filter("<b>hello"), "<b>hello</b>");
		assertEquals(vFilter.filter("hello<b>"), "hello");
		assertEquals(vFilter.filter("hello</b>"), "hello");
		assertEquals(vFilter.filter("hello<b/>"), "hello");
		assertEquals(vFilter.filter("<b><b><b>hello"), "<b><b><b>hello</b></b></b>");
		assertEquals(vFilter.filter("</b><b>"), "");
	}

	@Test
	public void testEndSlashes() {
		assertEquals(vFilter.filter("<img>"), "<img />");
		assertEquals(vFilter.filter("<img/>"), "<img />");
		assertEquals(vFilter.filter("<b/></b>"), "");
	}

	@Test
	public void testBalancingAngleBrackets() {
		if (vFilter.isAlwaysMakeTags()) {
			assertEquals(vFilter.filter("<img src=\"foo\""), "<img src=\"foo\" />");
			assertEquals(vFilter.filter("i>"), "");
			assertEquals(vFilter.filter("<img src=\"foo\"/"), "<img src=\"foo\" />");
			assertEquals(vFilter.filter(">"), "");
			assertEquals(vFilter.filter("foo<b"), "foo");
			assertEquals(vFilter.filter("b>foo"), "<b>foo</b>");
			assertEquals(vFilter.filter("><b"), "");
			assertEquals(vFilter.filter("b><"), "");
			assertEquals(vFilter.filter("><b>"), "");
		} else {
			assertEquals(vFilter.filter("<img src=\"foo\""), "&lt;img src=\"foo\"");
			assertEquals(vFilter.filter("b>"), "b&gt;");
			assertEquals(vFilter.filter("<img src=\"foo\"/"), "&lt;img src=\"foo\"/");
			assertEquals(vFilter.filter(">"), "&gt;");
			assertEquals(vFilter.filter("foo<b"), "foo&lt;b");
			assertEquals(vFilter.filter("b>foo"), "b&gt;foo");
			assertEquals(vFilter.filter("><b"), "&gt;&lt;b");
			assertEquals(vFilter.filter("b><"), "b&gt;&lt;");
			assertEquals(vFilter.filter("><b>"), "&gt;");
		}
	}

	@Test
	public void testAttributes() {
		assertEquals(vFilter.filter("<img src=foo>"), "<img src=\"foo\" />");
		assertEquals(vFilter.filter("<img asrc=foo>"), "<img />");
		assertEquals(vFilter.filter("<img src=test test>"), "<img src=\"test\" />");
	}

	@Test
	public void testDisallowScriptTags() {
		assertEquals(vFilter.filter("<script>"), "");
		String result = vFilter.isAlwaysMakeTags() ? "" : "&lt;script";
		assertEquals(vFilter.filter("<script"), result);
		assertEquals(vFilter.filter("<script/>"), "");
		assertEquals(vFilter.filter("</script>"), "");
		assertEquals(vFilter.filter("<script woo=yay>"), "");
		assertEquals(vFilter.filter("<script woo=\"yay\">"), "");
		assertEquals(vFilter.filter("<script woo=\"yay>"), "");
		assertEquals(vFilter.filter("<script woo=\"yay<b>"), "");
		assertEquals(vFilter.filter("<script<script>>"), "");
		assertEquals(vFilter.filter("<<script>script<script>>"), "script");
		assertEquals(vFilter.filter("<<script><script>>"), "");
		assertEquals(vFilter.filter("<<script>script>>"), "");
		assertEquals(vFilter.filter("<<script<script>>"), "");
	}

	@Test
	public void testProtocols() {
		assertEquals(vFilter.filter("<a href=\"http://foo\">bar</a>"), "<a href=\"http://foo\">bar</a>");
		assertEquals(vFilter.filter("<a href=\"https://foo\">bar</a>"), "<a href=\"https://foo\">bar</a>");
		// we don't allow ftp. t("<a href=\"ftp://foo\">bar</a>", "<a
		// href=\"ftp://foo\">bar</a>");
		assertEquals(vFilter.filter("<a href=\"mailto:foo\">bar</a>"), "<a href=\"mailto:foo\">bar</a>");
		assertEquals(vFilter.filter("<a href=\"javascript:foo\">bar</a>"), "<a href=\"#foo\">bar</a>");
		assertEquals(vFilter.filter("<a href=\"java script:foo\">bar</a>"), "<a href=\"#foo\">bar</a>");
		assertEquals(vFilter.filter("<a href=\"java\tscript:foo\">bar</a>"), "<a href=\"#foo\">bar</a>");
		assertEquals(vFilter.filter("<a href=\"java\nscript:foo\">bar</a>"), "<a href=\"#foo\">bar</a>");
		assertEquals(vFilter.filter("<a href=\"java" + HTMLFilter.chr(1) + "script:foo\">bar</a>"),
				"<a href=\"#foo\">bar</a>");
		assertEquals(vFilter.filter("<a href=\"jscript:foo\">bar</a>"), "<a href=\"#foo\">bar</a>");
		assertEquals(vFilter.filter("<a href=\"vbscript:foo\">bar</a>"), "<a href=\"#foo\">bar</a>");
		assertEquals(vFilter.filter("<a href=\"view-source:foo\">bar</a>"), "<a href=\"#foo\">bar</a>");
	}

	@Test
	public void testSelfClosingTags() {
		assertEquals(vFilter.filter("<img src=\"a\">"), "<img src=\"a\" />");
		assertEquals(vFilter.filter("<img src=\"a\">foo</img>"), "<img src=\"a\" />foo");
		assertEquals(vFilter.filter("</img>"), "");
	}

	@Test
	public void testComments() {
		if (vFilter.isStripComments()) {
			assertEquals(vFilter.filter("<!-- a<b --->"), "");
		} else {
			assertEquals(vFilter.filter("<!-- a<b --->"), "<!-- a&lt;b --->");
		}
	}

	@Test
	public void testEntities() {
		assertEquals(vFilter.filter("&nbsp;"), "&amp;nbsp;");
		assertEquals(vFilter.filter("&amp;"), "&amp;");
		assertEquals(vFilter.filter("test &nbsp; test"), "test &amp;nbsp; test");
		assertEquals(vFilter.filter("test &amp; test"), "test &amp; test");
		assertEquals(vFilter.filter("&nbsp;&nbsp;"), "&amp;nbsp;&amp;nbsp;");
		assertEquals(vFilter.filter("&amp;&amp;"), "&amp;&amp;");
		assertEquals(vFilter.filter("test &nbsp;&nbsp; test"), "test &amp;nbsp;&amp;nbsp; test");
		assertEquals(vFilter.filter("test &amp;&amp; test"), "test &amp;&amp; test");
		assertEquals(vFilter.filter("&amp;&nbsp;"), "&amp;&amp;nbsp;");
		assertEquals(vFilter.filter("test &amp;&nbsp; test"), "test &amp;&amp;nbsp; test");
	}

	@Test
	public void testDollar() {
		String text = "modeling & US MSRP $81.99, (Not Included)";
		String result = "modeling &amp; US MSRP $81.99, (Not Included)";

		assertEquals(vFilter.filter(text), result);
	}

	@Test
	public void testBr() {

		final Map<String, List<String>> allowed = new HashMap<String, List<String>>();
		for (String allow : "span;br;b;strong;em;u;i".split("\\s*;\\s*")) {
			if (0 < allow.indexOf(':')) {
				final String name = allow.split(":")[0];
				final String[] attributes = allow.split(":")[0].split("\\s*,\\s*");
				allowed.put(name, Arrays.asList(attributes));
			} else {
				allowed.put(allow, Collections.<String> emptyList());
			}
		}
		Map<String, Object> config = new HashMap<>();
		config.put("vSelfClosingTags", "img,br".split("\\s*,\\s*"));
		config.put("vNeedClosingTags", "a,b,strong,i,em".split("\\s*,\\s*"));
		config.put("vDisallowed", "".split("\\s*,\\s*"));
		config.put("vAllowedProtocols", "src,href".split("\\s*,\\s*"));
		config.put("vProtocolAtts", "".split("\\s*,\\s*"));
		config.put("vRemoveBlanks", "a,b,strong,i,em".split("\\s*,\\s*"));
		config.put("vAllowedEntities",
				"mdash,euro,quot,amp,lt,gt,nbsp,iexcl,cent,pound,curren,yen,brvbar,sect,uml,copy,ordf,laquo,not,shy,reg,macr,deg,plusmn,sup2,sup3,acute,micro,para,middot,cedil,sup1,ordm,raquo,frac14,frac12,frac34,iquest,Agrave,Aacute,Acirc,Atilde,Auml,Aring,AElig,Ccedil,Egrave,Eacute,Ecirc,Euml,Igrave,Iacute,Icirc,Iuml,ETH,Ntilde,Ograve,Oacute,Ocirc,Otilde,Ouml,times,Oslash,Ugrave,Uacute,Ucirc,Uuml,Yacute,THORN,szlig,agrave,aacute,acirc,atilde,auml,aring,aelig,ccedil,egrave,eacute,ecirc,euml,igrave,iacute,icirc,iuml,eth,ntilde,ograve,oacute,ocirc,otilde,ouml,divide,oslash,ugrave,uacute,ucirc,uuml,yacute,thorn,yuml,#34,#38,#60,#62,#160,#161,#162,#163,#164,#165,#166,#167,#168,#169,#170,#171,#172,#173,#174,#175,#176,#177,#178,#179,#180,#181,#182,#183,#184,#185,#186,#187,#188,#189,#190,#191,#192,#193,#194,#195,#196,#197,#198,#199,#200,#201,#202,#203,#204,#205,#206,#207,#208,#209,#210,#211,#212,#213,#214,#215,#216,#217,#218,#219,#220,#221,#222,#223,#224,#225,#226,#227,#228,#229,#230,#231,#232,#233,#234,#235,#236,#237,#238,#239,#240,#241,#242,#243,#244,#245,#246,#247,#248,#249,#250,#251,#252,#253,#254,#255"
						.split("\\s*,\\s*"));
		config.put("stripComment", Boolean.TRUE);
		config.put("alwaysMakeTags", Boolean.TRUE);
//		extendHtmlFilter = new HTMLFilter(config,allowed);
//		vFilter = HTMLFilterStrategy.getExtendHtmlFilter();
		System.out.println(vFilter.filter("test <br> test <br>"));
		assertEquals(vFilter.filter("test <br> test <br>"), "test <br /> test <br />");
	}

	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString((0b1010101010101 ^ (0>>>16))));
	}
}