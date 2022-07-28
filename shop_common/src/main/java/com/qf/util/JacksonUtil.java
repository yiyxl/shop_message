package com.qf.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * json转换工具类
 */
public class JacksonUtil {

	/**
	 * 根据json中的key获取value值并将值转成字符串类型
	 * @param body json字符串
	 * @param field json中的key
	 * @return
	 */
	public static String parseString(String body, String field) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(body);
			JsonNode leaf = node.get(field);
			if (leaf != null)
				return leaf.asText();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据json中的key获取value值, 并将值转成集合类型,泛型是字符串
	 * @param body json字符串
	 * @param field json中集合的key
	 * @return
	 */
	public static List<String> parseStringList(String body, String field) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(body);
			JsonNode leaf = node.get(field);

			if (leaf != null)
				return mapper.convertValue(leaf, new TypeReference<List<String>>() {
				});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据json中的key获取value值, 并将值转成int类型
	 * @param body json字符串
	 * @param field json中集合的key
	 * @return
	 */
	public static Integer parseInteger(String body, String field) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(body);
			JsonNode leaf = node.get(field);
			if (leaf != null)
				return leaf.asInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据json中的key获取value值, 并将值转成集合类型,泛型是int类型
	 * @param body json字符串
	 * @param field json中集合的key
	 */
	public static List<Integer> parseIntegerList(String body, String field) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(body);
			JsonNode leaf = node.get(field);

			if (leaf != null)
				return mapper.convertValue(leaf, new TypeReference<List<Integer>>() {
				});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据json中的key获取value值, 并将值转成布尔类型
	 * @param body json字符串
	 * @param field json中集合的key
	 */
	public static Boolean parseBoolean(String body, String field) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(body);
			JsonNode leaf = node.get(field);
			if (leaf != null)
				return leaf.asBoolean();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据json中的key获取value值, 并将值转成short类型
	 * @param body json字符串
	 * @param field json中集合的key
	 */
	public static Short parseShort(String body, String field) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(body);
			JsonNode leaf = node.get(field);
			if (leaf != null) {
				Integer value = leaf.asInt();
				return value.shortValue();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据json中的key获取value值, 并将值转成byte类型
	 * @param body json字符串
	 * @param field json中集合的key
	 */
	public static Byte parseByte(String body, String field) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(body);
			JsonNode leaf = node.get(field);
			if (leaf != null) {
				Integer value = leaf.asInt();
				return value.byteValue();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据json中的key获取value值, 并将值转成class类型
	 * @param body json字符串
	 * @param field json中集合的key
	 */
	public static <T> T parseObject(String body, String field, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(body);
			node = node.get(field);
			return mapper.treeToValue(node, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object toNode(String json) {
		if (json == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode jsonNode = mapper.readTree(json);
			return jsonNode;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
