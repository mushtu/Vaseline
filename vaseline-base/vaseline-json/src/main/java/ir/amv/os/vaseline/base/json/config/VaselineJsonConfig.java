package ir.amv.os.vaseline.base.json.config;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;
import ir.amv.os.vaseline.base.json.server.annot.ExcludeFromJson;
import ir.amv.os.vaseline.base.json.server.polymorphysm.GsonPolymorphysmSerializerAndDeserializer;
import ir.amv.os.vaseline.base.json.server.polymorphysm.IVaselinePolymorphysmClassHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMV on 2/3/2016.
 */
@Configuration
public class VaselineJsonConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Autowired
    protected GsonBuilder gsonBuilder(
            List<IVaselinePolymorphysmClassHolder> polymorphysmClassHolders) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        GsonPolymorphysmSerializerAndDeserializer polymorphysmSerializerAndDeserializer = childSerializerAndDeserializer();
        List<Class<?>> allParentClasses = new ArrayList<Class<?>>();
        for (IVaselinePolymorphysmClassHolder polymorphysmClassHolder : polymorphysmClassHolders) {
            for (Class<?> parentClass : polymorphysmClassHolder.parentClasses()) {
                allParentClasses.add(parentClass);
                gsonBuilder.registerTypeAdapter(parentClass,
                        polymorphysmSerializerAndDeserializer);
            }
        }
        polymorphysmSerializerAndDeserializer.setAllParentClasses(allParentClasses);
        gsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                if (f.getAnnotation(ExcludeFromJson.class) != null) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        });
        // didn't work, stackoverflow!
        // GsonLazySerializerAndDeserializer gsonLazySerializer =
        // gsonLazySerializer();
        // gsonBuilder.registerTypeHierarchyAdapter(HibernateProxy.class,
        // gsonLazySerializer);
        // gsonBuilder.registerTypeHierarchyAdapter(PersistentSet.class,
        // gsonLazySerializer);
        // gsonBuilder.registerTypeHierarchyAdapter(PersistentList.class,
        // gsonLazySerializer);
        return gsonBuilder;
    }

    @Bean
    public GsonPolymorphysmSerializerAndDeserializer childSerializerAndDeserializer() {
        return new GsonPolymorphysmSerializerAndDeserializer();
    }

}
