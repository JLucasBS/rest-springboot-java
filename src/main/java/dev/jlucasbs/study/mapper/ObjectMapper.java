//package dev.jlucasbs.study.mapper;
//
//import com.github.dozermapper.core.DozerBeanMapperBuilder;
//import com.github.dozermapper.core.Mapper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ObjectMapper {
//
//    private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();
//
//    public static <O, D> D parseObject(O origin, Class<D> destination) {
//        return mapper.map(origin, destination);
//    }
//
//    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
//        List<D> destinationsObjects = new ArrayList<>();
//
//        for (Object o : origin) {
//            destinationsObjects.add(parseObject(o, destination));
//        }
//
//        return destinationsObjects;
//    }
//}

package dev.jlucasbs.study.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapper {

    private static final ModelMapper mapper = new ModelMapper();

    static {
        mapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
    }

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        return origin.stream()
                .map(o -> parseObject(o, destination))
                .collect(Collectors.toList());
    }
}
