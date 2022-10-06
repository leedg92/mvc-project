package mvc.project;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicContainer.*;
import static org.junit.jupiter.api.DynamicTest.*;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import annotation.Controller;
import annotation.Service;
import model.User;

/**
 * 
 * 
 * @Controller 어노테이션이 설정되어 있는 모든 클래스를 찾아서 출력한다.
 *
 */


public class ReflectionTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);
	
	@Test
	void controllerScan() throws Exception {
		Set<Class<?>> beans = getTypesAnnotationWith(List.of(Controller.class,Service.class));
		
		logger.debug("beans : [" + beans + "]");
			
	}
	
	@Test
	void showClass() throws Exception {
		Class<User> clazz = User.class;
		logger.debug(clazz.getName());
		logger.debug("User all declared fields : [" + Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
		logger.debug("User all declared constructors : [" + Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
		logger.debug("User all declared methods : [" + Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
		
	}
	
	@Test
	void load() throws Exception {
		//1
		Class<User> clazz = User.class;
		
		//2
		User user = new User("serverwizrd","홍종완");
		Class<? extends User> clazz2 = user.getClass();
		
		//3
		Class<?> clazz3 = Class.forName("model.User");
		
		logger.debug("clazz : [" + clazz + "]");
		logger.debug("clazz2 : [" + clazz2 + "]");
		logger.debug("clazz3 : [" + clazz3 + "]");
		
		assertThat(clazz == clazz2).isTrue();
		assertThat(clazz2 == clazz3).isTrue();
		assertThat(clazz3 == clazz).isTrue();
	}

	private Set<Class<?>> getTypesAnnotationWith(List<Class<? extends Annotation>> annotations) {
		Reflections reflections = new Reflections("controller");
		
		Set<Class<?>> beans = new HashSet<>();
		annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

//		beans.addAll(reflections.getTypesAnnotatedWith(Controller.class));
//		beans.addAll(reflections.getTypesAnnotatedWith(Service.class));
		return beans;
	}

}
