package net.sf.anathema.hero.individual.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.library.initialization.ObjectFactory;

public class PolymorphicTypeAdapterFactoryFactory {
	
	public static final String FIELD_STRING = "getJsonField";
	public static final String TYPE_STRING = "getJsonType";
	
	public static RuntimeTypeAdapterFactory<?>[] generateFactories(ObjectFactory finder, Class<?>... baseClasses) {
		List<RuntimeTypeAdapterFactory<?>> factories = new ArrayList<>();
		for (Class<?> clazz : baseClasses) {
			RuntimeTypeAdapterFactory<?> factory = generateFactory(finder, clazz);
			if (factory != null) {
				factories.add(factory);
			}
		}
		
		return factories.toArray(new RuntimeTypeAdapterFactory[0]);
	}
	
	private static <T> RuntimeTypeAdapterFactory<T> generateFactory(ObjectFactory finder, Class<T> baseClass) {
		String fieldString;
		try {
			fieldString = (String)baseClass.getMethod(FIELD_STRING).invoke(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		RuntimeTypeAdapterFactory<T> factory = 
				RuntimeTypeAdapterFactory.of(baseClass, fieldString);
		
		Collection<Class<? extends T>> implementations = finder.getAllImplementers(baseClass);
		for (Class<? extends T> implementingClass : implementations) {
			String typeString;
			try {
				typeString = (String)implementingClass.getMethod(TYPE_STRING).invoke(null);
				factory.registerSubtype(implementingClass, typeString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return factory;
	}
}
