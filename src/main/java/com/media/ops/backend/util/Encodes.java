/**
 * Copyright (c) 2005-2012 springside.org.cn
 */
package com.media.ops.backend.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringEscapeUtils;

/**
 * 封装各种格式的编码解码工具类. 1.Commons-Codec的 hex/base64 编码 2.自制的base62 编码 3.Commons-Lang的xml/html escape
 * 4.JDK提供的URLEncoder
 * 
 * @author calvin
 * @version 2013-01-15
 */
public class Encodes {

  private static final String DEFAULT_URL_ENCODING = "UTF-8";
  private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

  /**
   * Hex编码.
   */
  public static String encodeHex(byte[] input) {
    return new String(Hex.encodeHex(input));
  }

  /**
   * Hex解码.
   * 
   * @throws DecoderException
   */
  public static byte[] decodeHex(String input) throws DecoderException {
    return Hex.decodeHex(input.toCharArray());
  }

  /**
   * Base64编码.
   */
  public static String encodeBase64(byte[] input) {
    return new String(Base64.encodeBase64(input));
  }

  /**
   * Base64编码.
   */
  public static String encodeBase64(String input) {
    try {
      return new String(Base64.encodeBase64(input.getBytes(DEFAULT_URL_ENCODING)));
    } catch (UnsupportedEncodingException e) {
      return "";
    }
  }

  // /**
  // * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
  // */
  // public static String encodeUrlSafeBase64(byte[] input) {
  // return Base64.encodeBase64URLSafe(input);
  // }

  /**
   * Base64解码.
   */
  public static byte[] decodeBase64(String input) {
    return Base64.decodeBase64(input.getBytes());
  }

  /**
   * Base64解码.
   */
  public static String decodeBase64String(String input) {
    try {
      return new String(Base64.decodeBase64(input.getBytes()), DEFAULT_URL_ENCODING);
    } catch (UnsupportedEncodingException e) {
      return "";
    }
  }

  /**
   * Base62编码。
   */
  public static String encodeBase62(byte[] input) {
    char[] chars = new char[input.length];
    for (int i = 0; i < input.length; i++) {
      chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
    }
    return new String(chars);
  }


  /**
   * Xml 解码.
   */
  public static String unescapeXml(String xmlEscaped) {
    return StringEscapeUtils.unescapeXml(xmlEscaped);
  }

  /**
   * URL 编码, Encode默认为UTF-8.
   * 
   * @throws UnsupportedEncodingException
   */
  public static String urlEncode(String part) throws UnsupportedEncodingException {
    return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
  }

  /**
   * URL 解码, Encode默认为UTF-8.
   * 
   * @throws UnsupportedEncodingException
   */
  public static String urlDecode(String part) throws UnsupportedEncodingException {

    return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
  }
}
