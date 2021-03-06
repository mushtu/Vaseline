package ir.amv.os.vaseline.base.architecture.impl.hibernate.server.dozer.fieldmapper;

import ir.amv.os.vaseline.base.architecture.impl.hibernate.server.util.HibernateUtils;
import org.dozer.CustomFieldMapper;
import org.dozer.classmap.ClassMap;
import org.dozer.fieldmap.FieldMap;
import org.springframework.stereotype.Component;

@Component
public class HibernateLazyFieldMapper implements CustomFieldMapper {

    @Override
    public boolean mapField(Object source, Object destination,
                            Object sourceFieldValue, ClassMap classMap, FieldMap fieldMapping) {
        boolean shouldBeSetAsNull = !HibernateUtils.isLazyObjectInitialized(sourceFieldValue);

        if (shouldBeSetAsNull) {
            // Set destination to null, and tell dozer that the field is mapped
            destination = null;
            return true;
        } else {
            // Allow dozer to map as normal
            return false;
        }
    }
}