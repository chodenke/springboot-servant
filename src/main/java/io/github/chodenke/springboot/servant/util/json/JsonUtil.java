package io.github.chodenke.springboot.servant.util.json;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.github.chodenke.springboot.servant.util.datetime.DateTimeUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.time.LocalDateTime;

/**
 * <h2>Json 工具类</h2>
 * <h3>note:</h3>
 * <ul>
 *     <li>暂无</li>
 * </ul>
 *
 * <p>datetime: 2024/5/15 10:10</p>
 *
 * @author chodenke
 * @since JDK 17
 */
@Component
public class JsonUtil {

    /** 唯一 instance */
    private static JsonUtil instance;

    /**
     * jackson ObjectMapper 实例
     */
    @Resource
    private ObjectMapper mapper;

    /**
     * 私有化的构造方法
     */
    private JsonUtil() {
    }

    /**
     * 封装 jackson ObjectMapper 实例的 writeValueAsString(Object value) 方法
     *
     * @param value 要转换成 json 字符串的 实例
     * @return json 字符串
     * @throws JsonProcessingException all problems encountered when processing (parsing, generating) JSON content that are not pure I/O problems
     */
    @SuppressWarnings("unused")
    public static String writeValueAsString(Object value) throws JsonProcessingException {
        return instance.mapper.writeValueAsString(value);
    }

    /**
     * 封装 jackson ObjectMapper 实例的 readValue(String content, Class<T> valueType) 方法
     *
     * @param content   json 字符串内容
     * @param valueType 要转成的实例类型
     * @param <T>       实例类型
     * @return 转换成的实例
     * @throws JsonProcessingException all problems encountered when processing (parsing, generating) JSON content that are not pure I/O problems
     */
    @SuppressWarnings("unused")
    public static <T> T readValue(String content, Class<T> valueType) throws JsonProcessingException {
        return instance.mapper.readValue(content, valueType);
    }

    /**
     * 封装 jackson ObjectMapper 实例的 readTree(String content) 方法
     * @param content json 字符串内容
     * @return JsonNode 实例
     * @throws JsonProcessingException all problems encountered when processing (parsing, generating) JSON content that are not pure I/O problems
     */
    @SuppressWarnings("unused")
    public static JsonNode readTree(String content) throws JsonProcessingException {
        return instance.mapper.readTree(content);
    }

    /**
     * 封装 jackson ObjectMapper 实例的 readValue(Reader src, Class<T> valueType) 方法
     *
     * @param reader    输入流实例
     * @param valueType 要转成的实例类型
     * @param <T>       实例类型
     * @return 转换成的实例
     * @throws IOException 将输入流中的数据转成 json 实例时发生的 IOException
     */
    @SuppressWarnings("unused")
    public static <T> T readValue(Reader reader, Class<T> valueType) throws IOException {
        return instance.mapper.readValue(reader, valueType);
    }

    /**
     * 封装 jackson ObjectMapper 实例的 readValue(String content, JavaType valueType) 方法
     *
     * @param content   json 字符串内容
     * @param valueType 要转成的实例类型
     * @param <T>       实例类型
     * @return 转换成的实例
     * @throws JsonProcessingException JsonProcessingException
     * @throws JsonMappingException    JsonMappingException
     */
    @SuppressWarnings("unused")
    public static <T> T readValue(String content, JavaType valueType) throws JsonProcessingException, JsonMappingException {
        return instance.mapper.readValue(content, valueType);
    }

    /**
     * 封装 jackson ObjectMapper 实例的 readTree(InputStream in) 方法
     *
     * @param inputStream 输入流
     * @return JsonNode 实例
     * @throws IOException 将输入流中的数据转成 json 实例时发生的 IOException
     */
    @SuppressWarnings("unused")
    public static JsonNode readTree(InputStream inputStream) throws IOException {
        return instance.mapper.readTree(inputStream);
    }

    /** Accessor for getting currently configured TypeFactory instance. */
    @SuppressWarnings("unused")
    public static TypeFactory getTypeFactory() {
        return instance.mapper.getTypeFactory();
    }

    /**
     * 初始化
     */
    @PostConstruct
    private void init() {
        instance = this;
        final SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        instance.mapper.registerModule(module);
        instance.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * 处理LocalDateTime类型实例在导出json时序列化格式
     * note: 这里的实现是将 LocalDateTime类型实例 序列化成 Long 型的时间戳
     */
    private static class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

        protected LocalDateTimeSerializer() {
            super(LocalDateTime.class);
        }

        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            if (value != null) {
                gen.writeNumber(DateTimeUtil.toEpochMilliWithSystemDefaultZoneId(value));
            } else {
                gen.writeNull();
            }
        }
    }

    /**
     * json时反序列化时处理LocalDateTime类型实例
     * note: 这里的实现是将 Long 型的时间戳 反序列化成 LocalDateTime类型实例
     */
    private static class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

        protected LocalDateTimeDeserializer() {
            super(LocalDateTime.class);
        }

        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            final String currentValue = p.getValueAsString();
            try {
                final Long longValue = Long.parseLong(currentValue);
                return DateTimeUtil.ofEpochMilliWithSystemDefaultZoneId(longValue);
            } catch (
                    NumberFormatException ex) {
                throw new InvalidFormatException(p, "Unable to deserialize to LocalDateTime instance", p.getCurrentValue(), LocalDateTime.class);
            }
        }
    }
}
